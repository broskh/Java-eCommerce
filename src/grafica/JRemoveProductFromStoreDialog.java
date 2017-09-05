package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JRemoveProductFromStoreDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 8312477817724761485L;

	private Magazzino magazzino;
	private JStoreTable jStoreTable;
	protected static final String TITLE = "Elimina prodotto";
	protected static final int MIN_HEIGHT_FRAME = 160; //200
	protected static final int MIN_WIDTH_FRAME = 300; //400
	
	private Prodotto product;

	private ArrayList<File> imageToDelete = new ArrayList<File>();
	private JLabel jStringLabel;
	private JComboBox <String> jCodeComboBox;
	private JButton jDeleteButton;

	private static final String STRING_LABEL_TEXT = "Selezionare il codice del prodotto da eliminare";
	private static final String DELETE_BUTTON_TEXT = "Elimina";
	private static final String DELETE_BUTTON_ACTION_COMMAND_TEXT = "elimina";
	
	private static final String J_OPTION_PANE_STRING = "Prodotto eliminato correttamente";
	private static final String J_OPTION_PANE_ALERT = "Attenzione!";
	
	private static final int HEIGHT_RIGID_AREA = 3;
	private static final int WIDTH_RIGID_AREA = 3;
	private static final int DIMENSION_HORIZONTAL_STRUT = 500;
	private static final int DIMENSION_VERTICAL_STRUT = 100;
	
	public JRemoveProductFromStoreDialog(JFrame mainFrame,Magazzino magazzino,JStoreTable jStoreTable, ArrayList<File> imageToDelete)
	{
		//JDialog.ModalityType.DOCUMENT_MODAL
		super(mainFrame,TITLE,JDialog.ModalityType.DOCUMENT_MODAL);

		this.imageToDelete = imageToDelete;
		this.magazzino = magazzino;
		this.jStoreTable = jStoreTable;
		
		
		this.setModal(true);
		this.magazzino = magazzino;
		this.setSize(new Dimension(JRemoveProductFromStoreDialog.MIN_WIDTH_FRAME,
				JRemoveProductFromStoreDialog.MIN_HEIGHT_FRAME));
		
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
		
		this.jStringLabel = new JLabel(JRemoveProductFromStoreDialog.STRING_LABEL_TEXT);

		this.jDeleteButton = new JButton(JRemoveProductFromStoreDialog.DELETE_BUTTON_TEXT);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.add(Box.createRigidArea(new Dimension(HEIGHT_RIGID_AREA,WIDTH_RIGID_AREA)));
		panel.add(this.jStringLabel);
		panel.add(Box.createHorizontalStrut(DIMENSION_HORIZONTAL_STRUT));
		panel.add(this.jCodeComboBox);
		panel.add(Box.createHorizontalStrut(DIMENSION_HORIZONTAL_STRUT));
		panel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		panel.add(this.jDeleteButton);
		this.jDeleteButton.setActionCommand(DELETE_BUTTON_ACTION_COMMAND_TEXT);
		this.jDeleteButton.addActionListener(this);

		JPanel contentPanel = new JPanel();
		contentPanel.add(panel);
		return contentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = (String) this.jCodeComboBox.getSelectedItem();
		product = magazzino.getProdotto(code);
		
		/**/
		this.imageToDelete.add(product.getImmagine());
		/**/
		magazzino.rimuoviProdotto(product.getCodice(), product.getQuantita());
		JOptionPane.showMessageDialog(this, J_OPTION_PANE_STRING,J_OPTION_PANE_ALERT,
				JOptionPane.INFORMATION_MESSAGE);
		
		((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
		
		/*File file = new File(prodotto.getImmagine().toString());
		if (file.exists()) {
		    file.delete();
		}*/
		this.setVisible(false);
	    
	}
}
