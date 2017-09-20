package negozio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Classe che gestisce le informazioni relative al magazzino di un negozio.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see GestioneProdotti
 * @see Prodotto
 */
public class Magazzino implements GestioneProdotti {
	/**
	 * Prodotti presenti in magazzino.
	 */
	private ArrayList <Prodotto> prodotti;

	/**
	 * Percorso dove salvare le immagini dei prodotti in magazzino.
	 */
	private static final String DIRECTORY_IMMAGINI_PRODOTTI = "media/img/products/";
	/**
	 * Percorso del file nel quale è memorizzato il percorso dell'ultimo file 
	 * magazzino salvato.
	 */
	private static final String PERCORSO_FILE_LAST = "media/saves/last"; 
	
	/**
	 * Stringa che indica il filtro per nome.
	 */
	public static final String STRINGA_FILTRO_NOME = "Nome"; 
	/**
	 * Stringa che indica il filtro per marca.
	 */
	public static final String STRINGA_FILTRO_MARCA = "Marca";
	/**
	 * Stringa che indica il filtro per codice.
	 */
	public static final String STRINGA_FILTRO_CODICE = "Codice";
	/**
	 * Stringa che indica il filtro per categoria.
	 */
	public static final String STRINGA_FILTRO_CATEGORIA = "Categoria";
	/**
	 * Stringa che indica il filtro per prezzo.
	 */
	public static final String STRINGA_FILTRO_PREZZO = "Prezzo";
	/**
	 * Stringa che indica il filtro per quantità.
	 */
	public static final String STRINGA_FILTRO_QUANTITA = "Quantità"; 
	
	/**
	 * Crea un {@link Magazzino} vuoto.
	 */
	public Magazzino () {
		this.prodotti = new ArrayList <Prodotto> ();
	}
	
	/*
	 * Prende un file immagine e ne ritorna il file che dovrà contenerne la 
	 * copia dopo il salvataggio del magazzino.
	 * 
	 * @param oldImageFile File dell'immagine da copiare.
	 * @return il file che ne conterrà la copia.
	 */
	private File getNuovoFileImmagine (File immagineSorgente) {
		String nuovoNomeFile = immagineSorgente.getName();
		File nuovoFile = new File (DIRECTORY_IMMAGINI_PRODOTTI + nuovoNomeFile);
		if (!nuovoFile.exists()) {
			return nuovoFile;
		}
		String estensione = "";
		int indiceEstensione = nuovoNomeFile.lastIndexOf('.');
		if (indiceEstensione != -1) {
			estensione = nuovoNomeFile.substring(indiceEstensione, nuovoNomeFile.length());
			nuovoNomeFile = nuovoNomeFile.substring(0, indiceEstensione);
		}
		int i = 0;
		do {
			i++;
			nuovoFile = new File (DIRECTORY_IMMAGINI_PRODOTTI + nuovoNomeFile + "_" + i + estensione);
		} while (nuovoFile.exists());
		return nuovoFile;
	}

	/*
	 * Crea una copia dell'immagine del prodotto nell'apposita cartella.
	 * 
	 * @param prodotto Prodotto del quale copiare l'immagine.
	 */
	private void copiaImmagineProdotto(Prodotto prodotto) {
		if (!prodotto.getImmagine().equals(Prodotto.IMMAGINE_DEFAULT)) {	
			File nuovoFile = getNuovoFileImmagine(prodotto.getImmagine());
			try {
				Files.copy(prodotto.getImmagine().toPath(), nuovoFile.toPath());
				prodotto.setImmagine(nuovoFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Salva lo stato di un magazzino su un file.
	 * 
	 * @param file File sul quale salvare il magazzino.
	 * @return "true" se il salvataggio è avvenuto con successo, "false" altrimenti.
	 */
	public boolean salvaMagazzino (File file) {
		ObjectOutputStream objectOutputStream;
		try {
//			CREO UNA COPIA DELLE IMMAGINI
			Magazzino vecchioMagazzino = null;
			if (file.exists()) {
				vecchioMagazzino = new Magazzino();
				vecchioMagazzino.caricaMagazzino(file);
			}
			for (Prodotto prodotto : this.prodotti) {
				this.copiaImmagineProdotto(prodotto);
			}
			if (vecchioMagazzino != null) {
				for (Prodotto prodotto : vecchioMagazzino.getProdotti()) {
					File fileToDelete = prodotto.getImmagine();
					if (!fileToDelete.equals(Prodotto.IMMAGINE_DEFAULT)) {
						try {
							Files.delete(fileToDelete.toPath());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
//			SALVO EFFETTIVAMENTE IL MAGAZZINO
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(this.prodotti);
			objectOutputStream.close();
//			SALVO NELL'APPOSITO FILE IL PERCORSO DELL'ULTIMO MAGAZZINO SALVATO
			BufferedWriter lastStoreFile = null;
	        try {
	        	lastStoreFile = new BufferedWriter(new FileWriter(
	        			new File(PERCORSO_FILE_LAST)));
	            String filePath = file.toString();
	            lastStoreFile.write(filePath);
	        	lastStoreFile.close();
	        } catch ( IOException g ) {
	            g.printStackTrace();
	        }
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
	 * @param file File sul quale salvare il magazzino.
	 * @return "true" se il salvataggio è avvenuto con successo, "false" altrimenti.
	 */
	public boolean salvaMagazzino (String file) {
		return this.salvaMagazzino(new File (file));
	}

	/**
	 * Carica lo stato di un magazzino da un file.
	 * 
	 * @param file File dal quale caricare il magazzino.
	 * @return "true" se il caricamento è avvenuto con successo, "false" altrimenti.
	 */
	@SuppressWarnings("unchecked")
	public boolean caricaMagazzino (File file) {

		ObjectInputStream objectInputStream;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(file));
			this.prodotti.clear();
			ArrayList <Prodotto> prodottiDelFile = 
					(ArrayList <Prodotto>) objectInputStream.readObject();
			for (Prodotto prodotto : prodottiDelFile) {
				this.prodotti.add(prodotto);
				if (!prodotto.getImmagine().exists()) {
					prodotto.setImmagine(Prodotto.IMMAGINE_DEFAULT);
				}
			}
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
	 * @param file File dal quale caricare il magazzino.
	 * @return "true" se il caricamento è avvenuto con successo, "false" altrimenti.
	 */
	public boolean caricaMagazzino (String file) {
		return this.caricaMagazzino(new File (file));
	}
	
	/**
	 * Filtra i prodotti presenti in magazzino e ritorna quelli la cui categoria rispetta
	 * il pattern di ricerca (case insensitive).
	 * 
	 * @param pattern Pattern di ricerca.
	 * @return i prodotti che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerCategoria (String pattern) {
		ArrayList <Prodotto> prodottiFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getCategoria().toLowerCase().contains(pattern.toLowerCase())) {
				prodottiFiltrati.add(prodotto);
			}
		}
		return prodottiFiltrati;
	}
	
	/**
	 * Filtra i prodotti presenti in magazzino e ritorna quelli il cui nome rispetta
	 * il pattern di ricerca (case insensitive).
	 * 
	 * @param pattern Pattern di ricerca.
	 * @return i prodotti che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerNome (String pattern) {
		ArrayList <Prodotto> prodottiFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getNome().toLowerCase().contains(pattern.toLowerCase())) {
				prodottiFiltrati.add(prodotto);
			}
		}
		return prodottiFiltrati;
	}
	
	/**
	 * Filtra i prodotti presenti in magazzino e ritorna quelli la cui marca rispetta
	 * il pattern di ricerca (case insensitive).
	 * 
	 * @param pattern Pattern di ricerca.
	 * @return i prodotti che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerMarca (String pattern) {
		ArrayList <Prodotto> prodottiFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getMarca().toLowerCase().contains(pattern.toLowerCase())) {
				prodottiFiltrati.add(prodotto);
			}
		}
		return prodottiFiltrati;
	}
	
	/**
	 * Filtra i prodotti presenti in magazzino e ritorna quelli il cui codice rispetta
	 * il pattern di ricerca (case insensitive).
	 * 
	 * @param pattern Pattern di ricerca.
	 * @return i prodotti che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerCodice (String pattern) {
		ArrayList <Prodotto> prodottiFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getCodice().toLowerCase().contains(pattern.toLowerCase())) {
				prodottiFiltrati.add(prodotto);
			}
		}
		return prodottiFiltrati;
	}
	
	/**
	 * Filtra i prodotti presenti in magazzino e ritorna quelli il cui prezzo è
	 * compreso nel range indicato.
	 * 
	 * @param prezzoMin Valore minimo del range.
	 * @param prezzoMax Valore massimo del range.
	 * @return i prodotti che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerPrezzo (float prezzoMin, float prezzoMax) {
		ArrayList <Prodotto> prodottiFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getPrezzoCadaunoScontato() >= prezzoMin && 
					prodotto.getPrezzoCadaunoScontato() <= prezzoMax) {
				prodottiFiltrati.add(prodotto);
			}
		}
		return prodottiFiltrati;
	}
	
	/**
	 * Filtra i prodotti presenti in magazzino e ritorna quelli il la cui quantità è
	 * compresa nel range indicato.
	 * 
	 * @param quantitaMin Valore minimo della quantità.
	 * @param quantitaMax Valore massimo della quantità.
	 * @return i prodotti che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerQuantita (int quantitaMin, int quantitaMax) {
		ArrayList <Prodotto> prodottiFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto prodotto : this.prodotti) {
			if (prodotto.getQuantita() >= quantitaMin && 
					prodotto.getQuantita() <= quantitaMax) {
				prodottiFiltrati.add(prodotto);
			}
		}
		return prodottiFiltrati;
	}
	
	@Override
	public ArrayList <Prodotto> getProdotti() {
		return this.prodotti;
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
				if (prodotto.decrementaQuantita(quantita)) {
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
		return "Magazzino [prodotti=" + prodotti + "]";
	}
}