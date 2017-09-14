package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.text.PlainDocument;

import negozio.Carrello;
import negozio.Magazzino;
import negozio.Prodotto;

import utenza.Cliente;

public class JClientContentPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -3383648558571677903L;

	private Magazzino store;
	private Cliente client;
	private Carrello cart;
	private ArrayList <Prodotto> viewedArticles;
	private int nPage;
	private int maxPage;
	private StringBuilder filterTypeString;

	private JFrame mainFrame;	
	private JPanel mainContentPanel;
	private JPanel showcasePanel;	
	private JFilterPanel filterPanel;	
	private JImageButton backButton;
	private JImageButton forwardButton;
	private JImageButton cartButton;
	private JComboBox <String> filterTypeComboBox;	
	private JMenuItem closeItem;
	private JMenuItem nameFilterItem;
	private JMenuItem brandFilterItem;
	private JMenuItem codeFilterItem;
	private JMenuItem categoryFilterItem;
	private JMenuItem costFilterItem;
	private JMenuItem amountFilterItem;
	private JMenuItem showCartItem;
	private JMenuItem addArticleItem;
	private JMenuItem removeArticleItem;
	private JMenuItem emptyCartItem;

	private static final int DEFAULT_GENERIC_MARGIN = 15;
	private static final int FRAME_RIGHT_MARGIN = 40;
	private static final int FRAME_LEFT_MARGIN = 40;	
	private static final int CONTENTPANEL_TOP_MARGIN = 15;	
	private static final int CONTENTPANEL_BOTTOM_MARGIN = 15;	
	private static final int BOTTOMBUTTONS_TOP_MARGIN = 10;
	private static final int BOTTOM_BUTTONS_SPACE = 450;
	private static final int CONTROLPANEL_LABEL_MARGIN = 20;
	private static final int CONTROLPANEL_RIGHT_MARGIN = 20;
	private static final int CONTROLPANEL_LEFT_MARGIN = 20;
	private static final int CONTROLPANEL_HORIZONTAL_SPACE = 60;
	private static final int ARTICLES_MARGIN = 10;

	private static final int MENUBAR_HEIGHT = 22;
	private static final int CONTROLPANEL_HEIGHT = 80;
	private static final int BOTTOM_BUTTON_SIZE = 45;
	private static final int ARTICLE_AMOUNT_WIDTH = 50;
	private static final int ARTICLE_AMOUNT_HEIGHT = 25;
	private static final int PRODUCT_ICON_SIZE = 120;
	private static final int ARTICLE_ADD_BUTTON_SIZE = 32;
	private static final int CART_BUTTON_SIZE = 80;
	private static final int ARTICLE_INTERACTION_HEIGHT = 35;
	private static final int ARTICLE_INTERACTION_HORIZONTAL_SPACE = 50;
	private static final int ARTICLE_INFORMATION_SPACE = 7;	
	private static final int ARTICLE_BORDER_SIZE = 3;
	private static final int ARTICLE_BORDER_RADII = 15;
	private static final int WIDTH = 220;
	
	private static final int DEFAULT_AMOUNT = 1;

	private static final Color ADD_PRODUCT_BUTTON_COLOR = new Color (23, 165, 86);

	private static final String BACK_BUTTON_TEXT = "<";
	private static final String FORWARD_BUTTON_TEXT = ">";
	private static final String FILTER_BUTTON_TEXT = "Filtra";
	private static final String CART_BUTTON_TEXT = "Carrello";	
	private static final String ADD_BUTTON_TEXT = "+";	
	private static final String FILTER_TYPE_LABEL = "Filtra per:";
	private static final String FILTER_STRING_LABEL = "Criterio di ricerca:";
	private static final String CODE_LABEL = "Codice: ";
	private static final String NAME_LABEL = "Nome: ";
	private static final String BRAND_LABEL = "Marca: ";
	private static final String CATEGORY_LABEL = "Categoria: ";
	private static final String COST_LABEL = "Prezzo: ";
	private static final String AVAILABILITY_LABEL = "Disponibilità: ";
	private static final String OFFER_LABEL = "Offerta: ";	
	private static final String FILE_MENU_STRING = "File";
	private static final String FILTER_MENU_STRING = "Filtra";
	private static final String CART_MENU_STRING = "Carrello";
	private static final String CLOSE_ITEM_STRING = "Chiudi";
	private static final String SHOW_CART_ITEM = "Visualizza";
	private static final String ADD_ARTICLE_ITEM = "Aggiungi articolo";
	private static final String REMOVE_ARTICLE_ITEM = "Rimuovi articolo";
	private static final String EMPTY_CART_ITEM = "Svuota";
	private static final String CURRENCY_SYMBOL = " €";
	private static final String NONE_OFFER_TEXT = "Nessuna";
	private static final String EMPTY_CART_TITLE = "Attenzione";
	private static final String EMPTY_CART_TEXT = "Il carrello è vuoto.";
	private static final String EMPTY_STORE_TITLE = "Attenzione";
	private static final String EMPTY_STORE_TEXT = "Il magazzino è vuoto.";
	
	private static final String[] FILTER_TYPE_STRINGS = {
			Magazzino.STRINGA_FILTRO_NOME,
			Magazzino.STRINGA_FILTRO_MARCA,
			Magazzino.STRINGA_FILTRO_CODICE,
			Magazzino.STRINGA_FILTRO_CATEGORIA,
			Magazzino.STRINGA_FILTRO_PREZZO,
			Magazzino.STRINGA_FILTRO_QUANTITA
		};
	
	private static final String ADD_IMAGE_PATH = "media/img/add_to_cart_icon.png";
	private static final String CART_IMAGE_PATH = "media/img/cart_icon.png";
	private static final String BACK_IMAGE_PATH = "media/img/back_icon.png";
	private static final String FORWARD_IMAGE_PATH = "media/img/forward_icon.png";
	
	public JClientContentPanel(JFrame mainFrame, Magazzino store, Cliente client) {
		this.mainFrame = mainFrame;
		this.store = store;
		this.client = client;
		this.cart = this.client.getCarrello();
		this.viewedArticles = this.store.getArticoli();
		this.nPage = 0;
		this.maxPage = 0;
		
		this.setLayout(new BorderLayout());
		this.add(this.menuBar(), BorderLayout.PAGE_START);
		this.add(this.contentPanel(), BorderLayout.CENTER);		
	}
	
	public void setViewedArticles (ArrayList <Prodotto> viewedArticles) {
		this.viewedArticles = viewedArticles;
		this.updateArticles();
	}

	private JPanel contentPanel () {
		this.showcasePanel = new JPanel();
		
		this.mainContentPanel = new JPanel(new BorderLayout());
		this.mainContentPanel.add(Box.createVerticalStrut(
				CONTENTPANEL_TOP_MARGIN), BorderLayout.PAGE_START);
		this.mainContentPanel.add(Box.createHorizontalStrut(
				FRAME_LEFT_MARGIN), BorderLayout.WEST);
		this.mainContentPanel.add(this.showcasePanel, BorderLayout.CENTER);
		this.mainContentPanel.add(Box.createHorizontalStrut(
				FRAME_RIGHT_MARGIN), BorderLayout.EAST);
		this.mainContentPanel.add(this.bottomButtonsPanel(), BorderLayout.PAGE_END);
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(this.controlPanel(), BorderLayout.PAGE_START);
		contentPanel.add(this.mainContentPanel, BorderLayout.CENTER);
		return contentPanel;
	}
	
	private JPanel bottomButtonsPanel () {
		this.backButton =
				new JImageButton(new File(BACK_IMAGE_PATH), BOTTOM_BUTTON_SIZE, BACK_BUTTON_TEXT);
		this.backButton.addActionListener(this);
		
		this.forwardButton = 
				new JImageButton(
				new File(FORWARD_IMAGE_PATH), BOTTOM_BUTTON_SIZE, FORWARD_BUTTON_TEXT);
		this.forwardButton.addActionListener(this);
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsPanel.add(this.backButton);
		buttonsPanel.add(Box.createHorizontalStrut(BOTTOM_BUTTONS_SPACE));
		buttonsPanel.add(this.forwardButton);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(Box.createVerticalStrut(BOTTOMBUTTONS_TOP_MARGIN), 
				BorderLayout.PAGE_START);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createVerticalStrut(
				CONTENTPANEL_BOTTOM_MARGIN), BorderLayout.PAGE_END);
		return bottomPanel;
	}
	
	private JMenuBar menuBar () {
		this.closeItem = new JMenuItem (CLOSE_ITEM_STRING);
		this.closeItem.addActionListener(this);
		JMenu fileMenu = new JMenu (FILE_MENU_STRING);
		fileMenu.add (this.closeItem);
		
		this.nameFilterItem = new JMenuItem (Magazzino.STRINGA_FILTRO_NOME);
		this.nameFilterItem.addActionListener(this);
		this.brandFilterItem = new JMenuItem (Magazzino.STRINGA_FILTRO_MARCA);
		this.brandFilterItem.addActionListener(this);
		this.codeFilterItem = new JMenuItem (Magazzino.STRINGA_FILTRO_CODICE);
		this.codeFilterItem.addActionListener(this);
		this.categoryFilterItem = new JMenuItem (Magazzino.STRINGA_FILTRO_CATEGORIA);
		this.categoryFilterItem.addActionListener(this);
		this.costFilterItem = new JMenuItem (Magazzino.STRINGA_FILTRO_PREZZO);
		this.costFilterItem.addActionListener(this);
		this.amountFilterItem = new JMenuItem (Magazzino.STRINGA_FILTRO_QUANTITA);
		this.amountFilterItem.addActionListener(this);
		JMenu filterMenu = new JMenu (FILTER_MENU_STRING);
		filterMenu.add (this.nameFilterItem);
		filterMenu.add (this.brandFilterItem);
		filterMenu.add (this.codeFilterItem);
		filterMenu.add (this.categoryFilterItem);
		filterMenu.add (this.costFilterItem);
		filterMenu.add (this.amountFilterItem);
		
		this.showCartItem = new JMenuItem(SHOW_CART_ITEM);
		this.showCartItem.addActionListener(this);
		this.addArticleItem = new JMenuItem(ADD_ARTICLE_ITEM);
		this.addArticleItem.addActionListener(this);
		this.removeArticleItem = new JMenuItem(REMOVE_ARTICLE_ITEM);
		this.removeArticleItem.addActionListener(this);
		this.emptyCartItem = new JMenuItem(EMPTY_CART_ITEM);
		this.emptyCartItem.addActionListener(new EmptyCartListener (this.mainFrame, this.cart));
		JMenu cartMenu = new JMenu (CART_MENU_STRING);
		cartMenu.add(this.showCartItem);
		cartMenu.add(this.addArticleItem);
		cartMenu.add(this.removeArticleItem);
		cartMenu.add(this.emptyCartItem);
		
		JMenuBar clientMenubar = new JMenuBar();
		clientMenubar.setPreferredSize(new Dimension(this.getWidth(), MENUBAR_HEIGHT));
		clientMenubar.add (fileMenu);
		clientMenubar.add (filterMenu);
		clientMenubar.add (cartMenu);
		return clientMenubar;
	}
	
	private JPanel controlPanel () {		
		JLabel filterTypeLabel = new JLabel (FILTER_TYPE_LABEL);
		this.filterTypeComboBox = new JComboBox <String> (FILTER_TYPE_STRINGS);
		this.filterTypeComboBox.addActionListener(this);
		JPanel filterTypePanel = new JPanel ();
		filterTypePanel.setOpaque(false);
		filterTypePanel.setLayout (new BorderLayout ());
		filterTypePanel.add (filterTypeLabel, BorderLayout.PAGE_START);
		filterTypePanel.add (Box.createVerticalStrut (CONTROLPANEL_LABEL_MARGIN));
		filterTypePanel.add (this.filterTypeComboBox, BorderLayout.PAGE_END);

		JLabel filterStringLabel = new JLabel (FILTER_STRING_LABEL);
		this.filterPanel = new JFilterPanel(this.store.maxQuantita(), this.store.maxPrezzo());
		JPanel filterStringPanel = new JPanel ();
		filterStringPanel.setOpaque(false);
		filterStringPanel.setLayout(new BorderLayout ());
		filterStringPanel.add (filterStringLabel, BorderLayout.PAGE_START);
		filterStringPanel.add (Box.createVerticalStrut (CONTROLPANEL_LABEL_MARGIN));
		filterStringPanel.add (this.filterPanel, BorderLayout.PAGE_END);
		
		this.filterTypeString = new StringBuilder(
				this.filterTypeComboBox.getSelectedItem().toString());
		JButton filterButton = new JButton (FILTER_BUTTON_TEXT);
		filterButton.addActionListener(new FilterListener (this, 
				this.filterTypeString, this.filterPanel, this.store));

		JPanel leftPanel = new JPanel ();
		leftPanel.setOpaque(false);
		leftPanel.add (Box.createRigidArea(
				new Dimension(CONTROLPANEL_LEFT_MARGIN, CONTROLPANEL_HEIGHT)));
		leftPanel.add (filterTypePanel);
		leftPanel.add (Box.createHorizontalStrut(CONTROLPANEL_HORIZONTAL_SPACE));
		leftPanel.add (filterStringPanel);
		leftPanel.add (Box.createHorizontalStrut(CONTROLPANEL_HORIZONTAL_SPACE));
		leftPanel.add (filterButton);
		
		this.cartButton = 
				new JImageButton(new File(CART_IMAGE_PATH), CART_BUTTON_SIZE, CART_BUTTON_TEXT);
		this.cartButton.addActionListener(this);
		this.cartButton.setTransferHandler(new ProdottoImportTransferHandler(
				this.cart));
		JPanel rightPanel = new JPanel ();
		rightPanel.setOpaque(false);
		rightPanel.add (cartButton);
		rightPanel.add (Box.createRigidArea(new Dimension(
				CONTROLPANEL_RIGHT_MARGIN, CONTROLPANEL_HEIGHT)));

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add (leftPanel, BorderLayout.WEST);
		controlPanel.add (rightPanel, BorderLayout.EAST);
		return controlPanel;
	}
	
	private JPanel articlePanel (Prodotto product) {
		JIconLabel imageLabel = new JIconLabel(product.getImmagine(), PRODUCT_ICON_SIZE);
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel imagePanel = new JPanel ();
		imagePanel.setOpaque(false);
		imagePanel.setPreferredSize(new Dimension(PRODUCT_ICON_SIZE, PRODUCT_ICON_SIZE));
		imagePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        imagePanel.add(imageLabel, gbc);
		
		JLabel codeLabel = new JLabel(CODE_LABEL + product.getCodice());
		JLabel nameLabel = new JLabel(NAME_LABEL + product.getNome());
		JLabel brandLabel = new JLabel(BRAND_LABEL + product.getMarca());
		JLabel categoryLabel = new JLabel(CATEGORY_LABEL + product.getCategoria());
		JLabel priceLabel = new JLabel(COST_LABEL + product.getPrezzo() + CURRENCY_SYMBOL);
		JLabel availabilityLabel = new JLabel(AVAILABILITY_LABEL + product.getQuantita());
		String offer;
		if (product.getOfferta() == null) {
			offer = NONE_OFFER_TEXT;
		}
		else {
			offer = product.getOfferta().toString();
		}
		JLabel offerLabel = new JLabel(OFFER_LABEL + offer);
		JPanel informationPanel = new JPanel ();
		informationPanel.setOpaque(false);
		informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
		informationPanel.add(codeLabel);
		informationPanel.add(nameLabel);
		informationPanel.add(brandLabel);
		informationPanel.add(categoryLabel);
		informationPanel.add(priceLabel);
		informationPanel.add(availabilityLabel);
		informationPanel.add(offerLabel);
		
		JTextField amountTextField = new JTextField (String.valueOf(DEFAULT_AMOUNT));
		amountTextField.setPreferredSize(
				new Dimension (ARTICLE_AMOUNT_WIDTH, ARTICLE_AMOUNT_HEIGHT));
		PlainDocument doc = (PlainDocument) amountTextField.getDocument();
		doc.setDocumentFilter(new AmountDocumentFilter(product.getQuantita()));	
		JImageButton addToCartButton = 
				new JImageButton(
				new File(ADD_IMAGE_PATH), ARTICLE_ADD_BUTTON_SIZE, ADD_BUTTON_TEXT);
		addToCartButton.addActionListener(new AddProductToCartListener(this.store, 
				this.cart, new StringBuilder(product.getCodice()), amountTextField));
		addToCartButton.setBackground(ADD_PRODUCT_BUTTON_COLOR);
		addToCartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JPanel interactionPanel = new JPanel ();
		interactionPanel.setOpaque(false);
		interactionPanel.add(amountTextField);
		interactionPanel.add(Box.createRigidArea(new Dimension(
				ARTICLE_INTERACTION_HORIZONTAL_SPACE, ARTICLE_INTERACTION_HEIGHT)));
		interactionPanel.add(addToCartButton);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		bottomPanel.add(Box.createHorizontalStrut(DEFAULT_GENERIC_MARGIN), BorderLayout.WEST);
		bottomPanel.add(interactionPanel, BorderLayout.CENTER);
		bottomPanel.add(Box.createHorizontalStrut(DEFAULT_GENERIC_MARGIN), BorderLayout.EAST);
		bottomPanel.add(Box.createVerticalStrut(DEFAULT_GENERIC_MARGIN), BorderLayout.PAGE_END);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout (new BorderLayout(0, DEFAULT_GENERIC_MARGIN));
        mainPanel.setBorder (new RoundedBorder(
        		Color.GRAY, ARTICLE_BORDER_SIZE, ARTICLE_BORDER_RADII, 0));
        mainPanel.setPreferredSize(new Dimension(WIDTH, JClientContentPanel.articlePanelHeight()));
        mainPanel.add (imagePanel, BorderLayout.PAGE_START);
        mainPanel.add (informationPanel, BorderLayout.CENTER);
        mainPanel.add (Box.createHorizontalStrut(DEFAULT_GENERIC_MARGIN), BorderLayout.WEST);
        mainPanel.add (bottomPanel, BorderLayout.PAGE_END);

        JPanel articlePanel = new JPanel();
        articlePanel.setOpaque(false);
        articlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        articlePanel.setTransferHandler(new ProdottoExportTransferHandler(product, amountTextField));
		articlePanel.addMouseMotionListener(new ArticleMouseAdapter(articlePanel));
        articlePanel.add (mainPanel, BorderLayout.PAGE_START);
        return articlePanel;
	}
	
	public static int articlePanelHeight () {
		return  ARTICLE_BORDER_SIZE + DEFAULT_GENERIC_MARGIN + 
				PRODUCT_ICON_SIZE + DEFAULT_GENERIC_MARGIN + 
				(new JLabel().getFont().getSize() + 
				ARTICLE_INFORMATION_SPACE) * 7 + DEFAULT_GENERIC_MARGIN + 
				ARTICLE_INTERACTION_HEIGHT + DEFAULT_GENERIC_MARGIN * 2 + 
				ARTICLE_BORDER_SIZE;
	}
	
	public void updateArticles () {
		JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		int showcaseWidth = mainFrame.getWidth() - FRAME_RIGHT_MARGIN - FRAME_LEFT_MARGIN;
		int showcaseHeight = this.getHeight() - CONTROLPANEL_HEIGHT - 
				CONTENTPANEL_TOP_MARGIN - CONTENTPANEL_BOTTOM_MARGIN - 
				BOTTOMBUTTONS_TOP_MARGIN - this.backButton.getHeight() - 1;
		int nColumns = showcaseWidth / (JClientContentPanel.WIDTH + ARTICLES_MARGIN);
		int nRows = showcaseHeight / (JClientContentPanel.articlePanelHeight() + ARTICLES_MARGIN);
		int nVisibleArticles = nRows * nColumns;
		this.maxPage = this.viewedArticles.size() / nVisibleArticles;
		if (this.maxPage != 0 && this.viewedArticles.size() % nVisibleArticles == 0) {
			this.maxPage--;
		}

		this.mainContentPanel.remove(this.showcasePanel);
		this.showcasePanel = new JPanel(new GridLayout(nRows, nColumns, 
				ARTICLES_MARGIN, ARTICLES_MARGIN));
		for (int i = 0; i < nVisibleArticles && 
				(i + nVisibleArticles * this.nPage) < this.viewedArticles.size(); i++) {
			this.showcasePanel.add(this.articlePanel(
					this.viewedArticles.get(i + nVisibleArticles * this.nPage)));
		}
		if ((this.viewedArticles.size() - (this.nPage * nVisibleArticles)) < nVisibleArticles) {
			int box = nVisibleArticles - (this.viewedArticles.size() % nVisibleArticles);
			for (int i = 0; i < box; i++) {
				this.showcasePanel.add(Box.createRigidArea(new Dimension(
						JClientContentPanel.WIDTH, JClientContentPanel.articlePanelHeight())));
			}
		}
		//aggiorno
		this.mainContentPanel.updateUI();
		this.mainContentPanel.add(this.showcasePanel, BorderLayout.CENTER);
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
		if (e.getSource().equals(this.closeItem)) {
			this.mainFrame.dispose();
		 }
		 else if (e.getSource().equals(this.nameFilterItem) || 
				 e.getSource().equals(this.brandFilterItem) ||
				 e.getSource().equals(this.codeFilterItem) || 
				 e.getSource().equals(this.categoryFilterItem) || 
				 e.getSource().equals(this.amountFilterItem) || 
				 e.getSource().equals(this.costFilterItem)) {
			JFilterDialog filterDialog = new JFilterDialog(
					this, this.store, ((JMenuItem)e.getSource()).getText());
			filterDialog.setVisible(true);
		}
		else if (e.getSource().equals(this.addArticleItem)) {
			if (this.store.getArticoli().isEmpty()) {
				JOptionPane.showMessageDialog(this, EMPTY_STORE_TEXT, EMPTY_STORE_TITLE,
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				JAddProductToCartDialog addArticleDialog = new JAddProductToCartDialog(
						this.mainFrame, this.store, this.cart);
				addArticleDialog.setVisible(true);
			}
		}
		else if (e.getSource().equals(this.removeArticleItem)) {
			if (this.cart.getArticoli().isEmpty()) {
				JOptionPane.showMessageDialog(this, EMPTY_CART_TEXT, EMPTY_CART_TITLE,
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				JRemoveProductFromCartDialog removeArticleDialog = 
						new JRemoveProductFromCartDialog(
					 this.mainFrame, this.cart);
				removeArticleDialog.setVisible(true);
			}			 
		}
		else if (e.getSource().equals(this.backButton)) {
			this.previousPage();
		}
		else if (e.getSource().equals(this.forwardButton)) {
			this.nextPage();
		}
		else if (e.getSource().equals(this.filterTypeComboBox)) {
			this.filterTypeString.replace(0, this.filterTypeString.length(), 
					this.filterTypeComboBox.getSelectedItem().toString());
			this.filterPanel.enableCorrectFilter (this.filterTypeString.toString());
		}
		else if (e.getSource().equals(this.showCartItem) || 
				e.getSource().equals(this.cartButton)) {
			JCartDialog cartDialog = new JCartDialog(this, this.cart, this.store);
			cartDialog.setVisible(true);
		}
	}
	
	class ArticleMouseAdapter extends MouseAdapter {
		private JPanel articlePanel;
		
		public ArticleMouseAdapter (JPanel articlePanel) {
			 this.articlePanel = articlePanel;
		}
		
		@Override
        public void mouseMoved(MouseEvent e) {
        	this.articlePanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }
    	
        @Override
        public void mouseDragged(MouseEvent e) {
            JPanel panel = (JPanel) e.getSource();
            TransferHandler handle = panel.getTransferHandler();
            handle.exportAsDrag(panel, e, TransferHandler.COPY);
        }
	}
}