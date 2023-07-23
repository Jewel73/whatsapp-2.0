package com.jewel.wpbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jewel.wpbackend.service.UserService;

@Service
public class CustomUserDetailsService  implements UserDetailsService{
	
	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		var user = service.getUser(username);
		var userPrinciple = new UserPrinciple();
		userPrinciple.setUserId(user.getUserId());
		userPrinciple.setEmail(user.getEmail());
		userPrinciple.setPassword(user.getPassword());
		userPrinciple.setAuthorities(user.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
		
		
		return userPrinciple;
	}

}
