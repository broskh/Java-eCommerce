package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import negozio.Magazzino;
import utenza.Cliente;
import utenza.Utente;

public class JeCommerceFrame extends JFrame implements ComponentListener {
	private static final long serialVersionUID = 611728494453017801L;
	
	private JMenuBar jMenuBar;
	private JPanel jContentPanel;
	private JStatusPanel jStatusPanel;
	
	private Utente utente;

	private static final String TITOLO = "Java-eCommerce";
	
	private static final int ALTEZZA_MENU = 22;
	private static final int ALTEZZA_STATUSBAR = 22;
	
	private static final int ALTEZZA_MINIMA_JFRAME = 600;
	private static final int LARGHEZZA_MINIMA_JFRAME = 800;
 
	public JeCommerceFrame (Utente utente, Magazzino magazzino) {
		super (TITOLO);
		this.utente = utente;
		this.setMinimumSize(new Dimension(LARGHEZZA_MINIMA_JFRAME, ALTEZZA_MINIMA_JFRAME));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.addComponentListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		if (this.utente.isAmministratore()) {
			this.jMenuBar = new JAdminMenuBar();
			this.jContentPanel = new JAdminContentPanel();
		}
		else {
			this.jMenuBar = new JClientMenuBar();
			this.jContentPanel = new JClientContentPanel(magazzino, (Cliente) this.utente);
		}
		this.jStatusPanel = new JStatusPanel (this.utente);
		
		this.jMenuBar.setPreferredSize(new Dimension(this.getWidth(), ALTEZZA_MENU));
		this.jStatusPanel.setPreferredSize(new Dimension(this.getWidth(), ALTEZZA_STATUSBAR));

		this.setLayout(new BorderLayout());
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

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if (!this.utente.isAmministratore()) {
			((JClientContentPanel) this.jContentPanel).aggiornaArticoli ();
		}		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub		
	}
}