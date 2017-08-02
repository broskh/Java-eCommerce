package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

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

public class JPaymentDialog extends JDialog {
	private static final long serialVersionUID = 8784508325649310067L;
	
	private Carrello carrello;
	private Magazzino magazzino;

	private JButton cancelButton;
	private JButton confirmButton;

	private static final String TITOLO = "Pagamento";
	
	private static final int LARGHEZZA_MINIMA = 640;
	private static final int ALTEZZA_MINIMA = 320;
	private static final int MARGINE_SUPERIORE = 40;
	private static final int MARGINE_DESTRO = 40;
	private static final int MARGINE_SINISTRO = 40;
	private static final int MARGINE_INFERIORE = 20;
	private static final int SPAZIO_BOTTONI = 50;
	private static final int SPAZIO_TOTALI = 15;
	private static final int MARGINE_INFERIORE_TOTALI = 20;
	private static final int MARGINE_SUPERIORE_BOTTOMPANEL = 10;

	private static final String TESTO_BOTTONE_PAGAMENTO_1 = "Bonifico";
	private static final String TESTO_BOTTONE_PAGAMENTO_2 = "Contrassegno";
	private static final String TESTO_BOTTONE_PAGAMENTO_3 = "Carta di credito";
	private static final String TESTO_BOTTONE_PAGAMENTO_4 = "PayPal";

	private static final String TESTO_TOTALE = "Totale: ";
	private static final String TESTO_TOTALE_SCONTATO = "Totale scontato: ";
	private static final String SIMBOLO_VALUTA = "€";
	
	private static final String TESTO_BOTTONE_CONFERMA = "Conferma";
	private static final String TESTO_BOTTONE_ANNULLA = "Annulla";

	public JPaymentDialog (JFrame jframe, Carrello carrello, Magazzino magazzino) {
		super (jframe, TITOLO, JDialog.ModalityType.DOCUMENT_MODAL);
//		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setMinimumSize(new Dimension(LARGHEZZA_MINIMA, ALTEZZA_MINIMA));
		this.setLocationRelativeTo(null);
//		this.setAlwaysOnTop (true);

		this.carrello = carrello;
		this.magazzino = magazzino;
		this.confirmButton = new JButton(TESTO_BOTTONE_CONFERMA);
		this.cancelButton = new JButton(TESTO_BOTTONE_ANNULLA);
//		this.cancelButton.addActionListener(new EmptyCartListener(this.carrello, this.jCartTable.getModel()));
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(this.cancelButton);
		buttonsPanel.add(Box.createHorizontalStrut(SPAZIO_BOTTONI));
		buttonsPanel.add(this.confirmButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE_BOTTOMPANEL), BorderLayout.PAGE_START);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createVerticalStrut(MARGINE_INFERIORE), BorderLayout.PAGE_END);
		
		JRadioButton transferButton = new JRadioButton(TESTO_BOTTONE_PAGAMENTO_1);
	    transferButton.setSelected(true);
	    JRadioButton cashOnDeliveryButton = new JRadioButton(TESTO_BOTTONE_PAGAMENTO_2);
	    JRadioButton creditCardButton = new JRadioButton(TESTO_BOTTONE_PAGAMENTO_3);
	    JRadioButton payPalButton = new JRadioButton(TESTO_BOTTONE_PAGAMENTO_4);

	    //Group the radio buttons.
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

		String totalString = TESTO_TOTALE + this.carrello.getTotale() + SIMBOLO_VALUTA;
		String discountedTotalString = TESTO_TOTALE_SCONTATO + this.carrello.getTotaleScontato() + SIMBOLO_VALUTA;
		JPanel totalCostPanel = new JPanel();
		totalCostPanel.setLayout(new BoxLayout(totalCostPanel, BoxLayout.Y_AXIS));
		totalCostPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE));
		totalCostPanel.add(new JLabel(totalString));
		totalCostPanel.add(Box.createVerticalStrut(SPAZIO_TOTALI));
		totalCostPanel.add(new JLabel(discountedTotalString));
		totalCostPanel.add(Box.createVerticalStrut(MARGINE_INFERIORE_TOTALI));
		
		JPanel paymentPanel = new JPanel (new BorderLayout());
		paymentPanel.add(totalCostPanel, BorderLayout.PAGE_START);
		paymentPanel.add(Box.createHorizontalStrut(MARGINE_SINISTRO), BorderLayout.WEST);
		paymentPanel.add(choosePaymentPanel, BorderLayout.CENTER);
		paymentPanel.add(Box.createHorizontalStrut(MARGINE_DESTRO), BorderLayout.EAST);
		paymentPanel.add(bottomPanel, BorderLayout.PAGE_END);
		this.add(paymentPanel);
	}
}
