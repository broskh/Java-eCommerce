package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negozio.Carrello;

public class EmptyCartListener implements ActionListener {

	private Carrello carrello;
	private ArticlesTableModel articlesTableModel;
	
	public EmptyCartListener (ArticlesTableModel articlesTableModel, Carrello carrello) {
		this.carrello = carrello;
		this.articlesTableModel = articlesTableModel;
	}
	
	public EmptyCartListener (Carrello carrello) {
		this (null, carrello);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.carrello.svuota();
		if (this.articlesTableModel != null) {
			this.articlesTableModel.fireTableDataChanged();
		}
	}
}