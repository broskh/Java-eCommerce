package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import negozio.Carrello;
import negozio.Magazzino;  /**/
import negozio.Prodotto;

public class JAdminContentPanel extends JPanel {
	private static final long serialVersionUID = -3936116522061776556L;
	private JAdminControlPanel jAdminControlPanel;
	
	
	private Magazzino magazzino;  /**/
	private Carrello carrello;
	
	
	private JStoreTable jCartTable;
	
	
	//private static final int LARGHEZZA_MINIMA = 800;
	//private static final int ALTEZZA_MINIMA = 600;
	private static final int MARGINE_SUPERIORE = 40;
	private static final int MARGINE_DESTRO = 40;
	private static final int MARGINE_SINISTRO = 40;
	//private static final int MARGINE_INFERIORE = 20;
	//private static final int SPAZIO_BOTTONI = 200;
	//private static final int MARGINE_SUPERIORE_BOTTOMPANEL = 10;

	private static final String COLONNA_QUANTITA = "Quantit�";
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
		
		this.carrello = new Carrello();
		ArrayList <Prodotto> articoli = new <Prodotto> ArrayList();
		articoli = magazzino.getArticoli();
		for(int i = 0;i<articoli.size();i++)
		{
			carrello.aggiungiProdotto(articoli.get(i));
		}
		//System.out.println(carrello);
		
		
		this.jCartTable = new JStoreTable(this.magazzino);
		
		JScrollPane scrollTable = new JScrollPane(this.jCartTable);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
        JPanel jCartPanel = new JPanel (new BorderLayout());
		jCartPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_SINISTRO), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_DESTRO), BorderLayout.EAST);
		//jCartPanel.add(bottomPanel, BorderLayout.PAGE_END);
		this.add(jCartPanel);
	
	}
	
	public class JStoreTable extends JTable implements JProductTable
	{
		private static final long serialVersionUID = 4734550632778588769L;
		
		private Carrello carrello;
		private Magazzino magazzino;
		
		private static final int ALTEZZA_RIGA = 100;
		private static final String COLONNA_QUANTITA = "Quantità";
		private static final String COLONNA_BOTTONE = "";
		
		public JStoreTable(Magazzino magazzino)
		{
			this.carrello = carrello;
			this.magazzino = magazzino;
			
			this.setModel(new ArticlesTableModel(this.magazzino.getArticoli(), ALTEZZA_RIGA));
			this.setRowHeight(ALTEZZA_RIGA);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
			this.setDefaultRenderer(String.class, centerRenderer);
			this.setBackground(null);
//			this.getColumn(COLONNA_QUANTITA).setCellRenderer(new AmountColumnRender(ALTEZZA_RIGA));
//			this.getColumn(COLONNA_QUANTITA).setCellEditor(new AmountColumnEditor((ArticlesTableModel) this.getModel(), this.carrello, this.magazzino, ALTEZZA_RIGA));
			this.getColumn(COLONNA_BOTTONE).setCellRenderer(new RemoveColumnRender(ALTEZZA_RIGA));
			this.getColumn(COLONNA_BOTTONE).setCellEditor(new RemoveColumnEditor(this.magazzino.getArticoli(), ALTEZZA_RIGA));
			this.setFocusable(false);
			this.setRowSelectionAllowed(false);

		}

		@Override
		public Prodotto getProductAtRow(int row) {
			return this.magazzino.getArticoli().get(row);
		}
	}
}

