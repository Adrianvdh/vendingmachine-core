package vendingmachine.money;

import vendingmachine.store.Inventory;

import java.util.Arrays;
import java.util.Map;

public class MoneyHolder extends Inventory<Money> {

    public void addMoney(Money... money) {
        Arrays.stream(money).forEach(m -> add(m));
    }

    public double getTotalAmount() {
        double summingAmount = 0;
        for(Map.Entry<Money, Integer> item : inventory.entrySet()) {
            Double itemAmount = item.getKey().getMonetaryAmount();
            int itemQuantity = item.getValue();
            summingAmount += itemAmount * itemQuantity;
        }
        return summingAmount;
    }
}
