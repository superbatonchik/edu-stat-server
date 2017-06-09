package ru.cmo.edu.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.cmo.edu.exception.InvalidTokenException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by to on 09.06.2017.
 */
@Service
public class JwtService {

    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_AUDIENCE = "audience";
    public static final String CLAIM_KEY_CREATED = "created";

    public static final String AUDIENCE_MOBILE = "mobile";

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Value("${jwt.expiration}")
    private Long tokenExpiration;

    private Claims getClaimsFromToken(String token) throws InvalidTokenException {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage(), token);
        }
        return claims;
    }

    public String getUsername(String token) throws InvalidTokenException {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public Date getCreated(String token) throws InvalidTokenException {
        Claims claims = getClaimsFromToken(token);
        return new Date((Long)claims.get(CLAIM_KEY_CREATED));
    }

    public Date getExpiration(String token) throws InvalidTokenException {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<String, Object>() {
            {
                put(CLAIM_KEY_USERNAME, userDetails.getUsername());
                put(CLAIM_KEY_AUDIENCE, AUDIENCE_MOBILE);
                put(CLAIM_KEY_CREATED, new Date());
            }
        };
        return generateToken(claims);
    }

    private Boolean isTokenExpired(String token) throws InvalidTokenException {
        final Date expiration = getExpiration(token);
        return new Date().after(expiration);
    }

    public String refreshToken(String token) throws InvalidTokenException {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage(), token);
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws InvalidTokenException {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
