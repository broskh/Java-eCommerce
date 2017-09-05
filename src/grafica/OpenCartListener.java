package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negozio.Carrello;
import negozio.Magazzino;

public class OpenCartListener implements ActionListener {
	private Magazzino store;
	private Carrello cart;
	
	private JClientContentPanel clientContentPanel;
	
	public OpenCartListener(JClientContentPanel clientContentPanel, Carrello cart, Magazzino store) {
		 this.store = store;
		 this.cart = cart;
		 this.clientContentPanel = clientContentPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JCartDialog cartDialog = new JCartDialog(this.clientContentPanel, this.cart, 
				this.store);
		cartDialog.setVisible(true);
	}
}