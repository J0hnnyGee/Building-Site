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

import dao.CantiereDAO;
import dao.DipendenteDAO;
import entita.Cantiere;
import entita.Dipendente;
import entita.Zona;

public class NuovaZonaGUI extends JFrame {
	private JTextField grandezza_field;
	private JComboBox tipo_combo;
	private JComboBox responsabile_combo;
	
	public NuovaZonaGUI(controller.Controller controller,  String id) {
		//Grandezza finestra
		setSize(840,546);
		setResizable(false);

		//Dichiarazione font
		Font sezione_font = new Font("Serif", Font.BOLD, 25);
		Font labels_font = new Font("Serif", Font.PLAIN, 18);
		Font submit_font = new Font("Serif", Font.PLAIN, 14);

		//Dichiarazione Layout
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel heading = new JLabel("New Zone");
		springLayout.putConstraint(SpringLayout.NORTH, heading, 65, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, heading, -339, SpringLayout.EAST, getContentPane());
		getContentPane().add(heading);
		heading.setFont(sezione_font);
		
		JLabel grandezza_label = new JLabel("Dimension:");
		getContentPane().add(grandezza_label);
		grandezza_label.setFont(labels_font);
		
		grandezza_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, grandezza_field, 67, SpringLayout.SOUTH, heading);
		springLayout.putConstraint(SpringLayout.NORTH, grandezza_label, -1, SpringLayout.NORTH, grandezza_field);
		springLayout.putConstraint(SpringLayout.WEST, grandezza_field, 357, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, grandezza_field, -298, SpringLayout.EAST, getContentPane());
		getContentPane().add(grandezza_field);
		grandezza_field.setColumns(10);
		grandezza_field.setFont(submit_font);
		
		JLabel tipo_label = new JLabel("Tyoe:");
		springLayout.putConstraint(SpringLayout.WEST, grandezza_label, 0, SpringLayout.WEST, tipo_label);
		getContentPane().add(tipo_label);
		tipo_label.setFont(labels_font);
		
		tipo_combo = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, tipo_label, -1, SpringLayout.NORTH, tipo_combo);
		springLayout.putConstraint(SpringLayout.NORTH, tipo_combo, 66, SpringLayout.SOUTH, grandezza_field);
		springLayout.putConstraint(SpringLayout.WEST, tipo_combo, 0, SpringLayout.WEST, heading);
		springLayout.putConstraint(SpringLayout.EAST, tipo_combo, 0, SpringLayout.EAST, grandezza_field);
		tipo_combo.setModel(new DefaultComboBoxModel(new String[] {"Verde", "Residenziale", "Servizi Pubblici"}));
		getContentPane().add(tipo_combo);
		tipo_combo.setFont(submit_font);
		
		JButton submit_button = new JButton("Add");
		springLayout.putConstraint(SpringLayout.NORTH, submit_button, 437, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, submit_button, -37, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, submit_button, 0, SpringLayout.EAST, grandezza_field);
		getContentPane().add(submit_button);
		submit_button.setFont(submit_font);
		submit_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(check()) {

				aggiungi(id, controller);
				controller.chiudiNuovaZonaGUI();
				}
			}});
	
		
		JLabel responsabile_label = new JLabel("Manager:");
		springLayout.putConstraint(SpringLayout.WEST, tipo_label, 0, SpringLayout.WEST, responsabile_label);
		getContentPane().add(responsabile_label);
		responsabile_label.setFont(labels_font);
		
		 ArrayList <Dipendente> tmp=listaDipendenti(id);
		ArrayList <String> cfs= new ArrayList <String>();
		for(int i=0; i<tmp.size();i++) {
			cfs.add(tmp.get(i).getCf());
		}

		responsabile_combo = new JComboBox(cfs.toArray());
		springLayout.putConstraint(SpringLayout.NORTH, responsabile_combo, 70, SpringLayout.SOUTH, tipo_combo);
		springLayout.putConstraint(SpringLayout.SOUTH, responsabile_combo, -62, SpringLayout.NORTH, submit_button);
		springLayout.putConstraint(SpringLayout.SOUTH, responsabile_label, 0, SpringLayout.SOUTH, responsabile_combo);
		springLayout.putConstraint(SpringLayout.EAST, responsabile_label, -14, SpringLayout.WEST, responsabile_combo);
		springLayout.putConstraint(SpringLayout.WEST, responsabile_combo, 0, SpringLayout.WEST, heading);
		springLayout.putConstraint(SpringLayout.EAST, responsabile_combo, 0, SpringLayout.EAST, grandezza_field);
		getContentPane().add(responsabile_combo);
		
		JLabel mq_label = new JLabel("sqmtr");
		springLayout.putConstraint(SpringLayout.WEST, mq_label, 6, SpringLayout.EAST, grandezza_field);
		springLayout.putConstraint(SpringLayout.SOUTH, mq_label, 0, SpringLayout.SOUTH, grandezza_label);
		getContentPane().add(mq_label);
		mq_label.setFont(labels_font);

	}

	//METODI
		private void aggiungi(String id, controller.Controller controller) {
			Zona d = new Zona();

			
			try {
					d.aggiungiZonaIdAuto((Integer.parseInt(grandezza_field.getText())),tipo_combo.getSelectedItem().toString(), Integer.parseInt(id),
							responsabile_combo.getSelectedItem().toString());
					
			} catch(IllegalArgumentException e) {
				messaggioAttenzioneGenerico(e.getMessage());
				return;
			}
				
			try {
				controller.inserisciZona(d);
				JOptionPane.showMessageDialog(this, "Success!", "", JOptionPane.INFORMATION_MESSAGE);
				controller.chiudiNuovaZonaGUI();
			} catch (SQLException e) {
				messaggioAttenzioneGenerico(e.getMessage());
			}
			 
		}
		
		public void messaggioAttenzioneGenerico(String messaggio) {
			JOptionPane.showMessageDialog(this,messaggio,"Warning",JOptionPane.WARNING_MESSAGE);
		}
		
		private ArrayList <Dipendente>  listaDipendenti(String id) {
			ArrayList <Dipendente> listaDipendenti = new ArrayList<>();
			try {
				DipendenteDAO dip = new DipendenteDAO();
				listaDipendenti = dip.getDipendenti(id);
			}
			catch (SQLException e){
				System.out.print(e.getLocalizedMessage());
				}
			return listaDipendenti;
		}
	
		

		public boolean check() {
	 		if (! intero( grandezza_field, "WARNING! Check the dimension."))
			    return false;
	 		else return true;
		}
		
		public boolean intero(JTextField f, String errore ) {
				  try {  
				    Integer.parseInt(f.getText());  
				    return true;
				  } catch(NumberFormatException e){  
				    return fallito(f, errore);  
				  }  
				}
		
		
		public boolean fallito(JTextField f, String messaggio)
		{
		  JOptionPane.showMessageDialog(null, messaggio); 
		  f.requestFocus(); 
		  return false; 
		}
}
