package ru.fa.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.fa.dto.AuthDto;
import ru.fa.dto.JwtDto;
import ru.fa.dto.UserDto;
import ru.fa.service.AuthService;
import ru.fa.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthDto authDto) {
        try {
            var tokenDto = authService.authorize(authDto);
            return ResponseEntity.ok(tokenDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            log.error("Ошибка", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/sign-up")
    @Transactional
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        try {
            userService.createUser(userDto);
            var tokenDto = authService.authorize(AuthDto.of(userDto));
            return ResponseEntity.ok(tokenDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            log.error("Ошибка", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
