package lectorManga;

public class SubManga extends Servidor {
	public SubManga(){
		setServidor("SubManga");
		setPath("http://submanga.com/series/n");
		setPatronManga("<td><a href=\"(http://submanga.com/.+?)\".+?</b>(.+?)<");
	}
}
