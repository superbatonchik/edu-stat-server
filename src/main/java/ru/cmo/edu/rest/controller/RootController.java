package ru.cmo.edu.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @RequestMapping("/")
    public String hello() {
        return "<H1>CMO stat</H1>";
    }
}
