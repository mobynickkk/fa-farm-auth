package ru.fa.dto;

public record JwtDto(String token) {
    public static JwtDto of(String token) {
        return new JwtDto(token);
    }
}
