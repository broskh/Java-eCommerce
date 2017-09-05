package grafica;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negozio.Magazzino;

public class FilterListener implements ActionListener {

	private Magazzino store;
	
	private StringBuilder filterType;
	private JFilterPanel filterPanel;
	private JClientContentPanel articlesPanel;
	private Window windowCaller;
	
	public FilterListener(Window windowCaller, JClientContentPanel articlesPanel, 
			StringBuilder filterType, JFilterPanel filterPanel, Magazzino store) {
		this.articlesPanel = articlesPanel;
		this.filterType = filterType;
		this.filterPanel = filterPanel;
		this.windowCaller = windowCaller;
		this.store = store;
		
	}
	
	public FilterListener(JClientContentPanel articlesPanel, StringBuilder filterType, 
			JFilterPanel filterPanel, Magazzino store) {
		this(null, articlesPanel, filterType, filterPanel, store);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.filterType.toString()) {
			case Magazzino.STRINGA_FILTRO_NOME:
				this.articlesPanel.setViewedArticles(
						this.store.filtraPerNome(this.filterPanel.getText()));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_MARCA:
				this.articlesPanel.setViewedArticles(
						this.store.filtraPerMarca(this.filterPanel.getText()));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_CODICE:
				this.articlesPanel.setViewedArticles(
						this.store.filtraPerCodice(this.filterPanel.getText()));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_CATEGORIA:
				this.articlesPanel.setViewedArticles(
						this.store.filtraPerCategoria(this.filterPanel.getText()));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_PREZZO:
				RangeExtremes estremiPrezzo = this.filterPanel.getCost();
				this.articlesPanel.setViewedArticles(
						this.store.filtraPerPrezzo(
								estremiPrezzo.getFirst(), estremiPrezzo.getLast()));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_QUANTITA:
				RangeExtremes estremiQuantita = this.filterPanel.getAmount();
				this.articlesPanel.setViewedArticles(
						this.store.filtraPerQuantita(
								estremiQuantita.getFirst(), estremiQuantita.getLast()));
				this.articlesPanel.resetPagina();
				break;
		}
		if (this.windowCaller != null) {
			this.windowCaller.dispose();
		}
	}
}