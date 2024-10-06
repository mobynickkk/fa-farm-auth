package ru.fa.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fa.dto.RoleDto;
import ru.fa.dto.UserDto;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    @Value("${app.name}")
    private String appName;
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.lifetime}")
    private Duration jwtLifetime;

    public String genJwt(UserDto userDto) {
        var issuedAt = new Date();
        return Jwts.builder()
                .setIssuer(appName)
                .setSubject(userDto.username())
                .setIssuedAt(issuedAt)
                .setExpiration(new Date(issuedAt.getTime() + jwtLifetime.toMillis()))
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(jwtSecret))
                .compact();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(TextCodec.BASE64.decode(jwtSecret))
                .parseClaimsJws(token)
                .getBody();
    }
}
