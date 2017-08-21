package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.EtchedBorder;
import javax.swing.text.PlainDocument;

import negozio.Carrello;
import negozio.Magazzino;
import negozio.Prodotto;

import utenza.Cliente;

public class JClientContentPanel extends JPanel {
	private static final long serialVersionUID = -3383648558571677903L;

	private Magazzino magazzino;
	private Cliente cliente;
	private ArrayList <Prodotto> articoliVisualizzati;
	
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
		this.articoliVisualizzati = this.magazzino.getArticoli();
		
		this.jClientControlPanel = new JClientControlPanel(this);
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
	
	public void setArticoliVisualizzati (ArrayList <Prodotto> articoliVisualizzati) {
		this.articoliVisualizzati = articoliVisualizzati;
		this.aggiornaArticoli();
	}
	
	public Magazzino getMagazzino () {
		return this.magazzino;
	}
	
	public Cliente getCliente () {
		return this.cliente;
	}
	
	public void aggiornaArticoli () {
		int larghezzaBacheca = ((JeCommerceFrame) SwingUtilities.getWindowAncestor(this)).getWidth() - LARGHEZZA_MARGINE_DESTRO - LARGHEZZA_MARGINE_SINISTRO;
		int altezzaBacheca = this.getHeight() - this.jClientControlPanel.getHeight() - ALTEZZA_MARGINE_SUPERIORE - ALTEZZA_MARGINE_INFERIORE - 1;
		int nColonne = larghezzaBacheca / (JArticlePanel.LARGHEZZA_DEFAULT + MARGINE_ARTICOLI);
		int nRighe = this.articoliVisualizzati.size() / nColonne;
		if (this.articoliVisualizzati.size() % nColonne != 0) {
			nRighe++;
		}

		this.mainPanel.remove(this.showcasePanel);
		this.showcasePanel = new JPanel(new GridLayout(nRighe, nColonne, MARGINE_ARTICOLI, MARGINE_ARTICOLI));
		Iterator <Prodotto> itr = this.articoliVisualizzati.iterator();
		while (itr.hasNext()) {
			Prodotto prodotto = itr.next();
			this.showcasePanel.add(new JArticlePanel(this.magazzino, prodotto, this.cliente));
		}
		//aggiungo box vuoti per far si che se ci sono pochi prodotti , abbiano comunque la solita dimensione
		if (nColonne > this.articoliVisualizzati.size()) {
			int nBoxVuoti = nColonne - this.articoliVisualizzati.size();
			for (int i = 0; i < nBoxVuoti; i++) {
				this.showcasePanel.add(Box.createRigidArea(new Dimension(JArticlePanel.LARGHEZZA_DEFAULT, JArticlePanel.ALTEZZA_DEFAULT)), BorderLayout.CENTER);
			}
		}
		if ((nRighe * JArticlePanel.ALTEZZA_DEFAULT) < altezzaBacheca) {
			int altezzaVuota = altezzaBacheca - (nRighe * JArticlePanel.height ());
			this.mainPanel.add(Box.createVerticalStrut(altezzaVuota + ALTEZZA_MARGINE_INFERIORE), BorderLayout.PAGE_END);
		}
		//aggiorno
		SwingUtilities.updateComponentTreeUI(this);
		this.mainPanel.add(this.showcasePanel, BorderLayout.CENTER);
	}
}

class JArticlePanel extends JPanel {
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
	private static final int SPAZIO_INFORMAZIONI = 6;
	private static final int DIMENSIONE_BORDO = 3;
	
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

	public JArticlePanel(Magazzino magazzino, Prodotto prodotto, Cliente cliente) {
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
		String offerta;
		if (this.prodotto.getOfferta() == null) {
			offerta = "Nessuna";
		}
		else {
			offerta = this.prodotto.getOfferta().toString();
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
		PlainDocument doc = (PlainDocument) this.amountTextField.getDocument();
		doc.setDocumentFilter(new AmountDocumentFilter(this.prodotto.getQuantita()));	
		this.addToCartButton = new JButton ();
		try {
		    Image img = ImageIO.read(new File (ADD_IMAGE_PATH));
		    this.addToCartButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.addToCartButton.setText(ADD_BUTTON_TEXT);
		}
		this.addToCartButton.setPreferredSize(new Dimension (DIMENSIONE_BOTTONE_AGGIUNTA_CARRELLO, DIMENSIONE_BOTTONE_AGGIUNTA_CARRELLO));
		this.addToCartButton.addActionListener(new AddArticleToCartListener(magazzino, this.cliente.getCarrello(), new StringBuilder(this.prodotto.getCodice()), this.amountTextField));
		this.addToCartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		JPanel interactionPanel = new JPanel ();
		interactionPanel.add(this.amountTextField);
		interactionPanel.add(Box.createRigidArea(new Dimension(SPAZIO_INTERNO_AREA_INTERAZIONE, ALTEZZA_AREA_INTERAZIONE)));
		interactionPanel.add(this.addToCartButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createHorizontalStrut(MARGINE_GENERALE), BorderLayout.WEST);
		bottomPanel.add(interactionPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createHorizontalStrut(MARGINE_GENERALE), BorderLayout.EAST);
		bottomPanel.add(Box.createVerticalStrut(MARGINE_GENERALE), BorderLayout.PAGE_END);

		this.setTransferHandler(new ValueExportTransferHandler(this.prodotto, this.amountTextField));
        this.addMouseMotionListener(new MouseAdapter() {
        	
            @Override
            public void mouseMoved(MouseEvent e) {
            	JArticlePanel.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        	
            @Override
            public void mouseDragged(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();
                TransferHandler handle = panel.getTransferHandler();
                handle.exportAsDrag(panel, e, TransferHandler.COPY);
            }
        });
		this.setLayout (new BorderLayout(0, MARGINE_GENERALE));
		this.setBorder (new RoundedBorder(Color.GRAY, DIMENSIONE_BORDO, MARGINE_GENERALE, 0));
		this.add (imagePanel, BorderLayout.PAGE_START);
		this.add (infoPanel, BorderLayout.CENTER);
		this.add (Box.createHorizontalStrut(MARGINE_GENERALE), BorderLayout.WEST);
		this.add (bottomPanel, BorderLayout.PAGE_END);
	}
	
	public static int height () {
		return  DIMENSIONE_BORDO + MARGINE_GENERALE + 
				DIMENSIONE_ICONA + MARGINE_GENERALE + 
				(new JLabel().getFont().getSize() + 
				SPAZIO_INFORMAZIONI) * 7 + MARGINE_GENERALE + 
				ALTEZZA_AREA_INTERAZIONE + MARGINE_GENERALE * 2 + 
				DIMENSIONE_BORDO;
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

    public static class ValueExportTransferHandler extends TransferHandler {
        private static final long serialVersionUID = -3689725432297463459L;

        private Prodotto prodottoMagazzino;
        private JTextField amountTextfield;
        
        public static final DataFlavor SUPPORTED_DATE_FLAVOR = Prodotto.getDataFlavor();

        public ValueExportTransferHandler(Prodotto value, JTextField amountTextfield) {
        	this.prodottoMagazzino = value;
            this.amountTextfield = amountTextfield;
        }

        public Prodotto getValue() {
        	try {
				Prodotto prodotto = this.prodottoMagazzino.clone();
				prodotto.setQuantita(Integer.parseInt(this.amountTextfield.getText()));
	            return prodotto;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
        	return null;
        }
        
        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = this.getValue();
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            super.exportDone(source, data, action);
            // Decide what to do after the drop has been accepted
        }

    }
}

class JClientControlPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -8385562955958262505L;
	
	private JComboBox <String> filterTypeComboBox;
	private JFilterPanel filterPanel;
	private JButton filterButton;
	private JButton cartButton;
	private StringBuilder filterTypeString;
	
	private JClientContentPanel mainPanel;

	protected static final int ALTEZZA = 80;

	private static final int MARGINE_LABEL = 20;
	private static final int MARGINE_DESTRO = 20;
	private static final int MARGINE_SINISTRO = 20;
	private static final int MARGINE_ORIZZONTALE_LAYOUT = 80;

	private static final String FILTER_TYPE_LABEL = "Filtra per:";
	private static final String FILTER_STRING_LABEL = "Criterio di ricerca:";
	private static final String FILTER_BUTTON_TEXT = "Filtra";
	private static final String CART_BUTTON_TEXT = "Carrello";	
	protected static final String[] FILTER_TYPE_STRINGS = {
			Magazzino.STRINGA_FILTRO_NOME,
			Magazzino.STRINGA_FILTRO_MARCA,
			Magazzino.STRINGA_FILTRO_CODICE,
			Magazzino.STRINGA_FILTRO_CATEGORIA,
			Magazzino.STRINGA_FILTRO_PREZZO,
			Magazzino.STRINGA_FILTRO_QUANTITA
		};
	private static final String CART_IMAGE_PATH = "media/img/cart.png";

	public JClientControlPanel (JClientContentPanel mainPanel) {
		this.mainPanel = mainPanel;
		JPanel leftPanel = new JPanel ();
		
		JPanel filterTypePanel = new JPanel ();
		JLabel filterTypeLabel = new JLabel (FILTER_TYPE_LABEL);
		filterTypePanel.setLayout (new BorderLayout ());
		filterTypePanel.add (filterTypeLabel, BorderLayout.PAGE_START);
		filterTypePanel.add (Box.createVerticalStrut (MARGINE_LABEL));
		this.filterTypeComboBox = new JComboBox <String> (FILTER_TYPE_STRINGS);
		this.filterTypeComboBox.addActionListener(this);
		filterTypePanel.add (this.filterTypeComboBox, BorderLayout.PAGE_END);
		
		JPanel filterStringPanel = new JPanel ();
		JLabel filterStringLabel = new JLabel (FILTER_STRING_LABEL);
		filterStringPanel.setLayout(new BorderLayout ());
		filterStringPanel.add (filterStringLabel, BorderLayout.PAGE_START);
		filterStringPanel.add (Box.createVerticalStrut (MARGINE_LABEL));
		this.filterPanel = new JFilterPanel(this.mainPanel.getMagazzino().MaxQuantita(), this.mainPanel.getMagazzino().MaxPrezzo());
		filterStringPanel.add (this.filterPanel, BorderLayout.PAGE_END);
		
		this.filterTypeString = new StringBuilder(this.filterTypeComboBox.getSelectedItem().toString());
		this.filterButton = new JButton (FILTER_BUTTON_TEXT);
		this.filterButton.addActionListener(new FilterListener (this.mainPanel, this.filterTypeString, this.filterPanel));
	
		leftPanel.add (Box.createRigidArea(new Dimension(MARGINE_SINISTRO, ALTEZZA)));
		leftPanel.add (filterTypePanel);
		leftPanel.add (Box.createHorizontalStrut(MARGINE_ORIZZONTALE_LAYOUT));
		leftPanel.add (filterStringPanel);
		leftPanel.add (Box.createHorizontalStrut(MARGINE_ORIZZONTALE_LAYOUT));
		leftPanel.add (this.filterButton);
		
		JPanel rightPanel = new JPanel ();
		
		this.cartButton = new JButton ();
		try {
		    Image img = ImageIO.read(new File (CART_IMAGE_PATH));
		    this.cartButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.cartButton.setText(CART_BUTTON_TEXT);
		}
		this.cartButton.addActionListener(new OpenCartListener((JFrame) SwingUtilities.getWindowAncestor(this), this.mainPanel.getCliente ().getCarrello (), this.mainPanel.getMagazzino()));
		this.cartButton.setTransferHandler(new ValueImportTransferHandler(this.mainPanel.getCliente().getCarrello()));
		
		rightPanel.add (this.cartButton);
		rightPanel.add (Box.createRigidArea(new Dimension(MARGINE_DESTRO, ALTEZZA)));
		
		this.setLayout(new BorderLayout());
		this.add (leftPanel, BorderLayout.WEST);
		this.add (rightPanel, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.filterTypeComboBox)) {
			this.filterTypeString.replace(0, this.filterTypeString.length(), this.filterTypeComboBox.getSelectedItem().toString());
			this.filterPanel.enableCorrectFilter (this.filterTypeString.toString());
		}
	}
	
	public static class ValueImportTransferHandler extends TransferHandler {
		private static final long serialVersionUID = 7407303027786470664L;

		private Carrello carrello;
		
        public static final DataFlavor SUPPORTED_DATE_FLAVOR = Prodotto.getDataFlavor();

        public ValueImportTransferHandler(Carrello carrello) {
        	this.carrello = carrello;
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof Prodotto) {
                        Component component = support.getComponent();
                        if (component instanceof JButton) {
                            this.carrello.aggiungiProdotto((Prodotto)value);
                            accept = true;
                        }
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }
    }
}