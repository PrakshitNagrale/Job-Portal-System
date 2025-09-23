package com.prakshit.jobportalsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationTime;

    public JwtUtil(
            @Value("${jwt.secret.key}")  String secretKey,            // to get the value from application.properties
            @Value("${jwt.expiration.time}") long expirationTime
            ){
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expirationTime = expirationTime;
    }


    //to generate jwt token and return the token
    public String generateToken(String email,String role){

        return Jwts.builder()
                .setSubject(email)      // email -> unique identifier of user,it will set sub = email
                .claim("role",role)           // custom claim → we add user role (ADMIN, EMPLOYER, APPLICANT),it will set role = (ADMIN, EMPLOYER, APPLICANT)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();                         // converts everything into final JWT string
    }

    //to decode token,it will get subject and role from the token
    public Claims extractClaims(String token){

        return Jwts.parserBuilder()
                .setSigningKey(key)             // verify with the same key
                .build()
                .parseClaimsJws(token)           // parses and validates
                .getBody();                      // extracts claims (email, role, expiry)
    }


    //to extract email
    public String extractEmail(String token){
        return extractClaims(token).getSubject();           //"sub" claim = email
    }

    //to extract role
    public String extractRole(String token){
        return extractClaims(token).get("role",String.class);
    }

    //to check token expiry
    public boolean isTokenExpired(String token){
        return  extractClaims(token).getExpiration().before(new Date());
    }

  //to validate token
    public boolean validateToken(String token, String email){                    // Ensures the token belongs to the right user AND is still valid.
        return (email.equals(extractEmail(token)) && !isTokenExpired(token));
    }




}
