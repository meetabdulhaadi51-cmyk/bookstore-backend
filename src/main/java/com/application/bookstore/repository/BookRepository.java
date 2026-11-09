package com.application.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
List <Book>findByTitleContainingIgnoreCase(String title);

}
