package negozio;

import java.util.ArrayList;

/**
 * Questa interfeccia implementa i metodi astratti necessari per la gestione di 
 * un'insieme di prodotti.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see Magazzino
 * @see Carrello
 */
public interface GestioneProdotti {
	
	/**
	 * @return i prodotti gestiti.
	 */
	public ArrayList <Prodotto> getProdotti();
	
	/**
	 * Ritorna un prodotto fra quelli gestiti. Il prodotto viene selezionato
	 * sulla base del suo codice.
	 * 
	 * @param codice Codice del prodotto da ritornare.
	 * @return il Prodotto con quel codice se è presente, null altrimenti.
	 */
	public Prodotto getProdotto(String codice);
	
	/**
	 * Aggiunge un prodotto a quelli già presenti.
	 * 
	 * @param prodotto Prodotto da aggiungere.
	 */
	public void aggiungiProdotto (Prodotto prodotto);
	
	/**
	 * Rimuove un prodotto da quelli presenti.
	 * 
	 * @param codice Codice del prodotto da rimuovere.
	 * @param quantita Quantità di unità del prodotto da rimuovere.
	 * @return "true" se la rimozione è avvenuta con successo, "false" altrimenti.
	 */
	public boolean rimuoviProdotto(String codice, int quantita);
	
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