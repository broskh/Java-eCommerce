package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import negozio.Carrello;
import negozio.Prodotto;

public class JRemoveProductFromCartDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -4438831458822655741L;
	
	private Carrello cart;

	private JButton okButton;
	private JButton cancelButton;
	private JSelectProductWithAmountPanel articlePanel;
	
	private static final int DIALOG_WIDTH = 400;
	private static final int DIALOG_HEIGHT = 145;
	private static final int BUTTONS_SPACE = 60;
	
	private static final int DIALOG_MARGIN = 15;

	private static final String TITLE = "Elimina articolo";
	private static final String EMPTY_CART_TEXT = "Il carrello è vuoto.";
	private static final String OK_BUTTON_TEXT = "Elimina";
	private static final String CANCEL_BUTTON_TEXT = "Annulla";
	
	public JRemoveProductFromCartDialog (JFrame mainFrame, Carrello cart) {
		super (mainFrame, TITLE, JDialog.ModalityType.DOCUMENT_MODAL);
		this.setMinimumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		this.cart = cart;
		
		this.cancelButton = new JButton(CANCEL_BUTTON_TEXT);
		this.cancelButton.addActionListener(this);
		JPanel bottomPanel;
		
		if (!this.cart.getArticoli().isEmpty()) {
			this.articlePanel = new JSelectProductWithAmountPanel(this.cart.getArticoli());
	
			this.okButton = new JButton(OK_BUTTON_TEXT);
			this.okButton.addActionListener(this);
			JPanel buttonsPanel = new JPanel();
			buttonsPanel.add(cancelButton, BorderLayout.WEST);
			buttonsPanel.add(Box.createHorizontalStrut(BUTTONS_SPACE), BorderLayout.CENTER);
			buttonsPanel.add(okButton, BorderLayout.EAST);
			
			bottomPanel = new JPanel(new BorderLayout());
			bottomPanel.add(Box.createHorizontalStrut(DIALOG_MARGIN), BorderLayout.WEST);
			bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
			bottomPanel.add(Box.createHorizontalStrut(DIALOG_MARGIN), BorderLayout.EAST);
			bottomPanel.add(Box.createVerticalStrut(DIALOG_MARGIN), BorderLayout.PAGE_END);

			this.add(articlePanel, BorderLayout.CENTER);
		}
		else {
			bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			bottomPanel.add(this.cancelButton);
			
			JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			messagePanel.add(new JLabel(EMPTY_CART_TEXT));
			this.add(messagePanel, BorderLayout.CENTER);
		}
		this.add(Box.createVerticalStrut(DIALOG_MARGIN), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(DIALOG_MARGIN), BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(DIALOG_MARGIN), BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getSource().equals(this.cancelButton)) {
			this.dispose();
		}
		else if (e.getSource().equals(this.okButton)) {
			String code = this.articlePanel.getCode().toString();
			Prodotto article = this.cart.getProdotto(code);
			if (article != null) {
				int amount = this.articlePanel.getAmount();
				if (amount > article.getQuantita()) {
					amount = article.getQuantita();
				}
				this.cart.rimuoviProdotto(code, amount);
				this.dispose();
			}
		}
	}
}