package com.ifsp.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifsp.connections.ClientDAO;
import com.ifsp.interfaces.Listable;

@Service
public class ClientService {
	ClientDAO DAO = new ClientDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
}
