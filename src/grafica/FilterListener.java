package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FilterListener implements ActionListener {

	private JComboBox <String> filterType;
	private JTextField filterString;
	private JClientContentPanel mainPanel;

	public static final String NAME_FILTER_STRING = "Nome";
	public static final String BRAND_FILTER_STRING = "Marca";
	public static final String CODE_FILTER_STRING = "Codice";
	public static final String CATEGORY_FILTER_STRING = "Categoria";
	public static final String COST_FILTER_STRING = "Prezzo";
	public static final String AMOUNT_FILTER_STRING = "Quantità";
	
	public FilterListener(JClientContentPanel mainPanel, JComboBox <String> filterType, JTextField filterString) {
		this.mainPanel = mainPanel;
		this.filterType = filterType;
		this.filterString = filterString;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.filterType.getSelectedItem().toString()) {
			case FilterListener.NAME_FILTER_STRING:
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerNome(this.filterString.getText()));
				break;
			case FilterListener.BRAND_FILTER_STRING:
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerMarca(this.filterString.getText()));
				break;
			case FilterListener.CODE_FILTER_STRING:
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerCodice(this.filterString.getText()));
				break;
			case FilterListener.CATEGORY_FILTER_STRING:
				this.mainPanel.setArticoliVisualizzati(this.mainPanel.getMagazzino ().filtraPerCategoria(this.filterString.getText()));
				break;
			case FilterListener.COST_FILTER_STRING:
				break;
			case FilterListener.AMOUNT_FILTER_STRING:
				break;
		}		
	}
}