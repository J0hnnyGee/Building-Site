package entita;

public class Login {
	//Attributi
	private String username;
	private String password;
	private boolean admin;
	
	//FK
	private String cf_dipendente;
	
	//Relazioni
	private Dipendente dipendente;
	
	public void registra(String username, String password, boolean admin,
			String cf_dipendente) {
			setUsername(username);
			setPassword(password);
			setAdmin(admin);
			setCf_dipendente(cf_dipendente);
		}
	
	public void inserite(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	
	
	//Getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getCf_dipendente() {
		return cf_dipendente;
	}
	public void setCf_dipendente(String cf_dipendente) {
		this.cf_dipendente = cf_dipendente;
	}
	public Dipendente getDipendente() {
		return dipendente;
	}
	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}
}
