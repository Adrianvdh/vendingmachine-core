package interaction;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import vendingmachine.VendingMachine;
import vendingmachine.builder.VendingMachineBuilder;
import vendingmachine.interaction.money.Coin;
import vendingmachine.interaction.money.Note;
import vendingmachine.item.selection.Chocolate;
import vendingmachine.item.selection.Coke;
import vendingmachine.item.selection.Fanta;

import java.io.PrintStream;

public class InteractiveVendingMachineTest {

    VendingMachine vendingMachine;

    @Before
    public void setUp() throws Exception {
        vendingMachine = VendingMachineBuilder.createVendingMachine()
                .withItemOf(new Coke(), 7)
                .withItemOf(new Fanta(), 5)
                .withItemOf(new Chocolate(), 3)
                .withMoneyOf(Note.TWENTY, 4)
                .withMoneyOf(Note.TEN, 7)
                .withMoneyOf(Coin.FIVE, 4)
                .withMoneyOf(Coin.TWO, 11)
                .withMoneyOf(Coin.ONE, 7)
                .build();
    }

    @Test
    public void listAvailableItems() throws Exception {
        InteractiveVendingMachine interactiveVendingMachine = new InteractiveVendingMachine(vendingMachine, System.out);

        PrintStream printStream = interactiveVendingMachine.showAvailableItems();

        Assert.assertThat("Coke @ R9.0", CoreMatchers.is(printStream.toString()));
    }
}
