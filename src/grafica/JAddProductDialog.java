package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;

public class JAddProductDialog extends JDialog {
	
	private static final long serialVersionUID = -3173951653228607174L;

	//private Magazzino magazzino;
	private JStoreTable jStoreTable;
	
	protected static final String TITLE = "Aggiungi prodotto";
	protected static final int MIN_FRAME_HEIGHT = 410;
	protected static final int MIN_FRAME_WIDTH = 300;
	
	private JAddProductPanel jAddProductPanel;
	
	public JAddProductDialog(JFrame mainFrame,Magazzino magazzino,JStoreTable jStoreTable)
	{
		super(mainFrame,TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.setModal(true);
		this.setSize(new Dimension(JAddProductDialog.MIN_FRAME_WIDTH,JAddProductDialog.
				MIN_FRAME_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		jAddProductPanel = new JAddProductPanel(magazzino,this,jStoreTable);
		this.add(this.jAddProductPanel, BorderLayout.CENTER);
	}
	
}