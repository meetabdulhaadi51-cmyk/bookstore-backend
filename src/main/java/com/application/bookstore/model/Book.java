package com.application.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
@NotBlank
	private String title;
@NotBlank
	private String author;
@Positive
	private Double price;
@PositiveOrZero
	private Integer stock;
private String imageUrl;
	
}
