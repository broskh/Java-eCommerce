package grafica;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import negozio.Carrello;
import negozio.GestioneProdotti;
import negozio.Magazzino;
import negozio.Prodotto;

public class JProductsTable extends JTable{
	private static final long serialVersionUID = 8903295824672808583L;
	
	private GestioneProdotti productsManager;
	private int mode;
	
	private static final int ROW_HEIGHT = 100;
	
	public JProductsTable(Magazzino store) {
		this.productsManager = store;
		this.mode = ProductsArticlesTableModel.STORE_MODE;
		
		basicTable();
	}
	
	public JProductsTable (Carrello cart, Magazzino store) {
		this.productsManager = cart;
		this.mode = ProductsArticlesTableModel.CART_MODE;

		basicTable();
		this.getColumn(ProductsArticlesTableModel.AMOUNT_COLUMN)
		.setCellRenderer(new AmountColumnRender(ROW_HEIGHT));
		ProductsArticlesTableModel model = (ProductsArticlesTableModel) this.getModel();
		this.getColumn(ProductsArticlesTableModel.AMOUNT_COLUMN)
		.setCellEditor(new AmountColumnEditor(model, (Carrello)this.productsManager, store, ROW_HEIGHT));
	}
	
	private void basicTable () {
		this.setModel(new ProductsArticlesTableModel(this.productsManager.getArticoli(),
				this.mode));
		this.setRowHeight(ROW_HEIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		this.setDefaultRenderer(String.class, centerRenderer);
		this.getColumn(ProductsArticlesTableModel.IMAGE_COLUMN).setCellRenderer(
				new ImageColumnRender(ROW_HEIGHT));
		this.getColumn(ProductsArticlesTableModel.BUTTON_COLUMN).setCellRenderer(
				new RemoveColumnRender(ROW_HEIGHT));
		this.getColumn(ProductsArticlesTableModel.BUTTON_COLUMN).setCellEditor(
				new RemoveColumnEditor(
				this.productsManager.getArticoli(), ROW_HEIGHT));			
		this.setFocusable(false);
		this.setRowSelectionAllowed(false);
	}

	public Prodotto getProductAtRow(int row) {
		return this.productsManager.getArticoli().get(row);
	}
}