package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import negozio.Carrello;
import negozio.Magazzino;

public class OpenCartDialogListener implements ActionListener {
	private Magazzino magazzino;
	private Carrello carrello;
	private JFrame mainFrame;
	
	public OpenCartDialogListener(JFrame mainFrame, Carrello carrello, Magazzino magazzino) {
		 this.magazzino = magazzino;
		 this.carrello = carrello;
		 this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JCartDialog cartDialog = new JCartDialog(this.mainFrame, this.carrello, this.magazzino);
		cartDialog.setVisible(true);
	}

}
