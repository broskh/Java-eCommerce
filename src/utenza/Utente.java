 package utenza;
 
 /**
  * La classe astratta Utente gestisce le informazioni di base di un utente.
  * 
  * @author Alessio Scheri
  * @version 1.0
  * @see Amministratore
  * @see Cliente
  */
public abstract class Utente {
	/**
	 * Nome dell'utente
	 */
	protected String nome;
	 /**
	  * Cognome dell'utente. 
	  */
	protected String cognome;
	
	/**
	 * Crea un Utente definendo nome e cognome.
	 * 
	 * @param nome Nome dell'utente.
	 * @param cognome Cognome dell'utente.
	 */
	protected Utente (String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	/**
	 * Crea un Utente senza informazioni di base.
	 */
	protected Utente () {
		this  ("", "");
	}

	/**
	 * @return il nome dell'utente.
	 */
	public String getNome () {
		return nome;
	}

	/**
	 * Modifica il nome dell'utente.
	 * 
	 * @param nome Nuovo nome dell'utente.
	 */
	public void setNome (String nome) {
		this.nome = nome;
	}

	/**
	 * @return il cognome dell'utente.
	 */
	public String getCognome () {
		return cognome;
	}

	/**
	 * Modifica il cognome dell'utente.
	 * 
	 * @param cognome Nuovo cognome dell'utente.
	 */
	public void setCognome (String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * @return "true" se l'utente è amministratore, "false" se è un cliente.
	 */
	public abstract boolean isAmministratore ();

	@Override
	public String toString () {
		return "Utente [nome=" + nome + ", cognome=" + cognome + "]";
	}
}