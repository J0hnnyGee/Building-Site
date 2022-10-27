package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

public class ConfermaChiusuraGUI extends JFrame {

	public ConfermaChiusuraGUI(controller.Controller controller, String id) {
		//Grandezza finestra
		setSize(660,211);
		setResizable(false);

		//Dichiarazione font
		Font sezione_font = new Font("Serif", Font.PLAIN, 25);
		Font labels_font = new Font("Serif", Font.PLAIN, 18);
		Font submit_font = new Font("Serif", Font.PLAIN, 14);

		//Dichiarazione Layout
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel paragrafo = new JLabel("Are you sure?");
		springLayout.putConstraint(SpringLayout.NORTH, paragrafo, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, paragrafo, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(paragrafo);
		paragrafo.setFont(sezione_font);
		
		JButton conferma_button = new JButton("Submit");
		conferma_button.setBackground(UIManager.getColor("Button.focus"));
		springLayout.putConstraint(SpringLayout.WEST, conferma_button, 431, SpringLayout.WEST, getContentPane());
		getContentPane().add(conferma_button);
		conferma_button.setFont(submit_font);
		conferma_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.chiusura(id);
				} catch (SQLException er){
					System.out.print(er.getLocalizedMessage());
				}
				controller.chiudiConfermaChiusuraGUI();
				controller.chiudiHomeCapoCantiereGUI();
			}});
		
		JButton annulla_button = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, conferma_button, 0, SpringLayout.NORTH, annulla_button);
		springLayout.putConstraint(SpringLayout.EAST, conferma_button, -6, SpringLayout.WEST, annulla_button);
		springLayout.putConstraint(SpringLayout.WEST, annulla_button, -107, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, annulla_button, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, annulla_button, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(annulla_button);
		annulla_button.setFont(submit_font);
		annulla_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.chiudiConfermaChiusuraGUI();
				
			}});

	}

}
