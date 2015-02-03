package lectorManga;

import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodrigo
 */
public class Animextremist {
	
    public Animextremist(Visor padre,boolean modal){
    	
    }
    
    public void obtenerListas(String codigoFuente,JTable tabla){
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
        int cantFilas= modelo.getRowCount();
        
        //vaciar la tabla
        for(int i=cantFilas-1;i>=0;i--) modelo.removeRow(i);
        
        //volver a cargar
        String[] lista= new String[2];
       // String nuevoContenido="";
       while(codigoFuente.indexOf("\"manga\"")>-1){
            codigoFuente=codigoFuente.substring(codigoFuente.indexOf("\"manga\"")+2);
            codigoFuente=codigoFuente.substring(codigoFuente.indexOf("http"));
            lista[0]=codigoFuente.substring(codigoFuente.indexOf("http"),codigoFuente.indexOf("\">"));
            lista[1]=codigoFuente.substring(codigoFuente.indexOf("\">")+2,codigoFuente.indexOf("</a>"));
            modelo.addRow(lista);
        }
       //JOptionPane.showMessageDialog(null, direccion,"error",JOptionPane.ERROR_MESSAGE);
        
    }
}
