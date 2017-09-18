package grafica;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Classe per realizzare icone e ridimensionarle.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class ResizableIcon {
	
	private File image;
	private BufferedImage bufferedImage;
	
	public ResizableIcon (File image, int newWidth, int newHeight) throws IOException {
		this.setImage(image, newWidth, newHeight);
	}
	
	public ResizableIcon (File image) throws IOException {
		this.setImage(image);
	}
	
	public void setImage (File image) throws IOException {
		this.image = image;
		this.bufferedImage = ImageIO.read(this.image);
	}
	
	public void setImage (File image, int newWidth, int newHeight) throws IOException {
		this.image = image;
		this.resizeIcon(newWidth, newHeight);
	}
	
	public File getImage () {
		return this.image;
	}
	
	public BufferedImage getBufferedImage () {
		return this.bufferedImage;
	}

	public BufferedImage resizeIcon (int newWidth, int newHeight) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(this.image);
			// Calcolo le giuste dimensioni per l'icona
			int originalWidth = bufferedImage.getWidth();
		    int originalHeight = bufferedImage.getHeight();
		    int boundWidth = newWidth;
		    int boundHeight = newHeight;
		    int finalWidth = originalWidth;
		    int finalHeight = originalHeight;
		    if (originalWidth > boundWidth) {
		        finalWidth = boundWidth;
		        finalHeight = (finalWidth * originalHeight) / originalWidth;
		    }
		    if (finalHeight > boundHeight) {
		        finalHeight = boundHeight;
		        finalWidth = (finalHeight * originalWidth) / originalHeight;
		    }
			
		    // Ridimensiono l'immagine
		    this.bufferedImage = new BufferedImage(finalWidth, 
		    		finalHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = this.bufferedImage.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
		    		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(bufferedImage, 0, 0, finalWidth, finalHeight, null);
		    g2.dispose();
		    return this.bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}