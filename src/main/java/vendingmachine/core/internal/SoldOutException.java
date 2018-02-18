package vendingmachine.core.internal;

public class SoldOutException extends Exception {
    public SoldOutException(String message) {
        super(message);
    }

    public SoldOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
