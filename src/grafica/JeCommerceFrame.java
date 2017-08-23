package grafica;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
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
	private Magazzino magazzino;

	private static final String TITOLO = "Java-eCommerce";
	
	public static final int ALTEZZA_MENU = 22;
	public static final int ALTEZZA_STATUSBAR = 22;
	
	private static final int ALTEZZA_MINIMA_JFRAME = 650;
	private static final int LARGHEZZA_MINIMA_JFRAME = 800;
 
	public JeCommerceFrame (Utente utente, Magazzino magazzino) {
		super (TITOLO);
		this.utente = utente;
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		this.setSize(bounds.width, bounds.height);
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(LARGHEZZA_MINIMA_JFRAME, ALTEZZA_MINIMA_JFRAME));
		this.addComponentListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		if (this.utente.isAmministratore()) {
			this.jMenuBar = new JAdminMenuBar(magazzino);
			this.jContentPanel = new JAdminContentPanel(magazzino);
		}
		else {
			this.jMenuBar = new JClientMenuBar(magazzino, ((Cliente) this.utente).getCarrello());
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
	
	public JPanel getJContentPanel () {
		return this.jContentPanel;
	}

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
			((JClientContentPanel) this.jContentPanel).resetPagina();;
			((JClientContentPanel) this.jContentPanel).aggiornaArticoli ();
		}		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub		
	}
}