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
 * La classe Magazzino gestisce le informazioni relative al magazzino di un negozio.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see GestioneProdotti
 * @see Prodotto
 */
public class Magazzino implements GestioneProdotti {
	private ArrayList <Prodotto> articoli; /**<Articoli presenti in magazzino.*/

	public static final String STRINGA_FILTRO_NOME = 
			"Nome"; /**<Stringa che indica il filtro per nome.*/
	public static final String STRINGA_FILTRO_MARCA = 
			"Marca"; /**<Stringa che indica il filtro per marca.*/
	public static final String STRINGA_FILTRO_CODICE = 
			"Codice"; /**<Stringa che indica il filtro per codice.*/
	public static final String STRINGA_FILTRO_CATEGORIA = 
			"Categoria"; /**<Stringa che indica il filtro per categoria.*/
	public static final String STRINGA_FILTRO_PREZZO = 
			"Prezzo"; /**<Stringa che indica il filtro per prezzo.*/
	public static final String STRINGA_FILTRO_QUANTITA = 
			"Quantità"; /**<Stringa che indica il filtro per quantità.*/

	private static final String NEW_IMAGES_FOLDER = "media/img/products/";
	/**<Percorso dove salvare le immagini dei prodotti in magazzino.*/
	private static final String LAST_STORE_FILE_PATH = "media/saves/last"; 
	/**<File nel quale è memorizzato l'ultimo file magazzino salvato.*/

	/**
	 * Copia il file sorgente nel file di destinazione.
	 * 
	 * @param sorgente File sorgente.
	 * @param destinazione File di destinazione.
	 * @throws IOException
	 */
	private static void copyImage(File sorgente, File destinazione) throws IOException {
		Files.copy(sorgente.toPath(), destinazione.toPath());
	}
	
	/**
	 * Prende un file immagine e ne ritorna il file che dovrà contenerne la copia dopo il salvataggio del magazzino.
	 * @param oldImageFile File dell'immagine da copiare.
	 * @return il file che ne conterrà la copia.
	 */
	private static File getNewImageFile (File oldImageFile) {
		String newFileName = oldImageFile.getName();
		File newFile = new File (NEW_IMAGES_FOLDER + newFileName);
		if (!newFile.exists()) {
			return newFile;
		}
		String extension = "";
		int extensionIndex = newFileName.lastIndexOf('.');
		if (extensionIndex != -1) {
			extension = newFileName.substring(extensionIndex, newFileName.length());
			newFileName = newFileName.substring(0, extensionIndex);
		}
		int i = 0;
		do {
			i++;
			newFile = new File (NEW_IMAGES_FOLDER + newFileName + "_" + i + extension);
		} while (newFile.exists());
		return newFile;
	}
	
	/**
	 * Crea un Magazzino vuoto.
	 */
	public Magazzino () {
		this.articoli = new ArrayList <Prodotto> ();
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
//			CREO UNA COPIA DELLE IMMAGINI
			Magazzino vecchioMagazzino = null;
			if (file.exists()) {
				vecchioMagazzino = new Magazzino();
				vecchioMagazzino.caricaMagazzino(file);
			}
			for (Prodotto articolo : this.articoli) {
				if (!articolo.getImmagine().equals(Prodotto.IMMAGINE_DEFAULT)) {	
					File newFile = getNewImageFile(articolo.getImmagine());
					try {
						copyImage(articolo.getImmagine(), newFile);
						articolo.setImmagine(newFile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			if (vecchioMagazzino != null) {
				for (Prodotto articolo : vecchioMagazzino.getArticoli()) {
					File fileToDelete = articolo.getImmagine();
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
			objectOutputStream.writeObject(this.articoli);
			objectOutputStream.close();
//			SALVO NELL'APPOSITO FILE IL PERCORSO DELL'ULTIMO MAGAZZINO SALVATO
			BufferedWriter lastStoreFile = null;
	        try {
	        	lastStoreFile = new BufferedWriter(new FileWriter(
	        			new File(LAST_STORE_FILE_PATH)));
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
	@SuppressWarnings("unchecked")
	public boolean caricaMagazzino (File file) {

		ObjectInputStream objectInputStream;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(file));
			this.articoli.clear();
			ArrayList <Prodotto> articoli = (ArrayList <Prodotto>) objectInputStream.readObject();
			for (Prodotto articolo : articoli) {
				this.articoli.add(articolo);
				if (!articolo.getImmagine().exists()) {
					articolo.setImmagine(Prodotto.IMMAGINE_DEFAULT);
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
			if (articolo.getCategoria().toLowerCase().contains(pattern.toLowerCase())) {
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
			if (articolo.getNome().toLowerCase().contains(pattern.toLowerCase())) {
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
			if (articolo.getMarca().toLowerCase().contains(pattern.toLowerCase())) {
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
			if (articolo.getCodice().toLowerCase().contains(pattern.toLowerCase())) {
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
	 * maggiore di quella indicata.
	 * 
	 * @param quantitaMin Valore minimo della quantità.
	 * @param quantitaMax Valore massimo della quantità.
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerQuantita (float quantitaMin, float quantitaMax) {
		ArrayList <Prodotto> articoliFiltrati = new ArrayList <Prodotto> ();
		for (Prodotto articolo : this.articoli) {
			if (articolo.getQuantita() >= quantitaMin && articolo.getQuantita() <= quantitaMax) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}
	
	/**
	 * Filtra gli articoli presenti in magazzino e ritorna quelli il la cui quantità è
	 * maggiore di quella indicata.
	 * 
	 * @param quantitaMin Valore minimo della quantità.
	 * @param quantitaMax Valore massimo della quantità.
	 * @return gli articoli che rispettano il criterio di filtraggio.
	 */
	public ArrayList <Prodotto> filtraPerQuantita (int quantitaMin, int quantitaMax) {
		return this.filtraPerQuantita((float)quantitaMin, (float)quantitaMax);
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
		return "Magazzino [articoli=" + articoli + "]";
	}
	
	

}