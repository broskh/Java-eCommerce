package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import negozio.Magazzino;

public class JDeleteProductDialog extends JDialog{

	private static final long serialVersionUID = 8312477817724761485L;

	private Magazzino magazzino;
	protected static final String TITLE = "Elimina prodotto";
	protected static final int MIN_HEIGHT_FRAME = 160; //200
	protected static final int MIN_WIDTH_FRAME = 300; //400
	
	private JDeleteProductPanel jDeleteProductPanel;
	
	public JDeleteProductDialog(JFrame mainFrame,Magazzino magazzino)
	{
		//JDialog.ModalityType.DOCUMENT_MODAL
		super(mainFrame,TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.setModal(true);
		this.magazzino = magazzino;
		this.setSize(new Dimension(JDeleteProductDialog.MIN_WIDTH_FRAME,
				JDeleteProductDialog.MIN_HEIGHT_FRAME));
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		jDeleteProductPanel = new JDeleteProductPanel(magazzino,this);
		this.add(this.jDeleteProductPanel, BorderLayout.CENTER);
	}
}
