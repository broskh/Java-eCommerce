package grafica;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class JClientMenuBar extends JMenuBar {
	private static final long serialVersionUID = 2390680916104303558L;
	private JMenu fileMenu;
	private JMenu filterMenu;
	private JMenu cartMenu;
	private JMenuItem closeItem;
	private JMenuItem nameFilterItem;
	private JMenuItem brandFilterItem;
	private JMenuItem codeFilterItem;
	private JMenuItem categoryFilterItem;
	private JMenuItem priceFilterItem;
	private JMenuItem amountFilterItem;
	private JMenuItem showCartItem;
	private JMenuItem addArticleItem;
	private JMenuItem removeArticleItem;
	private JMenuItem emptyCartItem;
	
	private static final String FILE_MENU_STRING = "File";
	private static final String FILTER_MENU_STRING = "Filtra";
	private static final String CART_MENU_STRING = "Carrello";
	private static final String CLOSE_ITEM_STRING = "Chiudi";
	private static final String NAME_FILTER_ITEM_STRING = "Nome";
	private static final String BRAND_FILTER_ITEM_STRING = "Marca";
	private static final String CODE_FILTER_ITEM_STRING = "Codice";
	private static final String CATEGORY_FILTER_ITEM_STRING = "Categoria";
	private static final String PRICE_FILTER_ITEM_STRING = "Prezzo";
	private static final String AMOUNT_FILTER_ITEM_STRING = "Quantità";
	private static final String SHOW_CART_ITEM = "Visualizza";
	private static final String ADD_ARTICLE_ITEM = "Aggiungi articolo";
	private static final String REMOVE_ARTICLE_ITEM = "Rimuovi articolo";
	private static final String EMPTY_CART_ITEM = "Svuota";
	
	public JClientMenuBar () {
		
		this.fileMenu = new JMenu (FILE_MENU_STRING);
		this.closeItem = new JMenuItem (CLOSE_ITEM_STRING);
		this.fileMenu.add (this.closeItem);
		
		this.filterMenu = new JMenu (FILTER_MENU_STRING);
		this.nameFilterItem = new JMenuItem (NAME_FILTER_ITEM_STRING);
		this.brandFilterItem = new JMenuItem (BRAND_FILTER_ITEM_STRING);
		this.codeFilterItem = new JMenuItem (CODE_FILTER_ITEM_STRING);
		this.categoryFilterItem = new JMenuItem (CATEGORY_FILTER_ITEM_STRING);
		this.priceFilterItem = new JMenuItem (PRICE_FILTER_ITEM_STRING);
		this.amountFilterItem = new JMenuItem (AMOUNT_FILTER_ITEM_STRING);
		this.filterMenu.add (this.nameFilterItem);
		this.filterMenu.add (this.brandFilterItem);
		this.filterMenu.add (this.codeFilterItem);
		this.filterMenu.add (this.categoryFilterItem);
		this.filterMenu.add (this.priceFilterItem);
		this.filterMenu.add (this.amountFilterItem);
		
		this.cartMenu = new JMenu (CART_MENU_STRING);
		this.showCartItem = new JMenuItem(SHOW_CART_ITEM);
		this.addArticleItem = new JMenuItem(ADD_ARTICLE_ITEM);
		this.removeArticleItem = new JMenuItem(REMOVE_ARTICLE_ITEM);
		this.emptyCartItem = new JMenuItem(EMPTY_CART_ITEM);
		this.cartMenu.add(this.showCartItem);
		this.cartMenu.add(this.addArticleItem);
		this.cartMenu.add(this.removeArticleItem);
		this.cartMenu.add(this.emptyCartItem);
		
		this.add (this.fileMenu);
		this.add (this.filterMenu);
		this.add (this.cartMenu);
//		this.fileMenu.setMnemonic(KeyEvent.VK_A);
//		this.fileMenu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
//		jMenuBar.setMnemonic(KeyEvent.VK_A);
//		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
//		menuBar.add(menu);
//
//		//a group of JMenuItems
//		menuItem = new JMenuItem("A text-only menu item",
//		                         KeyEvent.VK_T);
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(
//		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
//		menuItem.getAccessibleContext().setAccessibleDescription(
//		        "This doesn't really do anything");
//		menu.add(menuItem);
	}
}