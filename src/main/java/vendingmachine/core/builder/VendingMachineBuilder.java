package vendingmachine.core.builder;

import vendingmachine.core.SimpleVendingMachine;
import vendingmachine.core.VendingMachine;
import vendingmachine.core.interaction.money.Coin;
import vendingmachine.core.interaction.money.Note;
import vendingmachine.core.item.Item;
import vendingmachine.core.money.Money;
import vendingmachine.core.money.MoneyHolder;
import vendingmachine.core.store.Inventory;

import java.util.Arrays;

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
