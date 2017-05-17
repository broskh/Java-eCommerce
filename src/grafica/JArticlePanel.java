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

	private static final int LARGHEZZA_TEXTFIELD_QUANTITA = 50;
	private static final int ALTEZZA_TEXTFIELD_QUANTITA = 20;
	private static final int DIMENSIONE_BOTTONE_AGGIUNTA_CARRELLO = 30;
	private static final int MARGINE_GENERALE = 15;
	private static final int ALTEZZA_AREA_INTERAZIONE = 30;
	private static final int SPAZIO_INTERNO_AREA_INTERAZIONE = 50;
	
	private static final int QUANTITA_DEFAULT = 1;

	private static final String TESTO_CODICE = "Codice: ";
	private static final String TESTO_NOME = "Nome: ";
	private static final String TESTO_MARCA = "Marca: ";
	private static final String TESTO_CATEGORIA = "Categoria: ";
	private static final String TESTO_PREZZO = "Prezzo: ";
	private static final String SIMBOLO_EURO = " €";
	private static final String TESTO_DISPONIBILITA = "Disponibilità: ";
	private static final String TESTO_OFFERTA = "Offerta: ";
	private static final String TESTO_VALORE_SCONOSCIUTO = "Sconosciuto";
	
	private static final String ADD_BUTTON_TEXT = "+";
	private static final String ADD_IMAGE_PATH = "media/img/add.png";

	public JArticlePanel() {
		this.imageLabel = new JLabel("", SwingConstants.CENTER);
		this.imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		try {
			//cambiare solo altezza e lascia rapporto per larghezza
			ImageIcon provaIcon = new ImageIcon(this.getScaledImage(ImageIO.read(new File ("media/img/immagine_non_disponibile.jpg")), 120, 120));
			this.imageLabel.setIcon(provaIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel imagePanel = new JPanel ();
		imagePanel.setLayout(new BoxLayout (imagePanel, BoxLayout.Y_AXIS));
		imagePanel.add(Box.createVerticalStrut(MARGINE_GENERALE));
		imagePanel.add(this.imageLabel);
		
		this.codeLabel = new JLabel(TESTO_CODICE + TESTO_VALORE_SCONOSCIUTO);
		this.nameLabel = new JLabel(TESTO_NOME + TESTO_VALORE_SCONOSCIUTO);
		this.brandLabel = new JLabel(TESTO_MARCA + TESTO_VALORE_SCONOSCIUTO);
		this.categoryLabel = new JLabel(TESTO_CATEGORIA + TESTO_VALORE_SCONOSCIUTO);
		this.priceLabel = new JLabel(TESTO_PREZZO + TESTO_VALORE_SCONOSCIUTO + SIMBOLO_EURO);
		this.availabilityLabel = new JLabel(TESTO_DISPONIBILITA + TESTO_VALORE_SCONOSCIUTO);
		this.offerLabel = new JLabel(TESTO_OFFERTA + TESTO_VALORE_SCONOSCIUTO);
		JPanel infoPanel = new JPanel ();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(this.codeLabel);
		infoPanel.add(this.nameLabel);
		infoPanel.add(this.brandLabel);
		infoPanel.add(this.categoryLabel);
		infoPanel.add(this.priceLabel);
		infoPanel.add(this.availabilityLabel);
		infoPanel.add(this.offerLabel);
		
		this.amountTextField = new JTextField (String.valueOf(QUANTITA_DEFAULT));
		this.amountTextField.setPreferredSize(new Dimension (LARGHEZZA_TEXTFIELD_QUANTITA, ALTEZZA_TEXTFIELD_QUANTITA));
		this.addToCartButton = new JButton ();
		try {
		    Image img = ImageIO.read(new File (ADD_IMAGE_PATH));
		    this.addToCartButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.addToCartButton.setText(ADD_BUTTON_TEXT);
		}
		this.addToCartButton.setPreferredSize(new Dimension (DIMENSIONE_BOTTONE_AGGIUNTA_CARRELLO, DIMENSIONE_BOTTONE_AGGIUNTA_CARRELLO));
		JPanel interactionPanel = new JPanel ();
		interactionPanel.add(this.amountTextField);
		interactionPanel.add(Box.createRigidArea(new Dimension(SPAZIO_INTERNO_AREA_INTERAZIONE, ALTEZZA_AREA_INTERAZIONE)));
		interactionPanel.add(this.addToCartButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createHorizontalStrut(MARGINE_GENERALE), BorderLayout.WEST);
		bottomPanel.add(interactionPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createHorizontalStrut(MARGINE_GENERALE), BorderLayout.EAST);
		bottomPanel.add(Box.createVerticalStrut(MARGINE_GENERALE), BorderLayout.PAGE_END);

		this.setLayout (new BorderLayout(0, MARGINE_GENERALE));
		this.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		this.add(imagePanel, BorderLayout.PAGE_START);
		this.add(infoPanel, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(MARGINE_GENERALE), BorderLayout.WEST);
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
