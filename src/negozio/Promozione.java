package negozio;

import java.io.Serializable;

/**
 * Classe astratta per l'implementazione di una promozione.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see ScontoPercentuale
 * @see ScontoTrePerDue
 * @see Prodotto
 */
public abstract class Promozione  implements Serializable{
	private static final long serialVersionUID = 6507101060017034505L;

	/**
	 * Calcola il prezzo scontato di una determinata quantita di un prodotto.
	 * 
	 * @param prezzo Prezzo cadauno.
	 * @param quantita Quantita di pezzi sui quali deve essere calcolato lo sconto.
	 * @return il prezzo totale scontato.
	 */
	public abstract float calcolaSconto (float prezzo, int quantita);
}
