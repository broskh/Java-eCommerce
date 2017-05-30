package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AmountCell extends JPanel {
	private static final long serialVersionUID = 2028713241711826134L;
	
	private JTextField textField;
	
	private int altezzaCella;

	private static final int LARGHEZZA_TEXTFIELD = 50;
	private static final int ALTEZZA_TEXTFIELD = 22;
	private static final int MARGINE_LATERALE = 10;

	public AmountCell (JTextField textField, int altezzaCella) {
		this.altezzaCella = altezzaCella;
		this.textField = textField;
		this.textField.setPreferredSize(new Dimension(LARGHEZZA_TEXTFIELD, ALTEZZA_TEXTFIELD));
		int margineSuperiore = (this.altezzaCella - ALTEZZA_TEXTFIELD - 10) / 2;
		
		JPanel mainPanel = new JPanel ();
		mainPanel.add(this.textField);
		
		this.setLayout (new BorderLayout());
        this.add(Box.createVerticalStrut(margineSuperiore), BorderLayout.PAGE_START);
        this.add(Box.createHorizontalStrut(MARGINE_LATERALE), BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(Box.createHorizontalStrut(MARGINE_LATERALE), BorderLayout.EAST);
	}
}