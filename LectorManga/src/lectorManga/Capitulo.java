package lectorManga;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Capitulo {
	
	private ArrayList<ImageIcon> imagenes;
	private String path, nCapitulo, nroPaginas;
	
	public Capitulo(String nombre, String path){
		this.nCapitulo=nombre;
		this.path=path;
	};

}
