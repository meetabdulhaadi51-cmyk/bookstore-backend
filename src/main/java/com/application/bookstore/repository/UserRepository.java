package com.application.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.bookstore.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User>findByUsername(String username);
	

}
