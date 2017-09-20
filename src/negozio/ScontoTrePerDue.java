package negozio;

/**
 * La classe ScontoTrePerDue gestisce le informazioni relative ad una promozione
 * 3x2. Questo significa che 3 prodotti uguali avranno un costo pari a quello di 2.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see Promozione
 */
public class ScontoTrePerDue extends Promozione {
	private static final long serialVersionUID = 6231304518414497931L;

	/**
	 * Crea un oggetto ScontoTrePerDue.
	 */
	public ScontoTrePerDue () {
		super ();
	}

	@Override
	public float calcolaSconto (float prezzo, int quantita) {
		return prezzo * (quantita - (quantita / 3));
	}

	@Override
	public String toString() {
		return "3 x 2";
	}
}