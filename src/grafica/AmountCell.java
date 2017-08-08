package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class AmountCell extends JPanel {
	private static final long serialVersionUID = 2028713241711826134L;

	private JTextField textField;
	
	private static final int LARGHEZZA_TEXTFIELD = 50;
	private static final int ALTEZZA_TEXTFIELD = 22;
	private static final int MARGINE_LATERALE = 10;

	public AmountCell (Integer value, int altezzaCella) {
		this.textField = new JTextField();
		if (value != null) {
			this.textField.setText(value.toString());
		}
		this.textField.setPreferredSize(new Dimension(LARGHEZZA_TEXTFIELD, ALTEZZA_TEXTFIELD));
		int margineSuperiore = (altezzaCella - ALTEZZA_TEXTFIELD - 10) / 2;
		
		JPanel mainPanel = new JPanel ();
		mainPanel.add(this.textField);
		
		this.setLayout (new BorderLayout());
        this.add(Box.createVerticalStrut(margineSuperiore), BorderLayout.PAGE_START);
        this.add(Box.createHorizontalStrut(MARGINE_LATERALE), BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(Box.createHorizontalStrut(MARGINE_LATERALE), BorderLayout.EAST);
	}
	
	public AmountCell (int altezzaCella) {
		this (null, altezzaCella);
	}
	
	public void setValue (Integer value) {
		this.textField.setText(value.toString());
	}
	
	public int getValue () {
		return Integer.parseInt(this.textField.getText());
	}
	
	public void setFilter (int maxValue) {
		PlainDocument doc = (PlainDocument) this.textField.getDocument();
		doc.setDocumentFilter(new AmountDocumentFilter(maxValue));	
	}
}