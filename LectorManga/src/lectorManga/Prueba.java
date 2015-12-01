package lectorManga;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JTable;

import org.omg.CORBA.TIMEOUT;

public class Prueba extends Thread {
	private ModeloTablaManga modelo;
	private JTable tabla;
	
	public Prueba (ModeloTablaManga modelo1,JTable tabla1){
		modelo=modelo1;
		tabla=tabla1;
		start();
	}
	@Override
	public void run() {
		

			//String fuente = obtenerCodigoFuente("http://submanga.com/series/n");
//			Pattern p = Pattern.compile("<td><a href=\"(http://submanga.com/.+?)\".+?</b>(.+?)<");
//			Matcher m = p.matcher(fuente);
			int cont=0;
			//while (m.find() && cont<5){
			while (cont<50){
				modelo.addRow(new Manga(String.valueOf(cont), new SubManga(), String.valueOf(cont)));
				//modelo.addRow(new Manga(m.group(2), new SubManga(), m.group(1)));
				cont++;
				try{
					Thread.sleep(1000);
					
				}catch(Exception e){
					
				}
				
				
			}
			
	
		
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

}
