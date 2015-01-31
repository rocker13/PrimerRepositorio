package lectorManga;

import java.util.ArrayList;

public class Manga {
	
	private String titulo, sinopsis,estado,autor, path,servidor;
	private ArrayList<Capitulo> capitulos;
	
	public Manga(String titulo, String path) {
		this.titulo = titulo;
		this.path = path;
	}
	
	public void setServidor(String servidor){
		this.servidor=servidor;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public String getSinopsis(){
		return sinopsis;
	}
	
	public String getEstado(){
		return estado;
	}
	
	public String getAutor(){
		return autor;
	}
	
	public String getServidor(){
		return servidor;
	}
}
