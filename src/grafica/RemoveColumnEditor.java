package grafica;

import java.awt.Component;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;

import negozio.Prodotto;

class RemoveColumnEditor extends DefaultCellEditor{
    private static final long serialVersionUID = -5785051616524283761L;
    
	private RemoveCell cellPanel;
	private ArrayList <Prodotto> articoli;

    public RemoveColumnEditor (ArrayList <Prodotto> articoli, int altezzaRiga) {
    	super (new JTextField());
    	this.articoli = articoli;
    	this.cellPanel = new RemoveCell(altezzaRiga);
    	this.cellPanel.setArticoli(this.articoli);
		
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
		return super.getCellEditorValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.cellPanel.setnArticolo(row);
        return this.cellPanel;
	}
	
	@Override
	public boolean stopCellEditing () {
		return super.stopCellEditing();
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