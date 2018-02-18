package vendingmachine.core.internal.factory;

public class SpecialItemBuilder<T> extends AbstractBuilderConfigurer<T> {

    public enum Combo {
        CHEAPEST_ONE_FREE
    }

    public SpecialItemBuilder<T> ofCombo(Combo combo) {
        getBuilder();
        return this;
    }

}
