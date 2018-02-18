package vendingmachine.core.interaction;

import vendingmachine.core.interaction.Money;

public enum Note implements Money {

    TWO_HUNDRED(200.00),
    ONE_HUNDRED(100.00),
    FIFTY(50.0),
    TWENTY(20.0),
    TEN(10.0);

    private double value;

    Note(double value) {
         this.value = value;
    }

    @Override
    public Double getMonetaryAmount() {
        return value;
    }
}
