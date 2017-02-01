package grafica;

import java.awt.GridLayout;

import javax.swing.JFrame; 
import javax.swing.JPanel;

public class JeCommerceFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String titolo = "Java-eCommerce";
	
	private JPanel jMenuPanel;
	private JPanel jControlPanel;
	private JPanel jContentPanel;
	private JPanel jStatusPanel;
 
	public JeCommerceFrame () {
		super (JeCommerceFrame.titolo);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(4, 2));

		this.jMenuPanel = new JPanel ();
		this.jControlPanel = new JPanel ();
		this.jContentPanel = new JPanel (); //Deve contenere scelta utente all'inizio
		this.jStatusPanel = new JPanel ();

		this.add(this.jMenuPanel);
		this.add(this.jControlPanel);
		this.add(this.jContentPanel);
		this.add(this.jStatusPanel);
	}
}