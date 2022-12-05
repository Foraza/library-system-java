package com.ifsp.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifsp.entities.Account;
import com.ifsp.entities.Account;
import com.ifsp.entities.Account;
import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class AccountDAO implements DAOInterface{
	private Connection conn = new DBConnection().getConnection();

	@Override
	public String add(Listable item) throws SQLException {
		String sql="INSERT INTO account(id, email, password) VALUES ("
				+ ((Account) item).getId() + ","
				+ "'" + ((Account) item).getEmail() + "',"
				+ "'" + ((Account) item).getPassword() + "'" + ");";
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Conta adicionada com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao adicionar conta";
		}
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from account WHERE id = " + id;       //cria a string do sql
        
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
        Account aux = new Account();
    	aux.setId(rs.getInt("id"));
    	aux.setEmail(rs.getString("email"));
    	
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> accounts = new ArrayList<>();
		String sql="Select * from account";       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	Account aux = new Account();
        	aux.setId(rs.getInt("id"));
        	aux.setEmail(rs.getString("email"));
        	
        	accounts.add(aux);
        }
        
		return accounts;
	}

	@Override
	public String remove(int id) throws SQLException {
		String sql="DELETE from account WHERE id = " + id;       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
            return "Conta removida com sucesso!";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Falha ao remover conta";
		}
	}

	@Override
	public String update(int id, Listable item) throws SQLException {
		String sql = "SELECT * FROM account WHERE id = " + id;
		
		PreparedStatement ps = null; //Prepara a query e evita sql injection
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newEmail = ((Account) item).getEmail() != null ? ((Account) item).getEmail() : rs.getString("email");
		String newPassword = ((Account) item).getPassword() != null ? ((Account) item).getPassword() : rs.getString("password");
		
		sql = "UPDATE account SET "
				+ "email = '" + newEmail + "',"
				+ "password = '" + newPassword+ "' "
			+ "WHERE id = " + id + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Conta alterada com sucesso";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao alterar a conta";
		}
	}

}
