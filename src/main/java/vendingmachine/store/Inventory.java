package vendingmachine.store;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory<T> {

    protected Map<T, Integer> inventory = new HashMap<>();

    public Set<T> getAvailableItems() {
        return inventory.entrySet().stream()
                .map(item -> item.getKey())
                .filter(item -> inventoryHasItem(item))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public boolean hasItem(T item) {
        if(inventoryHasItem(item))
            return true;
        else if(hasRecordButNoQuantityOfItem(item)) {
            inventory.remove(item);
        }
        return false;
    }

    public void addOf(T item, int quantity) {
        inventory.put(item, quantity);
    }

    public void add(T item) {
        Integer quantity = inventory.getOrDefault(item, 0);
        inventory.put(item, ++quantity);
    }

    public void deduct(T item) {
        Integer quantity = inventory.getOrDefault(item, 0);
        inventory.put(item, --quantity);
    }

    public void clear() {
        inventory.clear();
    }



    private boolean inventoryHasItem(T item) {
        return inventory.containsKey(item) && inventory.get(item) > 0;
    }

    private boolean hasRecordButNoQuantityOfItem(T item) {
        return inventory.containsKey(item) && inventory.get(item) == 0;
    }
}
