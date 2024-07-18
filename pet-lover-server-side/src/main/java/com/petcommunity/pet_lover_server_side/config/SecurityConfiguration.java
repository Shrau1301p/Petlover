package com.petcommunity.pet_lover_server_side.config;

import java.util.List;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.petcommunity.pet_lover_server_side.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	 private final AuthenticationProvider authenticationProvider;
	    
	 private final JwtFilter jwtAuthenticationFilter;

	    public SecurityConfiguration(
	        JwtFilter jwtAuthenticationFilter,
	        AuthenticationProvider authenticationProvider
	    ) {
	        this.authenticationProvider = authenticationProvider;
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf()
	                .disable()
	                .authorizeHttpRequests()
	                .requestMatchers("/**","/h2-console/**").permitAll()
	                .requestMatchers(PathRequest.toH2Console()).permitAll()
	                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	                .anyRequest()
	                .authenticated()
	                .and()
	                .sessionManagement(session -> session.
	                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authenticationProvider(authenticationProvider)
	                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }

}
