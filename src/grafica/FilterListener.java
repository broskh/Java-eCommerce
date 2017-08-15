package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import negozio.Magazzino;

public class FilterListener implements ActionListener {

	private JComboBox <String> filterType;
	private JFilterPanel filterPanel;
	private JClientContentPanel mainPanel;
	
	public FilterListener(JClientContentPanel mainPanel, JComboBox <String> filterType, JFilterPanel filterPanel) {
		this.mainPanel = mainPanel;
		this.filterType = filterType;
		this.filterPanel = filterPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.filterType.getSelectedItem().toString()) {
			case Magazzino.STRINGA_FILTRO_NOME:
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerNome(this.filterPanel.getText()));
				break;
			case Magazzino.STRINGA_FILTRO_MARCA:
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerMarca(this.filterPanel.getText()));
				break;
			case Magazzino.STRINGA_FILTRO_CODICE:
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerCodice(this.filterPanel.getText()));
				break;
			case Magazzino.STRINGA_FILTRO_CATEGORIA:
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerCategoria(this.filterPanel.getText()));
				break;
			case Magazzino.STRINGA_FILTRO_PREZZO:
				EstremiRange estremiPrezzo = this.filterPanel.getCost();
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerPrezzo(estremiPrezzo.getPrimo(), estremiPrezzo.getUltimo()));
				break;
			case Magazzino.STRINGA_FILTRO_QUANTITA:
				EstremiRange estremiQuantita = this.filterPanel.getAmount();
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerQuantita(estremiQuantita.getPrimo(), estremiQuantita.getUltimo()));
				break;
		}		
	}
}