package negozio;

/**
 * Questa interfeccia implementa i metodi astratti necessari per la gestione di un'insieme
 * di articoli.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see Carrello
 * @see Magazzino
 */
public interface GestioneProdotti {
	/**
	 * Aggiunge un prodotto agli articoli già presenti.
	 * 
	 * @param prodotto Prodotto da aggiungere.
	 */
	public abstract void aggiungiProdotto (Prodotto prodotto);
	
	/**
	 * Rimuove un prodotto dagli articoli presenti.
	 * 
	 * @param prodotto Prodotto da rimuovere.
	 */
	public abstract void rimuoviProdotto (Prodotto prodotto);
	
	/**
	 * Rimuove tutti gli articoli presenti.
	 */
	public abstract void svuota ();
}