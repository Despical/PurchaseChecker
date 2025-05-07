package me.despical.purchasechecker.exceptions;

/**
 * @author Despical
 * <p>
 * Created at 7.05.2025
 */
public class NoSuchDocumentationException extends DocumentationException {

    public NoSuchDocumentationException() {
        super("No such documentation found the given with name.");
    }
}
