package com.prakshit.jobportalsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration                                                //  Marks as a configuration class so Spring can pick it up.
public class SecurityConfig {                                 //every api permission who can access in given from this class

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {        //Injects your custom JWT filter (JwtAuthenticationFilter),
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //for login and register without jwt token and all other api will need jwt tokens
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {   //SecurityFilterChain-> defines the full security rules

        http.csrf(csrf ->csrf.disable())                                //stateless REST API with JWT, CSRF is disabled.
                .authorizeHttpRequests(auth -> auth                 //inside here define who can access which endpoints:
                        //any one can access
                        .requestMatchers("/auth/login","/auth/register").permitAll()     //->Anyone (no token required) can log in or register a user.

                        //for admin
                        .requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")

                        //for applicant
                        .requestMatchers(HttpMethod.POST,"/jobApplications").hasRole("APPLICANT")       //Only users with ROLE_APPLICANT can create job applications.
                        .requestMatchers(HttpMethod.PUT,"/jobApplications/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.DELETE,"/jobApplications/**").hasRole("APPLICANT")
                            //only applicant and create & edit applicant profiles
                        .requestMatchers(HttpMethod.POST,"/applicant/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT,"/applicant/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.DELETE,"/applicant/**").hasRole("APPLICANT")


                        //for employer
                        .requestMatchers("/employers","/employers/**").hasRole("EMPLOYER") //only employer is access to get,post,put,delete
                        .requestMatchers(HttpMethod.POST,"/jobs").hasRole("EMPLOYER")           //Only EMPLOYER can create jobs.

                        //for employer and admin
                        .requestMatchers(HttpMethod.GET,"/jobApplications/**").hasAnyRole("EMPLOYER","ADMIN")   //Only EMPLOYER or ADMIN can view job applications.
                        .requestMatchers(HttpMethod.GET,"/applicant/**").hasAnyRole("EMPLOYER","ADMIN")         //only employer & admin can view applicant profiles

                        //for everyone
                        .requestMatchers(HttpMethod.GET,"/jobs/**").permitAll()                 //Anyone can view job listings (public access) without jwt token
                        //default
                        .anyRequest().authenticated())                                          //Any other request must have a valid token.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);          //before filter, run our JWT filter → so every request gets checked for a valid JWT token.

        return http.build();
    }
}
