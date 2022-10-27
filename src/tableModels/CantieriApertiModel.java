package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entita.Cantiere;

public class CantieriApertiModel extends AbstractTableModel {
	private ArrayList <Cantiere> listaAperti;					//righe
	private String[] nomiColonne;								//colonne
	
	public CantieriApertiModel(ArrayList <Cantiere> listaCantieri, String[] nomiColonne) {		//costruttore
		this.listaAperti = listaCantieri;
		this.nomiColonne = nomiColonne;
	}
	
	@Override
	public int getRowCount() {			//conta righe
		if (listaAperti.isEmpty())
			return 0;
		return listaAperti.size();
	}

	@Override	
	public int getColumnCount() {	//conta colonne
		if(nomiColonne == null)	
			return 0;
		return nomiColonne.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {		//per identificare il valore in un posto
		Object cant = null;
		
		if (columnIndex==0)
			cant = listaAperti.get(rowIndex).getId();
		if (columnIndex==1)
			cant = listaAperti.get(rowIndex).getCitta();
		if (columnIndex==2)
			cant = listaAperti.get(rowIndex).getVia();
		if (columnIndex==3)
			cant = listaAperti.get(rowIndex).getData_inizio();
		return cant;
	}
	
	public String getColumnName(int column) {					//nome della colonna
		return nomiColonne[column];
	}

}
