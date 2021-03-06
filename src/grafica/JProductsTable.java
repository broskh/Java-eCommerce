package grafica;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.PlainDocument;

import negozio.Carrello;
import negozio.GestioneProdotti;
import negozio.Magazzino;
import negozio.Prodotto;

/**
 * JTable per la visualizzazione dei prodotti di un magazzino o di un carrello 
 * (a seconda della modalità indicata).
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class JProductsTable extends JTable{
	private static final long serialVersionUID = 8903295824672808583L;
	
	private GestioneProdotti productsManager;
	private int mode;
	
	private static final int ROW_HEIGHT = 100;
	
	public JProductsTable(Magazzino store) {
		this.productsManager = store;
		this.mode = ProductsTableModel.STORE_MODE;
		
		basicTable();
	}
	
	public JProductsTable (Carrello cart, Magazzino store) {
		this.productsManager = cart;
		this.mode = ProductsTableModel.CART_MODE;

		basicTable();
		TableColumn amountColumn = this.getColumn(ProductsTableModel.AMOUNT_COLUMN);
		amountColumn.setCellRenderer(new AmountColumnRender(ROW_HEIGHT));
		ProductsTableModel model = (ProductsTableModel) this.getModel();
		amountColumn.setCellEditor(
				new AmountColumnEditor(model, (Carrello)this.productsManager, store, ROW_HEIGHT));
	}
	
	private void basicTable () {
		this.setModel(new ProductsTableModel(this.productsManager.getProdotti(),
				this.mode));
		this.setRowHeight(ROW_HEIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		this.setDefaultRenderer(String.class, centerRenderer);

		TableColumn imageColumn = this.getColumn(ProductsTableModel.IMAGE_COLUMN);
		imageColumn.setCellRenderer(new ImageColumnRender(ROW_HEIGHT));
		TableColumn buttonColumn = this.getColumn(ProductsTableModel.BUTTON_COLUMN);
		buttonColumn.setCellRenderer(new RemoveColumnRender(ROW_HEIGHT));
		buttonColumn.setCellEditor(
				new RemoveColumnEditor(this.productsManager.getProdotti(), ROW_HEIGHT));			
		this.setFocusable(false);
		this.setRowSelectionAllowed(false);
	}

	public Prodotto getProductAtRow(int row) {
		return this.productsManager.getProdotti().get(row);
	}

	/**
	 * TableCellRenderer per la visualizzazione dell'icona del prodotto.
	 * 
	 * @author Alessio Scheri
	 * @version 1.0
	 *
	 */
	class ImageColumnRender implements TableCellRenderer {
		
		private int iconSize;
		
	    public ImageColumnRender (int iconSize) {
	    	this.iconSize = iconSize;
	    }
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			table.getColumn(table.getColumnName(column)).setMinWidth(this.iconSize);
			ImageCell imageCell = new ImageCell(this.iconSize, (File) value);
			ProductsTableModel.setCellBackgroundColor(imageCell, row);
			return imageCell;
		}
	}
	
	/**
	 * Cella per la visualizzazione dell'icona del prodotto.
	 * 
	 * @author Alessio Scheri
	 * @version 1.0
	 *
	 */
	class ImageCell extends JPanel {
		private static final long serialVersionUID = 2028713241711826134L;
	
		public ImageCell (int iconSize, File image) {
	
			JIconLabel imageLabel = new JIconLabel(image,iconSize);		
			
			JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			imagePanel.setOpaque(false);
			imagePanel.add(imageLabel);
	
			this.setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        this.add(imagePanel, gbc);
		}
	}
	
	/**
	 * CellEditor per la modifica della quantità del prodotto.
	 * 
	 * @author Alessio Scheri
	 * @version 1.0
	 *
	 */
	class AmountColumnEditor extends DefaultCellEditor{
	    private static final long serialVersionUID = 7039137261252411532L;
	    
		private Integer lastRowSelected;
		private Carrello cart;
		private Magazzino store;
		
		private AmountCell cellPanel;
		
		private static final int NULL_VALUE = -1;
	
	    public AmountColumnEditor (ProductsTableModel productsTableModel, 
	    		Carrello cart, Magazzino store, int cellHeight) {
	    	super (new JTextField());
	    	this.lastRowSelected = NULL_VALUE;
	    	this.cart = cart;
	    	this.store = store;
	    	this.cellPanel = new AmountCell(cellHeight, productsTableModel);
			
	        this.setClickCountToStart(1);
	    }
	
		@Override
		public Object getCellEditorValue() {
			return this.cart.getProdotti().get(this.lastRowSelected).getQuantita();
		}
	
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, 
				boolean isSelected, int row, int column) {
			table.getColumn(table.getColumnName(column)).setMinWidth(AmountCell.TEXTFIELD_WIDTH);
			this.lastRowSelected = row; 
			Prodotto product = this.cart.getProdotti().get(this.lastRowSelected);
			Prodotto storeProduct = this.store.getProdotto(product.getCodice());
			this.cellPanel.setFilter(storeProduct.getQuantita());
			this.cellPanel.setProduct(product);
			ProductsTableModel.setCellBackgroundColor(cellPanel, row);
	        return this.cellPanel;
		}
		
		@Override
		public boolean stopCellEditing () {
			Prodotto cartProduct = this.cart.getProdotti().get(this.lastRowSelected);
	    	cartProduct.setQuantita((int)this.getCellEditorValue());
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
	
	/**
	 * TableCellRenderer per la modifica della quantità del prodotto.
	 * 
	 * @author Alessio Scheri
	 * @version 1.0
	 *
	 */
	class AmountColumnRender implements TableCellRenderer {
		
		private int rowHeight;
		
	    public AmountColumnRender (int rowHeight) {
	    	this.rowHeight = rowHeight;
	    }
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			table.getColumn(table.getColumnName(column)).setMinWidth(AmountCell.TEXTFIELD_WIDTH);
			AmountCell amountCell = new AmountCell(
					this.rowHeight, ((JProductsTable) table).getProductAtRow(row));
			ProductsTableModel.setCellBackgroundColor(amountCell, row);
			return amountCell;
		}
	}
	
	/**
	 * Cella per la modifica della quantità del prodotto.
	 * 
	 * @author Alessio Scheri
	 * @version 1.0
	 *
	 */
	class AmountCell extends JPanel {
		private static final long serialVersionUID = 2028713241711826134L;
	
		private JTextField textField;
		private ProductsTableModel productsTableModel;
		private Prodotto product;
	
		public static final int TEXTFIELD_WIDTH = 50;
	
		public AmountCell (int rowHeight, Prodotto product, Integer maxValue, 
				ProductsTableModel productsTableModel) {
			this.textField = new JTextField();
			this.textField.setPreferredSize(new Dimension(
					TEXTFIELD_WIDTH, (int)this.textField.getPreferredSize().getHeight()));
			this.product = product;
			this.productsTableModel = productsTableModel;
			if (product != null) {
				this.textField.setText(Integer.toString(this.product.getQuantita()));
			}
			this.initTableUpdate();
			if (maxValue != null) {
				PlainDocument doc = (PlainDocument) this.textField.getDocument();
				doc.setDocumentFilter(new AmountDocumentFilter(maxValue));
			}
			
			JPanel mainPanel = new JPanel (new FlowLayout(FlowLayout.CENTER, 0, 0));
			mainPanel.setOpaque(false);
			mainPanel.add(this.textField);
			this.setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        this.add(mainPanel, gbc);
		}
		
		public AmountCell (int rowHeight, ProductsTableModel productsTableModel) {
			this (rowHeight, null, null, productsTableModel);
		}
		
		public AmountCell (int rowHeight, Prodotto product) {
			this (rowHeight, product, null, null);
		}	
		
		private boolean initTableUpdate () {
			if (this.productsTableModel != null && this.product != null) {
				this.textField.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						if (!AmountCell.this.textField.getText().isEmpty()) {
							AmountCell.this.product.setQuantita(Integer.parseInt(
									AmountCell.this.textField.getText()));
							productsTableModel.fireTableDataChanged();
						}
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						AmountCell.this.product.setQuantita(Integer.parseInt(
								AmountCell.this.textField.getText()));
						productsTableModel.fireTableDataChanged();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						AmountCell.this.product.setQuantita(Integer.parseInt(
								AmountCell.this.textField.getText()));
						productsTableModel.fireTableDataChanged();
					}
				});
				return true;
			}
			return false;
		}
		
		public void setProduct (Prodotto product) {
			this.product = product;
			this.textField.setText(Integer.toString(this.product.getQuantita()));
			this.initTableUpdate();
		}
		
		public Prodotto getProduct() {
			return this.product;
		}
		
		public void setProductsTableModel (ProductsTableModel productsTableModel) {
			this.productsTableModel = productsTableModel;
			this.initTableUpdate();
		}
		
		public ProductsTableModel getProductsTableModel () {
			return this.productsTableModel;
		}
		
		public void setFilter (int maxValue) {
			PlainDocument doc = (PlainDocument) this.textField.getDocument();
			doc.setDocumentFilter(new AmountDocumentFilter(maxValue));	
		}
	}
	
	/**
	 * TableCellRenderer per la rimozione della quantità del prodotto.
	 * 
	 * @author Alessio Scheri
	 * @version 1.0
	 *
	 */
	class RemoveColumnRender implements TableCellRenderer {
		
		private int rowHeight;
		
	    public RemoveColumnRender (int rowHeight) {
			this.rowHeight = rowHeight;
	    }
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			table.getColumn(table.getColumnName(column)).setMinWidth(RemoveCell.BUTTON_SIZE);
			RemoveCell removeCell =new RemoveCell(this.rowHeight);
			ProductsTableModel.setCellBackgroundColor(removeCell, row);
			return removeCell;
		}
	}
	
	/**
	 * CellEditor per la rimozione della quantità del prodotto.
	 * 
	 * @author Alessio Scheri
	 * @version 1.0
	 *
	 */
	class RemoveColumnEditor extends DefaultCellEditor {
	    private static final long serialVersionUID = -5785051616524283761L;
	
		private Integer lastRowSelected;
		private RemoveCell cellPanel;
		private ArrayList <Prodotto> products;
		
		private static final int NULL_VALUE = -1;
	
	    public RemoveColumnEditor (ArrayList <Prodotto> products, int rowHeight) {
	    	super (new JTextField());
	    	this.products = products;
	    	this.lastRowSelected = NULL_VALUE;
	    	this.cellPanel = new RemoveCell(rowHeight);
	    	this.cellPanel.setArticoli(this.products);
			
	        this.setClickCountToStart(1);
	    }
	
		@Override
		public Object getCellEditorValue() {
			return super.getCellEditorValue();
		}
	
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, 
				boolean isSelected, int row, int column) {
			table.getColumn(table.getColumnName(column)).setMinWidth(RemoveCell.BUTTON_SIZE);
			this.lastRowSelected = row; 
			this.cellPanel.setnArticolo(row);
			ProductsTableModel.setCellBackgroundColor(cellPanel, this.lastRowSelected);
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
	
	/**
	 * Cella per la rimozione della quantità del prodotto.
	 * 
	 * @author Alessio Scheri
	 * @version 1.0
	 *
	 */
	class RemoveCell extends JPanel implements ActionListener{	
		private static final long serialVersionUID = -3278269777576840442L;
	
		private JImageButton removeButton;
		
		private Integer nProduct;
		private ArrayList <Prodotto> products;
	
		public static final int BUTTON_SIZE = 36;
		
		private static final String BUTTON_TEXT = "Elimina";
		
		private static final String BUTTON_IMAGE_PATH = "media/img/small_bin_icon.png";
		
	    public RemoveCell (int cellHeight) {
	    	this (cellHeight, null, null);
	    }
		
	    public RemoveCell (int cellHeight, Integer nProduct, ArrayList <Prodotto> products) {
			this.products = products;
			this.nProduct = nProduct;
	    	this.removeButton = 
	    			new JImageButton(new File (BUTTON_IMAGE_PATH), BUTTON_SIZE, BUTTON_TEXT);
			
			JPanel removePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			removePanel.setOpaque(false);
			removePanel.add(this.removeButton);
	
			this.setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        this.add(removePanel, gbc);
	        
			this.addRemoveButtonActionListener();
	    }
	    
	    private boolean addRemoveButtonActionListener () {
	    	if (this.nProduct != null && this.products != null) {
				this.removeButton.addActionListener(this);
				return true;
			}
	    	return false;
	    }
	    
	    public Integer getnArticolo () {
			return this.nProduct;
		}
	
		public void setnArticolo (Integer nArticolo) {
			this.nProduct = nArticolo;
			this.removeButton.removeActionListener(this);
			this.addRemoveButtonActionListener();
		}
	
		public ArrayList <Prodotto> getArticoli() {
			return this.products;
		}
	
		public void setArticoli (ArrayList <Prodotto> articoli) {
			this.products = articoli;
			this.removeButton.removeActionListener(this);
			this.addRemoveButtonActionListener();
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(this.removeButton)) {
				this.products.remove((int)this.nProduct);
				JTable table = (JTable) this.getParent();
				((ProductsTableModel) table.getModel()).fireTableDataChanged ();
			}
		}
	}
}