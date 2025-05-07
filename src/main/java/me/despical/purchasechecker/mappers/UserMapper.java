package me.despical.purchasechecker.mappers;

import me.despical.purchasechecker.dtos.UserDto;
import me.despical.purchasechecker.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Despical
 * <p>
 * Created at 19.04.2025
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "role", constant = "USER")
    User toEntity(Long id, String username);
}
