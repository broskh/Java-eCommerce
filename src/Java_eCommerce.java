import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

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

	private static final String FONT_PATH = "media/font/"; /**<Cartella contenente i font.*/
	private static final String FONT_NAME = "Encode Sans"; /**<Nome del font.*/
	
	public static void main(String[] args) {	    
	    //cambio tema grafico
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    File fontFolder = new File(FONT_PATH);
		    if (fontFolder.isDirectory()) {
			    for(final File fontFile : fontFolder.listFiles()) {
			    	String fontNameWithoutSpaces = FONT_NAME.replace(" ", "");
			    	if (fontFile.getName().startsWith(fontNameWithoutSpaces)) {
			    		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
			    	}
			    }
		    }
			UIManager.setLookAndFeel(new NimbusLookAndFeel() {
				private static final long serialVersionUID = 8961215144947530197L;

				@Override
				public UIDefaults getDefaults() {
					UIDefaults ret = super.getDefaults();
					Font defaultFont = new Font(FONT_NAME, Font.PLAIN, FONT_SIZE);
					ret.put("defaultFont", defaultFont);
					return ret;
				}
		    });
		} catch (Exception e) {
			e.printStackTrace();
		    // GUI alternativa
		}
	    
	    //programma vero e proprio
		Magazzino store = new Magazzino();
		JUserFrame jUserFrame = new JUserFrame(store);
		jUserFrame.setVisible(true);
	}
}