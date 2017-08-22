package grafica;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JAddProductFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	protected static final String TITOLO = "Aggiungi prodotto";
	protected static final int ALTEZZA_MINIMA_FRAME = 200;
	protected static final int LARGHEZZA_MINIMA_FRAME = 400;
	
	private JAddProductPanel jAddProductPanel;
	
	public JAddProductFrame()
	{
		super(JAddProductFrame.TITOLO);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.
		this.setSize(new Dimension(JAddProductFrame.LARGHEZZA_MINIMA_FRAME,JAddProductFrame.ALTEZZA_MINIMA_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		jAddProductPanel = new JAddProductPanel();
		this.add(this.jAddProductPanel, BorderLayout.CENTER);
	}
	
}