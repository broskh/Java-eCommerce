package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import negozio.Magazzino;

public class JFilterPanel extends JPanel implements ChangeListener{
	private static final long serialVersionUID = 6101085833044744812L;

	private static final int LARGHEZZA = 300;
	
	private JTextField stringFilter;
	private JRangeSlider amountFilter;
	private JRangeSlider costFilter;
	
    private JLabel lowerValue;
    private JLabel upperValue;
	
	public JFilterPanel (int amountMax, double costMax, String filterType) {
		int costMaxInt = (int) Math.ceil(costMax);
		
		this.stringFilter = new JTextField ();
		this.stringFilter.setPreferredSize (new Dimension(LARGHEZZA, this.stringFilter.getPreferredSize().height));
		
		this.amountFilter = new JRangeSlider(0, amountMax);
		this.amountFilter.setPreferredSize (new Dimension(LARGHEZZA, this.amountFilter.getPreferredSize().height));
		this.amountFilter.setLowerValue  (0);
		this.amountFilter.setUpperValue (amountMax);
		this.amountFilter.addChangeListener(this);
		
		this.costFilter = new JRangeSlider (0, costMaxInt);
		this.costFilter.setPreferredSize (new Dimension(LARGHEZZA, this.costFilter.getPreferredSize().height));
		this.costFilter.setLowerValue (0);
		this.costFilter.setUpperValue (costMaxInt);
		this.costFilter.addChangeListener(this);
		
		this.lowerValue = new JLabel();
		this.lowerValue.setHorizontalAlignment(JLabel.LEFT);
		this.upperValue = new JLabel();
		this.upperValue.setHorizontalAlignment(JLabel.LEFT);

		this.setLayout(new BorderLayout());
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
	
	public EstremiRange getAmount () {
		return new EstremiRange(this.amountFilter.getLowerValue(), this.amountFilter.getUpperValue());
	}
	
	public EstremiRange getCost () {
		return new EstremiRange(this.costFilter.getLowerValue(), this.costFilter.getUpperValue());
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