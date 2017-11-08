package vendingmachine.interaction;

import vendingmachine.interaction.money.Change;
import vendingmachine.item.Item;

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
