package entita;
import java.util.ArrayList;
import java.util.Date;

public class Cantiere {
	//Attributi
	private int id;
	private String citta;
	private String via;
	private String proprietario;
	private Date data_inizio;
	private Date data_fine;
	private double costo;
	private String stato;
	
	//Relazioni
	private ArrayList <Zona> zona;
	private ArrayList <Dipendente> dipendente;
	
	public void aggiungiCantiere(int id, String citta, String via, String proprietario, Date data_inizio, 
			   Date data_fine, double costo, String stato) {
			setId(id);
			setCitta(citta);
			setVia(via);
			setProprietario(proprietario);
			setData_inizio(data_inizio);
			setData_fine(data_fine);
			setCosto(costo);
			setStato(stato);
		}
	
	public void insCantiere(String citta, String via, String proprietario, Date data_inizio, 
			   double costo) {
			setCitta(citta);
			setVia(via);
			setProprietario(proprietario);
			setData_inizio(data_inizio);
			setCosto(costo);
		}
	
	//Getters and setters
	public ArrayList<Zona> getZona() {
		return zona;
	}
	public void setZona(ArrayList<Zona> zona) {
		this.zona = zona;
	}
	public ArrayList<Dipendente> getDipendente() {
		return dipendente;
	}
	public void setDipendente(ArrayList<Dipendente> dipendente) {
		this.dipendente = dipendente;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	public Date getData_inizio() {
		return data_inizio;
	}
	public void setData_inizio(Date data_inizio) {
		this.data_inizio = data_inizio;
	}
	public Date getData_fine() {
		return data_fine;
	}
	public void setData_fine(Date data_fine) {
		this.data_fine = data_fine;
	}
	
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
}
