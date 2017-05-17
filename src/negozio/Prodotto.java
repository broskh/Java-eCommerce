package negozio;

import java.io.File;
import java.io.Serializable;

/**
 * La classe prodotto gestisce tutte le informazioni relative ad un prodotto.
 * 
 * @author Alessio Scheri
 * @version 1.0
 */
public class Prodotto  implements Serializable{
	private static final long serialVersionUID = 7588762583069098992L;
	
	private String nome; /**<Nome del prodotto.*/
	private String marca; /**<Marca del prodotto.*/
	private String codice; /**<Codice del prodotto.*/
	private String categoria; /**<Categoria alla quale appartiene il prodotto.*/
	private float prezzo; /**<Prezzo del prodotto.*/
	private File immagine; /**<Immagine del prodotto.*/
	private int quantita; /**<Quantità del prodotto presente.*/
	private Promozione offerta; /**<Promozione attiva sul prodotto.*/
	
	private static final File immagineDefault = new File ("media/img/immagine_non_disponibile.jpg"); /**<Immagine di default.*/ 

	/**
	 * Crea un Prodotto in offerta, completo di tutte le informazioni.
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
	public Prodotto(String nome, String marca, String codice, String categoria, float prezzo, File immagine,
			int quantita, Promozione offerta) {
		this.nome = nome;
		this.marca = marca;
		this.codice = codice;
		this.categoria = categoria;
		if (prezzo > 0) {
			this.prezzo = prezzo;			
		}
		else {
			this.prezzo = 0;
		}
		if(immagine.exists() && !immagine.isDirectory()) { 
			this.immagine = immagine;
		}
		else {
			this.immagine = Prodotto.immagineDefault;
		}
		if (quantita >= 0) {
			this.quantita = quantita;
		}
		this.offerta = offerta;
	}

	/**
	 * Crea un Prodotto in offerta, completo di tutte le informazioni.
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
	public Prodotto(String nome, String marca, String codice, String categoria, float prezzo, String immagine,
			int quantita, Promozione offerta) {
		this (nome, marca, codice, categoria, prezzo, new File (immagine), quantita, offerta);
	}

	/**
	 * Crea un Prodotto non in offerta, completo di tutte le informazioni.
	 * 
	 * @param nome Nome del prodotto.
	 * @param marca Marca del prodotto.
	 * @param codice Codice del prodotto.
	 * @param categoria Categoria del prodotto.
	 * @param prezzo Prezzo del prodotto
	 * @param immagine Immagine del prodotto.
	 * @param quantita Quantita del prodotto.
	 */
	public Prodotto(String nome, String marca, String codice, String categoria, float prezzo, File immagine,
			int quantita) {
		this (nome, marca, codice, categoria, prezzo, immagine, quantita, null);
	}

	/**
	 * Crea un Prodotto non in offerta, completo di tutte le informazioni.
	 * 
	 * @param nome Nome del prodotto.
	 * @param marca Marca del prodotto.
	 * @param codice Codice del prodotto.
	 * @param categoria Categoria del prodotto.
	 * @param prezzo Prezzo del prodotto
	 * @param immagine Immagine del prodotto.
	 * @param quantita Quantita del prodotto.
	 */
	public Prodotto(String nome, String marca, String codice, String categoria, float prezzo, String immagine,
			int quantita) {
		this (nome, marca, codice, categoria, prezzo, new File (immagine), quantita, null);
	}

	/**
	 * Crea un Prodotto vuoto, senza alcun tipo di informazione.
	 */
	public Prodotto() {
		this ("", "", "", "", 0, Prodotto.immagineDefault, 0, null);
	}
	
	/**
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
	 * @return il prezzo del prodotto.
	 */
	public float getPrezzo() {
		return prezzo;
	}
	
	/**
	 * Modifica il prezzo del prodotto se il valore è maggiore di 0.
	 * 
	 * @param prezzo Nuovo prezzo del prodotto.
	 */
	public void setPrezzo(float prezzo) {
		if (prezzo > 0) {
			this.prezzo = prezzo;			
		}
	}
	
	/**
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
	 * @return la quantità del prodotto.
	 */
	public int getQuantita() {
		return quantita;
	}
	
	/**
	 * Modifica la quantità del prodotto se si tratta di un valore positivo.
	 * 
	 * @param quantita Nuova quantità del prodotto.
	 */
	public void setQuantita(int quantita) {
		if (quantita >= 0) {
			this.quantita = quantita;
		}
	}
	
	/**
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
	 * @return "true" se sul Prodotto è attiva un'offerta, "fasle" altrimenti.
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
	public float prezzoCadaunoScontato () {
		return this.offerta.calcolaSconto(this.prezzo, 1);
	}
	
	/**
	 * Calcola il prezzo dell'intera quantità del prodotto.
	 * 
	 * @return il prezzo di tutte le unità del prodotto.
	 */
	public float prezzoTotale () {
		return this.prezzo * this.quantita;
	}
	
	/**
	 * Calcola il prezzo scontato dell'intera quantità del prodotto.
	 * 
	 * @return il prezzo scontato di tutte le unità del prodotto.
	 */
	public float prezzoTotaleScontato () {
		return this.offerta.calcolaSconto(this.prezzo, this.quantita);
	}

	@Override
	public String toString() {
		return "Prodotto [nome=" + nome + ", marca=" + marca + ", codice=" + codice + ", categoria=" + categoria
				+ ", prezzo=" + prezzo + ", immagine=" + immagine + ", quantita=" + quantita + ", offerta=" + offerta
				+ "]";
	}
}