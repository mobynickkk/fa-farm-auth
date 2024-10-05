package ru.fa.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fa.dto.RoleDto;
import ru.fa.dto.UserDto;

import java.time.Duration;
import java.util.Date;

@Service
public class JwtService {
    @Value("${app.name}")
    private String appName;
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.lifetime}")
    private Duration jwtLifetime;

    public String genJwt(UserDto userDto) {
        var roles = userDto.roles().stream().map(RoleDto::roleCode).toList();
        var issuedAt = new Date();
        return Jwts.builder()
                .setIssuer(appName)
                .claim("roles", roles)
                .setSubject(userDto.username())
                .setIssuedAt(issuedAt)
                .setExpiration(new Date(issuedAt.getTime() + jwtLifetime.toMillis()))
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(jwtSecret))
                .compact();
    }
}
