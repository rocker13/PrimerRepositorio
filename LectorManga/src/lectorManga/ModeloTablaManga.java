package lectorManga;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaManga extends AbstractTableModel{

	private ArrayList<Manga> listaManga;
	
	public ModeloTablaManga(){
		listaManga = new ArrayList<Manga>();
		EsMangaHere manga = new EsMangaHere();
		listaManga = manga.obtenerMangas();
	}
	
	@Override
	public int getRowCount() {
		return listaManga.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		return listaManga.get(fila).toString();
	}

	@Override
	public String getColumnName(int column) {
		return "Mangas";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	}
	
	public void addRow (Manga manga){
		listaManga.add(manga);
	}
	
	public void setDatos (ArrayList<Manga> lista){
		listaManga = lista;
	}
	

}
