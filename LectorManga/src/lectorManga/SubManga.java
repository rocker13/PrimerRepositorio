package lectorManga;

public class SubManga extends Servidor {
	
	public SubManga(){
		setPatronManga("<td><a href=\"(http://submanga.com/.+?)\".+?</b>(.+?)<");
		setPath("http://submanga.com/series/n");
		setServidor(IdServidor.SubManga);
	}
}
