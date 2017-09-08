package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import negozio.Prodotto;

public class JSelectProductPanel extends JPanel {
	private static final long serialVersionUID = -7373492742309698433L;

	private ArrayList <Prodotto> prodotti;
	
	private JLabel label;
	private JComboBox <String> codeComboBox;
	private JButton selectButton;
	
	private static final int INTERACTIONPANEL_TOP_MARGIN = 50;

	private static final int INTERACTIONPANEL_SPACE = 120;
	
	public JSelectProductPanel (ArrayList <Prodotto> prodotti) {
		this.prodotti = prodotti;
		
		ArrayList <String> codici = new ArrayList<String>();
		for (Prodotto articolo : this.prodotti) {
			codici.add(articolo.getCodice());
		}
		this.codeComboBox = new JComboBox <String> (codici.toArray (new String[codici.size()]));
		this.label = new JLabel();
		this.selectButton = new JButton();
		JPanel labelPanel = new JPanel(new FlowLayout (FlowLayout.CENTER));
		labelPanel.add(this.label);
				
		JPanel interactionPanel = new JPanel();
		interactionPanel.add(this.codeComboBox);
		interactionPanel.add(Box.createHorizontalStrut(INTERACTIONPANEL_SPACE));
		interactionPanel.add(this.selectButton);

		this.setLayout(new BorderLayout());
		this.add(labelPanel, BorderLayout.PAGE_START);
		Dimension rigidAreadDimension = new Dimension(this.getWidth(), INTERACTIONPANEL_TOP_MARGIN);
		this.add(Box.createRigidArea(rigidAreadDimension), BorderLayout.CENTER);
		this.add(interactionPanel, BorderLayout.PAGE_END);
	}
	
	public void setLabelText (String labelText) {
		this.label.setText(labelText);
	}
	
	public void setButtonText (String buttonText) {
		this.selectButton.setText(buttonText);
	}
	
	public void setActionListener (ActionListener actionListener) {
		this.selectButton.addActionListener(actionListener);
	}
	
	public String getSelectedCode () {
		return (String) this.codeComboBox.getSelectedItem();
	}
}