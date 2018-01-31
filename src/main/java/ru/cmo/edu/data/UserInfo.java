package ru.cmo.edu.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserInfo extends User {
    private String name;
    private int accessRights;
    private int id;

    public UserInfo(String username, String password, String name, int id, Collection<? extends GrantedAuthority> authorities, int accessRights) {
        super(username, password, authorities);
        this.name = name;
        this.id = id;
        this.accessRights = accessRights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(int accessRights) {
        this.accessRights = accessRights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
