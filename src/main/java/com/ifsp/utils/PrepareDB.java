package com.ifsp.utils;

import java.sql.Connection;
import java.sql.Statement;

import com.ifsp.connections.DBConnection;

public class PrepareDB {
	private static DBConnection DB = new DBConnection();
	private static Connection conn = DB.getConnection();
	
	public static String createTables() {
		
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

        // books
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

        // shipping_info
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS shipping_info ("
                    + "shipper VARCHAR(50),"
                    + "address VARCHAR(50))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // billinginfo
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS billing_info ("
                    + "email VARCHAR(50),"
                    + "payment VARCHAR(50))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // order
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
            		"CREATE TABLE IF NOT EXISTS orders ("
                            + "id INT PRIMARY KEY AUTO_INCREMENT,"
                            + "date DATETIME DEFAULT CURRENT_TIMESTAMP,"
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
                    "CREATE TABLE IF NOT EXISTS order_items ("
                    + "fkorid INT," //id da venda
                    + "fkboid INT," //id do livro
                    + "quantity INT,"
                    + "CONSTRAINT FK_orid FOREIGN KEY (fkorid)"
                    + " REFERENCES orders(id),"
                    + "CONSTRAINT FK_boid FOREIGN KEY (fkboid)"
                    + " REFERENCES books(id))"
                    + "ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Tabelas criadas!";
	}
	
	public static String populate() {
		
		// client
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "insert into client (id, name) values\r\n"
                    + "	(1, 'Ellen Bosse'),\r\n"
                    + "	(2, 'Cissiee Ivanets'),\r\n"
                    + "	(3, 'Raquel Bubbear'),\r\n"
                    + "	(4, 'Candis Basden'),\r\n"
                    + "	(5, 'Pete Thinn'),\r\n"
                    + "	(6, 'Heidi Tapson'),\r\n"
                    + "	(7, 'Thorny Cluley'),\r\n"
                    + "	(8, 'Enriqueta Gudgeon'),\r\n"
                    + "	(9, 'Gwen Seyler'),\r\n"
                    + "	(10, 'Demeter Lugsdin');");
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        // publisher
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "insert into publisher (id, name, address) values (1, 'Herzog, Ryan and Koch', '672 Thompson Drive'),\r\n"
                    + "	(2, 'Botsford, Halvorson and Hoppe', '0547 Ludington Road'),\r\n"
                    + "	(3, 'Gottlieb-Abernathy', '5 Hansons Way'),\r\n"
                    + "	(4, 'Bergstrom, O''Reilly and Von', '70 Steensland Way'),\r\n"
                    + "	(5, 'Frami, Kihn and Hauck', '7 Scott Court');");
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        // author
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "insert into author (id, name) values (1, 'Di Gircke'),\r\n"
                    + "	(2, 'Lesly Winckles'),\r\n"
                    + "	(3, 'Sansone Coope'),\r\n"
                    + "	(4, 'Adriane Hentzeler'),\r\n"
                    + "	(5, 'Jelene Mottinelli');");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // books
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "insert into books (id, title, author, price, fkpuid, fkauid) values (1, 'Ballad of Narayama, The (Narayama bushiko)', 'Terencio Mathias', 67.22, 4, 2),\r\n"
                    + "    (2, 'Shaun of the Dead', 'Adriane Hentzeler', 173.53, 5, 4),\r\n"
                    + "    (3, 'Norte, the End of History', 'Lesly Winckles', 252.24, 3, 2),\r\n"
                    + "    (4, 'How to Murder Your Wife', 'Adriane Hentzeler', 161.3, 3, 4),\r\n"
                    + "    (5, 'Waking Ned Devine (a.k.a. Waking Ned)', 'Jelene Mottinelli', 39.9, 5, 5),\r\n"
                    + "    (6, 'Hotte in Paradise (Hotte im Paradies)', 'Adriane Hentzeler', 169.99, 3, 4),\r\n"
                    + "    (7, 'Field of Dreams', 'Lesly Winckles', 163.78, 4, 2);");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // account
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "insert into account (id, email, password) values (1, 'sjime0@purevolume.com', 'BIXN9J'),\r\n"
                    + "    (2, 'mhortop1@domainmarket.com', 'zB3vDzgWSD4'),\r\n"
                    + "    (3, 'tnewcomen2@indiegogo.com', 'ZwUF4wIUCI3'),\r\n"
                    + "    (4, 'fhuston3@blinklist.com', '7d9tM6riEB'),\r\n"
                    + "    (5, 'lhackleton4@bizjournals.com', 'cjWXU0'),\r\n"
                    + "    (6, 'spo5@com.com', '7dM5zZ'),\r\n"
                    + "    (7, 'hzamorrano6@infoseek.co.jp', 'VCQEW8VEf0'),\r\n"
                    + "    (8, 'bandrez7@tamu.edu', 'P9AdQ937'),\r\n"
                    + "    (9, 'gcrum8@zdnet.com', 'VHYBuLwM4Im'),\r\n"
                    + "    (10, 'acassedy9@redcross.org', 'OJv5MYbHt');");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // shipping_info
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "insert into shipping_info (shipper, address) values ('Howe-Dach', '25103 Towne Circle'),\r\n"
                    + "    ('Wehner and Sons', '7 Di Loreto Lane'),\r\n"
                    + "    ('Kuhn-Zemlak', '61 Hayes Pass'),\r\n"
                    + "    ('Olson and Sons', '801 Ridge Oak Crossing'),\r\n"
                    + "    ('Walker, Cremin and Ritchie', '64 Toban Alley');");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // billing_info
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "insert into billing_info (email, payment) values ('cwhiffen0@army.mil', 'paypal'),\r\n"
                    + "    ('ibrunesco1@arizona.edu', 'paypal'),\r\n"
                    + "    ('pwagstaff2@ycombinator.com', 'paypal'),\r\n"
                    + "    ('dweeden3@4shared.com', 'credit card'),\r\n"
                    + "    ('bdalton4@dailymotion.com', 'ticket');");
        } catch (Exception e) {
            e.printStackTrace();
        }
     
        // orders
        try (Statement stmt = conn.createStatement()){
        	stmt.execute(
        			"insert into orders (date, fkclid, address, payment) values\r\n"
        			+ "('2022-07-15 08:44:29', 9, '25872 Quincy Way', 'pix'),\r\n"
        			+ "('2022-11-11 13:57:03', 4, '3 Trailsway Circle', 'credit card'),\r\n"
        			+ "('2022-08-20 17:29:01', 1, '88 Grover Street', 'money'),\r\n"
        			+ "('2022-07-20 19:47:06', 7, '557 Pankratz Road', 'money'),\r\n"
        			+ "('2022-03-07 04:59:09', 2, '690 Holy Cross Crossing', 'ticket'),\r\n"
        			+ "('2022-10-29 10:58:30', 2, '9 Havey Lane', 'credit card'),\r\n"
        			+ "('2022-06-09 09:07:11', 4, '4 Monument Pass', 'pix'),\r\n"
        			+ "('2022-05-07 13:06:31', 3, '90339 Dorton Street', 'money'),\r\n"
        			+ "('2022-07-20 01:20:48', 5, '7104 Merry Drive', 'credit card'),\r\n"
        			+ "('2022-09-23 13:26:28', 5, '065 Killdeer Lane', 'pix'),\r\n"
        			+ "('2022-09-11 00:31:46', 1, '40172 East Lane', 'ticket'),\r\n"
        			+ "('2022-09-06 13:23:19', 9, '5 Fieldstone Avenue', 'credit card'),\r\n"
        			+ "('2022-08-26 01:32:30', 1, '9 Beilfuss Road', 'ticket'),\r\n"
        			+ "('2022-09-14 05:11:04', 5, '64 Holy Cross Junction', 'money'),\r\n"
        			+ "('2022-06-28 01:28:57', 7, '228 Larry Court', 'ticket');");
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        // order_items
        try(Statement stmt = conn.createStatement()){
        	stmt.execute(
        			"insert into order_items (fkorid, fkboid, quantity) values (2, 5, 14),\r\n"
        			+ "(4, 1, 9),\r\n"
        			+ "(11, 4, 12),\r\n"
        			+ "(6, 6, 5),\r\n"
        			+ "(9, 6, 11),\r\n"
        			+ "(7, 5, 7),\r\n"
        			+ "(5, 2, 1),\r\n"
        			+ "(12, 4, 3),\r\n"
        			+ "(3, 6, 8),\r\n"
        			+ "(1, 1, 6),\r\n"
        			+ "(13, 4, 2),\r\n"
        			+ "(10, 2, 9),\r\n"
        			+ "(10, 5, 12),\r\n"
        			+ "(9, 1, 5),\r\n"
        			+ "(12, 6, 1);");
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        return "Banco de dados povoado";
	}
}
