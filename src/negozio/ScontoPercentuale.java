package negozio;

/**
 * La classe ScontoPercentuale gestisce le informazioni relative ad una promozione
 * che fornisce uno sconto corrispondente ad una determinata percentuale del prezzo originale.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *  */
public class ScontoPercentuale extends Promozione {
	private int percentuale; /**<Percentuale di sconto.*/
	
	/**
	 * Crea un oggetto ScontoPercentuale definendo il valore dello sconto.
	 */
	public ScontoPercentuale (int percentuale) {
		super ();
		if (percentuale >= 0 && percentuale <= 100) {
			this.percentuale = percentuale;
		}
		else {
			this.percentuale = 0;
		}
	}
	
	/**
	 * Crea un oggetto ScontoPercentuale definendo il valore dello sconto uguale a 0.
	 */
	public ScontoPercentuale () {
		this (0);
	}
	
	/**
	 * @return il valore percentuale dello sconto.
	 */
	public int getPercentuale() {
		return percentuale;
	}

	/**
	 * Modifica il valore percentuale dello sconto se esso è compreso fra 0 e 100.
	 * 
	 * @param percentuale Nuovo valore della percentuale di sconto.
	 */
	public void setPercentuale(int percentuale) {
		if (percentuale >= 0 && percentuale <= 100) {
			this.percentuale = percentuale;
		}
	}

	@Override
	public float calcolaSconto (float prezzo, int quantita) {
		return (prezzo - (prezzo / 100 * this.percentuale)) * quantita;
	}

	@Override
	public String toString() {
		return "ScontoPercentuale [percentuale=" + percentuale + "]";
	}
}