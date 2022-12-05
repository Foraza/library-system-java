package com.ifsp.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.Services.AuthorService;
import com.ifsp.entities.Author;
import com.ifsp.interfaces.Listable;

@RestController
public class AuthorController {
private final AuthorService service;
	
	public AuthorController(AuthorService service) {
		this.service = service;
	}
	
	@GetMapping(value="/authors")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/authors/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@DeleteMapping(value="/authors/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
	
	@PostMapping(value="/authors")
	public ResponseEntity<String> add(@RequestBody Author author) throws SQLException{
		return service.add(author);
	}
	
	@PutMapping(value="/authors/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Author author) throws SQLException{
		return service.update(id, author);
	}
}
