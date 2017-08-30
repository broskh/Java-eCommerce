package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import negozio.Magazzino;

public class JSearchProductDialog extends JDialog{

	private static final long serialVersionUID = 2972527475553839904L;
	
	private Magazzino magazzino;
	
	protected static final String TITLE = "Ricerca prodotto";
	protected static final int MIN_HEIGHT_FRAME = 160; //200
	protected static final int MIN_WIDTH_FRAME = 300; //400
	
	private JSearchProductPanel jSearchProductPanel;
	
	public JSearchProductDialog(JFrame mainFrame,Magazzino magazzino)
	{
		super(mainFrame, TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.setModal(true);
		this.magazzino = magazzino;
		this.setSize(new Dimension(JSearchProductDialog.MIN_WIDTH_FRAME,
				JSearchProductDialog.MIN_HEIGHT_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		jSearchProductPanel = new JSearchProductPanel(mainFrame,magazzino,this);
		this.add(this.jSearchProductPanel, BorderLayout.CENTER);
	}
}