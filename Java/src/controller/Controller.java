package controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CantiereDAO;
import dao.ConnessioneDB;
import dao.DipendenteDAO;
import dao.LoginDAO;
import dao.SensoreDAO;
import dao.ZonaDAO;
import entita.Cantiere;
import entita.Dipendente;
import entita.Login;
import entita.Sensore;
import entita.Zona;
import gui.AggiungiDatoGasGUI;
import gui.AggiungiDatoRumoreGUI;
import gui.AggiungiDipendenteGUI;
import gui.CantieriApertiGUI;
import gui.CantieriChiusiGUI;
import gui.ConfermaChiusuraGUI;
import gui.ConfermaDeSospensioneGUI;
import gui.ConfermaSospensioneGUI;
import gui.CreaCantiereGUI;
import gui.CreaCapoCantiereGUI;
import gui.HomeAdminGUI;
import gui.HomeCapoCantiereGUI;
import gui.LoginGUI;
import gui.ModificaSogliaGasGUI;
import gui.ModificaSogliaRumoreGUI;
import gui.NuovaZonaGUI;
import gui.NuoveCredenzialiGUI;
import gui.NuovoSensoreGUI;
import gui.VisualizzaDipendentiGUI;
import gui.VisualizzaSensoriGUI;
import gui.VisualizzaZoneGUI;

public class Controller {

	private LoginGUI accessoGUI;
	private HomeAdminGUI adminGUI;
	private CreaCantiereGUI creaCantiereGUI;
	private CreaCapoCantiereGUI creaCapocantiereGUI;
	private NuoveCredenzialiGUI	registrazioneGUI;
	private CantieriApertiGUI listaApertiGUI;
	private CantieriChiusiGUI listaChiusiGUI;
	private HomeCapoCantiereGUI capocantiereGUI;
	private AggiungiDipendenteGUI aggiungiDipendenteGUI;
	private VisualizzaDipendentiGUI visualizzaDipendentiGUI;
	private NuovaZonaGUI nuovaZonaGUI;
	private VisualizzaZoneGUI visualizzaZoneGUI;
	private NuovoSensoreGUI nuovoSensoreGUI;
	private VisualizzaSensoriGUI gestioneSensoriGUI;
	private ConfermaChiusuraGUI confermaChiusuraGUI;
	private ConfermaSospensioneGUI confermaSospensioneGUI;
	private ConfermaDeSospensioneGUI confermaDeSospensioneGUI;
	private AggiungiDatoRumoreGUI aggiungiDatoRumoreGUI;
	private AggiungiDatoGasGUI aggiungiDatoGasGUI;
	private ModificaSogliaRumoreGUI modificaSogliaRumoreGUI;
	private ModificaSogliaGasGUI modificaSogliaGasGUI;
	private static ConnessioneDB istanzaDB = null;
	private Connection connessioneDB = null;
	private DipendenteDAO dipendenteDAO;
	
	public static void main(String[] args) {
		Controller c = new Controller();

	}

	public Controller(){							//Prima finestra che si apre
		apriAccessoGUI();			//Oggetto finestra (quale)
		accessoGUI.setVisible(true);				//visibile
		accessoGUI.setLocationRelativeTo(null); 	//Aprila al centro schermo
		istanzaDB = ConnessioneDB.getIstanza();
		connessioneDB = istanzaDB.connectToDb();
		}
	
	
	public void apriAccessoGUI(){
		ArrayList <Login> listaCredenziali = new ArrayList<>();
		try {
			LoginDAO log = new LoginDAO();
			listaCredenziali = log.getCredenziali();
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		accessoGUI = new LoginGUI(this, listaCredenziali);	
		accessoGUI.setVisible(true);	
		accessoGUI.setLocationRelativeTo(null);
	}
	
	public void apriAdminGUI() {
		ArrayList <Cantiere> listaCantieri = new ArrayList<>();
		try {
			CantiereDAO can = new CantiereDAO();
			listaCantieri = can.getCantieri();
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		adminGUI = new HomeAdminGUI(this, listaCantieri);
		adminGUI.setVisible(true);
		accessoGUI.dispose();
		adminGUI.setLocationRelativeTo(null);
	}
	
	public ArrayList <Cantiere> aggiornaCantieri(){
		ArrayList <Cantiere> listaCantieri = new ArrayList<>();
		try {
			CantiereDAO can = new CantiereDAO();
			listaCantieri = can.getCantieri();
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		return listaCantieri;
		
	}
	
	public void apriCreaCantiereGUI(int id) {
		creaCantiereGUI = new CreaCantiereGUI(this, id);
		creaCantiereGUI.setVisible(true);
		creaCantiereGUI.setLocationRelativeTo(null);
		
	}
	
	public void inserisciCantiere(Cantiere c, int id) throws SQLException{
		CantiereDAO can = new CantiereDAO();
		can.inserisciCantiere(c); 
		apriCreaCapoCantiereGUI(id);
	}
	
	public void apriCreaCapoCantiereGUI(int id) {
		creaCapocantiereGUI = new CreaCapoCantiereGUI(this, id);
		creaCapocantiereGUI.setVisible(true);
		creaCapocantiereGUI.setLocationRelativeTo(null);
		creaCantiereGUI.dispose();
	}
	
	public void inserisciCapoCantiere(Dipendente d) throws SQLException{
		DipendenteDAO dip = new DipendenteDAO();
		dip.inserisciDipendente(d); 
		String cf = d.getCf();
		apriRegistrazioneCapoGUI(cf);
		creaCapocantiereGUI.dispose();
	}
	
	public void apriRegistrazioneCapoGUI(String cf) {
		ArrayList <Login> listaCredenziali = new ArrayList<>();
		try {
			LoginDAO log = new LoginDAO();
			listaCredenziali = log.getCredenziali();
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		registrazioneGUI = new NuoveCredenzialiGUI(this, cf, listaCredenziali);
		registrazioneGUI.setVisible(true);
		registrazioneGUI.setLocationRelativeTo(null);
		creaCapocantiereGUI.dispose();
	}
	
	public void inserisciCredenzialiCapo(Login log, String cf) throws SQLException{
		LoginDAO login = new LoginDAO();
		login.registrazione(log, cf); 
	}
	
	public void chiudiRegistrazioneGUI() {
		registrazioneGUI.dispose();
	}
	
	public void apriRegistrazioneAdminGUI() {
		String cf = null;
		ArrayList <Login> listaCredenziali = new ArrayList<>();
		try {
			LoginDAO log = new LoginDAO();
			listaCredenziali = log.getCredenziali();
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		registrazioneGUI = new NuoveCredenzialiGUI(this, cf, listaCredenziali);
		registrazioneGUI.setVisible(true);
		registrazioneGUI.setLocationRelativeTo(null);
	}
	
	public void apriCantieriApertiGUI() {
		ArrayList <Cantiere> listaCantieri = new ArrayList<>();
		try {
			CantiereDAO can = new CantiereDAO();
			listaCantieri = can.getCantieriAperti();
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		listaApertiGUI = new CantieriApertiGUI(this, listaCantieri);
		listaApertiGUI.setVisible(true);
		listaApertiGUI.setLocationRelativeTo(null);
	}
	
	public void chiudiCantieriApertiGUI() {
		listaApertiGUI.dispose();
	}
	
	public void apriCantieriChiusiGUI() {
		ArrayList <Cantiere> listaCantieri = new ArrayList<>();
		try {
			CantiereDAO can = new CantiereDAO();
			listaCantieri = can.getCantieriChiusi();
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		listaChiusiGUI = new CantieriChiusiGUI(this, listaCantieri);
		listaChiusiGUI.setVisible(true);
		listaChiusiGUI.setLocationRelativeTo(null);
	}
	
	public void chiudiCantieriChiusiGUI() {
		listaChiusiGUI.dispose();
	}
	
	public void apriHomeCapoCantiereGUI(String id) {
		capocantiereGUI = new HomeCapoCantiereGUI(this, id);
		capocantiereGUI.setVisible(true);
		capocantiereGUI.setLocationRelativeTo(null);
		accessoGUI.dispose();
	}
	
	public void chiudiHomeCapoCantiereGUI() {
	capocantiereGUI.dispose();
	}

	
	public Cantiere aggiornaCantiere(String id){
		int id_int = Integer.parseInt(id);

		Cantiere CantiereTmp = new Cantiere();
		try {
			CantiereDAO can = new CantiereDAO();
			CantiereTmp = can.getCantiere(id_int);
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		return CantiereTmp;
		
	}
	
	public void apriAggiungiDipendenteGUI(String id) {
		aggiungiDipendenteGUI = new AggiungiDipendenteGUI(this, id);
		aggiungiDipendenteGUI.setVisible(true);
		aggiungiDipendenteGUI.setLocationRelativeTo(null);
	}
	
	public void inserisciDipendente(Dipendente d) throws SQLException{
		DipendenteDAO dip = new DipendenteDAO();
		dip.inserisciDipendente(d); 
	}
	
	public void chiudiAggiungiDipendenteGUI() {
		aggiungiDipendenteGUI.dispose();
	}
	
	public void apriVisualizzaDipendentiGUI(String id) {
		ArrayList <Dipendente> listaDipendenti = new ArrayList<>();
		try {
			DipendenteDAO dip = new DipendenteDAO();
			listaDipendenti = dip.getDipendenti(id);
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		visualizzaDipendentiGUI = new VisualizzaDipendentiGUI(this, listaDipendenti, id);
		visualizzaDipendentiGUI.setVisible(true);
		visualizzaDipendentiGUI.setLocationRelativeTo(null);
	}
	
	public void chiudiVisualizzaDipendentiGUI() {
		visualizzaDipendentiGUI.dispose();
	}
	
	public void apriNuovaZonaGUI(String id) {
		nuovaZonaGUI = new NuovaZonaGUI(this, id);
		nuovaZonaGUI.setVisible(true);
		nuovaZonaGUI.setLocationRelativeTo(null);
	}
	
	public void inserisciZona(Zona z) throws SQLException{
		ZonaDAO zona = new ZonaDAO();
		zona.inserisciZona(z); 
	}
	
	public void chiudiNuovaZonaGUI() {
		nuovaZonaGUI.dispose();
	}
	
	public void apriVisualizzaZoneGUI(String id) {
		ArrayList <Zona> listaZone = new ArrayList<>();
		try {
			ZonaDAO zona = new ZonaDAO();
			listaZone = zona.getZone(id);
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		visualizzaZoneGUI = new VisualizzaZoneGUI(this, listaZone);
		visualizzaZoneGUI.setVisible(true);
		visualizzaZoneGUI.setLocationRelativeTo(null);
	}
	
	public void chiudiVisualizzaZoneGUI() {
		visualizzaZoneGUI.dispose();
	}
	
	public void apriNuovoSensoreGUI(String id) {
		nuovoSensoreGUI = new NuovoSensoreGUI(this, id);
		nuovoSensoreGUI.setVisible(true);
		nuovoSensoreGUI.setLocationRelativeTo(null);
	}
	
	public void inserisciSensore(Sensore s) throws SQLException{
		SensoreDAO sen = new SensoreDAO();
		sen.inserisciSensore(s); 
	}
	
	public void chiudiNuovoSensoreGUI() {
		nuovoSensoreGUI.dispose();
	}
	
	
	public void apriVisualizzaSensoriGUI(String id) {
		ArrayList <Sensore> listaSensori = new ArrayList<>();
		try {
			SensoreDAO sen = new SensoreDAO();
			listaSensori = sen.getSensori(id);
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		gestioneSensoriGUI = new VisualizzaSensoriGUI(this, listaSensori, id);
		gestioneSensoriGUI.setVisible(true);
		gestioneSensoriGUI.setLocationRelativeTo(null);
	}
	
	
	public void chiudiVisualizzaSensoriGUI() {
		gestioneSensoriGUI.dispose();
	}
	
	public void apriConfermaChiusuraGUI(String id) {
		confermaChiusuraGUI = new ConfermaChiusuraGUI(this, id);
		confermaChiusuraGUI.setVisible(true);
		confermaChiusuraGUI.setLocationRelativeTo(null);
	}
	
	public void chiudiConfermaChiusuraGUI() {
		confermaChiusuraGUI.dispose();
	}
	
	public void apriConfermaSospensioneGUI(String id) {
		confermaSospensioneGUI = new ConfermaSospensioneGUI(this, id);
		confermaSospensioneGUI.setVisible(true);
		confermaSospensioneGUI.setLocationRelativeTo(null);
	}
	
	public void sospensione(String id) throws SQLException{
		CantiereDAO can = new CantiereDAO();
		can.sospendi(id); 
	}
	
	public void chiudiConfermaSospensioneGUI() {
		confermaSospensioneGUI.dispose();
		
	}
	
	public void chiusura(String id) throws SQLException{
		CantiereDAO can = new CantiereDAO();
		can.chiudi(id); 
	}
	
	public void apriConfermaDeSospensioneGUI(String id) {
		confermaDeSospensioneGUI = new ConfermaDeSospensioneGUI(this, id);
		confermaDeSospensioneGUI.setVisible(true);
		confermaDeSospensioneGUI.setLocationRelativeTo(null);
	}
	
	public void deSospensione(String id) throws SQLException{
		CantiereDAO can = new CantiereDAO();
		can.deSospendi(id); 
	}
	
	public void chiudiConfermaDeSospensioneGUI() {
		confermaDeSospensioneGUI.dispose();
		
	}
	
	public void apriAggiungiDatoRumoreGUI(int id) {
		aggiungiDatoRumoreGUI = new AggiungiDatoRumoreGUI(this, id);
		aggiungiDatoRumoreGUI.setVisible(true);
		aggiungiDatoRumoreGUI.setLocationRelativeTo(null);
	}
	
	public void DatoSensore(Sensore s, int id) throws SQLException{
		SensoreDAO sen = new SensoreDAO();
		sen.DatoSensore(s, id); 
	}
	
	public void chiudiAggiungiDatoRumoreGUI() {
		aggiungiDatoRumoreGUI.dispose();
	}
	
	public void apriAggiungiDatoGasGUI(int id) {
		aggiungiDatoGasGUI = new AggiungiDatoGasGUI(this, id);
		aggiungiDatoGasGUI.setVisible(true);
		aggiungiDatoGasGUI.setLocationRelativeTo(null);
	}
	
	public void chiudiAggiungiDatoGasGUI() {
		aggiungiDatoGasGUI.dispose();
	}
	
	
	public void apriModificaSogliaRumoreGUI(String tipo, int id) {
		modificaSogliaRumoreGUI = new ModificaSogliaRumoreGUI(this, tipo, id);
		modificaSogliaRumoreGUI.setVisible(true);
		modificaSogliaRumoreGUI.setLocationRelativeTo(null);
	}
	
	public void SogliaSensore(Sensore s, String tipo, int id) throws SQLException{
		SensoreDAO sen = new SensoreDAO();
		sen.SogliaSensore(s, tipo, id); 
	}
	
	public void chiudiModificaSogliaRumoreGUI() {
		modificaSogliaRumoreGUI.dispose();
	}
	
	public void apriModificaSogliaGasGUI(String tipo, int id) {
		modificaSogliaGasGUI = new ModificaSogliaGasGUI(this, tipo, id);
		modificaSogliaGasGUI.setVisible(true);
		modificaSogliaGasGUI.setLocationRelativeTo(null);
	}
	
	public void chiudiModificaSogliaGasGUI() {
		modificaSogliaGasGUI.dispose();
	}
	
	public  ArrayList <Sensore> aggiornaSensori(String id){
		
		ArrayList <Sensore> tmp = new ArrayList <Sensore>();
		
		try {
			SensoreDAO sen = new SensoreDAO();
			tmp = sen.getSensori(id);
		}
		catch (SQLException e){
			System.out.print(e.getLocalizedMessage());
			}
		return tmp;
		
	}
	
public  ArrayList <Sensore> filtraGas(String id){
		
	ArrayList <Sensore> listaGas = new ArrayList<>();
	try {
		SensoreDAO sen = new SensoreDAO();
		listaGas = sen.getGas(id);

	}
	catch (SQLException e){
		System.out.print(e.getLocalizedMessage());
		}
	return listaGas;
		
	}
	

public  ArrayList <Sensore> filtraRumore(String id){
	
	ArrayList <Sensore> listaRumore = new ArrayList<>();
	try {
		SensoreDAO sen = new SensoreDAO();
		listaRumore = sen.getRumore(id);

	}
	catch (SQLException e){
		System.out.print(e.getLocalizedMessage());
		}
	return listaRumore;
	
}

}
