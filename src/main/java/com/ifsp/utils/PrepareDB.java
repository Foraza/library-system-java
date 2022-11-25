package com.ifsp.utils;

import java.sql.Connection;
import java.sql.Statement;

import com.ifsp.connections.DBConnection;

public class PrepareDB {
	private static DBConnection DB = new DBConnection();
	private static Connection conn = DB.getConnection();
	
	public static void createTables() {
		
		// client
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS client ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(50))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // publisher
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS publisher ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(50),"
                    + "address VARCHAR(50))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // author
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS author ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(50))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // book
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS books ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "title VARCHAR(50),"
                    + "author VARCHAR(50),"
                    + "price DOUBLE,"
                    + "fkpuid INT,"
                    + "fkauid INT,"
                    + "CONSTRAINT FK_puid FOREIGN KEY (fkpuid)"
                    + " REFERENCES publisher(id),"
                    + "CONSTRAINT FK_auid FOREIGN KEY (fkauid)"
                    + " REFERENCES author(id))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // account
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS account ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "email VARCHAR(50),"
                    + "password VARCHAR(50))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // shippinginfo
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS shippinginfo ("
                    + "shipper VARCHAR(50),"
                    + "address VARCHAR(50))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // billinginfo
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS billinginfo ("
                    + "email VARCHAR(50),"
                    + "payment VARCHAR(50))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // order
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS order ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "date DATETIME"
                    + "fkclid INT,"
                    + "address VARCHAR(50),"
                    + "payment VARCHAR(50),"
                    + "CONSTRAINT FK_clid FOREIGN KEY (fkclid)"
                    + " REFERENCES client(id))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // order itens
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS orderitens ("
                    + "fkorid," //id da venda
                    + "fkboid," //id do livro
                    + "quantity INT,"
                    + "CONSTRAINT FK_orid FOREIGN KEY (fkorid)"
                    + " REFERENCES order(id),"
                    + "CONSTRAINT FK_boid FOREIGN KEY (fkboid)"
                    + " REFERENCES books(id))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
