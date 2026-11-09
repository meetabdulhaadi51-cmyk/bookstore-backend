package com.application.bookstore.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.bookstore.model.User;
import com.application.bookstore.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	public UserController(UserRepository userRepo,PasswordEncoder passwordEncoder){
		this.userRepo=userRepo;
		this.passwordEncoder=passwordEncoder;
	}
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		if(userRepo.findByUsername((user.getUsername())).isPresent()) {
			return"Error:Username already exists";
			
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		userRepo.save(user);
		return "User registered successfully";	}
}
