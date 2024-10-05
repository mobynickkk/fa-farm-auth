package ru.fa.dto;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record UserDto(String username,
                      String password,
                      String firstName,
                      String secondName,
                      String email,
                      String phone,
                      List<RoleDto> roles) { }
