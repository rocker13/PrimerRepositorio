package lectorManga;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelInformacion extends JFrame{
	
	private ImageIcon imagen;
	private String autor, estado;
	private JPanel panelIzqSup= new JPanel();
	private JTextArea descripcion;
	private JLabel labelDatos;
	
	public PanelInformacion(){
		setTitle("Información del Manga");
		setLayout(new BorderLayout());
		inicializarUI();
		setDatos(new Manga());  
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void inicializarUI(){
		labelDatos = new JLabel();
		descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setEditable(false);
		JPanel panelDerecho = new JPanel();
		JPanel panelIzquierdo = new JPanel();
		JPanel panelIzqInf = new JPanel();
		panelIzqInf.add(labelDatos);
		JScrollPane scrollDescripcion = new JScrollPane(descripcion,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDescripcion.setPreferredSize(new Dimension(300,200));
        panelDerecho.add(scrollDescripcion);
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.add(panelIzqSup,BorderLayout.NORTH);
        panelIzquierdo.add(panelIzqInf,BorderLayout.CENTER);
        this.add(panelIzquierdo,BorderLayout.WEST);
        this.add(panelDerecho,BorderLayout.EAST);
	}
	
	public void setDatos(Manga manga){
		imagen=manga.getImagen();
		panelIzqSup.add(new JLabel(imagen));
		descripcion.setText(manga.getSinopsis());
		labelDatos.setText(String.format("<html> %s <br> %s </html>", manga.getAutor(), manga.getEstado()));
		//this.repaint();
	}
	
	
	

}
