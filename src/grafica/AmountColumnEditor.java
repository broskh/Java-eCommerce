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
    
	private AmountCell cellPanel;
	private JTextField textField;
	
	private int oldValue;
	private int lastRowSelected;
	private ArrayList <Prodotto> articoli;

    public AmountColumnEditor (ArrayList <Prodotto> articoli) {
    	super (new JTextField());
    	this.lastRowSelected = -1;
    	this.oldValue = -1;
    	this.articoli = articoli;
    	this.textField = new JTextField();
    	this.cellPanel = new AmountCell(this.textField);        
		
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
		return this.textField.getText();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (this.oldValue == -1) {
    		this.oldValue = Integer.parseInt(value.toString());
    	}
    	this.lastRowSelected = row;
        this.textField.setText(value.toString());
        return this.cellPanel;
	}
	
	@Override
	public boolean stopCellEditing () {
		Prodotto articolo = this.articoli.get(this.lastRowSelected);    	            
    	articolo.setQuantita(Integer.parseInt((String)this.getCellEditorValue()));
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