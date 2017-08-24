package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import negozio.Carrello;
import negozio.Magazzino;

public class OpenCartListener implements ActionListener {
	private Magazzino store;
	private Carrello cart;
	
	private JFrame mainFrame;
	
	public OpenCartListener(JFrame mainFrame, Carrello cart, Magazzino store) {
		 this.store = store;
		 this.cart = cart;
		 this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JCartDialog cartDialog = new JCartDialog(this.mainFrame, this.cart, 
				this.store);
		cartDialog.setVisible(true);
	}
}