package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import negozio.Magazzino;
import negozio.Prodotto;

/**
 * JDialog per l'aggiunta di un prodotto al magazzino.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class JAddProductToStoreDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = -3173951653228607174L;
	
	private Magazzino store;
	private Prodotto product;

	private JFrame mainFrame;
	private JEditProductPanel modifyProductPanel;
	private ProductsTableModel storeTableModel;

	protected static final int FRAME_HEIGHT = 510;
	protected static final int FRAME_WIDTH = 300;
	
	private static final int MARGIN = 15;
	
	private static final String BUTTON_TEXT = "Aggiungi";
	private static final String ALERT_ERROR_TITLE = "Attenzione!";
	private static final String ALERT_SUCCESS_TITLE = "Aggiunta avvenuta";
	private static final String EMPTY_FIELDS_TEXT = "Inserire tutti i dati correttamente.";
	private static final String NO_COST_TEXT = "Inserire un prezzo diverso da 0.";
	private static final String EXSISTING_PRODUCT_TEXT = 
			"È già presente nel magazzino un prodotto con questo codice.";
	private static final String SUCCESS_TEXT = "Aggiunta effettuata con successo.";	
	protected static final String TITLE = "Aggiungi prodotto";
	
	public JAddProductToStoreDialog (JFrame mainFrame, Magazzino store, ProductsTableModel storeTableModel) {
		super(mainFrame, TITLE, ModalityType.DOCUMENT_MODAL);
		this.store = store;
		this.product = new Prodotto();
		this.mainFrame = mainFrame;
		this.storeTableModel = storeTableModel;

		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.modifyProductPanel = new JEditProductPanel(this.product);
		this.modifyProductPanel.setButtonText(BUTTON_TEXT);
		this.modifyProductPanel.setButtonActionListener(this);

		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(MARGIN), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(MARGIN), BorderLayout.WEST);
		this.add(this.modifyProductPanel, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(MARGIN), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(MARGIN), BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.modifyProductPanel.getProductName().equals("") && 
				!this.modifyProductPanel.getProductCode().equals("") &&
				!this.modifyProductPanel.getProductBrand().equals("") &&
				!this.modifyProductPanel.getProductCategory().equals("")) {
			if (this.modifyProductPanel.getProductCost() == 0) {
				JOptionPane.showMessageDialog(this, NO_COST_TEXT,
						ALERT_ERROR_TITLE,JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (this.store.getProdotto(this.modifyProductPanel.getProductCode()) != null) {
				JOptionPane.showMessageDialog(this, EXSISTING_PRODUCT_TEXT,
						ALERT_ERROR_TITLE,JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.product.setImmagine(this.modifyProductPanel.getProductImage());
			this.product.setCodice(this.modifyProductPanel.getProductCode());
			this.product.setNome(this.modifyProductPanel.getProductName());
			this.product.setCategoria(this.modifyProductPanel.getProductCategory());
			this.product.setMarca(this.modifyProductPanel.getProductBrand());
			this.product.setPrezzo(this.modifyProductPanel.getProductCost());
			this.product.setQuantita(this.modifyProductPanel.getProductAmount());
			this.product.setOfferta(this.modifyProductPanel.getProductOffer());
			this.store.aggiungiProdotto(this.product);
			this.storeTableModel.fireTableDataChanged();
			this.dispose ();
			JOptionPane.showMessageDialog(this.mainFrame, SUCCESS_TEXT,
					ALERT_SUCCESS_TITLE,JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this, EMPTY_FIELDS_TEXT,
					ALERT_ERROR_TITLE,JOptionPane.ERROR_MESSAGE);
		}
	}
}