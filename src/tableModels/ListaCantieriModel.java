package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entita.Cantiere;

public class ListaCantieriModel extends AbstractTableModel {
	private ArrayList <Cantiere> listaCantieri;
	private String[] nomiColonne;
	
	public ListaCantieriModel(ArrayList <Cantiere> listaCantieri, String[] nomiColonne) {
		this.listaCantieri = listaCantieri;
		this.nomiColonne = nomiColonne;
	}
	
	
	@Override
	public int getRowCount() {
		if (listaCantieri.isEmpty())
			return 0;
		return listaCantieri.size();	
	}

	@Override
	public int getColumnCount() {
			if(nomiColonne == null)
				return 0;
			return nomiColonne.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object cant = null;
		if (columnIndex==0)
			cant=listaCantieri.get(rowIndex).getId();
		if (columnIndex==1)
			cant=listaCantieri.get(rowIndex).getCitta();
		if (columnIndex==2)
			cant=listaCantieri.get(rowIndex).getVia();
		if (columnIndex==3)
			cant=listaCantieri.get(rowIndex).getStato();
		return cant;
	}

	public String getColumnName(int column) {
		return nomiColonne[column];
	}
	
}
