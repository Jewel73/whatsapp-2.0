package com.jewel.wpbackend.security;

import java.io.IOException;
import java.util.Optional;

import org.apache.el.parser.TokenMgrError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jewel.wpbackend.jwt.JwtDecoder;
import com.jewel.wpbackend.jwt.JwtToPrincipleConverter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ValidationException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private JwtDecoder jwtDecoder;
	
	@Autowired
	private JwtToPrincipleConverter converter;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(!request.getRequestURL().toString().contains("login")) {
			getToken(request)
			.map(jwtDecoder::decode)
			.map(converter::convert)
			.map(UserAuthenticationPrincipleToken::new)
			.ifPresent(principle -> SecurityContextHolder.getContext().setAuthentication(principle));
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	public static Optional<String> getToken(HttpServletRequest request){
		
		var auth = request.getHeader("Authorization");
		
		if(StringUtils.isEmpty(auth) || !auth.startsWith("Bearer ")) throw new ValidationException("Token Is not valide");
		
		var token = Optional.of(auth.substring(7));
		return token;
		
	}

}









