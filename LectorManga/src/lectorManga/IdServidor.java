package lectorManga;

public enum IdServidor {
	
	EsMangaHere("EsMangaHere"),
	SubManga("SubManga"),
	TusMangasOnline("TusMangasOnline");
	private final String servidor;
	
	private IdServidor (String servidor){
		this.servidor=servidor;
	}
	
	@Override
	public String toString() {
		return servidor;
	}
	
}
