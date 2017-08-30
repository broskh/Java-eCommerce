package grafica;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negozio.Magazzino;

public class FilterListener implements ActionListener {

	private StringBuilder filterType;
	private JFilterPanel filterPanel;
	private JClientContentPanel mainPanel;
	private Window windowCaller;
	
	public FilterListener(Window windowCaller, JClientContentPanel mainPanel, 
			StringBuilder filterType, JFilterPanel filterPanel) {
		this.mainPanel = mainPanel;
		this.filterType = filterType;
		this.filterPanel = filterPanel;
		this.windowCaller = windowCaller;
	}
	
	public FilterListener(JClientContentPanel mainPanel, StringBuilder filterType, 
			JFilterPanel filterPanel) {
		this(null, mainPanel, filterType, filterPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.filterType.toString()) {
			case Magazzino.STRINGA_FILTRO_NOME:
				this.mainPanel.setViewedArticles(
						this.mainPanel.getStore ().filtraPerNome(this.filterPanel.getText()));
				this.mainPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_MARCA:
				this.mainPanel.setViewedArticles(
						this.mainPanel.getStore ().filtraPerMarca(this.filterPanel.getText()));
				this.mainPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_CODICE:
				this.mainPanel.setViewedArticles(
						this.mainPanel.getStore ().filtraPerCodice(this.filterPanel.getText()));
				this.mainPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_CATEGORIA:
				this.mainPanel.setViewedArticles(
						this.mainPanel.getStore ().filtraPerCategoria(this.filterPanel.getText()));
				this.mainPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_PREZZO:
				EstremiRange estremiPrezzo = this.filterPanel.getCost();
				this.mainPanel.setViewedArticles(
						this.mainPanel.getStore ().filtraPerPrezzo(
								estremiPrezzo.getPrimo(), estremiPrezzo.getUltimo()));
				this.mainPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_QUANTITA:
				EstremiRange estremiQuantita = this.filterPanel.getAmount();
				this.mainPanel.setViewedArticles(
						this.mainPanel.getStore ().filtraPerQuantita(
								estremiQuantita.getPrimo(), estremiQuantita.getUltimo()));
				this.mainPanel.resetPagina();
				break;
		}
		if (this.windowCaller != null) {
			this.windowCaller.dispose();
		}
	}
}