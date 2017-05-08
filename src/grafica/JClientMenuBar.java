package grafica;

//import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class JClientMenuBar extends JMenuBar {
	private static final long serialVersionUID = 2390680916104303558L;
	private JMenu fileMenu;
	private JMenu cartMenu;
	private JMenuItem closeMenuItem;
	
	public JClientMenuBar () {
		
		this.fileMenu = new JMenu("File");
//		this.fileMenu.setMnemonic(KeyEvent.VK_A);
//		this.fileMenu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		this.cartMenu = new JMenu ("Carrello");
		this.closeMenuItem = new JMenuItem("Chiudi");
		this.fileMenu.add(this.closeMenuItem);
		this.add(this.fileMenu);
		this.add(this.cartMenu);
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