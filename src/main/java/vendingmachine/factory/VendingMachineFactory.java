package vendingmachine.factory;

import vendingmachine.SimpleVendingMachine;
import vendingmachine.VendingMachine;
import vendingmachine.interaction.money.Coin;
import vendingmachine.interaction.money.Note;
import vendingmachine.item.Item;
import vendingmachine.money.MoneyHolder;
import vendingmachine.store.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachineFactory {

    private List<Note> notes = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    private MoneyHolder cashInventory;
    private Inventory<Item> goodsInventory;

    public static VendingMachineFactory createVendingMachine() {
        return new VendingMachineFactory();
    }


    public VendingMachineFactory withNotes(Note... notes) {
        Arrays.asList(notes).forEach(note -> this.notes.add(note));

        return this;
    }

    public VendingMachineFactory withCoins(Coin... coins) {
        Arrays.asList(coins).forEach(coin -> this.coins.add(coin));

        return this;
    }

    public VendingMachineFactory withItems(Item... items) {
        Arrays.asList(items).forEach(item -> this.items.add(item));

        return this;
    }

    public VendingMachine build() {
        provideGoodsInventory();
        provideCashInventory();

        VendingMachine vendingMachine = new SimpleVendingMachine(goodsInventory, cashInventory);
        return vendingMachine;
    }

    private void provideCashInventory() {
        cashInventory = new MoneyHolder();
        cashInventory.addMoney(notes.toArray(new Note[] {}));
        cashInventory.addMoney(coins.toArray(new Coin[] {}));
    }

    private void provideGoodsInventory() {
        goodsInventory = new Inventory<>();
        items.forEach(item -> goodsInventory.add(item));
    }
}
