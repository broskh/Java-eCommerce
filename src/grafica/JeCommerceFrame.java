package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import utenza.Utente;

public class JeCommerceFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String titolo = "Java-eCommerce";
//	private static final int altezzaDefault = 600;
//	private static final int larghezzaDefault = 800;
	
	private JMenuBar jMenuBar;
//	private JPanel jControlPanel;
	private JPanel jContentPanel;
	private JStatusPanel jStatusPanel;
 
	public JeCommerceFrame () {
		super (JeCommerceFrame.titolo);
//		this.setSize(JeCommerceFrame.larghezzaDefault, JeCommerceFrame.altezzaDefault);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.jMenuBar = new JMenuBar ();
		this.jMenuBar.setPreferredSize(new Dimension(this.getWidth(), 20));
//		this.jControlPanel = new JPanel ();
		this.jContentPanel = new JUserContentPanel();
		this.jStatusPanel = new JStatusPanel ();

		this.add(this.jMenuBar, BorderLayout.PAGE_START);
//		this.add(this.jControlPanel, BorderLayout.NORTH);
		this.add(this.jContentPanel, BorderLayout.CENTER);
		this.add(this.jStatusPanel, BorderLayout.SOUTH);
	}
	
	public void changeMenu (Utente utente) {
		if (utente.isAmministratore()) {
			this.jMenuBar = new JAdminMenuBar ();
		}
		else {
			this.jMenuBar = new JClientMenuBar ();
		}
	}
}