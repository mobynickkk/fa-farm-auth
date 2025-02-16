package ru.fa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class GatewayController {

    @GetMapping("/plants/**")
    public ResponseEntity<?> routeGetPlantsRequest(ProxyExchange<Object> proxy,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                   @RequestHeader(HttpHeaders.COOKIE) String cookie,
                                                   @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/plants/");
            return proxy.uri("http://localhost:8081/" + path)
                    .header(HttpHeaders.AUTHORIZATION, auth)
                    .header(HttpHeaders.COOKIE, cookie)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(body)
                    .get();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/plants/**")
    public ResponseEntity<?> routePostPlantsRequest(ProxyExchange<Object> proxy,
                                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                    @RequestHeader(HttpHeaders.COOKIE) String cookie,
                                                    @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/plants/");
            return proxy.uri("http://localhost:8081/" + path)
                    .header(HttpHeaders.AUTHORIZATION, auth)
                    .header(HttpHeaders.COOKIE, cookie)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(body)
                    .post();
        } catch (Exception e) {
            log.error("Ошибка", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/animals/**")
    public ResponseEntity<?> routeDeletePlantsRequest(ProxyExchange<Object> proxy,
                                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                      @RequestHeader(HttpHeaders.COOKIE) String cookie,
                                                    @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/animals/");
            return proxy.uri("http://localhost:8081/" + path)
                    .header(HttpHeaders.AUTHORIZATION, auth)
                    .header(HttpHeaders.COOKIE, cookie)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(body)
                    .delete();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/animals/**")
    public ResponseEntity<?> routeGetAnimalsRequest(ProxyExchange<Object> proxy,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                   @RequestHeader(HttpHeaders.COOKIE) String cookie,
                                                   @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/animals/");
            return proxy.uri("http://localhost:8082/" + path)
                    .header(HttpHeaders.AUTHORIZATION, auth)
                    .header(HttpHeaders.COOKIE, cookie)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(body)
                    .get();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/animals/**")
    public ResponseEntity<?> routePostAnimalsRequest(ProxyExchange<Object> proxy,
                                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                    @RequestHeader(HttpHeaders.COOKIE) String cookie,
                                                    @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/animals/");
            return proxy.uri("http://localhost:8082/" + path)
                    .header(HttpHeaders.AUTHORIZATION, auth)
                    .header(HttpHeaders.COOKIE, cookie)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(body)
                    .post();
        } catch (Exception e) {
            log.error("Ошибка", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/animals/**")
    public ResponseEntity<?> routeDeleteAnimalsRequest(ProxyExchange<Object> proxy,
                                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                      @RequestHeader(HttpHeaders.COOKIE) String cookie,
                                                      @RequestBody(required = false) Object body) {
        try {
            String path = proxy.path("/animals/");
            return proxy.uri("http://localhost:8082/" + path)
                    .header(HttpHeaders.AUTHORIZATION, auth)
                    .header(HttpHeaders.COOKIE, cookie)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(body)
                    .delete();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
