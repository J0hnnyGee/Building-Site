package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import dao.DipendenteDAO;
import dao.ZonaDAO;
import entita.Dipendente;
import entita.Sensore;
import entita.Zona;

public class NuovoSensoreGUI extends JFrame {
	private JTextField posizione_field;
	private JComboBox tipo_combo;
	private JComboBox dipendente_combo;
	private JComboBox zona_combo;
	
	public NuovoSensoreGUI(controller.Controller controller, String id) {
				//Grandezza finestra
				setSize(840,568);
				setResizable(false);

				//Dichiarazione font
				Font sezione_font = new Font("Serif", Font.BOLD, 25);
				Font labels_font = new Font("Serif", Font.PLAIN, 18);
				Font submit_font = new Font("Serif", Font.PLAIN, 14);

				//Dichiarazione Layout
				SpringLayout springLayout = new SpringLayout();
				getContentPane().setLayout(springLayout);
				
				JLabel heading = new JLabel("Nuovo Sensore");
				springLayout.putConstraint(SpringLayout.NORTH, heading, 43, SpringLayout.NORTH, getContentPane());
				getContentPane().add(heading);
				heading.setFont(sezione_font);
				
				JLabel posizione_label = new JLabel("Posizione:");
				getContentPane().add(posizione_label);
				posizione_label.setFont(labels_font);
				
				posizione_field = new JTextField();
				springLayout.putConstraint(SpringLayout.WEST, posizione_field, 23, SpringLayout.EAST, posizione_label);
				springLayout.putConstraint(SpringLayout.EAST, posizione_field, -302, SpringLayout.EAST, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, heading, 0, SpringLayout.WEST, posizione_field);
				springLayout.putConstraint(SpringLayout.NORTH, posizione_field, 1, SpringLayout.NORTH, posizione_label);
				getContentPane().add(posizione_field);
				posizione_field.setColumns(10);
				posizione_field.setFont(submit_font);
				
				JLabel tipo_label = new JLabel("Tipo:");
				springLayout.putConstraint(SpringLayout.NORTH, tipo_label, 145, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, tipo_label, 228, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.NORTH, posizione_label, 60, SpringLayout.SOUTH, tipo_label);
				springLayout.putConstraint(SpringLayout.WEST, posizione_label, 0, SpringLayout.WEST, tipo_label);
				getContentPane().add(tipo_label);
				tipo_label.setFont(labels_font);
				
				tipo_combo = new JComboBox();
				springLayout.putConstraint(SpringLayout.EAST, tipo_label, -60, SpringLayout.WEST, tipo_combo);
				springLayout.putConstraint(SpringLayout.NORTH, tipo_combo, 68, SpringLayout.SOUTH, heading);
				springLayout.putConstraint(SpringLayout.WEST, tipo_combo, 326, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, tipo_combo, -61, SpringLayout.NORTH, posizione_field);
				springLayout.putConstraint(SpringLayout.EAST, tipo_combo, -300, SpringLayout.EAST, getContentPane());
				tipo_combo.setModel(new DefaultComboBoxModel(new String[] {"Rumore", "Gas"}));
				getContentPane().add(tipo_combo);
				tipo_combo.setFont(submit_font);
				
				JButton submit_button = new JButton("Aggiungi");
				springLayout.putConstraint(SpringLayout.NORTH, submit_button, 209, SpringLayout.SOUTH, posizione_field);
				springLayout.putConstraint(SpringLayout.SOUTH, submit_button, -32, SpringLayout.SOUTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, submit_button, -303, SpringLayout.EAST, getContentPane());
				getContentPane().add(submit_button);
				submit_button.setFont(submit_font);
				submit_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(check()) {
						aggiungi(controller);
						controller.chiudiNuovoSensoreGUI();
						}
					}});
				
				JLabel inserito_label = new JLabel("Inserito Da:");
				springLayout.putConstraint(SpringLayout.WEST, inserito_label, 0, SpringLayout.WEST, posizione_label);
				springLayout.putConstraint(SpringLayout.SOUTH, inserito_label, -192, SpringLayout.SOUTH, getContentPane());
				getContentPane().add(inserito_label);
				inserito_label.setFont(labels_font);
				
				 ArrayList <Dipendente> tmp=listaOperatori(id);
					ArrayList <String> cfs= new ArrayList <String>();
					for(int i=0; i<tmp.size();i++) {
						cfs.add(tmp.get(i).getCf());
					}
				
				dipendente_combo = new JComboBox(cfs.toArray());
				springLayout.putConstraint(SpringLayout.NORTH, dipendente_combo, 3, SpringLayout.NORTH, inserito_label);
				springLayout.putConstraint(SpringLayout.WEST, dipendente_combo, 14, SpringLayout.EAST, inserito_label);
				springLayout.putConstraint(SpringLayout.SOUTH, dipendente_combo, -189, SpringLayout.SOUTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, dipendente_combo, 0, SpringLayout.EAST, posizione_field);
				getContentPane().add(dipendente_combo);
				
				JLabel id_label = new JLabel("Id Zona:");
				springLayout.putConstraint(SpringLayout.NORTH, id_label, 57, SpringLayout.SOUTH, inserito_label);
				springLayout.putConstraint(SpringLayout.WEST, id_label, 0, SpringLayout.WEST, posizione_label);
				getContentPane().add(id_label);
				id_label.setFont(labels_font);
				
				 ArrayList <Zona> tmpZona = listaZone(id);
					ArrayList <Integer> id_zone= new ArrayList <Integer>();
					for(int i=0; i<tmpZona.size();i++) {
						id_zone.add(tmpZona.get(i).getId());
					}
					
				
				
				zona_combo = new JComboBox(id_zone.toArray());
				springLayout.putConstraint(SpringLayout.NORTH, zona_combo, 0, SpringLayout.NORTH, id_label);
				springLayout.putConstraint(SpringLayout.WEST, zona_combo, 0, SpringLayout.WEST, heading);
				springLayout.putConstraint(SpringLayout.SOUTH, zona_combo, 0, SpringLayout.SOUTH, id_label);
				springLayout.putConstraint(SpringLayout.EAST, zona_combo, 0, SpringLayout.EAST, posizione_field);
				getContentPane().add(zona_combo);
	}
	
	//METODI
		private void aggiungi(controller.Controller controller) {
			Sensore s = new Sensore();

			
			try {
					s.aggiungiSensoreIdAuto(tipo_combo.getSelectedItem().toString(), 0.0 , posizione_field.getText(),false,
							dipendente_combo.getSelectedItem().toString(), Integer.parseInt(zona_combo.getSelectedItem().toString()));
					
			} catch(IllegalArgumentException e) {
				messaggioAttenzioneGenerico(e.getMessage());
				return;
			}
				
			try {
				controller.inserisciSensore(s);
				JOptionPane.showMessageDialog(this, "Inserimento riuscito!", "", JOptionPane.INFORMATION_MESSAGE);
				controller.chiudiNuovoSensoreGUI();
			} catch (SQLException e) {
				messaggioAttenzioneGenerico(e.getMessage());
			}
			 
		}
		
		public void messaggioAttenzioneGenerico(String messaggio) {
			JOptionPane.showMessageDialog(this,messaggio,"Attenzione",JOptionPane.WARNING_MESSAGE);
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
		
		
		public boolean check() {
	 		if (! vuoto( posizione_field, "ATTENZIONE! Inserire Posizione."))
			    return false;
	 		else return true;
		}
		
		public boolean vuoto(JTextField f, String errore ) {
			if ( f.getText().equals("") )
			    return fallito( f, errore );
			  else
			    return true;
		}
		
		public boolean fallito(JTextField f, String messaggio)
		{
		  JOptionPane.showMessageDialog(null, messaggio); 
		  f.requestFocus(); 
		  return false; 
		}
		
	}
