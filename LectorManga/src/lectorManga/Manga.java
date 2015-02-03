package lectorManga;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Manga {
	
	private String titulo, sinopsis,estado,autor, path;
	private Servidor servidor;
	private ArrayList<Capitulo> capitulos;
	private ImageIcon imagen;
	
	public Manga(String titulo, Servidor servidor, String path) {
		this.titulo = titulo;
		this.servidor = servidor;
		this.path = path;
	}
	
	public Manga(){
		setImagen(new ImageIcon(getClass().getResource("/imagenes/manga.jpg")));
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public String getSinopsis(){
		return sinopsis;
	}
	
	public String getEstado(){
		return String.format("Estado: %s", estado);
	}
	
	public String getAutor(){
		return String.format("Autor: %s", autor);
	}
	
	public String getServidor(){
		return servidor.toString();
	}
	
	public String getPath(){
		return path;
	}
	
	public ImageIcon getImagen(){
		return imagen;
	}
	
	public void setImagen(ImageIcon img){
		BufferedImage bi = new BufferedImage(120, 160,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img.getImage(), 0, 0, 120, 160, null);
		imagen = new ImageIcon(bi);
	}
	
	public void setCapitulos(ArrayList<Capitulo> capitulos){
		this.capitulos = capitulos;
	}
	
	public void setAutor(String autor){
		this.autor = autor;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
	
	public void setSinopsis(String sinopsis){
		this.sinopsis = sinopsis;
	}

	@Override
	public String toString() {
		return titulo;
	}
	
}
