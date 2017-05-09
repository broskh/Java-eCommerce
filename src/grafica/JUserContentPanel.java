package grafica;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import utenza.Amministratore;
import utenza.Cliente;
import utenza.Utente;

public class JUserContentPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static final String testoNameLabel = "Nome:";
	private static final String testoSurnameLabel = "Cognome:";
	private static final String testoUserTypeTitleLabel = "Tipo di utente:";
	private static final String testoClientRadioButton = "Cliente";
	private static final String testoAdminRadioButton = "Amministratore";
	private static final String testoOkButton = "Accedi";
	private static final int larghezzaTextBox = 30;
	
	private JLabel jNameLabel;
	private JTextField jNameTextField;
	private JLabel jSurnameLabel;
	private JTextField jSurnameTextField;
	private JLabel jUserTypeTitleLabel;
	private JRadioButton jClientRadioButton;
	private JRadioButton jAdminRadioButton;
	private JButton jOkButton;
	
	public JUserContentPanel () {
		this.jNameLabel = new JLabel(JUserContentPanel.testoNameLabel);
		this.jNameTextField = new JTextField(JUserContentPanel.larghezzaTextBox);
		this.jSurnameLabel = new JLabel(JUserContentPanel.testoSurnameLabel);
		this.jSurnameTextField = new JTextField (JUserContentPanel.larghezzaTextBox);
		this.jSurnameTextField.setSize(10,  10);
		this.jUserTypeTitleLabel = new JLabel(JUserContentPanel.testoUserTypeTitleLabel);
		this.jClientRadioButton = new JRadioButton(JUserContentPanel.testoClientRadioButton);
		this.jAdminRadioButton = new JRadioButton(JUserContentPanel.testoAdminRadioButton);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.jClientRadioButton);
		buttonGroup.add(this.jAdminRadioButton);
		this.jOkButton = new JButton(JUserContentPanel.testoOkButton);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pannelloAnagrafico = new JPanel(new GridLayout(2, 2));
		pannelloAnagrafico.add (this.jNameLabel);
		pannelloAnagrafico.add (this.jSurnameLabel);
		pannelloAnagrafico.add (this.jNameTextField);
		pannelloAnagrafico.add (this.jSurnameTextField);
		JPanel pannelloTipoUtente = new JPanel(new GridLayout(3, 1));
		pannelloTipoUtente.add(this.jUserTypeTitleLabel);
		pannelloTipoUtente.add(this.jClientRadioButton);
		pannelloTipoUtente.add(this.jAdminRadioButton);
		this.add (pannelloAnagrafico);
		this.add (pannelloTipoUtente);
		this.add (this.jOkButton);
		jOkButton.addActionListener(this);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(jAdminRadioButton.isSelected())
		{
			Utente user = new Amministratore(jNameTextField.getText(),jSurnameTextField.getText());
			JeCommerceFrame frame = new JeCommerceFrame(user);
			if(jNameTextField.getText().equals("") || jSurnameTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Inserire i propri dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				frame.setVisible(true);
			}
		}
		else if(jClientRadioButton.isSelected())
		{
			Utente user = new Cliente(jNameTextField.getText(),jSurnameTextField.getText());
			JeCommerceFrame frame = new JeCommerceFrame(user);
			if(jNameTextField.getText().equals("") || jSurnameTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Inserire i propri dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				frame.setVisible(true);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Selezionare il tipo di utente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
