package com.test.javadev.controllers;

import com.test.javadev.requests.AuthRequest;
import com.test.javadev.services.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService services;

    public AuthController(AuthService services) {
        this.services = services;
    }

    @PostMapping(value = "/login", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity login(@RequestBody AuthRequest request) {
        return services.login(request);
    }

    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> register(@RequestBody AuthRequest request) {
        System.out.println(request);

        return services.register(request);
    }

}