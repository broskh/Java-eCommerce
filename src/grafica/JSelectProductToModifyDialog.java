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

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JSelectProductToModifyDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 2972527475553839904L;
	
	private Magazzino store;
	private HashSet <Prodotto> articlesAdded;
	private HashSet <File> imagesToRemove;

	private JStoreTable jStoreTable;
	private JFrame mainFrame;
	private JSelectProductPanel selectProductPanel;

//	private ArrayList<File> imageToDeleteFromMod = new ArrayList<File>();
//	private ArrayList<Image> imageFromMod = new ArrayList<Image>();
//	private ArrayList<Prodotto> imageToSave = new ArrayList<Prodotto>();	
//	private ArrayList<Prodotto> imageToSaveFromMod = new ArrayList<Prodotto>();
		
	private static final int FRAME_HEIGHT = 130;
	private static final int FRAME_WIDTH = 400;
	
	private static final int MARGIN = 20;

	private static final String TITLE = "Modifica prodotto";
	private static final String STRING_LABEL_TEXT = "Seleziona il prodotto da modificare:";
	private static final String SELECT_BUTTON_TEXT = "Modifica";
	
	public JSelectProductToModifyDialog(JFrame mainFrame,Magazzino magazzino,JStoreTable jStoreTable,HashSet <Prodotto> articlesAdded, HashSet <File> imagesToRemove) {
		super(mainFrame, TITLE, ModalityType.DOCUMENT_MODAL);
		this.store = magazzino;
		this.jStoreTable = jStoreTable;
		this.articlesAdded = articlesAdded;
		this.imagesToRemove = imagesToRemove;

//		this.imageToDeleteFromMod = imageToDeleteFromMod;
//		this.imageFromMod = imageFromMod;
//		this.imageToSave = imageToSave;
//		this.imageToSaveFromMod = imageToSaveFromMod;
		
		this.mainFrame = mainFrame;
		this.setModal(true);
		this.store = magazzino;
		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.selectProductPanel = new JSelectProductPanel(this.store.getArticoli());
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
		JModifyProductDialog modifyProductDialog = new JModifyProductDialog(mainFrame, product, store, 
				jStoreTable, this.articlesAdded, this.imagesToRemove);
		modifyProductDialog.setVisible(true);
	}
}