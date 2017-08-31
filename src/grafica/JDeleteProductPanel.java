package grafica;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import negozio.Magazzino;
import negozio.Prodotto;

public class JDeleteProductPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JDeleteProductDialog jDeleteProductDialog;
	private Magazzino magazzino;
	private Prodotto prodotto;

	private JLabel jStringLabel;
	private JComboBox <String> jCodeComboBox;
	protected JButton jDeleteButton;

	private static final String STRING_LABEL_TEXT = "Selezionare il codice del prodotto da eliminare";
	private static final String DELETE_BUTTON_TEXT = "Elimina";
	private static final String DELETE_BUTTON_ACTION_COMMAND_TEXT = "elimina";
	
	private static final String J_OPTION_PANE_STRING = "Prodotto eliminato correttamente";
	private static final String J_OPTION_PANE_ALERT = "Attenzione!";
	
	
	private static final int HEIGHT_RIGID_AREA = 3;
	private static final int WIDTH_RIGID_AREA = 3;
	private static final int DIMENSION_HORIZONTAL_STRUT = 500;
	private static final int DIMENSION_VERTICAL_STRUT = 100;
	
	
	
	
	
	public JDeleteProductPanel(Magazzino magazzino, JDeleteProductDialog jDeleteProductDialog)
	{
		ArrayList <String> str = new ArrayList<String>();
		ArrayList <Prodotto> p = new ArrayList<Prodotto>();
		this.magazzino = magazzino;
		this.jDeleteProductDialog = jDeleteProductDialog;
		
		p = magazzino.getArticoli();
		for(int i = 0;i<p.size();i++)
		{
			str.add(i, p.get(i).getCodice());
		}
		
		String[] CODE_STRING = str.toArray(new String[str.size()]);
		this.jCodeComboBox = new JComboBox<String>(CODE_STRING);
		
		this.jStringLabel = new JLabel(JDeleteProductPanel.STRING_LABEL_TEXT);

		this.jDeleteButton = new JButton(JDeleteProductPanel.DELETE_BUTTON_TEXT);
		
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

		this.add(panel);		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String code = (String) this.jCodeComboBox.getSelectedItem();
		prodotto = magazzino.getProdotto(code);
		magazzino.rimuoviProdotto(prodotto.getCodice(), prodotto.getQuantita());
		JOptionPane.showMessageDialog(this, J_OPTION_PANE_STRING,J_OPTION_PANE_ALERT,
				JOptionPane.INFORMATION_MESSAGE);
		
		
		File file = new File(prodotto.getImmagine().toString());
		if (file.exists()) {
		    file.delete();
		}
		this.jDeleteProductDialog.setVisible(false);
	    
	}
}
