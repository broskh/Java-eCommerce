package grafica;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class AmountDocumentFilter extends DocumentFilter {
	
	private int maxValue;
	
	public AmountDocumentFilter (int maxValue) {
		this.maxValue = maxValue;
	}
	
	public AmountDocumentFilter() {
		this (-1);
	}

	private boolean testInteger (String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private String correctValue (String value) {
		int intValue = Integer.parseInt(value);
		
		if (this.maxValue != -1 && intValue > this.maxValue) {
			return Integer.toString(this.maxValue);
		}
		return Integer.toString(intValue);
	}
	
	public void setMaxValue (int maxValue) {
		this.maxValue = maxValue;
	}
	
	public int getMaxValue () {
		return this.maxValue;
	}
	
	@Override
	public void insertString (FilterBypass fb, int offset, String string, 
			AttributeSet attr) throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);

		if (this.testInteger(sb.toString())) {
			super.insertString(fb, offset, string, attr);
		} else {
			// warn the user and don't allow the insert
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, 
			AttributeSet attrs) throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);

		if (this.testInteger (sb.toString())) {
			super.replace(fb, 0, doc.getLength(), 
					this.correctValue(sb.toString()), attrs);
		} else {
         // 	warn the user and don't allow the insert
		}
	}
	
	@Override
	public void remove(FilterBypass fb, int offset, int length) 
			throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);
		
		if (this.testInteger (sb.toString())) {
			super.remove(fb, offset, length);
		} else {
			this.replace(fb, offset, length, "0", null);
			// warn the user and don't allow the insert
		}
	}
}