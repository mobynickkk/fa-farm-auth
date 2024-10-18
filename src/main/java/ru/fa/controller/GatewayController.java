package ru.fa.controller;

import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GatewayController {

    @GetMapping("/cattle/**")
    public ResponseEntity<?> routeGetCattleRequest(ProxyExchange<Object> proxy,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                   @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/cattle/");
            return proxy.uri("http://localhost:8081/" + path).header(HttpHeaders.AUTHORIZATION, auth).body(body).get();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/cattle/**")
    public ResponseEntity<?> routePostCattleRequest(ProxyExchange<Object> proxy,
                                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                    @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/cattle/");
            return proxy.uri("http://localhost:8081/" + path).header(HttpHeaders.AUTHORIZATION, auth).body(body).post();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
