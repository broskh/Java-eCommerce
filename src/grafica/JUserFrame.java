package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		this.setSize(new Dimension(JUserFrame.LARGHEZZA_MINIMA_FRAME, JUserFrame.ALTEZZA_MINIMA_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);	//imposto che non si pu� ne allargare ne rimpicciolire
		this.setLayout(new BorderLayout());
		this.JContentPanel = new JUserContentPanel();
		this.add(this.JContentPanel, BorderLayout.CENTER);
	}
}
