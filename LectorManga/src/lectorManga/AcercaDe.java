package lectorManga;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AcercaDe extends JDialog
implements ActionListener
{
	JButton cmdAceptar = new JButton("Aceptar");

	public AcercaDe(JFrame padre, boolean modal) { super(padre, modal);
	setTitle("Acerca de");
	setBounds(0, 0, 350, 150);
	setResizable(false);
	setLocationRelativeTo(null);
	setLayout(new GridLayout(2, 1));
	this.cmdAceptar.addActionListener(this);
	JPanel panelBoton = new JPanel();
	JLabel texto = new JLabel("<html> Programa creado por: Rocker13 <br> Version: 1.0 </html>");
	texto.setHorizontalAlignment(0);
	panelBoton.add(this.cmdAceptar);
	add(texto);
	add(panelBoton); }
	
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
	}
}
