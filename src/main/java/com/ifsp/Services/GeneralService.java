package com.ifsp.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class GeneralService {
	private DAOInterface DAO;
	
	public GeneralService(DAOInterface DAO) {
		this.DAO = DAO;
	}
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(Listable item) throws SQLException{
		return ResponseEntity.ok(DAO.remove(item));
	}
	
	public ResponseEntity<String> add(Listable item) throws SQLException{
		return ResponseEntity.ok(DAO.add(item));
	}
	
	public ResponseEntity<String> update(Listable item) throws SQLException{
		return ResponseEntity.ok(DAO.update(item));
	}
}
