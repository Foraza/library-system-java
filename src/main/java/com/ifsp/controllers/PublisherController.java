package com.ifsp.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.Services.PublisherService;
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
	
	@GetMapping(value="/publishers/remove/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
}
