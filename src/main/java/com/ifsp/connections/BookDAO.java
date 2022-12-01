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
	public String add(Listable item) {
		String sql="INSERT INTO books(id, title, author, price, fkpuid, fkauid) VALUES ("
				+ ((Book) item).getId() + ","
				+ "'" + ((Book) item).getTitle() + "'" + ","
				+ "'" + ((Book) item).getAuthor() + "'" + ","
				+ ((Book) item).getPrice() + ","
				+ ((Book) item).getPuId() + ","
				+ ((Book) item).getAuId() + ");";
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Livro adicionado com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao adicionar o livro";
		}
		
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
    	aux.setPuId(rs.getInt("fkpuid"));
    	aux.setAuId(rs.getInt("fkauid"));
		
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
        	aux.setPuId(rs.getInt("fkpuid"));
        	aux.setAuId(rs.getInt("fkauid"));
        	
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
	public String update(int id, Listable item) throws SQLException {
		String sql = "SELECT * FROM books WHERE id = " + id;
		
		PreparedStatement ps = null; //Prepara a query e evita sql injection
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newTitle = ((Book) item).getTitle() != null ? ((Book) item).getTitle() : rs.getString("title");
		String newAuthor = ((Book) item).getAuthor() != null ? ((Book) item).getAuthor() : rs.getString("author");
		Double newPrice = ((Book) item).getPrice() != 0 ? ((Book) item).getPrice() : rs.getDouble("price");
		int newPuId = ((Book) item).getPuId() != 0 ? ((Book) item).getPuId() : rs.getInt("fkpuid");
		int newAuId = ((Book) item).getAuId() != 0 ? ((Book) item).getAuId() : rs.getInt("fkauid");
		
		sql = "UPDATE books SET "
				+ "title= '" + newTitle + "',"
				+ "author= '" + newAuthor + "',"
				+ "price=" + newPrice + ","
				+ "fkpuid=" + newPuId + ","
				+ "fkauid=" + newAuId + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Livro alterado com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao alterar o livro";
		}
		
	}

}
