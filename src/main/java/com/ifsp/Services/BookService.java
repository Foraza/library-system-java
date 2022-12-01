package com.ifsp.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifsp.connections.BookDAO;
import com.ifsp.entities.Book;
import com.ifsp.interfaces.Listable;

@Service
public class BookService {
	BookDAO DAO = new BookDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> add(Book book) throws SQLException{
		return ResponseEntity.ok(DAO.add(book));
	}
	
	public ResponseEntity<String> update(int id, Book book) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, book));
	}
}
