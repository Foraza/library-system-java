package com.ifsp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.Services.BookService;

@RestController
public class BookController {
	private final BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	@GetMapping(value="/books")
	public ResponseEntity<String> test(){
		return service.findAll();
	}
}
