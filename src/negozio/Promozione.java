package negozio;

/**
 * Classe per l'implementazione di una promozione.
 * 
 * @author Alessio Scheri
 * @version 1.0
 */
public abstract class Promozione {
	/**
	 * Calcola il prezzo scontato.
	 * 
	 * @param prezzo Prezzo privo di sconto.
	 * @return prezzo scontato.
	 */
	public abstract float applicaSconto (float prezzo);
}
