package ru.fa.controller;

import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/cattle/**")
    public ResponseEntity<?> routeGetCattleRequest(ProxyExchange<Object> proxy) {
        try {
            return proxy.uri("http://localhost:8081").get();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/cattle/**")
    public ResponseEntity<?> routePostCattleRequest(ProxyExchange<Object> proxy) {
        try {
            return proxy.uri("http://localhost:8081").post();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
