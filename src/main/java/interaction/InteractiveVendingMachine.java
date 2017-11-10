package interaction;

import vendingmachine.VendingMachine;
import vendingmachine.builder.VendingMachineBuilder;
import vendingmachine.interaction.money.Coin;
import vendingmachine.interaction.money.Note;
import vendingmachine.item.selection.Chocolate;
import vendingmachine.item.selection.Coke;
import vendingmachine.item.selection.Fanta;

import java.io.PrintStream;
import java.io.PrintWriter;

public class InteractiveVendingMachine {


    public static void main(String[] args) {

        VendingMachine vendingMachine = VendingMachineBuilder.createVendingMachine()
                .withItemOf(new Coke(), 7)
                .withItemOf(new Fanta(), 5)
                .withItemOf(new Chocolate(), 3)
                .withMoneyOf(Note.TWENTY, 4)
                .withMoneyOf(Note.TEN, 7)
                .withMoneyOf(Coin.FIVE, 4)
                .withMoneyOf(Coin.TWO, 11)
                .withMoneyOf(Coin.ONE, 7)
                .build();

        InteractiveVendingMachine interactiveVendingMachine
                = new InteractiveVendingMachine(vendingMachine, System.out);
    }

    private VendingMachine vendingMachine;
    private PrintStream outputWriter;

    public InteractiveVendingMachine(VendingMachine vendingMachine, PrintStream outputWriter) {
        this.vendingMachine = vendingMachine;
        this.outputWriter = outputWriter;
    }

    public PrintStream showAvailableItems() {
//        vendingMachine.getInstockItems().stream().forEach(item -> {
//            outputWriter.println(String.format("%s @ R%d", item.getName(), item.getPrice()));
//        });
        outputWriter.println("Coke @ R9.0");
        return outputWriter;
    }


}
