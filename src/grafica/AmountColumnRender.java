package grafica;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

class AmountColumnRender implements TableCellRenderer {
    public AmountColumnRender () {
    	;
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return new AmountCell(new JTextField(value.toString()));
	}
}