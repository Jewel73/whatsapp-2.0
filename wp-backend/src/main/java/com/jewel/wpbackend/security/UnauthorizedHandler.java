package com.jewel.wpbackend.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UnauthorizedHandler extends BasicAuthenticationEntryPoint {

	public UnauthorizedHandler() {
        // Set the realmName here
        setRealmName("MyRealm");
    }
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		// TODO Auto-generated method stub
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        
        // You can customize the error message here
        String errorMessage = "Authentication failed: " + authException.getMessage();
        String jsonMessage = "{\"error\": \"" + errorMessage + "\"}";

        PrintWriter writer = response.getWriter();
        writer.println(jsonMessage);
	}

}
