package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
	
	private JTextField nameTextField;
	private JTextField surnameTextField;
	private JRadioButton clientRadioButton;
	private JRadioButton adminRadioButton;
	private JButton okButton;
	
	private static final int FRAME_HEIGHT = 220;
	private static final int FRAME_WIDTH = 450;

	private static final int GENERIC_MARGIN = 10;
	private static final int FRAME_MARGIN = 15;
	
	private static final int PERSONAL_DATA_PANEL_ROWS = 2;
	private static final int PERSONAL_DATA_PANEL_COLUMNS = 2;
	private static final int USER_TYPE_PANEL_ROWS = 3;
	private static final int USER_TYPE_PANEL_COLUMNS = 1;

	private static final String TITLE = "Login";
	private static final String NAME_TEXT = "Nome:";
	private static final String SURNAME_TEXT = "Cognome:";
	private static final String USER_TYPE_TITLE = "Tipo di utente:";
	private static final String CLIENT_TEXT = "Cliente";
	private static final String ADMIN_TEXT = "Amministratore";
	private static final String OK_BUTTON_TEXT = "Accedi";
	private static final String ERROR_TITLE = "Attenzione";
	private static final String NO_TYPE_ERROR_TEXT = "Selezionare il tipo di utente.";
	private static final String NO_PERSONAL_DATA_ERROR_TEXT = 
			"Inserire i propri dati correttamente.";
	private static final String ALERT_FILE_NOT_FOUND_TITLE = "File non trovato";
	private static final String ALERT_FILE_NOT_FOUND_TEXT = 
			"File contenente il magazzino non trovato.";
	
	private static final String LAST_CONFIG_FILE = "media/saves/last";
	
	public JUserFrame()	{
		super(JUserFrame.TITLE);
		this.setIconImage(new ImageIcon(JeCommerceFrame.SOFTWARE_ICON).getImage());
		this.store  = new Magazzino();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(JUserFrame.FRAME_WIDTH, JUserFrame.FRAME_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setLayout(new BorderLayout());
		this.add(Box.createVerticalStrut(FRAME_MARGIN), BorderLayout.PAGE_START);
		this.add(Box.createHorizontalStrut(FRAME_MARGIN), BorderLayout.WEST);
		this.add(this.contentPanel(), BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(FRAME_MARGIN), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(FRAME_MARGIN), BorderLayout.PAGE_END);
	}
	
	private JPanel contentPanel () {
		JLabel nameLabel = new JLabel(JUserFrame.NAME_TEXT);
		this.nameTextField = new JTextField();
		JLabel surnameLabel = new JLabel(JUserFrame.SURNAME_TEXT);
		this.surnameTextField = new JTextField ();
		JPanel personalDataPanel = new JPanel(
				new GridLayout(PERSONAL_DATA_PANEL_ROWS, PERSONAL_DATA_PANEL_COLUMNS));
		personalDataPanel.setOpaque(true);
		personalDataPanel.add (nameLabel);
		personalDataPanel.add (surnameLabel);
		personalDataPanel.add (this.nameTextField);
		personalDataPanel.add (this.surnameTextField);

		JLabel userTypeTitleLabel = new JLabel(JUserFrame.USER_TYPE_TITLE);
		this.clientRadioButton = new JRadioButton(JUserFrame.CLIENT_TEXT);
		this.adminRadioButton = new JRadioButton(JUserFrame.ADMIN_TEXT);		
		ButtonGroup userTypeGroup = new ButtonGroup();
		userTypeGroup.add(this.clientRadioButton);
		userTypeGroup.add(this.adminRadioButton);
		JPanel userTypePanel = new JPanel(
				new GridLayout(USER_TYPE_PANEL_ROWS, USER_TYPE_PANEL_COLUMNS));
		userTypePanel.setOpaque(true);
		userTypePanel.add(userTypeTitleLabel);
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
		contentPanel.setOpaque(true);
		return contentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.okButton)) {
			boolean noTextFieldEmpty = !this.nameTextField.getText().equals("") && 
					!this.surnameTextField.getText().equals("");
			if (noTextFieldEmpty) {
				Utente user;
				JeCommerceFrame eCommerceFrame;
				if(this.adminRadioButton.isSelected()) {
					user = new Amministratore(this.nameTextField.getText(),
							this.surnameTextField.getText());
					eCommerceFrame = new JeCommerceFrame(user, this.store);
					this.setVisible(false);
					eCommerceFrame.setVisible(true);
				}
				else if (this.clientRadioButton.isSelected()) {
					user = new Cliente(this.nameTextField.getText(),
							this.surnameTextField.getText());
					File lastStoreFile = new File(LAST_CONFIG_FILE);
					try {
						BufferedReader lastBufferedReader = 
								new BufferedReader(new FileReader(lastStoreFile));
						String lastContent = lastBufferedReader.readLine();
						if (lastContent != null) {
							File storeFile = new File(lastContent);
							if (storeFile.exists()) {
								store.caricaMagazzino(storeFile);
								eCommerceFrame = new JeCommerceFrame(user, this.store);
								eCommerceFrame.setVisible(true);
							}
							else {
								JOptionPane.showMessageDialog(this, ALERT_FILE_NOT_FOUND_TEXT,
										ALERT_FILE_NOT_FOUND_TITLE, JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(this, ALERT_FILE_NOT_FOUND_TEXT,
									ALERT_FILE_NOT_FOUND_TITLE, JOptionPane.INFORMATION_MESSAGE);
						}
						lastBufferedReader.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					this.setVisible(false);
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