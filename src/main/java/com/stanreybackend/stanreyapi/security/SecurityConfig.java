package com.stanreybackend.stanreyapi.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.stanreybackend.stanreyapi.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UsuarioRepository userRepository, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter(jwtUtil, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/stanrey/auth/**").permitAll()
                        .requestMatchers("/stanrey/test/**").permitAll()
                        .requestMatchers("/stanrey/categoria/**").permitAll()
                        .requestMatchers("/stanrey/producto/**").permitAll()
                        .requestMatchers("/stanrey/cliente/**").hasRole("CLIENT")
                        .requestMatchers("/stanrey/it_admin/**").hasRole("IT_ADMIN")
                        .requestMatchers("/stanrey/manager/**").hasRole("MANAGER")
                        .anyRequest().authenticated())
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
