package utenza;

/**
 * La classe Amministratore gestisce le informazioni di un utente amministratore.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see Utente
 */
public class Amministratore extends Utente {

	/**
	 * Crea un Amministratore con nome e cognome.
	 * 
	 * @param nome Nome dell'utente amministratore.
	 * @param cognome Cognome dell'utente amministratore.
	 */
	public Amministratore (String nome, String cognome) {
		super (nome, cognome);
	}

	/**
	 * Crea un Amministratore senza informazioni di base.
	 */
	public Amministratore () {
		super ();
	}

	@Override
	public boolean isAmministratore () {
		return true;
	}

	@Override
	public String toString() {
		return "Amministratore [nome=" + nome + ", cognome=" + cognome + "]";
	}
}