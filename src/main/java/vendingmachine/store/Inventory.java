package vendingmachine.store;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Inventory<T> {

    protected Map<T, Integer> inventory = new HashMap<>();

    public Set<T> getAvailableItems() {
        return inventory.entrySet().stream()
                .filter(item -> inventoryHasItem(item.getKey()))
                .map(item -> item.getKey())
                .collect(Collectors.toSet());

//        Set<T> availableItems = new HashSet<>();
//        for (Map.Entry<T, Integer> item : inventory.entrySet()) {
//            if(inventoryHasItem(item.getKey()))
//                availableItems.add(item.getKey());
//        }
//        return availableItems;
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
