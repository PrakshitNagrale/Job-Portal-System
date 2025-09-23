package com.prakshit.jobportalsystem.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {                 //This class runs once per request and checks every incoming HTTP request for a valid JWT token.

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");  //to get authorization header -> Ex.Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...(it will get the whole authorization (Bearer+token))


        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            String  token = authHeader.substring(7);     //it will remove Bearer and take the token

            try{
                Claims claims = jwtUtil.extractClaims(token);                               //token contains ex:"sub": "ibm@gmail.com","role": "EMPLOYER","iat": 1758470303,"exp": 1758506303
                String email = claims.getSubject();                         // it will get the email as subject
                String role = claims.get("role", String.class);             // it will get the role of the user ex.role claim = "EMPLOYER".

                if(email != null && role!= null &&
                        SecurityContextHolder.getContext().getAuthentication() == null){

                    // Add ROLE_ prefix for Spring Security
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);      //Ex.ROLE_EMPLOYER

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(email,null,
                                    Collections.singletonList(authority));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }


            } catch (Exception e) {
                System.out.println("JWT validation failed: " + e.getMessage());

            }
        }

        // Proceed with next filter in the chain
        filterChain.doFilter(request, response);

    }
}
