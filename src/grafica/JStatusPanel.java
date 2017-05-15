package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import utenza.Utente;

public class JStatusPanel extends JPanel{
	private static final long serialVersionUID = -1821269487258614928L;
//	private String nome;
//	private String cognome;
//	private String tipo;

	private JLabel jNameLabel;
	private JLabel jTypeLabel;
	private JLabel jAuthorLabel;

	private static final String intestazioneNomeUtente = "Nome utente: ";
	private static final String intestazioneTipoUtente = "Tipo utente: ";
	private static final String intestazioneAutore = "Autore: ";
	private static final String stringaTipoAmministratore = "Amministratore";
	private static final String stringaTipoCliente = "Cliente";
	private static final String autore = "Alessio Scheri";
	private static final int altezza = 22;
	
	public JStatusPanel (Utente utente) {
//		//inizializzo gli attributi contenenti i valori da utilizzare
//		this.nome = nome;
//		this.cognome = cognome;
//		this.tipo = tipo;
		//setto la grafica del panel
		this.setPreferredSize(new Dimension(this.getWidth(), altezza));
		this.setLayout(new GridLayout(1,3));
		//setto i vari componenti
		this.jNameLabel = new JLabel(intestazioneNomeUtente + utente.getNome() + " " + utente.getCognome());
		if (utente.isAmministratore()) {
			this.jTypeLabel = new JLabel(intestazioneTipoUtente + stringaTipoAmministratore);
		}
		else {
			this.jTypeLabel = new JLabel(intestazioneTipoUtente + stringaTipoCliente);
		}
		this.jAuthorLabel = new JLabel(intestazioneAutore + autore);
		this.jNameLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.jNameLabel.setHorizontalAlignment(JLabel.CENTER);
		this.jTypeLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.jTypeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.jAuthorLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.jAuthorLabel.setHorizontalAlignment(JLabel.CENTER);
//		//aggiorno le stringhe delle label
//		this.updateLabels();
		//aggiungo le label al panel
		this.add(this.jNameLabel);
		this.add(this.jTypeLabel);
		this.add(this.jAuthorLabel);
	}
//	
//	public JStatusPanel () {
//		this ("", "", "");
//	}
//
//	public String getCognome() {
//		return cognome;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public String getTipo() {
//		return tipo;
//	}
//
//	public void setCognome(String cognome) {
//		this.cognome = cognome;
//	}
//
//	public void setCognome(Utente utente) {
//		this.cognome = utente.getCognome();
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public void setNome(Utente utente) {
//		this.nome = utente.getNome();
//	}
//
//	public void setTipo(String tipo) {
//		this.tipo = tipo;
//	}
//
//	public void setTipo(Utente utente) {
//		if (utente.isAmministratore()) {
//			this.tipo = "Amministratore";
//		}
//		else {
//			this.tipo = "Cliente";
//		}
//	}
//	
//	public void updateLabels () {
//		this.jNameLabel.setText(intestazioneNomeUtente + this.nome + " " + this.cognome);
//		this.jTypeLabel.setText(intestazioneTipoUtente + this.tipo);
//		this.jAuthorLabel.setText(intestazioneAutore + autore);
//	}
}