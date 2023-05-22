package ru.bmstu.identityprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.identityprovider.dto.AuthRequest;
import ru.bmstu.identityprovider.dto.RegisterRequest;
import ru.bmstu.identityprovider.dto.TokenResponse;
import ru.bmstu.identityprovider.service.IDService;


@Slf4j
@RestController
@RequestMapping("api/v1/identityprovider")
public class IPController {
    @Autowired
    private IDService service;

    @PostMapping(value = "/authorize")
    public TokenResponse getToken(@RequestBody AuthRequest request) {
        log.info("[IDENTITY PROVIDER]: authorization request was caught.");

        return service.getToken(request);
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        log.info("[IDENTITY PROVIDER]: registration request was caught.");

        return new ResponseEntity(service.register(request));
    }
}
