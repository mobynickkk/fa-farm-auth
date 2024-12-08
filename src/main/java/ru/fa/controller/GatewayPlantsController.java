package ru.fa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class GatewayPlantsController {

    @GetMapping("/plants/**")
    public ResponseEntity<?> routeGetPlantsRequest(ProxyExchange<Object> proxy,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                   @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/plants/");
            return proxy.uri("http://localhost:8080/" + path).header(HttpHeaders.AUTHORIZATION, auth).body(body).get();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/plants/**")
    public ResponseEntity<?> routePostPlantsRequest(ProxyExchange<Object> proxy,
                                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                    @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/plants/");
            return proxy.uri("http://localhost:8080/" + path)
                    .header(HttpHeaders.AUTHORIZATION, auth)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(body)
                    .post();
        } catch (Exception e) {
            log.error("Ошибка", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/plants/**")
    public ResponseEntity<?> routeDeletePlantsRequest(ProxyExchange<Object> proxy,
                                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                    @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/plants/");
            return proxy.uri("http://localhost:8080/" + path).header(HttpHeaders.AUTHORIZATION, auth).body(body).delete();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
