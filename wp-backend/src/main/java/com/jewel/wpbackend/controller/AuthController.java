package com.jewel.wpbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jewel.wpbackend.jwt.JwtIssuer;
import com.jewel.wpbackend.model.LoginRequest;
import com.jewel.wpbackend.model.LoginResponse;
import com.jewel.wpbackend.security.UserPrinciple;

@RestController
public class AuthController {

	@Autowired
	private JwtIssuer issuer;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
		
		org.springframework.security.core.Authentication authentication = null;
		try {
			
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getpassword())
					);
		}catch(Exception e) {
			System.out.print("Error..........Message: "+ e.getLocalizedMessage());
		}
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		var userPrinciple = (UserPrinciple)authentication.getPrincipal();
		
		return LoginResponse.builder()
				.setToken(issuer.jwtIssuer(userPrinciple.getUserId(), userPrinciple.getUsername(), 
						userPrinciple.getAuthorities()
						.stream().map(GrantedAuthority::getAuthority)
						.toList()))
				.build();
	}
	
}
