package ru.fa.dto;

import lombok.Builder;

@Builder
public record AuthDto(String username, String password) { }
