package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entita.Sensore;

public class VisualizzaSensoriModel extends AbstractTableModel {
	private ArrayList <Sensore> listaSensori;
	private String[] nomiColonne;								//colonne

	public VisualizzaSensoriModel(ArrayList <Sensore> listaSensori, String[] nomiColonne) {
		this.listaSensori = listaSensori;
		this.nomiColonne = nomiColonne;

	}
	
	
	@Override
	public int getRowCount() {
		if (listaSensori.isEmpty())
			return 0;
		return listaSensori.size();
	}

	@Override
	public int getColumnCount() {
		if(nomiColonne == null)	
			return 0;
		return nomiColonne.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object sensore = null;
		
		if (columnIndex==0)
			sensore = listaSensori.get(rowIndex).getId();
		if (columnIndex==1)
			sensore = listaSensori.get(rowIndex).getTipo();
		if (columnIndex==2)
			sensore = listaSensori.get(rowIndex).getDato();
		if (columnIndex==3)
			sensore = listaSensori.get(rowIndex).getSoglia();
		if (columnIndex==4)
			sensore = listaSensori.get(rowIndex).getId_zona();
		if (columnIndex==5)
			sensore = listaSensori.get(rowIndex).getPosizione();
		if (columnIndex==6)
			sensore = listaSensori.get(rowIndex).getCf_dipendente();
		return sensore;
	}
	
	public String getColumnName(int column) {					//nome delle colonne
		return nomiColonne[column];
	}
}
