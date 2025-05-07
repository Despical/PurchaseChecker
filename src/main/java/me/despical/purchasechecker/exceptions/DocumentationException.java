package me.despical.purchasechecker.exceptions;

/**
 * @author Despical
 * <p>
 * Created at 7.05.2025
 */
public abstract class DocumentationException extends RuntimeException {

    public DocumentationException() {
        super();
    }

    public DocumentationException(String message) {
        super(message);
    }
}
