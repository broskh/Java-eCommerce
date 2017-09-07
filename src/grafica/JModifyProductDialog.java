package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.HashSet;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JModifyProductDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -5456722239846991776L;
	
	private Prodotto product;
	private Magazzino store;
	private HashSet <Prodotto> articlesAdded;
	private HashSet <File> imagesToRemove;

	private JStoreTable jStoreTable;
	private JModifyProductPanel modifyProductPanel;
	
	private static final String TITLE = "Modifica prodotto";
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 300;

	private static final String BUTTON_TEXT = "MODIFICA";
	private static final String ALERT_ERROR_TITLE = "Attenzione!";
	private static final String ALERT_SUCCESS_TITLE = "Modifica avvenuta";
	private static final String EMPTY_FIELDS_TEXT = "Inserire tutti i dati correttamente.";
	private static final String NO_COST_TEXT = 
			"Inserire un prezzo diverso da 0.";
	private static final String EXSISTING_PRODUCT_TEXT = "È già presente nel magazzino un prodotto con questo codice.";
	private static final String SUCCESS_TEXT = "Modifica effettuata con successo";
	
	
	
	public JModifyProductDialog(JFrame mainFrame,Prodotto prodotto, Magazzino magazzino,
			JStoreTable jStoreTable, HashSet <Prodotto> articlesAdded, HashSet <File> imagesToRemove)
	{
		super(mainFrame,TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.setModal(true);
		
		this.product = prodotto;
		this.store = magazzino;
		this.jStoreTable = jStoreTable;
		this.articlesAdded = articlesAdded;
		this.imagesToRemove = imagesToRemove;
		
		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.modifyProductPanel = new JModifyProductPanel(this.product);
		this.modifyProductPanel.setButtonText(BUTTON_TEXT);
		this.modifyProductPanel.setButtonActionListener(this);

		this.setLayout(new BorderLayout());
		this.add(this.modifyProductPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.modifyProductPanel.getProductName().equals("") && 
				!this.modifyProductPanel.getProductCode().equals("") &&
				!this.modifyProductPanel.getProductBrand().equals("") &&
				!this.modifyProductPanel.getProductCategory().equals("")) {
			if (this.modifyProductPanel.getProductCost() == 0) {
				JOptionPane.showMessageDialog(this, NO_COST_TEXT,
						ALERT_ERROR_TITLE,JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (this.store.getProdotto(this.modifyProductPanel.getProductCode()) != null) {
				JOptionPane.showMessageDialog(this, EXSISTING_PRODUCT_TEXT,
						ALERT_ERROR_TITLE,JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			this.imagesToRemove.add(this.product.getImmagine());
			this.product.setImmagine(this.modifyProductPanel.getImmagine());
			this.product.setCodice(this.modifyProductPanel.getProductCode());
			this.product.setNome(this.modifyProductPanel.getProductName());
			this.product.setCategoria(this.modifyProductPanel.getProductCategory());
			this.product.setMarca(this.modifyProductPanel.getProductBrand());
			this.product.setPrezzo(this.modifyProductPanel.getProductCost());
			this.product.setQuantita(this.modifyProductPanel.getProductAmount());
			this.product.setOfferta(this.modifyProductPanel.getProductOffer());
			this.articlesAdded.add(this.product);
			((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
			
			JOptionPane.showMessageDialog(this, SUCCESS_TEXT,
					ALERT_SUCCESS_TITLE,JOptionPane.INFORMATION_MESSAGE);
			this.dispose ();
		}
		else {
			JOptionPane.showMessageDialog(this, EMPTY_FIELDS_TEXT,
					ALERT_ERROR_TITLE,JOptionPane.INFORMATION_MESSAGE);
		}
	}
}