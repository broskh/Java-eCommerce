package grafica;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import negozio.Magazzino;
import negozio.Prodotto;


public class JModifyProductFrame extends JFrame {

	private JSearchProductFrame jSearchProductFrame;
	private Prodotto prodotto;
	private Magazzino magazzino;
	private static final long serialVersionUID = 1L;
	protected static final String TITOLO = "Modifica prodotto";
	protected static final int ALTEZZA_MINIMA_FRAME = 410; //200
	protected static final int LARGHEZZA_MINIMA_FRAME = 300; //400
	
	private JModifyProductPanel jModifyProductPanel;
	
	
	public JModifyProductFrame(Prodotto prodotto, Magazzino magazzino,JSearchProductFrame jSearchProductFrame)
	{
		super(JModifyProductFrame.TITOLO);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.
		this.jSearchProductFrame = jSearchProductFrame;
		this.jSearchProductFrame.setVisible(false);
		
		this.prodotto = prodotto;
		this.magazzino = magazzino;
		this.setSize(new Dimension(JModifyProductFrame.LARGHEZZA_MINIMA_FRAME,JModifyProductFrame.ALTEZZA_MINIMA_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		jModifyProductPanel = new JModifyProductPanel(prodotto,magazzino);
		this.add(this.jModifyProductPanel, BorderLayout.CENTER);
	}
}
