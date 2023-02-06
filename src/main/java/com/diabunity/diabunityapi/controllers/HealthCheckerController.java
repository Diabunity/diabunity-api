package com.diabunity.diabunityapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckerController {

    @GetMapping("/")
    public Object HealthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
