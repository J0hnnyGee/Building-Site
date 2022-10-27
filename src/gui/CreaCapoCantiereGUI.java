package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import com.toedter.calendar.JDateChooser;

import entita.Dipendente;

public class CreaCapoCantiereGUI extends JFrame {

	private JPanel contentPane;
	private JTextField nome_field;
	private JTextField cognome_field;
	private JTextField cf_field;
	private JTextField mail_field;
	private JTextField telefono_field;
	private JTextField indirizzo_field;
	private JTextField stipendio_field;
	private JDateChooser date_chooser;
	
	public CreaCapoCantiereGUI(controller.Controller controller, int id) {
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

		
		JLabel heading_label = new JLabel("Insert the following datas:");
		springLayout.putConstraint(SpringLayout.NORTH, heading_label, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, heading_label, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(heading_label);
		heading_label.setFont(sezione_font);
		
		JLabel nome_label = new JLabel("Name:");
		springLayout.putConstraint(SpringLayout.NORTH, nome_label, 55, SpringLayout.SOUTH, heading_label);
		springLayout.putConstraint(SpringLayout.WEST, nome_label, 36, SpringLayout.WEST, getContentPane());
		getContentPane().add(nome_label);
		nome_label.setFont(labels_font);
		
		nome_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nome_field, 60, SpringLayout.SOUTH, heading_label);
		springLayout.putConstraint(SpringLayout.WEST, nome_field, 53, SpringLayout.EAST, nome_label);
		getContentPane().add(nome_field);
		nome_field.setColumns(10);
		nome_field.setFont(submit_font);
		
		JLabel cognome_label = new JLabel("Surname:");
		springLayout.putConstraint(SpringLayout.NORTH, cognome_label, 56, SpringLayout.SOUTH, nome_label);
		springLayout.putConstraint(SpringLayout.WEST, cognome_label, 0, SpringLayout.WEST, nome_label);
		getContentPane().add(cognome_label);
		cognome_label.setFont(labels_font);

		cognome_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, cognome_field, 1, SpringLayout.NORTH, cognome_label);
		springLayout.putConstraint(SpringLayout.WEST, cognome_field, 0, SpringLayout.WEST, nome_field);
		getContentPane().add(cognome_field);
		cognome_field.setColumns(10);
		cognome_field.setFont(submit_font);
		
		JLabel birth_label = new JLabel("Birth Date:");
		springLayout.putConstraint(SpringLayout.NORTH, birth_label, 56, SpringLayout.SOUTH, cognome_label);
		springLayout.putConstraint(SpringLayout.WEST, birth_label, 0, SpringLayout.WEST, nome_label);
		getContentPane().add(birth_label);
		birth_label.setFont(labels_font);
		
		date_chooser = new JDateChooser();
		date_chooser.getCalendar();
		date_chooser.setMaxSelectableDate(new Date());
		
		springLayout.putConstraint(SpringLayout.WEST, date_chooser, 5, SpringLayout.EAST, birth_label);
		springLayout.putConstraint(SpringLayout.SOUTH, date_chooser, 0, SpringLayout.SOUTH, birth_label);
		getContentPane().add(date_chooser);
		date_chooser.setFont(submit_font);
		
		JLabel cf_label = new JLabel("Tax Code:");
		springLayout.putConstraint(SpringLayout.NORTH, cf_label, 56, SpringLayout.SOUTH, birth_label);
		springLayout.putConstraint(SpringLayout.WEST, cf_label, 0, SpringLayout.WEST, nome_label);
		getContentPane().add(cf_label);
		cf_label.setFont(labels_font);
		
		cf_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, cf_field, 1, SpringLayout.NORTH, cf_label);
		springLayout.putConstraint(SpringLayout.WEST, cf_field, 0, SpringLayout.WEST, nome_field);
		getContentPane().add(cf_field);
		cf_field.setColumns(10);
		cf_field.setFont(submit_font);
		
		JLabel mail_label = new JLabel("E-mail:");
		springLayout.putConstraint(SpringLayout.EAST, nome_field, -252, SpringLayout.WEST, mail_label);
		springLayout.putConstraint(SpringLayout.SOUTH, mail_label, 0, SpringLayout.SOUTH, nome_label);
		springLayout.putConstraint(SpringLayout.EAST, mail_label, -244, SpringLayout.EAST, getContentPane());
		getContentPane().add(mail_label);
		mail_label.setFont(labels_font);
		
		mail_field = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, mail_field, 55, SpringLayout.EAST, mail_label);
		springLayout.putConstraint(SpringLayout.SOUTH, mail_field, 0, SpringLayout.SOUTH, nome_label);
		springLayout.putConstraint(SpringLayout.EAST, mail_field, 195, SpringLayout.EAST, mail_label);
		getContentPane().add(mail_field);
		mail_field.setColumns(10);
		mail_field.setFont(submit_font);
		
		JLabel telefono_label = new JLabel("Phone:");
		springLayout.putConstraint(SpringLayout.EAST, cognome_field, -252, SpringLayout.WEST, telefono_label);
		springLayout.putConstraint(SpringLayout.WEST, telefono_label, 0, SpringLayout.WEST, mail_label);
		springLayout.putConstraint(SpringLayout.SOUTH, telefono_label, 0, SpringLayout.SOUTH, cognome_label);
		getContentPane().add(telefono_label);
		telefono_label.setFont(labels_font);
		
		telefono_field = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, telefono_field, 37, SpringLayout.EAST, telefono_label);
		springLayout.putConstraint(SpringLayout.SOUTH, telefono_field, 0, SpringLayout.SOUTH, cognome_label);
		springLayout.putConstraint(SpringLayout.EAST, telefono_field, 0, SpringLayout.EAST, mail_field);
		getContentPane().add(telefono_field);
		telefono_field.setColumns(10);
		telefono_field.setFont(submit_font);
		
		
		JLabel indirizzo_label = new JLabel("Address:");
		springLayout.putConstraint(SpringLayout.EAST, date_chooser, -252, SpringLayout.WEST, indirizzo_label);
		springLayout.putConstraint(SpringLayout.WEST, indirizzo_label, 0, SpringLayout.WEST, mail_label);
		springLayout.putConstraint(SpringLayout.SOUTH, indirizzo_label, 0, SpringLayout.SOUTH, birth_label);
		getContentPane().add(indirizzo_label);
		indirizzo_label.setFont(labels_font);
		
		indirizzo_field = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, indirizzo_field, 39, SpringLayout.EAST, indirizzo_label);
		springLayout.putConstraint(SpringLayout.SOUTH, indirizzo_field, 0, SpringLayout.SOUTH, birth_label);
		springLayout.putConstraint(SpringLayout.EAST, indirizzo_field, 0, SpringLayout.EAST, mail_field);
		getContentPane().add(indirizzo_field);
		indirizzo_field.setColumns(10);
		indirizzo_field.setFont(submit_font);
		
		JLabel stipendio_label = new JLabel("Salary:");
		springLayout.putConstraint(SpringLayout.EAST, cf_field, -252, SpringLayout.WEST, stipendio_label);
		springLayout.putConstraint(SpringLayout.WEST, stipendio_label, 0, SpringLayout.WEST, mail_label);
		springLayout.putConstraint(SpringLayout.SOUTH, stipendio_label, 0, SpringLayout.SOUTH, cf_label);
		getContentPane().add(stipendio_label);
		stipendio_label.setFont(labels_font);
		
		stipendio_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, stipendio_field, 1, SpringLayout.NORTH, cf_label);
		springLayout.putConstraint(SpringLayout.WEST, stipendio_field, 0, SpringLayout.WEST, mail_field);
		springLayout.putConstraint(SpringLayout.EAST, stipendio_field, 0, SpringLayout.EAST, mail_field);
		getContentPane().add(stipendio_field);
		stipendio_field.setColumns(10);
		stipendio_field.setFont(submit_font);
		
		JLabel euro_label = new JLabel("\u20AC");
		springLayout.putConstraint(SpringLayout.NORTH, euro_label, 0, SpringLayout.NORTH, cf_label);
		springLayout.putConstraint(SpringLayout.WEST, euro_label, 6, SpringLayout.EAST, stipendio_field);
		springLayout.putConstraint(SpringLayout.EAST, euro_label, 765, SpringLayout.WEST, cf_label);
		getContentPane().add(euro_label);
		euro_label.setFont(labels_font);
		
		JButton submit_button = new JButton("Add Login Datas >>");
		springLayout.putConstraint(SpringLayout.SOUTH, submit_button, -59, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, submit_button, 0, SpringLayout.EAST, mail_field);
		getContentPane().add(submit_button);
		submit_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(check()) {

				aggiungi(id, controller);	
				}
			}});
	}
	
	//METODI
		private void aggiungi(int id, controller.Controller controller) {
			Dipendente d = new Dipendente();

			
			try {
					d.aggiungiDipendente(cf_field.getText(),nome_field.getText(), cognome_field.getText(), date_chooser.getDate(),  mail_field.getText(), telefono_field.getText(), indirizzo_field.getText(),
							 Double.parseDouble(stipendio_field.getText()),"Capocantiere", id);
					
			} catch(IllegalArgumentException e) {
				messaggioAttenzioneGenerico(e.getMessage());
				return;
			}
				
			try {
				controller.inserisciCapoCantiere(d);
				JOptionPane.showMessageDialog(this, "Success!", "", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				messaggioAttenzioneGenerico(e.getMessage());
			}
			 
		}
		
		public void messaggioAttenzioneGenerico(String messaggio) {
			JOptionPane.showMessageDialog(this,messaggio,"Warning",JOptionPane.WARNING_MESSAGE);
		}


		public boolean check() {
			 		if (! vuoto( nome_field, "WARNING! Insert the name."))
					    return false;
					  else
					  if (! vuoto( cognome_field, "WARNING! Insert the Surname."))
					    return false;
					  else
					  if (! cf( cf_field, "WARNING! Tac code must be of 16 characters."))
					    return false;
					  else
					  if (! mail( mail_field, "WARNING! Check the e-mail."))
					   return false;
					  else
				       if (! telefono( telefono_field, "WARNING! Phone number must be of 10 digits."))
					  return false;
					  else
				      if (! indirizzo( indirizzo_field, "WARNING! Check the address."))
					  return false;
					  else
					  if (! double_input( stipendio_field, "WARNING! Check the salary."))
					  return false;
					  else
					  if (! eta( date_chooser, "WARNING! The employee must be 16 or older."))
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
		
		public boolean cf(JTextField f, String errore ) {
			String s = f.getText();
			if(s.length()!=16) {
				return fallito( f, errore );
		}
			else if(!f.getText().matches("[a-zA-Z0-9]*")) {
				return fallito( f, "WARNING! The tax code must only contain letters and numbers." );

			}
			else
			    return true;
			
		}
		
		public boolean mail(JTextField f, String errore ) {
		String EMAIL_PATTERN = 
			    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			
			if(!vuoto(f, "WARNING! Inserire e-mail."))
			return false;
			if (!f.getText().matches(EMAIL_PATTERN)) {
				return fallito(f, errore);
			}
			else
				return true;
		}
		
		public boolean telefono(JTextField f, String errore ) {
			String s = f.getText();
			if(!vuoto(f, "WARNING! Insert the phone number."))
				return false;
			if(s.length()!=10) {
				return fallito( f, errore );
		}
			else
			    return true;
			
		}
		
		public boolean indirizzo(JTextField f, String errore ) {
			String s=f.getText();
			if(!vuoto(f, "WARNING! Insert the address."))
				return false;
			if(Character.isDigit(s.charAt(s.length()-1))) {
				return true;
			}
			else
			return fallito( f, errore );
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
		
		public boolean eta( JDateChooser f, String errore )
		{
			if(f.getDate()==null) {
				return fallitoData( f, "WARNING! Select the birth date." );
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dataString = dateFormat.format(f.getDate());
			LocalDate date = LocalDate.parse(dataString);
			LocalDate nascita = new LocalDate (date);
			LocalDate oggi = new LocalDate();
			Period period = new Period(nascita, oggi);
			int anni = period.getYears();
			if (anni>=16)
				return true;
			else
				 return fallitoData( f, errore );	
					
		}
		
		public boolean fallito(JTextField f, String messaggio)
		{
		  JOptionPane.showMessageDialog(null, messaggio); 
		  f.requestFocus(); 
		  return false; 
		}
		
		public boolean fallitoData(JDateChooser f, String messaggio)
		{
		  JOptionPane.showMessageDialog(null, messaggio); 
		  f.requestFocus(); 
		  return false; 
		}
		
	}
