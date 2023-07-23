package com.jewel.wpbackend.security;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Service;


public class UserAuthenticationPrincipleToken extends AbstractAuthenticationToken{

	private UserPrinciple userPrinciple;
	
	public UserAuthenticationPrincipleToken(UserPrinciple userPrinciple) {
		super(userPrinciple.getAuthorities());
		this.userPrinciple = userPrinciple;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return userPrinciple;
	}

}
