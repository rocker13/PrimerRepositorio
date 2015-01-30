package lectorManga;

/*    */ import java.net.URL;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ public class Hilo extends Thread
/*    */ {
/*    */   private ImageIcon[] icono1;
/*    */   private int contador;
/*    */   private String direcImagen;
/*    */ 
/*    */   Hilo(int cont, ImageIcon[] icon, String direccion)
/*    */   {
/* 20 */     this.icono1 = icon;
/* 21 */     this.direcImagen = direccion;
/* 22 */     this.contador = cont;
/* 23 */     start();
/*    */   }
/*    */ 
/*    */   public void run() {
/*    */     try {
/* 28 */       for (int i = 0; i < this.contador; i++) {
/* 29 */         int a = i + 1;
/*    */ 
/* 31 */         this.icono1[i] = new ImageIcon(new URL("http://" + this.direcImagen + a + ".jpg"));
/*    */       }
/*    */     }
/*    */     catch (Exception ex)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/rodrigo/Descargas/LectorManga4.jar
 * Qualified Name:     Hilo
 * JD-Core Version:    0.6.2
 */