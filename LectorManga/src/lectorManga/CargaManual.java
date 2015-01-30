package lectorManga;

/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class CargaManual extends JDialog
/*    */   implements ActionListener
/*    */ {
/*    */   private static JPanel panelTitulo;
/*    */   private static JPanel panelTexto;
/*    */   private static JPanel panelBotones;
/*    */   private static JButton cmdAceptar;
/*    */   private static JButton cmdCancelar;
/*    */   private static JLabel lblTitulo;
/*    */   private static JTextField txtDireccion;
/*    */   private static Imagen fpadre;
/*    */   public static String direccion;
/*    */   public static String manga;
/*    */ 
/*    */   public CargaManual(JFrame padre, boolean modal)
/*    */   {
/* 26 */     super(padre, modal);
/* 27 */     setTitle("Carga Manual");
/* 28 */     setBounds(0, 0, 500, 130);
/* 29 */     setResizable(false);
/* 30 */     setLocationRelativeTo(null);
/* 31 */     setLayout(new BorderLayout());
/* 32 */     lblTitulo = new JLabel("Escriba la direccion del manga a cargar:(submanga.com/Torre_de_Dios/completa)");
/* 33 */     txtDireccion = new textoStandar();
/* 34 */     txtDireccion.setPreferredSize(new Dimension(350, 25));
/* 35 */     cmdAceptar = new JButton("Aceptar");
/* 36 */     cmdAceptar.addActionListener(this);
/* 37 */     cmdCancelar = new JButton("Cancelar");
/* 38 */     cmdCancelar.addActionListener(this);
/* 39 */     panelTitulo = new JPanel();
/* 40 */     panelTexto = new JPanel();
/* 41 */     panelBotones = new JPanel();
/* 42 */     panelTitulo.add(lblTitulo);
/* 43 */     panelTexto.add(txtDireccion);
/* 44 */     panelBotones.add(cmdAceptar);
/* 45 */     panelBotones.add(cmdCancelar);
/* 46 */     add(panelTitulo, "North");
/* 47 */     add(panelTexto, "Center");
/* 48 */     add(panelBotones, "South");
/*    */   }
/*    */   public void actionPerformed(ActionEvent evento) {
/* 51 */     if (evento.getSource() == cmdAceptar) {
/* 52 */       direccion = txtDireccion.getText();
/* 53 */       if ((direccion.contains("/completa")) && (new Varios().revisarConexion(direccion))) {
/* 54 */         if (!direccion.contains("http://")) {
/* 55 */           direccion = "http://" + direccion;
/*    */         }
/* 57 */         manga = direccion.substring(20, direccion.indexOf("/completa"));
/* 58 */         setVisible(false);
/*    */       } else {
/* 60 */         JOptionPane.showMessageDialog(null, "Error en la direccion ingresada", "Error de direccion", 0);
/* 61 */         txtDireccion.requestFocus();
/*    */       }
/*    */     } else {
/* 64 */       direccion = "";
/* 65 */       setVisible(false);
/*    */     }
/* 67 */     txtDireccion.setText("");
/*    */   }
/*    */ }

/* Location:           /home/rodrigo/Descargas/LectorManga4.jar
 * Qualified Name:     CargaManual
 * JD-Core Version:    0.6.2
 */