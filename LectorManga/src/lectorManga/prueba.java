package lectorManga;


	import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.*;
import javax.swing.*;
	 
	/**
	 * This class demonstrates how to load an Image from an external file
	 */
	public class prueba extends Component {
	           
	    BufferedImage img;
	    JLabel label;
	 
	    public void paint(Graphics g) {
	        //g.drawImage(img, 0, 0, null);
	    }
	 
	    public prueba() {
	       try {
	    	   URL url = new URL("http://myanx.com/mangas-online/happy-world/capitulo-3/happyworld.jpg");
	            URLConnection uc =  url.openConnection();
	            uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)");
	            uc.connect();
	            InputStream is=uc.getInputStream();
	           img = ImageIO.read(is);
	           ImageIcon imagen=new ImageIcon(img);
	           label=new JLabel();
	           label.setIcon(imagen);
	           
	       } catch (IOException e) {
	       }
	 
	    }
	 
	    public Dimension getPreferredSize() {
	        if (img == null) {
	             return new Dimension(100,100);
	        } else {
	           return new Dimension(img.getWidth(null), img.getHeight(null));
	       }
	    }
	 
	    public static void main(String[] args) {
	 
	        JFrame f = new JFrame("Load Image Sample");
	            
	        f.addWindowListener(new WindowAdapter(){
	                public void windowClosing(WindowEvent e) {
	                    System.exit(0);
	                }
	            });
	 
	        f.add(new prueba().label);
	        f.pack();
	        f.setVisible(true);
	    }
	}

