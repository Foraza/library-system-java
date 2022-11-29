package com.ifsp.connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class ClientDAO implements DAOInterface{
	private Connection conn = new DBConnection().getConnection();

	@Override
	public void add(Listable item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Listable get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remove(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Listable item) {
		// TODO Auto-generated method stub
		
	}

}
