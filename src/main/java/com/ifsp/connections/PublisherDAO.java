package com.ifsp.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifsp.entities.Client;
import com.ifsp.entities.Publisher;
import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class PublisherDAO implements DAOInterface{
	private Connection conn = new DBConnection().getConnection();

	@Override
	public String add(Listable item) {
		return null;
		
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from publisher WHERE id = " + id;       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        rs.next();
        Publisher aux = new Publisher();
    	aux.setId(rs.getInt("id"));
    	aux.setName(rs.getString("name"));
    	
		
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> publishers = new ArrayList<>();
		String sql="Select * from publisher";       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	Publisher aux = new Publisher();
        	aux.setId(rs.getInt("id"));
        	aux.setName(rs.getString("name"));
        	
        	publishers.add(aux);
        }
        
		return publishers;
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
