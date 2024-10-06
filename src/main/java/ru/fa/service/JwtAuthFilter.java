package ru.fa.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTH_HEADER = "Authorization";

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var authHeader = Optional.ofNullable(request.getHeader(AUTH_HEADER)).filter(StringUtils::isNotEmpty);
        if (SecurityContextHolder.getContext().getAuthentication() != null || authHeader.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = authHeader
                .filter(auth -> auth.startsWith(BEARER_PREFIX))
                .map(auth -> auth.substring(BEARER_PREFIX.length()));
        try {
            var username = token.map(jwtService::getUsername);
            var userDetails = username.map(userService::loadUserByUsername);
            var internalToken = userDetails
                    .map(user -> new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()))
                    .orElseThrow(() -> new BadCredentialsException("Не удалось авторизовать пользователя"));
            SecurityContextHolder.getContext().setAuthentication(internalToken);
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("Истек срок действия токена", e);
        } catch (SignatureException e) {
            throw new BadCredentialsException("Неверная подпись", e);
        }

        filterChain.doFilter(request, response);
    }
}
