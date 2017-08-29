package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negozio.Magazzino;
import negozio.Prodotto;

public class JDeleteProductPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	
	//private JModifyProductFrame modifyProductFrame;
	private JDeleteProductFrame jDeleteProductFrame;
	private Magazzino magazzino;
	private Prodotto prodotto;
	
	
	private JLabel jStringaLabel;
	private JComboBox <String> jCodiciComboBox;
	protected JButton jCercaButton;


	private static final String TESTO_STRINGA_LABEL = "Selezionare il codice del prodotto da eliminare";
	private static final String TESTO_CERCA_BUTTON = "Elimina";
	
	
	
	public JDeleteProductPanel(Magazzino magazzino, JDeleteProductFrame jDeleteProductFrame)
	{
		ArrayList <String> s = new <String> ArrayList();
		ArrayList <Prodotto> l = new <Prodotto> ArrayList();
		this.magazzino = magazzino;
		this.jDeleteProductFrame = jDeleteProductFrame;
		
		l = magazzino.getArticoli();
		for(int i = 0;i<l.size();i++)
		{
			s.add(i, l.get(i).getCodice());
		}
		
		String[] STRINGA_CODICI = s.toArray(new String[s.size()]);
		this.jCodiciComboBox = new JComboBox(STRINGA_CODICI);
		
		this.jStringaLabel = new JLabel(this.TESTO_STRINGA_LABEL);

		this.jCercaButton = new JButton(this.TESTO_CERCA_BUTTON);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pannello = new JPanel();//(new GridLayout(3,1));
		pannello.add(Box.createRigidArea(new Dimension(3,3)));
		pannello.add(this.jStringaLabel);
		pannello.add(Box.createHorizontalStrut(500));
		pannello.add(this.jCodiciComboBox);
		pannello.add(Box.createHorizontalStrut(500));
		pannello.add(Box.createVerticalStrut(100));
		pannello.add(this.jCercaButton);
		this.jCercaButton.setActionCommand("cerca");
		this.jCercaButton.addActionListener(this);

		this.add(pannello);		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String codice = (String) this.jCodiciComboBox.getSelectedItem();
		prodotto = magazzino.getProdotto(codice);
		magazzino.rimuoviProdotto(prodotto.getCodice(), prodotto.getQuantita());
		magazzino.salvaMagazzino("media/saves/save21.mag");
		JOptionPane.showMessageDialog(this, "Prodotto eliminato correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
		this.jDeleteProductFrame.setVisible(false);
	    
	}
}
