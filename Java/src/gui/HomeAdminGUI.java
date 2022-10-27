package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import dao.CantiereDAO;
import entita.Cantiere;
import tableModels.ListaCantieriModel;

public class HomeAdminGUI extends JFrame {
	private String[] nomiColonne = {"Id", "Citta", "Via", "Stato"};
	private ArrayList <Cantiere> listaCantieri = new ArrayList<>();
	private JTable table;

	public HomeAdminGUI(controller.Controller controller, ArrayList<Cantiere> listaCantieri) {
		//Grandezza finestra
		setSize(840,546);
		setResizable(false);

		//Dichiarazione Layout
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		//Font
		Font heading_font = new Font("Serif", Font.PLAIN, 18);
		Font info_font = new Font("Serif", Font.PLAIN, 13);
		Font opzioni_font = new Font("Serif", Font.PLAIN, 18);

		JLabel lista_label = new JLabel("Lista Cantieri");
		springLayout.putConstraint(SpringLayout.NORTH, lista_label, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lista_label, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lista_label);
		lista_label.setFont(heading_font);
		
		
		JButton info_button = new JButton("Info Cantiere");
		springLayout.putConstraint(SpringLayout.NORTH, info_button, 438, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, info_button, -396, SpringLayout.EAST, getContentPane());
		getContentPane().add(info_button);
		info_button.setFont(info_font);
		info_button.setFont(opzioni_font);
		info_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(check(row)) {
				controller.apriHomeCapoCantiereGUI(infoCantiere(row));
				}
			}});
		
		JButton cantiere_button = new JButton("Crea Cantiere");
		springLayout.putConstraint(SpringLayout.NORTH, cantiere_button, 35, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, cantiere_button, 439, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cantiere_button, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(cantiere_button);
		cantiere_button.setFont(opzioni_font);
		cantiere_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id= infoCantiereNoClic(listaCantieri);
				controller.apriCreaCantiereGUI(id);

			}});
		
		JButton admin_button = new JButton("Crea Admin");
		springLayout.putConstraint(SpringLayout.SOUTH, cantiere_button, -22, SpringLayout.NORTH, admin_button);
		springLayout.putConstraint(SpringLayout.WEST, admin_button, 439, SpringLayout.WEST, getContentPane());
		getContentPane().add(admin_button);
		admin_button.setFont(opzioni_font);
		admin_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.apriRegistrazioneAdminGUI();
				
			}});
		
		JButton aperti_button = new JButton("Cantieri Aperti");
		springLayout.putConstraint(SpringLayout.NORTH, aperti_button, 245, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, aperti_button, 439, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, aperti_button, -179, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, aperti_button, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, admin_button, -105, SpringLayout.NORTH, aperti_button);
		springLayout.putConstraint(SpringLayout.SOUTH, admin_button, -22, SpringLayout.NORTH, aperti_button);
		springLayout.putConstraint(SpringLayout.EAST, admin_button, 375, SpringLayout.WEST, aperti_button);
		getContentPane().add(aperti_button);
		aperti_button.setFont(opzioni_font);
		aperti_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.apriCantieriApertiGUI();
				
			}});
		
		JButton chiusi_button = new JButton("Cantieri Chiusi");
		springLayout.putConstraint(SpringLayout.NORTH, chiusi_button, 18, SpringLayout.SOUTH, aperti_button);
		springLayout.putConstraint(SpringLayout.WEST, chiusi_button, 439, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, chiusi_button, -78, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, chiusi_button, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(chiusi_button);
		chiusi_button.setFont(opzioni_font);
		chiusi_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.apriCantieriChiusiGUI();
				
				
			}});
		ListaCantieriModel cantieriModel = new ListaCantieriModel(listaCantieri, nomiColonne);
		table = new JTable(cantieriModel);
		springLayout.putConstraint(SpringLayout.NORTH, table, 6, SpringLayout.SOUTH, lista_label);
		springLayout.putConstraint(SpringLayout.WEST, table, 8, SpringLayout.WEST, lista_label);
		springLayout.putConstraint(SpringLayout.SOUTH, table, 98, SpringLayout.SOUTH, aperti_button);
		springLayout.putConstraint(SpringLayout.EAST, table, 0, SpringLayout.EAST, info_button);
		getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, cantiere_button);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lista_label);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -11, SpringLayout.NORTH, info_button);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -393, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);
		
		JButton aggiorna_button = new JButton("Aggiorna");
		springLayout.putConstraint(SpringLayout.NORTH, aggiorna_button, 11, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, aggiorna_button, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, aggiorna_button, 59, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, aggiorna_button, 137, SpringLayout.WEST, getContentPane());
		getContentPane().add(aggiorna_button);
		aggiorna_button.setFont(opzioni_font);
		aggiorna_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 ArrayList <Cantiere> listaCantieriTmp = new ArrayList<>();
				 listaCantieriTmp=controller.aggiornaCantieri();
				ListaCantieriModel cantieriModel= new ListaCantieriModel(listaCantieriTmp, nomiColonne);

				table.setModel(cantieriModel);

				
				
			}});
	
	}
	
	
	private String infoCantiere(int row)  {
		String idCantiere = table.getModel().getValueAt(row, 0).toString();
		return idCantiere;
	}
	
	private int infoCantiereNoClic(ArrayList<Cantiere> listaCantieri)  {
		Cantiere temp = listaCantieri.get(listaCantieri.size() - 1);
		int idCantiere = temp.getId()+1;
		return idCantiere;
	}
	
	public boolean check(int row) {
 		if (! vuoto( row, "ATTENZIONE! Selezionare cantiere."))
		    return false;
 		else return true;
	}
	
	public boolean vuoto(int row, String errore ) {
		if ( row==-1 )
		    return fallito(errore );
		  else
		    return true;
	}
	
	public boolean fallito(String messaggio)
	{
	  JOptionPane.showMessageDialog(null, messaggio); 
	  return false; 
	}
	
	
}
