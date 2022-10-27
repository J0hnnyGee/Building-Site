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

import entita.Zona;
import tableModels.VisualizzaZoneModel;

public class VisualizzaZoneGUI extends JFrame {
	private JTable table;
	private String[] nomiColonne = {"Id", "Dimension", "Type", "Manager"};				//colonne
	private ArrayList <Zona> listaZone = new ArrayList<> ();
	
	public VisualizzaZoneGUI(controller.Controller controller, ArrayList<Zona> listaZone) {
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
		
		JLabel zone_label = new JLabel("Lista Zone");
		springLayout.putConstraint(SpringLayout.NORTH, zone_label, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, zone_label, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(zone_label);
		zone_label.setFont(sezione_font);
		
		VisualizzaZoneModel zoneModel = new VisualizzaZoneModel(listaZone, nomiColonne);
		table = new JTable(zoneModel);
		springLayout.putConstraint(SpringLayout.NORTH, table, 21, SpringLayout.SOUTH, zone_label);
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, -184, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 814, SpringLayout.WEST, getContentPane());
		table.setBackground(Color.WHITE);
		getContentPane().add(table);
		
		JButton indietro_button = new JButton("Back");
		springLayout.putConstraint(SpringLayout.WEST, indietro_button, -119, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, indietro_button, -156, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(indietro_button);
		indietro_button.setFont(submit_font);
		indietro_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.chiudiVisualizzaZoneGUI();
				
			}});
		
		JScrollPane scrollPane = new JScrollPane(table);
		springLayout.putConstraint(SpringLayout.NORTH, indietro_button, 31, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, indietro_button, 0, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, zone_label);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, zone_label);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -214, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 691, SpringLayout.EAST, zone_label);
		getContentPane().add(scrollPane);
		
	}

}
