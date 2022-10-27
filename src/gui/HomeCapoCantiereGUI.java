package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import dao.CantiereDAO;
import dao.DipendenteDAO;
import dao.ZonaDAO;
import entita.Cantiere;
import entita.Dipendente;
import entita.Zona;
import tableModels.ListaCantieriModel;

public class HomeCapoCantiereGUI extends JFrame {

	public HomeCapoCantiereGUI(controller.Controller controller, String id) {
				//Grandezza finestra
				setSize(840,546);
				setResizable(false);

				//Dichiarazione Layout
				SpringLayout springLayout = new SpringLayout();
				getContentPane().setLayout(springLayout);
				
				//Font
				Font heading_font = new Font("Serif", Font.PLAIN, 24);
				Font info_font = new Font("Serif", Font.PLAIN, 13);
				Font opzioni_font = new Font("Serif", Font.PLAIN, 18);
				Font opzioni2_font = new Font("Serif", Font.PLAIN, 16);
				Font labels_font = new Font("Serif", Font.PLAIN, 18);

				JLabel heading = new JLabel("Building Site:");
				springLayout.putConstraint(SpringLayout.NORTH, heading, 10, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, heading, 10, SpringLayout.WEST, getContentPane());
				getContentPane().add(heading);
				heading.setFont(heading_font);
				
				JButton aggiungiDipendente_button = new JButton("Add Empoyee");
				springLayout.putConstraint(SpringLayout.NORTH, aggiungiDipendente_button, 48, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, aggiungiDipendente_button, 440, SpringLayout.WEST, getContentPane());
				getContentPane().add(aggiungiDipendente_button);
				aggiungiDipendente_button.setFont(opzioni_font);
				
				
				JButton visualizzaDipendente_button = new JButton("Show Employees");
				springLayout.putConstraint(SpringLayout.SOUTH, aggiungiDipendente_button, -6, SpringLayout.NORTH, visualizzaDipendente_button);
				springLayout.putConstraint(SpringLayout.EAST, aggiungiDipendente_button, 0, SpringLayout.EAST, visualizzaDipendente_button);
				springLayout.putConstraint(SpringLayout.NORTH, visualizzaDipendente_button, 115, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, visualizzaDipendente_button, 440, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, visualizzaDipendente_button, -331, SpringLayout.SOUTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, visualizzaDipendente_button, -10, SpringLayout.EAST, getContentPane());
				getContentPane().add(visualizzaDipendente_button);
				visualizzaDipendente_button.setFont(opzioni_font);
				visualizzaDipendente_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.apriVisualizzaDipendentiGUI(id);
						
					}});
				
				JButton aggiungiZona_button = new JButton("Add Zone");
				springLayout.putConstraint(SpringLayout.NORTH, aggiungiZona_button, 182, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, aggiungiZona_button, 440, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, aggiungiZona_button, -10, SpringLayout.EAST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, aggiungiZona_button, 67, SpringLayout.SOUTH, visualizzaDipendente_button);
				getContentPane().add(aggiungiZona_button);
				aggiungiZona_button.setFont(opzioni_font);
				
				
				JButton visualizzaZona_button = new JButton("Show Zones");
				springLayout.putConstraint(SpringLayout.NORTH, visualizzaZona_button, 6, SpringLayout.SOUTH, aggiungiZona_button);
				springLayout.putConstraint(SpringLayout.WEST, visualizzaZona_button, 440, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, visualizzaZona_button, 67, SpringLayout.SOUTH, aggiungiZona_button);
				springLayout.putConstraint(SpringLayout.EAST, visualizzaZona_button, -10, SpringLayout.EAST, getContentPane());
				getContentPane().add(visualizzaZona_button);
				visualizzaZona_button.setFont(opzioni_font);
				visualizzaZona_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.apriVisualizzaZoneGUI(id);
						
					}});
				
				JButton aggiungiSensore_button = new JButton("Add Sensor");
				springLayout.putConstraint(SpringLayout.NORTH, aggiungiSensore_button, 6, SpringLayout.SOUTH, visualizzaZona_button);
				springLayout.putConstraint(SpringLayout.WEST, aggiungiSensore_button, 440, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, aggiungiSensore_button, 67, SpringLayout.SOUTH, visualizzaZona_button);
				springLayout.putConstraint(SpringLayout.EAST, aggiungiSensore_button, -10, SpringLayout.EAST, getContentPane());
				getContentPane().add(aggiungiSensore_button);
				aggiungiSensore_button.setFont(opzioni_font);
				
				
				JButton azioniSensore_button = new JButton("Show/Edit Sensors");
				springLayout.putConstraint(SpringLayout.NORTH, azioniSensore_button, 6, SpringLayout.SOUTH, aggiungiSensore_button);
				springLayout.putConstraint(SpringLayout.WEST, azioniSensore_button, 440, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, azioniSensore_button, 67, SpringLayout.SOUTH, aggiungiSensore_button);
				springLayout.putConstraint(SpringLayout.EAST, azioniSensore_button, -10, SpringLayout.EAST, getContentPane());
				getContentPane().add(azioniSensore_button);
				azioniSensore_button.setFont(opzioni_font);
				azioniSensore_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.apriVisualizzaSensoriGUI(id);
						
					}});
				Cantiere tmp=controller.aggiornaCantiere(id);;

				JButton chiudiCantiere_button = new JButton("<html><center>Close<br/>Site</center></html>");
				springLayout.putConstraint(SpringLayout.NORTH, chiudiCantiere_button, 408, SpringLayout.SOUTH, heading);
				springLayout.putConstraint(SpringLayout.SOUTH, chiudiCantiere_button, -10, SpringLayout.SOUTH, getContentPane());
				chiudiCantiere_button.setBackground(UIManager.getColor("Button.focus"));
				springLayout.putConstraint(SpringLayout.WEST, chiudiCantiere_button, 9, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, chiudiCantiere_button, 133, SpringLayout.WEST, getContentPane());
				getContentPane().add(chiudiCantiere_button);
				chiudiCantiere_button.setFont(opzioni2_font);
				
				
				JButton sospendiCantiere_button = new JButton("<html><center>Suspend<br/>Site</center></html>");
				springLayout.putConstraint(SpringLayout.WEST, sospendiCantiere_button, 6, SpringLayout.EAST, chiudiCantiere_button);
				springLayout.putConstraint(SpringLayout.SOUTH, sospendiCantiere_button, -10, SpringLayout.SOUTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, sospendiCantiere_button, 130, SpringLayout.EAST, chiudiCantiere_button);
				getContentPane().add(sospendiCantiere_button);
				sospendiCantiere_button.setFont(opzioni2_font);
				
				

				JLabel IdCantiere_label = new JLabel(id);
				springLayout.putConstraint(SpringLayout.NORTH, sospendiCantiere_button, 408, SpringLayout.SOUTH, IdCantiere_label);
				springLayout.putConstraint(SpringLayout.WEST, IdCantiere_label, 7, SpringLayout.EAST, heading);
				springLayout.putConstraint(SpringLayout.SOUTH, IdCantiere_label, 0, SpringLayout.SOUTH, heading);
				getContentPane().add(IdCantiere_label);
				IdCantiere_label.setFont(heading_font);
				
				JLabel citta_label = new JLabel("City:");
				springLayout.putConstraint(SpringLayout.NORTH, citta_label, 18, SpringLayout.NORTH, aggiungiDipendente_button);
				springLayout.putConstraint(SpringLayout.WEST, citta_label, 0, SpringLayout.WEST, heading);
				getContentPane().add(citta_label);
				citta_label.setFont(labels_font);
				
				JLabel citta_var = new JLabel(tmp.getCitta());
				springLayout.putConstraint(SpringLayout.NORTH, citta_var, 18, SpringLayout.NORTH, aggiungiDipendente_button);
				getContentPane().add(citta_var);
				citta_var.setFont(labels_font);
				
				JLabel via_label = new JLabel("Address:");
				springLayout.putConstraint(SpringLayout.NORTH, via_label, 18, SpringLayout.NORTH, visualizzaDipendente_button);
				springLayout.putConstraint(SpringLayout.WEST, via_label, 0, SpringLayout.WEST, heading);
				getContentPane().add(via_label);
				via_label.setFont(labels_font);

				JLabel via_var = new JLabel(tmp.getVia());
				springLayout.putConstraint(SpringLayout.EAST, citta_var, 0, SpringLayout.EAST, via_var);
				springLayout.putConstraint(SpringLayout.NORTH, via_var, 18, SpringLayout.NORTH, visualizzaDipendente_button);
				getContentPane().add(via_var);
				via_var.setFont(labels_font);
				
				JLabel proprietario_label = new JLabel("Owner:");
				springLayout.putConstraint(SpringLayout.NORTH, proprietario_label, 18, SpringLayout.NORTH, aggiungiZona_button);
				springLayout.putConstraint(SpringLayout.WEST, proprietario_label, 0, SpringLayout.WEST, heading);
				getContentPane().add(proprietario_label);
				proprietario_label.setFont(labels_font);

				JLabel proprietario_var = new JLabel(tmp.getProprietario());
				springLayout.putConstraint(SpringLayout.EAST, via_var, 0, SpringLayout.EAST, proprietario_var);
				springLayout.putConstraint(SpringLayout.NORTH, proprietario_var, 18, SpringLayout.NORTH, aggiungiZona_button);
				springLayout.putConstraint(SpringLayout.WEST, proprietario_var, 19, SpringLayout.EAST, proprietario_label);
				getContentPane().add(proprietario_var);
				proprietario_var.setFont(labels_font);
				
				JLabel dataInizio_label = new JLabel("Starting Date:");
				springLayout.putConstraint(SpringLayout.NORTH, dataInizio_label, 18, SpringLayout.NORTH, visualizzaZona_button);
				springLayout.putConstraint(SpringLayout.WEST, dataInizio_label, 0, SpringLayout.WEST, heading);
				getContentPane().add(dataInizio_label);
				dataInizio_label.setFont(labels_font);
				
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				
				JLabel dataInizio_var = new JLabel(dateFormat.format(tmp.getData_inizio()));
				springLayout.putConstraint(SpringLayout.NORTH, dataInizio_var, 18, SpringLayout.NORTH, visualizzaZona_button);
				springLayout.putConstraint(SpringLayout.EAST, dataInizio_var, 0, SpringLayout.EAST, proprietario_var);
				getContentPane().add(dataInizio_var);
				dataInizio_var.setFont(labels_font);
				
				JLabel costo_label = new JLabel("Cost:");
				springLayout.putConstraint(SpringLayout.NORTH, costo_label, 18, SpringLayout.NORTH, aggiungiSensore_button);
				springLayout.putConstraint(SpringLayout.WEST, costo_label, 0, SpringLayout.WEST, heading);
				getContentPane().add(costo_label);
				costo_label.setFont(labels_font);

				JLabel costo_var = new JLabel(String.valueOf(tmp.getCosto()));
				springLayout.putConstraint(SpringLayout.NORTH, costo_var, 18, SpringLayout.NORTH, aggiungiSensore_button);
				springLayout.putConstraint(SpringLayout.WEST, costo_var, 0, SpringLayout.WEST, proprietario_var);
				getContentPane().add(costo_var);
				costo_var.setFont(labels_font);
				
				JLabel stato_label = new JLabel("State:");
				springLayout.putConstraint(SpringLayout.NORTH, stato_label, 18, SpringLayout.NORTH, azioniSensore_button);
				springLayout.putConstraint(SpringLayout.WEST, stato_label, 0, SpringLayout.WEST, heading);
				getContentPane().add(stato_label);
				stato_label.setFont(labels_font);

				JLabel stato_var = new JLabel(tmp.getStato());
				springLayout.putConstraint(SpringLayout.NORTH, stato_var, 18, SpringLayout.NORTH, azioniSensore_button);
				springLayout.putConstraint(SpringLayout.WEST, stato_var, 0, SpringLayout.WEST, proprietario_var);
				getContentPane().add(stato_var);
				stato_var.setFont(labels_font);
				
				JLabel dataFine_label = new JLabel("End Date:");
				springLayout.putConstraint(SpringLayout.NORTH, dataFine_label, 18, SpringLayout.NORTH, visualizzaZona_button);
				springLayout.putConstraint(SpringLayout.EAST, dataFine_label, -517, SpringLayout.EAST, getContentPane());
				getContentPane().add(dataFine_label);
				dataFine_label.setFont(labels_font);

				JLabel dataFine_var = new JLabel(finitoCheck(tmp));
				springLayout.putConstraint(SpringLayout.NORTH, dataFine_var, 18, SpringLayout.NORTH, visualizzaZona_button);
				springLayout.putConstraint(SpringLayout.WEST, dataFine_var, 13, SpringLayout.EAST, dataFine_label);
				getContentPane().add(dataFine_var);
				dataFine_var.setFont(labels_font);
				
				JButton aggiorna_button = new JButton("Refresh");
				springLayout.putConstraint(SpringLayout.NORTH, aggiorna_button, 0, SpringLayout.NORTH, heading);
				springLayout.putConstraint(SpringLayout.WEST, aggiorna_button, 6, SpringLayout.EAST, IdCantiere_label);
				springLayout.putConstraint(SpringLayout.SOUTH, aggiorna_button, 42, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, aggiorna_button, 106, SpringLayout.EAST, IdCantiere_label);
				getContentPane().add(aggiorna_button);
				aggiorna_button.setFont(opzioni2_font);
				aggiorna_button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						 Cantiere CantiereTmp = new Cantiere();
						 CantiereTmp=controller.aggiornaCantiere(id);
						stato_var.setText(CantiereTmp.getStato());
						
						
					}});
				JButton desospensione_button = new JButton("<html><center>Cancel<br/>Suspension</center></html>");
				springLayout.putConstraint(SpringLayout.NORTH, desospensione_button, 0, SpringLayout.NORTH, chiudiCantiere_button);
				springLayout.putConstraint(SpringLayout.WEST, desospensione_button, 6, SpringLayout.EAST, sospendiCantiere_button);
				springLayout.putConstraint(SpringLayout.SOUTH, desospensione_button, -10, SpringLayout.SOUTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, desospensione_button, 130, SpringLayout.EAST, sospendiCantiere_button);
				getContentPane().add(desospensione_button);
				desospensione_button.setFont(opzioni2_font);
				
				chiudiCantiere_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(!stato_var.getText().equals("Finito")) {
							controller.apriConfermaChiusuraGUI(id);
							
						}
						else
							JOptionPane.showMessageDialog(null, "WARNING! The Site is already closed."); 
					}});
				
				sospendiCantiere_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(stato_var.getText().equals("Finito")) {
							JOptionPane.showMessageDialog(null, "WARNING! The site is closed."); 
						}
						else if(stato_var.getText().equals("Sospeso")) {
							JOptionPane.showMessageDialog(null, "WARNING! The site is already suspended."); 
						}
						else
						controller.apriConfermaSospensioneGUI(id);
					}});
				
				
				desospensione_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(stato_var.getText().equals("Finito")) {
							JOptionPane.showMessageDialog(null, "WARNING! The site is closed."); 
						}
						else if(stato_var.getText().equals("Aperto")) {
							JOptionPane.showMessageDialog(null, "WARNING! The site is closed."); 
						}
						else
						controller.apriConfermaDeSospensioneGUI(id);
					}});
	
	
	aggiungiDipendente_button.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!stato_var.getText().equals("Finito")) {
				controller.apriAggiungiDipendenteGUI(id);
				
			}
			else
				JOptionPane.showMessageDialog(null, "WARNING! Is not possible to add employees: the site is closed."); 
			
		}});
	
	aggiungiZona_button.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!stato_var.getText().equals("Finito")) {
			controller.apriNuovaZonaGUI(id);
			}
			else
				JOptionPane.showMessageDialog(null, "WARNING! Is not possible to add a zone: the site is closed."); 
		}});
	
	aggiungiSensore_button.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!stato_var.getText().equals("Finito")) {
				 ArrayList <Zona> tmpZona = listaZone(id);
					ArrayList <Integer> id_zone= new ArrayList <Integer>();
					for(int i=0; i<tmpZona.size();i++) {
						id_zone.add(tmpZona.get(i).getId());
					}
					ArrayList <Dipendente> tmp=listaOperatori(id);
					ArrayList <String> cfs= new ArrayList <String>();
					for(int i=0; i<tmp.size();i++) {
						cfs.add(tmp.get(i).getCf());
					}
					
				if(id_zone.isEmpty()) {
					messaggioAttenzioneGenerico("WARNING, There are no zone with less than two sensors");
				}
				else if(cfs.isEmpty()) {
					messaggioAttenzioneGenerico("WARNING, there are no available operators");
				}
				else
					controller.apriNuovoSensoreGUI(id);
			}
			else
				JOptionPane.showMessageDialog(null, "WARNING! Is not possible to add sensors: the site is closed."); 

		}});
	
	}
	
	//METODI
			
			
			private String finitoCheck(Cantiere tmp) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");;
				if (tmp.getData_fine()==null) {
					return " ";
				}
				else
					return dateFormat.format(tmp.getData_fine());
			}
			
			private ArrayList <Dipendente>  listaOperatori(String id) {
				ArrayList <Dipendente> listaDipendenti = new ArrayList<>();
				try {
					DipendenteDAO dip = new DipendenteDAO();
					listaDipendenti = dip.getOperatori(id);
				}
				catch (SQLException e){
					System.out.print(e.getLocalizedMessage());
					}
				return listaDipendenti;
			}
			
			private ArrayList <Zona>  listaZone(String id) {
				ArrayList <Zona> listaZone = new ArrayList<>();
				try {
					ZonaDAO zona = new ZonaDAO();
					listaZone = zona.getZoneSensori(id);
				}
				catch (SQLException e){
					System.out.print(e.getLocalizedMessage());
					}
				return listaZone;
			}
			
			public void messaggioAttenzioneGenerico(String messaggio) {
				JOptionPane.showMessageDialog(this,messaggio,"WARNING",JOptionPane.WARNING_MESSAGE);
			}
}
