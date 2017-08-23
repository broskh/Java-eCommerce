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
	private float totaleScontato; /**<Totale scontato dei costi degli articoli inseriti nel carrello.*/
	
	/**
	 * Crea un Carrello vuoto.
	 */
	public Carrello () {
		this.articoli = new ArrayList <Prodotto> ();
		this.totale = 0;
		this.totaleScontato = 0;
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
	public float getTotaleScontato() {
		return totaleScontato;
	}


	/**
	 * Rimuove tutti gli articoli presenti.
	 */
	public void svuota() {
		this.articoli.clear();
		this.totale = 0;
		this.totaleScontato = 0;
	}

	@Override
	public void aggiungiProdotto(Prodotto prodotto) {
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCodice().equals(prodotto.getCodice())) {
				this.totaleScontato -= articolo.prezzoTotaleScontato();
				this.totale += prodotto.prezzoTotale();
				articolo.incrementaQuantita(prodotto.getQuantita());
				this.totaleScontato += articolo.prezzoTotaleScontato();
				return;
			}
		}
		this.totale += prodotto.prezzoTotale();
		this.totaleScontato += prodotto.prezzoTotaleScontato();
		this.articoli.add(prodotto);
	}

	@Override
	public boolean rimuoviProdotto(String codice, int quantita) {
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCodice().equals(codice)) {
				if (quantita > 0 && quantita <= articolo.getQuantita()) {
					this.totale -= articolo.prezzoTotale();
					this.totaleScontato -= articolo.prezzoTotaleScontato();
					articolo.decrementaQuantita(quantita);
					if (articolo.getQuantita() == 0) {
						this.articoli.remove(articolo);
					}
					else {
						this.totale += articolo.prezzoTotale();
						this.totaleScontato += articolo.prezzoTotaleScontato();
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
			if (articolo.getCodice().equals(codice)) {
				return articolo;
			}
		}		
		return null;
	}

	@Override
	public float MaxPrezzo() {
		float max = 0;
		for (Prodotto articolo : this.articoli) {
			if (articolo.getPrezzo() > max) {
				max = articolo.getPrezzo();
			}
		}
		return max;
	}

	@Override
	public int MaxQuantita() {
		int max = 0;
		for (Prodotto articolo : this.articoli) {
			if (articolo.getQuantita() > max) {
				max = articolo.getQuantita();
			}
		}
		return max;
	}

	@Override
	public String toString() {
		return "Carrello [articoli=" + articoli + ", totale=" + totale + ", totale_scontato=" + totaleScontato + "]";
	}
}