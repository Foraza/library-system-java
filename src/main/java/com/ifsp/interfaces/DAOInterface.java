package com.ifsp.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.ifsp.connections.DBConnection;

public interface DAOInterface {
	public final static DBConnection conn = new DBConnection();
			
	public void add(Listable item);
	
	public Listable get(int id) throws SQLException;
	
	public List<Listable> getAll() throws SQLException;
	
	public String remove(int id);
	
	public void update(Listable item);
}
