package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import negozio.Magazzino;
import negozio.Prodotto;

import utenza.Cliente;

public class JClientContentPanel extends JPanel {
	private static final long serialVersionUID = -3383648558571677903L;

	private Magazzino magazzino;
	private Cliente cliente;
	
	private JClientControlPanel jClientControlPanel;
	private JPanel mainPanel;
	private JPanel showcasePanel;
	
	private static final int LARGHEZZA_MARGINE_DESTRO = 40;
	private static final int LARGHEZZA_MARGINE_SINISTRO = 40;	
	private static final int ALTEZZA_MARGINE_SUPERIORE = 40;	
	private static final int ALTEZZA_MARGINE_INFERIORE = 40;	
	private static final int MARGINE_ARTICOLI = 20;

	public JClientContentPanel(Magazzino magazzino, Cliente cliente) {
		this.magazzino = magazzino;
		this.cliente = cliente;
		
		this.jClientControlPanel = new JClientControlPanel(this.cliente, this.magazzino);
		this.jClientControlPanel.setBorder(new EtchedBorder ());

		this.showcasePanel = new JPanel();
		this.mainPanel = new JPanel(new BorderLayout());
		this.mainPanel.add(Box.createVerticalStrut(ALTEZZA_MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		this.mainPanel.add(Box.createHorizontalStrut(LARGHEZZA_MARGINE_SINISTRO), BorderLayout.WEST);
		this.mainPanel.add(this.showcasePanel, BorderLayout.CENTER);
		this.mainPanel.add(Box.createHorizontalStrut(LARGHEZZA_MARGINE_DESTRO), BorderLayout.EAST);
		this.mainPanel.add(Box.createVerticalStrut(ALTEZZA_MARGINE_INFERIORE), BorderLayout.PAGE_END);
		JScrollPane scrollPanel = new JScrollPane(this.mainPanel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setBorder(null);
		
		this.setLayout(new BorderLayout());
		this.add(this.jClientControlPanel, BorderLayout.PAGE_START);
		this.add(scrollPanel, BorderLayout.CENTER);
	}
	
	public void aggiornaArticoli () {
		int larghezzaBacheca = ((JFrame) SwingUtilities.getWindowAncestor(this)).getWidth() - LARGHEZZA_MARGINE_DESTRO - LARGHEZZA_MARGINE_SINISTRO;
		int nColonne = larghezzaBacheca / (JArticlePanel.LARGHEZZA_DEFAULT + MARGINE_ARTICOLI);
//		if (larghezzaBacheca % JArticlePanel.LARGHEZZA_DEFAULT != 0) {
//			nColonne++;
//		}
		int nRighe = this.magazzino.getArticoli().size() / nColonne;
		if (this.magazzino.getArticoli().size() % nColonne != 0) {
			nRighe++;
		}
//		int nRighe = altezzaContentPanel / JArticlePanel.ALTEZZA_DEFAULT;

		this.mainPanel.remove(this.showcasePanel);
		this.showcasePanel = new JPanel(new GridLayout(nRighe, nColonne, MARGINE_ARTICOLI, MARGINE_ARTICOLI));
		Iterator <Prodotto> itr = this.magazzino.getArticoli().iterator();
		while (itr.hasNext()) {
			Prodotto prodotto = itr.next();
			this.showcasePanel.add(new JArticlePanel(prodotto, this.cliente));
		}
		SwingUtilities.updateComponentTreeUI(this);
		this.mainPanel.add(this.showcasePanel, BorderLayout.CENTER);
	}
	
	public class JArticlePanel extends JPanel implements ActionListener{
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
		
		private Prodotto prodotto;
		private Cliente cliente;

		private static final int LARGHEZZA_TEXTFIELD_QUANTITA = 50;
		protected static final int ALTEZZA_DEFAULT = 210;
		protected static final int LARGHEZZA_DEFAULT = 220;
		private static final int ALTEZZA_TEXTFIELD_QUANTITA = 20;
		private static final int DIMENSIONE_ICONA = 120;
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
		
		private static final String ADD_BUTTON_TEXT = "+";
		private static final String ADD_IMAGE_PATH = "media/img/add.png";

		public JArticlePanel(Prodotto prodotto, Cliente cliente) {
			this.prodotto = prodotto;
			this.cliente = cliente;
			this.imageLabel = new JLabel("", SwingConstants.CENTER);
			this.imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			ImageIcon icon = new ImageIcon (this.ridimensionaImmagine (this.prodotto.getImmagine()));
			if (icon != null) {
				this.imageLabel.setIcon(icon);
			}
			JPanel imagePanel = new JPanel ();
			imagePanel.setLayout(new BoxLayout (imagePanel, BoxLayout.Y_AXIS));
			imagePanel.add(Box.createVerticalStrut(MARGINE_GENERALE));
			imagePanel.add(this.imageLabel);
			
			this.codeLabel = new JLabel(TESTO_CODICE + this.prodotto.getCodice());
			this.nameLabel = new JLabel(TESTO_NOME + this.prodotto.getNome());
			this.brandLabel = new JLabel(TESTO_MARCA + this.prodotto.getMarca());
			this.categoryLabel = new JLabel(TESTO_CATEGORIA + this.prodotto.getCategoria());
			this.priceLabel = new JLabel(TESTO_PREZZO + this.prodotto.getPrezzo() + SIMBOLO_EURO);
			this.availabilityLabel = new JLabel(TESTO_DISPONIBILITA + this.prodotto.getQuantita());
			String offerta = this.prodotto.getOfferta().toString();
			if (offerta == null) {
				offerta = "Nessuna";
			}
			this.offerLabel = new JLabel(TESTO_OFFERTA + offerta);
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
			this.addToCartButton.addActionListener(this);
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
			this.setBorder (new RoundedBorder(Color.GRAY,2,16,0));
			this.add (imagePanel, BorderLayout.PAGE_START);
			this.add (infoPanel, BorderLayout.CENTER);
			this.add (Box.createHorizontalStrut(MARGINE_GENERALE), BorderLayout.WEST);
			this.add (bottomPanel, BorderLayout.PAGE_END);
		}
		
		private Image ridimensionaImmagine (File immagine) {
			BufferedImage bimg;
			try {
				bimg = ImageIO.read(immagine);
				// Calcolo le giuste dimensioni per l'icona
				int original_width = bimg.getWidth();
			    int original_height = bimg.getHeight();
			    int bound_width = DIMENSIONE_ICONA;
			    int bound_height = DIMENSIONE_ICONA;
			    int new_width = original_width;
			    int new_height = original_height;
			    if (original_width > bound_width) {
			        new_width = bound_width;
			        new_height = (new_width * original_height) / original_width;
			    }
			    if (new_height > bound_height) {
			        new_height = bound_height;
			        new_width = (new_height * original_width) / original_height;
			    }
				
			    // Ridimensiono l'immagine
			    BufferedImage resizedImg = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_ARGB);
			    Graphics2D g2 = resizedImg.createGraphics();
			    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			    g2.drawImage(bimg, 0, 0, new_width, new_height, null);
			    g2.dispose();

			    return resizedImg;
			} catch (IOException e) {
				e.printStackTrace();			
				return null;
			}		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(this.addToCartButton)) {
				try {
					int quantitaDaAggiungere = Integer.parseInt (this.amountTextField.getText());
					Prodotto giaInCarrello = this.cliente.getCarrello().getProdotto(this.prodotto.getCodice());
					if (giaInCarrello != null && giaInCarrello.getQuantita() + quantitaDaAggiungere > this.prodotto.getQuantita()) {
						;
					}
					else {
						Prodotto nuovoProdotto = this.prodotto.clone ();
						nuovoProdotto.setQuantita (quantitaDaAggiungere);
						this.cliente.getCarrello().aggiungiProdotto(nuovoProdotto);
					}
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException er) {
					er.printStackTrace();
				}
			}
		}
	}
}