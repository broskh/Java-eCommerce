package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import negozio.Carrello;
import negozio.Magazzino;
import negozio.Prodotto;

public class ProductsArticlesTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -3450099361845613304L;
	
	private ArrayList <Prodotto> products;
	private int iconSize;
	private int mode;

	public static final int IMAGE_COLUMN_NUMBER = 0;
	public static final int CODE_COLUMN_NUMBER = 1;
	public static final int NAME_COLUMN_NUMBER = 2;
	public static final int BRAND_COLUMN_NUMBER = 3;
	public static final int CATEGORY_COLUMN_NUMBER = 4;
	public static final int OFFER_COLUMN_NUMBER = 5;
	public static final int EACH_COST_COLUMN_NUMBER = 6;
	public static final int EACH_DISCOUNTED_COST_COLUMN_NUMBER = 7;
	public static final int TOTAL_COST_COLUMN_NUMBER = 7;
	public static final int AMOUNT_COLUMN_NUMBER = 8;
	public static final int BUTTON_COLUMN_NUMBER = 9;

	public static final String IMAGE_COLUMN = "Immagine";
	public static final String CODE_COLUMN = "Codice";
	public static final String NAME_COLUMN = "Nome";
	public static final String BRAND_COLUMN = "Marca";
	public static final String CATEGORY_COLUMN = "Categoria";
	public static final String OFFER_COLUMN = "Offerta";
	public static final String EACH_COST_COLUMN = "Prezzo cadauno";
	public static final String EACH_DISCOUNTED_COST_COLUMN = "Prezzo cadauno scontato";
	public static final String TOTAL_COST_COLUMN = "Prezzo totale";
	public static final String AMOUNT_COLUMN = "Quantità";
	public static final String BUTTON_COLUMN = "";
	
	private static final String [] CART_COLUMNS = {
			ProductsArticlesTableModel.IMAGE_COLUMN,
			ProductsArticlesTableModel.CODE_COLUMN,
			ProductsArticlesTableModel.NAME_COLUMN,
			ProductsArticlesTableModel.BRAND_COLUMN,
			ProductsArticlesTableModel.CATEGORY_COLUMN,
			ProductsArticlesTableModel.OFFER_COLUMN,
			ProductsArticlesTableModel.EACH_COST_COLUMN,
			ProductsArticlesTableModel.TOTAL_COST_COLUMN,
			ProductsArticlesTableModel.AMOUNT_COLUMN,
			ProductsArticlesTableModel.BUTTON_COLUMN
		};
	private static final String [] STORE_COLUMNS = {
			ProductsArticlesTableModel.IMAGE_COLUMN,
			ProductsArticlesTableModel.CODE_COLUMN,
			ProductsArticlesTableModel.NAME_COLUMN,
			ProductsArticlesTableModel.BRAND_COLUMN,
			ProductsArticlesTableModel.CATEGORY_COLUMN,
			ProductsArticlesTableModel.OFFER_COLUMN,
			ProductsArticlesTableModel.EACH_COST_COLUMN,
			ProductsArticlesTableModel.EACH_DISCOUNTED_COST_COLUMN,
			ProductsArticlesTableModel.AMOUNT_COLUMN,
			ProductsArticlesTableModel.BUTTON_COLUMN
		};
	private static final String NONE_OFFER_TEXT = "Nessuna";

	public static final int CART_MODE = 1;
	public static final int STORE_MODE = 2;
	
	public ProductsArticlesTableModel (ArrayList <Prodotto> products, int iconSize, int mode) {
		super ();
		this.products = products;
		this.iconSize = iconSize;
		this.mode = CART_MODE;
		if (mode == ProductsArticlesTableModel.CART_MODE || mode == ProductsArticlesTableModel.STORE_MODE) {
			this.mode = mode;
		}
	}

	@Override
	public int getRowCount() {
		return this.products.size();
	}

	@Override
	public int getColumnCount() {
		if (this.mode == ProductsArticlesTableModel.CART_MODE) {
			return ProductsArticlesTableModel.CART_COLUMNS.length;
		}
		else if (this.mode == ProductsArticlesTableModel.STORE_MODE) {
			return ProductsArticlesTableModel.STORE_COLUMNS.length;
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case IMAGE_COLUMN_NUMBER:
				try {
					return new ImageIcon (new ResizableIcon(this.products.get(rowIndex)
							.getImmagine(), this.iconSize, this.iconSize).getBufferedImage());
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			case CODE_COLUMN_NUMBER:
				return this.products.get(rowIndex).getCodice();
			case NAME_COLUMN_NUMBER:
				return this.products.get(rowIndex).getNome();
			case BRAND_COLUMN_NUMBER:
				return this.products.get(rowIndex).getMarca();
			case CATEGORY_COLUMN_NUMBER:
				return this.products.get(rowIndex).getCategoria();
			case OFFER_COLUMN_NUMBER:
				if (this.products.get(rowIndex).getOfferta() != null) {
					return this.products.get(rowIndex).getOfferta().toString();
				}
				return NONE_OFFER_TEXT;
			case EACH_COST_COLUMN_NUMBER:
				if (this.mode == ProductsArticlesTableModel.CART_MODE) {
					return String.format("%.2f", this.products.get(
							rowIndex).prezzoCadaunoScontato());
				}
				else if (this.mode == ProductsArticlesTableModel.STORE_MODE) {
					return String.format("%.2f", this.products.get(
							rowIndex).getPrezzo());
				}
				break;
			case EACH_DISCOUNTED_COST_COLUMN_NUMBER:
				if (this.mode == ProductsArticlesTableModel.CART_MODE) {
					return String.format("%.2f", this.products.get(
							rowIndex).prezzoTotaleScontato());
				}
				else if (this.mode == ProductsArticlesTableModel.STORE_MODE) {
					return String.format("%.2f", this.products.get(
							rowIndex).prezzoCadaunoScontato());
				}
				break;
			case AMOUNT_COLUMN_NUMBER:
				return Integer.toString(this.products.get(rowIndex).getQuantita());
			case BUTTON_COLUMN_NUMBER:
				return new Object ();
		}
		return null;
	}

	@Override
	public String getColumnName (int column) {
		if (this.mode == ProductsArticlesTableModel.CART_MODE) {
			return ProductsArticlesTableModel.CART_COLUMNS [column];
		}
		else if (this.mode == ProductsArticlesTableModel.STORE_MODE) {
			return ProductsArticlesTableModel.STORE_COLUMNS [column];
		}
		return null;
	}

	@Override
	public boolean isCellEditable (int row, int column) {
		if (column == AMOUNT_COLUMN_NUMBER || column == BUTTON_COLUMN_NUMBER) {
			return true;
		}
		return false;
	}
	
	@Override
	public Class <?> getColumnClass (int column) {
		switch (column) {
			case IMAGE_COLUMN_NUMBER:
				return ImageIcon.class;
			case CODE_COLUMN_NUMBER:
				return String.class;
			case NAME_COLUMN_NUMBER:
				return String.class;
			case BRAND_COLUMN_NUMBER:
				return String.class;
			case CATEGORY_COLUMN_NUMBER:
				return String.class;
			case OFFER_COLUMN_NUMBER:
				return String.class;
			case EACH_COST_COLUMN_NUMBER:
				return String.class;
			case EACH_DISCOUNTED_COST_COLUMN_NUMBER:
				return String.class;
			case AMOUNT_COLUMN_NUMBER:
				return String.class;
			case BUTTON_COLUMN_NUMBER:
				return Object.class;
		}
		return null;
	}
}

class AmountColumnEditor extends DefaultCellEditor{
    private static final long serialVersionUID = 7039137261252411532L;
    
	private int lastRowSelected;
	private Carrello cart;
	private Magazzino store;
	
	private AmountCell cellPanel;
	
	private static final int NULL_VALUE = -1;

    public AmountColumnEditor (ProductsArticlesTableModel articlesTableModel, 
    		Carrello cart, Magazzino store, int cellHeight) {
    	super (new JTextField());
    	this.lastRowSelected = NULL_VALUE;
    	this.cart = cart;
    	this.store = store;
    	this.cellPanel = new AmountCell(cellHeight, articlesTableModel);
		
        this.setClickCountToStart(1);
    }

	@Override
	public Object getCellEditorValue() {
		return this.cart.getArticoli().get(this.lastRowSelected).getQuantita();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, 
			boolean isSelected, int row, int column) {
		this.lastRowSelected = row; 
		Prodotto product = this.cart.getArticoli().get(this.lastRowSelected);
		Prodotto storeProduct = this.store.getProdotto(product.getCodice());
		this.cellPanel.setFilter(storeProduct.getQuantita());
		this.cellPanel.setArticle(product);
        return this.cellPanel;
	}
	
	@Override
	public boolean stopCellEditing () {
		Prodotto cartArticle = this.cart.getArticoli().get(this.lastRowSelected);
    	cartArticle.setQuantita((int)this.getCellEditorValue());
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
	
	private int rowHeight;
	
    public AmountColumnRender (int rowHeight) {
    	this.rowHeight = rowHeight;
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, 
			Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return new AmountCell(this.rowHeight, ((JProductsTable) table).getProductAtRow(row));
	}
}

class AmountCell extends JPanel {
	private static final long serialVersionUID = 2028713241711826134L;

	private JTextField textField;
	private ProductsArticlesTableModel articlesTableModel;
	private Prodotto article;
	
	private static final int TEXTFIELD_WIDTH = 50;
	private static final int TEXTFIELD_HEIGHT = 22;
	private static final int SIDE_MARGIN = 10;
	private static final int GENERIC_MARGIN = 10;

	public AmountCell (int rowHeight, Prodotto article, Integer maxValue, 
			ProductsArticlesTableModel articlesTableModel) {
		this.textField = new JTextField();
		this.article = article;
		this.articlesTableModel = articlesTableModel;
		if (article != null) {
			this.textField.setText(Integer.toString(this.article.getQuantita()));
		}
		this.initTableUpdate();
		if (maxValue != null) {
			PlainDocument doc = (PlainDocument) this.textField.getDocument();
			doc.setDocumentFilter(new AmountDocumentFilter(maxValue));
		}
		
		this.textField.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		int topMargin = (rowHeight - TEXTFIELD_HEIGHT - GENERIC_MARGIN) / 2;
		
		JPanel mainPanel = new JPanel ();
		mainPanel.add(this.textField);
		
		this.setLayout (new BorderLayout());
        this.add(Box.createVerticalStrut(topMargin), BorderLayout.PAGE_START);
        this.add(Box.createHorizontalStrut(SIDE_MARGIN), BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(Box.createHorizontalStrut(SIDE_MARGIN), BorderLayout.EAST);
	}
	
	public AmountCell (int rowHeight) {
		this (rowHeight, null, null, null);
	}
	
	public AmountCell (int rowHeight, ProductsArticlesTableModel articlesTableModel) {
		this (rowHeight, null, null, articlesTableModel);
	}
	
	public AmountCell (int rowHeight, Prodotto article) {
		this (rowHeight, article, null, null);
	}
	
	public AmountCell (int rowHeight, Prodotto article, Integer maxValue) {
		this (rowHeight, article, maxValue, null);
	}
	
	private boolean initTableUpdate () {
		if (this.articlesTableModel != null && this.article != null) {
			this.textField.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					if (!AmountCell.this.textField.getText().isEmpty()) {
						AmountCell.this.article.setQuantita(Integer.parseInt(
								AmountCell.this.textField.getText()));
						articlesTableModel.fireTableDataChanged();
					}
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					AmountCell.this.article.setQuantita(Integer.parseInt(
							AmountCell.this.textField.getText()));
					articlesTableModel.fireTableDataChanged();
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					AmountCell.this.article.setQuantita(Integer.parseInt(
							AmountCell.this.textField.getText()));
					articlesTableModel.fireTableDataChanged();
				}
			});
			return true;
		}
		return false;
	}
	
	public void setArticle (Prodotto article) {
		this.article = article;
		this.textField.setText(Integer.toString(this.article.getQuantita()));
		this.initTableUpdate();
	}
	
	public void setArticlesTableModel (ProductsArticlesTableModel articlesTableModel) {
		this.articlesTableModel = articlesTableModel;
		this.initTableUpdate();
	}
	
	public Prodotto getArticle() {
		return this.article;
	}
	
	public ProductsArticlesTableModel getArticlesTableModel () {
		return this.articlesTableModel;
	}
	
	public void setFilter (int maxValue) {
		PlainDocument doc = (PlainDocument) this.textField.getDocument();
		doc.setDocumentFilter(new AmountDocumentFilter(maxValue));	
	}
}

class RemoveColumnRender implements TableCellRenderer {
	
	private int rowHeight;
	
    public RemoveColumnRender (int rowHeight) {
		this.rowHeight = rowHeight;
    }
    
    public void setRowHeight (int rowHeight) {
    	this.rowHeight = rowHeight;
    }
    
    public int getRowHeight () {
    	return this.rowHeight;
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, 
			boolean isSelected, boolean hasFocus, int row, int column) {
		return new RemoveCell(this.rowHeight);
	}
}

class RemoveColumnEditor extends DefaultCellEditor{
    private static final long serialVersionUID = -5785051616524283761L;
    
	private RemoveCell cellPanel;
	private ArrayList <Prodotto> articles;

    public RemoveColumnEditor (ArrayList <Prodotto> articles, int rowHeight) {
    	super (new JTextField());
    	this.articles = articles;
    	this.cellPanel = new RemoveCell(rowHeight);
    	this.cellPanel.setArticoli(this.articles);
		
        this.setClickCountToStart(1);
    }

	@Override
	public Object getCellEditorValue() {
		return super.getCellEditorValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, 
			boolean isSelected, int row, int column) {
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
	
	private Integer nArticle;
	private ArrayList <Prodotto> articles;

	private static final int BUTTON_SIZE = 28;
	private static final int GENERIC_MARGIN = 10;
	
	private static final String BUTTON_TEXT = "Remove";
	private static final String IMAGE_PATH = "media/img/remove.png";
	
    public RemoveCell (int cellHeight) {
    	this (cellHeight, null, null);
    }
	
    public RemoveCell (int cellHeight, Integer nArticle, ArrayList <Prodotto> articles) {
		this.articles = articles;
		this.nArticle = nArticle;
    	this.removeButton = new JButton();
		try {
		    Image image = ImageIO.read(new File (IMAGE_PATH));
		    this.removeButton.setIcon(new ImageIcon(image));
		} catch (Exception ex) {
			this.removeButton.setText(BUTTON_TEXT);
		}
		this.removeButton.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		
		int topMargin = (cellHeight - BUTTON_SIZE - GENERIC_MARGIN) / 2;
		
		JPanel removePanel = new JPanel();
		removePanel.add(this.removeButton);
		
		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(topMargin), BorderLayout.PAGE_START);
		this.add(removePanel);
		
		this.addRemoveButtonActionListener();
    }

	private boolean addRemoveButtonActionListener () {
    	if (this.nArticle != null && this.articles != null) {
			this.removeButton.addActionListener(this);
			return true;
		}
    	return false;
    }
    
    public Integer getnArticolo () {
		return this.nArticle;
	}

	public void setnArticolo (Integer nArticolo) {
		this.nArticle = nArticolo;
		this.addRemoveButtonActionListener();
	}

	public ArrayList <Prodotto> getArticoli() {
		return this.articles;
	}

	public void setArticoli (ArrayList <Prodotto> articoli) {
		this.articles = articoli;
		this.addRemoveButtonActionListener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.articles.remove((int)this.nArticle);
		((ProductsArticlesTableModel)((JTable)this.getParent()).getModel()).fireTableDataChanged();
	}
}