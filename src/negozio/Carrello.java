package negozio;

import java.util.ArrayList;

/**
 * Classe che gestisce tutte le informazioni relative al carrello di un cliente.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see GestioneProdotti
 * @see Prodotto
 */
public class Carrello implements GestioneProdotti{
	/**
	 * Prodotti presenti nel carrello.
	 */
	private ArrayList <Prodotto> prodotti;
	
	/**
	 * Crea un {@link Carrello} vuoto.
	 */
	public Carrello () {
		this.prodotti = new ArrayList <Prodotto> ();
	}

	/**
	 * Ritorna il totale del costo dei prodotti nel carrello (privo di sconto).
	 * 
	 * @return il totale non scontato.
	 */
	public float getTotale() {
		float totale = 0;
		for (Prodotto prodotto : this.prodotti) {
			totale += prodotto.getPrezzoTotale();
		}
		return totale;
	}

	/**
	 * Ritorna il totale del costo scontato dei prodotti nel carrello.
	 * 
	 * @return il totale scontato.
	 */
	public float getTotaleScontato() {
		float totaleScontato = 0;
		for (Prodotto prodotto : this.prodotti) {
			totaleScontato += prodotto.getPrezzoTotaleScontato();
		}
		return totaleScontato;
	}


	/**
	 * Rimuove tutti i prodotti presenti nel carrello.
	 */
	public void svuota() {
		this.prodotti.clear();
	}
	
	@Override
	public ArrayList <Prodotto> getProdotti() {
		return prodotti;
	}

	@Override
	public Prodotto getProdotto(String codice) {
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getCodice().equals(codice)) {
				return prodotto;
			}
		}		
		return null;
	}

	@Override
	public void aggiungiProdotto(Prodotto prodotto) {
		for (Prodotto prodottoPresente : this.prodotti) {
			if (prodottoPresente.getCodice().equals(prodotto.getCodice())) {
				prodottoPresente.incrementaQuantita(prodotto.getQuantita());
				return;
			}
		}
		this.prodotti.add(prodotto);
	}

	@Override
	public boolean rimuoviProdotto(String codice, int quantita) {
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getCodice().equals(codice)) {
				if (quantita > 0 && quantita <= prodotto.getQuantita()) {
					prodotto.decrementaQuantita(quantita);
					if (prodotto.getQuantita() == 0) {
						this.prodotti.remove(prodotto);
					}
					return true;
				}
				break;
			}
		}
		return false;
	}

	@Override
	public float maxPrezzo() {
		float max = 0;
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getPrezzo() > max) {
				max = prodotto.getPrezzo();
			}
		}
		return max;
	}

	@Override
	public int maxQuantita() {
		int max = 0;
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getQuantita() > max) {
				max = prodotto.getQuantita();
			}
		}
		return max;
	}

	@Override
	public String toString() {
		return "Carrello [prodotti=" + this.prodotti + "]";
	}
}