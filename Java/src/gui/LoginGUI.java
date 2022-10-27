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

import dao.LoginDAO;
import entita.Login;

public class LoginGUI extends JFrame {
	private JTextField username_field;
	private JTextField password_field;

	public LoginGUI(controller.Controller controller, ArrayList<Login> listaCredenziali) {
		//Grandezza finestra
		setSize(840,546);
		setResizable(false);

		//Dichiarazione font
		Font titolo_font = new Font("Serif", Font.BOLD, 40);
		Font sottotitolo_font = new Font("Serif", Font.PLAIN, 20);
		Font sezione_font = new Font("Serif", Font.BOLD, 25);
		Font labels_font = new Font("Serif", Font.PLAIN, 18);
		Font submit_font = new Font("Serif", Font.PLAIN, 14);

		//Dichiarazione Layout
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
				
				
		JLabel login_title = new JLabel("Benvenuto in WorKing");
		springLayout.putConstraint(SpringLayout.NORTH, login_title, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, login_title, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, login_title, -445, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, login_title, -288, SpringLayout.EAST, getContentPane());
		getContentPane().add(login_title);
		login_title.setFont(titolo_font);
		
		JLabel login_subtitle = new JLabel("Perch\u00E8 nel nostro lavoro siamo i Re");
		springLayout.putConstraint(SpringLayout.NORTH, login_subtitle, 6, SpringLayout.SOUTH, login_title);
		springLayout.putConstraint(SpringLayout.WEST, login_subtitle, 10, SpringLayout.WEST, login_title);
		springLayout.putConstraint(SpringLayout.EAST, login_subtitle, -305, SpringLayout.EAST, getContentPane());
		getContentPane().add(login_subtitle);
		login_subtitle.setFont(sottotitolo_font);
		
		JLabel login_section = new JLabel("LOGIN");
		springLayout.putConstraint(SpringLayout.NORTH, login_section, 59, SpringLayout.SOUTH, login_subtitle);
		springLayout.putConstraint(SpringLayout.WEST, login_section, 362, SpringLayout.WEST, getContentPane());
		getContentPane().add(login_section);
		login_section.setFont(sezione_font);
		
		JLabel username_label = new JLabel("Username:");
		springLayout.putConstraint(SpringLayout.NORTH, username_label, 141, SpringLayout.SOUTH, login_subtitle);
		springLayout.putConstraint(SpringLayout.WEST, username_label, 237, SpringLayout.WEST, getContentPane());
		getContentPane().add(username_label);
		username_label.setFont(labels_font);
		
		JLabel password_label = new JLabel("Password:");
		springLayout.putConstraint(SpringLayout.NORTH, password_label, 36, SpringLayout.SOUTH, username_label);
		springLayout.putConstraint(SpringLayout.EAST, password_label, 0, SpringLayout.EAST, username_label);
		getContentPane().add(password_label);
		password_label.setFont(labels_font);
		
		username_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, username_field, 234, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, username_field, 338, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, username_field, 261, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, username_field, -355, SpringLayout.EAST, getContentPane());
		getContentPane().add(username_field);
		username_field.setColumns(10);
		
		password_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, password_field, 33, SpringLayout.SOUTH, username_field);
		springLayout.putConstraint(SpringLayout.WEST, password_field, 24, SpringLayout.EAST, password_label);
		springLayout.putConstraint(SpringLayout.SOUTH, password_field, 60, SpringLayout.SOUTH, username_field);
		springLayout.putConstraint(SpringLayout.EAST, password_field, -355, SpringLayout.EAST, getContentPane());
		getContentPane().add(password_field);
		password_field.setColumns(10);
		
		JButton submit = new JButton("Accedi");
		springLayout.putConstraint(SpringLayout.NORTH, submit, 41, SpringLayout.SOUTH, password_field);
		springLayout.putConstraint(SpringLayout.WEST, submit, -93, SpringLayout.EAST, username_field);
		springLayout.putConstraint(SpringLayout.EAST, submit, 0, SpringLayout.EAST, username_field);
		getContentPane().add(submit);
		submit.setFont(submit_font);
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confronto(controller, listaCredenziali);
				
			}});
	}
	
	//METODI
		private void confronto(controller.Controller controller, ArrayList<Login> lista) {
			Login input = new Login();
	
			try {
				input.inserite(username_field.getText(),password_field.getText());
				
		} catch(IllegalArgumentException e) {
			messaggioAttenzioneGenerico(e.getMessage());
			return;
		}
		int flag=0, save=0, id;
		String id_string = null;
		
		for(int i=0;i<lista.size(); i++) {
			String tmp=lista.get(i).getUsername();

			if(lista.get(i).getUsername().equals(username_field.getText())) {
				if(lista.get(i).getPassword().equals(password_field.getText())) {
					flag=1;
					save=i;
				}
			}
		}
		
		if(flag==1) {
			if(lista.get(save).isAdmin()==true) {
				controller.apriAdminGUI();				
			}
			else {
				try {
					String cf=lista.get(save).getCf_dipendente();
					LoginDAO log = new LoginDAO();
					id = log.getIdLog(cf);
					id_string=String.valueOf(id);  
				}
				catch (SQLException e){
					System.out.print(e.getLocalizedMessage());
					}

				controller.apriHomeCapoCantiereGUI(id_string);
			}
		}
		else
			JOptionPane.showMessageDialog(this, "Attenzione, Username o Password Sono errati!", "", JOptionPane.INFORMATION_MESSAGE);

		}
		
		public void messaggioAttenzioneGenerico(String messaggio) {
			JOptionPane.showMessageDialog(this,messaggio,"Attenzione",JOptionPane.WARNING_MESSAGE);
		}

		
}
