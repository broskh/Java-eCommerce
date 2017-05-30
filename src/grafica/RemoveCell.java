package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class RemoveCell extends JPanel {	
	private static final long serialVersionUID = -3278269777576840442L;

	private JButton removeButton;
	
	private int altezzaCella;

	private static final int DIMENSIONE_BOTTONE = 28;		
	private static final String REMOVE_BUTTON_TEXT = "Remove";
	private static final String REMOVE_IMAGE_PATH = "media/img/remove.png";
	
    public RemoveCell (int altezzaCella) {
		this.altezzaCella = altezzaCella;
		this.removeButton = new JButton();
		try {
		    Image img = ImageIO.read(new File (REMOVE_IMAGE_PATH));
		    this.removeButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.removeButton.setText(REMOVE_BUTTON_TEXT);
		}
		this.removeButton.setPreferredSize(new Dimension(DIMENSIONE_BOTTONE, DIMENSIONE_BOTTONE));
		
		int margineSuperiore = (this.altezzaCella - DIMENSIONE_BOTTONE - 10) / 2;
		
		JPanel removePanel = new JPanel();
		removePanel.add(this.removeButton);
		
		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(margineSuperiore), BorderLayout.PAGE_START);
		this.add(removePanel);
    }
}