package lectorManga;

/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.swing.JTextField;
/*    */ import javax.swing.KeyStroke;
/*    */ 
/*    */ public class textoStandar extends JTextField
/*    */ {
/*    */   public textoStandar()
/*    */   {
/* 21 */     Set forwardKeys = getFocusTraversalKeys(0);
/* 22 */     Set newforwardKeys = new HashSet(forwardKeys);
/* 23 */     newforwardKeys.add(KeyStroke.getKeyStroke(10, 0));
/* 24 */     setFocusTraversalKeys(0, newforwardKeys);
/*    */   }
/*    */ }

/* Location:           /home/rodrigo/Descargas/LectorManga4.jar
 * Qualified Name:     textoStandar
 * JD-Core Version:    0.6.2
 */