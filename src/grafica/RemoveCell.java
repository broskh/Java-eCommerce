package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import negozio.Prodotto;

class RemoveCell extends JPanel implements ActionListener{	
	private static final long serialVersionUID = -3278269777576840442L;

	private JButton removeButton;
	
	private Integer nArticolo;
	private ArrayList <Prodotto> articoli;

	private static final int DIMENSIONE_BOTTONE = 28;		
	private static final String REMOVE_BUTTON_TEXT = "Remove";
	private static final String REMOVE_IMAGE_PATH = "media/img/remove.png";
	
    public RemoveCell (int altezzaCella) {
    	this (altezzaCella, null, null);
    }
	
    public RemoveCell (int altezzaCella, Integer nArticolo, ArrayList <Prodotto> articoli) {
		this.articoli = articoli;
		this.nArticolo = nArticolo;
    	this.removeButton = new JButton();
		try {
		    Image img = ImageIO.read(new File (REMOVE_IMAGE_PATH));
		    this.removeButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.removeButton.setText(REMOVE_BUTTON_TEXT);
		}
		this.removeButton.setPreferredSize(new Dimension(DIMENSIONE_BOTTONE, DIMENSIONE_BOTTONE));
		
		int margineSuperiore = (altezzaCella - DIMENSIONE_BOTTONE - 10) / 2;
		
		JPanel removePanel = new JPanel();
		removePanel.add(this.removeButton);
		
		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(margineSuperiore), BorderLayout.PAGE_START);
		this.add(removePanel);
		
		this.addRemoveButtonActionListener();
    }
    
    public Integer getnArticolo () {
		return nArticolo;
	}

	public void setnArticolo (Integer nArticolo) {
		this.nArticolo = nArticolo;
		this.addRemoveButtonActionListener();
	}

	public ArrayList <Prodotto> getArticoli() {
		return articoli;
	}

	public void setArticoli (ArrayList <Prodotto> articoli) {
		this.articoli = articoli;
		this.addRemoveButtonActionListener();
	}

	private boolean addRemoveButtonActionListener () {
    	if (this.nArticolo != null && this.articoli != null) {
			this.removeButton.addActionListener(this);
			return true;
		}
    	return false;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		this.articoli.remove((int)this.nArticolo);
	}
}