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

import com.ifsp.Services.AccountService;
import com.ifsp.entities.Account;
import com.ifsp.interfaces.Listable;

@RestController
public class AccountController {
private final AccountService service;
	
	public AccountController(AccountService service) {
		this.service = service;
	}
	
	@GetMapping(value="/accounts")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/accounts/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@DeleteMapping(value="/accounts")
	public ResponseEntity<String> remove(@RequestBody Account account) throws SQLException{
		return service.remove(account);	
	}
	
	@PostMapping(value="/accounts")
	public ResponseEntity<String> add(@RequestBody Account account) throws SQLException{
		return service.add(account);
	}
	
	@PutMapping(value="/accounts")
	public ResponseEntity<String> update(@RequestBody Account account) throws SQLException{
		return service.update(account);
	}
}
