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
	
	private static int TEXTFIELDS_SPACE = 20;
	private static int CODE_TEXTFIELD_WIDTH = 150;
	private static int AMOUNT_TEXTFIELD_COLUMNS = 3;
	private static int DEFAULT_AMOUNT = 1;
	private static String CODE_TEXT = "Codice: ";
	private static String AMOUNT_TEXT = "Qnt: ";
	
	public JModifyArticlePanel () {
		this.code = new StringBuilder();
		
		this.codeTextfield = new JTextField();
		this.codeTextfield.setPreferredSize(new Dimension(CODE_TEXTFIELD_WIDTH, 
				this.codeTextfield.getPreferredSize().height));
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
				JModifyArticlePanel.this.code.replace(0, JModifyArticlePanel.this.code.length(), 
						JModifyArticlePanel.this.codeTextfield.getText());
			}
		});
		
		this.amountTextfield = new JTextField(String.valueOf(DEFAULT_AMOUNT), 
				AMOUNT_TEXTFIELD_COLUMNS);
		PlainDocument doc = (PlainDocument) this.amountTextfield.getDocument();
		doc.setDocumentFilter(new AmountDocumentFilter());

		this.add(new JLabel(CODE_TEXT));
		this.add(this.codeTextfield);
		this.add(Box.createHorizontalStrut(TEXTFIELDS_SPACE));
		this.add(new JLabel(AMOUNT_TEXT));
		this.add(this.amountTextfield);
	}
	
	public void setAmount (int amount) {
		this.amountTextfield.setText(Integer.toString(amount));
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
}
