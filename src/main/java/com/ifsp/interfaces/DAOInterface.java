package com.ifsp.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ifsp.connections.DBConnection;

public interface DAOInterface {
	public final static Connection conn = new DBConnection().getConnection();
			
	public String add(Listable item) throws SQLException;
	
	public Listable get(int id) throws SQLException;
	
	public List<Listable> getAll() throws SQLException;
	
	public String remove(Listable item) throws SQLException;
	
	public String update(Listable item) throws SQLException;
}
