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
		String filter = "";
		switch (this.filterType.toString()) {
			case Magazzino.STRINGA_FILTRO_NOME:
				filter = this.filterPanel.getText();
				this.articlesPanel.setViewedArticles(this.store.filtraPerNome(filter));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_MARCA:
				filter = this.filterPanel.getText();
				this.articlesPanel.setViewedArticles(this.store.filtraPerMarca(filter));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_CODICE:
				filter = this.filterPanel.getText();
				this.articlesPanel.setViewedArticles(this.store.filtraPerCodice(filter));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_CATEGORIA:
				filter = this.filterPanel.getText();
				this.articlesPanel.setViewedArticles(this.store.filtraPerCategoria(filter));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_PREZZO:
				RangeExtremes costRange = this.filterPanel.getCost();
				this.articlesPanel.setViewedArticles(
						this.store.filtraPerPrezzo(
								costRange.getFirst(), costRange.getLast()));
				this.articlesPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_QUANTITA:
				RangeExtremes amountRange = this.filterPanel.getAmount();
				this.articlesPanel.setViewedArticles(this.store.filtraPerQuantita(
								amountRange.getFirst(), amountRange.getLast()));
				this.articlesPanel.resetPagina();
				break;
		}
		if (this.windowCaller != null) {
			this.windowCaller.dispose();
		}
	}
}