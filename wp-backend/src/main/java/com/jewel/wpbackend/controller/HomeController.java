package com.jewel.wpbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String greeting() {
		return "Hello World !";
	}
	
	@GetMapping("/public")
	public String publicGreetings() {
		return "Hello World !, this is a public controller";
	}
	
	@GetMapping("/user")
	public String anyauthenticateUser() {
		return "Hello World !, You are normal authentictae user";
	}
	
	
	@GetMapping("/admin")
	public String greetingAdmin() {
		return "Hello World !  You are Admin! Cool";
	}
}
