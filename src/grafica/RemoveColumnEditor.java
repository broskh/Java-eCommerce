package grafica;

import java.awt.Component;

import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;

class RemoveColumnEditor extends DefaultCellEditor{
    private static final long serialVersionUID = -5785051616524283761L;
    
	private RemoveCell cellPanel;
	
	private int lastRowSelected;
	
	private static int VALORE_NULLO = -1;

    public RemoveColumnEditor (int altezzaRiga) {
    	super (new JTextField());
    	this.lastRowSelected = VALORE_NULLO;
    	this.cellPanel = new RemoveCell(altezzaRiga);        
		
        this.setClickCountToStart(1);
        this.editorComponent = this.cellPanel;
        
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
		this.lastRowSelected = row;
        return this.cellPanel;
	}
	
	@Override
	public boolean stopCellEditing () {
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