package vendingmachine.builder;

import vendingmachine.SimpleVendingMachine;
import vendingmachine.VendingMachine;
import vendingmachine.interaction.money.Coin;
import vendingmachine.interaction.money.Note;
import vendingmachine.item.Item;
import vendingmachine.item.selection.Chocolate;
import vendingmachine.item.selection.Coke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachineBuilder {

    private List<Note> notes = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public static VendingMachineBuilder createVendingMachine() {
        return new VendingMachineBuilder();
    }


    public VendingMachineBuilder withNotes(Note... notes) {
        Arrays.asList(notes).forEach(note -> this.notes.add(note));

        return this;
    }

    public VendingMachineBuilder  withCoins(Coin... coins) {
        Arrays.asList(coins).forEach(coin -> this.coins.add(coin));

        return this;
    }

    public VendingMachineBuilder withItems(Item... items) {
        Arrays.asList(items).forEach(item -> this.items.add(item));

        return this;
    }

    public VendingMachine build() {
        VendingMachine vendingMachine = new SimpleVendingMachine();
        vendingMachine.addMoneyToInventory(notes.toArray(new Note[] {}));
        vendingMachine.addMoneyToInventory(coins.toArray(new Coin[] {}));
        vendingMachine.addItemToInventory(items.toArray(new Item[] {}));
        return vendingMachine;
    }
}
