package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import negozio.Carrello;
import negozio.Magazzino;

public class PaymentInformationListener implements ActionListener {

	private Carrello carrello;
	private Magazzino magazzino;
	private JFrame jFrame;
	
	public PaymentInformationListener (JFrame jFrame, Carrello carrello, Magazzino magazzino) {
		this.jFrame = jFrame;
		this.carrello = carrello;
		this.magazzino = magazzino;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPaymentDialog jPaymentDialog = new JPaymentDialog (this.jFrame, this.carrello, this.magazzino);
		jPaymentDialog.setVisible (true);
	}
}
