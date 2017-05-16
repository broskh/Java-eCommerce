package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class JArticlePanel extends JPanel {
	private static final long serialVersionUID = -2838106312491733874L;
	
	private JLabel imageLabel;
	private JLabel codeLabel;
	private JLabel nameLabel;
	private JLabel brandLabel;
	private JLabel categoryLabel;
	private JLabel priceLabel;
	private JLabel availabilityLabel;
	private JLabel offerLabel;
	private JTextField amountTextField;
	private JButton addToCartButton;

	private static final String ADD_BUTTON_TEXT = "+";	
	private static final String ADD_IMAGE_PATH = "media/img/add.png";

	public JArticlePanel() {
		this.imageLabel = new JLabel("", SwingConstants.CENTER);
		this.imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		try {
			ImageIcon provaIcon = new ImageIcon(this.getScaledImage(ImageIO.read(new File ("media/img/immagine_non_disponibile.jpg")), 120, 120));
			this.imageLabel.setIcon(provaIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel imagePanel = new JPanel ();
		imagePanel.setLayout(new BoxLayout (imagePanel, BoxLayout.Y_AXIS));
		imagePanel.add(Box.createVerticalStrut(15));
		imagePanel.add(this.imageLabel);
		
		this.codeLabel = new JLabel("Codice: 1234567890");
		this.nameLabel = new JLabel("Nome: Sconosciuto");
		this.brandLabel = new JLabel("Marca: Sconosciuto");
		this.categoryLabel = new JLabel("Categoria: Sconosciuta");
		this.priceLabel = new JLabel("Prezzo: 0,0 €");
		this.availabilityLabel = new JLabel("Disponibilità: 000");
		this.offerLabel = new JLabel("Offerta: Non in offerta");
		JPanel infoPanel = new JPanel ();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(this.codeLabel);
		infoPanel.add(this.nameLabel);
		infoPanel.add(this.brandLabel);
		infoPanel.add(this.categoryLabel);
		infoPanel.add(this.priceLabel);
		infoPanel.add(this.availabilityLabel);
		infoPanel.add(this.offerLabel);
		
		this.amountTextField = new JTextField ("1");
		this.amountTextField.setPreferredSize(new Dimension (50, 20));
		this.addToCartButton = new JButton ();
		try {
		    Image img = ImageIO.read(new File (ADD_IMAGE_PATH));
		    this.addToCartButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.addToCartButton.setText(ADD_BUTTON_TEXT);
		}
		this.addToCartButton.setPreferredSize(new Dimension (30, 30));
		JPanel actionPanel = new JPanel ();
		actionPanel.add(this.amountTextField);
		actionPanel.add(Box.createRigidArea(new Dimension(50, 30)));
		actionPanel.add(this.addToCartButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
		bottomPanel.add(actionPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createHorizontalStrut(15), BorderLayout.EAST);
		bottomPanel.add(Box.createVerticalStrut(15), BorderLayout.PAGE_END);

		this.setLayout (new BorderLayout(0, 15));
		this.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		this.add(imagePanel, BorderLayout.PAGE_START);
		this.add(infoPanel, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		this.add(bottomPanel, BorderLayout.PAGE_END);
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
