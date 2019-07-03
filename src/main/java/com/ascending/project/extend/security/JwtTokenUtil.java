package com.ascending.project.extend.security;

import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    static private final String CLAIM_KEY_USERNAME = "sub";
    static private final String CLAIM_KEY_CREATED = "created";
    static private final String secret = "secretCodeSample";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String getUsernameFromToken(String token){
        String username;
        try{
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception ex){
            username = null;
            logger.warn("get username from token failure", ex);
        }
        return username;
    }

    public String generateToken(UserDetails userDetails){       //convert user details to hashmap claims
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex){
            claims = null;
            logger.warn("unable to decrypt token");
        }
        return claims;
    }

    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + 86400 * 1000);     //expire in 1 day
    }
}
