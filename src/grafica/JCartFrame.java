package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
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

	private JButton emptyButton;
	private JButton payButton;

	private static final String TITOLO = "Carrello";
	
	private static final int LARGHEZZA_MINIMA = 800;
	private static final int ALTEZZA_MINIMA = 600;
	private static final int MARGINE_SUPERIORE = 40;
	private static final int MARGINE_DESTRO = 40;
	private static final int MARGINE_SINISTRO = 40;
	private static final int MARGINE_INFERIORE = 20;
	private static final int SPAZIO_BOTTONI = 200;
	private static final int MARGINE_SUPERIORE_BOTTOMPANEL = 10;

	private static final String COLONNA_QUANTITA = "Quantità";
	private static final String COLONNA_BOTTONE = "";
	private static final String TESTO_BOTTONE_PAGA = "Paga";
	private static final String TESTO_BOTTONE_SVUOTA_CARRELLO = "Svuota carrello";
	
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
		table.getColumn(COLONNA_QUANTITA).setCellRenderer(new AmountColumnRender());
		table.getColumn(COLONNA_QUANTITA).setCellEditor(new AmountColumnEditor(this.carrello.getArticoli()));
		table.getColumn(COLONNA_BOTTONE).setCellRenderer(new RemoveColumnRender());
		table.getColumn(COLONNA_BOTTONE).setCellEditor(new RemoveColumnEditor());
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);

		this.payButton = new JButton(TESTO_BOTTONE_PAGA);
		this.emptyButton = new JButton(TESTO_BOTTONE_SVUOTA_CARRELLO);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(this.emptyButton);
		buttonsPanel.add(Box.createHorizontalStrut(SPAZIO_BOTTONI));
		buttonsPanel.add(this.payButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE_BOTTOMPANEL), BorderLayout.PAGE_START);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createVerticalStrut(MARGINE_INFERIORE), BorderLayout.PAGE_END);
		
		JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jCartPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_SINISTRO), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_DESTRO), BorderLayout.EAST);
		jCartPanel.add(bottomPanel, BorderLayout.PAGE_END);
		this.add(jCartPanel);
		this.setVisible(true);
	}
}