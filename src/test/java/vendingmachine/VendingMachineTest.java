package vendingmachine;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import vendingmachine.interaction.Order;
import vendingmachine.interaction.item.exception.SoldOutException;
import vendingmachine.interaction.money.Change;
import vendingmachine.interaction.money.exception.NotSufficientChangeException;
import vendingmachine.item.Item;
import vendingmachine.item.selection.Chocolate;
import vendingmachine.item.selection.Coke;
import vendingmachine.item.selection.Fanta;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static vendingmachine.interaction.money.Coin.FIVE;
import static vendingmachine.interaction.money.Coin.TWO;
import static vendingmachine.interaction.money.Note.ONE_HUNDRED;
import static vendingmachine.interaction.money.Note.TEN;

public class VendingMachineTest {


    @Test
    public void listAvailableItems() throws Exception {
        VendingMachine vendingMachine = new SimpleVendingMachine();

        Collection<Item> instockItems = vendingMachine.getInstockItems();

        Assert.assertThat(instockItems, Matchers.containsInAnyOrder(new Coke(), new Chocolate()));
    }

    @Test
    public void selectItemAndGetPriceOfItem() {
        VendingMachine vendingMachine = new SimpleVendingMachine();
        Item coldDrink = new Coke();

        double itemPrice = vendingMachine.selectItemAndGetPrice(coldDrink);

        Assert.assertThat(itemPrice, CoreMatchers.is(9.0));
    }

    @Test(expected = SoldOutException.class)
    public void selectItemAndExpectItemToBeSoldOut() throws Exception {
        VendingMachine vendingMachine = new SimpleVendingMachine();
        Item coldDrink = new Fanta();

        vendingMachine.selectItemAndGetPrice(coldDrink);
    }

    @Test
    public void insertMoney() throws Exception {
        VendingMachine vendingMachine = new SimpleVendingMachine();

        vendingMachine.insertCoin(FIVE, TWO);
        vendingMachine.insertNote(TEN);

        Assert.assertThat(vendingMachine.getCurrentBalance(), CoreMatchers.is(17.0));
    }

    @Test
    public void insertMoneyAndGetRefund() throws Exception {
        VendingMachine vendingMachine = new SimpleVendingMachine();
        vendingMachine.insertCoin(FIVE);
        vendingMachine.insertNote(TEN);

        Change change = vendingMachine.refundAndReturnChange();

        Assert.assertThat(change.getValue(), CoreMatchers.is(15.0));
    }

    @Test
    public void collectItemAndChange() throws Exception {
        VendingMachine vendingMachine = new SimpleVendingMachine();
        vendingMachine.insertNote(TEN);
        vendingMachine.selectItemAndGetPrice(new Coke());

        Order itemOrder = vendingMachine.collectItemOrder();

        Assert.assertThat(itemOrder.getItem(), CoreMatchers.is(new Coke()));
        Assert.assertThat(itemOrder.getChange().getValue(), CoreMatchers.is(1.0));
    }

    @Test(expected = NotSufficientChangeException.class)
    public void collectItemAndNotSufficientChange() throws Exception {
        VendingMachine vendingMachine = new SimpleVendingMachine();
        vendingMachine.insertNote(ONE_HUNDRED);

        vendingMachine.selectItemAndGetPrice(new Chocolate());

        vendingMachine.collectItemOrder();

        // The vending machine has a R20 note, R5 coin and a R1 coin.
        // The returning change amount required is R15.0, which cannot be made-up from
        // then machine's cash inventory.
    }

    @Test(expected = SoldOutException.class)
    public void resetAndTrySelectItem() throws Exception {
        VendingMachine vendingMachine = new SimpleVendingMachine();

        vendingMachine.reset();
        vendingMachine.insertCoin(FIVE, FIVE);

        vendingMachine.selectItemAndGetPrice(new Coke());
    }
}
