package grafica;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

public class JUserContentPanel extends JPanel{
	private static final long serialVersionUID = 3035684738663525272L;
	
	private JLabel jNameLabel;
	protected JTextField jNameTextField;
	private JLabel jSurnameLabel;
	protected JTextField jSurnameTextField;
	private JLabel jUserTypeTitleLabel;
	protected JRadioButton jClientRadioButton;
	protected JRadioButton jAdminRadioButton;
	protected JButton jOkButton;

	private static final int LARGHEZZA_TEXTBOX = 30;
	private static final int N_RIGHE_PANNELLO_ANAGRAFICO = 2;
	private static final int N_COLONNE_PANNELLO_ANAGRAFICO = 2;
	private static final int N_RIGHE_PANNELLO_TIPO_UTENTE = 3;
	private static final int N_COLONNE_PANNELLO_TIPO_UTENTE = 1;
	
	private static final String TESTO_NAME_LABEL = "Nome:";
	private static final String TESTO_SURNAME_LABEL = "Cognome:";
	private static final String TESTO_USER_TYPE_TITLE_LABEL = "Tipo di utente:";
	private static final String TESTO_CLIENT_RADIO_BUTTON = "Cliente";
	private static final String TESTO_ADMIN_RADIO_BUTTON = "Amministratore";
	private static final String TESTO_OK_BUTTON = "Accedi";
	
	public JUserContentPanel () {
		this.jNameLabel = new JLabel(JUserContentPanel.TESTO_NAME_LABEL);
		this.jNameTextField = new JTextField(JUserContentPanel.LARGHEZZA_TEXTBOX);
		this.jSurnameLabel = new JLabel(JUserContentPanel.TESTO_SURNAME_LABEL);
		this.jSurnameTextField = new JTextField (JUserContentPanel.LARGHEZZA_TEXTBOX);
		this.jSurnameTextField.setSize(10,  10);
		this.jUserTypeTitleLabel = new JLabel(JUserContentPanel.TESTO_USER_TYPE_TITLE_LABEL);
		this.jClientRadioButton = new JRadioButton(JUserContentPanel.TESTO_CLIENT_RADIO_BUTTON);
		this.jAdminRadioButton = new JRadioButton(JUserContentPanel.TESTO_ADMIN_RADIO_BUTTON);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.jClientRadioButton);
		buttonGroup.add(this.jAdminRadioButton);
		this.jOkButton = new JButton(JUserContentPanel.TESTO_OK_BUTTON);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pannelloAnagrafico = new JPanel(new GridLayout(N_RIGHE_PANNELLO_ANAGRAFICO, N_COLONNE_PANNELLO_ANAGRAFICO));
		pannelloAnagrafico.add (this.jNameLabel);
		pannelloAnagrafico.add (this.jSurnameLabel);
		pannelloAnagrafico.add (this.jNameTextField);
		pannelloAnagrafico.add (this.jSurnameTextField);
		JPanel pannelloTipoUtente = new JPanel(new GridLayout(N_RIGHE_PANNELLO_TIPO_UTENTE, N_COLONNE_PANNELLO_TIPO_UTENTE));
		pannelloTipoUtente.add(this.jUserTypeTitleLabel);
		pannelloTipoUtente.add(this.jClientRadioButton);
		pannelloTipoUtente.add(this.jAdminRadioButton);
		this.add (pannelloAnagrafico);
		this.add (pannelloTipoUtente);
		this.add (this.jOkButton);
	}
}
