package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import negozio.Magazzino;

public class JAdminMenuBar extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 8251356911457819974L;
	
	private JFrame mainFrame;
	
	private Magazzino magazzino;
	
	private JMenu fileMenu;
	private JMenu managementMenu;
	private JMenuItem closeMenuItem;
	private JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem addMenuItem;
	private JMenuItem modifyMenuItem;
	private JMenuItem deleteMenuItem;

	private static final String FILE_MENU_TEXT = "File";
	private static final String MANAGEMENT_MENU_TEXT = "Gestione";
	private static final String CLOSE_MENU_ITEM_TEXT = "Chiudi";
	private static final String LOAD_MENU_ITEM_TEXT = "Carica";
	private static final String SAVE_MENU_ITEM_TEXT = "Salva";
	private static final String ADD_MENU_ITEM_TEXT = "Aggiungi";
	private static final String MODIFY_MENU_ITEM_TEXT = "Modifica";
	private static final String DELETE_MENU_ITEM_TEXT = "Elimina";
	
	private static final String CLOSE_MENU_ITEM_ACTION_COMMAND_TEXT = "chiudi";
	private static final String LOAD_MENU_ITEM_ACTION_COMMAND_TEXT = "carica";
	private static final String SAVE_MENU_ITEM_ACTION_COMMAND_TEXT = "salva";
	private static final String ADD_MENU_ITEM_ACTION_COMMAND_TEXT = "aggiungi";
	private static final String MODIFY_MENU_ITEM_ACTION_COMMAND_TEXT = "modifica";
	private static final String DELETE_MENU_ITEM_ACTION_COMMAND_TEXT = "elimina";
	
	private static final String FILE_SAVE_STRING = "media/saves/save21.mag";

	public JAdminMenuBar(Magazzino magazzino,JFrame mainFrame)
	{
		this.magazzino = magazzino;
		this.fileMenu = new JMenu(FILE_MENU_TEXT);
		this.managementMenu = new JMenu(MANAGEMENT_MENU_TEXT);
		
		this.closeMenuItem = new JMenuItem(CLOSE_MENU_ITEM_TEXT);
		this.closeMenuItem.setActionCommand(CLOSE_MENU_ITEM_ACTION_COMMAND_TEXT);
		this.closeMenuItem.addActionListener(this);
		
		this.loadMenuItem = new JMenuItem(LOAD_MENU_ITEM_TEXT);
		this.loadMenuItem.setActionCommand(LOAD_MENU_ITEM_ACTION_COMMAND_TEXT);
		this.loadMenuItem.addActionListener(this);
		
		this.saveMenuItem = new JMenuItem(SAVE_MENU_ITEM_TEXT);
		this.saveMenuItem.setActionCommand(SAVE_MENU_ITEM_ACTION_COMMAND_TEXT);
		this.saveMenuItem.addActionListener(this);
		
		this.fileMenu.add(this.loadMenuItem);
		this.fileMenu.add(this.saveMenuItem);
		this.fileMenu.add(this.closeMenuItem);
		
		this.addMenuItem = new JMenuItem(ADD_MENU_ITEM_TEXT);
		this.addMenuItem.setActionCommand(ADD_MENU_ITEM_ACTION_COMMAND_TEXT);
		this.addMenuItem.addActionListener(this);
		
		this.modifyMenuItem = new JMenuItem(MODIFY_MENU_ITEM_TEXT);
		this.modifyMenuItem.setActionCommand(MODIFY_MENU_ITEM_ACTION_COMMAND_TEXT);
		this.modifyMenuItem.addActionListener(this);
		
		this.deleteMenuItem = new JMenuItem(DELETE_MENU_ITEM_TEXT);
		this.deleteMenuItem.setActionCommand(DELETE_MENU_ITEM_ACTION_COMMAND_TEXT);
		this.deleteMenuItem.addActionListener(this);
		
		this.managementMenu.add(this.addMenuItem);
		this.managementMenu.add(this.modifyMenuItem);
		this.managementMenu.add(this.deleteMenuItem);
		this.add(this.fileMenu);
		this.add(this.managementMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(LOAD_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			/*magazzino.caricaMagazzino("media/saves/magazzino.mag");			
			System.out.println(magazzino);*/
		}
		if(e.getActionCommand().equals(SAVE_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			magazzino.salvaMagazzino(FILE_SAVE_STRING);
		}
		if(e.getActionCommand().equals(CLOSE_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			SwingUtilities.getWindowAncestor(this).dispose();
		}
		if (e.getActionCommand().equals(ADD_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JAddProductDialog jAddProductDialog = new JAddProductDialog(mainFrame,magazzino);
			jAddProductDialog.setVisible(true);
		}
		if (e.getActionCommand().equals(MODIFY_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JSearchProductDialog jSearchProductDialog = new JSearchProductDialog(mainFrame,magazzino);
			jSearchProductDialog.setVisible(true);
		}
		if (e.getActionCommand().equals(DELETE_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JDeleteProductDialog jDeleteProductDialog = new JDeleteProductDialog(mainFrame,magazzino);
			jDeleteProductDialog.setVisible(true);
		}
	}
}

