package me.despical.purchasechecker.exceptions;

/**
 * @author Despical
 * <p>
 * Created at 7.05.2025
 */
public abstract class VerifyException extends RuntimeException {

    public VerifyException() {
        super();
    }

    public VerifyException(String message) {
        super(message);
    }
}
