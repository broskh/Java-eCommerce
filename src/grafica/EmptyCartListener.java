package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.TableModel;

import negozio.Carrello;

public class EmptyCartListener implements ActionListener {

	private Carrello carrello;
	private ArticlesTableModel tableModel;
	
	public EmptyCartListener (Carrello carrello) {
		this (carrello, null);
	}
	
	public EmptyCartListener (Carrello carrello, TableModel tableModel) {
		this.carrello = carrello;
		this.tableModel = (ArticlesTableModel) tableModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.carrello.svuota();
		if (this.tableModel != null) {
			this.tableModel.fireTableDataChanged();
		}
	}
}