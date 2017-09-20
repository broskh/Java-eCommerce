package utenza;

/**
 * Classe che gestisce le informazioni di un utente di tipo amministratore.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see Utente
 */
public class Amministratore extends Utente {

	/**
	 * Crea un {@link Amministratore} con nome e cognome.
	 * 
	 * @param nome Nome dell'utente amministratore.
	 * @param cognome Cognome dell'utente amministratore.
	 */
	public Amministratore (String nome, String cognome) {
		super (nome, cognome);
	}

	/**
	 * Crea un {@link Amministratore} senza informazioni di base.
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