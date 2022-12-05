package com.ifsp.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifsp.connections.AccountDAO;
import com.ifsp.entities.Account;
import com.ifsp.interfaces.Listable;

@Service
public class AccountService {
	AccountDAO DAO = new AccountDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> add(Account account) throws SQLException{
		return ResponseEntity.ok(DAO.add(account));
	}
	
	public ResponseEntity<String> update(int id, Account account) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, account));
	}
}
