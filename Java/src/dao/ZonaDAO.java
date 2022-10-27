package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entita.Zona;

public class ZonaDAO {
	private Connection connessioneDB;
	private ConnessioneDB istanzaDB = null;
	private PreparedStatement getZone, inserisciZona;
	
	public ZonaDAO() {
		istanzaDB = ConnessioneDB.getIstanza();
	}
	
	public ArrayList <Zona> getZone (String id) throws SQLException{
		ArrayList<Zona> listaZone = new ArrayList<Zona>();
		try {
			connessioneDB = istanzaDB.connectToDb();
			int id_fk = Integer.parseInt(id);

			getZone = connessioneDB.prepareStatement("SELECT * "
														+ "FROM  zona AS z "
														+ "WHERE z.id_cantiere ="+ id_fk
														+ "ORDER BY id");
			ResultSet rs = getZone.executeQuery();
			
			while(rs.next()) {
				Zona zona = new Zona();
				zona.aggiungiZona(rs.getInt("id"), rs.getInt("grandezza"), rs.getString("tipo"), rs.getInt("id_cantiere"), 
						 rs.getString("cf"));
				
				listaZone.add(zona);
			}
			rs.close();
			getZone.close();
			istanzaDB.closeDbConnection();
			
		} catch (SQLException e) {
		}
		
		return listaZone;
	}
	
	
	public ArrayList <Zona> getZoneSensori (String id) throws SQLException{
		ArrayList<Zona> listaZone = new ArrayList<Zona>();
		try {
			connessioneDB = istanzaDB.connectToDb();
			int id_fk = Integer.parseInt(id);

			getZone = connessioneDB.prepareStatement("SELECT z.id "
													+ "FROM sensore as s RIGHT OUTER JOIN zona as z ON s.id_zona=z.id "
													+ "WHERE z.id_cantiere ="+id_fk
													+ " GROUP BY z.id "
													+ "HAVING COUNT (s.id_zona)<2 "
													+ "ORDER BY z.id");

			ResultSet rs = getZone.executeQuery();
			
			while(rs.next()) {
				Zona zona = new Zona();
				zona.aggiungiId(rs.getInt("id"));
				
				listaZone.add(zona);
			}
			rs.close();
			getZone.close();
			istanzaDB.closeDbConnection();
			
		} catch (SQLException e) {
		}
		
		return listaZone;
	}
	
	
	public void inserisciZona(Zona z) throws SQLException {
		
		try {
			connessioneDB = istanzaDB.connectToDb();
			inserisciZona = connessioneDB.prepareStatement("INSERT INTO zona VALUES(nextval('id_zona_seq'), ?, ?, ?, ?)");

			inserisciZona.setInt(1, z.getGrandezza());
			inserisciZona.setString(2, z.getTipo());
			inserisciZona.setInt(3, z.getId_cantiere());
			inserisciZona.setString(4, z.getCf());
			
			
			inserisciZona.executeUpdate();
			
			inserisciZona.close();
			istanzaDB.closeDbConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
}
