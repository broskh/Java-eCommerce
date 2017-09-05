package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import grafica.JUserFrame;
import negozio.Magazzino;
import utenza.Amministratore;
import utenza.Cliente;
import utenza.Utente;

public class JUserFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 979362510733265067L;
	
	private Magazzino store;
	private Utente user;
	
	private JeCommerceFrame eCommerceFrame;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel surnameLabel;
	private JTextField surnameTextField;
	private JLabel userTypeTitleLabel;
	private JRadioButton clientRadioButton;
	private JRadioButton adminRadioButton;
	private JButton okButton;
	
	private static final int FRAME_HEIGHT = 225;
	private static final int FRAME_WIDTH = 450;

	private static final int PERSONAL_DATA_PANEL_ROWS = 2;
	private static final int PERSONAL_DATA_PANEL_COLUMNS = 2;
	private static final int USER_TYPE_PANEL_ROWS = 3;
	private static final int USER_TYPE_PANEL_COLUMNS = 1;
	private static final int GENERIC_MARGIN = 15;
	private static final int BOTTOM_MARGIN = 15;
	private static final int TOP_MARGIN = 15;
	private static final int LEFT_MARGIN = 15;
	private static final int RIGHT_MARGIN = 15;
	
	private static final String NAME_LABEL_TEXT = "Nome:";
	private static final String SURNAME_LABEL_TEXT = "Cognome:";
	private static final String USER_TYPE_TITLE = "Tipo di utente:";
	private static final String CLIENT_RADIO_BUTTON_TEXT = "Cliente";
	private static final String ADMIN_RADIO_BUTTON_TEXT = "Amministratore";
	private static final String OK_BUTTON_TEXT = "Accedi";
	private static final String NO_TYPE_ERROR_TEXT = "Selezionare il tipo di utente";
	private static final String NO_PERSONAL_DATA_ERROR_TEXT = "Inserire i propri dati correttamente";

	private static final String ERROR_TITLE = "Attenzione";
	private static final String TITLE = "Login";
	
	public JUserFrame(JeCommerceFrame eCommerceFrame, Magazzino store, Utente user)	{
		super(JUserFrame.TITLE);
		this.eCommerceFrame = eCommerceFrame;
		this.store  = store;
		this.user = user;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(JUserFrame.FRAME_WIDTH, JUserFrame.FRAME_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(TOP_MARGIN), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(LEFT_MARGIN), BorderLayout.WEST);
		this.add(this.contentPanel(), BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(RIGHT_MARGIN), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(BOTTOM_MARGIN), BorderLayout.PAGE_END);
	}
	
	private JPanel contentPanel () {
		this.nameLabel = new JLabel(JUserFrame.NAME_LABEL_TEXT);
		this.nameTextField = new JTextField();
		this.surnameLabel = new JLabel(JUserFrame.SURNAME_LABEL_TEXT);
		this.surnameTextField = new JTextField ();
		this.surnameTextField.setSize(10,  10);
		JPanel personalDataPanel = new JPanel(
				new GridLayout(PERSONAL_DATA_PANEL_ROWS, PERSONAL_DATA_PANEL_COLUMNS));
		personalDataPanel.add (this.nameLabel);
		personalDataPanel.add (this.surnameLabel);
		personalDataPanel.add (this.nameTextField);
		personalDataPanel.add (this.surnameTextField);

		this.userTypeTitleLabel = new JLabel(JUserFrame.USER_TYPE_TITLE);
		this.clientRadioButton = new JRadioButton(JUserFrame.CLIENT_RADIO_BUTTON_TEXT);
		this.adminRadioButton = new JRadioButton(JUserFrame.ADMIN_RADIO_BUTTON_TEXT);		
		ButtonGroup userTypeGroup = new ButtonGroup();
		userTypeGroup.add(this.clientRadioButton);
		userTypeGroup.add(this.adminRadioButton);
		JPanel userTypePanel = new JPanel(
				new GridLayout(USER_TYPE_PANEL_ROWS, USER_TYPE_PANEL_COLUMNS));
		userTypePanel.add(this.userTypeTitleLabel);
		userTypePanel.add(this.clientRadioButton);
		userTypePanel.add(this.adminRadioButton);

		this.okButton = new JButton(JUserFrame.OK_BUTTON_TEXT);
		this.okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.okButton.addActionListener(this);
		JPanel contentPanel = new JPanel();	
		contentPanel.setLayout (new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setAlignmentX (Component.CENTER_ALIGNMENT);
		contentPanel.add (personalDataPanel);
		contentPanel.add (Box.createVerticalStrut(GENERIC_MARGIN));
		contentPanel.add (userTypePanel);
		contentPanel.add (Box.createVerticalStrut(GENERIC_MARGIN));
		contentPanel.add (this.okButton);
		return contentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.okButton)) {
			if (!this.nameTextField.getText().equals("") && 
					!this.surnameTextField.getText().equals("")) {
				if(this.adminRadioButton.isSelected()) {
					this.user = new Amministratore(this.nameTextField.getText(),
							this.surnameTextField.getText());
					this.eCommerceFrame = new JeCommerceFrame(this.user, this.store);
					this.setVisible(false);
					this.eCommerceFrame.setVisible(true);
				}
				else if (this.clientRadioButton.isSelected()) {
					this.user = new Cliente(this.nameTextField.getText(),
							this.surnameTextField.getText());
					this.eCommerceFrame = new JeCommerceFrame(this.user, this.store);
					this.setVisible(false);
					this.eCommerceFrame.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(this, JUserFrame.NO_TYPE_ERROR_TEXT,
							JUserFrame.ERROR_TITLE, JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, JUserFrame.NO_PERSONAL_DATA_ERROR_TEXT,
						JUserFrame.ERROR_TITLE, JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}