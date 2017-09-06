package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JSearchProductDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 2972527475553839904L;
	
	private Magazzino store;
	private HashSet <Prodotto> articlesAdded;
	private HashSet <File> imagesToRemove;

	private JStoreTable jStoreTable;
	private JFrame mainFrame;
	private JComboBox <String> codeComboBox;
	private JButton searchButton;

//	private ArrayList<File> imageToDeleteFromMod = new ArrayList<File>();
//	private ArrayList<Image> imageFromMod = new ArrayList<Image>();
//	private ArrayList<Prodotto> imageToSave = new ArrayList<Prodotto>();	
//	private ArrayList<Prodotto> imageToSaveFromMod = new ArrayList<Prodotto>();
	
	
	private static final int FRAME_HEIGHT = 130;
	private static final int FRAME_WIDTH = 400;
	private static final int INTERACTIONPANEL_SPACE = 120;
	
	private static final int MARGIN = 20;
	private static final int INTERACTIONPANEL_TOP_MARGIN = 50;

	private static final String TITLE = "Modifica prodotto";
	private static final String STRING_LABEL_TEXT = "Seleziona il prodotto da modificare:";
	private static final String SEARCH_BUTTON_TEXT = "Modifica";
	
	public JSearchProductDialog(JFrame mainFrame,Magazzino magazzino,JStoreTable jStoreTable,HashSet <Prodotto> articlesAdded, HashSet <File> imagesToRemove) {
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
		
		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(MARGIN), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(MARGIN), BorderLayout.WEST);
		this.add(this.contentPanel(), BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(MARGIN), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(MARGIN), BorderLayout.PAGE_END);
	}
	
	private JPanel contentPanel () {
		ArrayList <String> codici = new ArrayList<String>();
		for (Prodotto articolo : this.store.getArticoli()) {
			codici.add(articolo.getCodice());
		}
		this.codeComboBox = new JComboBox <String> (codici.toArray(
				new String[codici.size()]));
		JLabel label = new JLabel(STRING_LABEL_TEXT);
		label.setAlignmentX(SwingConstants.LEFT);
		this.searchButton = new JButton(SEARCH_BUTTON_TEXT);
		this.searchButton.addActionListener(this);
				
		JPanel interactionPanel = new JPanel();
		interactionPanel.add(this.codeComboBox);
		interactionPanel.add(Box.createHorizontalStrut(INTERACTIONPANEL_SPACE));
		interactionPanel.add(this.searchButton);

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(label, BorderLayout.PAGE_START);
		contentPanel.add(Box.createRigidArea(new Dimension(contentPanel.getWidth(), INTERACTIONPANEL_TOP_MARGIN)), BorderLayout.CENTER);
		contentPanel.add(interactionPanel, BorderLayout.PAGE_END);
		return contentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.searchButton)) {
			String code = (String) this.codeComboBox.getSelectedItem();
			Prodotto product = store.getProdotto(code);	
			JModifyProductDialog modifyProductDialog = new JModifyProductDialog(mainFrame, product, store, 
					this, jStoreTable, this.articlesAdded, this.imagesToRemove);
			modifyProductDialog.setVisible(true);
		}		
	}
}