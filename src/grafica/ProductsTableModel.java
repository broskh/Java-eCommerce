package grafica;

import java.awt.Color;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import negozio.Prodotto;

public class ProductsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -3450099361845613304L;
	
	private ArrayList <Prodotto> products;
	private int mode;

	public static final int CART_MODE = 1;
	public static final int STORE_MODE = 2;

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
	public static final String EACH_COST_COLUMN = "Prezzo cad.";
	public static final String EACH_DISCOUNTED_COST_COLUMN = "Prezzo cad. scontato";
	public static final String TOTAL_COST_COLUMN = "Prezzo totale";
	public static final String AMOUNT_COLUMN = "Quantità";
	public static final String BUTTON_COLUMN = "";
	
	private static final String [] CART_COLUMNS = {
			ProductsTableModel.IMAGE_COLUMN,
			ProductsTableModel.CODE_COLUMN,
			ProductsTableModel.NAME_COLUMN,
			ProductsTableModel.BRAND_COLUMN,
			ProductsTableModel.CATEGORY_COLUMN,
			ProductsTableModel.OFFER_COLUMN,
			ProductsTableModel.EACH_COST_COLUMN,
			ProductsTableModel.TOTAL_COST_COLUMN,
			ProductsTableModel.AMOUNT_COLUMN,
			ProductsTableModel.BUTTON_COLUMN
		};
	private static final String [] STORE_COLUMNS = {
			ProductsTableModel.IMAGE_COLUMN,
			ProductsTableModel.CODE_COLUMN,
			ProductsTableModel.NAME_COLUMN,
			ProductsTableModel.BRAND_COLUMN,
			ProductsTableModel.CATEGORY_COLUMN,
			ProductsTableModel.OFFER_COLUMN,
			ProductsTableModel.EACH_COST_COLUMN,
			ProductsTableModel.EACH_DISCOUNTED_COST_COLUMN,
			ProductsTableModel.AMOUNT_COLUMN,
			ProductsTableModel.BUTTON_COLUMN
		};
	private static final String NONE_OFFER_TEXT = "Nessuna";

	public static final Color EVEN_COLOR = Color.WHITE;
	public static final Color ODD_COLOR = new Color(242, 242, 242);
	
	public ProductsTableModel (ArrayList <Prodotto> products, int mode) {
		super ();
		this.products = products;
		this.mode = CART_MODE;
		if (mode == ProductsTableModel.CART_MODE || 
				mode == ProductsTableModel.STORE_MODE) {
			this.mode = mode;
		}
	}

	@Override
	public int getRowCount() {
		return this.products.size();
	}

	@Override
	public int getColumnCount() {
		if (this.mode == ProductsTableModel.CART_MODE) {
			return ProductsTableModel.CART_COLUMNS.length;
		}
		else if (this.mode == ProductsTableModel.STORE_MODE) {
			return ProductsTableModel.STORE_COLUMNS.length;
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case IMAGE_COLUMN_NUMBER:
				return this.products.get(rowIndex).getImmagine();
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
				if (this.mode == ProductsTableModel.CART_MODE) {
					return String.format("%.2f", this.products.get(
							rowIndex).prezzoCadaunoScontato());
				}
				else if (this.mode == ProductsTableModel.STORE_MODE) {
					return String.format("%.2f", this.products.get(
							rowIndex).getPrezzo());
				}
				break;
			case EACH_DISCOUNTED_COST_COLUMN_NUMBER:
				if (this.mode == ProductsTableModel.CART_MODE) {
					float cost = this.products.get(rowIndex).prezzoTotaleScontato();
					return String.format("%.2f", cost);
				}
				else if (this.mode == ProductsTableModel.STORE_MODE) {
					float cost = this.products.get(rowIndex).prezzoCadaunoScontato();
					return String.format("%.2f", cost);
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
		if (this.mode == ProductsTableModel.CART_MODE) {
			return ProductsTableModel.CART_COLUMNS [column];
		}
		else if (this.mode == ProductsTableModel.STORE_MODE) {
			return ProductsTableModel.STORE_COLUMNS [column];
		}
		return null;
	}

	@Override
	public boolean isCellEditable (int row, int column) {
		if (column == BUTTON_COLUMN_NUMBER) {
			return true;
		}
		else if (this.mode == CART_MODE && column == AMOUNT_COLUMN_NUMBER) {
			return true;
		}
		return false;
	}
	
	@Override
	public Class <?> getColumnClass (int column) {
		switch (column) {
			case IMAGE_COLUMN_NUMBER:
				return File.class;
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
	
	public static void setCellBackgroundColor (JPanel cell, int row) {
		if (row % 2 == 0) {
			cell.setBackground(EVEN_COLOR);
		}
		else {
			cell.setBackground(ODD_COLOR);
		}
	}
}