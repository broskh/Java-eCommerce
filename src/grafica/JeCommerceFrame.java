package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.GraphicsEnvironment;
//import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import negozio.Magazzino;
import utenza.Cliente;
import utenza.Utente;

public class JeCommerceFrame extends JFrame implements ComponentListener {
	private static final long serialVersionUID = 611728494453017801L;
	
	private Utente user;
	
	private JPanel contentPanel;
	
	private static final int STATUSBAR_HEIGHT = 22;	
	private static final int MINIMUM_JFRAME_HEIGHT = 650;
	private static final int MINIMUM_JFRAME_WIDTH = 800;

	private static final String TITLE = "Java-eCommerce";
	private static final String NAME_TEXT = "Nome utente:  ";
	private static final String TYPE_TEXT = "Tipo utente:  ";
	private static final String AUTHOR_TEXT = "Autore:  ";
	private static final String ADMIN_STRING = "Amministratore";
	private static final String CLIENT_STRING = "Cliente";
	private static final String AUTHOR = "Alessio Scheri";
 
	public JeCommerceFrame (Utente user, Magazzino store) {
		super (TITLE);
		this.user = user;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(MINIMUM_JFRAME_WIDTH, MINIMUM_JFRAME_HEIGHT));
		this.addComponentListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		if (this.user.isAmministratore()) {
			this.contentPanel = new JAdminContentPanel(this, store);
		}
		else {
			this.contentPanel = new JClientContentPanel(this, store, (Cliente) this.user);
		}
		
		this.setLayout(new BorderLayout());
		this.add(this.contentPanel, BorderLayout.CENTER);
		this.add(this.statusPanel (), BorderLayout.PAGE_END);
	}
	
	private JPanel statusPanel () {
		JLabel nameLabel = new JLabel(NAME_TEXT + this.user.getNome() + 
				" " + this.user.getCognome());
		JLabel typeLabel = new JLabel ();
		if (this.user.isAmministratore()) {
			typeLabel.setText(TYPE_TEXT + ADMIN_STRING);
		}
		else {
			typeLabel.setText(TYPE_TEXT + CLIENT_STRING);
		}
		JLabel authorLabel = new JLabel(AUTHOR_TEXT + AUTHOR);
		nameLabel.setBorder(new EtchedBorder());
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		typeLabel.setBorder(new EtchedBorder());
		typeLabel.setHorizontalAlignment(JLabel.CENTER);
		authorLabel.setBorder(new EtchedBorder());
		authorLabel.setHorizontalAlignment(JLabel.CENTER);

		JPanel statusPanel = new JPanel(new GridLayout(1,3));
		statusPanel.setPreferredSize(new Dimension(statusPanel.getWidth(), STATUSBAR_HEIGHT));
		statusPanel.add(nameLabel);
		statusPanel.add(typeLabel);
		statusPanel.add(authorLabel);
		return statusPanel;
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		;
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		;	
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if (!this.user.isAmministratore()) {
			((JClientContentPanel) this.contentPanel).resetPagina();
			((JClientContentPanel) this.contentPanel).updateArticles ();
		}		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		;	
	}
}