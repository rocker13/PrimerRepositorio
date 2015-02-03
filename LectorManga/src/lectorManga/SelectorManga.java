package lectorManga;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class SelectorManga extends JDialog{

	public SelectorManga(Visor padre,boolean modal){
		 super(padre,modal);
		 JTable tabla = new JTable(new ModeloTablaManga());
         tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                 
         JLabel filtro = new JLabel("Filtro: ");
         JTextField texto= new JTextField("");
                 
         JScrollPane scrollTabla = new JScrollPane(tabla);
                 
         JPanel panelPrincipal = new JPanel();
         JPanel panelSuperior = new JPanel();
         JPanel panelSupIzq = new JPanel();
         JPanel panelSupDer = new JPanel();
         JPanel panelIzquierdo = new JPanel();
         PanelInformacion panelDerecho = new PanelInformacion();
         JPanel panelInferior = new JPanel();
         
         JButton btnVer = new JButton("Ver Informacion");
         JButton btnAceptar = new JButton("Aceptar");
         JButton btnCerrar = new JButton("Cerrar");
         
         JComboBox cboServidor = new JComboBox();
                 
         texto.setPreferredSize(new Dimension(300,25));
         
         scrollTabla.setPreferredSize(new Dimension(350,200));
         cboServidor.setPreferredSize(new Dimension(300,25));
         
         panelSuperior.setLayout(new BorderLayout());
         panelPrincipal.setLayout(new BorderLayout());
         
         panelIzquierdo.add(scrollTabla);
         
         panelSupIzq.add(filtro);
         panelSupIzq.add(texto);
         panelSupDer.add(new JLabel("Servidor:"));
         panelSupDer.add(cboServidor);
         
         panelSuperior.add(panelSupIzq,BorderLayout.WEST);
         panelSuperior.add(panelSupDer,BorderLayout.EAST);
         
         panelInferior.add(btnVer);
         panelInferior.add(btnAceptar);
         panelInferior.add(btnCerrar);
         
         panelPrincipal.add(panelSuperior,BorderLayout.NORTH);
         panelPrincipal.add(panelIzquierdo,BorderLayout.WEST);
         panelPrincipal.add(panelDerecho,BorderLayout.EAST);
         panelPrincipal.add(panelInferior,BorderLayout.SOUTH);
         
         setTitle("Selector de Manga");

         btnVer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
         	
         });
         
         btnCerrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
         });    
         
         setSize(800, 300);
         setLocationRelativeTo(null);
         setResizable(false);
         add(panelPrincipal);
         setVisible(true);
         
 
	}
	
	
	
}
