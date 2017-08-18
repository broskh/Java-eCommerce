package grafica;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.PlainDocument;

public class JModifyArticlePanel extends JPanel{
	private static final long serialVersionUID = -8842725930146342385L;

	private JTextField codeTextfield;
	private JTextField amountTextfield;
	
	private StringBuilder code;
	
	private static int SPAZIO_FRA_TEXTFIELD = 20;
	private static int LARGHEZZA_TEXTFIELD_CODICE = 150;
	private static int COLONNE_TEXTFIELD_QUANTITA = 3;
	private static int QUANTITA_DEFAULT = 1;
	private static String STRINGA_CODICE = "Codice: ";
	private static String STRINGA_QUANTITA = "Qnt: ";
	
	public JModifyArticlePanel () {
		this.code = new StringBuilder();
		
		this.codeTextfield = new JTextField();
		this.codeTextfield.setPreferredSize(new Dimension(LARGHEZZA_TEXTFIELD_CODICE, this.codeTextfield.getPreferredSize().height));
		this.codeTextfield.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				this.changeCode();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				this.changeCode();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				this.changeCode();
			}

			private void changeCode() {
				JModifyArticlePanel.this.code.replace(0, JModifyArticlePanel.this.code.length(), JModifyArticlePanel.this.codeTextfield.getText());
			}
		});
		
		this.amountTextfield = new JTextField(String.valueOf(QUANTITA_DEFAULT), COLONNE_TEXTFIELD_QUANTITA);
		PlainDocument doc = (PlainDocument) this.amountTextfield.getDocument();
		doc.setDocumentFilter(new AmountDocumentFilter());

		this.add(new JLabel(STRINGA_CODICE));
		this.add(this.codeTextfield);
		this.add(Box.createHorizontalStrut(SPAZIO_FRA_TEXTFIELD));
		this.add(new JLabel(STRINGA_QUANTITA));
		this.add(this.amountTextfield);
	}
	
	public StringBuilder getCode () {
		return this.code;
	}
	
	public int getAmount () {
		return Integer.parseInt(this.amountTextfield.getText());
	}
	
	public JTextField getAmountTextfield () {
		return this.amountTextfield;
	}
}
