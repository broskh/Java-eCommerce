package negozio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * La classe Magazzino gestisce le informazioni relative al magazzino di un negozio.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see GestioneProdotti
 * @see Prodotto
 */
public class Magazzino implements GestioneProdotti {
	private ArrayList <Prodotto> articoli; /**<Articoli presenti in magazzino.*/
	
	/**
	 * Crea un Magazzino vuoto.
	 */
	public Magazzino () {
		this.articoli = new ArrayList <Prodotto> ();
	}
	
	/**
	 * @return gli articoli presenti in magazzino.
	 */
	public ArrayList <Prodotto> getArticoli() {
		return articoli;
	}
	
	/**
	 * Salva lo stato di un magazzino su un file.
	 * 
	 * @param file File sul quale salvare lo stato del magazzino.
	 * @return "true" se il salvataggio è avvenuto con successo, "false" altrimenti.
	 */
	public boolean salvaMagazzino (File file) {
		ObjectOutputStream objectOutputStream;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(this.articoli);
			objectOutputStream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Salva lo stato di un magazzino su un file.
	 * 
	 * @param file File sul quale salvare lo stato del magazzino.
	 * @return "true" se il salvataggio è avvenuto con successo, "false" altrimenti.
	 */
	public boolean salvaMagazzino (String file) {
		return this.salvaMagazzino(new File (file));
	}

	/**
	 * Carica lo stato di un magazzino da un file.
	 * 
	 * @param file File dal quale caricare lo stato del magazzino.
	 * @return "true" se il caricamento è avvenuto con successo, "false" altrimenti.
	 */
	public boolean caricaMagazzino (File file) {

		ObjectInputStream objectInputStream;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(file));
			this.articoli = (ArrayList <Prodotto>) objectInputStream.readObject();
			objectInputStream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Carica lo stato di un magazzino da un file.
	 * 
	 * @param file File dal quale caricare lo stato del magazzino.
	 * @return "true" se il caricamento è avvenuto con successo, "false" altrimenti.
	 */
	public boolean caricaMagazzino (String file) {
		return this.caricaMagazzino(new File (file));
	}
	
	/**
	 * Filtra gli articoli presenti in magazzino e ritorna quelli la cui categoria rispetta
	 * il pattern di ricerca.
	 * 
	 * @param pattern Stringa utilizzata come pattern di ricerca.
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerCategoria (String pattern) {
		ArrayList <Prodotto> articoliFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCategoria().contains(pattern)) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}
	
	/**
	 * Filtra gli articoli presenti in magazzino e ritorna quelli il cui nome rispetta
	 * il pattern di ricerca.
	 * 
	 * @param pattern Stringa utilizzata come pattern di ricerca.
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerNome (String pattern) {
		ArrayList <Prodotto> articoliFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto articolo : this.articoli) {
			if (articolo.getNome().contains(pattern)) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}
	
	/**
	 * Filtra gli articoli presenti in magazzino e ritorna quelli la cui marca rispetta
	 * il pattern di ricerca.
	 * 
	 * @param pattern Stringa utilizzata come pattern di ricerca.
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerMarca (String pattern) {
		ArrayList <Prodotto> articoliFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto articolo : this.articoli) {
			if (articolo.getMarca().contains(pattern)) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}
	
	/**
	 * Filtra gli articoli presenti in magazzino e ritorna quelli il cui codice rispetta
	 * il pattern di ricerca.
	 * 
	 * @param pattern Stringa utilizzata come pattern di ricerca.
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerCodice (String pattern) {
		ArrayList <Prodotto> articoliFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto articolo : this.articoli) {
			if (articolo.getCodice().contains(pattern)) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}
	
	/**
	 * Filtra gli articoli presenti in magazzino e ritorna quelli il cui prezzo è
	 * compreso nel range indicato.
	 * 
	 * @param prezzoMin Valore minimo del range.
	 * @param prezzoMax Valore massimo del range.
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerPrezzo (float prezzoMin, float prezzoMax) {
		ArrayList <Prodotto> articoliFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto articolo : this.articoli) {
			if (articolo.prezzoCadaunoScontato() >= prezzoMin && articolo.prezzoCadaunoScontato() <= prezzoMax) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}
	
	/**
	 * Filtra gli articoli presenti in magazzino e ritorna quelli il la cui quantità è
	 * msggiore di quella indicata.
	 * 
	 * @param quantitaMin Valore minimo della quantità.
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerQuantita (int quantitaMin) {
		ArrayList <Prodotto> articoliFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto articolo : this.articoli) {
			if (articolo.getQuantita() >= quantitaMin) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}
	
	/**
	 * Filtra gli articoli presenti in magazzino e ritorna quelli in offerta.
	 * 
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	/*public ArrayList <Prodotto> filtraPerOfferta () {
		ArrayList <Prodotto> articoliFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto articolo : this.articoli) {
			if (articolo.inOfferta ()) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}*/
	
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
				if (articolo.decrementaQuantita(quantita)) {
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
	public String toString() {
		return "Magazzino [articoli=" + articoli + "]";
	}
}