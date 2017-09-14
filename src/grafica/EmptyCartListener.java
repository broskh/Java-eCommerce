package grafica;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import negozio.Carrello;

public class EmptyCartListener implements ActionListener {

	private Carrello cart;

	private Window windowCaller;
	private ProductsTableModel articlesTableModel;
	
	private static final String ALERT_TITLE = "Carrello svuotato";
	private static final String ALERT_TEXT = "Carrello svuotato correttamente.";
	
	public EmptyCartListener (Window windowCaller, 
			ProductsTableModel articlesTableModel, Carrello cart) {
		this.cart = cart;
		this.windowCaller = windowCaller;
		this.articlesTableModel = articlesTableModel;
	}
	
	public EmptyCartListener (Window windowCaller, Carrello cart) {
		this (windowCaller, null, cart);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.cart.svuota();
		if (this.articlesTableModel != null) {
			this.articlesTableModel.fireTableDataChanged();
		}
		JOptionPane.showMessageDialog(this.windowCaller, ALERT_TEXT,
				ALERT_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}
}