package com.ifsp.interfaces;

import java.util.List;

import com.ifsp.connections.DBConnection;

public interface DAOInterface {
	public final static DBConnection conn = new DBConnection();
			
	public void add(Listable item);
	
	public Listable get(int id);
	
	public List<Listable> getAll();
	
	public void remove(int id);
	
	public void update(Listable item);
}
