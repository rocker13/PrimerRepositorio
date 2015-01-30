package lectorManga;

/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class Favoritos extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static JButton cmdVer;
/*     */   private static JButton cmdCerrar;
/*     */   private static JButton cmdBorrar;
/*     */   private static JButton cmdActualizar;
/*     */   private static JTable tabla;
/*  26 */   private static Dimension tBoton = new Dimension(100, 25);
/*  27 */   private static Modelo modelo = new Modelo();
/*     */   private static boolean verFavorito;
/*     */   private static Object manga;
/*     */   private static Object tomo;
/*  30 */   private static ArrayList<Object[][]> array = new ArrayList();
			private int posicion;
/*     */ 
/*     */   public Favoritos(JFrame padre, boolean modal)
/*     */   {
/*  34 */     super(padre, modal);
/*  35 */     setTitle("Favoritos");
/*  36 */     setBounds(0, 0, 500, 400);
/*  37 */     setResizable(false);
/*  38 */     verFavorito = false;
/*  39 */     manga = null;
/*  40 */     setLocationRelativeTo(null);
/*  41 */     JPanel panelTabla = new JPanel();
/*  42 */     JPanel panelBotones = new JPanel();
/*  43 */     cmdVer = new JButton("Ver");
/*  44 */     cmdVer.setPreferredSize(tBoton);
/*  45 */     cmdBorrar = new JButton("Borrar");
/*  46 */     cmdBorrar.setPreferredSize(tBoton);
/*  47 */     cmdActualizar = new JButton("Actualizar");
/*  48 */     cmdActualizar.setPreferredSize(tBoton);
/*  49 */     cmdCerrar = new JButton("Cerrar");
/*  50 */     cmdCerrar.setPreferredSize(tBoton);
/*  51 */     cmdVer.addActionListener(this);
/*  52 */     cmdCerrar.addActionListener(this);
/*  53 */     cmdBorrar.addActionListener(this);
/*  54 */     cmdActualizar.addActionListener(this);
/*  55 */     panelBotones.add(cmdVer);
/*  56 */     panelBotones.add(cmdBorrar);
/*  57 */     panelBotones.add(cmdActualizar);
/*  58 */     panelBotones.add(cmdCerrar);
/*  59 */     hidratar("listado.dat");
/*  60 */     modelo.setDatos(array);
/*  61 */     tabla = new JTable(modelo);
/*  62 */     tabla.getTableHeader().setReorderingAllowed(false);
/*  63 */     tabla.setAutoResizeMode(0);
/*  64 */     tabla.getColumnModel().getColumn(0).setPreferredWidth(297);
/*  65 */     tabla.getColumnModel().getColumn(1).setPreferredWidth(90);
/*  66 */     tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
/*  67 */     tabla.getColumnModel().getColumn(0).setResizable(false);
/*  68 */     tabla.getColumnModel().getColumn(1).setResizable(false);
/*  69 */     tabla.getColumnModel().getColumn(2).setResizable(false);
/*  70 */     JScrollPane scroll = new JScrollPane(tabla, 22, 31);
/*  71 */     setLayout(new BorderLayout());
/*  72 */     add(scroll, "Center");
/*  73 */     add(panelBotones, "South");
/*     */   }
/*     */   public void actionPerformed(ActionEvent evento) {
/*  76 */     if (evento.getSource() == cmdVer) {
/*  77 */       if (tabla.getSelectedRow() > -1) {
/*  78 */         manga = tabla.getValueAt(tabla.getSelectedRow(), 0);
/*  79 */         tomo = tabla.getValueAt(tabla.getSelectedRow(), 1);
/*  80 */         verFavorito = true;
/*  81 */         setVisible(false);
/*     */       } else {
/*  83 */         JOptionPane.showMessageDialog(null, "Debe seleccionar un manga primero", "Error", 0);
/*     */       }
/*  85 */     } else if (evento.getSource() == cmdBorrar) {
/*  86 */       if (tabla.getSelectedRow() > -1) {
/*  87 */         int resp = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el manga de la lista?", "Eliminar", 0, -1);
/*  88 */         if (resp == 0) {
/*  89 */           modelo.removeRow(tabla.getSelectedRow());
/*  90 */           repaint();
/*  91 */           serializar("listado.dat");
/*     */         }
/*     */       } else {
/*  94 */         JOptionPane.showMessageDialog(null, "Debe seleccionar un manga primero", "Error", 0);
/*     */       }
/*  96 */     } else if (evento.getSource() == cmdActualizar)
/*  97 */       JOptionPane.showMessageDialog(null, "Este boton aun no hace nada \n Espere la sgte version", "Error", 0);
/*     */     else
/*  99 */       setVisible(false);
/*     */   }
/*     */ 
/*     */   public void setDatos(Object col1, Object col2, Object col3)
/*     */   {
/* 104 */     boolean encontrado = false;
/*     */ 
/* 106 */     for (int i = 0; i < tabla.getRowCount(); i++) {
/* 107 */       if (col1.equals(tabla.getValueAt(i, 0))) {
/* 108 */         encontrado = true;
					posicion=i;
/* 109 */         break;
/*     */       }
/*     */     }
/* 112 */     if (!encontrado) {
/* 113 */       Object[][] objeto = { { col1, col2, col3 } };
/* 114 */       modelo.addRow(objeto);
/*     */     } else {
/* 116 */       int resultado = JOptionPane.showConfirmDialog(null, "El manga ya se encuentra en la lista. \n Desea actualizarla?", "Actualizar Favorito", 0, -1);
/* 117 */       if (resultado == 0) {
/* 118 */         Object[][] objeto = { { col1, col2, col3 } };
/* 119 */         modelo.setValueAt(objeto, posicion);
/* 120 */         modelo.setValueAt(objeto, posicion);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean getVerFavorito() {
/* 126 */     return verFavorito;
/*     */   }
/*     */   public void setVerFavorito(boolean vf) {
/* 129 */     verFavorito = vf;
/*     */   }
/*     */   public Object getManga() {
/* 132 */     return manga;
/*     */   }
/*     */   public Object getTomo() {
/* 135 */     return tomo;
/*     */   }
/*     */   public void serializar(String archivo) {
/*     */     try {
/* 139 */       ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo));
/* 140 */       array = modelo.getDatos();
/* 141 */       salida.writeObject(array);
/* 142 */       salida.close();
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/*     */   }
/*     */ 
/*     */   public void hidratar(String archivo) {
/*     */     try {
/* 150 */       ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo));
/* 151 */       array = (ArrayList)entrada.readObject();
/* 152 */       entrada.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/rodrigo/Descargas/LectorManga4.jar
 * Qualified Name:     Favoritos
 * JD-Core Version:    0.6.2
 */