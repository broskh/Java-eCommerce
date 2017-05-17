package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import grafica.JUserContentPanel;

public class JUserFrame extends JFrame{
	private static final long serialVersionUID = 979362510733265067L;
	
	protected JUserContentPanel jContentPanel;

	protected static final String TITOLO = "Login";
	
	protected static final int ALTEZZA_MINIMA_FRAME = 200;
	protected static final int LARGHEZZA_MINIMA_FRAME = 400;
	
	public JUserFrame()
	{
		super(JUserFrame.TITOLO);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(JUserFrame.LARGHEZZA_MINIMA_FRAME, JUserFrame.ALTEZZA_MINIMA_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);	//imposto che non si pu� ne allargare ne rimpicciolire
		this.setLayout(new BorderLayout());
		this.jContentPanel = new JUserContentPanel();
		this.add(this.jContentPanel, BorderLayout.CENTER);
	}
	
	public void setAccessListener (ActionListener asctionListener) {
		this.jContentPanel.jOkButton.addActionListener(asctionListener);
	}
}