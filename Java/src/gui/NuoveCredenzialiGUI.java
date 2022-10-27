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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import entita.Login;

public class NuoveCredenzialiGUI extends JFrame {
	private JTextField username_field;
	private JTextField password_field;

	public NuoveCredenzialiGUI(controller.Controller controller, String cf, ArrayList<Login> listaCredenziali) {
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
				
				JLabel heading = new JLabel("Nuovo Utente");
				springLayout.putConstraint(SpringLayout.NORTH, heading, 65, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, heading, -339, SpringLayout.EAST, getContentPane());
				getContentPane().add(heading);
				heading.setFont(sezione_font);
				
				JLabel username_label = new JLabel("Username:");
				getContentPane().add(username_label);
				username_label.setFont(labels_font);
				
				username_field = new JTextField();
				springLayout.putConstraint(SpringLayout.NORTH, username_field, 67, SpringLayout.SOUTH, heading);
				springLayout.putConstraint(SpringLayout.WEST, username_field, 22, SpringLayout.EAST, username_label);
				springLayout.putConstraint(SpringLayout.EAST, username_field, -298, SpringLayout.EAST, getContentPane());
				springLayout.putConstraint(SpringLayout.NORTH, username_label, -1, SpringLayout.NORTH, username_field);
				getContentPane().add(username_field);
				username_field.setColumns(10);
				username_field.setFont(submit_font);
				
				JLabel password_label = new JLabel("Password:");
				springLayout.putConstraint(SpringLayout.NORTH, password_label, 69, SpringLayout.SOUTH, username_label);
				springLayout.putConstraint(SpringLayout.WEST, username_label, 0, SpringLayout.WEST, password_label);
				getContentPane().add(password_label);
				password_label.setFont(labels_font);
				
				password_field = new JTextField();
				springLayout.putConstraint(SpringLayout.WEST, password_field, 362, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, password_label, -25, SpringLayout.WEST, password_field);
				springLayout.putConstraint(SpringLayout.EAST, password_field, -298, SpringLayout.EAST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, password_field, 0, SpringLayout.SOUTH, password_label);
				getContentPane().add(password_field);
				password_field.setColumns(10);
				password_field.setFont(submit_font);
				
				JButton submit_button = new JButton("Registra");
				springLayout.putConstraint(SpringLayout.NORTH, submit_button, 40, SpringLayout.SOUTH, password_field);
				springLayout.putConstraint(SpringLayout.SOUTH, submit_button, 73, SpringLayout.SOUTH, password_field);
				springLayout.putConstraint(SpringLayout.EAST, submit_button, -298, SpringLayout.EAST, getContentPane());
				getContentPane().add(submit_button);
				submit_button.setFont(submit_font);
				submit_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(check()) {

						aggiungiCredenziali(controller, cf, listaCredenziali);
						}
					}});
			}
	
	private void aggiungiCredenziali(controller.Controller controller, String cf,  ArrayList<Login> lista) {
		Login log = new Login();
		int flag=0;
		
		for(int i=0;i<lista.size(); i++) {
			String tmp=lista.get(i).getUsername();

			if(lista.get(i).getUsername().equals(username_field.getText())) {
					flag=1;			
					}
		}
		
		if (flag==0) {
			try {
				if(cf!=null) {
					log.registra(username_field.getText(), password_field.getText(), false, cf );
				}
				else {
					log.registra(username_field.getText(), password_field.getText(), true, cf );

				}
			} catch(IllegalArgumentException e) {
				messaggioAttenzioneGenerico(e.getMessage());
				return;
			}
			
			try {
				controller.inserisciCredenzialiCapo(log, cf);
				JOptionPane.showMessageDialog(this, "Inserimento riuscito!", "", JOptionPane.INFORMATION_MESSAGE);
				controller.chiudiRegistrazioneGUI();
			} catch (SQLException e) {
				messaggioAttenzioneGenerico(e.getMessage());
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "ATTENZIONE! L'username scelto è già in uso.", "", JOptionPane.INFORMATION_MESSAGE);

		}
	}
	
	public void messaggioAttenzioneGenerico(String messaggio) {
		JOptionPane.showMessageDialog(this,messaggio,"Attenzione",JOptionPane.WARNING_MESSAGE);
	}


	public boolean check() {
 		if (! vuoto( username_field, "ATTENZIONE! Inserire username."))
		    return false;
		  else
		  if (! vuoto( password_field, "ATTENZIONE! Inserire password."))
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
	
	public boolean fallito(JTextField f, String messaggio)
	{
	  JOptionPane.showMessageDialog(null, messaggio); 
	  f.requestFocus(); 
	  return false; 
	}
}
