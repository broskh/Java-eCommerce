package grafica;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negozio.Magazzino;

/**
 * ActionListener per il filtraggio di un insieme di prodotti.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class FilterListener implements ActionListener {

	private Magazzino store;
	
	private StringBuilder filterType;
	private JFilterPanel filterPanel;
	private JClientContentPanel productsPanel;
	private Window windowCaller;
	
	public FilterListener(Window windowCaller, JClientContentPanel productsPanel, 
			StringBuilder filterType, JFilterPanel filterPanel, Magazzino store) {
		this.productsPanel = productsPanel;
		this.filterType = filterType;
		this.filterPanel = filterPanel;
		this.windowCaller = windowCaller;
		this.store = store;
		
	}
	
	public FilterListener(JClientContentPanel productsPanel, StringBuilder filterType, 
			JFilterPanel filterPanel, Magazzino store) {
		this(null, productsPanel, filterType, filterPanel, store);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String filter = "";
		switch (this.filterType.toString()) {
			case Magazzino.STRINGA_FILTRO_NOME:
				filter = this.filterPanel.getText();
				this.productsPanel.setViewedProducts(this.store.filtraPerNome(filter));
				this.productsPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_MARCA:
				filter = this.filterPanel.getText();
				this.productsPanel.setViewedProducts(this.store.filtraPerMarca(filter));
				this.productsPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_CODICE:
				filter = this.filterPanel.getText();
				this.productsPanel.setViewedProducts(this.store.filtraPerCodice(filter));
				this.productsPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_CATEGORIA:
				filter = this.filterPanel.getText();
				this.productsPanel.setViewedProducts(this.store.filtraPerCategoria(filter));
				this.productsPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_PREZZO:
				RangeExtremes costRange = this.filterPanel.getCost();
				this.productsPanel.setViewedProducts(
						this.store.filtraPerPrezzo(
								costRange.getFirst(), costRange.getLast()));
				this.productsPanel.resetPagina();
				break;
			case Magazzino.STRINGA_FILTRO_QUANTITA:
				RangeExtremes amountRange = this.filterPanel.getAmount();
				this.productsPanel.setViewedProducts(this.store.filtraPerQuantita(
								amountRange.getFirst(), amountRange.getLast()));
				this.productsPanel.resetPagina();
				break;
		}
		if (this.windowCaller != null) {
			this.windowCaller.dispose();
		}
	}
}