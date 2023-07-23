package com.jewel.wpbackend.jwt;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.jewel.wpbackend.security.UserPrinciple;

@Service
public class JwtToPrincipleConverter {
	
	public UserPrinciple convert(DecodedJWT jwt) {
		
		UserPrinciple principle = new UserPrinciple();
		
		principle.setUserId(Long.parseLong(jwt.getSubject()));
		principle.setEmail(jwt.getClaim("e").asString());
		principle.setAuthorities(geteAutorities(jwt));
		
		return principle;
	}
	
	
	public List<SimpleGrantedAuthority> geteAutorities(DecodedJWT jwt){
		
		if (jwt.getClaim("r").isNull()) return List.of();
		var list = jwt.getClaim("r").asList(String.class);
		
		return list.stream()
				.map(SimpleGrantedAuthority::new)
				.toList();
	}

}
