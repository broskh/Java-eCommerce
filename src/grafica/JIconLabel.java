package grafica;

import java.awt.Color;

import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import negozio.Prodotto;

public class JIconLabel extends JLabel {
	private static final long serialVersionUID = 7691599046739986927L;
	
	public static int BORDER_THICKNESS = 1;
	
	public JIconLabel (File image, int iconSize) {
		super ("", SwingConstants.CENTER);
		this.setIcon(image, iconSize);
		this.setBorder(BorderFactory.createMatteBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, Color.DARK_GRAY));
	}
	
	public void setIcon (File image, int iconSize) {
		ImageIcon icon;
		try {
			if (!image.exists()) {
				image = Prodotto.IMMAGINE_DEFAULT;
			}
			icon = new ImageIcon (new ResizableIcon(
					image, iconSize, iconSize).getBufferedImage());
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}