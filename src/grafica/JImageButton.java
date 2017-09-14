package grafica;

import java.awt.Dimension;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JImageButton extends JButton {
	private static final long serialVersionUID = -5170045716389532892L;
	
	private static final int DEFAULT_BUTTON_PADDING = 10;
	
	public JImageButton (File image, int buttonSize, int buttonPadding, String buttonText) {
		this.setPreferredSize(new Dimension(buttonSize, buttonSize));
		this.setToolTipText(buttonText);
		
		int iconSize = buttonSize - buttonPadding;
		try {
			ResizableIcon resizableIcon = new ResizableIcon(image);
		    Icon icon = new ImageIcon(resizableIcon.resizeIcon(iconSize, iconSize));
		    this.setIcon(icon);
		} catch (Exception e) {
			this.setText(buttonText);
			e.printStackTrace();
		}
	}
	
	public JImageButton (File image, int buttonSize, String buttonText) {
		this (image, buttonSize, DEFAULT_BUTTON_PADDING, buttonText);
	}
}