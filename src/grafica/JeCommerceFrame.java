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
	
	private JMenuBar menuBar;
	private JPanel contentPanel;
	private JStatusPanel statusPanel;
	
	private Utente user;
	
	public static final int MENU_HEIGHT = 22;
	public static final int STATUSBAR_HEIGHT = 22;
	
	private static final int MINIMUM_JFRAME_HEIGHT = 650;
	private static final int MINIMUM_JFRAME_WIDTH = 800;

	private static final String TITLE = "Java-eCommerce";
 
	public JeCommerceFrame (Utente user, Magazzino store) {
		super (TITLE);
		this.user = user;
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		this.setSize(bounds.width, bounds.height);
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(MINIMUM_JFRAME_WIDTH, MINIMUM_JFRAME_HEIGHT));
		this.addComponentListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		if (this.user.isAmministratore()) {
			this.menuBar = new JAdminMenuBar(store);
			this.contentPanel = new JAdminContentPanel(store);
		}
		else {
			this.menuBar = new JClientMenuBar(this, store, ((Cliente) this.user).getCarrello());
			this.contentPanel = new JClientContentPanel(this, store, (Cliente) this.user);
		}
		this.statusPanel = new JStatusPanel (this.user);
		
		this.menuBar.setPreferredSize(new Dimension(this.getWidth(), MENU_HEIGHT));
		this.statusPanel.setPreferredSize(new Dimension(this.getWidth(), STATUSBAR_HEIGHT));

		this.setLayout(new BorderLayout());
		this.add(this.menuBar, BorderLayout.PAGE_START);
		this.add(this.contentPanel, BorderLayout.CENTER);
		this.add(this.statusPanel, BorderLayout.PAGE_END);
	}
	
	public JPanel getJContentPanel () {
		return this.contentPanel;
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
		if (!this.user.isAmministratore()) {
			((JClientContentPanel) this.contentPanel).resetPagina();;
			((JClientContentPanel) this.contentPanel).updateArticles ();
		}		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub		
	}
}