package lectorManga;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelInformacion extends JPanel{
	private ImageIcon imagen;
	private JPanel panelIzqSup= new JPanel();
	public PanelInformacion(){
		setLayout(new BorderLayout());
		imagen = new ImageIcon(getClass().getResource("/imagenes/favoritos.jpg"));
		
		JPanel panelDerecho = new JPanel();
		JPanel panelIzquierdo = new JPanel();
		
		JPanel panelIzqInf = new JPanel();
		setImagen(imagen,imagen.getIconHeight()*2,imagen.getIconWidth()*2);
		panelIzqInf.add(new JLabel("Autor: \n Estado: "));
		JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        JScrollPane scrollDescripcion = new JScrollPane(descripcion,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDescripcion.setPreferredSize(new Dimension(300,200));
        panelDerecho.add(scrollDescripcion);
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.add(panelIzqSup,BorderLayout.NORTH);
        panelIzquierdo.add(panelIzqInf,BorderLayout.CENTER);
        this.add(panelIzquierdo,BorderLayout.WEST);
        this.add(panelDerecho,BorderLayout.EAST);
        //this.add(scrollDescripcion,BorderLayout.EAST);
	}
	public void setImagen(ImageIcon img,int x,int y){
		BufferedImage bi = new BufferedImage(x, y, 2);
		Graphics g = bi.createGraphics();
		g.drawImage(img.getImage(), 0, 0, x, y, null);
		ImageIcon newIcon = new ImageIcon(bi);
		panelIzqSup.add(new JLabel(newIcon));
	}

}
