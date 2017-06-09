package ru.cmo.edu.rest.security;

import java.io.Serializable;

/**
 * Created by to on 09.06.2017.
 */
public class UserRequest implements Serializable {
    private String username;
    private String password;

    public UserRequest() {
        super();
    }

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
