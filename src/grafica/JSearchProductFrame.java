package grafica;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import negozio.Magazzino;


public class JSearchProductFrame extends JFrame{

	private static final long seralVersionUID = 1L;
	
	private Magazzino magazzino;
	protected static final String TITOLO = "Ricerca prodotto";
	protected static final int ALTEZZA_MINIMA_FRAME = 160; //200
	protected static final int LARGHEZZA_MINIMA_FRAME = 300; //400
	
	private JSearchProductPanel jSearchProductPanel;
	
	public JSearchProductFrame(Magazzino magazzino)
	{
		super(JSearchProductFrame.TITOLO);
		this.magazzino = magazzino;
		this.setSize(new Dimension(JSearchProductFrame.LARGHEZZA_MINIMA_FRAME,JSearchProductFrame.ALTEZZA_MINIMA_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		jSearchProductPanel = new JSearchProductPanel(magazzino,this);
		this.add(this.jSearchProductPanel, BorderLayout.CENTER);
	}
	

}
