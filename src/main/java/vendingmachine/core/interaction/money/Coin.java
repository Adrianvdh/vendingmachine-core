package vendingmachine.core.interaction.money;

import vendingmachine.core.money.Money;

public enum Coin implements Money {
    FIVE(5.0),
    TWO(2.0),
    ONE(1.0);

    private double value;

    Coin(double value) {
        this.value = value;
    }


    @Override
    public Double getMonetaryAmount() {
        return value;
    }
}
