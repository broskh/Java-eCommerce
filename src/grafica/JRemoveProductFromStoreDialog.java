package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import grafica.JAdminContentPanel.JStoreTable;

import negozio.Magazzino;
import negozio.Prodotto;

public class JRemoveProductFromStoreDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 8312477817724761485L;

	private Magazzino store;
	private HashSet <File> imagesToRemove;

//	private ArrayList<File> imageToDelete = new ArrayList<File>();
	
	private JSelectProductPanel selectProductPanel;
	private JStoreTable jStoreTable;
	
	private static final int FRAME_HEIGHT = 130;
	private static final int FRAME_WIDTH = 400;
	
	private static final int MARGIN = 20;

	private static final String TITLE = "Elimina prodotto";
	private static final String ALERT_TITLE = "Operazione eseguita";
	private static final String STRING_LABEL_TEXT = "Seleziona il prodotto da eliminare:";
	private static final String DELETE_BUTTON_TEXT = "Elimina";
	private static final String ALERT_TEXT = "Prodotto eliminato correttamente";

	public JRemoveProductFromStoreDialog(JFrame mainFrame, Magazzino store, JStoreTable jStoreTable, HashSet <File> imagesToRemove) {
		super(mainFrame, TITLE, ModalityType.DOCUMENT_MODAL);
		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));		
		this.setLocationRelativeTo(null);
		this.setResizable(false);

//		this.imageToDelete = imageToDelete;
		this.store = store;
		this.jStoreTable = jStoreTable;
		this.imagesToRemove = imagesToRemove;
		
		this.selectProductPanel = new JSelectProductPanel(this.store.getArticoli());
		this.selectProductPanel.setLabelText(STRING_LABEL_TEXT);
		this.selectProductPanel.setButtonText(DELETE_BUTTON_TEXT);
		this.selectProductPanel.setActionListener(this);
		
		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(MARGIN), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(MARGIN), BorderLayout.WEST);
		this.add(this.selectProductPanel, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(MARGIN), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(MARGIN), BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Prodotto product = store.getProdotto(this.selectProductPanel.getSelectedCode());
		if (!product.getImmagine().equals(Prodotto.IMMAGINE_DEFAULT)) {
			this.imagesToRemove.add(product.getImmagine());
		}
		store.rimuoviProdotto(product.getCodice(), product.getQuantita());
		JOptionPane.showMessageDialog(this, ALERT_TEXT,ALERT_TITLE,
				JOptionPane.INFORMATION_MESSAGE);			
		((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
		this.setVisible(false);	 
	}
}
