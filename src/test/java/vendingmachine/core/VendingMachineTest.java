package vendingmachine.core;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import vendingmachine.core.factory.SpecialItemBuilder;
import vendingmachine.core.factory.VendingMachineFactory;
import vendingmachine.core.interaction.Order;
import vendingmachine.core.interaction.item.exception.SoldOutException;
import vendingmachine.core.interaction.money.Change;
import vendingmachine.core.interaction.money.Coin;
import vendingmachine.core.interaction.money.Note;
import vendingmachine.core.interaction.money.exception.NotFullPaidException;
import vendingmachine.core.interaction.money.exception.NotSufficientChangeException;
import vendingmachine.core.item.Item;
import vendingmachine.core.item.selection.Chocolate;
import vendingmachine.core.item.selection.Coke;
import vendingmachine.core.item.selection.Fanta;
import vendingmachine.core.item.selection.LaysChips;

import java.util.Collection;

import static vendingmachine.core.interaction.money.Note.ONE_HUNDRED;
import static vendingmachine.core.interaction.money.Note.TEN;

public class VendingMachineTest {

    @Test
    public void listAvailableItems() {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .withItems(new Coke(), new Chocolate())
                .build();

        Collection<Item> instockItems = vendingMachine.getInstockItems();

        Assert.assertThat(instockItems, Matchers.containsInAnyOrder(new Coke(), new Chocolate()));
    }

    @Test
    public void selectItemAndGetPriceOfItem() throws SoldOutException {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .withItems(new Coke())
                .withNotes(Note.TEN)
                .build();
        Item coldDrink = new Coke();

        double itemPrice = vendingMachine.selectItemAndGetPrice(coldDrink);

        Assert.assertThat(itemPrice, CoreMatchers.is(9.0));
    }

    @Test(expected = SoldOutException.class)
    public void selectItemAndExpectItemToBeSoldOut() throws SoldOutException {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .build();
        Item coldDrink = new Fanta();

        vendingMachine.selectItemAndGetPrice(coldDrink);
    }

    @Test
    public void insertMoney() {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .build();

        vendingMachine.insertCoin(Coin.FIVE, Coin.TWO);
        vendingMachine.insertNote(TEN);

        Assert.assertThat(vendingMachine.getCurrentBalance(), CoreMatchers.is(17.0));
    }

    @Test
    public void insertMoneyAndGetRefund() {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .build();
        vendingMachine.insertCoin(Coin.FIVE);
        vendingMachine.insertNote(TEN);

        Change change = vendingMachine.refundAndReturnChange();

        Assert.assertThat(change.getValue(), CoreMatchers.is(15.0));
    }

    @Test
    public void collectItemAndChange() throws SoldOutException, NotFullPaidException {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .withItems(new Coke())
                .withCoins(Coin.ONE)
                .build();
        vendingMachine.insertNote(TEN);
        vendingMachine.selectItemAndGetPrice(new Coke());

        Order itemOrder = vendingMachine.collectItemOrder();

        Assert.assertThat(itemOrder.getItem(), CoreMatchers.is(new Coke()));
        Assert.assertThat(itemOrder.getChange().getValue(), CoreMatchers.is(1.0));
    }

    @Test(expected = NotFullPaidException.class)
    public void collectItemWhenHasNotPaidFull() throws SoldOutException, NotFullPaidException {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .withItems(new Coke())
                .build();
        vendingMachine.selectItemAndGetPrice(new Coke());

        vendingMachine.collectItemOrder();

    }

    @Test(expected = NotSufficientChangeException.class)
    public void collectItemAndNotSufficientChange() throws SoldOutException, NotFullPaidException {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .withItems(new Chocolate())
                .withNotes(Note.TWENTY)
                .withCoins(Coin.FIVE, Coin.ONE)
                .build();
        vendingMachine.insertNote(ONE_HUNDRED);

        vendingMachine.selectItemAndGetPrice(new Chocolate());

        vendingMachine.collectItemOrder();
    }


    @Test
    public void listInstockItemsAndIncludeSpecials() throws Exception {
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachine()
                .withSpecial(new Coke(), new LaysChips())
                    .ofCombo(SpecialItemBuilder.Combo.CHEAPEST_ONE_FREE) //use strategy pattern
                .and()
                .withItems(new Chocolate())
                .withNotes(Note.TWENTY)
                .build();


    }
}
