package vendingmachine.builder;

import vendingmachine.SimpleVendingMachine;
import vendingmachine.VendingMachine;
import vendingmachine.interaction.money.Coin;
import vendingmachine.interaction.money.Note;
import vendingmachine.item.Item;
import vendingmachine.money.Money;
import vendingmachine.money.MoneyHolder;
import vendingmachine.store.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class VendingMachineBuilder {

    private List<Money> monies = new ArrayList<>();
    private List<Note> notes = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    private MoneyHolder cashInventory;
    private Inventory<Item> goodsInventory;

    public static VendingMachineBuilder createVendingMachine() {
        return new VendingMachineBuilder();
    }


    public VendingMachineBuilder withNotes(Note... notes) {
        Arrays.asList(notes).forEach(note -> this.notes.add(note));
        return this;
    }

    public VendingMachineBuilder withMoneyOf(Money money, int quantity) {
        for(int i = 0; i < quantity; i++)
            monies.add(money);

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

    public VendingMachineBuilder withItemOf(Item item, int quantity) {
        for(int i = 0; i < quantity; i++)
            items.add(item);

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
