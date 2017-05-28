package grafica;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;
 
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import negozio.Prodotto;

public class ArticlesTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -3450099361845613304L;
	
	private ArrayList <Prodotto> articoli;
	
	private static final int COLONNA_QUANTITA = 8;
	private static final int COLONNA_BOTTONE = 9;
		private static final String [] COLONNE = {"Immagine", "Codice", "Nome", "Marca", 
			"Categoria", "Offerta", "Prezzo cadauno", "Prezzo totale", "Quantità", ""};
	protected static final int DIMENSIONE_ICONA = 100;
	
	public ArticlesTableModel (ArrayList <Prodotto> articoli) {
		super ();
		this.articoli = articoli;
	}
	
	private Image ridimensionaImmagine (File immagine) {
		BufferedImage bimg;
		try {
			bimg = ImageIO.read(immagine);
			// Calcolo le giuste dimensioni per l'icona
			int original_width = bimg.getWidth();
		    int original_height = bimg.getHeight();
		    int bound_width = DIMENSIONE_ICONA;
		    int bound_height = DIMENSIONE_ICONA;
		    int new_width = original_width;
		    int new_height = original_height;
		    if (original_width > bound_width) {
		        new_width = bound_width;
		        new_height = (new_width * original_height) / original_width;
		    }
		    if (new_height > bound_height) {
		        new_height = bound_height;
		        new_width = (new_height * original_width) / original_height;
		    }
			
		    // Ridimensiono l'immagine
		    BufferedImage resizedImg = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = resizedImg.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(bimg, 0, 0, new_width, new_height, null);
		    g2.dispose();

		    return resizedImg;
		} catch (IOException e) {
			e.printStackTrace();			
			return null;
		}		
	}

	@Override
	public int getRowCount() {
		return this.articoli.size();
	}

	@Override
	public int getColumnCount() {
		return COLONNE.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
//				JLabel icona = new JLabel();
//				icona.setBackground(null);
//				icona.setIcon();
//				return icona;
				return new ImageIcon (this.ridimensionaImmagine (this.articoli.get(rowIndex).getImmagine()));
			case 1:
				return this.articoli.get(rowIndex).getCodice();
			case 2:
				return this.articoli.get(rowIndex).getNome();
			case 3:
				return this.articoli.get(rowIndex).getMarca();
			case 4:
				return this.articoli.get(rowIndex).getCategoria();
			case 5:
				return this.articoli.get(rowIndex).getOfferta().toString();
			case 6:
				return String.format("%.2f", this.articoli.get(rowIndex).prezzoCadaunoScontato());
			case 7:
				return String.format("%.2f", this.articoli.get(rowIndex).prezzoTotaleScontato());
			case 8:
				return this.articoli.get(rowIndex).getQuantita();
			case 9:
				return "";
		}
		return null;
	}

	@Override
	public String getColumnName (int column) {
		return COLONNE [column];
	}

	@Override
	public boolean isCellEditable (int row, int column) {
		if (column == COLONNA_QUANTITA || column == COLONNA_BOTTONE) {
			return true;
		}
		return false;
	}
	
	@Override
	public Class <?> getColumnClass (int column) {
		switch (column) {
			case 0:
				return ImageIcon.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			case 6:
				return String.class;
			case 7:
				return String.class;
			case 8:
				return String.class;
			case 9:
				return String.class;
		}
		return null;
	}
}