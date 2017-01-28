package negozio;

import java.util.ArrayList;

/**
 * La classe carrello gestisce tutte le informazioni relative al carrello di un cliente.
 * 
 * @author Alessio Scheri
 * @version 1.0
 */
public class Carrello implements GestioneProdotti{
	private ArrayList <Prodotto> articoli; /**<Articoli desiderati dal cliente.*/
	private float totale; /**<Totale dei costi degli articoli inseriti nel carrello.*/
	private float totale_scontato; /**<Totale scontato dei costi degli articoli inseriti nel carrello.*/
	
	@Override
	public void aggiungiProdotto(Prodotto prodotto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuoviProdotto(Prodotto prodotto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void svuota() {
		// TODO Auto-generated method stub
		
	}

}
