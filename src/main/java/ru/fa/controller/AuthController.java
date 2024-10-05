package ru.fa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fa.dto.AuthDto;

@RestController("/auth")
public class AuthController {

    @PostMapping("/")
    public ResponseEntity<?> authenticate(AuthDto authDto) {
        try {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
