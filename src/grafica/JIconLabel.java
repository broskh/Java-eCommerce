package grafica;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import negozio.Prodotto;

/**
 * Classe per visualizzare un'icona.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class JIconLabel extends JLabel {
	private static final long serialVersionUID = 7691599046739986927L;
	
	private File image;
	
	public JIconLabel (File image, int iconSize) {
		super ("", SwingConstants.CENTER);
		this.setIcon(image, iconSize);
	}
	
	public void setIcon (File image, int iconSize) {
		try {
			this.image = image;
			if (!this.image.exists()) {
				this.image = Prodotto.IMMAGINE_DEFAULT;
			}
			Icon icon = new ImageIcon (new ResizableIcon(
					this.image, iconSize, iconSize).getBufferedImage());
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getIconFile () {
		return this.image;
	}
}