package grafica;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class RemoveColumnRender implements TableCellRenderer {
	
    public RemoveColumnRender () {
		;
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return new RemoveCell();
	}
}