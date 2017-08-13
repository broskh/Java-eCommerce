package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.PlainDocument;

import grafica.JCartDialog.JCartTable;
import negozio.Carrello;
import negozio.Magazzino;
import negozio.Prodotto;

public class ArticlesTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -3450099361845613304L;
	
	private ArrayList <Prodotto> articoli;
	private int dimensioneIcona;
	
	private static final int COLONNA_QUANTITA = 8;
	private static final int COLONNA_BOTTONE = 9;
	private static final String [] COLONNE = {"Immagine", "Codice", "Nome", "Marca", 
		"Categoria", "Offerta", "Prezzo cadauno", "Prezzo totale", "Quantità", ""};
	
	public ArticlesTableModel (ArrayList <Prodotto> articoli, int dimensioneIcona) {
		super ();
		this.articoli = articoli;
		this.dimensioneIcona = dimensioneIcona;
	}
	
	private Image ridimensionaImmagine (File immagine) {
		BufferedImage bimg;
		try {
			bimg = ImageIO.read(immagine);
			// Calcolo le giuste dimensioni per l'icona
			int original_width = bimg.getWidth();
		    int original_height = bimg.getHeight();
		    int bound_width = this.dimensioneIcona;
		    int bound_height = this.dimensioneIcona;
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
				return new Object ();
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
				return int.class;
			case 9:
				return Object.class;
		}
		return null;
	}
}

class AmountColumnEditor extends DefaultCellEditor{
    private static final long serialVersionUID = 7039137261252411532L;
    
	private int lastRowSelected;
	private Carrello carrello;
	private Magazzino magazzino;
	
	private AmountCell cellPanel;
	
	private static final int VALORE_NULLO = -1;

    public AmountColumnEditor (ArticlesTableModel articlesTableModel, Carrello carrello, Magazzino magazzino, int altezzaCella) {
    	super (new JTextField());
    	this.lastRowSelected = VALORE_NULLO;
    	this.carrello = carrello;
    	this.magazzino = magazzino;
    	this.cellPanel = new AmountCell(altezzaCella, articlesTableModel);
		
        this.setClickCountToStart(1);
    }

	@Override
	public Object getCellEditorValue() {
		return this.carrello.getArticoli().get(this.lastRowSelected).getQuantita();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.lastRowSelected = row; 
		Prodotto prodotto = this.carrello.getArticoli().get(this.lastRowSelected);
		Prodotto articoloMagazzino = this.magazzino.getProdotto(prodotto.getCodice());
		this.cellPanel.setFilter(articoloMagazzino.getQuantita());
		this.cellPanel.setProdotto(prodotto);
        return this.cellPanel;
	}
	
	@Override
	public boolean stopCellEditing () {
		Prodotto articoloCarrello = this.carrello.getArticoli().get(this.lastRowSelected);
    	articoloCarrello.setQuantita((int)this.getCellEditorValue());
    	return true;
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return super.shouldSelectCell(anEvent);
	}

	@Override
	public void cancelCellEditing() {
		super.cancelCellEditing();
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		super.addCellEditorListener(l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		super.removeCellEditorListener(l);
	}
}

class AmountColumnRender implements TableCellRenderer {
	
	private int altezzaRiga;
	
    public AmountColumnRender (int altezzaRiga) {
    	this.altezzaRiga = altezzaRiga;
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return new AmountCell(this.altezzaRiga, ((JCartTable) table).getProductAtRow(row));
	}
}

class AmountCell extends JPanel {
	private static final long serialVersionUID = 2028713241711826134L;

	private JTextField textField;
	private ArticlesTableModel articlesTableModel;
	private Prodotto prodotto;
	
	private static final int LARGHEZZA_TEXTFIELD = 50;
	private static final int ALTEZZA_TEXTFIELD = 22;
	private static final int MARGINE_LATERALE = 10;

	public AmountCell (int altezzaCella, Prodotto prodotto, Integer maxValue, ArticlesTableModel articlesTableModel) {
		this.textField = new JTextField();
		this.prodotto = prodotto;
		this.articlesTableModel = articlesTableModel;
		if (prodotto != null) {
			this.textField.setText(Integer.toString(this.prodotto.getQuantita()));
		}
		this.initTableUpdate();
		if (maxValue != null) {
			PlainDocument doc = (PlainDocument) this.textField.getDocument();
			doc.setDocumentFilter(new AmountDocumentFilter(maxValue));
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
		this (altezzaCella, null, null, null);
	}
	
	public AmountCell (int altezzaCella, ArticlesTableModel articlesTableModel) {
		this (altezzaCella, null, null, articlesTableModel);
	}
	
	public AmountCell (int altezzaCella, Prodotto prodotto) {
		this (altezzaCella, prodotto, null, null);
	}
	
	public AmountCell (int altezzaCella, Prodotto prodotto, Integer maxValue) {
		this (altezzaCella, prodotto, maxValue, null);
	}
	
	private boolean initTableUpdate () {
		if (this.articlesTableModel != null && this.prodotto != null) {
			this.textField.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					if (!AmountCell.this.textField.getText().isEmpty()) {
						AmountCell.this.prodotto.setQuantita(Integer.parseInt(AmountCell.this.textField.getText()));
						articlesTableModel.fireTableDataChanged();
					}
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					AmountCell.this.prodotto.setQuantita(Integer.parseInt(AmountCell.this.textField.getText()));
					articlesTableModel.fireTableDataChanged();
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					AmountCell.this.prodotto.setQuantita(Integer.parseInt(AmountCell.this.textField.getText()));
					articlesTableModel.fireTableDataChanged();
				}
			});
			return true;
		}
		return false;
	}
	
	public void setProdotto (Prodotto prodotto) {
		this.prodotto = prodotto;
		this.textField.setText(Integer.toString(this.prodotto.getQuantita()));
		this.initTableUpdate();
	}
	
	public void setFilter (int maxValue) {
		PlainDocument doc = (PlainDocument) this.textField.getDocument();
		doc.setDocumentFilter(new AmountDocumentFilter(maxValue));	
	}
	
	public void setArticlesTableModel (ArticlesTableModel articlesTableModel) {
		this.articlesTableModel = articlesTableModel;
		this.initTableUpdate();
	}
}

class RemoveColumnRender implements TableCellRenderer {
	
	private int altezzaRiga;
	
    public RemoveColumnRender (int altezzaRiga) {
		this.altezzaRiga = altezzaRiga;
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return new RemoveCell(this.altezzaRiga);
	}
}

class RemoveColumnEditor extends DefaultCellEditor{
    private static final long serialVersionUID = -5785051616524283761L;
    
	private RemoveCell cellPanel;
	private ArrayList <Prodotto> articoli;

    public RemoveColumnEditor (ArrayList <Prodotto> articoli, int altezzaRiga) {
    	super (new JTextField());
    	this.articoli = articoli;
    	this.cellPanel = new RemoveCell(altezzaRiga);
    	this.cellPanel.setArticoli(this.articoli);
		
        this.setClickCountToStart(1);
    }

	@Override
	public Object getCellEditorValue() {
		return super.getCellEditorValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.cellPanel.setnArticolo(row);
        return this.cellPanel;
	}
	
	@Override
	public boolean stopCellEditing () {
		return super.stopCellEditing();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return super.shouldSelectCell(anEvent);
	}

	@Override
	public void cancelCellEditing() {
		super.cancelCellEditing();
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		super.addCellEditorListener(l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		super.removeCellEditorListener(l);
	}
}

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
		((ArticlesTableModel)((JTable)this.getParent()).getModel()).fireTableDataChanged();
	}
}