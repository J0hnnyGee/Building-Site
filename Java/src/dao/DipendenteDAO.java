package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entita.Dipendente;

public class DipendenteDAO {
	private ConnessioneDB istanzaDB = null;
	private Connection connessioneDB = null;
	public PreparedStatement getDipendenti, inserisciDipendente;
	
	public DipendenteDAO() {
		istanzaDB = ConnessioneDB.getIstanza();
	}
	
	public ArrayList <Dipendente> getDipendenti(String id) throws SQLException {
		ArrayList <Dipendente> listaDipendenti = new ArrayList <Dipendente>();
		
		try {
			connessioneDB = istanzaDB.connectToDb();
			int id_fk = Integer.parseInt(id);
			
			getDipendenti = connessioneDB.prepareStatement("SELECT * "
														+ "FROM dipendente "
														+ "WHERE id_cantiere ="+ id_fk
														+ "ORDER BY cognome, nome");
			ResultSet rs = getDipendenti.executeQuery();
			
			while(rs.next()) {
				Dipendente d = new Dipendente();
				d.aggiungiDipendente(rs.getString("cf"), rs.getString("nome"), rs.getString("cognome"),
						rs.getDate("data_nascita"), rs.getString("email"),
						rs.getString("telefono"), rs.getString("indirizzo"), rs.getDouble("stipendio"),
						rs.getString("ruolo"), rs.getInt("id_cantiere"));
				listaDipendenti.add(d);
			}
			rs.close();
			getDipendenti.close();
			istanzaDB.closeDbConnection();
			
		} catch (SQLException e) {
		}
		
		return listaDipendenti;
	}
	
	
	public ArrayList <Dipendente> getOperatori(String id) throws SQLException {
		ArrayList <Dipendente> listaDipendenti = new ArrayList <Dipendente>();
		
		try {
			connessioneDB = istanzaDB.connectToDb();
			int id_fk = Integer.parseInt(id);
			
			getDipendenti = connessioneDB.prepareStatement("SELECT * "
														+ "FROM dipendente "
														+ "WHERE id_cantiere ="+ id_fk+" AND ruolo='Operatore' "
														+ "ORDER BY cognome, nome");
			ResultSet rs = getDipendenti.executeQuery();
			
			while(rs.next()) {
				Dipendente d = new Dipendente();
				d.aggiungiDipendente(rs.getString("cf"), rs.getString("nome"), rs.getString("cognome"),
						rs.getDate("data_nascita"), rs.getString("email"),
						rs.getString("telefono"), rs.getString("indirizzo"), rs.getDouble("stipendio"),
						rs.getString("ruolo"), rs.getInt("id_cantiere"));
				listaDipendenti.add(d);
			}
			rs.close();
			getDipendenti.close();
			istanzaDB.closeDbConnection();

		} catch (SQLException e) {
		}
		
		return listaDipendenti;
	}
	
	
	public void inserisciDipendente(Dipendente d) throws SQLException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			connessioneDB = istanzaDB.connectToDb();
			inserisciDipendente = connessioneDB.prepareStatement("INSERT INTO dipendente VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			inserisciDipendente.setString(1, d.getCf());
			inserisciDipendente.setString(2, d.getNome());
			inserisciDipendente.setString(3, d.getCognome());
			inserisciDipendente.setDate(4, java.sql.Date.valueOf(dateFormat.format(d.getData_nascita())));
			inserisciDipendente.setString(5, d.getEmail());
			inserisciDipendente.setString(6, d.getTelefono());
			inserisciDipendente.setString(7, d.getIndirizzo());
			inserisciDipendente.setDouble(8, d.getStipendio());
			inserisciDipendente.setString(9, d.getRuolo());
			inserisciDipendente.setInt(10, d.getId_cantiere());
			
			inserisciDipendente.executeUpdate();
			
			inserisciDipendente.close();
			istanzaDB.closeDbConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
	
}
