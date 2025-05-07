package me.despical.purchasechecker.exceptions;

/**
 * @author Despical
 * <p>
 * Created at 7.05.2025
 */
public final class InvalidTokenException extends VerifyException {

    public InvalidTokenException() {
        super("The token is invalid or has expired. Please try verifying again.");
    }
}
