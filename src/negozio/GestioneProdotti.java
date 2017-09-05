package negozio;

/**
 * Questa interfeccia implementa i metodi astratti necessari per la gestione di un'insieme
 * di articoli.
 * 
 * @author Alessio Scheri
 * @version 1.0
 */
public interface GestioneProdotti {
	/**
	 * Aggiunge un prodotto agli articoli già presenti.
	 * 
	 * @param prodotto Prodotto da aggiungere.
	 */
	public abstract void aggiungiProdotto (Prodotto prodotto);
	
	/**
	 * Rimuove un prodotto dagli articoli presenti. La quantità di unità del prodotto
	 * da rimuovere deve essere positiva e non maggiore alla quantità attuale del
	 * prodotto.
	 * 
	 * @param codice Codice del prodotto da rimuovere.
	 * @param quantita Quantità di unità del prodotto da rimuovere.
	 * @return "true" se la rimozione è avvenuta con successo, "false" altrimenti.
	 */
	public boolean rimuoviProdotto(String codice, int quantita);
	
	/**
	 * Ritorna un prodotto dagli articoli presenti. Il prodotto viene selezionato
	 * sulla base del codice dell'articolo.
	 * 
	 * @param codice Codice del prodotto da ritornare.
	 * @return il Prodotto con quel codice se è presente, null altrimenti.
	 */
	public Prodotto getProdotto(String codice);
	
	/**
	 * Ritorna il prezzo del prodotto con tale valore maggiore.
	 * 
	 * @return Prezzo massimo.
	 */
	public float maxPrezzo ();
	
	/**
	 * Ritorna la quantità del prodotto con tale valore maggiore.
	 * 
	 * @return Quantità massima.
	 */
	public int maxQuantita ();
}