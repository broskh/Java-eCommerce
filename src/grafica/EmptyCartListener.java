package grafica;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import negozio.Carrello;

/**
 * ActionListener per svuotare il carrello.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class EmptyCartListener implements ActionListener {

	private Carrello cart;

	private Window windowCaller;
	private ProductsTableModel productsTableModel;
	
	private static final String ALERT_TITLE = "Carrello svuotato";
	private static final String ALERT_TEXT = "Carrello svuotato correttamente.";
	
	public EmptyCartListener (Window windowCaller, 
			ProductsTableModel productsTableModel, Carrello cart) {
		this.cart = cart;
		this.windowCaller = windowCaller;
		this.productsTableModel = productsTableModel;
	}
	
	public EmptyCartListener (Window windowCaller, Carrello cart) {
		this (windowCaller, null, cart);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.cart.svuota();
		if (this.productsTableModel != null) {
			this.productsTableModel.fireTableDataChanged();
		}
		JOptionPane.showMessageDialog(this.windowCaller, ALERT_TEXT,
				ALERT_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}
}