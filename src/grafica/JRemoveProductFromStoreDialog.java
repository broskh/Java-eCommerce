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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import grafica.JAdminContentPanel.JStoreTable;

import negozio.Magazzino;
import negozio.Prodotto;

public class JRemoveProductFromStoreDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 8312477817724761485L;

	private Magazzino store;
	private HashSet <File> imagesToRemove;

//	private ArrayList<File> imageToDelete = new ArrayList<File>();
	
	private JStoreTable jStoreTable;
	private JComboBox <String> codeComboBox;
	private JButton deleteButton;
	
	private static final int FRAME_HEIGHT = 130;
	private static final int FRAME_WIDTH = 400;
	private static final int INTERACTIONPANEL_SPACE = 120;
	
	private static final int MARGIN = 20;
	private static final int INTERACTIONPANEL_TOP_MARGIN = 50;

	private static final String TITLE = "Elimina prodotto";
	private static final String ALERT_TITLE = "Operazione eseguita";
	private static final String STRING_LABEL_TEXT = "Seleziona il prodotto da eliminare:";
	private static final String DELETE_BUTTON_TEXT = "Elimina";
	private static final String ALERT_TEXT = "Prodotto eliminato correttamente";

//	public JRemoveProductFromStoreDialog(JFrame mainFrame,Magazzino magazzino,JStoreTable jStoreTable, ArrayList<File> imageToDelete)
	public JRemoveProductFromStoreDialog(JFrame mainFrame, Magazzino store, JStoreTable jStoreTable, HashSet <File> imagesToRemove) {
		super(mainFrame, TITLE, ModalityType.DOCUMENT_MODAL);
//		this.setModal(true);
		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));		
		this.setLocationRelativeTo(null);
		this.setResizable(false);

//		this.imageToDelete = imageToDelete;
		this.store = store;
		this.jStoreTable = jStoreTable;
		this.imagesToRemove = imagesToRemove;
		
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
		this.deleteButton = new JButton(DELETE_BUTTON_TEXT);
		this.deleteButton.addActionListener(this);
				
		JPanel interactionPanel = new JPanel();
		interactionPanel.add(this.codeComboBox);
		interactionPanel.add(Box.createHorizontalStrut(INTERACTIONPANEL_SPACE));
		interactionPanel.add(this.deleteButton);

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(label, BorderLayout.PAGE_START);
		contentPanel.add(Box.createRigidArea(new Dimension(contentPanel.getWidth(), INTERACTIONPANEL_TOP_MARGIN)), BorderLayout.CENTER);
		contentPanel.add(interactionPanel, BorderLayout.PAGE_END);
		return contentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.deleteButton)) {
			String code = (String) this.codeComboBox.getSelectedItem();
			Prodotto product = store.getProdotto(code);
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
}
