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

import negozio.Carrello;
import negozio.Magazzino;

public class JAddProductToCartDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -4438831458822655741L;

	private JButton okButton;
	private JButton cancelButton;

	private static final int DIALOG_WIDTH = 400;
	private static final int DIALOG_HEIGHT = 145;
	private static final int BUTTONS_SPACE = 60;

	private static final int DIALOG_MARGIN = 15;

	private static final String TITLE = "Aggiungi articolo";
	private static final String OK_STRING = "Aggiungi";
	private static final String CANCEL_STRING = "Annulla";
	
	public JAddProductToCartDialog (JFrame mainFrame, Magazzino store, Carrello cart) {
		super (mainFrame, TITLE, ModalityType.DOCUMENT_MODAL);
		this.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());

		JSelectProductWithAmountPanel articlePanel = new JSelectProductWithAmountPanel(store.getArticoli());

		this.cancelButton = new JButton(CANCEL_STRING);
		this.cancelButton.addActionListener(this);
		this.okButton = new JButton(OK_STRING);
		this.okButton.addActionListener(new AddProductToCartListener(
				this, store, cart, articlePanel.getCode(), articlePanel.getAmountTextfield()));
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(cancelButton, BorderLayout.WEST);
		buttonsPanel.add(Box.createHorizontalStrut(BUTTONS_SPACE), BorderLayout.CENTER);
		buttonsPanel.add(okButton, BorderLayout.EAST);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createHorizontalStrut(DIALOG_MARGIN), BorderLayout.WEST);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createHorizontalStrut(DIALOG_MARGIN), BorderLayout.EAST);
		bottomPanel.add(Box.createVerticalStrut(DIALOG_MARGIN), BorderLayout.PAGE_END);

		this.add(Box.createVerticalStrut(DIALOG_MARGIN), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(DIALOG_MARGIN), BorderLayout.WEST);
		this.add(articlePanel, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(DIALOG_MARGIN), BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getSource().equals(this.cancelButton)) {
			this.dispose();
		}
	}
}