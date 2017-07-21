package ru.cmo.edu.rest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.cmo.edu.rest.security.Role;

/**
 * Created by to on 21.07.2017.
 */
public class BaseController {
    protected Role getRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Role role = null;
        for (GrantedAuthority authority : auth.getAuthorities()) {
            try {
                role = Role.valueOf(authority.getAuthority());
                break;
            } catch (IllegalArgumentException e) {}
        }
        return role;
    }
}
