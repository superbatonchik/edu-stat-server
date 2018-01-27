package ru.cmo.edu.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserInfo extends User {
    private String name;

    public UserInfo(String username, String password, String name, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
