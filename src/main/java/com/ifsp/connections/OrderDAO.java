package com.ifsp.connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifsp.entities.Book;
import com.ifsp.entities.Client;
import com.ifsp.entities.Order;
import com.ifsp.entities.OrderItem;
import com.ifsp.interfaces.DAOInterface;
import com.ifsp.interfaces.Listable;

public class OrderDAO  implements DAOInterface{
	private ClientDAO clDao = new ClientDAO();
	private BookDAO boDao = new BookDAO();
	
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
            	
            	ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
                ps.executeQuery(); //execute consulta
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
    	aux.setClId(rs.getInt("fkclid"));
    	aux.setDate(rs.getDate("date"));
    	aux.setAddress(rs.getString("address"));
    	aux.setPayment(rs.getString("payment"));
    	
    	Client auxClient = (Client) clDao.get(rs.getInt("fkclid"));
    	aux.setClient(auxClient);
    	
    	sql = "SELECT * FROM order_items WHERE fkorid = " + id;
    	
    	try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            rs = ps.executeQuery(); //execute consulta 
 
        } catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	while(rs.next()) {
    		OrderItem item = new OrderItem();
    		Book bookItem = (Book) boDao.get(rs.getInt("fkboid"));
    		
    		item.setBook(bookItem);
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
        	aux.setClId(rs.getInt("fkclid"));
        	aux.setDate(rs.getDate("date"));
        	aux.setAddress(rs.getString("address"));
        	aux.setPayment(rs.getString("payment"));
        	
        	Client auxClient = (Client) clDao.get(rs.getInt("fkclid"));
        	aux.setClient(auxClient);
        	
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
        		Book bookItem = (Book) boDao.get(rs2.getInt("fkboid"));
        		
        		item.setBook(bookItem);
        		item.setBookId(rs2.getInt("fkboid"));
        		item.setQuantity(rs2.getInt("quantity"));
        		
        		aux.addBook(item);
        	}
        	
        	orders.add(aux);
        }
        
		return orders;
	}

	@Override
	public String remove(Listable item) throws SQLException {
		String sql="DELETE from order_items WHERE fkorid = " + ((Order) item).getId();       //cria a string do sql
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
 
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao remover pedido";
		}
        
        sql = "DELETE from orders WHERE id = " + ((Order) item).getId();
        
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
	public String update(Listable item) throws SQLException {
		String sql = "SELECT * FROM author WHERE id = " + ((Order) item).getId();	
		
		PreparedStatement ps = null; //Prepara a query e evita sql injection
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return "Falha ao alterar o pedido";
		}
		
		int newClid = ((Order) item).getClId() != 0 ? ((Order) item).getClId() : rs.getInt("fkclid");
		String newAddress = ((Order) item).getAddress() != null ? ((Order) item).getAddress() : rs.getString("address");
		String newPayment = ((Order) item).getPayment() != null ? ((Order) item).getPayment() : rs.getString("payment");
		
		sql = "UPDATE orders SET "
				+ "date = current_timestamp(),"
				+ "fkclid = " + newClid + ","
				+ "address = '" + newAddress + "',"
				+ "payment = '" + newPayment + "' "
			+ "WHERE id = " + ((Order) item).getId() + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
            ps.executeQuery(); //execute consulta 
            
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Falha ao alterar o pedido";
		}
        
        if(((Order) item).getItems() != null) {
        	
        	//Remove os itens antigos
        	sql = "DELETE FROM order_items WHERE fkorid = " + ((Order) item).getId();
            
            try {
            	ps = conn.prepareStatement(sql);
            	ps.executeQuery();
            	
            }catch(SQLException e){
            	e.printStackTrace();
            	return "Falha ao alterar o pedido";
            }
            
            //Adiciona os itens novos
            try {
            	for(OrderItem i : ((Order) item).getItems()) {
                	sql="INSERT INTO order_items(fkorid, fkboid, quantity) VALUES ("
                			+ ((Order) item).getId() + ","
            				+ i.getBookId() + ","
            				+ i.getQuantity() + ");";
                	
                	ps = conn.prepareStatement(sql); //obtem a conexao e prepara a estrutura para a string sql
                    ps.executeQuery(); //execute consulta
                }
            	
            }catch(SQLException e){
            	e.printStackTrace();
            	return "Falha ao alterar o pedido";
            }
        }
        
        return "Pedido alterados com sucesso";
	}
	
}
