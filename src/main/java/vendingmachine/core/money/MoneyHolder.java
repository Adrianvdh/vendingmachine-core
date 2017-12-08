package vendingmachine.core.money;

import vendingmachine.core.store.Inventory;

import java.util.Arrays;
import java.util.Map;

public class MoneyHolder extends Inventory<Money> {

    public void addMoney(Money... money) {
        Arrays.stream(money).forEach(m -> add(m));
    }

    public double getTotalAmount() {
        double summingAmount = 0.0;
        return multipleAllItemMonetaryAmountByItemQuantity(summingAmount);
    }

    private double multipleAllItemMonetaryAmountByItemQuantity(double summingAmount) {
        for(Map.Entry<Money, Integer> item : inventory.entrySet()) {
            Double itemAmount = item.getKey().getMonetaryAmount();
            int itemQuantity = item.getValue();
            summingAmount += itemAmount * itemQuantity;
        }
        return summingAmount;
    }
}
