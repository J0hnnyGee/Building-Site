package gui;

import java.awt.Color;
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

import entita.Cantiere;
import tableModels.CantieriApertiModel;

public class CantieriApertiGUI extends JFrame {
	private JTable table;
	private String[] nomiColonne = {"Id", "Citta", "Via", "Data Apertura"};				//colonne
	private ArrayList <Cantiere> listaCantieri = new ArrayList<>();		//righe

	public CantieriApertiGUI(controller.Controller controller, ArrayList<Cantiere> listaCantieri) {
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
		
		JLabel aperti_label = new JLabel("Cantieri Aperti");
		springLayout.putConstraint(SpringLayout.NORTH, aperti_label, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, aperti_label, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(aperti_label);
		aperti_label.setFont(sezione_font);
		
		CantieriApertiModel apertiModel = new CantieriApertiModel(listaCantieri, nomiColonne);			//oggetto modello
		table = new JTable(apertiModel);																//tabella con quel modello
		springLayout.putConstraint(SpringLayout.NORTH, table, 21, SpringLayout.SOUTH, aperti_label);
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, -184, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 814, SpringLayout.WEST, getContentPane());
		table.setBackground(Color.WHITE);
		getContentPane().add(table);
		
		JButton indietro_button = new JButton("Indietro");
		springLayout.putConstraint(SpringLayout.WEST, indietro_button, -119, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, indietro_button, -181, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, indietro_button, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(indietro_button);
		indietro_button.setFont(submit_font);
		indietro_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.chiudiCantieriApertiGUI();
				
			}});
			
		JScrollPane scrollPane = new JScrollPane(table);															//scrollpane della tabella
		springLayout.putConstraint(SpringLayout.NORTH, indietro_button, 22, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, aperti_label);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -230, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);
		//getContentPane().add(new JScrollPane(table)
	}
}
