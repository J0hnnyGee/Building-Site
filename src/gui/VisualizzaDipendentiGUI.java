package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import entita.Dipendente;
import tableModels.DipendenteModel;

public class VisualizzaDipendentiGUI extends JFrame {
	private String[] nomiColonne = {"Tax Code", "Name", "Surname", "Birth Date", "Email", "Phone Number", "Address", "Salary", "Role", "Site"};
	private ArrayList <Dipendente> listaDipendenti = new ArrayList<>();
	private JTable table;

	public VisualizzaDipendentiGUI(controller.Controller controller,ArrayList<Dipendente> listaDipendenti, String id) {
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
				
				JLabel dipendenti_label = new JLabel("Employees List");
				springLayout.putConstraint(SpringLayout.NORTH, dipendenti_label, 10, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, dipendenti_label, 10, SpringLayout.WEST, getContentPane());
				getContentPane().add(dipendenti_label);
				dipendenti_label.setFont(sezione_font);
				
				
				
				JButton indietro_button = new JButton("Back");
				springLayout.putConstraint(SpringLayout.NORTH, indietro_button, 339, SpringLayout.NORTH, getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, indietro_button, -119, SpringLayout.EAST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, indietro_button, -141, SpringLayout.SOUTH, getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, indietro_button, -10, SpringLayout.EAST, getContentPane());
				getContentPane().add(indietro_button);
				indietro_button.setFont(submit_font);
				
				DipendenteModel dipendenteModel = new DipendenteModel(listaDipendenti, nomiColonne);
				table = new JTable(dipendenteModel);
				springLayout.putConstraint(SpringLayout.NORTH, table, 19, SpringLayout.SOUTH, dipendenti_label);
				springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, table, -59, SpringLayout.NORTH, indietro_button);
				springLayout.putConstraint(SpringLayout.EAST, table, 0, SpringLayout.EAST, indietro_button);
				getContentPane().add(table);
				
	
				JScrollPane scrollPane = new JScrollPane(table);
				springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, dipendenti_label);
				springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, dipendenti_label);
				springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 267, SpringLayout.SOUTH, dipendenti_label);
				springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, indietro_button);
				getContentPane().add(scrollPane);
				
				indietro_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.chiudiVisualizzaDipendentiGUI();
						
					}});
				
				
	}
}
