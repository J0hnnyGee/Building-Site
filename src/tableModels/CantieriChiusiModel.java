package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entita.Cantiere;

public class CantieriChiusiModel extends AbstractTableModel {
	private ArrayList <Cantiere> listaChiusi;					//righe
	private String[] nomiColonne;								//colonne
	
	public CantieriChiusiModel(ArrayList <Cantiere> listaCantieri, String[] nomiColonne) {		//costruttore
		this.listaChiusi = listaCantieri;
		this.nomiColonne = nomiColonne;
	}
	
	@Override
	public int getRowCount() {
		if (listaChiusi.isEmpty())
			return 0;
		return listaChiusi.size();
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
			cant = listaChiusi.get(rowIndex).getId();
		if (columnIndex==1)
			cant = listaChiusi.get(rowIndex).getCitta();
		if (columnIndex==2)
			cant = listaChiusi.get(rowIndex).getVia();
		if (columnIndex==3)
			cant = listaChiusi.get(rowIndex).getData_inizio();
		if (columnIndex==4)
			cant = listaChiusi.get(rowIndex).getData_fine();
		return cant;
	}

	public String getColumnName(int column) {					//nome delle colonne
		return nomiColonne[column];
	}
}
