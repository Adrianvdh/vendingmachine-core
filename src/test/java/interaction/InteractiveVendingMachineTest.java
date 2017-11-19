package interaction;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InteractiveVendingMachineTest {

    VendingMachine vendingMachine;

    TextIO textIO;

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

        textIO = TextIoFactory.getTextIO();
    }

    @Test
    public void listAvailableItems() {
        InteractiveVendingMachine interactiveVendingMachine = new InteractiveVendingMachine(vendingMachine);

        Collection<String> actualItems = interactiveVendingMachine.listAvailableItems();

        Collection<String> expectedItems = vendingMachine.getInstockItems().stream().map(item -> item.getName()).collect(Collectors.toList());
        Assert.assertEquals(expectedItems, actualItems);
    }
}
