package com.jewel.wpbackend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jewel.wpbackend.entity.User;

@Service
public class UserService {
	
	private List<User> list = Arrays.asList(
			
			new User(1L, "jewel@gmail.com", "$2a$12$Qt1TQniYbQl60m8h3NUSmuuSF5R0T9jW7Xa5RytOtMoVGgyS.AqtW", Arrays.asList("ROLE_ADMIN")),
			new User(2L, "sohel@gmail.com", "$2a$12$Qt1TQniYbQl60m8h3NUSmuuSF5R0T9jW7Xa5RytOtMoVGgyS.AqtW", Arrays.asList("ROLE_USER")),
			new User(3L, "korim@gmail.com", "$2a$12$Qt1TQniYbQl60m8h3NUSmuuSF5R0T9jW7Xa5RytOtMoVGgyS.AqtW", Arrays.asList("ROLE_USER"))
			
			);
	
	public User getUser(String email) {
		
		for (User user : list) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		
		throw new UsernameNotFoundException("User not found in the db: "+ email);
	}
	
	
	

}
