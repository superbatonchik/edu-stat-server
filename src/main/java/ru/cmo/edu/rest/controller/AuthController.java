package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.cmo.edu.exception.InvalidTokenException;
import ru.cmo.edu.rest.json.JsonEduFormDataResponseFactory;
import ru.cmo.edu.rest.security.UserRequest;
import ru.cmo.edu.service.JwtService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UserRequest userRequest) throws InvalidTokenException {
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new HashMap<String, Object>() {
            {
                put("token", token);
                put("username", username);
                put("expiration", jwtService.getExpiration(token));
            }
        });
    }
}
