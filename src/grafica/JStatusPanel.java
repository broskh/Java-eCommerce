package grafica;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import utenza.Utente;

public class JStatusPanel extends JPanel{
	private static final long serialVersionUID = -1821269487258614928L;

	private static final String NAME_TEXT = "Nome utente: ";
	private static final String TYPE_TEXT = "Tipo utente: ";
	private static final String AUTHOR_TEXT = "Autore: ";
	private static final String ADMIN_STRING = "Amministratore";
	private static final String CLIENT_STRING = "Cliente";
	private static final String AUTHOR = "Alessio Scheri";
	
	public JStatusPanel (Utente user) {
		this.setLayout(new GridLayout(1,3));
		JLabel nameLabel = new JLabel(NAME_TEXT + user.getNome() + 
				" " + user.getCognome());
		JLabel typeLabel = new JLabel ();
		if (user.isAmministratore()) {
			typeLabel.setText(TYPE_TEXT + ADMIN_STRING);
		}
		else {
			typeLabel.setText(TYPE_TEXT + CLIENT_STRING);
		}
		JLabel authorLabel = new JLabel(AUTHOR_TEXT + AUTHOR);
		nameLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		typeLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		typeLabel.setHorizontalAlignment(JLabel.CENTER);
		authorLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		authorLabel.setHorizontalAlignment(JLabel.CENTER);

		this.add(nameLabel);
		this.add(typeLabel);
		this.add(authorLabel);
	}
}