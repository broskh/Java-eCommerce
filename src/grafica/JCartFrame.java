package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.EventObject;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import negozio.Carrello;

public class JCartFrame extends JFrame {
	private static final long serialVersionUID = 6774400022574447743L;
	
	private Carrello carrello;

	private static final String TITOLO = "Carrello";
	
	private static final int LARGHEZZA_MINIMA = 800;
	private static final int ALTEZZA_MINIMA = 600;
	private static final int MARGINE_DESTRO = 40;
	private static final int MARGINE_SINISTRO = 40;
	
	public JCartFrame (Carrello carrello) {
		super (TITOLO);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setMinimumSize(new Dimension(LARGHEZZA_MINIMA, ALTEZZA_MINIMA));
//		this.setResizable(false);
		this.carrello = carrello;
		
		JPanel jCartPanel = new JPanel (new BorderLayout());
		JTable table = new JTable(new ArticlesTableModel(this.carrello.getArticoli()));
		table.setRowHeight(ArticlesTableModel.DIMENSIONE_ICONA);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		table.setBackground(null);
		table.getColumn("Quantità").setCellRenderer(new JTextFieldCellRenderer());
		table.getColumn("Quantità").setCellEditor(new JTextFieldCellRenderer());
        table.setFillsViewportHeight(true); 
		
		JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_SINISTRO), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_DESTRO), BorderLayout.EAST);
		this.add(jCartPanel);
		this.setVisible(true);
	}
	
	class JTextFieldCellRenderer implements TableCellRenderer, TableCellEditor
    {
        private JTextField textField;
 
        public JTextFieldCellRenderer ()
        {
            this.textField = new JTextField();
        }
 
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        		boolean hasFocus, final int row, final int column) {
            if (isSelected) {
                ;
            }
            else {
                ;
            }
 
            if (value == null || value.toString().length() == 0) {
                ;
            }
 
            this.textField.setText(value.toString());
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(Box.createVerticalStrut(40), BorderLayout.PAGE_START);
            panel.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
            panel.add(this.textField, BorderLayout.CENTER);
            panel.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
            panel.add(Box.createVerticalStrut(40), BorderLayout.PAGE_END);
            return panel;
        }

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isCellEditable(EventObject anEvent) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean shouldSelectCell(EventObject anEvent) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean stopCellEditing() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void cancelCellEditing() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addCellEditorListener(CellEditorListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeCellEditorListener(CellEditorListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return null;
		}
    }
}