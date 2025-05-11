package com.example.ecommerce.config;

import com.example.ecommerce.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    // Endpoints públicos de los protegidos
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        //1 - Medida de seguridad para POST, una autenticación de un token csfr válido.
        //2 - Establece como publicas las rutas de auth, las demás requieren autenticación.
        return http
                .csrf(AbstractHttpConfigurer::disable) // 1
                .authorizeHttpRequests(authRequest ->
                        authRequest // 2
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/detalles_productos").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categorias").permitAll()
                                .requestMatchers(HttpMethod.GET, "/talles").permitAll()
                                .anyRequest().authenticated())
                // Autenticación basada en jwt. No utilize la de Spring Security
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
