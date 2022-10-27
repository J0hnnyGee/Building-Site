package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {
	private static ConnessioneDB istanzaDB = null;
	private Connection connessioneDB = null;
	
	
	
	private ConnessioneDB() {
		
	}
	
	public static ConnessioneDB getIstanza() {
		if(istanzaDB == null) {
			istanzaDB = new ConnessioneDB();
		}
		
		return istanzaDB;
	}
	
	
	
	
	
	public Connection connectToDb() {
		if(connessioneDB == null) {
			try {
				connessioneDB = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cantiere", "postgres", "test");
			}
			catch(SQLException e) {
				System.out.println("SQL Exception: " + e);
				e.printStackTrace();
			}
		}
		
		return connessioneDB;
	}
	
	
	
	
	
	public boolean checkConnectionToDb() {	
		try {
			return connessioneDB.isValid(10);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	public boolean closeDbConnection() {
		try {
			connessioneDB.close();
			connessioneDB = null;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}