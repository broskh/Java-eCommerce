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
	
	private Carrello carrello;
	private Magazzino magazzino;

	private JButton cancelButton;
	private JButton confirmButton;
	private JeCommerceFrame mainFrame;

	private static final String TITOLO = "Pagamento";
	
	private static final int LARGHEZZA_MINIMA = 640;
	private static final int ALTEZZA_MINIMA = 320;
	private static final int MARGINE_SUPERIORE = 40;
	private static final int MARGINE_DESTRO = 40;
	private static final int MARGINE_SINISTRO = 40;
	private static final int MARGINE_INFERIORE = 20;
	private static final int SPAZIO_BOTTONI = 50;
	private static final int SPAZIO_TOTALI = 15;
	private static final int MARGINE_INFERIORE_TOTALI = 50;
	private static final int MARGINE_SUPERIORE_BOTTOMPANEL = 10;
	private static final int DIMENSIONE_FONT_TOTALI = 20;

	private static final String TESTO_BOTTONE_PAGAMENTO_1 = "Bonifico";
	private static final String TESTO_BOTTONE_PAGAMENTO_2 = "Contrassegno";
	private static final String TESTO_BOTTONE_PAGAMENTO_3 = "Carta di credito";
	private static final String TESTO_BOTTONE_PAGAMENTO_4 = "PayPal";

	private static final String TESTO_TOTALE = "Totale: ";
	private static final String TESTO_TOTALE_SCONTATO = "Totale scontato: ";
	private static final String SIMBOLO_VALUTA = "€";
	
	private static final String TESTO_BOTTONE_CONFERMA = "Conferma";
	private static final String TESTO_BOTTONE_ANNULLA = "Annulla";

	public JPaymentDialog (JFrame jFrame, Carrello carrello, Magazzino magazzino) {
		super (jFrame, TITOLO, JDialog.ModalityType.DOCUMENT_MODAL);
		this.setMinimumSize(new Dimension(LARGHEZZA_MINIMA, ALTEZZA_MINIMA));
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.carrello = carrello;
		this.magazzino = magazzino;
		this.mainFrame = (JeCommerceFrame) jFrame;
		this.confirmButton = new JButton(TESTO_BOTTONE_CONFERMA);
		this.cancelButton = new JButton(TESTO_BOTTONE_ANNULLA);
		this.confirmButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
		
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
		JLabel totalLabel = new JLabel(TESTO_TOTALE + String.format("%.2f", this.carrello.getTotale()) + SIMBOLO_VALUTA);
		totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		totalLabel.setFont(new Font(totalLabel.getFont().getName(), Font.PLAIN, DIMENSIONE_FONT_TOTALI));
		JLabel discountedTotalLabel = new JLabel(TESTO_TOTALE_SCONTATO + String.format("%.2f", this.carrello.getTotaleScontato()) + SIMBOLO_VALUTA);
		discountedTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		discountedTotalLabel.setFont(new Font(discountedTotalLabel.getFont().getName(), Font.PLAIN, DIMENSIONE_FONT_TOTALI));
		completePanel.add(totalLabel);
		completePanel.add(Box.createVerticalStrut(SPAZIO_TOTALI));
		completePanel.add(discountedTotalLabel);
		completePanel.add(Box.createVerticalStrut(MARGINE_INFERIORE_TOTALI));
		completePanel.add(choosePaymentPanel);
		
		JPanel paymentPanel = new JPanel (new BorderLayout());
		paymentPanel.add(Box.createVerticalStrut(MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		paymentPanel.add(Box.createHorizontalStrut(MARGINE_SINISTRO), BorderLayout.WEST);
		paymentPanel.add(completePanel, BorderLayout.CENTER);
		paymentPanel.add(Box.createHorizontalStrut(MARGINE_DESTRO), BorderLayout.EAST);
		paymentPanel.add(bottomPanel, BorderLayout.PAGE_END);
		this.add(paymentPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.cancelButton) {
			this.setVisible(false);
		}
		else if (e.getSource() == this.confirmButton) {
			for (Prodotto articolo : this.carrello.getArticoli()) {
				this.magazzino.rimuoviProdotto(articolo.getCodice(), articolo.getQuantita());
			}
			this.carrello.svuota();
			this.setVisible(false);
			((JClientContentPanel)this.mainFrame.getJContentPanel()).aggiornaArticoli();
		}
	}
}
