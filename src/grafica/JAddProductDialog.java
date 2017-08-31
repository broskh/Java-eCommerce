package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

import negozio.Magazzino;

public class JAddProductDialog extends JDialog {
	
	private static final long serialVersionUID = -3173951653228607174L;

	//private Magazzino magazzino;
	
	protected static final String TITLE = "Aggiungi prodotto";
	protected static final int MIN_FRAME_HEIGHT = 410; //280
	protected static final int MIN_FRAME_WIDTH = 300; //300
	
	private JAddProductPanel jAddProductPanel;
	
	public JAddProductDialog(JFrame mainFrame,Magazzino magazzino)
	{
		super(mainFrame,TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.setModal(true);
		this.setSize(new Dimension(JAddProductDialog.MIN_FRAME_WIDTH,JAddProductDialog.
				MIN_FRAME_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		jAddProductPanel = new JAddProductPanel(magazzino,this);
		this.add(this.jAddProductPanel, BorderLayout.CENTER);
	}
	
}