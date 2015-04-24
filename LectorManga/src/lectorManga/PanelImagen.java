package lectorManga;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;


public class PanelImagen extends JPanel{
	private JPanel panelComponentes;
	private JScrollPane scroll;
	private JLabel labelImagen;
	private JLabel labelCantidad;
	private JButton botonAtras;
	private JButton botonAdelante;
	private JViewport view;
	
	public PanelImagen (){
		setLayout(new BorderLayout());
		labelCantidad = new JLabel("00/00");
		labelCantidad.setHorizontalAlignment(0);
		botonAdelante = new JButton("Siguiente");
		botonAtras = new JButton("Anterior");
		labelImagen = new JLabel();
		labelImagen.setHorizontalAlignment(0);
		scroll = new JScrollPane(labelImagen, 22, 32);
		panelComponentes = new JPanel();
		panelComponentes.add(new JLabel("Capitulo: "));
		panelComponentes.add(new JComboBox<>());
		panelComponentes.add(new JLabel("Pagina:"));
		panelComponentes.add(labelCantidad);
		panelComponentes.add(this.botonAtras);
		panelComponentes.add(this.botonAdelante);
		
		add(this.panelComponentes, "North");
		add(this.scroll, "Center");
		
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
	}
}
