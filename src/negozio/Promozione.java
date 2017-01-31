package negozio;

/**
 * Classe astratta per l'implementazione di una promozione.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see ScontoPercentuale
 * @see ScontoTrePerDue
 */
public abstract class Promozione {
	/**
	 * Calcola il prezzo scontato di una determinata quantita di prodotti dello stesso tipo.
	 * 
	 * @param prezzo Prezzo cadauno.
	 * @param quantita Quantita di pezzi sui quali deve essere calcolato lo sconto.
	 * @return il prezzo totale scontato.
	 */
	public abstract float calcolaSconto (float prezzo, int quantita);
}
