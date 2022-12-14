package com.ifsp.connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifsp.entities.Client;
import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class ClientDAO implements DAOInterface{

	@Override
	public String add(Listable item) {
		String sql="INSERT INTO client(id, name) VALUES ("
				+ ((Client) item).getId() + ","				
				+ "'" + ((Client) item).getName() + "'" + ");";
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Cliente adicionado com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Fala ao adicionar cliente";
		}
		
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from client WHERE id = " + id;       //cria a string do sql
        
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
        Client aux = new Client();
    	aux.setId(rs.getInt("id"));
    	aux.setName(rs.getString("name"));
    	
		
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> clients = new ArrayList<>();
		String sql="Select * from client";       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	Client aux = new Client();
        	aux.setId(rs.getInt("id"));
        	aux.setName(rs.getString("name"));
        	
        	clients.add(aux);
        }
        
		return clients;
	}

	@Override
	public String remove(Listable item) {
		String sql="SELECT id from orders WHERE fkclid = " + ((Client) item).getId();       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
		ResultSet rs = null;
		int orderId;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				orderId = rs.getInt("id");
				
				sql = "DELETE FROM order_items WHERE fkorid = " + orderId;
				
				try {
					ps = conn.prepareStatement(sql);
					ps.executeQuery();
					
				}catch(SQLException e) {
					e.printStackTrace();
					return "Falha ao remover cliente";
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return "Falha ao remover cliente";
		}
		
		
		sql="DELETE from orders WHERE fkclid = " + ((Client) item).getId();       //cria a string do sql
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Falha ao remover cliente";
		}
		
		sql="DELETE from client WHERE id = " + ((Client) item).getId();       //cria a string do sql		
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
            return "Cliente removido com sucesso!";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Falha ao remover cliente";
		}
	}

	@Override
	public String update(Listable item) throws SQLException {
		String sql = "SELECT * FROM client WHERE id = " + ((Client) item).getId();
		
		PreparedStatement ps = null; //Prepara a query e evita sql injection
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newName = ((Client) item).getName() != null ? ((Client) item).getName() : rs.getString("name");
		
		sql = "UPDATE client SET "
				+ "name = '" + newName + "' "				
			+ "WHERE id = " + ((Client) item).getId() + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Cliente alterado com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao alterar o cliente";
		}
	}

}
