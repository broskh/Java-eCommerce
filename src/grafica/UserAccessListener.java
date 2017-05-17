package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import negozio.Magazzino;
import utenza.Amministratore;
import utenza.Cliente;
import utenza.Utente;

public class UserAccessListener implements ActionListener {

	private Utente utente;
	private JUserFrame userFrame;
	private JeCommerceFrame eCommerceFrame;
	private Magazzino magazzino;
	private String fileMagazzinoCliente;
	
	public UserAccessListener(JUserFrame userFrame, JeCommerceFrame eCommerceFrame, Utente utente, Magazzino magazzino, String fileMagazzinoCliente) {
		this.utente = utente;
		this.userFrame = userFrame;
		this.eCommerceFrame = eCommerceFrame;
		this.magazzino = magazzino;
		this.fileMagazzinoCliente = fileMagazzinoCliente;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.userFrame.jContentPanel.jAdminRadioButton.isSelected()) {
			if(this.userFrame.jContentPanel.jNameTextField.getText().equals("") 
					|| this.userFrame.jContentPanel.jSurnameTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(this.userFrame, "Inserire i propri dati correttamente",
						"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				this.utente = new Amministratore(this.userFrame.jContentPanel.jNameTextField.getText(),
						this.userFrame.jContentPanel.jSurnameTextField.getText());
				this.eCommerceFrame = new JeCommerceFrame(this.utente);
				this.userFrame.setVisible(false);
				this.eCommerceFrame.setVisible(true);
			}
		}
		else if(this.userFrame.jContentPanel.jClientRadioButton.isSelected()) {
			if(this.userFrame.jContentPanel.jNameTextField.getText().equals("") 
					|| this.userFrame.jContentPanel.jSurnameTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(this.userFrame, "Inserire i propri dati correttamente",
						"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				this.utente = new Cliente(this.userFrame.jContentPanel.jNameTextField.getText(),
						this.userFrame.jContentPanel.jSurnameTextField.getText());
				this.magazzino.caricaMagazzino(this.fileMagazzinoCliente);
				this.eCommerceFrame = new JeCommerceFrame(this.utente);
				this.userFrame.setVisible(false);
				this.eCommerceFrame.setVisible(true);
			}
		}
		else {
			JOptionPane.showMessageDialog(this.userFrame, "Selezionare il tipo di utente",
					"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}