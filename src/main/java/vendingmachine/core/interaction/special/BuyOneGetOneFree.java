package vendingmachine.core.interaction.special;

import vendingmachine.core.item.Item;
import vendingmachine.core.store.Inventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

public class BuyOneGetOneFree {

    Inventory<Item> registeredItems = new Inventory<Item>();
    Inventory<Item> selectedItems = new Inventory<Item>();


    public void registerEligibleItemsFromSystem(Item... items) {
        Arrays.asList(items).forEach(item -> registeredItems.add(item));
    }


    public void selectItem(Item... items) {
        Arrays.asList(items).forEach(item -> selectedItems.add(item));
    }

    public Collection<Item> claimSpecial() {

        Collection<Item> paidAndSpecialItems = new LinkedHashSet<>();
        for(Item selectedItem : selectedItems) {
            Integer itemQuantity = selectedItems.getItemQuantity(selectedItem);

            while(itemQuantity % 2 != 0) {
                paidAndSpecialItems.add(selectedItem);
            }

        }

        return paidAndSpecialItems;
    }
}
