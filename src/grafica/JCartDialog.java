package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import negozio.Carrello;
import negozio.Magazzino;

public class JCartDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = 6774400022574447743L;

	private Magazzino store;
	private Carrello cart;
	
	private JClientContentPanel clientContentPanel;
	private JButton payButton;
	
	private static final int DIALOG_WIDTH = 950;
	private static final int DIALOG_HEIGHT = 600;
	private static final int BUTTONS_SPACE = 300;
	
	private static final int TOP_MARGIN = 30;
	private static final int SIDE_MARGIN = 30;
	private static final int BOTTOM_MARGIN = 20;
	private static final int BOTTOMPANEL_TOP_MARGIN = 10;
	
	private static final String TITLE = "Carrello";
	private static final String PAY_BUTTON_TEXT = "Paga";
	private static final String EMPTY_CART_BUTTON_TEXT = "Svuota carrello";
	
	public JCartDialog (JClientContentPanel clientContentPanel, Carrello cart, 
			Magazzino store) {
		super (SwingUtilities.getWindowAncestor(clientContentPanel), 
				TITLE, JDialog.ModalityType.DOCUMENT_MODAL);
		this.setMinimumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		this.setLocationRelativeTo(null);

		this.clientContentPanel = clientContentPanel;
		this.cart = cart;
		this.store = store;

		this.payButton = new JButton(PAY_BUTTON_TEXT);
		JButton emptyButton = new JButton(EMPTY_CART_BUTTON_TEXT);
		JProductsTable cartTable = new JProductsTable(cart, store);
		emptyButton.addActionListener(new EmptyCartListener(this, 
				(ProductsArticlesTableModel) cartTable.getModel(), cart));
		this.payButton.addActionListener(this);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(emptyButton);
		buttonsPanel.add(Box.createHorizontalStrut(BUTTONS_SPACE));
		buttonsPanel.add(this.payButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createVerticalStrut(BOTTOMPANEL_TOP_MARGIN), 
				BorderLayout.PAGE_START);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createVerticalStrut(BOTTOM_MARGIN), BorderLayout.PAGE_END);
		
		JScrollPane scrollTable = new JScrollPane(cartTable);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel jCartPanel = new JPanel (new BorderLayout());
		jCartPanel.add(Box.createVerticalStrut(TOP_MARGIN), BorderLayout.PAGE_START);
		jCartPanel.add(Box.createHorizontalStrut(SIDE_MARGIN), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(SIDE_MARGIN), BorderLayout.EAST);
		jCartPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		this.add(jCartPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.payButton)) {
			this.dispose();
			JPaymentDialog jPaymentDialog = new JPaymentDialog (
					this.clientContentPanel, this.cart, this.store);
			jPaymentDialog.setVisible (true);
		}
	}
}