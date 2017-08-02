package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import negozio.Carrello;
import negozio.Magazzino;

public class JCartDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = 6774400022574447743L;
	
	private Carrello carrello;
	private Magazzino magazzino;

	private JButton emptyButton;
	private JButton payButton;
	private JCartTable jCartTable;
	private JFrame parentFrame;

	private static final String TITOLO = "Carrello";
	
	private static final int LARGHEZZA_MINIMA = 950;
	private static final int ALTEZZA_MINIMA = 600;
	private static final int MARGINE_SUPERIORE = 40;
	private static final int MARGINE_DESTRO = 40;
	private static final int MARGINE_SINISTRO = 40;
	private static final int MARGINE_INFERIORE = 20;
	private static final int SPAZIO_BOTTONI = 200;
	private static final int MARGINE_SUPERIORE_BOTTOMPANEL = 10;
	private static final String TESTO_BOTTONE_PAGA = "Paga";
	private static final String TESTO_BOTTONE_SVUOTA_CARRELLO = "Svuota carrello";
	
	public JCartDialog (JFrame parentFrame, Carrello carrello, Magazzino magazzino) {
		super (parentFrame, TITOLO, JDialog.ModalityType.DOCUMENT_MODAL);
//		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setMinimumSize(new Dimension(LARGHEZZA_MINIMA, ALTEZZA_MINIMA));
		this.setLocationRelativeTo(null);
//		this.setAlwaysOnTop (true);

		this.carrello = carrello;
		this.magazzino = magazzino;
		this.parentFrame = parentFrame;
		this.payButton = new JButton(TESTO_BOTTONE_PAGA);
		this.emptyButton = new JButton(TESTO_BOTTONE_SVUOTA_CARRELLO);
		this.jCartTable = new JCartTable(this.carrello);
		this.emptyButton.addActionListener(new EmptyCartListener(this.carrello, this.jCartTable.getModel()));
		this.payButton.addActionListener(this);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(this.emptyButton);
		buttonsPanel.add(Box.createHorizontalStrut(SPAZIO_BOTTONI));
		buttonsPanel.add(this.payButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE_BOTTOMPANEL), BorderLayout.PAGE_START);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createVerticalStrut(MARGINE_INFERIORE), BorderLayout.PAGE_END);
		
		JScrollPane scrollTable = new JScrollPane(this.jCartTable);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel jCartPanel = new JPanel (new BorderLayout());
		jCartPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_SINISTRO), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(MARGINE_DESTRO), BorderLayout.EAST);
		jCartPanel.add(bottomPanel, BorderLayout.PAGE_END);
		this.add(jCartPanel);
	}
	
	public class JCartTable extends JTable {
		private static final long serialVersionUID = 4734550632778588769L;

		private Carrello carrello;

		private static final int ALTEZZA_RIGA = 100;
		private static final String COLONNA_QUANTITA = "Quantità";
		private static final String COLONNA_BOTTONE = "";
		
		public JCartTable (Carrello carrello) {
			this.carrello = carrello;
			
			this.setModel(new ArticlesTableModel(this.carrello.getArticoli(), ALTEZZA_RIGA));
			this.setRowHeight(ALTEZZA_RIGA);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
			this.setDefaultRenderer(String.class, centerRenderer);
			this.setBackground(null);
			this.getColumn(COLONNA_QUANTITA).setCellRenderer(new AmountColumnRender(ALTEZZA_RIGA));
			this.getColumn(COLONNA_QUANTITA).setCellEditor(new AmountColumnEditor(this.carrello.getArticoli(), ALTEZZA_RIGA));
			this.getColumn(COLONNA_BOTTONE).setCellRenderer(new RemoveColumnRender(ALTEZZA_RIGA));
			this.getColumn(COLONNA_BOTTONE).setCellEditor(new RemoveColumnEditor(this.carrello.getArticoli(), ALTEZZA_RIGA));
			this.setFocusable(false);
			this.setRowSelectionAllowed(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		JPaymentDialog jPaymentDialog = new JPaymentDialog (this.parentFrame, this.carrello, this.magazzino);
		jPaymentDialog.setVisible (true);
	}
}