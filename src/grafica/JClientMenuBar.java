package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import negozio.Carrello;
import negozio.Magazzino;

public class JClientMenuBar extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 2390680916104303558L;
	
	private Magazzino magazzino;
	private Carrello carrello;
	
	private JMenu fileMenu;
	private JMenu filterMenu;
	private JMenu cartMenu;
	private JMenuItem closeItem;
	private JMenuItem nameFilterItem;
	private JMenuItem brandFilterItem;
	private JMenuItem codeFilterItem;
	private JMenuItem categoryFilterItem;
	private JMenuItem costFilterItem;
	private JMenuItem amountFilterItem;
	private JMenuItem showCartItem;
	private JMenuItem emptyCartItem;
	
	private static final String FILE_MENU_STRING = "File";
	private static final String FILTER_MENU_STRING = "Filtra";
	private static final String CART_MENU_STRING = "Carrello";
	private static final String CLOSE_ITEM_STRING = "Chiudi";
	private static final String SHOW_CART_ITEM = "Visualizza";
	private static final String EMPTY_CART_ITEM = "Svuota";
	
	public JClientMenuBar (Magazzino magazzino, Carrello carrello) {
		this.magazzino = magazzino;
		this.carrello = carrello;
		this.fileMenu = new JMenu (FILE_MENU_STRING);
		this.closeItem = new JMenuItem (CLOSE_ITEM_STRING);
		this.closeItem.addActionListener(this);
		this.fileMenu.add (this.closeItem);
		
		this.filterMenu = new JMenu (FILTER_MENU_STRING);
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
		this.filterMenu.add (this.nameFilterItem);
		this.filterMenu.add (this.brandFilterItem);
		this.filterMenu.add (this.codeFilterItem);
		this.filterMenu.add (this.categoryFilterItem);
		this.filterMenu.add (this.costFilterItem);
		this.filterMenu.add (this.amountFilterItem);
		
		this.cartMenu = new JMenu (CART_MENU_STRING);
		this.showCartItem = new JMenuItem(SHOW_CART_ITEM);
		this.showCartItem.addActionListener(new OpenCartDialogListener((JeCommerceFrame) SwingUtilities.getWindowAncestor(this), this.carrello, this.magazzino));
		this.emptyCartItem = new JMenuItem(EMPTY_CART_ITEM);
		this.emptyCartItem.addActionListener(new EmptyCartListener (this.carrello));
		this.cartMenu.add(this.showCartItem);
		this.cartMenu.add(this.emptyCartItem);
		
		this.add (this.fileMenu);
		this.add (this.filterMenu);
		this.add (this.cartMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource().equals(this.closeItem)) {
			 SwingUtilities.getWindowAncestor(this).dispose();
		 }
		 else if (e.getSource().equals(this.nameFilterItem) || 
				 e.getSource().equals(this.brandFilterItem) ||
				 e.getSource().equals(this.codeFilterItem) || 
				 e.getSource().equals(this.categoryFilterItem) || 
				 e.getSource().equals(this.amountFilterItem) || 
				 e.getSource().equals(this.costFilterItem)) {
			 JFilterDialog filterDialog = new JFilterDialog(((JeCommerceFrame) SwingUtilities.getWindowAncestor(this)), this.magazzino, ((JMenuItem)e.getSource()).getText());
			 filterDialog.setVisible(true);
		}
	}
}