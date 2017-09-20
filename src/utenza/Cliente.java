package utenza;
import negozio.Carrello;

/**
 * La classe Cliente gestisce le informazioni di un cliente.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see Utente
 */
public class Cliente extends Utente {
	/**
	 * Carrello del cliente.
	 */
	private Carrello carrello;
	
	/**
	 * Crea un Cliente con nome e cognome.
	 * 
	 * @param nome Nome del cliente.
	 * @param cognome Cognome del cliente.
	 */
	public Cliente (String nome, String cognome) {
		super (nome, cognome);
		this.carrello = new Carrello ();
	}

	/**
	 * Crea un Cliente senza informazioni di base.
	 */
	public Cliente () {
		super ();
		this.carrello = new Carrello ();
	}

	/**
	 * @return il carrello del cliente.
	 */
	public Carrello getCarrello () {
		return carrello;
	}

	@Override
	public boolean isAmministratore () {
		return false;
	}

	@Override
	public String toString () {
		return "Cliente [nome=" + nome + ", cognome=" + cognome + ", carrello=" + carrello + "]";
	}
}