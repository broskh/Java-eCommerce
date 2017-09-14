package grafica;

import java.awt.Color;

import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import negozio.Prodotto;

public class JIconLabel extends JLabel {
	private static final long serialVersionUID = 7691599046739986927L;
	
	private File image;
	
	public static int BORDER_THICKNESS = 1;
	
	public JIconLabel (File image, int iconSize) {
		super ("", SwingConstants.CENTER);
		this.setIcon(image, iconSize);
		this.setBorder(BorderFactory.createMatteBorder(BORDER_THICKNESS, 
				BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, Color.DARK_GRAY));
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