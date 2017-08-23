package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import negozio.Magazzino;

public class JFilterDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -4438831458822655741L;

	private JButton okButton;
	private JButton cancelButton;

	private static final String TITOLO = "Filtra per ";
	private static final String OK_STRING = "FILTRA";
	private static final String CANCEL_STRING = "ANNULLA";
	private static final int LARGHEZZA_MINIMA = 400;
	private static final int ALTEZZA_MINIMA = 160;
	private static final int MARGINE_LATERALE = 50;
	private static final int MARGINE_SUPERIORE = 30;
	private static final int SPAZIO_BOTTONI = 50;
	private static final int MARGINE_INFERIORE = 30;
	
	public JFilterDialog (JeCommerceFrame mainFrame, Magazzino magazzino, String filterType) {
		super (mainFrame, TITOLO + filterType.toLowerCase(), JDialog.ModalityType.DOCUMENT_MODAL);
		this.setMinimumSize(new Dimension(LARGHEZZA_MINIMA, ALTEZZA_MINIMA));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JFilterPanel filterPanel = new JFilterPanel(magazzino.MaxQuantita(), magazzino.MaxPrezzo(), filterType);

		this.okButton = new JButton(OK_STRING);
		this.okButton.addActionListener(new FilterListener(((JClientContentPanel)mainFrame.getJContentPanel()), new StringBuilder(filterType), filterPanel, this));
		this.cancelButton = new JButton(CANCEL_STRING);
		this.cancelButton.addActionListener(this);
		JPanel buttonsPanel = new JPanel(new BorderLayout());
		buttonsPanel.add(cancelButton, BorderLayout.WEST);
		buttonsPanel.add(Box.createHorizontalStrut(SPAZIO_BOTTONI), BorderLayout.CENTER);
		buttonsPanel.add(okButton, BorderLayout.EAST);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createHorizontalStrut(MARGINE_LATERALE), BorderLayout.WEST);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createHorizontalStrut(MARGINE_LATERALE), BorderLayout.EAST);
		bottomPanel.add(Box.createVerticalStrut(MARGINE_INFERIORE), BorderLayout.PAGE_END);

		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(MARGINE_LATERALE), BorderLayout.WEST);
		this.add(filterPanel, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(MARGINE_LATERALE), BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getSource().equals(this.cancelButton)) {
			this.dispose();
		}
	}
}