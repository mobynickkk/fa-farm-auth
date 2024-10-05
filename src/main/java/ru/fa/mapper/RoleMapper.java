package ru.fa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import ru.fa.dto.RoleDto;
import ru.fa.persistence.entity.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(RoleEntity roleEntity);
    RoleEntity toEntity(RoleDto roleDto);
}
