package lectorManga;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class EsMangaHere extends Servidor{
	
	public EsMangaHere(){
		setPatronManga("<li><a class=\"manga_info\" rel=\"([^\"]*)\" href=\"([^\"]*)\"><span>[^<]*</span>([^<]*)</a></li>");
		setPath("http://es.mangahere.co/mangalist/");
		setServidor(IdServidor.EsMangaHere);
	}

	@Override
	public ArrayList<Manga> obtenerMangas() {
		ArrayList<Manga> mangas = new ArrayList<Manga>();
		String fuente = obtenerCodigoFuente(this.getPath());
		Pattern p = Pattern.compile(this.getPatronManga());
		Matcher m = p.matcher(fuente);
		while (m.find()){
			mangas.add(new Manga(m.group(1), this.getServidor(), m.group(2)));
		}
		return mangas;
	}
	
	@Override
	public void obtenerMangas(ModeloTablaManga modelo){
		String fuente = obtenerCodigoFuente(this.getPath());
		Pattern p = Pattern.compile(this.getPatronManga());
		Matcher m = p.matcher(fuente);
		while (m.find()){
			modelo.addRow(new Manga(m.group(1), this.getServidor(), m.group(2)));
		}
	}
	
}
