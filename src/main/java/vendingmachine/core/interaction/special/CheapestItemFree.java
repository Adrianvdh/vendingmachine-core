package vendingmachine.core.interaction.special;

import vendingmachine.core.item.Item;
import vendingmachine.core.item.selection.Chocolate;
import vendingmachine.core.item.selection.Coke;
import vendingmachine.core.store.Inventory;

import java.util.Arrays;

public class CheapestItemFree {

    Inventory<Item> itemInventory = new Inventory<Item>();

    public void withEligibleItems(Item... items) {
        Arrays.asList(items).forEach(item -> itemInventory.add(item));
    }

    public Item claimSpecial() {
        Item cheapest = null;
        for(Item item : itemInventory.getAvailableItems()) {
            if(cheapest == null || item.getPrice() < cheapest.getPrice())
                cheapest = item;
        }
        return cheapest;
    }
}
