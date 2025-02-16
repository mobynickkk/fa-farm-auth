package ru.fa.dto;

import lombok.Builder;

@Builder
public record AuthDto(String username, String password) {

    public static AuthDto of(UserDto userDto) {
        return new AuthDto(userDto.username(), userDto.password());
    }
}
