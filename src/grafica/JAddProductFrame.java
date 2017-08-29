package grafica;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import negozio.Magazzino;

public class JAddProductFrame extends JFrame {
	
	private Magazzino magazzino;
	
	private static final long serialVersionUID = 1L;
	protected static final String TITOLO = "Aggiungi prodotto";
	protected static final int ALTEZZA_MINIMA_FRAME = 410; //280
	protected static final int LARGHEZZA_MINIMA_FRAME = 300; //300
	
	private JAddProductPanel jAddProductPanel;
	
	public JAddProductFrame(Magazzino magazzino)
	{
		super(JAddProductFrame.TITOLO);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.
		this.setSize(new Dimension(JAddProductFrame.LARGHEZZA_MINIMA_FRAME,JAddProductFrame.ALTEZZA_MINIMA_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		jAddProductPanel = new JAddProductPanel(magazzino);
		this.add(this.jAddProductPanel, BorderLayout.CENTER);
	}
	
}