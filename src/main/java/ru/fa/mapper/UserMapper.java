package ru.fa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.fa.dto.UserDto;
import ru.fa.persistence.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userDto.password()))")
    UserEntity toEntity(UserDto userDto, PasswordEncoder passwordEncoder);
}
