package entita;

public class Sensore {
	//Attributi
	private int id;
	private String tipo;
	private double dato;
	private double soglia;
	private String posizione;
	private boolean allarme;
	//FK
	private String cf_dipendente;
	private int id_zona;
	
	//Relazioni
	private Dipendente dipendente;
	private Zona zona;
	
	public void aggiungiSensore(int id, String tipo, double dato, double soglia, String posizione, 
			   boolean allarme, String cf_dipendente, int id_zona) {
			setId(id);
			setTipo(tipo);
			setDato(dato);
			setSoglia(soglia);
			setPosizione(posizione);
			setAllarme(allarme);
			setCf_dipendente(cf_dipendente);
			setId_zona(id_zona);
		}
	
	public void aggiungiSensoreIdAuto(String tipo, double soglia, String posizione, 
			   boolean allarme, String cf_dipendente, int id_zona) {
			setTipo(tipo);
			setSoglia(soglia);
			setPosizione(posizione);
			setAllarme(allarme);
			setCf_dipendente(cf_dipendente);
			setId_zona(id_zona);
		}
	
	public void dato(double dato) {
		setDato(dato);
	}
	
	public void soglia(double soglia) {
		setSoglia(soglia);
	}
	
	//Getters and setters
	public Dipendente getDipendente() {
		return dipendente;
	}
	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getDato() {
		return dato;
	}
	public void setDato(double dato) {
		this.dato = dato;
	}
	
	public double getSoglia() {
		return soglia;
	}
	public void setSoglia(double soglia) {
		this.soglia = soglia;
	}
	
	public String getPosizione() {
		return posizione;
	}
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	public boolean isAllarme() {
		return allarme;
	}
	public void setAllarme(boolean allarme) {
		this.allarme = allarme;
	}
	public String getCf_dipendente() {
		return cf_dipendente;
	}
	public void setCf_dipendente(String cf_dipendente) {
		this.cf_dipendente = cf_dipendente;
	}

	public int getId_zona() {
		return id_zona;
	}

	public void setId_zona(int id_zona) {
		this.id_zona = id_zona;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
}
