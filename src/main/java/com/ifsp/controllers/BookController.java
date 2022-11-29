package com.ifsp.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.Services.BookService;
import com.ifsp.interfaces.Listable;

@RestController
public class BookController {
	private final BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	@GetMapping(value="/books")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/books/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@GetMapping(value="/books/remove/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
}
