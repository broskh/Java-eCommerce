package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import negozio.Carrello;
import negozio.Magazzino;
import negozio.Prodotto;

public class JPaymentDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 8784508325649310067L;
	
	private Carrello cart;
	private Magazzino store;

	private JButton cancelButton;
	private JButton okButton;
	private JeCommerceFrame mainFrame;
	
	private static final int WIDTH = 640;
	private static final int HEIGHT = 320;
	private static final int TOP_MARGIN = 40;
	private static final int RIGHT_MARGIN = 40;
	private static final int LEFT_MARGIN = 40;
	private static final int BOTTOM_MARGIN = 20;
	private static final int BUTTONS_SPACE = 50;
	private static final int TOTALS_SPACE = 15;
	private static final int TOTALS_BOTTOM_MARGIN = 50;
	private static final int BOTTOMPANEL_TOP_MARGIN = 10;
	private static final int TOTAL_FONTS_SIZE = 20;

	private static final String PAYMENT_1_TEXT = "Bonifico";
	private static final String PAYMENT_2_TEXT = "Contrassegno";
	private static final String PAYMENT_3_TEXT = "Carta di credito";
	private static final String PAYMENT_5_TEXT = "PayPal";

	private static final String TOTAL_TEXT = "Totale: ";
	private static final String DISCOUNTED_TOTAL_TEXT = "Totale scontato: ";
	private static final String CURRENCY_SYMBOL = "€";
	
	private static final String OK_BUTTON_STRING = "Conferma";
	private static final String CANCEL_BUTTON_STRING = "Annulla";

	private static final String TITLE = "Pagamento";

	public JPaymentDialog (JFrame mainFrame, Carrello cart, Magazzino store) {
		super (mainFrame, TITLE, JDialog.ModalityType.DOCUMENT_MODAL);
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.cart = cart;
		this.store = store;
		this.mainFrame = (JeCommerceFrame) mainFrame;
		this.okButton = new JButton(OK_BUTTON_STRING);
		this.cancelButton = new JButton(CANCEL_BUTTON_STRING);
		this.okButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(this.cancelButton);
		buttonsPanel.add(Box.createHorizontalStrut(BUTTONS_SPACE));
		buttonsPanel.add(this.okButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createVerticalStrut(BOTTOMPANEL_TOP_MARGIN), BorderLayout.PAGE_START);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createVerticalStrut(BOTTOM_MARGIN), BorderLayout.PAGE_END);
		
		JRadioButton transferButton = new JRadioButton(PAYMENT_1_TEXT);
	    transferButton.setSelected(true);
	    JRadioButton cashOnDeliveryButton = new JRadioButton(PAYMENT_2_TEXT);
	    JRadioButton creditCardButton = new JRadioButton(PAYMENT_3_TEXT);
	    JRadioButton payPalButton = new JRadioButton(PAYMENT_5_TEXT);

	    ButtonGroup group = new ButtonGroup();
	    group.add(transferButton);
	    group.add(cashOnDeliveryButton);
	    group.add(creditCardButton);
	    group.add(payPalButton);
		
		JPanel choosePaymentPanel = new JPanel();
		choosePaymentPanel.add (transferButton);
		choosePaymentPanel.add (cashOnDeliveryButton);
		choosePaymentPanel.add (creditCardButton);
		choosePaymentPanel.add (payPalButton);

		JPanel completePanel = new JPanel();
		completePanel.setLayout(new BoxLayout(completePanel, BoxLayout.Y_AXIS));
		JLabel totalLabel = new JLabel(TOTAL_TEXT + String.format("%.2f", 
				this.cart.getTotale()) + CURRENCY_SYMBOL);
		totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		totalLabel.setFont(new Font(totalLabel.getFont().getName(), Font.PLAIN, 
				TOTAL_FONTS_SIZE));
		JLabel discountedTotalLabel = new JLabel(DISCOUNTED_TOTAL_TEXT + 
				String.format("%.2f", this.cart.getTotaleScontato()) + CURRENCY_SYMBOL);
		discountedTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		discountedTotalLabel.setFont(new Font(discountedTotalLabel.getFont()
				.getName(), Font.PLAIN, TOTAL_FONTS_SIZE));
		completePanel.add(totalLabel);
		completePanel.add(Box.createVerticalStrut(TOTALS_SPACE));
		completePanel.add(discountedTotalLabel);
		completePanel.add(Box.createVerticalStrut(TOTALS_BOTTOM_MARGIN));
		completePanel.add(choosePaymentPanel);
		
		JPanel paymentPanel = new JPanel (new BorderLayout());
		paymentPanel.add(Box.createVerticalStrut(TOP_MARGIN), BorderLayout.PAGE_START);
		paymentPanel.add(Box.createHorizontalStrut(LEFT_MARGIN), BorderLayout.WEST);
		paymentPanel.add(completePanel, BorderLayout.CENTER);
		paymentPanel.add(Box.createHorizontalStrut(RIGHT_MARGIN), BorderLayout.EAST);
		paymentPanel.add(bottomPanel, BorderLayout.PAGE_END);
		this.add(paymentPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.cancelButton) {
			this.setVisible(false);
		}
		else if (e.getSource() == this.okButton) {
			for (Prodotto articolo : this.cart.getArticoli()) {
				this.store.rimuoviProdotto(articolo.getCodice(), articolo.getQuantita());
			}
			this.cart.svuota();
			this.setVisible(false);
			((JClientContentPanel)this.mainFrame.getJContentPanel()).updateArticles();
		}
	}
}
