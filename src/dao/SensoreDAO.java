package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entita.Sensore;

public class SensoreDAO {
	private Connection connessioneDB;
	private ConnessioneDB istanzaDB = null;
	private PreparedStatement getSensori, inserisciSensore, DatoSensore, sogliaSensore;
	
	public SensoreDAO(){
		istanzaDB = ConnessioneDB.getIstanza();
	}
	
	public ArrayList <Sensore> getSensori(String id) throws SQLException {
		ArrayList <Sensore> listaSensori = new ArrayList<Sensore>();
		try {
			connessioneDB = istanzaDB.connectToDb();
			int id_fk = Integer.parseInt(id);

			getSensori = connessioneDB.prepareStatement("SELECT * "
														+ "FROM  sensore AS s JOIN dipendente AS d ON s.cf_dipendente=d.cf "
														+ "WHERE d.id_cantiere ="+ id_fk
														+ "ORDER BY s.id");
			ResultSet rs = getSensori.executeQuery();
			
			while(rs.next()) {
				Sensore c = new Sensore();
				c.aggiungiSensore(rs.getInt("id"), rs.getString("tipo"), rs.getDouble("dato"), rs.getDouble("soglia"), rs.getString("posizione"), rs.getBoolean("allarme"), 
				   rs.getString("cf_dipendente"), rs.getInt("id_zona"));
				
				listaSensori.add(c);
			}
			rs.close();
			getSensori.close();
			istanzaDB.closeDbConnection();
			
		} catch (SQLException e) {
		}
		
		return listaSensori;
	}
	
	
	public ArrayList <Sensore> getRumore(String id) throws SQLException {
		ArrayList <Sensore> listaSensori = new ArrayList<Sensore>();
		try {
			connessioneDB = istanzaDB.connectToDb();
			int id_fk = Integer.parseInt(id);

			getSensori = connessioneDB.prepareStatement("SELECT * "
														+ "FROM  sensore AS s JOIN dipendente AS d ON s.cf_dipendente=d.cf "
														+ "WHERE (d.id_cantiere ="+ id_fk + ") AND (s.tipo='Rumore') "
														+ "ORDER BY s.id");
			ResultSet rs = getSensori.executeQuery();
			
			while(rs.next()) {
				Sensore c = new Sensore();
				c.aggiungiSensore(rs.getInt("id"), rs.getString("tipo"), rs.getDouble("dato"), rs.getDouble("soglia"), rs.getString("posizione"), rs.getBoolean("allarme"), 
				   rs.getString("cf_dipendente"), rs.getInt("id_zona"));
				
				listaSensori.add(c);
			}
			rs.close();
			getSensori.close();
			istanzaDB.closeDbConnection();
			
		} catch (SQLException e) {
		}
		
		return listaSensori;
	}
	
	
	public ArrayList <Sensore> getGas(String id) throws SQLException {
		ArrayList <Sensore> listaSensori = new ArrayList<Sensore>();
		try {
			connessioneDB = istanzaDB.connectToDb();
			int id_fk = Integer.parseInt(id);

			getSensori = connessioneDB.prepareStatement("SELECT * "
														+ "FROM  sensore AS s JOIN dipendente AS d ON s.cf_dipendente=d.cf "
														+ "WHERE (d.id_cantiere ="+ id_fk + ") AND ((s.tipo='gas') OR (s.tipo='Gas'))"
														+ "ORDER BY s.id");
			ResultSet rs = getSensori.executeQuery();
			
			while(rs.next()) {
				Sensore c = new Sensore();
				c.aggiungiSensore(rs.getInt("id"), rs.getString("tipo"), rs.getDouble("dato"), rs.getDouble("soglia"), rs.getString("posizione"), rs.getBoolean("allarme"), 
				   rs.getString("cf_dipendente"), rs.getInt("id_zona"));
				
				listaSensori.add(c);
			}
			rs.close();
			getSensori.close();
			istanzaDB.closeDbConnection();
		} catch (SQLException e) {
		}
		
		return listaSensori;
	}
	
	
	public void inserisciSensore(Sensore s) throws SQLException {
	try {
		connessioneDB = istanzaDB.connectToDb();
		inserisciSensore = connessioneDB.prepareStatement("INSERT INTO sensore VALUES(nextval('id_sensore_seq'), ?, NULL, ?, ?, ?, ?, ?)");

		inserisciSensore.setString(1, s.getTipo());
		inserisciSensore.setDouble(2, s.getSoglia());
		inserisciSensore.setString(3, s.getPosizione());
		inserisciSensore.setBoolean(4, s.isAllarme());
		inserisciSensore.setString(5, s.getCf_dipendente());
		inserisciSensore.setInt(6, s.getId_zona());

		
		inserisciSensore.executeUpdate();
		
		inserisciSensore.close();
		istanzaDB.closeDbConnection();
	} catch (SQLException e) {
		throw e;
	}
}
	
	
	public void DatoSensore(Sensore s, int id) throws SQLException {
		try {
			connessioneDB = istanzaDB.connectToDb();
			int id_sen = id;
			
			DatoSensore = connessioneDB.prepareStatement("UPDATE sensore SET dato=?"+
																"WHERE id ="+ id_sen);

			DatoSensore.setDouble(1, s.getDato());
			

			
			DatoSensore.executeUpdate();
			
			DatoSensore.close();
			istanzaDB.closeDbConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void SogliaSensore(Sensore s, String tipo, int id) throws SQLException {
		try {
			connessioneDB = istanzaDB.connectToDb();
			sogliaSensore = connessioneDB.prepareStatement("UPDATE sensore SET soglia= ? "+
																" FROM dipendente AS d "+
																" WHERE (tipo = ? AND ?=d.id_cantiere AND d.cf=cf_dipendente)");
		
			sogliaSensore.setDouble(1, s.getSoglia());
			sogliaSensore.setString(2, tipo);
			sogliaSensore.setInt(3, id);
			
			sogliaSensore.executeUpdate();
			
			sogliaSensore.close();
			istanzaDB.closeDbConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
	
}
