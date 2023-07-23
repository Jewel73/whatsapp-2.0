package com.jewel.wpbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private UnauthorizedHandler unauthorizedHandler;
	
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    	
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin(AbstractHttpConfigurer::disable)
            .securityMatcher("/**")
            .exceptionHandling(h -> h.authenticationEntryPoint(unauthorizedHandler))
            .authorizeHttpRequests(registry -> registry
            		
            		.requestMatchers("/").permitAll()
            		.requestMatchers("/auth/login").permitAll()
            		.requestMatchers("/public/**").permitAll()
            		.requestMatchers("/admin/**").hasRole("ADMIN")
            		.anyRequest().authenticated()
            		
            		);
            
        return http.build();
	}
    
    @Bean
    PasswordEncoder encoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    	
    	var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	builder.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
    	
    	return builder.build();
    }

}




