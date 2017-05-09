package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utenza.Amministratore;
import utenza.Cliente;
import utenza.Utente;
import grafica.JUserContentPanel;

public class JUserFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	protected static final String TITOLO = "Login";
	
	protected static final int ALTEZZA_MINIMA_FRAME = 200;
	protected static final int LARGHEZZA_MINIMA_FRAME = 400;
	
	private JPanel JContentPanel;
	
	public JUserFrame()
	{
		super(JUserFrame.TITOLO);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(JUserFrame.LARGHEZZA_MINIMA_FRAME, JUserFrame.ALTEZZA_MINIMA_FRAME));
		this.setResizable(false);	//imposto che non si può ne allargare ne rimpicciolire
		this.setLayout(new BorderLayout());
		this.JContentPanel = new JUserContentPanel();
		this.add(this.JContentPanel, BorderLayout.CENTER);	
		
	}
}
