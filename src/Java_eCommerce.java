import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import grafica.JUserFrame;
import negozio.Magazzino;
import utenza.Utente;

/**
 * La classe Java_eCommerce è la classe principale che contiene il metodo main per 
 * l'esecuzione del programma.
 * Il programma  consiste in un semplice e-Commerce che consente di accedere in due
 * diverse modalità utente e a seconda della scelta offre funzionalità differenti.
 * All'utente amministratore viene data la possibilità di gestire il magazzino e i 
 * prodotti che in esso sono contenuti.
 * L'utente cliente può invece sfruttare le apposite funzionalità per la visualizzazione 
 * e l'acquisto dei prodotti.
 * 
 * @author Alessio Scheri
 * @version 1.0
 * @see Magazzino
 * @see Utente
 */
public class Java_eCommerce  {
	/**
	 * Dimensione del font di default.
	 */
	private static final int DIMENSIONE_FONT = 13;

	/**
	 * Nome del font di default.
	 */
	private static final String NOME_FONT = "Encode Sans";
	 /**
	  * Cartella contenente il font di default.
	  */	
	private static final String DIRECTORY_FONT = 
			"media/font/";
	
	/**
	 * Il metodo main prima di tutto modifica il font di default e il tema grafico 
	 * della GUI. In seguito si occupa di aprire la schermata iniziale che consente la 
	 * scelta dell'utente. Sarà poi questa schermata a occuparsi di far procedere 
	 * correttamente l'esecuzione del software.
	 * 
	 * @param args Argomenti non utilizzati.
	 */
	public static void main(String[] args) {	    
	    //cambio font e tema grafico
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    File cartellaFont = new File(DIRECTORY_FONT);
		    if (cartellaFont.isDirectory()) {
			    for(final File fontFile : cartellaFont.listFiles()) {
			    	String nomeFontSenzaSpazi = NOME_FONT.replace(" ", "");
			    	if (fontFile.getName().startsWith(nomeFontSenzaSpazi)) {
			    		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
			    	}
			    }
		    }
			UIManager.setLookAndFeel(new NimbusLookAndFeel() {
				private static final long serialVersionUID = 8961215144947530197L;

				@Override
				public UIDefaults getDefaults() {
					UIDefaults ret = super.getDefaults();
					Font defaultFont = new Font(NOME_FONT, Font.PLAIN, DIMENSIONE_FONT);
					ret.put("defaultFont", defaultFont);
					return ret;
				}
		    });
		} catch (Exception e) {
			e.printStackTrace();
		    // GUI alternativa
		}
	    
	    //programma vero e proprio
		JUserFrame userFrame = new JUserFrame();
		userFrame.setVisible(true);
	}
}