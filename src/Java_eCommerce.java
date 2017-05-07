import grafica.JeCommerceFrame;
import utenza.Cliente;
import utenza.Utente;

/**
 * La classe Java_eCommerce è la classe principale che contiene il
 * metodo main per l'esecuzione del programma.
 * ---------------->CONTINUARE<---------------------
 * 
 * @author Alessio Scheri
 * @version 1.0
 */
public class Java_eCommerce {
	public static void main(String[] args) {
		Utente utente = new Cliente();
		
		JeCommerceFrame jeCommerceFrame = new JeCommerceFrame();
		jeCommerceFrame.setVisible(true);
		
		jeCommerceFrame.changeMenu(utente);
//		jeCommerceFrame.repaint();
//		jeCommerceFrame.revalidate();
//		jeCommerceFrame.doLayout();
	}
}