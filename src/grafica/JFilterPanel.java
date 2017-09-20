package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import negozio.Magazzino;

/**
 * Pannello per l'immissione delle informazioni necessarie per filtrare
 * un insieme di prodotti.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class JFilterPanel extends JPanel implements ChangeListener{
	private static final long serialVersionUID = 6101085833044744812L;
	
	private JTextField stringFilter;
	private JRangeSlider amountFilter;
	private JRangeSlider costFilter;
	
    private JLabel lowerValue;
    private JLabel upperValue;

	private static final int PANEL_WIDTH = 300;
	
	public JFilterPanel (int amountMax, double costMax, String filterType) {		
		this.stringFilter = new JTextField ();
		this.stringFilter.setPreferredSize (new Dimension(PANEL_WIDTH, 
				this.stringFilter.getPreferredSize().height));
		
		this.amountFilter = new JRangeSlider(0, amountMax);
		this.amountFilter.setPreferredSize (new Dimension(PANEL_WIDTH, 
				this.amountFilter.getPreferredSize().height));
		this.amountFilter.setLowerValue  (0);
		this.amountFilter.setUpperValue (amountMax);
		this.amountFilter.addChangeListener(this);
		this.amountFilter.setOpaque(false);

		int costMaxInt = (int) Math.ceil(costMax);
		this.costFilter = new JRangeSlider (0, costMaxInt);
		this.costFilter.setPreferredSize (new Dimension(PANEL_WIDTH, 
				this.costFilter.getPreferredSize().height));
		this.costFilter.setLowerValue (0);
		this.costFilter.setUpperValue (costMaxInt);
		this.costFilter.addChangeListener(this);
		this.costFilter.setOpaque(false);
		
		this.lowerValue = new JLabel();
		this.lowerValue.setHorizontalAlignment(JLabel.LEFT);
		this.upperValue = new JLabel();
		this.upperValue.setHorizontalAlignment(JLabel.LEFT);

		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.enableCorrectFilter(filterType);
	}
	
	public JFilterPanel (int amountMax, double costMax) {
		this (amountMax, costMax, Magazzino.STRINGA_FILTRO_CODICE);
	}
	
	private void showSlider (JRangeSlider slider) {
		this.removeAll();
		this.add(slider, BorderLayout.PAGE_START);
		this.lowerValue.setText(Integer.toString(slider.getLowerValue()));
		this.add(this.lowerValue, BorderLayout.WEST);
		this.upperValue.setText(Integer.toString(slider.getUpperValue()));
		this.add(this.upperValue, BorderLayout.EAST);
		this.updateUI();
	}
	
	public String getText () {
		return this.stringFilter.getText();
	}
	
	public RangeExtremes getAmount () {
		return new RangeExtremes(this.amountFilter.getLowerValue(), 
				this.amountFilter.getUpperValue());
	}
	
	public RangeExtremes getCost () {
		return new RangeExtremes(this.costFilter.getLowerValue(), 
				this.costFilter.getUpperValue());
	}
	
	private void enableStringFilter () {
		this.removeAll();
		this.add(this.stringFilter, BorderLayout.PAGE_START);
		this.updateUI();
	}
	
	private void enableAmountFilter () {
		this.showSlider(this.amountFilter);
	}
	
	private void enableCostFilter () {
		this.showSlider(this.costFilter);
	}
	
	public void enableCorrectFilter (String filterType) {
		switch (filterType) {
			case Magazzino.STRINGA_FILTRO_NOME:
			case Magazzino.STRINGA_FILTRO_MARCA:
			case Magazzino.STRINGA_FILTRO_CODICE:
			case Magazzino.STRINGA_FILTRO_CATEGORIA:
				this.enableStringFilter();
				break;
			case Magazzino.STRINGA_FILTRO_PREZZO:
				this.enableCostFilter();
				break;
			case Magazzino.STRINGA_FILTRO_QUANTITA:
				this.enableAmountFilter();
				break;
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JRangeSlider slider = (JRangeSlider) e.getSource();
        this.lowerValue.setText(String.valueOf(slider.getLowerValue()));
        this.upperValue.setText(String.valueOf(slider.getUpperValue()));
	}
}