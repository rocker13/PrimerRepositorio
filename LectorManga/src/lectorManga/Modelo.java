package lectorManga;

/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class Modelo extends AbstractTableModel
/*    */   implements Serializable
/*    */ {
/*    */   private ArrayList<Object[][]> arrayDatos;
/* 18 */   private String[] titulos = { "Manga", "Ultimo Visto", "Total de Tomos" };
/*    */ 
/*    */   public Modelo()
/*    */   {
/* 23 */     this.arrayDatos = new ArrayList();
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 27 */     return this.arrayDatos.size();
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 31 */     return this.titulos.length;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int col) {
/* 35 */     return this.titulos[col];
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int fila, int col) {
/* 39 */     return ((Object[][])this.arrayDatos.get(fila))[0][col];
/*    */   }
/*    */ 
/*    */   public void addRow(Object[][] objeto) {
/* 43 */     this.arrayDatos.add(objeto);
/*    */   }
/*    */ 
/*    */   public void removeRow(int fila) {
/* 47 */     this.arrayDatos.remove(fila);
/*    */   }
/*    */ 
/*    */   public void setValueAt(Object[][] objeto, int fila) {
/* 51 */     this.arrayDatos.set(fila, objeto);
/*    */   }
/*    */ 
/*    */   public void setDatos(ArrayList<Object[][]> array) {
/* 55 */     this.arrayDatos = array;
/*    */   }
/*    */ 
/*    */   public ArrayList<Object[][]> getDatos() {
/* 59 */     return this.arrayDatos;
/*    */   }
/*    */ }

/* Location:           /home/rodrigo/Descargas/LectorManga4.jar
 * Qualified Name:     Modelo
 * JD-Core Version:    0.6.2
 */