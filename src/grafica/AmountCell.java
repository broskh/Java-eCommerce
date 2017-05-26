package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AmountCell extends JPanel {
	private static final long serialVersionUID = 2028713241711826134L;
	
	private JTextField textField;

	public AmountCell (JTextField textField) {
		this.textField = textField;
		this.textField.setPreferredSize(new Dimension(50, 22));
		
		JPanel mainPanel = new JPanel ();
		mainPanel.add(this.textField);
		
		this.setLayout (new BorderLayout());
        this.add(Box.createVerticalStrut(35), BorderLayout.PAGE_START);
        this.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
	}
}