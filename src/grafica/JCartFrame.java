package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import negozio.Carrello;

public class JCartFrame extends JFrame {
	private static final long serialVersionUID = 6774400022574447743L;
	
	private Carrello carrello;

	private static final String TITOLO = "Carrello";
	
	private static final int LARGHEZZA_MINIMA = 800;
	private static final int ALTEZZA_MINIMA = 600;
	private static final int MARGINE_SUPERIORE = 40;
	private static final int MARGINE_DESTRO = 40;
	private static final int MARGINE_SINISTRO = 40;
	private static final int MARGINE_INFERIORE = 40;
	
	public JCartFrame (Carrello carrello) {
		super (TITOLO);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setMinimumSize(new Dimension(LARGHEZZA_MINIMA, ALTEZZA_MINIMA));
		this.setLocationRelativeTo(null);
		this.carrello = carrello;
		
		JPanel jCartPanel = new JPanel (new BorderLayout());
		JTable table = new JTable(new ArticlesTableModel(this.carrello.getArticoli()));
		table.setRowHeight(ArticlesTableModel.DIMENSIONE_ICONA);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setBackground(null);
		table.getColumn("Quantità").setCellRenderer(new AmountColumnRender());
		table.getColumn("Quantità").setCellEditor(new AmountColumnEditor(this.carrello.getArticoli()));
		table.getColumn("").setCellRenderer(new RemoveColumnRender());
		table.getColumn("").setCellEditor(new RemoveColumnEditor());
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		
		JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jCartPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_SINISTRO), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_DESTRO), BorderLayout.EAST);
		jCartPanel.add(Box.createVerticalStrut(MARGINE_INFERIORE), BorderLayout.PAGE_END);
		this.add(jCartPanel);
		this.setVisible(true);
	}
}