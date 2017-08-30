package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import negozio.Magazzino;
import negozio.Prodotto;

public class JModifyProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	
	private JSearchProductDialog jSearchProductDialog;
	private Prodotto prodotto;
	private Magazzino magazzino;
	
	protected static final String TITLE = "Modifica prodotto";
	protected static final int MIN_HEIGHT_FRAME = 410; //200
	protected static final int MIN_WIDTH_FRAME = 300; //400
	
	private JModifyProductPanel jModifyProductPanel;
	
	
	public JModifyProductDialog(JFrame mainFrame,Prodotto prodotto, Magazzino magazzino,
			JSearchProductDialog jSearchProductDialog)
	{
		super(mainFrame,TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.setModal(true);
		this.jSearchProductDialog = jSearchProductDialog;
		this.jSearchProductDialog.setVisible(false);
		
		this.prodotto = prodotto;
		this.magazzino = magazzino;
		this.setSize(new Dimension(JModifyProductDialog.MIN_WIDTH_FRAME,
				JModifyProductDialog.MIN_HEIGHT_FRAME));
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		jModifyProductPanel = new JModifyProductPanel(this,prodotto,magazzino);
		this.add(this.jModifyProductPanel, BorderLayout.CENTER);
	}
}