package me.despical.purchasechecker.dtos;

import me.despical.purchasechecker.entities.Role;

/**
 * @author Despical
 * <p>
 * Created at 19.04.2025
 */
public record UserDto(Long id, String username, Role role) {
}
