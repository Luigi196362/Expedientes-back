package com.uv.api_expedientes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        http.authorizeHttpRequests(request -> request.
        requestMatchers("/usuario/**").permitAll().
        requestMatchers("/rol/**").permitAll()
        ).csrf(csrf -> csrf.disable());

        return http.build();
        */

        //Permitir todo
        
        
        http.authorizeHttpRequests(requests -> requests
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable()); // Deshabilita CSRF si no es necesario
            

        return http.build();
        
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

