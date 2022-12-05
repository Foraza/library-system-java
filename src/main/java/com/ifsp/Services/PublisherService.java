package com.ifsp.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifsp.connections.PublisherDAO;
import com.ifsp.entities.Publisher;
import com.ifsp.interfaces.Listable;

@Service
public class PublisherService {
	PublisherDAO DAO = new PublisherDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> add(Publisher publisher) throws SQLException{
		return ResponseEntity.ok(DAO.add(publisher));
	}
	
	public ResponseEntity<String> update(int id, Publisher publisher) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, publisher));
	}
}
