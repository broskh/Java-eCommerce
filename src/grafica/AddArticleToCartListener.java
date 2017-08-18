package grafica;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import negozio.Carrello;
import negozio.Magazzino;
import negozio.Prodotto;

public class AddArticleToCartListener implements ActionListener {
	
	private Carrello carrello;
	private Magazzino magazzino;
	private StringBuilder codice;
	private JTextField quantita;
	private Window windowCaller;
	
	public AddArticleToCartListener (Window windowCaller, Magazzino magazzino, Carrello carrello, StringBuilder codice, JTextField quantita) {
		this.magazzino = magazzino;
		this.carrello = carrello;
		this.codice = codice;
		this.quantita = quantita;
		this.windowCaller = windowCaller;
	}
	
	public AddArticleToCartListener (Magazzino magazzino, Carrello carrello, StringBuilder codice, JTextField quantita) {
		this (null, magazzino, carrello, codice, quantita);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println(this.codice.toString());
		Prodotto inMagazzino = this.magazzino.getProdotto(this.codice.toString());
		if (inMagazzino != null) {
			Prodotto giaInCarrello = this.carrello.getProdotto(this.codice.toString());
			int quantita = Integer.parseInt(this.quantita.getText());
//			controllo di poter inserire questa quantità
			if (giaInCarrello != null) {
				quantita += giaInCarrello.getQuantita();
			}			
			if (quantita > inMagazzino.getQuantita()) {
				quantita = inMagazzino.getQuantita();
			}
			if (giaInCarrello != null) {
				quantita -= giaInCarrello.getQuantita();
			}
			System.out.println("hey1");
			try {
				System.out.println("hey2");
				Prodotto nuovoProdotto = inMagazzino.clone();
				nuovoProdotto.setQuantita(quantita);
				this.carrello.aggiungiProdotto(nuovoProdotto);
				if (this.windowCaller != null) {
					this.windowCaller.dispose();
				}
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		}
	}
}