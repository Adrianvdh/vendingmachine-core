package vendingmachine.interaction.money;

import vendingmachine.money.Money;
import vendingmachine.money.MoneyHolder;

import java.util.Collection;

public class Change {

    private MoneyHolder moneyHolder = new MoneyHolder();

    public Change addAll(Collection<Money> moneyCollection) {
        moneyCollection.forEach(moneyHolder::add);
        return this;
    }

    public double getValue() {
        return moneyHolder.getTotalAmount();
    }
}
