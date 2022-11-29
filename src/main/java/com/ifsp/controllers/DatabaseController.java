package com.ifsp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.utils.PrepareDB;

@RestController
public class DatabaseController {

	@GetMapping(value="/createTables")
	public ResponseEntity<String> createTables(){
		String msg = PrepareDB.createTables();
		
		return ResponseEntity.ok(msg);
	}
}
