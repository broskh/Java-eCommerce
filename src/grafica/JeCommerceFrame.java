package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import utenza.Utente;

public class JeCommerceFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected static final String TITOLO = "Java-eCommerce";
	
	protected static final int ALTEZZA_MENU = 22;
	protected static final int ALTEZZA_STATUSBAR = 22;	
	protected static final int ALTEZZA_CONTROL_BAR = 80;
	
	protected static final int ALTEZZA_MINIMA_JFRAME = 600;
	protected static final int LARGHEZZA_MINIMA_JFRAME = 800;
	
	private JMenuBar jMenuBar;
	private JPanel jContentPanel;
	private JStatusPanel jStatusPanel;
 
	public JeCommerceFrame (Utente utente) {
		super (JeCommerceFrame.TITOLO);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(JeCommerceFrame.LARGHEZZA_MINIMA_JFRAME, JeCommerceFrame.ALTEZZA_MINIMA_JFRAME));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

		if (utente.isAmministratore()) {
			this.jMenuBar = new JAdminMenuBar();
			this.jContentPanel = new JAdminContentPanel();
		}
		else {
			this.jMenuBar = new JClientMenuBar();
			this.jContentPanel = new JClientContentPanel();
		}
		this.jStatusPanel = new JStatusPanel (utente);
		
		this.jMenuBar.setPreferredSize(new Dimension(this.getWidth(), JeCommerceFrame.ALTEZZA_MENU));
		this.jStatusPanel.setPreferredSize(new Dimension(this.getWidth(), JeCommerceFrame.ALTEZZA_STATUSBAR));

		this.add(this.jMenuBar, BorderLayout.PAGE_START);
		this.add(this.jContentPanel, BorderLayout.CENTER);
		this.add(this.jStatusPanel, BorderLayout.PAGE_END);
	}
	
//	public void changeMenu (Utente utente) {
//		if (utente.isAmministratore()) {
//			this.jMenuBar = new JAdminMenuBar ();
//		}
//		else {
//			this.jMenuBar = new JClientMenuBar ();
//		}
//		this.remove(this.jMenuBar);
//		this.add(this.jMenuBar, BorderLayout.PAGE_START);
//	}
}