package lectorManga;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public abstract class Servidor{

	private String PATH, PATRON_MANGA, PATRON_CAPITULOS, patronDatos;
	private IdServidor idServidor;
	
	public Servidor(){
		
	};
	
	public void setPath(String path){
		PATH = path;
	}
	
	public String getPath(){
		return PATH;
	}

	public void setPatronManga(String patronManga){
		PATRON_MANGA = patronManga;
	}
	
	public String getPatronManga(){
		return PATRON_MANGA;
	}

	public void setServidor(IdServidor idServer){
		idServidor = idServer;
	}
	
	public IdServidor getServidor(){
		return idServidor;
	}
	public String obtenerCodigoFuente(String direccion) {
		StringBuilder contenido = new StringBuilder();
		try{
			InputStreamReader isr = new InputStreamReader(obtenerConexion(direccion));
			//Creamos el objeto con el que vamos a leer
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				contenido.append(inputLine + "\n");
			}
			in.close();
		}catch(Exception e){
			
		}
		return contenido.toString();
    }
	
	public InputStream obtenerConexion(String direccion) throws Exception{
            URL url = new URL(direccion);
            URLConnection uc =  url.openConnection();
            uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)");
            uc.connect();
            InputStream is=uc.getInputStream();
            return is;
	}
	
	public ImageIcon obtenerImagen (String direccion){
		try{
			BufferedImage imagen1= ImageIO.read(obtenerConexion(direccion));
			return new ImageIcon(imagen1);
		}catch(Exception e){
			
		}
		return null;
	}
	
	public ArrayList<Manga> obtenerMangas(){
		ArrayList<Manga> mangas = new ArrayList<Manga>();
		String fuente = obtenerCodigoFuente(PATH);
		Pattern p = Pattern.compile(PATRON_MANGA);
		Matcher m = p.matcher(fuente);
		while (m.find()){
			mangas.add(new Manga(m.group(2), idServidor, m.group(1)));
		}
		return mangas;
	}
	
	public void obtenerMangas(ModeloTablaManga modelo){
		String fuente = obtenerCodigoFuente(PATH);
		Pattern p = Pattern.compile(PATRON_MANGA);
		Matcher m = p.matcher(fuente);
		while (m.find()){
			modelo.addRow(new Manga(m.group(2), idServidor, m.group(1)));
		}
		
	}
	
	public void cargarCapitulos(Manga manga){
		ArrayList<Capitulo> capitulos = new ArrayList<Capitulo>();
		String fuente = obtenerCodigoFuente(manga.getPath());
		Pattern p = Pattern.compile(PATRON_CAPITULOS);
		Matcher m = p.matcher(fuente);
		while (m.find()){
			capitulos.add(new Capitulo(m.group(2), m.group(1)));
		}
		manga.setCapitulos(capitulos);
	}
	
	public void setDatosManga(Manga manga){
		String fuente = obtenerCodigoFuente(manga.getPath());
		Pattern p = Pattern.compile(patronDatos);
		Matcher m = p.matcher(fuente);
		if(m.find()){
			manga.setImagen(obtenerImagen(m.group()));
			manga.setAutor(m.group());
			manga.setEstado(m.group());
			manga.setSinopsis(m.group());
		}
	}
	
}
