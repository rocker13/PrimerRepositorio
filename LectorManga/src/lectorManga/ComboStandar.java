package lectorManga;

/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.KeyStroke;
/*    */ 
/*    */ public class ComboStandar extends JComboBox
/*    */ {
/*    */   public ComboStandar()
/*    */   {
/* 28 */     Set forwardKeys = getFocusTraversalKeys(0);
/* 29 */     Set newforwardKeys = new HashSet(forwardKeys);
/* 30 */     newforwardKeys.add(KeyStroke.getKeyStroke(10, 0));
/* 31 */     setFocusTraversalKeys(0, newforwardKeys);
/*    */   }
/*    */ }

/* Location:           /home/rodrigo/Descargas/LectorManga4.jar
 * Qualified Name:     ComboStandar
 * JD-Core Version:    0.6.2
 */