package negozio;

import java.util.ArrayList;

/**
 * La classe carrello gestisce tutte le informazioni relative al carrello di un cliente.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see GestioneProdotti
 * @see Prodotto
 */
public class Carrello implements GestioneProdotti{
	private ArrayList <Prodotto> articoli; /**<Articoli aggiunti dal cliente nel carrello.*/
	private float totale; /**<Totale dei costi degli articoli inseriti nel carrello.*/
	private float totale_scontato; /**<Totale scontato dei costi degli articoli inseriti nel carrello.*/
	
	/**
	 * Crea un Carrello vuoto.
	 */
	public Carrello () {
		this.articoli = new ArrayList <Prodotto> ();
		this.totale = 0;
		this.totale_scontato = 0;
	}
	
	/**
	 * @return gli articoli presenti nel carrello.
	 */
	public ArrayList <Prodotto> getArticoli() {
		return articoli;
	}

	/**
	 * @return il totale del costo degli articoli nel carrello, privo di sconto.
	 */
	public float getTotale() {
		return totale;
	}

	/**
	 * @return il totale del costo scontato degli articoli nel carrello.
	 */
	public float getTotale_scontato() {
		return totale_scontato;
	}


	/**
	 * Rimuove tutti gli articoli presenti.
	 */
	public void svuota() {
		this.articoli.clear();
		this.totale = 0;
		this.totale_scontato = 0;
	}

	@Override
	public void aggiungiProdotto(Prodotto prodotto) {
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCodice().equals(prodotto.getCodice())) {
				this.totale_scontato -= articolo.prezzoTotaleScontato();
				this.totale += prodotto.prezzoTotale();
				articolo.incrementaQuantita(prodotto.getQuantita());
				this.totale_scontato += articolo.prezzoTotaleScontato();
				return;
			}
		}
		this.articoli.add(prodotto);
	}

	@Override
	public boolean rimuoviProdotto(String codice, int quantita) {
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCodice().equals(codice)) {
				if (quantita > 0 && quantita >= articolo.getQuantita()) {
					this.totale -= articolo.prezzoTotale();
					this.totale_scontato -= articolo.prezzoTotaleScontato();
					articolo.decrementaQuantita(quantita);
					if (articolo.getQuantita() == 0) {
						this.articoli.remove(articolo);
					}
					else {
						this.totale += articolo.prezzoTotale();
						this.totale_scontato += articolo.prezzoTotaleScontato();
					}
					return true;
				}
				break;
			}
		}
		return false;
	}

	@Override
	public Prodotto getProdotto(String codice) {
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCodice() == codice) {
				return articolo;
			}
		}		
		return null;
	}

	@Override
	public String toString() {
		return "Carrello [articoli=" + articoli + ", totale=" + totale + ", totale_scontato=" + totale_scontato + "]";
	}
}