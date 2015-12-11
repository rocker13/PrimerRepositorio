package lectorManga;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SelectorManga extends JDialog{
private final Visor padre2;
ModeloTablaManga modelo = new ModeloTablaManga();
int i = 0;

	public SelectorManga(Visor padre,boolean modal){
		 super(padre,false);
		 padre2=padre;
		 JPanel panelPrincipal = new JPanel();
		 panelPrincipal.setLayout(new GridBagLayout());
		 GridBagConstraints constraints = new GridBagConstraints();
                 
         JLabel lblServidor = new JLabel("Servidor:");
         constraints.gridx = 0;
         constraints.gridy = 0;
         constraints.gridwidth = 1;
         constraints.gridheight = 1;
         constraints.insets = new Insets(1,3,1,1);
         panelPrincipal.add(lblServidor,constraints);
         
         final JComboBox<IdServidor> cboServidor = new JComboBox<>(IdServidor.values());
//         cboServidor.addItem("Seleccione un Servidor");
//         cboServidor.addItem("EsMangaHere");
         cboServidor.setPreferredSize(new Dimension(250,25));
         constraints.gridx = 1;
         constraints.gridy = 0;
         constraints.gridwidth = 1;
         constraints.gridheight = 1;
         constraints.insets = new Insets(1,1,1,1);
         constraints.anchor = GridBagConstraints.LINE_END;
         panelPrincipal.add(cboServidor,constraints);
         
         JButton btnCargarServidor = new JButton("->");
         constraints.gridx = 2;
         constraints.gridy = 0;
         constraints.insets = new Insets (1,1,1,3);
         panelPrincipal.add(btnCargarServidor,constraints);
         
         JLabel lblFiltro = new JLabel("Filtro:");
         constraints.gridx = 0;
         constraints.gridy = 1;
         constraints.gridwidth = 1;
         constraints.gridheight = 1;
         panelPrincipal.add(lblFiltro,constraints);
         
         final JTextField txtFiltro= new JTextField("");
         txtFiltro.setPreferredSize(new Dimension(280,25));
         constraints.gridx = 1;
         constraints.gridy = 1;
         constraints.gridwidth = 2;
         constraints.gridheight = 1;
         constraints.insets = new Insets(1,1,1,3);
         panelPrincipal.add(txtFiltro,constraints);
                 
        final JTable tabla = new JTable(new ModeloTablaManga());
        tabla.setModel(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setAutoCreateRowSorter(true);
		JScrollPane scrollTabla = new JScrollPane(tabla);
		scrollTabla.setPreferredSize(new Dimension(350,200));
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.insets = new Insets(1,3,1,3);
		panelPrincipal.add(scrollTabla,constraints);
         
         JPanel panelInferior = new JPanel();
         constraints.gridx = 0;
         constraints.gridy = 3;
         constraints.gridwidth = 3;
         constraints.gridheight = 1;
         constraints.anchor = GridBagConstraints.CENTER;
         
         JButton btnVer = new JButton(new ImageIcon(getClass().getResource("/imagenes/info.png")));
         btnVer.setToolTipText("Ver Información");
         JButton btnAceptar = new JButton(new ImageIcon(getClass().getResource("/imagenes/aceptar.jpg")));
         btnAceptar.setToolTipText("Aceptar");
         JButton btnCerrar = new JButton(new ImageIcon(getClass().getResource("/imagenes/cerrar.png")));
         btnCerrar.setToolTipText("Cerrar");
         
         panelInferior.add(btnVer);
         panelInferior.add(btnAceptar);
         panelInferior.add(btnCerrar);
         
         panelPrincipal.add(panelInferior,constraints);
         
         setTitle("Selector de Manga");

         btnVer.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 if(tabla.getSelectedRow()>-1){
        			 
        			 new PanelInformacion();
        		 }else{
        			 JOptionPane.showMessageDialog(null, "Debe seleccionar un manga","Error",JOptionPane.ERROR_MESSAGE);
        			 txtFiltro.requestFocus();
        		 }
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

         btnCargarServidor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				txtFiltro.setText(null);
				Servidor servidor=null;
				if (cboServidor.getSelectedItem().toString()=="EsMangaHere"){
					servidor = new EsMangaHere();
				}else{
					servidor = new SubManga();
				}
				servidor.obtenerMangas(modelo);
			}
         });
        
         txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
             public void keyReleased(java.awt.event.KeyEvent evt) {
                 TableRowSorter gridFiltrado= new TableRowSorter(tabla.getModel());
                 gridFiltrado.setRowFilter(RowFilter.regexFilter(txtFiltro.getText(), 0));
                 tabla.setRowSorter(gridFiltrado);
             }
         });
         

         add(panelPrincipal);
         pack();
         setLocationRelativeTo(null);
         setResizable(false);
         setVisible(true);
         
 
	}
	
	
	
}
