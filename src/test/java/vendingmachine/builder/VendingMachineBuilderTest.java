package vendingmachine.builder;

import org.junit.Test;
import vendingmachine.VendingMachine;
import vendingmachine.interaction.item.exception.SoldOutException;
import vendingmachine.interaction.money.Coin;
import vendingmachine.interaction.money.Note;
import vendingmachine.item.selection.Coke;

public class VendingMachineBuilderTest {

    @Test(expected = SoldOutException.class)
    public void createVendingMachineWithMoneyAndNoItems() throws Exception {
        VendingMachine vendingMachine = VendingMachineBuilder.createVendingMachine()
                .withNotes(Note.TWENTY)
                .withCoins(Coin.FIVE, Coin.ONE)
                .build();

        vendingMachine.selectItemAndGetPrice(new Coke());
    }

    @Test
    public void createVendingMachineWithMoney() throws Exception {
    }
}
