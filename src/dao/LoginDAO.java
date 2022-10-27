package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entita.Dipendente;
import entita.Login;

public class LoginDAO {
	private Connection connessioneDB;
	private ConnessioneDB istanzaDB = null;
	private PreparedStatement registrazione, getCredenziali, getIdLog;
	
	public LoginDAO() {
		istanzaDB = ConnessioneDB.getIstanza();
	}
	
	
	public void registrazione(Login log, String cf) throws SQLException {
		try {
			connessioneDB = istanzaDB.connectToDb();
			registrazione = connessioneDB.prepareStatement("INSERT INTO login VALUES(?, ?, ?, ?)");

			registrazione.setString(1, log.getUsername());
			registrazione.setString(2, log.getPassword());
			registrazione.setBoolean(3, log.isAdmin());
			registrazione.setString(4, cf);

			
			registrazione.executeUpdate();
			
			registrazione.close();
			istanzaDB.closeDbConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	public ArrayList <Login> getCredenziali() throws SQLException {
		ArrayList <Login> listaCredenziali = new ArrayList <Login>();
		
		try {
			connessioneDB = istanzaDB.connectToDb();
			
			getCredenziali = connessioneDB.prepareStatement("SELECT * "
														+ "FROM login ");
			ResultSet rs = getCredenziali.executeQuery();
			
			while(rs.next()) {
				Login l = new Login();
				l.registra(rs.getString("username"), rs.getString("password"), rs.getBoolean("admin"), rs.getString("cf_dipendente"));
				listaCredenziali.add(l);
			}
			rs.close();
			getCredenziali.close();
			istanzaDB.closeDbConnection();
			
		} catch (SQLException e) {
		}
		return listaCredenziali;
	}
	
	public int getIdLog(String cf_fk) throws SQLException {
		int id = 0;
		try {
			connessioneDB = istanzaDB.connectToDb();
			
			getIdLog = connessioneDB.prepareStatement("SELECT * "
													+ "FROM dipendente "
													+ "WHERE cf ='"+ cf_fk+"'");
			ResultSet rs = getIdLog.executeQuery();
			
			while(rs.next()) {

				Dipendente d = new Dipendente();
				d.aggiungiDipendente(rs.getString("cf"), rs.getString("nome"), rs.getString("cognome"),
						rs.getDate("data_nascita"), rs.getString("email"),
						rs.getString("telefono"), rs.getString("indirizzo"), rs.getDouble("stipendio"),
						rs.getString("ruolo"), rs.getInt("id_cantiere"));
				 id= d.getId_cantiere();
			}
			
			rs.close();
			getIdLog.close();
			istanzaDB.closeDbConnection();
			
		} catch (SQLException e) {
			System.out.print(e.getLocalizedMessage());

		}
		return id;
	}
}
