package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entita.Zona;

public class VisualizzaZoneModel extends AbstractTableModel {
	private ArrayList <Zona> listaZone;
	private String[] nomiColonne;								//colonne

	public VisualizzaZoneModel(ArrayList <Zona> listaZone, String[] nomiColonne) {		//costruttore
		this.listaZone = listaZone;
		this.nomiColonne = nomiColonne;
	}
	
	@Override
	public int getRowCount() {
		if (listaZone.isEmpty())
			return 0;
		return listaZone.size();
	}

	@Override
	public int getColumnCount() {
		if(nomiColonne == null)	
			return 0;
		return nomiColonne.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object zona = null;
		
		if (columnIndex == 0)
			zona = listaZone.get(rowIndex).getId();
		if (columnIndex == 1)
			zona = listaZone.get(rowIndex).getGrandezza();
		if (columnIndex == 2)
			zona = listaZone.get(rowIndex).getTipo();
		if (columnIndex == 3)
			zona = listaZone.get(rowIndex).getCf();
		return zona;
	}
	
	public String getColumnName(int column) {					//nome della colonna
		return nomiColonne[column];
	}

}
