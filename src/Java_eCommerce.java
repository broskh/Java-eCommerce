import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.FontUIResource;

import grafica.JUserFrame;
import negozio.Magazzino;

/**
 * La classe Java_eCommerce è la classe principale che contiene il
 * metodo main per l'esecuzione del programma.
 * ---------------->CONTINUARE<---------------------
 * 
 * @author Alessio Scheri
 * @version 1.0
 */
public class Java_eCommerce  {
	private static final int FONT_SIZE = 13; /**<Font size.*/

	private static final String FONT = "Inconsolata"; /**<Font.*/
//	private static final String THEME = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"; /**<Tema.*/
	
	public static void main(String[] args) {	    
	    //cambio tema grafico
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
//		            UIManager.put("nimbusBase", Color.GREEN); //alone
//		            UIManager.put("nimbusBlueGrey", new Color(0,	127,	255)); //componenti tipo bottoni
//		            UIManager.put("control", Color.WHITE); //sfondo
		            break;
		        }
		    }
		} catch (Exception e) {
		    // GUI alternativa
		}
	    
		Enumeration <Object> keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	    	Object key = keys.nextElement();
	    	Object value = UIManager.get (key);
			//cambio font di default
	    	if (value != null && value instanceof FontUIResource) {
	    		UIManager.put (key, new FontUIResource(
	    				Java_eCommerce.FONT, Font.PLAIN, Java_eCommerce.FONT_SIZE));
	    	}
	    }
	    
	    //programma vero e proprio
		Magazzino store = new Magazzino();
		JUserFrame jUserFrame = new JUserFrame(store);
		jUserFrame.setVisible(true);				
		
//		Magazzino magazzino = new Magazzino();
//		Prodotto prodotto1 = new Prodotto("nome1", "marca1", "codice1", "categoria1", 
//				(float)1.0, "media/img/immagine_non_disponibile.jpg", 10, new ScontoTrePerDue());
//		Prodotto prodotto2 = new Prodotto("nome2", "marca2", "codice2", "categoria2", 
//				(float)2.0, "media/img/immagine_non_disponibile.jpg", 20, new ScontoTrePerDue());
//		Prodotto prodotto3 = new Prodotto("nome3", "marca3", "codice3", "categoria3", 
//				(float)3.0, "media/img/immagine_non_disponibile.jpg", 30, new ScontoTrePerDue());
//		Prodotto prodotto4 = new Prodotto("nome4", "marca4", "codice4", "categoria4", 
//				(float)4.0, "media/img/immagine_non_disponibile.jpg", 40, new ScontoTrePerDue());
//		Prodotto prodotto5 = new Prodotto("nome5", "marca5", "codice5", "categoria5", 
//				(float)5.0, "media/img/immagine_non_disponibile.jpg", 50, new ScontoTrePerDue());
//		magazzino.aggiungiProdotto(prodotto1);
//		magazzino.aggiungiProdotto(prodotto2);
//		magazzino.aggiungiProdotto(prodotto3);
//		magazzino.aggiungiProdotto(prodotto4);
//		magazzino.aggiungiProdotto(prodotto5);
//		magazzino.salvaMagazzino("media/saves/magazzino.mag");
		
		
//		Magazzino magazzino = new Magazzino();
//		Prodotto prodotto1 = new Prodotto("nome1", "marca1", "codice1", "categoria1", 
//				(float)1.0, "media/img/immagine_non_disponibile.jpg", 10, new ScontoTrePerDue());
//		Prodotto prodotto2 = new Prodotto("nome2", "marca2", "codice2", "categoria2", 
//				(float)2.0, "media/img/immagine_non_disponibile.jpg", 20, new ScontoTrePerDue());
//		Prodotto prodotto3 = new Prodotto("nome3", "marca3", "codice3", "categoria3", 
//				(float)3.0, "media/img/immagine_non_disponibile.jpg", 30, new ScontoTrePerDue());
//		Prodotto prodotto4 = new Prodotto("nome4", "marca4", "codice4", "categoria4", 
//				(float)4.0, "media/img/immagine_non_disponibile.jpg", 40, new ScontoTrePerDue());
//		Prodotto prodotto5 = new Prodotto("nome5", "marca5", "codice5", "categoria5", 
//				(float)5.0, "media/img/immagine_non_disponibile.jpg", 50, new ScontoTrePerDue());
//		Prodotto prodotto6 = new Prodotto("nome6", "marca6", "codice6", "categoria6", 
//				(float)6.0, "media/img/immagine_non_disponibile.jpg", 60, new ScontoTrePerDue());
//		Prodotto prodotto7 = new Prodotto("nome7", "marca7", "codice7", "categoria7", 
//				(float)7.0, "media/img/immagine_non_disponibile.jpg", 70, new ScontoTrePerDue());
//		Prodotto prodotto8 = new Prodotto("nome8", "marca8", "codice8", "categoria8", 
//				(float)8.0, "media/img/immagine_non_disponibile.jpg", 80, new ScontoTrePerDue());
//		Prodotto prodotto9 = new Prodotto("nome9", "marca9", "codice9", "categoria9", 
//				(float)9.0, "media/img/immagine_non_disponibile.jpg", 90, new ScontoTrePerDue());
//		Prodotto prodotto10 = new Prodotto("nome10", "marca10", "codice10", "categoria10", 
//				(float)10.0, "media/img/immagine_non_disponibile.jpg", 100, new ScontoTrePerDue());
//		Prodotto prodotto11 = new Prodotto("nome11", "marca11", "codice11", "categoria11", 
//				(float)11.0, "media/img/immagine_non_disponibile.jpg", 110, new ScontoTrePerDue());
//		Prodotto prodotto12 = new Prodotto("nome12", "marca12", "codice12", "categoria12", 
//				(float)12.0, "media/img/immagine_non_disponibile.jpg", 120, new ScontoTrePerDue());
//		Prodotto prodotto13 = new Prodotto("nome13", "marca13", "codice13", "categoria13", 
//				(float)13.0, "media/img/immagine_non_disponibile.jpg", 130, new ScontoTrePerDue());
//		Prodotto prodotto14 = new Prodotto("nome14", "marca14", "codice14", "categoria14", 
//				(float)14.0, "media/img/immagine_non_disponibile.jpg", 140, new ScontoTrePerDue());
//		Prodotto prodotto15 = new Prodotto("nome15", "marca15", "codice15", "categoria15", 
//				(float)15.0, "media/img/immagine_non_disponibile.jpg", 150, new ScontoTrePerDue());
//		Prodotto prodotto16 = new Prodotto("nome16", "marca16", "codice16", "categoria16", 
//				(float)16.0, "media/img/immagine_non_disponibile.jpg", 160, new ScontoTrePerDue());
//		Prodotto prodotto17 = new Prodotto("nome17", "marca17", "codice17", "categoria17", 
//				(float)17.0, "media/img/immagine_non_disponibile.jpg", 170, new ScontoTrePerDue());
//		Prodotto prodotto18 = new Prodotto("nome18", "marca18", "codice18", "categoria18", 
//				(float)18.0, "media/img/immagine_non_disponibile.jpg", 180, new ScontoTrePerDue());
//		Prodotto prodotto19 = new Prodotto("nome19", "marca19", "codice19", "categoria19", 
//				(float)19.0, "media/img/immagine_non_disponibile.jpg", 190, new ScontoTrePerDue());
//		Prodotto prodotto20 = new Prodotto("nome20", "marca20", "codice20", "categoria20", 
//				(float)20.0, "media/img/immagine_non_disponibile.jpg", 200, new ScontoTrePerDue());
//		Prodotto prodotto21 = new Prodotto("nome21", "marca21", "codice21", "categoria21", 
//				(float)21.0, "media/img/immagine_non_disponibile.jpg", 210, new ScontoTrePerDue());
//		magazzino.aggiungiProdotto(prodotto1);
//		magazzino.aggiungiProdotto(prodotto2);
//		magazzino.aggiungiProdotto(prodotto3);
//		magazzino.aggiungiProdotto(prodotto4);
//		magazzino.aggiungiProdotto(prodotto5);
//		magazzino.aggiungiProdotto(prodotto6);
//		magazzino.aggiungiProdotto(prodotto7);
//		magazzino.aggiungiProdotto(prodotto8);
//		magazzino.aggiungiProdotto(prodotto9);
//		magazzino.aggiungiProdotto(prodotto10);
//		magazzino.aggiungiProdotto(prodotto11);
//		magazzino.aggiungiProdotto(prodotto12);
//		magazzino.aggiungiProdotto(prodotto13);
//		magazzino.aggiungiProdotto(prodotto14);
//		magazzino.aggiungiProdotto(prodotto15);
//		magazzino.aggiungiProdotto(prodotto16);
//		magazzino.aggiungiProdotto(prodotto17);
//		magazzino.aggiungiProdotto(prodotto18);
//		magazzino.aggiungiProdotto(prodotto19);
//		magazzino.aggiungiProdotto(prodotto20);
//		magazzino.aggiungiProdotto(prodotto21);
//		magazzino.salvaMagazzino("media/saves/save21.mag");
	}
}