package lectorManga;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.BoxLayout;
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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SelectorManga extends JDialog{
private final Visor padre2;

	public SelectorManga(Visor padre,boolean modal){
		 super(padre,modal);
		 padre2=padre;
		 final JTable tabla = new JTable(new ModeloTablaManga());
         tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                 
         JLabel filtro = new JLabel("Filtro:");
         //filtro.setAlignmentX(CENTER_ALIGNMENT);
         final JTextField txtFiltro= new JTextField("");
         //txtFiltro.setAlignmentX(CENTER_ALIGNMENT);
                 
         JScrollPane scrollTabla = new JScrollPane(tabla);
                 
         JPanel panelPrincipal = new JPanel();
         JPanel panelSuperior = new JPanel();
         JPanel panelSupFiltro = new JPanel();
         JPanel panelSupServidor = new JPanel();
         JPanel panelCentral = new JPanel();
         JPanel panelInferior = new JPanel();
         
         JButton btnVer = new JButton(new ImageIcon(getClass().getResource("/imagenes/info.png")));
         btnVer.setToolTipText("Ver Información");
         JButton btnAceptar = new JButton(new ImageIcon(getClass().getResource("/imagenes/aceptar.jpg")));
         btnAceptar.setToolTipText("Aceptar");
         JButton btnCerrar = new JButton(new ImageIcon(getClass().getResource("/imagenes/cerrar.png")));
         btnCerrar.setToolTipText("Cerrar");
         
         JComboBox cboServidor = new JComboBox();
                 
         txtFiltro.setPreferredSize(new Dimension(300,25));
         
         scrollTabla.setPreferredSize(new Dimension(350,200));
         cboServidor.setPreferredSize(new Dimension(280,25));
         
         panelSuperior.setLayout(new GridLayout(2,1));
         panelPrincipal.setLayout(new BorderLayout());
         
         panelCentral.add(scrollTabla);
         
         panelSupFiltro.add(filtro);
         panelSupFiltro.add(txtFiltro);
         panelSupServidor.add(new JLabel("Servidor:"));
         panelSupServidor.add(cboServidor);
         
         panelSuperior.add(panelSupServidor);
         panelSuperior.add(panelSupFiltro);
         
         panelInferior.add(btnVer);
         panelInferior.add(btnAceptar);
         panelInferior.add(btnCerrar);
         
         panelPrincipal.add(panelSuperior,BorderLayout.NORTH);
         panelPrincipal.add(panelCentral,BorderLayout.CENTER);
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
         txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
             public void keyReleased(java.awt.event.KeyEvent evt) {
                 TableRowSorter gridFiltrado= new TableRowSorter(tabla.getModel());
                 gridFiltrado.setRowFilter(RowFilter.regexFilter(txtFiltro.getText(), 0));
                 tabla.setRowSorter(gridFiltrado);
             }
         });

         
         setSize(360, 350);
         setLocationRelativeTo(null);
         setResizable(false);
         add(panelPrincipal);
         setVisible(true);
         
 
	}
	
	
	
}
