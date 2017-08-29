package grafica;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class PriceDocumentFilter extends DocumentFilter{

	private float maxValue;
	
	public PriceDocumentFilter(float maxValue)
	{
		this.maxValue = maxValue;
	}
	
	public PriceDocumentFilter()
	{
		this(-1);
	}
	
	@Override
	public void insertString(FilterBypass fb,int offset,String string,AttributeSet attr) throws BadLocationException
	{
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);
		
		if(this.testFloat(sb.toString()))
		{
			super.insertString(fb, offset, string, attr);
		}
		else
		{
			
		}
	}
	
	private boolean testFloat(String text)
	{
		try
		{
			Float.parseFloat(text);
			return true;
		} 
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	/*@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);

		if (this.testFloat (sb.toString())) {
			super.replace(fb, 0, doc.getLength(), this.correctValue(sb.toString()), attrs);
		} else {
         // 	warn the user and don't allow the insert
		}

	}*/
	
	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);
		
		if (this.testFloat (sb.toString())) {
			super.remove(fb, offset, length);
		} else {
			this.replace(fb, offset, length, "0", null);
			// warn the user and don't allow the insert
		}

	}
}
