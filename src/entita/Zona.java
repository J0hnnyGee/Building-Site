package entita;


public class Zona {
	//Attributi
	private int id;
	private int grandezza;
	private String tipo;
	//FK
	private int id_cantiere;
	private String cf;
	
	//Relazioni
	private Cantiere cantiere;
	private Dipendente dipendente;
	private Sensore[] sensore=new Sensore[2];
	
	public void aggiungiZona(int id, int grandezza, String tipo, int id_cantiere, String cf) {
		setId(id);
		setGrandezza(grandezza);
		setTipo(tipo);
		setId_cantiere(id_cantiere);
		setCf(cf);
	}
	
	public void aggiungiId(int id) {
		setId(id);
	}
	
	public void aggiungiZonaIdAuto(int grandezza, String tipo, int id_cantiere, String cf) {
		setGrandezza(grandezza);
		setTipo(tipo);
		setId_cantiere(id_cantiere);
		setCf(cf);
	}
	
	//Getters and setters
	public Cantiere getCantiere() {
		return cantiere;
	}
	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}
	public Dipendente getDipendente() {
		return dipendente;
	}
	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}
	
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGrandezza() {
		return grandezza;
	}
	public void setGrandezza(int grandezza) {
		this.grandezza = grandezza;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getId_cantiere() {
		return id_cantiere;
	}
	public void setId_cantiere(int id_cantiere) {
		this.id_cantiere = id_cantiere;
	}

	public Sensore[] getSensore() {
		return sensore;
	}

	public void setSensore(Sensore[] sensore) {
		this.sensore = sensore;
	}
	
}
