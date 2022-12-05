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

import com.ifsp.Services.OrderService;
import com.ifsp.entities.Order;
import com.ifsp.interfaces.Listable;

@RestController
public class OrderController {
	private OrderService service = new OrderService();
	
	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@GetMapping(value="/orders")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/orders/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@DeleteMapping(value="/orders/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
	
	@PostMapping(value="/orders")
	public ResponseEntity<String> add(@RequestBody Order order) throws SQLException{
		return service.add(order);
	}
	
	@PutMapping(value="/orders/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Order order) throws SQLException{
		return service.update(id, order);
	}
}
