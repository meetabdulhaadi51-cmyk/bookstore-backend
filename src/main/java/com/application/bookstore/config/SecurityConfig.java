package com.application.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.application.bookstore.repository.UserRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username->userRepo.findByUsername(username)
				.map(user->org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole().replace("ROLE_", ""))
				.build())
				.orElseThrow(()->new RuntimeException("User not found"));
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.cors(org.springframework.security.config.Customizer.withDefaults())
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth
				.requestMatchers("/api/auth/register").permitAll()
				.requestMatchers("/api/books/**").authenticated()
				.anyRequest().permitAll()
				)
		.httpBasic(org.springframework.security.config.Customizer.withDefaults());
		return http.build();
	}
}
