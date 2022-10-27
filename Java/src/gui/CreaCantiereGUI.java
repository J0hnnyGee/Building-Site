package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.toedter.calendar.JDateChooser;

import entita.Cantiere;

public class CreaCantiereGUI extends JFrame {
	private JTextField citta_field;
	private JTextField via_field;
	private JTextField proprietario_field;
	private JTextField costo_field;
	private JDateChooser data_chooser;
	
	public CreaCantiereGUI(controller.Controller controller, int id) {
				//Grandezza finestra
				setSize(840,546);
				setResizable(false);

				//Dichiarazione Layout
				SpringLayout springLayout = new SpringLayout();
				getContentPane().setLayout(springLayout);
				
				//Font
				Font sezione_font = new Font("Serif", Font.PLAIN, 25);
				Font labels_font = new Font("Serif", Font.PLAIN, 18);
				Font submit_font = new Font("Serif", Font.PLAIN, 14);

				
				JLabel heading_label = new JLabel("Inserire i seguenti dati:");
				springLayout.putConstraint(SpringLayout.NORTH, heading_label, 40, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, heading_label, 36, SpringLayout.WEST, getContentPane());
				getContentPane().add(heading_label);
				heading_label.setFont(sezione_font);
				
				JLabel city_label = new JLabel("Citt\u00E0:");
				springLayout.putConstraint(SpringLayout.NORTH, city_label, 86, SpringLayout.SOUTH, heading_label);
				springLayout.putConstraint(SpringLayout.WEST, city_label, 52, SpringLayout.WEST, getContentPane());
				getContentPane().add(city_label);
				city_label.setFont(labels_font);
				
				citta_field = new JTextField();
				springLayout.putConstraint(SpringLayout.NORTH, citta_field, 1, SpringLayout.NORTH, city_label);
				springLayout.putConstraint(SpringLayout.WEST, citta_field, 59, SpringLayout.EAST, city_label);
				getContentPane().add(citta_field);
				citta_field.setColumns(10);
				citta_field.setFont(submit_font);
				
				JLabel via_label = new JLabel("Via:");
				springLayout.putConstraint(SpringLayout.NORTH, via_label, 61, SpringLayout.SOUTH, city_label);
				springLayout.putConstraint(SpringLayout.WEST, via_label, 0, SpringLayout.WEST, city_label);
				getContentPane().add(via_label);
				via_label.setFont(labels_font);
				
				via_field = new JTextField();
				springLayout.putConstraint(SpringLayout.WEST, via_field, 69, SpringLayout.EAST, via_label);
				springLayout.putConstraint(SpringLayout.EAST, citta_field, 0, SpringLayout.EAST, via_field);
				springLayout.putConstraint(SpringLayout.NORTH, via_field, 1, SpringLayout.NORTH, via_label);
				via_field.setColumns(10);
				getContentPane().add(via_field);
				via_field.setFont(submit_font);
				
				JLabel proprietario_label = new JLabel("Proprietario:");
				springLayout.putConstraint(SpringLayout.NORTH, proprietario_label, 64, SpringLayout.SOUTH, via_label);
				springLayout.putConstraint(SpringLayout.WEST, proprietario_label, 0, SpringLayout.WEST, city_label);
				getContentPane().add(proprietario_label);
				proprietario_label.setFont(labels_font);
				
				proprietario_field = new JTextField();
				springLayout.putConstraint(SpringLayout.NORTH, proprietario_field, 1, SpringLayout.NORTH, proprietario_label);
				springLayout.putConstraint(SpringLayout.WEST, proprietario_field, 8, SpringLayout.EAST, proprietario_label);
				proprietario_field.setColumns(10);
				getContentPane().add(proprietario_field);
				proprietario_field.setFont(submit_font);
				
				JLabel costo_label = new JLabel("Costo:");
				springLayout.putConstraint(SpringLayout.NORTH, costo_label, 0, SpringLayout.NORTH, city_label);
				getContentPane().add(costo_label);
				costo_label.setFont(labels_font);
				
				costo_field = new JTextField();
				springLayout.putConstraint(SpringLayout.NORTH, costo_field, 1, SpringLayout.NORTH, city_label);
				springLayout.putConstraint(SpringLayout.WEST, costo_field, 49, SpringLayout.EAST, costo_label);
				costo_field.setColumns(10);
				getContentPane().add(costo_field);
				costo_field.setFont(submit_font);
				
				JLabel stato_label = new JLabel("Stato:");
				springLayout.putConstraint(SpringLayout.EAST, via_field, -228, SpringLayout.WEST, stato_label);
				springLayout.putConstraint(SpringLayout.WEST, costo_label, 0, SpringLayout.WEST, stato_label);
				springLayout.putConstraint(SpringLayout.NORTH, stato_label, 8, SpringLayout.NORTH, via_label);
				getContentPane().add(stato_label);
				stato_label.setFont(labels_font);
				
				JComboBox stato_combo = new JComboBox();
				springLayout.putConstraint(SpringLayout.NORTH, stato_combo, 1, SpringLayout.NORTH, stato_label);
				springLayout.putConstraint(SpringLayout.WEST, stato_combo, 0, SpringLayout.WEST, costo_field);
				springLayout.putConstraint(SpringLayout.EAST, stato_combo, -40, SpringLayout.EAST, getContentPane());
				stato_combo.setModel(new DefaultComboBoxModel(new String[] {"Aperto", "Sospeso"}));
				getContentPane().add(stato_combo);
				stato_combo.setFont(submit_font);
				
				JLabel data_label = new JLabel("Data Inizio:");
				springLayout.putConstraint(SpringLayout.EAST, proprietario_field, -228, SpringLayout.WEST, data_label);
				springLayout.putConstraint(SpringLayout.WEST, stato_label, 0, SpringLayout.WEST, data_label);
				springLayout.putConstraint(SpringLayout.NORTH, data_label, 0, SpringLayout.NORTH, proprietario_label);
				springLayout.putConstraint(SpringLayout.EAST, data_label, -208, SpringLayout.EAST, getContentPane());
				getContentPane().add(data_label);
				data_label.setFont(labels_font);
				
				
				
				data_chooser = new JDateChooser();
				data_chooser.getCalendar();
				data_chooser.setMaxSelectableDate(new Date());
				
				springLayout.putConstraint(SpringLayout.WEST, data_chooser, 12, SpringLayout.EAST, data_label);
				springLayout.putConstraint(SpringLayout.SOUTH, data_chooser, 0, SpringLayout.SOUTH, proprietario_label);
				springLayout.putConstraint(SpringLayout.EAST, data_chooser, -40, SpringLayout.EAST, getContentPane());
				getContentPane().add(data_chooser);
				data_chooser.setFont(submit_font);
				
				JButton submit_button = new JButton("Crea Capocantiere >>");
				springLayout.putConstraint(SpringLayout.NORTH, submit_button, -97, SpringLayout.SOUTH, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, submit_button, -53, SpringLayout.SOUTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, submit_button, -40, SpringLayout.EAST, getContentPane());
				getContentPane().add(submit_button);
				submit_button.setFont(submit_font);
				submit_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(check()) {
						aggiungi(controller, id);
						}
					}});
				
				JLabel euro_label = new JLabel("\u20AC");
				springLayout.putConstraint(SpringLayout.WEST, euro_label, 790, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, costo_field, -6, SpringLayout.WEST, euro_label);
				springLayout.putConstraint(SpringLayout.NORTH, euro_label, 0, SpringLayout.NORTH, city_label);
				springLayout.putConstraint(SpringLayout.EAST, euro_label, -6, SpringLayout.EAST, getContentPane());
				getContentPane().add(euro_label);
				euro_label.setFont(labels_font);
				
	}
	
	private void aggiungi(controller.Controller controller, int id) {
		Cantiere c = new Cantiere();

		
		try {
				c.insCantiere(citta_field.getText(),via_field.getText(), proprietario_field.getText(), data_chooser.getDate(), 
						Double.parseDouble(costo_field.getText()));
				
		} catch(IllegalArgumentException e) {
			messaggioAttenzioneGenerico(e.getMessage());
			return;
		}
			
		try {
			controller.inserisciCantiere(c, id);
			JOptionPane.showMessageDialog(this, "Inserimento riuscito!", "", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			messaggioAttenzioneGenerico(e.getMessage());
		}
		 
	}
	
	public void messaggioAttenzioneGenerico(String messaggio) {
		JOptionPane.showMessageDialog(this,messaggio,"Attenzione",JOptionPane.WARNING_MESSAGE);
	}

	public boolean check() {
 		if (! vuoto( citta_field, "ATTENZIONE! Inserire Città."))
		    return false;
		  else
		  if (! indirizzo( via_field, "ATTENZIONE!  Controllare di aver inserito correttamente la via."))
		    return false;
		  else
		  if (! vuoto( proprietario_field, "ATTENZIONE! Inserire proprietario."))
			return false;
 		  if (! double_input( costo_field, "ATTENZIONE! Controllare di aver inserito correttamente il costo."))
			  return false;
 	  	  if (! data_vuoto( data_chooser, "ATTENZIONE! Inserire data di inizio."))
			return false;
		  else
		    return true;	
}
	
	
	public boolean vuoto(JTextField f, String errore ) {
		if ( f.getText().equals("") )
		    return fallito( f, errore );
		  else
		    return true;
	}
	
	public boolean indirizzo(JTextField f, String errore ) {
		String s=f.getText();
		if(!vuoto(f, "ATTENZIONE! Inserire indirizzo."))
			return false;
		if(Character.isDigit(s.charAt(s.length()-1))) {
			return true;
		}
		else
		return fallito( f, errore );
		}
	
	public boolean data_vuoto( JDateChooser f, String errore )
	{
		if(f.getDate()==null) {
			return fallitoData( f, "ATTENZIONE! Selezionare data di inizio." );
		}
		else return true;
	}
	
	
	public boolean double_input( JTextField f, String errore )
	{
		if(f.getText().equals("") )
	    return fallito( f, errore );
	  else {
	  try
	  {  
	    double i = Double.parseDouble(f.getText());
	    if ( i > 0 )
	      return true; 
	   }
	   catch(Exception e)
	   {
	   }
	   return fallito( f, errore );
	  }
	}
	
	
	public boolean fallitoData(JDateChooser f, String messaggio)
	{
		 JOptionPane.showMessageDialog(null, messaggio); 
		 f.requestFocus(); 
		 return false; 
	}
		
	public boolean fallito(JTextField f, String messaggio)
	{
	  JOptionPane.showMessageDialog(null, messaggio); 
	  f.requestFocus(); 
	  return false; 
	}
}
