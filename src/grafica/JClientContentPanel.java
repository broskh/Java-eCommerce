package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;

import java.util.ArrayList;

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

public class JClientContentPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -3383648558571677903L;

	private Magazzino store;
	private Cliente client;
	private ArrayList <Prodotto> viewedArticles;
	private int nPage;
	private int maxPage;
	
	private JClientControlPanel jClientControlPanel;
	private JPanel mainPanel;
	private JPanel showcasePanel;
	private JButton backButton;
	private JButton forwardButton;
	
	private static final int RIGHT_MARGIN = 40;
	private static final int LEFT_MARGIN = 40;	
	private static final int TOP_MARGIN = 15;	
	private static final int BOTTOM_MARGIN = 15;	
	private static final int BUTTONS_TOP_MARGIN = 10;	
	private static final int ARTICLES_MARGIN = 10;
	private static final int BUTTONS_SPACE = 450;
	
	private static final String BACK_IMAGE_PATH = "media/img/back_icon.png";
	private static final String FORWARD_IMAGE_PATH = "media/img/forward_icon.png";

	
	private static final String BACK_BUTTON_TEXT = "<";
	private static final String FORWARD_BUTTON_TEXT = ">";

	public JClientContentPanel(JFrame mainFrame, Magazzino store, Cliente client) {
		this.store = store;
		this.client = client;
		this.viewedArticles = this.store.getArticoli();
		this.nPage = 0;
		this.maxPage = 0;
		
		this.jClientControlPanel = new JClientControlPanel(mainFrame, this);
		this.jClientControlPanel.setBorder(new EtchedBorder ());
		
		JPanel buttonsPanelLV2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.backButton = new JButton();
		this.backButton.addActionListener(this);
		try {
		    Image img = ImageIO.read(new File (BACK_IMAGE_PATH));
		    this.backButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.backButton.setText(BACK_BUTTON_TEXT);
		}
		this.forwardButton = new JButton();
		this.forwardButton.addActionListener(this);
		try {
		    Image img = ImageIO.read(new File (FORWARD_IMAGE_PATH));
		    this.forwardButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.forwardButton.setText(FORWARD_BUTTON_TEXT);
		}
		buttonsPanelLV2.add(this.backButton);
		buttonsPanelLV2.add(Box.createHorizontalStrut(BUTTONS_SPACE));
		buttonsPanelLV2.add(this.forwardButton);
		JPanel buttonsPanelLV1 = new JPanel(new BorderLayout());
		buttonsPanelLV1.add(Box.createVerticalStrut(BUTTONS_TOP_MARGIN), 
				BorderLayout.PAGE_START);
		buttonsPanelLV1.add(buttonsPanelLV2, BorderLayout.CENTER);
		buttonsPanelLV1.add(Box.createVerticalStrut(BOTTOM_MARGIN), BorderLayout.PAGE_END);

		this.showcasePanel = new JPanel();
		this.mainPanel = new JPanel(new BorderLayout());
		this.mainPanel.add(Box.createVerticalStrut(TOP_MARGIN), BorderLayout.PAGE_START);
		this.mainPanel.add(Box.createHorizontalStrut(LEFT_MARGIN), BorderLayout.WEST);
		this.mainPanel.add(this.showcasePanel, BorderLayout.CENTER);
		this.mainPanel.add(Box.createHorizontalStrut(RIGHT_MARGIN), BorderLayout.EAST);
		this.mainPanel.add(buttonsPanelLV1, BorderLayout.PAGE_END);
		
		this.setLayout(new BorderLayout());
		this.add(this.jClientControlPanel, BorderLayout.PAGE_START);
		this.add(this.mainPanel, BorderLayout.CENTER);
	}
	
	public void setViewedArticles (ArrayList <Prodotto> viewedArticles) {
		this.viewedArticles = viewedArticles;
		this.updateArticles();
	}
	
	public ArrayList <Prodotto> getViewedArticles () {
		return this.viewedArticles;
	}
	
	public void setStore (Magazzino store) {
		this.store = store;
	}
	
	public Magazzino getStore () {
		return this.store;
	}
	
	public void setClient (Cliente client) {
		this.client = client;
	}
	
	public Cliente getClient () {
		return this.client;
	}
	
	public void updateArticles () {
		int showcaseWidth = ((JeCommerceFrame) SwingUtilities.getWindowAncestor(this)).getWidth() - 
				RIGHT_MARGIN - LEFT_MARGIN;
		int showcaseHeight = this.getHeight() - this.jClientControlPanel.getHeight() - 
				TOP_MARGIN - BOTTOM_MARGIN - BUTTONS_TOP_MARGIN - this.backButton.getHeight() - 1;
		int nColumns = showcaseWidth / (JArticlePanel.WIDTH + ARTICLES_MARGIN);
		int nRows = showcaseHeight / (JArticlePanel.height() + ARTICLES_MARGIN);
		int nVisibleArticles = nRows * nColumns;
		this.maxPage = this.viewedArticles.size() / nVisibleArticles;
		if (this.viewedArticles.size() % nVisibleArticles == 0) {
			this.maxPage--;
		}

		this.mainPanel.remove(this.showcasePanel);
		this.showcasePanel = new JPanel(new GridLayout(nRows, nColumns, 
				ARTICLES_MARGIN, ARTICLES_MARGIN));
		for (int i = 0; i < nVisibleArticles && 
				(i + nVisibleArticles * this.nPage) < this.viewedArticles.size(); i++) {
			this.showcasePanel.add(new JArticlePanel(this.store, 
					this.viewedArticles.get(i + nVisibleArticles * this.nPage), this.client));
		}
//		if (this.nPage == this.maxPage && this.viewedArticles.size() / nVisibleArticles != 0) {
		if ((this.viewedArticles.size() - (this.nPage * nVisibleArticles)) < nVisibleArticles) {
			int box = nVisibleArticles - (this.viewedArticles.size() % nVisibleArticles);
			System.out.println ("B: " + box);
			for (int i = 0; i < box; i++) {
				this.showcasePanel.add(Box.createRigidArea(new Dimension(
						JArticlePanel.WIDTH, JArticlePanel.height())));
			}
		}
		//aggiorno
		this.mainPanel.updateUI();
		this.mainPanel.add(this.showcasePanel, BorderLayout.CENTER);
	}
	
	public void resetPagina () {
		this.nPage = 0;
		this.updateArticles();
	}
	
	private void nextPage () {
		this.nPage++;
		if (this.nPage > this.maxPage) {
			this.nPage = this.maxPage;
		}
		this.updateArticles();		
	}
	
	private void previousPage () {
		this.nPage--;
		if (this.nPage < 0) {
			this.nPage = 0;
		}
		this.updateArticles();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.backButton)) {
			this.previousPage();
		}
		else if (e.getSource().equals(this.forwardButton)) {
			this.nextPage();
		}
	}
}

class JClientControlPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -8385562955958262505L;
	
	private JComboBox <String> filterTypeComboBox;
	private JFilterPanel filterPanel;
	private StringBuilder filterTypeString;

	private static final int LABEL_MARGIN = 20;
	private static final int RIGHT_MARGIN = 20;
	private static final int LEFT_MARGIN = 20;
	private static final int LAYOUT_HORIZONTAL_MARGIN = 50;

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
	
	protected static final int HEIGHT = 80;

	public JClientControlPanel (JFrame mainFrame, JClientContentPanel parentPanel) {
		JPanel leftPanel = new JPanel ();
		
		JPanel filterTypePanel = new JPanel ();
		JLabel filterTypeLabel = new JLabel (FILTER_TYPE_LABEL);
		filterTypePanel.setLayout (new BorderLayout ());
		filterTypePanel.add (filterTypeLabel, BorderLayout.PAGE_START);
		filterTypePanel.add (Box.createVerticalStrut (LABEL_MARGIN));
		this.filterTypeComboBox = new JComboBox <String> (FILTER_TYPE_STRINGS);
		this.filterTypeComboBox.addActionListener(this);
		filterTypePanel.add (this.filterTypeComboBox, BorderLayout.PAGE_END);
		
		JPanel filterStringPanel = new JPanel ();
		JLabel filterStringLabel = new JLabel (FILTER_STRING_LABEL);
		filterStringPanel.setLayout(new BorderLayout ());
		filterStringPanel.add (filterStringLabel, BorderLayout.PAGE_START);
		filterStringPanel.add (Box.createVerticalStrut (LABEL_MARGIN));
		this.filterPanel = new JFilterPanel(parentPanel.getStore().MaxQuantita(), 
				parentPanel.getStore().MaxPrezzo());
		filterStringPanel.add (this.filterPanel, BorderLayout.PAGE_END);
		
		this.filterTypeString = new StringBuilder(
				this.filterTypeComboBox.getSelectedItem().toString());
		JButton filterButton = new JButton (FILTER_BUTTON_TEXT);
		filterButton.addActionListener(new FilterListener (parentPanel, 
				this.filterTypeString, this.filterPanel));
	
		leftPanel.add (Box.createRigidArea(new Dimension(LEFT_MARGIN, HEIGHT)));
		leftPanel.add (filterTypePanel);
		leftPanel.add (Box.createHorizontalStrut(LAYOUT_HORIZONTAL_MARGIN));
		leftPanel.add (filterStringPanel);
		leftPanel.add (Box.createHorizontalStrut(LAYOUT_HORIZONTAL_MARGIN));
		leftPanel.add (filterButton);
		
		JPanel rightPanel = new JPanel ();
		
		JButton cartButton = new JButton ();
		try {
		    Image img = ImageIO.read(new File (CART_IMAGE_PATH));
		    cartButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			cartButton.setText(CART_BUTTON_TEXT);
		}
		cartButton.addActionListener(new OpenCartListener(mainFrame, 
				parentPanel.getClient ().getCarrello (), parentPanel.getStore()));
		cartButton.setTransferHandler(new ValueImportTransferHandler(
				parentPanel.getClient().getCarrello()));
		
		rightPanel.add (cartButton);
		rightPanel.add (Box.createRigidArea(new Dimension(RIGHT_MARGIN, HEIGHT)));
		
		this.setLayout(new BorderLayout());
		this.add (leftPanel, BorderLayout.WEST);
		this.add (rightPanel, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.filterTypeComboBox)) {
			this.filterTypeString.replace(0, this.filterTypeString.length(), 
					this.filterTypeComboBox.getSelectedItem().toString());
			this.filterPanel.enableCorrectFilter (this.filterTypeString.toString());
		}
	}
}

class ValueImportTransferHandler extends TransferHandler {
	private static final long serialVersionUID = 7407303027786470664L;

	private Carrello cart;
	
    public static final DataFlavor SUPPORTED_DATE_FLAVOR = Prodotto.getDataFlavor();

    public ValueImportTransferHandler(Carrello cart) {
    	this.cart = cart;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
    }

    @Override
    public boolean importData(TransferSupport support) {
        boolean accept = false;
        if (canImport(support)) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                if (value instanceof Prodotto) {
                    Component component = support.getComponent();
                    if (component instanceof JButton) {
                        this.cart.aggiungiProdotto((Prodotto)value);
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

class JArticlePanel extends JPanel {
	private static final long serialVersionUID = -2838106312491733874L;
	
	private static final int AMOUNT_TEXTFIELD_WIDTH = 50;
	private static final int AMOUNT_TEXTFIELD_HEIGHT = 20;
	private static final int ICON_SIZE = 120;
	private static final int BUTTON_SIZE = 30;
	private static final int GENERIC_MARGIN = 15;
	private static final int INTERACTION_HEIGHT = 30;
	private static final int INTERACTION_HORIZONTAL_SPACE = 50;
	private static final int INFORMATION_SPACE = 6;
	private static final int MARGIN_SIZE = 3;
	
	private static final int DEFAULT_AMOUNT = 1;

	private static final String CODE_TEXT = "Codice: ";
	private static final String NAME_TEXT = "Nome: ";
	private static final String BRAND_TEXT = "Marca: ";
	private static final String CATEGORY_TEXT = "Categoria: ";
	private static final String COST_TEXT = "Prezzo: ";
	private static final String CURRENCY_SYMBOL = " €";
	private static final String AVAILABILITY_TEXT = "Disponibilità: ";
	private static final String OFFER_TEXT = "Offerta: ";
	private static final String NONE_OFFER_TEXT = "Nessuna";
	
	private static final String ADD_BUTTON_TEXT = "+";
	private static final String ADD_IMAGE_PATH = "media/img/add.png";

	protected static final int WIDTH = 220;

	public JArticlePanel(Magazzino store, Prodotto article, Cliente client) {
		JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ImageIcon icon = new ImageIcon(new ResizableIcon(article.getImmagine()).resizeIcon(ICON_SIZE, ICON_SIZE));
		if (icon != null) {
			imageLabel.setIcon(icon);
		}
		JPanel imagePanel = new JPanel ();
		imagePanel.setLayout(new BoxLayout (imagePanel, BoxLayout.Y_AXIS));
		imagePanel.add(Box.createVerticalStrut(GENERIC_MARGIN));
		imagePanel.add(Box.createVerticalStrut((ICON_SIZE - icon.getIconHeight()) / 2));
		imagePanel.add(imageLabel);
		imagePanel.add(Box.createVerticalStrut((ICON_SIZE - icon.getIconHeight()) / 2));
		
		JLabel codeLabel = new JLabel(CODE_TEXT + article.getCodice());
		JLabel nameLabel = new JLabel(NAME_TEXT + article.getNome());
		JLabel brandLabel = new JLabel(BRAND_TEXT + article.getMarca());
		JLabel categoryLabel = new JLabel(CATEGORY_TEXT + article.getCategoria());
		JLabel priceLabel = new JLabel(COST_TEXT + article.getPrezzo() + CURRENCY_SYMBOL);
		JLabel availabilityLabel = new JLabel(AVAILABILITY_TEXT + article.getQuantita());
		String offer;
		if (article.getOfferta() == null) {
			offer = NONE_OFFER_TEXT;
		}
		else {
			offer = article.getOfferta().toString();
		}
		JLabel offerLabel = new JLabel(OFFER_TEXT + offer);
		JPanel informationPanel = new JPanel ();
		informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
		informationPanel.add(codeLabel);
		informationPanel.add(nameLabel);
		informationPanel.add(brandLabel);
		informationPanel.add(categoryLabel);
		informationPanel.add(priceLabel);
		informationPanel.add(availabilityLabel);
		informationPanel.add(offerLabel);
		
		JTextField amountTextField = new JTextField (String.valueOf(DEFAULT_AMOUNT));
		amountTextField.setPreferredSize(new Dimension (AMOUNT_TEXTFIELD_WIDTH, 
				AMOUNT_TEXTFIELD_HEIGHT));
		PlainDocument doc = (PlainDocument) amountTextField.getDocument();
		doc.setDocumentFilter(new AmountDocumentFilter(article.getQuantita()));	
		JButton addToCartButton = new JButton ();
		try {
		    Image img = ImageIO.read(new File (ADD_IMAGE_PATH));
		    addToCartButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			addToCartButton.setText(ADD_BUTTON_TEXT);
		}
		addToCartButton.setPreferredSize(new Dimension (BUTTON_SIZE, BUTTON_SIZE));
		addToCartButton.addActionListener(new AddArticleToCartListener(store, 
				client.getCarrello(), new StringBuilder(article.getCodice()), 
				amountTextField));
		addToCartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		JPanel interactionPanel = new JPanel ();
		interactionPanel.add(amountTextField);
		interactionPanel.add(Box.createRigidArea(new Dimension(INTERACTION_HORIZONTAL_SPACE, 
				INTERACTION_HEIGHT)));
		interactionPanel.add(addToCartButton);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createHorizontalStrut(GENERIC_MARGIN), BorderLayout.WEST);
		bottomPanel.add(interactionPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createHorizontalStrut(GENERIC_MARGIN), BorderLayout.EAST);
		bottomPanel.add(Box.createVerticalStrut(GENERIC_MARGIN), BorderLayout.PAGE_END);

		this.setTransferHandler(new ValueExportTransferHandler(article, amountTextField));
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout (new BorderLayout(0, GENERIC_MARGIN));
        panel.setBorder (new RoundedBorder(Color.GRAY, MARGIN_SIZE, GENERIC_MARGIN, 0));
        panel.setPreferredSize(new Dimension(WIDTH, JArticlePanel.height()));
        panel.add (imagePanel, BorderLayout.PAGE_START);
        panel.add (informationPanel, BorderLayout.CENTER);
        panel.add (Box.createHorizontalStrut(GENERIC_MARGIN), BorderLayout.WEST);
        panel.add (bottomPanel, BorderLayout.PAGE_END);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.add (panel, BorderLayout.PAGE_START);
	}
	
	public static int height () {
		return  MARGIN_SIZE + GENERIC_MARGIN + 
				ICON_SIZE + GENERIC_MARGIN + 
				(new JLabel().getFont().getSize() + 
				INFORMATION_SPACE) * 7 + GENERIC_MARGIN + 
				INTERACTION_HEIGHT + GENERIC_MARGIN * 2 + 
				MARGIN_SIZE;
	}
}

class ValueExportTransferHandler extends TransferHandler {
    private static final long serialVersionUID = -3689725432297463459L;

    private Prodotto storeProduct;
    private JTextField amountTextfield;
    
    public static final DataFlavor SUPPORTED_DATE_FLAVOR = Prodotto.getDataFlavor();

    public ValueExportTransferHandler(Prodotto storeProduct, JTextField amountTextfield) {
    	this.storeProduct = storeProduct;
        this.amountTextfield = amountTextfield;
    }

    public Prodotto getValue() {
    	try {
			Prodotto product = this.storeProduct.clone();
			product.setQuantita(Integer.parseInt(this.amountTextfield.getText()));
            return product;
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