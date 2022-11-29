package com.ifsp.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifsp.entities.Book;
import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class BookDAO implements DAOInterface {
	private Connection conn = new DBConnection().getConnection();

	@Override
	public void add(Listable item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from books WHERE id = " + id;       //cria a string do sql
        
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
        Book aux = new Book();
    	aux.setId(rs.getInt("id"));
    	aux.setTitle(rs.getString("title"));
    	aux.setPrice(rs.getDouble("price"));
    	aux.setAuthor(rs.getString("author"));
		
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> books = new ArrayList<>();
		String sql="Select * from books";       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	Book aux = new Book();
        	aux.setId(rs.getInt("id"));
        	aux.setTitle(rs.getString("title"));
        	aux.setPrice(rs.getDouble("price"));
        	aux.setAuthor(rs.getString("author"));
        	
        	books.add(aux);
        }
        
		return books;
	}

	@Override
	public String remove(int id) {
		String sql="DELETE from books WHERE id = " + id;       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
            
            return "Livro removido com sucesso!";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Falha ao remover livro";
		}
		
	}

	@Override
	public void update(Listable item) {
		// TODO Auto-generated method stub
		
	}

}
