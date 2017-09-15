package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;

import negozio.Magazzino;
import negozio.Prodotto;

public class JSelectProductToModifyDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 2972527475553839904L;
	
	private Magazzino store;

	private JFrame mainFrame;
	private ProductsTableModel storeTableModel;
	private JSelectProductPanel selectProductPanel;
		
	private static final int FRAME_HEIGHT = 130;
	private static final int FRAME_WIDTH = 400;
	
	private static final int MARGIN = 15;

	private static final String TITLE = "Seleziona prodotto";
	private static final String STRING_LABEL_TEXT = "Seleziona il prodotto da modificare:";
	private static final String SELECT_BUTTON_TEXT = "Seleziona";
	
	public JSelectProductToModifyDialog(JFrame mainFrame, Magazzino store, 
			ProductsTableModel storeTableModel) {
		super(mainFrame, TITLE, ModalityType.DOCUMENT_MODAL);
		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.store = store;
		this.mainFrame = mainFrame;
		this.storeTableModel = storeTableModel;
				
		this.selectProductPanel = new JSelectProductPanel(this.store.getProdotti());
		this.selectProductPanel.setLabelText(STRING_LABEL_TEXT);
		this.selectProductPanel.setButtonText(SELECT_BUTTON_TEXT);
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
		this.dispose();
		Prodotto product = this.store.getProdotto(this.selectProductPanel.getSelectedCode());	
		JModifyProductDialog modifyProductDialog = new JModifyProductDialog(this.mainFrame, 
				product, this.store, this.storeTableModel);
		modifyProductDialog.setVisible(true);
	}
}