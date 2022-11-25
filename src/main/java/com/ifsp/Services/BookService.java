package com.ifsp.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	public ResponseEntity<String> findAll(){
		return ResponseEntity.ok("Todos os livros aqui");
	}
}
