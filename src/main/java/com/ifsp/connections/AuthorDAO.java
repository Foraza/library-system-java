package com.ifsp.connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifsp.entities.Author;
import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class AuthorDAO implements DAOInterface{

	@Override
	public String add(Listable item) {
		String sql="INSERT INTO author(id, name) VALUES ("
				+ ((Author) item).getId() + ","				
				+ "'" + ((Author) item).getName() + "'" + ");";
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Autor adicionado com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao adicionar o autor";
		}
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from author WHERE id = " + id;       //cria a string do sql
        
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
        Author aux = new Author();
    	aux.setId(rs.getInt("id"));
    	aux.setName(rs.getString("name"));
    	
		
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> authors = new ArrayList<>();
		String sql="Select * from author";       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	Author aux = new Author();
        	aux.setId(rs.getInt("id"));
        	aux.setName(rs.getString("name"));
        	
        	authors.add(aux);
        }
        
		return authors;
	}

	@Override
	public String remove(Listable item) {
		String sql="DELETE from author WHERE id = " + ((Author) item).getId();       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
            return "Autor removido com sucesso!";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Falha ao remover autor";
		}
	}

	@Override
	public String update(Listable item) throws SQLException {
		String sql = "SELECT * FROM author WHERE id = " + ((Author) item).getId();
		
		PreparedStatement ps = null; //Prepara a query e evita sql injection
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newName = ((Author) item).getName() != null ? ((Author) item).getName() : rs.getString("name");
		
		sql = "UPDATE author SET "
				+ "name = '" + newName + "' "				
			+ "WHERE id = " + ((Author) item).getId() + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Autor alterado com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao alterar o autor";
		}
	}

}
