package com.ifsp.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifsp.entities.Book;
import com.ifsp.entities.Client;
import com.ifsp.entities.Publisher;
import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class PublisherDAO implements DAOInterface{
	private Connection conn = new DBConnection().getConnection();

	@Override
	public String add(Listable item) {
		String sql="INSERT INTO publisher(id, name, address) VALUES ("
				+ ((Publisher) item).getId() + ","
				+ "'" + ((Publisher) item).getName() + "'" + ","
				+ "'" + ((Publisher) item).getAddress() + "'" + ");";
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Editora adicionada com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao adicionar o editora";
		}
		
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
    	aux.setAddress(rs.getString("address"));
		
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
		String sql="DELETE from publisher WHERE id = " + id;       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
            return "Editora removida com sucesso!";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Falha ao remover editora";
		}
	}

	@Override
	public String update(int id, Listable item) throws SQLException {
		String sql = "SELECT * FROM publisher WHERE id = " + id;
		
		PreparedStatement ps = null; //Prepara a query e evita sql injection
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newName = ((Publisher) item).getName() != null ? ((Publisher) item).getName() : rs.getString("name");
		String newAddress = ((Publisher) item).getAddress() != null ? ((Publisher) item).getAddress() : rs.getString("address");
		
		sql = "UPDATE publisher SET "
				+ "name = '" + newName + "', "				
				+ "address = '" + newAddress + "'"
			+ "WHERE id = " + id + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Editora alterada com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao alterar o editora";
		}
	}
}
