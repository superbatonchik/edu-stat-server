package ru.cmo.edu.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController()
public class RootController {

    @RequestMapping(value = "/now", method = RequestMethod.GET)
    public ResponseEntity hello() {

        return ResponseEntity.ok(new HashMap<String, Object>() {
            { put("now", LocalDateTime.now()); }
        });
    }
}
