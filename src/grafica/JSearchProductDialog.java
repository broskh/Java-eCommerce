package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JSearchProductDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 2972527475553839904L;
	
	private JStoreTable jStoreTable;
	private Magazzino magazzino;
	
	private static final String TITLE = "Ricerca prodotto";
	private static final int MIN_HEIGHT_FRAME = 160; //200
	private static final int MIN_WIDTH_FRAME = 300; //400

	private JModifyProductDialog modifyProductDialog;
	private Prodotto prodotto;
	private JFrame mainFrame;

	private ArrayList<File> imageToDeleteFromMod = new ArrayList<File>();
	private ArrayList<Image> imageFromMod = new ArrayList<Image>();
	private ArrayList<Prodotto> imageToSave = new ArrayList<Prodotto>();	
	private ArrayList<Prodotto> imageToSaveFromMod = new ArrayList<Prodotto>();
	
	private JLabel jStringLabel;
	private JComboBox <String> jCodeComboBox;
	private JButton jSearchButton;

	private static final String STRING_LABEL_TEXT = "Selezionare il codice del prodotto";
	private static final String SEARCH_BUTTON_TEXT = "Cerca";
	private static final String SEARCH_BUTTON_ACTION_COMMAND_TEXT = "cerca";
	
	private static final int HEIGHT_RIGID_AREA = 3;
	private static final int WIDTH_RIGID_AREA = 3;
	private static final int DIMENSION_HORIZONTAL_STRUT = 500;
	private static final int DIMENSION_VERTICAL_STRUT = 100;
	
	public JSearchProductDialog(JFrame mainFrame,Magazzino magazzino,JStoreTable jStoreTable,ArrayList<File> imageToDeleteFromMod, 
			ArrayList<Image> imageFromMod, ArrayList<Prodotto> imageToSave, ArrayList<Prodotto> imageToSaveFromMod) {
		super(mainFrame, TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.magazzino = magazzino;
		this.jStoreTable = jStoreTable;

		this.imageToDeleteFromMod = imageToDeleteFromMod;
		this.imageFromMod = imageFromMod;
		this.imageToSave = imageToSave;
		this.imageToSaveFromMod = imageToSaveFromMod;
		
		this.mainFrame = mainFrame;
		this.setModal(true);
		this.magazzino = magazzino;
		this.setSize(new Dimension(JSearchProductDialog.MIN_WIDTH_FRAME,
				JSearchProductDialog.MIN_HEIGHT_FRAME));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		this.add(this.contentPanel(), BorderLayout.CENTER);
	}
	
	private JPanel contentPanel () {		
		ArrayList <String> str = new ArrayList<String>();
		ArrayList <Prodotto> p = new ArrayList<Prodotto>();
		p = magazzino.getArticoli();
		for(int i = 0;i<p.size();i++)
		{
			str.add(i, p.get(i).getCodice());
		}

		String[] CODE_STRING = str.toArray(new String[str.size()]);
		this.jCodeComboBox = new JComboBox<String>(CODE_STRING);

		this.jStringLabel = new JLabel(JSearchProductDialog.STRING_LABEL_TEXT);

		this.jSearchButton = new JButton(JSearchProductDialog.SEARCH_BUTTON_TEXT);
		
		
		JPanel panel = new JPanel();
		panel.add(Box.createRigidArea(new Dimension(HEIGHT_RIGID_AREA,WIDTH_RIGID_AREA)));
		panel.add(this.jStringLabel);
		panel.add(Box.createHorizontalStrut(DIMENSION_HORIZONTAL_STRUT));
		panel.add(this.jCodeComboBox);
		panel.add(Box.createHorizontalStrut(DIMENSION_HORIZONTAL_STRUT));
		panel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		panel.add(this.jSearchButton);
		this.jSearchButton.setActionCommand(SEARCH_BUTTON_ACTION_COMMAND_TEXT);
		this.jSearchButton.addActionListener(this);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		contentPanel.add(panel);
		return contentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = (String) this.jCodeComboBox.getSelectedItem();
		prodotto = magazzino.getProdotto(code);	
		this.modifyProductDialog = new JModifyProductDialog(mainFrame,prodotto,magazzino,
				this,jStoreTable,imageToDeleteFromMod, 
				imageFromMod, imageToSave, imageToSaveFromMod);
		
		this.modifyProductDialog.setVisible(true);
		
	}
}