package vendingmachine.core.interaction;

import vendingmachine.core.interaction.money.Change;
import vendingmachine.core.interaction.special.CheapestItemFree;
import vendingmachine.core.item.Item;
import vendingmachine.core.item.selection.Coke;
import vendingmachine.core.store.Inventory;

import java.util.Arrays;

public class SpecialOffer {

    private Inventory<Item> goodsInventory;

    public SpecialOffer() {
        goodsInventory = new Inventory<>();
    }

    public SpecialOffer(Inventory<Item> goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    public void withItems(Item...items) {
        Arrays.asList(items).forEach(item -> goodsInventory.add(item));
    }

    public void setOfferPolicy(CheapestItemFree offerPolicy) {

    }

    public Order collectOrder() {
        return new Order(new Coke(), new Change());
    }
}
