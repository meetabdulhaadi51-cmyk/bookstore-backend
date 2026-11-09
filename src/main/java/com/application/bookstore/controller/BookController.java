package com.application.bookstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.bookstore.model.Book;
import com.application.bookstore.repository.BookRepository;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookRepository repo;
	public BookController(BookRepository repo) {
		this.repo=repo;
	}
	@GetMapping
	public List<Book>getAll(){
		return repo.findAll();
	}
	@GetMapping("/{id}")
	public Book getOne(@PathVariable Long id) {
		return repo.findById(id).orElseThrow();
		
	}
	@PostMapping
	public Book create(@Valid@RequestBody Book book) {
		return repo.save(book);
	}
	@PutMapping("/{id}")
	public Book update(@PathVariable Long id,@Valid@RequestBody Book book) {
		book.setId(id);
		return repo.save(book);
		
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
	@GetMapping("/search")
	public List<Book>search(@RequestParam String title){
		return repo.findByTitleContainingIgnoreCase(title);
	}
}
