package me.despical.purchasechecker.exceptions;

/**
 * @author Despical
 * <p>
 * Created at 2.05.2025
 */
public final class NoSuchRoleException extends VerifyException {

    public NoSuchRoleException(Long pluginId) {
        super("No such role found for plugin " + pluginId);
    }
}
