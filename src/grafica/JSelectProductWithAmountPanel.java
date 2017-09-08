package grafica;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import negozio.Prodotto;

public class JSelectProductWithAmountPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -8842725930146342385L;
	
	private StringBuilder code;
	private ArrayList <Prodotto> articoli;

	private JComboBox <String> codeComboBox;
	private JTextField amountTextfield;
	private AmountDocumentFilter amountDocumentFilter;
	
	private static final int CODE_TEXTFIELD_WIDTH = 150;
	private static final int AMOUNT_TEXTFIELD_COLUMNS = 3;
	private static final int COMPONENTS_SPACE = 30;
	
	private static final int DEFAULT_AMOUNT = 0;
	
	private static final String CODE_TEXT = "Codice: ";
	private static final String AMOUNT_TEXT = "Qnt: ";
	
	public JSelectProductWithAmountPanel (ArrayList <Prodotto> articoli) {
		this.articoli = articoli;
		ArrayList <String> codici = new ArrayList <> ();
		for (Prodotto articolo : this.articoli) {
			codici.add(articolo.getCodice());
		}
		this.codeComboBox = new JComboBox <String> (codici.toArray(
				new String[codici.size()]));
		this.codeComboBox.setPreferredSize(new Dimension(CODE_TEXTFIELD_WIDTH, 
				this.codeComboBox.getPreferredSize().height));
		this.codeComboBox.addActionListener(this);
		this.code = new StringBuilder(this.codeComboBox.getItemAt(0).toString());
		
		this.amountTextfield = new JTextField(String.valueOf(DEFAULT_AMOUNT), 
				AMOUNT_TEXTFIELD_COLUMNS);
		PlainDocument doc = (PlainDocument) this.amountTextfield.getDocument();
		this.amountDocumentFilter = new AmountDocumentFilter(this.articoli.get(0).getQuantita());
		doc.setDocumentFilter(this.amountDocumentFilter);

		this.add(new JLabel(CODE_TEXT));
		this.add(this.codeComboBox);
		this.add(Box.createHorizontalStrut(COMPONENTS_SPACE));
		this.add(new JLabel(AMOUNT_TEXT));
		this.add(this.amountTextfield);
	}
	
	public int getAmount () {
		return Integer.parseInt(this.amountTextfield.getText());
	}
	
	public StringBuilder getCode () {
		return this.code;
	}
	
	public JTextField getAmountTextfield () {
		return this.amountTextfield;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.code.replace(0, this.code.length(), 
				this.codeComboBox.getSelectedItem().toString());
		this.amountTextfield.setText(String.valueOf(DEFAULT_AMOUNT));
		this.amountDocumentFilter.setMaxValue(this.articoli.get(this.codeComboBox.getSelectedIndex()).getQuantita());
	}
}
