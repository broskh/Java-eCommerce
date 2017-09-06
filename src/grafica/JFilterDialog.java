package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import negozio.Magazzino;

public class JFilterDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -4438831458822655741L;

	private JButton okButton;
	private JButton cancelButton;
	
	private static final int DIALOG_WIDTH = 400;
	private static final int DIALOG_HEIGHT = 160;
	private static final int BUTTONS_SPACE = 50;
	
	private static final int SIDE_MARGIN = 50;
	private static final int TOP_MARGIN = 30;
	private static final int BOTTOM_MARGIN = 30;

	private static final String TITLE = "Filtra per ";
	private static final String OK_BUTTON_TEXT = "FILTRA";
	private static final String CANCEL_BUTTON_TEXT = "ANNULLA";
	
	public JFilterDialog (JClientContentPanel clientContentPanel, Magazzino store, 
			String filterType) {
		super (SwingUtilities.getWindowAncestor(clientContentPanel), 
				TITLE + filterType.toLowerCase(), JDialog.ModalityType.DOCUMENT_MODAL);
		this.setMinimumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JFilterPanel filterPanel = new JFilterPanel(store.maxQuantita(), store.maxPrezzo(), 
				filterType);

		this.okButton = new JButton(OK_BUTTON_TEXT);
		this.okButton.addActionListener(new FilterListener(this, 
				clientContentPanel, new StringBuilder(filterType), filterPanel, store));
		this.cancelButton = new JButton(CANCEL_BUTTON_TEXT);
		this.cancelButton.addActionListener(this);
		JPanel buttonsPanel = new JPanel(new BorderLayout());
		buttonsPanel.add(cancelButton, BorderLayout.WEST);
		buttonsPanel.add(Box.createHorizontalStrut(BUTTONS_SPACE), BorderLayout.CENTER);
		buttonsPanel.add(okButton, BorderLayout.EAST);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createHorizontalStrut(SIDE_MARGIN), BorderLayout.WEST);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createHorizontalStrut(SIDE_MARGIN), BorderLayout.EAST);
		bottomPanel.add(Box.createVerticalStrut(BOTTOM_MARGIN), BorderLayout.PAGE_END);

		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(TOP_MARGIN), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(SIDE_MARGIN), BorderLayout.WEST);
		this.add(filterPanel, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(SIDE_MARGIN), BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getSource().equals(this.cancelButton)) {
			this.dispose();
		}
	}
}