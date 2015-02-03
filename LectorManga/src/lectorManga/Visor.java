package lectorManga;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.FocusAdapter;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Visor extends JFrame{
	     
	private static Visor visor;
	private JLabel labelImagen;
	private JLabel labelCantidad;
	private JButton botonAtras;
	private JButton botonAdelante;
	private JButton botonbhInicio;
	private JButton botonbhCargar;
	private JButton botonbhAFavorito;
	private JButton botonbhVFavorito;
	private JButton botonbhAyuda;
	private JButton botonbhZoomG;
	private JButton botonbhZoomC;
	private JPanel panelNorte;
	private JPanel panelComponentes;
	private JPanel panelImagen;
	private JPanel panelContenidoDirectorio;
	private JScrollPane scroll;
	
	
	private ImageIcon imagen;

	private JMenu menuArchivo;
	private JMenu menuAyuda;
	private JMenuBar barraMenu;
	private JMenuItem itemArSalir;
	private JMenuItem itemArCargaManual;
	private JMenuItem itemArCargaAuto;
	private JMenuItem itemAyAyuda;
	private JMenuItem itemAyAcerca;
	private JTabbedPane Pestaña = new Pestaña();

	private JToolBar barraHerramientas;
	
	private JViewport view;
	private int contador=0;


	public Visor() {
		
		this.barraMenu = new JMenuBar();
		this.barraHerramientas = new JToolBar("Herramientas");
		setJMenuBar(this.barraMenu);
		this.botonbhInicio = new JButton(new ImageIcon(getClass().getResource("/imagenes/inicio.png")));
		this.botonbhInicio.setToolTipText("Cargar Manga");
		this.botonbhCargar = new JButton(new ImageIcon(getClass().getResource("/imagenes/cargar.png")));
		this.botonbhCargar.setToolTipText("Cargar Manualmente");
		this.botonbhVFavorito = new JButton(new ImageIcon(getClass().getResource("/imagenes/favoritos.jpg")));
		this.botonbhVFavorito.setToolTipText("Ver Favoritos");
		this.botonbhAFavorito = new JButton(new ImageIcon(getClass().getResource("/imagenes/favoritos.png")));
		this.botonbhAFavorito.setToolTipText("Agregar a Favoritos");
		this.botonbhZoomG = new JButton(new ImageIcon(getClass().getResource("/imagenes/zoomIn.png")));
		this.botonbhZoomG.setToolTipText("Zoom +");
		this.botonbhZoomC = new JButton(new ImageIcon(getClass().getResource("/imagenes/zoomOut.png")));
		this.botonbhZoomC.setToolTipText("Zoom -");
		this.botonbhAyuda = new JButton(new ImageIcon(getClass().getResource("/imagenes/ayuda.png")));
		this.botonbhAyuda.setToolTipText("Ayuda(Pendiente)");
		this.menuArchivo = new JMenu("Archivo");
		this.itemArCargaAuto = new JMenuItem("Buscar por genero");
		this.itemArCargaManual = new JMenuItem("Cargar Manualmente");
		this.itemArSalir = new JMenuItem("Salir");
		this.menuArchivo.add(this.itemArCargaManual);
		this.menuArchivo.add(this.itemArCargaAuto);
		this.menuArchivo.add(this.itemArSalir);
		this.menuAyuda = new JMenu("Ayuda");
		this.itemAyAyuda = new JMenuItem("Manual de usuario");
		this.itemAyAcerca = new JMenuItem("Acerca de");
		this.menuAyuda.add(this.itemAyAyuda);
		this.menuAyuda.add(this.itemAyAcerca);
		this.barraMenu.add(this.menuArchivo);
		this.barraMenu.add(this.menuAyuda);
		
		this.barraHerramientas.add(this.botonbhInicio);
		this.barraHerramientas.add(this.botonbhCargar);
		this.barraHerramientas.add(this.botonbhAFavorito);
		this.barraHerramientas.add(this.botonbhVFavorito);
		this.barraHerramientas.add(this.botonbhZoomG);
		this.barraHerramientas.add(this.botonbhZoomC);
		this.barraHerramientas.add(this.botonbhAyuda);
		
		this.labelCantidad = new JLabel("00/00");
		this.labelCantidad.setHorizontalAlignment(0);
		this.labelImagen = new JLabel();
		this.labelImagen.setHorizontalAlignment(0);
		this.scroll = new JScrollPane(labelImagen, 22, 32);
		
		this.botonAdelante = new JButton("Siguiente");
		this.botonAtras = new JButton("Anterior");

		this.panelNorte = new JPanel();
		this.panelNorte.setLayout(new BorderLayout());
		this.panelComponentes = new JPanel();
		this.panelComponentes.add(this.botonAtras);
		this.panelComponentes.add(this.labelCantidad);
		this.panelComponentes.add(this.botonAdelante);
		this.panelComponentes.setBorder(BorderFactory.createTitledBorder("Controles de imagen"));
		this.panelNorte.add(this.barraHerramientas, "North");
		this.panelImagen = new JPanel();
		this.panelImagen.setLayout(new BorderLayout());
		this.panelImagen.setBorder(BorderFactory.createTitledBorder("Visualizacion de la imagen"));
		this.panelImagen.add(this.scroll, "Center");
		add(this.Pestaña, "Center");
		this.panelNorte.add(this.panelComponentes, "South");
		add(this.panelNorte, "North");
		 
		this.panelContenidoDirectorio = new JPanel();
		this.panelContenidoDirectorio.setLayout(new BorderLayout());
		
		
		     
		view = scroll.getViewport();
		InputMap inputMap = this.view.getInputMap(2);
		ActionMap actionMap = this.view.getActionMap();
		
		Action upKeyAction = new Imagen.MoveAction(this.view, 1, 1, 0, this.labelImagen);
		KeyStroke upKey = KeyStroke.getKeyStroke("UP");
		inputMap.put(upKey, "up");
		actionMap.put("up", upKeyAction);
		
		Action downKeyAction = new Imagen.MoveAction(this.view, 0, 1, 0, this.labelImagen);
		KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
		inputMap.put(downKey, "down");
		actionMap.put("down", downKeyAction);
		
		Action leftKeyAction = new Imagen.MoveAction(this.view, 1, 0, 0, this.labelImagen, this.botonAtras, this.botonAdelante);
		KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
		inputMap.put(leftKey, "left");
		actionMap.put("left", leftKeyAction);
		
		Action rightKeyAction = new Imagen.MoveAction(this.view, 0, 0, 0, this.labelImagen, this.botonAtras, this.botonAdelante);
		KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
		inputMap.put(rightKey, "right");
		actionMap.put("right", rightKeyAction);
		
		Action pgUpKeyAction = new Imagen.MoveAction(this.view, 1, 1, 1, this.labelImagen);
		KeyStroke pgUpKey = KeyStroke.getKeyStroke("PAGE_UP");
		inputMap.put(pgUpKey, "pgUp");
		actionMap.put("pgUp", pgUpKeyAction);
		
		Action pgDnKeyAction = new Imagen.MoveAction(this.view, 0, 1, 1, this.labelImagen);
		KeyStroke pgDnKey = KeyStroke.getKeyStroke("PAGE_DOWN");
		inputMap.put(pgDnKey, "pgDn");
		actionMap.put("pgDn", pgDnKeyAction);
	
	
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Visor de Mangas");
		setDefaultCloseOperation(3);
		setVisible(true);
	
			
	botonbhInicio.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
			new SelectorManga(visor,true);
			agregarPestañas();
		}
	});
	
	this.itemAyAcerca.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AcercaDe acercaDe = new AcercaDe(visor, true);
			acercaDe.setVisible(true);
		}
	});
		 
}
	
	
		

	
	public static void main(String[] args){
		try
		{
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		       JOptionPane.showMessageDialog(null, "Clase no encotrada.", "Error", 0);
		} catch (UnsupportedLookAndFeelException e) {
		       JOptionPane.showMessageDialog(null, "Sin soporte grafico.", "Error", 0);
		} catch (InstantiationException e) {
		       JOptionPane.showMessageDialog(null, "Error de Instanciacion.", "Error", 0);
		} catch (IllegalAccessException e) {
		       JOptionPane.showMessageDialog(null, "Acceso Ilegal.", "Error", 0);
		}
        visor = new Visor();
	}
	
	public void agregarPestañas(){
		contador++;	
		//labelImagen.setIcon(imagen);
		Pestaña.addTab("Title "+contador, new JLabel(imagen));
        Pestaña.setSelectedIndex(Pestaña.getTabCount() - 1);
	}
	
}
