package grafica;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import negozio.Magazzino;  
import negozio.Prodotto;

public class JAdminContentPanel extends JPanel {
	private static final long serialVersionUID = -3936116522061776556L;
	private JAdminControlPanel jAdminControlPanel;

	private Magazzino magazzino;  
	private JStoreTable jStoreTable;

	private static final int TOP_MARGIN = 40;
	private static final int RIGHT_MARGIN = 40;
	private static final int LEFT_MARGIN = 40;

	public JAdminContentPanel(JFrame mainFrame,Magazzino magazzino)
	{
		this.magazzino = magazzino;  
		this.setLayout(new BorderLayout());
		this.jAdminControlPanel = new JAdminControlPanel(magazzino,mainFrame);
		this.jAdminControlPanel.setBorder(new EtchedBorder());
		this.add(this.jAdminControlPanel, BorderLayout.PAGE_START);

		this.jStoreTable = new JStoreTable(this.magazzino);
		
		JScrollPane scrollTable = new JScrollPane(this.jStoreTable);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
        JPanel jCartPanel = new JPanel (new BorderLayout());
		jCartPanel.add(Box.createVerticalStrut(TOP_MARGIN), BorderLayout.PAGE_START);
		jCartPanel.add(Box.createHorizontalStrut(LEFT_MARGIN), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(RIGHT_MARGIN), BorderLayout.EAST);
		this.add(jCartPanel);
	
	}
	
	public class JStoreTable extends JTable implements JProductTable
	{
		private static final long serialVersionUID = 4734550632778588769L;
		
		private Magazzino magazzino;
		
		private static final int LINE_HEIGHT = 100;
		//private static final String AMOUNT_COLUMN = "Quantità";
		private static final String BUTTON_COLUMN = "";
		
		public JStoreTable(Magazzino magazzino)
		{
			this.magazzino = magazzino;
			
			this.setModel(new ArticlesTableModel(this.magazzino.getArticoli(), LINE_HEIGHT));
			this.setRowHeight(LINE_HEIGHT);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
			this.setDefaultRenderer(String.class, centerRenderer);
			this.setBackground(null);
//			this.getColumn(AMOUNT_COLUMN).setCellRenderer(new AmountColumnRender(LINE_HEIGHT));
//			this.getColumn(AMOUNT_COLUMN).setCellEditor(new AmountColumnEditor((ArticlesTableModel) this.getModel(), this.carrello, this.magazzino, LINE_HEIGHT));
			this.getColumn(BUTTON_COLUMN).setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
			this.getColumn(BUTTON_COLUMN).setCellEditor(new RemoveColumnEditor(
					this.magazzino.getArticoli(), LINE_HEIGHT));
			
			this.setFocusable(false);
			this.setRowSelectionAllowed(false);

		}

		@Override
		public Prodotto getProductAtRow(int row) {
			return this.magazzino.getArticoli().get(row);
		}
	}
}

