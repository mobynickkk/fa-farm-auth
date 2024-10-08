package ru.fa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.fa.dto.AuthDto;
import ru.fa.dto.JwtDto;
import ru.fa.dto.RoleDto;
import ru.fa.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtDto authorize(AuthDto authDto) throws AuthenticationException {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password()));
        var token = jwtService.genJwt(
                UserDto.builder()
                        .username(authDto.username())
                        .roles(getRelevantRoles(auth))
                        .build());
        return JwtDto.of(token);
    }

    private static List<RoleDto> getRelevantRoles(Authentication auth) {
        return auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(it -> RoleDto.builder().roleCode(it).build())
                .toList();
    }
}
