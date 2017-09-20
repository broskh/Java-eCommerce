package grafica;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * DocumentFilter per JTextField utilizzata nell'inserimento
 * di un prezzo.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class CostDocumentFilter extends DocumentFilter{

	private float maxValue;

	private static final String EMPTY_FIELD_VALUE = "0";
	private static final int NULL_VALUE = -1;
	
	public CostDocumentFilter(float maxValue) {
		this.maxValue = maxValue;
	}
	
	public CostDocumentFilter() {
		this(NULL_VALUE);
	}
	
	private boolean testFloat (String value) {
		try {
//			CONTROLLO CHE CI SIA UN SOLO PUNTO
			int dots = 0;
			for (int i = 0; i < value.length(); i++) {
				if (value.charAt(i) == '.') {
					dots++;
				}
			}
			if (dots > 1) {
				return false;
			}
//			CONTROLLO CHE NON VENGA AGGIUNTA UNA f
			if (value.length() > 0 && (value.charAt(value.length() - 1) == 'f' || 
					value.charAt(value.length() - 1) == 'F' || 
					value.charAt(value.length() - 1) == 'd' || 
					value.charAt(value.length() - 1) == 'D')) {
				return false;
			}
//			CONTROLLO CHE SIA UN FLOAT ACCETTABILE
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private String correctValue(String value) {
//		CONTROLLO CHE LA STRINGA NON SIA VUOTA
		if (value.equals("")) {
			value = EMPTY_FIELD_VALUE;
		}
//		CONTROLLO CHE CI SIANO SOLO DUE CIFRE DOPO LA VIRGOLA
		if ((value.indexOf('.') != -1 && (value.length() - 1) - value.indexOf('.') > 2)) {
			value = value.substring(0, value.length() - 2) + 
					value.charAt(value.length() - 1);
		}
//		CONTROLLO CHE NON CI SIA UNO ZERO INUTILE ALL'INIZIO DEL NUMERO
		if (value.length() > 1) {
			if (value.charAt(0) == '0' && value.charAt(1) != '.') {
				value = value.substring(1);
			}
		}
		Float floatValue = Float.parseFloat(value);
		if(this.maxValue != NULL_VALUE && floatValue > this.maxValue) {
			return Float.toString(this.maxValue);
		}
		return value;
	}
	
	public void  setMaxValue(Float maxValue) {
		this.maxValue = maxValue;
	}
	
	public Float getMaxValue() {
		return this.maxValue;
	}
	
	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);
		
		if(this.testFloat(sb.toString())) {
			super.insertString(fb, offset, string, attr);
		}
	}
	
	@Override
	public void replace(FilterBypass fb,int offset,int length,String text,AttributeSet attrs)
			throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);
		
		if(this.testFloat(sb.toString())) {
			super.replace(fb, 0, doc.getLength(), this.correctValue(sb.toString()), attrs);
		}
	}
	
	@Override
	public void remove(FilterBypass fb, int offset, int length) 
			throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);
		
		if (this.testFloat (sb.toString())) {
			super.remove(fb, offset, length);
		} else {
			this.replace(fb, offset, length, this.correctValue(sb.toString()), null);
		}
	}
}