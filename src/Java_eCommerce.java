import grafica.JUserFrame;
import grafica.JeCommerceFrame;
import grafica.UserAccessListener;

import negozio.Magazzino;

import utenza.Utente;

/**
 * La classe Java_eCommerce è la classe principale che contiene il
 * metodo main per l'esecuzione del programma.
 * ---------------->CONTINUARE<---------------------
 * 
 * @author Alessio Scheri
 * @version 1.0
 */
public class Java_eCommerce  {
	private static final String FILE_MAGAZZINO = "media/saves/save21.mag";
	
	public static void main(String[] args) {
		
		Utente utente = null;
		Magazzino magazzino = new Magazzino();
		magazzino.caricaMagazzino("media/saves/save21.mag");   /* scheri */
		JUserFrame jUserFrame = new JUserFrame();
		JeCommerceFrame jeCommerceFrame = null;
		UserAccessListener userAccessListener = new UserAccessListener(jUserFrame, jeCommerceFrame, utente, magazzino, FILE_MAGAZZINO);
		
		
		
		
		
		
		
		
		jUserFrame.setVisible(true);
		jUserFrame.setAccessListener(userAccessListener);
	}
}





































