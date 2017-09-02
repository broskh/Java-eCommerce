package grafica;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JSearchProductPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JStoreTable jStoreTable;
	private JModifyProductDialog modifyProductDialog;
	private JFrame mainFrame;
	private Magazzino magazzino;
	private Prodotto prodotto;
	private JSearchProductDialog jSearchProductDialog;
	
	private JLabel jStringLabel;
	private JComboBox <String> jCodeComboBox;
	protected JButton jSearchButton;

	private static final String STRING_LABEL_TEXT = "Selezionare il codice del prodotto";
	private static final String SEARCH_BUTTON_TEXT = "Cerca";
	private static final String SEARCH_BUTTON_ACTION_COMMAND_TEXT = "cerca";
	
	private static final int HEIGHT_RIGID_AREA = 3;
	private static final int WIDTH_RIGID_AREA = 3;
	private static final int DIMENSION_HORIZONTAL_STRUT = 500;
	private static final int DIMENSION_VERTICAL_STRUT = 100;
	
	
	
	public JSearchProductPanel(JFrame mainFrame,Magazzino magazzino, JSearchProductDialog jSearchProductDialog,JStoreTable jStoreTable)
	{
		this.magazzino = magazzino;
		this.jSearchProductDialog = jSearchProductDialog;
		this.jStoreTable = jStoreTable;
		
		ArrayList <String> str = new ArrayList<String>();
		ArrayList <Prodotto> p = new ArrayList<Prodotto>();
		p = magazzino.getArticoli();
		for(int i = 0;i<p.size();i++)
		{
			str.add(i, p.get(i).getCodice());
		}

		String[] CODE_STRING = str.toArray(new String[str.size()]);
		this.jCodeComboBox = new JComboBox<String>(CODE_STRING);

		this.jStringLabel = new JLabel(JSearchProductPanel.STRING_LABEL_TEXT);

		this.jSearchButton = new JButton(JSearchProductPanel.SEARCH_BUTTON_TEXT);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
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
		this.add(panel);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = (String) this.jCodeComboBox.getSelectedItem();
		prodotto = magazzino.getProdotto(code);	
		this.modifyProductDialog = new JModifyProductDialog(mainFrame,prodotto,magazzino,
				jSearchProductDialog,jStoreTable);
		
		this.modifyProductDialog.setVisible(true);
		
	}
}
