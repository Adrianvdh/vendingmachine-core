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

    private MoneyHolder cashInventory = new MoneyHolder();
    private Inventory<Item> goodsInventory = new Inventory<>();

    public static VendingMachineBuilder createVendingMachine() {
        return new VendingMachineBuilder();
    }


    public VendingMachineBuilder withNotes(Note... notes) {
        cashInventory.addMoney(notes);
        return this;
    }

    public VendingMachineBuilder withMoneyOf(Money money, int quantity) {
        cashInventory.addOf(money, quantity);
        return this;
    }


    public VendingMachineBuilder  withCoins(Coin... coins) {
        cashInventory.addMoney(coins);
        return this;
    }

    public VendingMachineBuilder withItems(Item... items) {
        Arrays.asList(items).forEach(item -> goodsInventory.add(item));
        return this;
    }

    public VendingMachineBuilder withItemOf(Item item, int quantity) {
        goodsInventory.addOf(item, quantity);
        return this;
    }

    public VendingMachine build() {
        VendingMachine vendingMachine = new SimpleVendingMachine(goodsInventory, cashInventory);
        return vendingMachine;
    }
}
