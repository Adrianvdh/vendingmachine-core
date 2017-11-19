package interaction;

import interaction.cli.CLI;
import interaction.cli.CLIFactory;
import org.beryx.textio.GenericInputReader;
import org.beryx.textio.InputReader;
import vendingmachine.VendingMachine;
import vendingmachine.builder.VendingMachineBuilder;
import vendingmachine.interaction.money.Coin;
import vendingmachine.interaction.money.Note;
import vendingmachine.item.Item;
import vendingmachine.item.selection.Chocolate;
import vendingmachine.item.selection.Coke;
import vendingmachine.item.selection.Fanta;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class InteractiveVendingMachine {


    private VendingMachine vendingMachine;
    private CLI cli;

    {
        cli = CLIFactory.newCLI();
    }

    public InteractiveVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public Collection<String> listAvailableItems() {
        return cli
                .<Item>listItemSelection(vendingMachine.getInstockItems(), item ->
                        String.format("%s @ R%d", item.getName(), item.getPrice()))
                .show();
    }

    static void showAvailableItems(VendingMachine vendingMachine, InputReader reader) {

        reader
                .withNumberedPossibleValues(vendingMachine.getInstockItems())
                .read("Select an item from the machine:");

    }

}
