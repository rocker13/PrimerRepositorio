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

public abstract class SelectorManga extends JDialog{
	String direccion;
	final Visor padre;
	public SelectorManga(Visor padre,boolean modal,String direccion){
		 super(padre,modal);
		 this.padre=padre;
		 this.direccion=direccion;
		 JTable tabla = new JTable(new DefaultTableModel(new String[][]{{"asdf","asdf"},{"asdf","asdf"}},new String[]{"Direccion","Mangas"}));
         tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         
         JLabel filtro = new JLabel("Filtro: ");
         JTextField texto= new JTextField("");
        
         obtenerListas(obtenerCodigoFuente(),tabla);
         JTextArea descripcion = new JTextArea();
         descripcion.setLineWrap(true);
         
         JScrollPane scrollTabla = new JScrollPane(tabla);
         JScrollPane scrollDescripcion = new JScrollPane(descripcion,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         
         JPanel panelPrincipal = new JPanel();
         JPanel panelSuperior = new JPanel();
         JPanel panelSupIzq = new JPanel();
         JPanel panelSupDer = new JPanel();
         JPanel panelIzquierdo = new JPanel();
         JPanel panelDerecho = new JPanel();
         JPanel panelInferior = new JPanel();
         
         JButton btnVer = new JButton("Ver Informacion");
         JButton btnAceptar = new JButton("Aceptar");
         JButton btnCerrar = new JButton("Cerrar");
         
       
         
         texto.setPreferredSize(new Dimension(300,25));
         scrollDescripcion.setPreferredSize(new Dimension(300,200));
         scrollTabla.setPreferredSize(new Dimension(350,200));
         
         panelSuperior.setLayout(new BorderLayout());
         panelPrincipal.setLayout(new BorderLayout());
         
         panelIzquierdo.add(scrollTabla);
         panelDerecho.add(scrollDescripcion);
         panelSupIzq.add(filtro);
         panelSupIzq.add(texto);
         
         panelSuperior.add(panelSupIzq,BorderLayout.WEST);
         panelSuperior.add(panelSupDer,BorderLayout.EAST);
         
         panelInferior.add(btnVer);
         panelInferior.add(btnAceptar);
         panelInferior.add(btnCerrar);
         
         panelPrincipal.add(panelSuperior,BorderLayout.NORTH);
         panelPrincipal.add(panelIzquierdo,BorderLayout.WEST);
         panelPrincipal.add(panelDerecho,BorderLayout.EAST);
         panelPrincipal.add(panelInferior,BorderLayout.SOUTH);
         
         setTitle("Animextremist");

         btnVer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//this.padre.agregarPestañas();
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
	
	public String obtenerCodigoFuente(){
        StringBuilder contenido = new StringBuilder();
        try{
            URL url = new URL(direccion);
            URLConnection uc =  url.openConnection();
            uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)");
            uc.connect();
		   
            InputStream is=uc.getInputStream();
		    
		    
            //Creamos el objeto con el que vamos a leer
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contenido.append(inputLine + "\n");
            }
            in.close();
        }catch(MalformedURLException e){
            JOptionPane.showMessageDialog(null, "hola","error",JOptionPane.ERROR_MESSAGE);
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "hol1a","error",JOptionPane.ERROR_MESSAGE);
        }
        return contenido.toString();
    }
	
	public abstract void obtenerListas(String codigoFuente,JTable tabla);
}
