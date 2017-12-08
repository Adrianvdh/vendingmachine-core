package vendingmachine.core.factory;

public class AbstractBuilderConfigurer<T> {

    private T builder;

    protected final T getBuilder() {
        if (builder == null) {
            throw new IllegalStateException("parent builder T cannot be null");
        }
        return builder;
    }

    public T and() {
        return getBuilder();
    }

}
