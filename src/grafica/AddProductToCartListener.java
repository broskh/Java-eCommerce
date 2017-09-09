package grafica;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negozio.Carrello;
import negozio.Magazzino;
import negozio.Prodotto;

public class AddProductToCartListener implements ActionListener {
	
	private Carrello cart;
	private Magazzino store;
	private StringBuilder code;
	private JTextField amount;
	private Window windowCaller;
	
	private static final String ALERT_TITLE = "Prodotto aggiunto";
	private static final String ALERT_TEXT = "Prodotto aggiunto correttamente al carrello.";
	
	public AddProductToCartListener (Window windowCaller, Magazzino store, 
			Carrello cart, StringBuilder code, JTextField amount) {
		this.store = store;
		this.cart = cart;
		this.code = code;
		this.amount = amount;
		this.windowCaller = windowCaller;
	}
	
	public AddProductToCartListener (Magazzino store, Carrello cart, 
			StringBuilder code, JTextField amount) {
		this (null, store, cart, code, amount);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Prodotto inStore = this.store.getProdotto(this.code.toString());
		if (inStore != null) {
			Prodotto inCart = this.cart.getProdotto(this.code.toString());
			int newAmount = Integer.parseInt(this.amount.getText());
//			controllo di poter inserire questa quantità
			if (inCart != null) {
				newAmount += inCart.getQuantita();
			}			
			if (newAmount > inStore.getQuantita()) {
				newAmount = inStore.getQuantita();
			}
			if (inCart != null) {
				newAmount -= inCart.getQuantita();
			}
			try {
				Prodotto newArticle = inStore.clone();
				newArticle.setQuantita(newAmount);
				this.cart.aggiungiProdotto(newArticle);
				if (this.windowCaller != null) {
					this.windowCaller.dispose();
				}
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(this.windowCaller, ALERT_TEXT,
				ALERT_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}
}