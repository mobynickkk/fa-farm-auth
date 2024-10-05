package ru.fa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.fa.dto.RoleDto;
import ru.fa.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String authorize(UserDto userDto) throws AuthenticationException {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password()));
        return jwtService.genJwt(userDto.toBuilder().roles(getRelevantRoles(auth)).build());
    }

    private static List<RoleDto> getRelevantRoles(Authentication auth) {
        return auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(it -> RoleDto.builder().roleCode(it).build())
                .toList();
    }
}
