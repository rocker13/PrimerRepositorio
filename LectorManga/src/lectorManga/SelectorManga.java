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
private final Visor padre2;

	public SelectorManga(Visor padre,boolean modal){
		 super(padre,modal);
		 padre2=padre;
		 final JTable tabla = new JTable(new ModeloTablaManga());
         tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                 
         JLabel filtro = new JLabel("Filtro: ");
         final JTextField txtFiltro= new JTextField("");
                 
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
                 
         txtFiltro.setPreferredSize(new Dimension(300,25));
         
         scrollTabla.setPreferredSize(new Dimension(350,200));
         cboServidor.setPreferredSize(new Dimension(300,25));
         
         panelSuperior.setLayout(new BorderLayout());
         panelPrincipal.setLayout(new BorderLayout());
         
         panelIzquierdo.add(scrollTabla);
         
         panelSupIzq.add(filtro);
         panelSupIzq.add(txtFiltro);
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
         
         btnAceptar.addActionListener(new ActionListener(){
        	 public void actionPerformed(ActionEvent e){
        		 if(tabla.getSelectedRow()>-1){
        			 padre2.agregarPestañas();
        			 dispose();
        		 }else{
        			 JOptionPane.showMessageDialog(null, "Debe seleccionar un manga","Error",JOptionPane.ERROR_MESSAGE);
        			 txtFiltro.requestFocus();
        		 }
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
