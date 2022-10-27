package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import dao.SensoreDAO;
import entita.Cantiere;
import entita.Sensore;
import tableModels.ListaCantieriModel;
import tableModels.VisualizzaSensoriModel;

public class VisualizzaSensoriGUI extends JFrame {
	private JTable table;
	private String[] nomiColonne = {"Id", "Type", "Data", "Threshold", "Zone", "Position", "Operator"};
	private ArrayList <Sensore> listaSensori = new ArrayList<>();
	
	public VisualizzaSensoriGUI(controller.Controller controller, ArrayList<Sensore> listaSensori, String id_can) {
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
		
		int id_cantiere=Integer.parseInt(id_can);
		
		JLabel sensori_label = new JLabel("Sensors List");
		springLayout.putConstraint(SpringLayout.NORTH, sensori_label, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, sensori_label, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(sensori_label);
		sensori_label.setFont(sezione_font);
		
		VisualizzaSensoriModel sensoriModel = new VisualizzaSensoriModel(listaSensori, nomiColonne);
		table = new JTable(sensoriModel);
		springLayout.putConstraint(SpringLayout.NORTH, table, 21, SpringLayout.SOUTH, sensori_label);
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, -184, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 814, SpringLayout.WEST, getContentPane());
		table.setBackground(Color.WHITE);
		getContentPane().add(table);
		allarme(listaSensori);
		
		JButton indietro_button = new JButton("Back");
		springLayout.putConstraint(SpringLayout.WEST, indietro_button, 673, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, indietro_button, -10, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(indietro_button);
		indietro_button.setFont(submit_font);
		indietro_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.chiudiVisualizzaSensoriGUI();
				
			}});
		
		JButton dato_button = new JButton("<html><center>Add Sensor<br/>Data</center></html>");
		springLayout.putConstraint(SpringLayout.WEST, dato_button, 369, SpringLayout.WEST, sensori_label);
		getContentPane().add(dato_button);
		dato_button.setFont(submit_font);
		dato_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(check(row)) {
				int id_sen = table.getSelectedRow();
				int id =IdSensore(id_sen);
				if((TipoSensore(row)).equals("Gas"))
					controller.apriAggiungiDatoGasGUI(id);
				else
					controller.apriAggiungiDatoRumoreGUI(id);
				}
				
			}});
		
		JButton sogliaGas_button = new JButton("<html><center>Edit Gas<br/>threshold</center></html>");
		springLayout.putConstraint(SpringLayout.NORTH, dato_button, 0, SpringLayout.NORTH, sogliaGas_button);
		springLayout.putConstraint(SpringLayout.NORTH, indietro_button, 89, SpringLayout.SOUTH, sogliaGas_button);
		springLayout.putConstraint(SpringLayout.EAST, indietro_button, 0, SpringLayout.EAST, sogliaGas_button);
		springLayout.putConstraint(SpringLayout.SOUTH, sogliaGas_button, -146, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, sogliaGas_button, 663, SpringLayout.WEST, sensori_label);
		getContentPane().add(sogliaGas_button);
		sogliaGas_button.setFont(submit_font);
		sogliaGas_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.apriModificaSogliaGasGUI("Gas", id_cantiere);
				
			}});
		
		JButton sogliaRumore_button = new JButton("<html><center>Edit noise<br/>threshold</center></html>");
		springLayout.putConstraint(SpringLayout.WEST, sogliaRumore_button, 526, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, sogliaRumore_button, -6, SpringLayout.WEST, sogliaGas_button);
		springLayout.putConstraint(SpringLayout.EAST, dato_button, -6, SpringLayout.WEST, sogliaRumore_button);
		springLayout.putConstraint(SpringLayout.SOUTH, sogliaRumore_button, -146, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(sogliaRumore_button);
		sogliaRumore_button.setFont(submit_font);
		sogliaRumore_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.apriModificaSogliaRumoreGUI("Rumore", id_cantiere);

				
			}});
		
		JScrollPane scrollPane = new JScrollPane(table);
		JScrollPane scrollPane_1 = new JScrollPane(table);
		springLayout.putConstraint(SpringLayout.NORTH, sogliaRumore_button, 6, SpringLayout.SOUTH, scrollPane_1);
		springLayout.putConstraint(SpringLayout.NORTH, sogliaGas_button, 6, SpringLayout.SOUTH, scrollPane_1);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, -199, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, sogliaGas_button, 0, SpringLayout.EAST, scrollPane_1);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 6, SpringLayout.SOUTH, sensori_label);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 0, SpringLayout.WEST, sensori_label);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane_1);
		
		JButton rumore_button = new JButton("Filtra Rumore");
		springLayout.putConstraint(SpringLayout.NORTH, rumore_button, 6, SpringLayout.SOUTH, scrollPane_1);
		springLayout.putConstraint(SpringLayout.WEST, rumore_button, 0, SpringLayout.WEST, sensori_label);
		springLayout.putConstraint(SpringLayout.SOUTH, rumore_button, 0, SpringLayout.SOUTH, dato_button);
		springLayout.putConstraint(SpringLayout.EAST, rumore_button, -228, SpringLayout.WEST, dato_button);
		getContentPane().add(rumore_button);
		rumore_button.setFont(submit_font);
		rumore_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList <Sensore> listaRumore = new ArrayList<>();
				listaRumore = controller.filtraRumore(id_can);
				VisualizzaSensoriModel rumoreModel = new VisualizzaSensoriModel(listaRumore, nomiColonne);
				table.setModel(rumoreModel);
				allarme(listaRumore);

		}});
		
		JButton gas_button = new JButton("Filter Gas");
		springLayout.putConstraint(SpringLayout.NORTH, gas_button, 19, SpringLayout.SOUTH, rumore_button);
		springLayout.putConstraint(SpringLayout.WEST, gas_button, 0, SpringLayout.WEST, sensori_label);
		springLayout.putConstraint(SpringLayout.EAST, gas_button, 0, SpringLayout.EAST, sensori_label);
		getContentPane().add(gas_button);
		gas_button.setFont(submit_font);
		gas_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList <Sensore> listaGas = new ArrayList<>();
				listaGas = controller.filtraGas(id_can);
				VisualizzaSensoriModel gasModel = new VisualizzaSensoriModel(listaGas, nomiColonne);
				table.setModel(gasModel);
				allarme(listaGas);

		}});

		
		JButton reset_button = new JButton("Reset/Refresh");
		springLayout.putConstraint(SpringLayout.SOUTH, gas_button, -23, SpringLayout.NORTH, reset_button);
		springLayout.putConstraint(SpringLayout.NORTH, reset_button, 0, SpringLayout.NORTH, indietro_button);
		springLayout.putConstraint(SpringLayout.WEST, reset_button, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, reset_button, 0, SpringLayout.SOUTH, indietro_button);
		springLayout.putConstraint(SpringLayout.EAST, reset_button, 0, SpringLayout.EAST, sensori_label);
		getContentPane().add(reset_button);
		reset_button.setFont(submit_font);
		reset_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 ArrayList <Sensore> tmp = new ArrayList<>();
				 tmp=controller.aggiornaSensori(id_can);
				VisualizzaSensoriModel sensoriModel= new VisualizzaSensoriModel(tmp, nomiColonne);
				table.setModel(sensoriModel);
				
				allarme(tmp);

		}});

	}
	
	//(METODI)
	
	private String TipoSensore(int row)  {
		String tipoSensore = table.getModel().getValueAt(row, 1).toString();
		return tipoSensore;
	}
	
	private int IdSensore(int row)  {
		int id = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		return id;
	}
	
	public boolean check(int row) {
 		if (! vuoto( row, "ATTENZIONE! Selezionare sensore."))
		    return false;
 		else return true;
	}
	
	public boolean vuoto(int row, String errore ) {
		if ( row==-1 )
		    return fallito(errore );
		  else
		    return true;
	}
	
	public boolean fallito(String messaggio)
	{
	  JOptionPane.showMessageDialog(null, messaggio); 
	  return false; 
	}
	
	public boolean allarmeMessage(String messaggio)
	{
	  JOptionPane.showMessageDialog(null, messaggio); 
	  return false; 
	}
	
	public boolean allarme(ArrayList <Sensore> sensori) {
		for(int i=0; i<sensori.size();i++) {
			if((sensori.get(i).getDato())>(sensori.get(i).getSoglia())){
				allarmeMessage("Warning, one or more sensors are exceding the threshold!");
			return false;
			}
		}
		return true;
	}
	
}
