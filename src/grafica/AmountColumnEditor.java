package grafica;

import java.awt.Component;

import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;

import negozio.Prodotto;

class AmountColumnEditor extends DefaultCellEditor{
    private static final long serialVersionUID = 7039137261252411532L;
    
	private int lastRowSelected;
	private ArrayList <Prodotto> articoli;
	
	private AmountCell cellPanel;
	
	private static final int VALORE_NULLO = -1;

    public AmountColumnEditor (ArrayList <Prodotto> articoli, int altezzaCella) {
    	super (new JTextField());
    	this.lastRowSelected = VALORE_NULLO;
    	this.articoli = articoli;
    	this.cellPanel = new AmountCell(altezzaCella);
		
        this.setClickCountToStart(1);
        
//		CellEditorListener changeNotification = new CellEditorListener() {
//	        public void editingCanceled(ChangeEvent e) {
//	            ;
//	        }
//
//	        public void editingStopped(ChangeEvent e) {
//	        	AmountFieldCellRendererEditor jTextFieldCellRenderer = (AmountFieldCellRendererEditor) e.getSource();
//	        	Prodotto articolo = jTextFieldCellRenderer.articoli.get(jTextFieldCellRenderer.lastRowSelected);    	            
//            	articolo.setQuantita(Integer.parseInt((String)jTextFieldCellRenderer.getCellEditorValue()));
//	        }
//	    };
//	    
//	    this.addCellEditorListener(changeNotification);
    }

	@Override
	public Object getCellEditorValue() {
		return this.cellPanel.getValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.lastRowSelected = row;
		this.cellPanel.setValue((Integer) value);
        return this.cellPanel;
	}
	
	@Override
	public boolean stopCellEditing () {
		Prodotto articolo = this.articoli.get(this.lastRowSelected);    	            
    	articolo.setQuantita((int)this.getCellEditorValue());
		return true;
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return super.shouldSelectCell(anEvent);
	}

	@Override
	public void cancelCellEditing() {
		super.cancelCellEditing();
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		super.addCellEditorListener(l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		super.removeCellEditorListener(l);
	}
}