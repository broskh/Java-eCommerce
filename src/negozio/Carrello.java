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
	
	/**
	 * Crea un Carrello vuoto.
	 */
	public Carrello () {
		this.articoli = new ArrayList <Prodotto> ();
	}

	/**
	 * @return il totale del costo degli articoli nel carrello, privo di sconto.
	 */
	public float getTotale() {
		float totale = 0;
		for (Prodotto articolo : this.articoli) {
			totale += articolo.prezzoTotale();
		}
		return totale;
	}

	/**
	 * @return il totale del costo scontato degli articoli nel carrello.
	 */
	public float getTotaleScontato() {
		float totaleScontato = 0;
		for (Prodotto articolo : this.articoli) {
			totaleScontato += articolo.prezzoTotaleScontato();
		}
		return totaleScontato;
	}


	/**
	 * Rimuove tutti gli articoli presenti.
	 */
	public void svuota() {
		this.articoli.clear();
	}
	
	@Override
	public ArrayList <Prodotto> getArticoli() {
		return articoli;
	}

	@Override
	public void aggiungiProdotto(Prodotto prodotto) {
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCodice().equals(prodotto.getCodice())) {
				articolo.incrementaQuantita(prodotto.getQuantita());
				return;
			}
		}
		this.articoli.add(prodotto);
	}

	@Override
	public boolean rimuoviProdotto(String codice, int quantita) {
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCodice().equals(codice)) {
				if (quantita > 0 && quantita <= articolo.getQuantita()) {
					articolo.decrementaQuantita(quantita);
					if (articolo.getQuantita() == 0) {
						this.articoli.remove(articolo);
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
	public float maxPrezzo() {
		float max = 0;
		for (Prodotto articolo : this.articoli) {
			if (articolo.getPrezzo() > max) {
				max = articolo.getPrezzo();
			}
		}
		return max;
	}

	@Override
	public int maxQuantita() {
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
		return "Carrello [articoli=" + articoli + "]";
	}
}