package grafica;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame; 
import javax.swing.JPanel;

public class JeCommerceFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String titolo = "Java-eCommerce";
//	private static final int altezzaDefault = 600;
//	private static final int larghezzaDefault = 800;
	
	private JPanel jMenuPanel;
	private JPanel jControlPanel;
	private JPanel jContentPanel;
	private JStatusPanel jStatusPanel;
 
	public JeCommerceFrame () {
		super (JeCommerceFrame.titolo);
//		this.setSize(JeCommerceFrame.larghezzaDefault, JeCommerceFrame.altezzaDefault);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.jMenuPanel = new JPanel ();
		this.jMenuPanel.setSize(1920, 10); //togliere
		this.jMenuPanel.setBackground(new Color(0, 0, 0)); //togliere
		this.jControlPanel = new JPanel ();
		this.jControlPanel.setSize(1920, 30); //togliere
		this.jControlPanel.setBackground(new Color(255, 255, 255)); //togliere
		this.jContentPanel = new JUserContentPanel();
		this.jStatusPanel = new JStatusPanel ();

		this.add(this.jMenuPanel, BorderLayout.PAGE_START);
		this.add(this.jControlPanel, BorderLayout.NORTH);
		this.add(this.jContentPanel, BorderLayout.CENTER);
		this.add(this.jStatusPanel, BorderLayout.SOUTH);
	}
}