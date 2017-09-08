package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negozio.Carrello;

public class EmptyCartListener implements ActionListener {

	private Carrello cart;
	private ProductsArticlesTableModel articlesTableModel;
	
	public EmptyCartListener (ProductsArticlesTableModel articlesTableModel, Carrello cart) {
		this.cart = cart;
		this.articlesTableModel = articlesTableModel;
	}
	
	public EmptyCartListener (Carrello cart) {
		this (null, cart);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.cart.svuota();
		if (this.articlesTableModel != null) {
			this.articlesTableModel.fireTableDataChanged();
		}
	}
}