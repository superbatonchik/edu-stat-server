package ru.cmo.edu.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController()
public class RootController {

    @RequestMapping(value = "/now", method = RequestMethod.GET)
    public ResponseEntity hello() {
        return ResponseEntity.ok(LocalDateTime.now());
    }
}
