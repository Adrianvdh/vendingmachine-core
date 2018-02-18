package vendingmachine.core.internal.factory;

class AbstractBuilderConfigurer<T> {

    private T builder;

    protected final T getBuilder() {
        if (builder == null) {
            throw new IllegalStateException("parent builder T cannot be null");
        }
        return builder;
    }

    protected T and() {
        return getBuilder();
    }

}
