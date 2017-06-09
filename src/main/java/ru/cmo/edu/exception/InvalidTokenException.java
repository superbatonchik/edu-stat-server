package ru.cmo.edu.exception;

/**
 * Created by to on 09.06.2017.
 */
public class InvalidTokenException extends Exception {
    private String token;

    public InvalidTokenException(String message, String token) {
        super(message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
