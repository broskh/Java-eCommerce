package negozio;

/**
 * La classe ScontoTrePerDue gestisce le informazioni relative ad una promozione
 * 3x2. Questo significa che 3 prodotti uguali avranno un costo pari a quello di 2.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *  */
public class ScontoTrePerDue extends Promozione {
	
	/**
	 * Crea un oggetto ScontoTrePerDue vuoto.
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
		return "ScontoTrePerDue []";
	}
}