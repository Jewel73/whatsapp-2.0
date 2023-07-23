package com.jewel.wpbackend.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtIssuer {
	
	@Autowired
	private JwtProperties jwtProperties;
	
	public String jwtIssuer(long userId, String email, List<String> roles) {
		
		return JWT.create()
		.withSubject(String.valueOf(userId))
		.withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
		.withClaim("e", email)
		.withClaim("r", roles)
		.sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
	
	}
}




