package org.example.fuel_management_system.config;

import jakarta.servlet.http.HttpServletRequest;
import org.example.fuel_management_system.filter.JwtAuthenticationFilter;
import org.example.fuel_management_system.service.JwtService;
import org.example.fuel_management_system.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableAspectJAutoProxy
public class SecurityConfig {
    @Autowired
    private final UserAccountService userAccountService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserAccountService userAccountService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userAccountService = userAccountService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/api/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userAccountService)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .build();

    }

    private CorsConfigurationSource corsConfigurationSource(){
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration=new CorsConfiguration();
                configuration.setAllowCredentials(true);
                configuration.setMaxAge(3600L);
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:5173","http://localhost:5174"));
                configuration.setAllowedMethods(Collections.singletonList("*"));
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                return configuration;
            }
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }


}

