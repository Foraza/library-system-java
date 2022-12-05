package com.ifsp.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifsp.connections.OrderDAO;
import com.ifsp.entities.Order;
import com.ifsp.interfaces.Listable;

@Service
public class OrderService {
	OrderDAO DAO = new OrderDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> add(Order order) throws SQLException{
		return ResponseEntity.ok(DAO.add(order));
	}
	
	public ResponseEntity<String> update(int id, Order order) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, order));
	}
}
