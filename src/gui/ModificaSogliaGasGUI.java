package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import entita.Sensore;

public class ModificaSogliaGasGUI extends JFrame {

	private JTextField valore_field;

	public ModificaSogliaGasGUI(controller.Controller controller, String tipo, int id) {
		//Grandezza finestra
		setSize(412,247);
		setResizable(false);

		//Dichiarazione font
		Font sezione_font = new Font("Serif", Font.PLAIN, 25);
		Font labels_font = new Font("Serif", Font.PLAIN, 18);
		Font submit_font = new Font("Serif", Font.PLAIN, 14);

		//Dichiarazione Layout
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel sensore = new JLabel("Edit Gas threshold:");
		springLayout.putConstraint(SpringLayout.NORTH, sensore, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, sensore, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(sensore);
		sensore.setFont(sezione_font);
		
		JLabel valore_label = new JLabel("Value:");
		springLayout.putConstraint(SpringLayout.NORTH, valore_label, 40, SpringLayout.SOUTH, sensore);
		springLayout.putConstraint(SpringLayout.WEST, valore_label, 64, SpringLayout.WEST, getContentPane());
		getContentPane().add(valore_label);
		valore_label.setFont(labels_font);
		
		valore_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, valore_field, 5, SpringLayout.NORTH, valore_label);
		springLayout.putConstraint(SpringLayout.WEST, valore_field, 6, SpringLayout.EAST, valore_label);
		springLayout.putConstraint(SpringLayout.EAST, valore_field, -103, SpringLayout.EAST, getContentPane());
		getContentPane().add(valore_field);
		valore_field.setColumns(10);
		
		JLabel percentuale = new JLabel("%");
		springLayout.putConstraint(SpringLayout.NORTH, percentuale, 84, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, percentuale, 6, SpringLayout.EAST, valore_field);
		springLayout.putConstraint(SpringLayout.SOUTH, percentuale, 0, SpringLayout.SOUTH, valore_field);
		getContentPane().add(percentuale);
		percentuale.setFont(labels_font);
		
		JButton submit_button = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.NORTH, submit_button, 162, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, submit_button, 276, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, submit_button, -22, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, submit_button, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(submit_button);
		submit_button.setFont(submit_font);
		submit_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(check()) {

				soglia(controller, tipo, id);
				controller.chiudiModificaSogliaGasGUI();
				}
			}});
	}

	//METODI
			private void soglia(controller.Controller controller, String tipo, int id) {
				Sensore s = new Sensore();

				
				try {
						s.soglia(Double.parseDouble(valore_field.getText()));
						
				} catch(IllegalArgumentException e) {
					messaggioAttenzioneGenerico(e.getMessage());
					return;
				}
					
				try {
					controller.SogliaSensore(s, tipo, id);
					JOptionPane.showMessageDialog(this, "Success!", "", JOptionPane.INFORMATION_MESSAGE);
					controller.chiudiModificaSogliaGasGUI();
				} catch (SQLException e) {
					messaggioAttenzioneGenerico(e.getMessage());
				}
				 
			}
			
			public void messaggioAttenzioneGenerico(String messaggio) {
				JOptionPane.showMessageDialog(this,messaggio,"Warning",JOptionPane.WARNING_MESSAGE);
			}
		
			public boolean check() {
		 		if (! input( valore_field, "WARNING! Check the value."))
				    return false;
		 		else return true;
			}
			
			public boolean input(JTextField f, String errore ) {
					  try {  
					    Double.parseDouble(f.getText());  
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
