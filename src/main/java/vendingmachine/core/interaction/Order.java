package vendingmachine.core.interaction;

import vendingmachine.core.interaction.money.Change;
import vendingmachine.core.item.Item;

public class Order {
    private Item selectedItem;
    private Change returningChange;

    public Order(Item item, Change change) {
        this.selectedItem = item;
        this.returningChange = change;
    }


    public Item getItem() {
        return selectedItem;
    }

    public Change getChange() {
        return returningChange;
    }
}
