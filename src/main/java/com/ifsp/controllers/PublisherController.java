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

import com.ifsp.Services.PublisherService;
import com.ifsp.entities.Publisher;
import com.ifsp.interfaces.Listable;

@RestController
public class PublisherController {
	private final PublisherService service;
	
	public PublisherController(PublisherService service) {
		this.service = service;
	}
	
	@GetMapping(value="/publishers")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/publishers/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@DeleteMapping(value="/publishers/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
	
	@PostMapping(value="/publishers")
	public ResponseEntity<String> add(@RequestBody Publisher publisher) throws SQLException{
		return service.add(publisher);
	}
	
	@PutMapping(value="/publishers/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Publisher publisher) throws SQLException{
		return service.update(id, publisher);
	}
	
}
