package lectorManga;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Visor extends JFrame{
	     
	private static Visor visor;
	
	
	private JButton botonbhInicio;
	private JButton botonbhCargar;
	private JButton botonbhAFavorito;
	private JButton botonbhVFavorito;
	private JButton botonbhAyuda;
	private JButton botonbhZoomG;
	private JButton botonbhZoomC;
	private JPanel panelNorte;
	
	private JPanel panelContenidoDirectorio;
	

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
		
		
		
		

		this.panelNorte = new JPanel();
		this.panelNorte.setLayout(new BorderLayout());
		
		this.panelNorte.add(this.barraHerramientas, "North");
		
		add(this.Pestaña, "Center");
		//this.panelNorte.add(this.panelComponentes, "South");
		add(this.panelNorte, "North");
		 
		this.panelContenidoDirectorio = new JPanel();
		this.panelContenidoDirectorio.setLayout(new BorderLayout());
		
		
		     
		
	
	
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Visor de Mangas");
		setDefaultCloseOperation(3);
		setVisible(true);
	
			
	botonbhInicio.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
			new SelectorManga(visor,true);
			//agregarPestañas();
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
		Pestaña.addTab("Title "+contador, new PanelImagen());
        Pestaña.setSelectedIndex(Pestaña.getTabCount() - 1);
	}
	
}
