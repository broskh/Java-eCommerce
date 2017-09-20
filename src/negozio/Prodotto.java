package negozio;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Classe che gestisce tutte le informazioni relative ad un prodotto.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see GestioneProdotti
 * @see Magazzino
 * @see Carrello
 * @see Promozione
 */
public class Prodotto implements Serializable, Cloneable, Transferable {
	private static final long serialVersionUID = 7588762583069098992L;
	/**
	 * Nome del prodotto.
	 */
	private String nome;
	/**
	 * Marca del prodotto.
	 */
	private String marca;
	/**
	 * Codice del prodotto.
	 */
	private String codice;
	/**
	 * Categoria alla quale appartiene il prodotto.
	 */
	private String categoria;
	/**
	 * Prezzo del prodotto.
	 */
	private float prezzo;
	/**
	 * Immagine del prodotto.
	 */
	private File immagine;
	/**
	 * Quantità del prodotto presente.
	 */
	private int quantita;
	/**
	 * Promozione attiva sul prodotto.
	 */
	private Promozione offerta;
	
	/**
	 * Immagine di default.
	 */
	public static final File IMMAGINE_DEFAULT = 
			new File ("media/img/immagine_non_disponibile.jpg");
	
	/**
	 * Crea un {@link Prodotto}, completo di tutte le informazioni.
	 * 
	 * @param nome Nome del prodotto.
	 * @param marca Marca del prodotto.
	 * @param codice Codice del prodotto.
	 * @param categoria Categoria del prodotto.
	 * @param prezzo Prezzo del prodotto
	 * @param immagine Immagine del prodotto.
	 * @param quantita Quantita del prodotto.
	 * @param offerta Offerta attiva sul prodotto.
	 */
	public Prodotto(String nome, String marca, String codice, String categoria, 
			float prezzo, File immagine, int quantita, Promozione offerta) {
		this.nome = nome;
		this.marca = marca;
		this.codice = codice;
		this.categoria = categoria;
		this.prezzo = 0;
		this.setPrezzo(prezzo);
		this.setImmagine(immagine);
		this.quantita = 0;
		this.setQuantita(quantita);
		this.offerta = offerta;
	}

	/**
	 * Crea un {@link Prodotto}, completo di tutte le informazioni.
	 * 
	 * @param nome Nome del prodotto.
	 * @param marca Marca del prodotto.
	 * @param codice Codice del prodotto.
	 * @param categoria Categoria del prodotto.
	 * @param prezzo Prezzo del prodotto
	 * @param immagine Immagine del prodotto.
	 * @param quantita Quantita del prodotto.
	 * @param offerta Offerta attiva sul prodotto.
	 */
	public Prodotto(String nome, String marca, String codice, String categoria, 
			float prezzo, String immagine, int quantita, Promozione offerta) {
		this (nome, marca, codice, categoria, prezzo, new File (immagine), quantita, offerta);
	}

	/**
	 * Crea un {@link Prodotto} non in offerta, completo di tutte le informazioni.
	 * 
	 * @param nome Nome del prodotto.
	 * @param marca Marca del prodotto.
	 * @param codice Codice del prodotto.
	 * @param categoria Categoria del prodotto.
	 * @param prezzo Prezzo del prodotto
	 * @param immagine Immagine del prodotto.
	 * @param quantita Quantita del prodotto.
	 */
	public Prodotto(String nome, String marca, String codice, String categoria, 
			float prezzo, File immagine, int quantita) {
		this (nome, marca, codice, categoria, prezzo, immagine, quantita, null);
	}

	/**
	 * Crea un {@link Prodotto} non in offerta, completo di tutte le informazioni.
	 * 
	 * @param nome Nome del prodotto.
	 * @param marca Marca del prodotto.
	 * @param codice Codice del prodotto.
	 * @param categoria Categoria del prodotto.
	 * @param prezzo Prezzo del prodotto
	 * @param immagine Immagine del prodotto.
	 * @param quantita Quantita del prodotto.
	 */
	public Prodotto(String nome, String marca, String codice, String categoria, 
			float prezzo, String immagine, int quantita) {
		this (nome, marca, codice, categoria, prezzo, new File (immagine), quantita, null);
	}

	/**
	 * Crea un {@link Prodotto} vuoto, senza alcun tipo di informazione.
	 */
	public Prodotto() {
		this ("", "", "", "", 0, Prodotto.IMMAGINE_DEFAULT, 0, null);
	}
	
	/**
	 * Ritorna il DataFlavor per la classe Prodotto.
	 * 
	 * @return il DataFlavor della classe.
	 */
	public static final DataFlavor getDataFlavor () {
		try {
			return new DataFlavor (DataFlavor.javaJVMLocalObjectMimeType 
					+ ";class=\""+Prodotto.class.getName() + "\"");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ritorna il nome del prodotto.
	 * 
	 * @return il nome del prodotto.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Modifica il nome del prodotto.
	 * 
	 * @param nome Nuovo nome del prodotto.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Ritorna la marca del prodotto.
	 * 
	 * @return la marca del prodotto.
	 */
	public String getMarca() {
		return marca;
	}
	
	/**
	 * Modifica la marca del prodotto.
	 * 
	 * @param marca Nuova marca del prodotto.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	/**
	 * Ritorna il codice del prodotto.
	 * 
	 * @return il codice del prodotto.
	 */
	public String getCodice() {
		return codice;
	}
	
	/**
	 * Modifica il codice del prodotto.
	 * 
	 * @param codice Nuovo codice del prodotto.
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	/**
	 * Ritorna la categoria del prodotto.
	 * 
	 * @return la categoria nella quale è inserito il prodotto.
	 */
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 * Modifica la categoria del prodotto.
	 * 
	 * @param categoria Nuova categoria del prodotto.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * Ritorna il prezzo del prodotto.
	 * 
	 * @return il prezzo del prodotto.
	 */
	public float getPrezzo() {
		return prezzo;
	}
	
	/**
	 * Modifica il prezzo del prodotto (se il valore è maggiore di 0).
	 * 
	 * @param prezzo Nuovo prezzo del prodotto.
	 */
	public void setPrezzo(float prezzo) {
		if (prezzo > 0) {
			this.prezzo = prezzo;
		}
	}
	
	/**
	 * Ritorna l'immagine del prodotto.
	 * 
	 * @return l'immagine del prodotto.
	 */
	public File getImmagine() {
		return immagine;
	}
	
	/**
	 * Modifica l'immagine del prodotto.
	 * 
	 * @param immagine Nuova immagine del prodotto.
	 */
	public void setImmagine(File immagine) {
		if(immagine.exists() && !immagine.isDirectory()) { 
			this.immagine = immagine;
		}
		else {
			this.immagine = IMMAGINE_DEFAULT;
		}
	}
	
	/**
	 * Modifica l'immagine del prodotto.
	 * 
	 * @param immagine Nuova immagine del prodotto.
	 */
	public void setImmagine(String immagine) {
		this.setImmagine(new File (immagine));
	}
	
	/**
	 * Ritorna la quantità del prodotto.
	 * 
	 * @return la quantità del prodotto.
	 */
	public int getQuantita() {
		return quantita;
	}
	
	/**
	 * Modifica la quantità del prodotto (se si tratta di un valore positivo).
	 * 
	 * @param quantita Nuova quantità del prodotto.
	 */
	public void setQuantita(int quantita) {
		if (quantita >= 0) {
			this.quantita = quantita;
		}
	}
	
	/**
	 * Ritorna l'offerta attiva sul prodotto.
	 * 
	 * @return l'offerta attiva sul prodotto.
	 */
	public Promozione getOfferta() {
		return offerta;
	}
	
	/**
	 * Modifica l'offerta attiva sul prodotto.
	 * 
	 * @param offerta Nuova offerta attiva sul prodotto.
	 */
	public void setOfferta(Promozione offerta) {
		this.offerta = offerta;
	}
	
	/**
	 * Decrementa la quantità del prodotto del valore indicato. Se il valore indicato
	 * è negativo o maggiore della quantità attuale del prodotto, il decremento non
	 * viene eseguito.
	 * 
	 * @param quantita Valore del quale decrementare la quantità del prodotto.
	 * @return "true" se il decremento è avvenuto con successo, "false" altrimenti.
	 */
	public boolean decrementaQuantita (int quantita) {
		if (quantita >= 0 && quantita <= this.quantita) {
			this.quantita -= quantita;
			return true;
		}
		return false;
	}
	
	/**
	 * Incrementa la quantità del prodotto del valore indicato. Se il valore indicato
	 * è negativo l'incremento non viene eseguito.
	 * 
	 * @param quantita Valore del quale incrementare la quantità del prodotto.
	 * @return "true" se l'incremento è avvenuto con successo, "false" altrimenti.
	 */
	public boolean incrementaQuantita (int quantita) {
		if (quantita >= 0) {
			this.quantita += quantita;
			return true;
		}
		return false;
	}
	
	/**
	 * Indica se un Prodotto è in offerta.
	 * 
	 * @return <code>true</code> se sul Prodotto è attiva un'offerta, 
	 * <code>false</code> altrimenti.
	 */
	public boolean inOfferta () {
		if (this.offerta != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Calcola il prezzo scontato di una sola unità del prodotto.
	 * 
	 * @return il prezzo scontato cadauno.
	 */
	public float getPrezzoCadaunoScontato () {
		if (this.offerta != null) {
			return this.offerta.calcolaSconto(this.prezzo, 1);
		}
		return this.getPrezzo();
	}
	
	/**
	 * Calcola il prezzo non scontato dell'intera quantità del prodotto.
	 * 
	 * @return il prezzo totale non scontato.
	 */
	public float getPrezzoTotale () {
		return this.prezzo * this.quantita;
	}
	
	/**
	 * Calcola il prezzo scontato dell'intera quantità del prodotto.
	 * 
	 * @return il prezzo totale scontato.
	 */
	public float getPrezzoTotaleScontato () {
		if (this.offerta != null) {
			return this.offerta.calcolaSconto(this.prezzo, this.quantita);
		}
		return this.getPrezzoTotale();
	}
	
	@Override
	public Prodotto clone() throws CloneNotSupportedException {
		return (Prodotto) super.clone();
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] supportedFlavors = { Prodotto.getDataFlavor() };
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (flavor.equals(Prodotto.getDataFlavor())) {
			return true;
		}
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) 
			throws UnsupportedFlavorException, IOException {
		if (flavor.equals(Prodotto.getDataFlavor())) {
			return this;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Prodotto [nome=" + nome + ", marca=" + marca + ", codice=" + codice 
				+ ", categoria=" + categoria + ", prezzo=" + prezzo + ", immagine=" 
				+ immagine + ", quantita=" + quantita + ", offerta=" + offerta + "]";
	}
}