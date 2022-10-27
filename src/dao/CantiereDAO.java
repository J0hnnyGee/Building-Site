package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entita.Cantiere;

public class CantiereDAO {
		private Connection connessioneDB;
		private ConnessioneDB istanzaDB = null;
		private PreparedStatement getDipendenti, chiudi, sospendi, inserisciCantiere, getCantiere;
		
		public CantiereDAO(){
			istanzaDB = ConnessioneDB.getIstanza();
		}
		 

		
		public ArrayList<Cantiere> getCantieri() throws SQLException{
			ArrayList<Cantiere> listaCantieri = new ArrayList<Cantiere>();
			
			try {
				connessioneDB = istanzaDB.connectToDb();
				
				getDipendenti = connessioneDB.prepareStatement("SELECT * "
															+ "FROM  cantiere "
															+ "ORDER BY id");
				ResultSet rs = getDipendenti.executeQuery();
				
				while(rs.next()) {
					Cantiere c = new Cantiere();
					c.aggiungiCantiere(rs.getInt("id"), rs.getString("citta"), rs.getString("via"), rs.getString("proprietario"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
					  rs.getDouble("costo"), rs.getString("stato"));
					
					listaCantieri.add(c);
				}
				rs.close();
				getDipendenti.close();
				istanzaDB.closeDbConnection();
				
			} catch (SQLException e) {
			}
			
			return listaCantieri;
		}


public ArrayList<Cantiere> getCantieriAperti() throws SQLException{
	ArrayList<Cantiere> listaCantieri = new ArrayList<Cantiere>();
	
	try {
		connessioneDB = istanzaDB.connectToDb();
		
		getDipendenti = connessioneDB.prepareStatement("SELECT * "
													+ "FROM  cantiere AS C "
													+ "WHERE C.stato= 'Aperto' OR C.stato='aperto'"
													+ "ORDER BY id");
		ResultSet rs = getDipendenti.executeQuery();
		
		while(rs.next()) {
			Cantiere c = new Cantiere();
			c.aggiungiCantiere(rs.getInt("id"), rs.getString("citta"), rs.getString("via"), rs.getString("proprietario"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
			  rs.getDouble("costo"), rs.getString("stato"));
			
			listaCantieri.add(c);
		}
		rs.close();
		getDipendenti.close();
		istanzaDB.closeDbConnection();
		
	} catch (SQLException e) {
	}
	
	return listaCantieri;
}

public ArrayList<Cantiere> getCantieriChiusi() throws SQLException{
	ArrayList<Cantiere> listaCantieri = new ArrayList<Cantiere>();
	
	try {
		connessioneDB = istanzaDB.connectToDb();
		
		getDipendenti = connessioneDB.prepareStatement("SELECT * "
													+ "FROM  cantiere AS C "
													+ "WHERE (C.stato= 'Finito') OR (C.stato='finito')"
													+ "ORDER BY id");
		ResultSet rs = getDipendenti.executeQuery();
		
		while(rs.next()) {
			Cantiere c = new Cantiere();
			c.aggiungiCantiere(rs.getInt("id"), rs.getString("citta"), rs.getString("via"), rs.getString("proprietario"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
			  rs.getDouble("costo"), rs.getString("stato"));
			
			listaCantieri.add(c);
		}
		rs.close();
		getDipendenti.close();
		istanzaDB.closeDbConnection();
		
	} catch (SQLException e) {
	}
	
	return listaCantieri;
}

public void chiudi(String id) throws SQLException {
	try {
		
	int id_cant=Integer.parseInt(id);

		connessioneDB = istanzaDB.connectToDb();		
		chiudi = connessioneDB.prepareStatement("UPDATE cantiere SET stato='Finito', data_fine=CURRENT_DATE "+
															"WHERE id =?");

		chiudi.setInt(1, id_cant);


		
		chiudi.executeUpdate();
		
		chiudi.close();
		istanzaDB.closeDbConnection();
	} catch (SQLException e) {
		throw e;
	}
}

public void sospendi(String id) throws SQLException {
	try {
	int id_can=Integer.parseInt(id);
	connessioneDB = istanzaDB.connectToDb();		
	sospendi = connessioneDB.prepareStatement("UPDATE cantiere SET stato='Sospeso'"+
														"WHERE id =?");

	sospendi.setInt(1, id_can);

	
	sospendi.executeUpdate();
	
	sospendi.close();
	istanzaDB.closeDbConnection();
	} 
	catch (SQLException e) {
		throw e;
	}
	}

public void deSospendi(String id) throws SQLException {
	try {
	int id_can=Integer.parseInt(id);
	connessioneDB = istanzaDB.connectToDb();		
	sospendi = connessioneDB.prepareStatement("UPDATE cantiere SET stato='Aperto'"+
														"WHERE id =?");

	sospendi.setInt(1, id_can);

	
	sospendi.executeUpdate();
	
	sospendi.close();
	istanzaDB.closeDbConnection();
	} 
	catch (SQLException e) {
		throw e;
	}
	}

public void inserisciCantiere(Cantiere c) throws SQLException {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	try {
		connessioneDB = istanzaDB.connectToDb();
		inserisciCantiere = connessioneDB.prepareStatement("INSERT INTO cantiere VALUES(nextval('id_cantiere_seq'), ?, ?, ?, ?, NULL, ?, 'Aperto')");

		inserisciCantiere.setString(1, c.getCitta());
		inserisciCantiere.setString(2, c.getVia());
		inserisciCantiere.setString(3, c.getProprietario());
		inserisciCantiere.setDate(4, java.sql.Date.valueOf(dateFormat.format(c.getData_inizio())));
		inserisciCantiere.setDouble(5, c.getCosto());

		inserisciCantiere.executeUpdate();
		
		inserisciCantiere.close();
		istanzaDB.closeDbConnection();
	} catch (SQLException e) {
		throw e;
	}
}

public Cantiere getCantiere(int id) throws SQLException{
	ArrayList<Cantiere> listaCantieri = new ArrayList<Cantiere>();
	Cantiere can = null;
	try {
		connessioneDB = istanzaDB.connectToDb();
		
		getCantiere = connessioneDB.prepareStatement("SELECT * "
													+ "FROM  cantiere "
													+ "WHERE id=" + id);
		ResultSet rs = getCantiere.executeQuery();
		
		while(rs.next()) {
			Cantiere c = new Cantiere();
			c.aggiungiCantiere(rs.getInt("id"), rs.getString("citta"), rs.getString("via"), rs.getString("proprietario"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
			  rs.getDouble("costo"), rs.getString("stato"));
			
			listaCantieri.add(c);
			can=c;
		}
		rs.close();
		getCantiere.close();
		istanzaDB.closeDbConnection();
		
	} catch (SQLException e) {
	}
	
	return can;
}


}
