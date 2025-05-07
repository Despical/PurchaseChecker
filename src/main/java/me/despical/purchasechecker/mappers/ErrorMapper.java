package me.despical.purchasechecker.mappers;

import me.despical.purchasechecker.entities.VerificationError;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Despical
 * <p>
 * Created at 2.05.2025
 */
@Mapper(componentModel = "spring")
public interface ErrorMapper {

    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    VerificationError toEntity(String token, String message);
}
