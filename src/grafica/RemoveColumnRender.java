package grafica;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class RemoveColumnRender implements TableCellRenderer {
	
	private int altezzaRiga;
	
    public RemoveColumnRender (int altezzaRiga) {
		this.altezzaRiga = altezzaRiga;
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return new RemoveCell(this.altezzaRiga);
	}
}