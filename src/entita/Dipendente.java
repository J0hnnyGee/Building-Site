package entita;
import java.util.Date;

public class Dipendente {
	//Attributi
	private String cf;
	private String nome;
	private String cognome;
	private Date data_nascita;
	private String email;
	private String telefono;
	private String indirizzo;
	private double stipendio;
	private String ruolo;
	
	//FK
	private int id_cantiere;
	
	//Relazioni
	private Cantiere cantiere;
	private Zona zona;
	private Sensore[] sensore=new Sensore[2];
	private Login login;

	
	public void aggiungiDipendente(String cf, String nome, String cognome, Date data_nascita, String email, 
			   String telefono, String indirizzo, Double stipendio, String ruolo, int id_cantiere) {
			setCf(cf);
			setNome(nome);
			setCognome(cognome);
			setIndirizzo(indirizzo);
			setData_nascita(data_nascita);
			setEmail(email);
			setTelefono(telefono);
			setStipendio(stipendio);
			setRuolo(ruolo);
			setId_cantiere(id_cantiere);
			
		}
	
	public void aggiungiCapoCantiere(String cf, String nome, String cognome, Date data_nascita, String email, 
			   String telefono, String indirizzo, Double stipendio, String ruolo, int id_cantiere) {
			setCf(cf);
			setNome(nome);
			setCognome(cognome);
			setIndirizzo(indirizzo);
			setData_nascita(data_nascita);
			setEmail(email);
			setTelefono(telefono);
			setStipendio(stipendio);
			setRuolo(ruolo);
			setId_cantiere(id_cantiere);
			
		}
	
	//Getters and setters
	public Cantiere getCantiere() {
		return cantiere;
	}
	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public Sensore[] getSensore() {
		return sensore;
	}
	public void setSensore(Sensore[] sensore) {
		this.sensore = sensore;
	}
	public int getId_cantiere() {
		return id_cantiere;
	}
	public void setId_cantiere(int id_cantiere) {
		this.id_cantiere = id_cantiere;
	}
	
	public String getCf(){
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getData_nascita() {
		return data_nascita;
	}
	public void setData_nascita(Date data_nascita) {
		this.data_nascita = data_nascita;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public double getStipendio() {
		return stipendio;
	}
	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	
}
