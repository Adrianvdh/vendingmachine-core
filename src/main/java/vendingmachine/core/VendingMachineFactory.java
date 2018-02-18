package vendingmachine.core;

import vendingmachine.core.internal.SimpleVendingMachine;
import vendingmachine.core.internal.factory.SpecialItemBuilder;
import vendingmachine.core.interaction.Coin;
import vendingmachine.core.interaction.Note;
import vendingmachine.core.item.Item;
import vendingmachine.core.interaction.Money;
import vendingmachine.core.store.MoneyHolder;
import vendingmachine.core.store.Inventory;

import java.util.Arrays;

public class VendingMachineFactory {

    private MoneyHolder cashInventory;
    private Inventory<Item> goodsInventory;

    private VendingMachineFactory() {
        cashInventory = new MoneyHolder();
        goodsInventory = new Inventory<>();
    }

    public static VendingMachineFactory createVendingMachine() {
        return new VendingMachineFactory();
    }


    public VendingMachineFactory withNotes(Note... notes) {
        cashInventory.addMoney(notes);
        return this;
    }

    public VendingMachineFactory withMoneyOf(Money money, int quantity) {
        cashInventory.addOf(money, quantity);
        return this;
    }


    public VendingMachineFactory  withCoins(Coin... coins) {
        cashInventory.addMoney(coins);
        return this;
    }

    public VendingMachineFactory withItems(Item... items) {
        Arrays.asList(items).forEach(item -> goodsInventory.add(item));
        return this;
    }

    public VendingMachineFactory withItemOf(Item item, int quantity) {
        goodsInventory.addOf(item, quantity);
        return this;
    }

    public SpecialItemBuilder<VendingMachineFactory> withSpecial(Item... items) {
        return new SpecialItemBuilder<VendingMachineFactory>();
    }

    public VendingMachine build() {
        VendingMachine vendingMachine = new SimpleVendingMachine(goodsInventory, cashInventory);
        return vendingMachine;
    }


}
