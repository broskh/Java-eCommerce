 package utenza;
 
 /**
  * Classe astratta per la gestione delle informazioni di base di un utente.
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
	 * Crea un {@link Utente} definendo nome e cognome.
	 * 
	 * @param nome Nome dell'utente.
	 * @param cognome Cognome dell'utente.
	 */
	protected Utente (String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	/**
	 * Crea un {@link Utente} senza informazioni di base.
	 */
	protected Utente () {
		this  ("", "");
	}

	/**
	 * Ritorna il nome dell'utente.
	 * 
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
	 * Ritorna il cognome dell'utente.
	 * 
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
	 * Indica se l'utente è di tipo amministratore o no.
	 * 
	 * @return <code>true</code> se l'utente è amministratore, <code>false</code> se è un cliente.
	 */
	public abstract boolean isAmministratore ();

	@Override
	public String toString () {
		return "Utente [nome=" + nome + ", cognome=" + cognome + "]";
	}
}