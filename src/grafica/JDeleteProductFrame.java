package grafica;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import negozio.Magazzino;


public class JDeleteProductFrame extends JFrame{

	private static final long seralVersionUID = 1L;
	
	private Magazzino magazzino;
	protected static final String TITOLO = "Elimina prodotto";
	protected static final int ALTEZZA_MINIMA_FRAME = 160; //200
	protected static final int LARGHEZZA_MINIMA_FRAME = 300; //400
	
	private JDeleteProductPanel jDeleteProductPanel;
	
	public JDeleteProductFrame(Magazzino magazzino)
	{
		super(JDeleteProductFrame.TITOLO);
		this.magazzino = magazzino;
		this.setSize(new Dimension(JDeleteProductFrame.LARGHEZZA_MINIMA_FRAME,JDeleteProductFrame.ALTEZZA_MINIMA_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		jDeleteProductPanel = new JDeleteProductPanel(magazzino,this);
		this.add(this.jDeleteProductPanel, BorderLayout.CENTER);
	}
	

}
