package lectorManga;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EsMangaHere extends Servidor{

	public EsMangaHere(){
		//setPath("http://es.mangahere.co/mangalist/");
		setServidor("EsMangaHere");
		//setPatronManga("<li><a class=\"manga_info\" rel=\"([^\"]*)\" href=\"([^\"]*)\"><span>[^<]*</span>([^<]*)</a></li>");
		//setPatronCapitulos();
		//setPatronDatos();	
	}

	@Override
	public ArrayList<Manga> obtenerMangas() {
		ArrayList<Manga> mangas = new ArrayList<Manga>();
		String fuente = obtenerCodigoFuente("http://es.mangahere.co/mangalist/");
		Pattern p = Pattern.compile("<li><a class=\"manga_info\" rel=\"([^\"]*)\" href=\"([^\"]*)\"><span>[^<]*</span>([^<]*)</a></li>");
		Matcher m = p.matcher(fuente);
		while (m.find()){
			mangas.add(new Manga(m.group(1), this, m.group(2)));
		}
		return mangas;
	}
	
}
