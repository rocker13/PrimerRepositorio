package lectorManga;

 import java.awt.event.ActionEvent;
 import java.util.HashSet;
 import java.util.Set;
 import javax.swing.JComboBox;
 import javax.swing.KeyStroke;
 
 public class ComboStandar extends JComboBox
 {
   public ComboStandar()
   {
     Set forwardKeys = getFocusTraversalKeys(0);
     Set newforwardKeys = new HashSet(forwardKeys);
     newforwardKeys.add(KeyStroke.getKeyStroke(10, 0));
     setFocusTraversalKeys(0, newforwardKeys);
			
   }
			
			
 }
