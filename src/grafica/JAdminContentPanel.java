package grafica;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import negozio.Magazzino;  /**/

public class JAdminContentPanel extends JPanel {
	private static final long serialVersionUID = -3936116522061776556L;
	private JAdminControlPanel jAdminControlPanel;
	
	private Magazzino magazzino;  /**/
	
	/*private JButton emptyButton;
	private JButton payButton;*/
	
	
	
	//private static final int LARGHEZZA_MINIMA = 800;
	//private static final int ALTEZZA_MINIMA = 600;
	private static final int MARGINE_SUPERIORE = 40;
	private static final int MARGINE_DESTRO = 40;
	private static final int MARGINE_SINISTRO = 40;
	//private static final int MARGINE_INFERIORE = 20;
	//private static final int SPAZIO_BOTTONI = 200;
	//private static final int MARGINE_SUPERIORE_BOTTOMPANEL = 10;

	private static final String COLONNA_QUANTITA = "Quantità";
	private static final String COLONNA_BOTTONE = "";
	/*private static final String TESTO_BOTTONE_PAGA = "Paga";
	private static final String TESTO_BOTTONE_SVUOTA_CARRELLO = "Svuota carrello";*/
	
	
	public JAdminContentPanel(Magazzino magazzino)
	{
		this.magazzino = magazzino;   /**/
		
		this.setLayout(new BorderLayout());
		this.jAdminControlPanel = new JAdminControlPanel(magazzino);
		this.jAdminControlPanel.setBorder(new EtchedBorder());
		this.add(this.jAdminControlPanel, BorderLayout.PAGE_START);
		
		
		
		JPanel jTablePanel = new JPanel (new BorderLayout());
		JTable table = new JTable(new ArticlesTableModel(this.magazzino.getArticoli()));
		table.setRowHeight(ArticlesTableModel.DIMENSIONE_ICONA);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setBackground(null);
		table.getColumn(COLONNA_QUANTITA).setCellRenderer(new AmountColumnRender());
		table.getColumn(COLONNA_QUANTITA).setCellEditor(new AmountColumnEditor(this.magazzino.getArticoli()));
		table.getColumn(COLONNA_BOTTONE).setCellRenderer(new RemoveColumnRender());
		table.getColumn(COLONNA_BOTTONE).setCellEditor(new RemoveColumnEditor());
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);

		/*this.payButton = new JButton(TESTO_BOTTONE_PAGA);
		this.emptyButton = new JButton(TESTO_BOTTONE_SVUOTA_CARRELLO);*/
		
		/*JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(this.emptyButton);
		buttonsPanel.add(Box.createHorizontalStrut(SPAZIO_BOTTONI));
		buttonsPanel.add(this.payButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE_BOTTOMPANEL), BorderLayout.PAGE_START);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createVerticalStrut(MARGINE_INFERIORE), BorderLayout.PAGE_END);*/
		
		JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jTablePanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		jTablePanel.add(Box.createHorizontalStrut(MARGINE_SINISTRO), BorderLayout.WEST);
		jTablePanel.add(scrollTable, BorderLayout.CENTER);
		jTablePanel.add(Box.createHorizontalStrut(MARGINE_DESTRO), BorderLayout.EAST);
		//jCartPanel.add(bottomPanel, BorderLayout.PAGE_END);
		this.add(jTablePanel);
		this.setVisible(true);
		
	}

}