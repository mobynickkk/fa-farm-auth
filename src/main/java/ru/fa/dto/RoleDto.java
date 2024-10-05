package ru.fa.dto;

import lombok.Builder;

@Builder
public record RoleDto(String roleCode, String roleName) { }
