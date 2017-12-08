package vendingmachine.core.interaction.money;

import vendingmachine.core.money.Money;
import vendingmachine.core.money.MoneyHolder;

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
