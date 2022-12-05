package com.ifsp.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifsp.entities.Order;
import com.ifsp.entities.OrderItem;
import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class OrderDAO  implements DAOInterface{
	Connection conn = new DBConnection().getConnection();
	
	@Override
	public String add(Listable item) throws SQLException {
		List<OrderItem> items = ((Order) item).getItems(); //Pega todos os itens do pedido
		
		String sql="INSERT INTO orders(id, fkclid, address, payment) VALUES ("
				+ ((Order) item).getId() + ","
				+ ((Order) item).getClId() + ","
				+ "'" + ((Order) item).getAddress() + "',"
				+ "'" + ((Order) item).getPayment() + "'" + ");";
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao adicionar pedido";
		}
        
        try{
        	for(OrderItem i : items) {
            	sql="INSERT INTO order_items(fkorid, fkboid, quantity) VALUES ("
            			+ ((Order) item).getId() + ","
        				+ i.getBookId() + ","
        				+ i.getQuantity() + ");";
            }
        
        	return "Pedido adicionado com sucesso";
        	
        }catch (Exception e) {
        	e.printStackTrace();
        	return "Falha ao adicionar pedido";
        }
        
        
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from orders WHERE id = " + id;       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
        rs.next();
        Order aux = new Order();
    	aux.setId(rs.getInt("id"));
    	aux.setDate(rs.getDate("date"));
    	aux.setClId(rs.getInt("fkclid"));
    	aux.setAddress(rs.getString("address"));
    	aux.setPayment(rs.getString("payment"));
    	
    	sql = "SELECT * FROM order_items WHERE fkorid = " + id;
    	
    	try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	while(rs.next()) {
    		OrderItem item = new OrderItem();
    		item.setBookId(rs.getInt("fkboid"));
    		item.setQuantity(rs.getInt("quantity"));
    		
    		aux.addBook(item);
    	}
    	
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> orders = new ArrayList<>();
		String sql="SELECT * FROM orders";       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        ResultSet rs = null; //criar o resultSet, uma lista especializada para receber dados SQL
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	Order aux = new Order();
        	aux.setId(rs.getInt("id"));
        	aux.setDate(rs.getDate("date"));
        	aux.setClId(rs.getInt("fkclid"));
        	aux.setAddress(rs.getString("address"));
        	aux.setPayment(rs.getString("payment"));
        	
        	sql = "SELECT * FROM order_items WHERE fkorid = " + aux.getId();
        	ResultSet rs2 = null;
        	
        	try {
                ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
                rs2 = ps.executeQuery(); //execute consulta 
     
            } catch (SQLException e) {
    			e.printStackTrace();
    		}
        	
        	while(rs2.next()) {
        		OrderItem item = new OrderItem();
        		item.setBookId(rs2.getInt("fkboid"));
        		item.setQuantity(rs2.getInt("quantity"));
        		
        		aux.addBook(item);
        	}
        	
        	orders.add(aux);
        }
        
		return orders;
	}

	@Override
	public String remove(int id) throws SQLException {
		String sql="DELETE from order_items WHERE fkorid = " + id;       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
 
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao remover pedido";
		}
        
        sql = "DELETE from orders WHERE id = " + id;
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            return "Pedido removido com sucesso";
 
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao remover pedido";
		}
	}

	@Override
	//Faz sentido utilizar o método update para pedidos?
	public String update(int id, Listable item) throws SQLException {
		return null;
	}
	
}
