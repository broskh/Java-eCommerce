package grafica;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negozio.Carrello;
import negozio.Magazzino;
import negozio.Prodotto;

/**
 * ActionListener per l'aggiunta di un prodotto al carrello.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class AddProductToCartListener implements ActionListener {
	
	private Carrello cart;
	private Magazzino store;
	private StringBuilder code;
	private JTextField amount;
	private Window windowCaller;
	
	private static final String ALERT_TITLE = "Prodotto aggiunto";
	private static final String ALERT_TEXT = "Prodotto aggiunto correttamente al carrello.";
	private static final String ALERT_NO_AMOUNT_TITLE = "Attenzione";
	private static final String ALERT_NO_AMOUNT_TEXT = 
			"Inserire un valore valido nel campo dedicato alla quantità.";
	
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
			if (newAmount == 0) {
				JOptionPane.showMessageDialog(this.windowCaller, ALERT_NO_AMOUNT_TEXT,
						ALERT_NO_AMOUNT_TITLE, JOptionPane.ERROR_MESSAGE);
				return;
			}
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
				Prodotto newProduct = inStore.clone();
				newProduct.setQuantita(newAmount);
				this.cart.aggiungiProdotto(newProduct);
				if (this.windowCaller != null) {
					this.windowCaller.dispose();
				}
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			if (this.windowCaller != null && this.windowCaller.getClass().equals(JAddProductToCartDialog.class)) {
				JOptionPane.showMessageDialog(this.windowCaller, ALERT_TEXT,
						ALERT_TITLE, JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}