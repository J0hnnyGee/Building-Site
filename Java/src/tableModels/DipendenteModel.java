package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entita.Dipendente;

public class DipendenteModel extends AbstractTableModel {
	private ArrayList <Dipendente> listaDipendente;
	private String[] nomiColonne;
	
	public DipendenteModel(ArrayList <Dipendente> listaDipendente,String[] nomiColonne) {
		this.listaDipendente = listaDipendente;
		this.nomiColonne = nomiColonne;
	}
	
	@Override
	public int getRowCount() {
		if (listaDipendente.isEmpty())
			return 0;
		return listaDipendente.size();		
	}

	@Override
	public int getColumnCount() {
		if(nomiColonne == null)
			return 0;
		return nomiColonne.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object dip = null;
		
		if (columnIndex==0)
			dip = listaDipendente.get(rowIndex).getCf();
		if (columnIndex==1)
			dip = listaDipendente.get(rowIndex).getNome();
		if (columnIndex==2)
			dip = listaDipendente.get(rowIndex).getCognome();
		if (columnIndex==3)
			dip = listaDipendente.get(rowIndex).getData_nascita();
		if (columnIndex==4)
			dip = listaDipendente.get(rowIndex).getEmail();
		if (columnIndex==5)
			dip = listaDipendente.get(rowIndex).getTelefono();
		if (columnIndex==6)
			dip = listaDipendente.get(rowIndex).getIndirizzo();
		if (columnIndex==7)
			dip = listaDipendente.get(rowIndex).getStipendio();
		if (columnIndex==8)
			dip = listaDipendente.get(rowIndex).getRuolo();
		if (columnIndex==9)
			dip = listaDipendente.get(rowIndex).getId_cantiere();
		
		return dip;
	}
		public String getColumnName(int column) {
			return nomiColonne[column];
		}
	}


