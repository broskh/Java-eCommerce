package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;

import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;

import negozio.Magazzino;  
import negozio.Prodotto;

public class JAdminContentPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -3936116522061776556L;

	private Magazzino store;
	private HashSet <Prodotto> productsAdded;

	private JFrame mainFrame;
	private JStoreTable storeTable;
	private JButton saveButton;
	private JButton loadButton;
	private JButton addButton;
	private JButton modifyButton;
	private JButton deleteButton;
	private JMenuItem closeMenuItem;
	private JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem addMenuItem;
	private JMenuItem modifyMenuItem;
	private JMenuItem deleteMenuItem;

	private static final int CONTENTPANEL_TOP_MARGIN = 30;
	private static final int CONTENTPANEL_SIDE_MARGIN = 30;
	private static final int CONTROLPANEL_BUTTONS_MARGIN = 5;

	private static final int MENUBAR_HEIGHT = 22;
	private static final int CONTROLPANEL_BUTTON_SIZE = 80;
	private static final int CONTROLPANEL_BUTTONS_SPACE = 20;
	
	private static final String FILE_CHOOSER_SAVE_TITLE = "Salva";
	private static final String FILE_CHOOSER_LOAD_TITLE = "Carica";
	private static final String SAVE_BUTTON_TEXT = "Salva";
	private static final String LOAD_BUTTON_TEXT = "Carica";
	private static final String ADD_BUTTON_TEXT = "Aggiungi";
	private static final String MODIFY_BUTTON_TEXT = "Modifica";
	private static final String DELETE_BUTTON_TEXT = "Elimina";
	private static final String FILE_CHOOSER_SAVE_BUTTON_TEXT = "Salva";
	private static final String FILE_CHOOSER_LOAD_BUTTON_TEXT = "Carica";
	private static final String FILE_MENU_TEXT = "File";
	private static final String MANAGEMENT_MENU_TEXT = "Gestione";
	private static final String CLOSE_MENU_ITEM_TEXT = "Chiudi";
	private static final String LOAD_MENU_ITEM_TEXT = "Carica";
	private static final String SAVE_MENU_ITEM_TEXT = "Salva";
	private static final String ADD_MENU_ITEM_TEXT = "Aggiungi";
	private static final String MODIFY_MENU_ITEM_TEXT = "Modifica";
	private static final String DELETE_MENU_ITEM_TEXT = "Elimina";
	private static final String ALERT_LOAD_TITLE = "Caricamento avvenuto";
	private static final String ALERT_LOAD_TEXT = "Caricamento avvenuto con successo.";
	private static final String ALERT_SAVE_TITLE = "Salvataggio avvenuto";
	private static final String ALERT_SAVE_TEXT = "Salvataggio avvenuto con successo.";
	
	private static final String NEW_IMAGES_FOLDER = "media/img/products/";
	private static final String FILE_CHOOSER_OPEN_DIRECTORY = "media/saves";
	private static final String LAST_STORE_FILE_PATH = "media/saves/last";

	public JAdminContentPanel(JFrame mainFrame, Magazzino store) {
		this.store = store;  
		this.mainFrame = mainFrame;
		this.productsAdded = new HashSet<>();
		
		this.setLayout(new BorderLayout());
		this.add(this.menuBar(), BorderLayout.PAGE_START);
		this.add(this.contentPanel(), BorderLayout.CENTER);	
	}
	
	private JPanel contentPanel () {
		this.storeTable = new JStoreTable(this.store);
		JScrollPane scrollTable = new JScrollPane(this.storeTable);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
        JPanel tablePanel = new JPanel (new BorderLayout());
		tablePanel.add(Box.createVerticalStrut(CONTENTPANEL_TOP_MARGIN), BorderLayout.PAGE_START);
		tablePanel.add(Box.createHorizontalStrut(CONTENTPANEL_SIDE_MARGIN), BorderLayout.WEST);
		tablePanel.add(scrollTable, BorderLayout.CENTER);
		tablePanel.add(Box.createHorizontalStrut(CONTENTPANEL_SIDE_MARGIN), BorderLayout.EAST);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(this.controlPanel(), BorderLayout.PAGE_START);
		contentPanel.add(tablePanel, BorderLayout.CENTER);
		return contentPanel;
	}
	
	private JPanel controlPanel () {
		this.saveButton = new JButton(SAVE_BUTTON_TEXT);
		this.saveButton.setPreferredSize(new Dimension(CONTROLPANEL_BUTTON_SIZE,
				CONTROLPANEL_BUTTON_SIZE));
		this.saveButton.addActionListener(this);
		
		this.loadButton = new JButton(LOAD_BUTTON_TEXT);
		this.loadButton.setPreferredSize(new Dimension(CONTROLPANEL_BUTTON_SIZE,
				CONTROLPANEL_BUTTON_SIZE));
		this.loadButton.addActionListener(this);
		
		this.addButton = new JButton(ADD_BUTTON_TEXT);
		this.addButton.setPreferredSize(new Dimension(CONTROLPANEL_BUTTON_SIZE,
				CONTROLPANEL_BUTTON_SIZE));
		this.addButton.addActionListener(this);
		
		this.modifyButton = new JButton(MODIFY_BUTTON_TEXT);
		this.modifyButton.setPreferredSize(new Dimension(CONTROLPANEL_BUTTON_SIZE,
				CONTROLPANEL_BUTTON_SIZE));
		this.modifyButton.addActionListener(this);
		
		this.deleteButton = new JButton(DELETE_BUTTON_TEXT);
		this.deleteButton.setPreferredSize(new Dimension(CONTROLPANEL_BUTTON_SIZE,
				CONTROLPANEL_BUTTON_SIZE));
		this.deleteButton.addActionListener(this);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new EtchedBorder());
		controlPanel.setLayout(new FlowLayout(FlowLayout.LEADING, CONTROLPANEL_BUTTONS_SPACE, CONTROLPANEL_BUTTONS_MARGIN));
		controlPanel.add(this.saveButton);
		controlPanel.add(this.loadButton);
		controlPanel.add(this.addButton);
		controlPanel.add(this.modifyButton);
		controlPanel.add(this.deleteButton);
		return controlPanel;
	}
	
	private JMenuBar menuBar () {
		this.loadMenuItem = new JMenuItem(LOAD_MENU_ITEM_TEXT);
		this.loadMenuItem.addActionListener(this);		
		this.saveMenuItem = new JMenuItem(SAVE_MENU_ITEM_TEXT);
		this.saveMenuItem.addActionListener(this);
		this.closeMenuItem = new JMenuItem(CLOSE_MENU_ITEM_TEXT);
		this.closeMenuItem.addActionListener(this);
		JMenu fileMenu = new JMenu(FILE_MENU_TEXT);
		fileMenu.add(this.loadMenuItem);
		fileMenu.add(this.saveMenuItem);
		fileMenu.add(this.closeMenuItem);
		
		this.addMenuItem = new JMenuItem(ADD_MENU_ITEM_TEXT);
		this.addMenuItem.addActionListener(this);		
		this.modifyMenuItem = new JMenuItem(MODIFY_MENU_ITEM_TEXT);
		this.modifyMenuItem.addActionListener(this);		
		this.deleteMenuItem = new JMenuItem(DELETE_MENU_ITEM_TEXT);
		this.deleteMenuItem.addActionListener(this);
		JMenu managementMenu = new JMenu(MANAGEMENT_MENU_TEXT);
		managementMenu.add(this.addMenuItem);
		managementMenu.add(this.modifyMenuItem);
		managementMenu.add(this.deleteMenuItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(menuBar.getWidth(), MENUBAR_HEIGHT));
		menuBar.add(fileMenu);
		menuBar.add(managementMenu);
		return menuBar;
	}
	
	private File getNewImageFile (File oldImageFile) {
		String newFileName = oldImageFile.getName();
		File newFile = new File (newFileName);
		if (!newFile.exists()) {
			return newFile;
		}
		String extension = "";
		int extensionIndex = newFileName.lastIndexOf('.');
		if (extensionIndex != -1) {
			extension = newFileName.substring(extensionIndex, newFileName.length());
			newFileName = newFileName.substring(0, extensionIndex);
		}
		int i = 0;
		do {
			i++;
			newFile = new File (NEW_IMAGES_FOLDER + newFileName + "_" + i + extension);
		} while (newFile.exists());
		return newFile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.saveButton) || e.getSource().equals(this.saveMenuItem)) {	
			JFileChooser fc = new JFileChooser(new File(FILE_CHOOSER_OPEN_DIRECTORY));
			fc.setDialogTitle(FILE_CHOOSER_SAVE_TITLE);
			fc.setApproveButtonText(FILE_CHOOSER_SAVE_BUTTON_TEXT);
			fc.setFileFilter(new StoreFileFilter());
			if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//				AGGIUNTA NUOVE IMMAGINI
				for (Prodotto articolo : this.productsAdded) {
					File newFile = this.getNewImageFile(articolo.getImmagine());
					try {
						JAdminContentPanel.copyImage(articolo.getImmagine(), newFile);
						articolo.setImmagine(newFile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
//				PULISCO LISTE
				this.productsAdded.clear();
				
				if (fc.getSelectedFile().getName().endsWith(StoreFileFilter.EXTENSION)) {
					store.salvaMagazzino(fc.getSelectedFile());
				}
				else {
					store.salvaMagazzino(new File(
							fc.getSelectedFile().getPath() + StoreFileFilter.EXTENSION));
				}
//				SALVO NELL'APPOSITO FILE IL PERCORSO DELL'ULTIMO MAGAZZINO SALVATO
				BufferedWriter lastStoreFile = null;
		        try {
		        	lastStoreFile = new BufferedWriter(new FileWriter(new File(LAST_STORE_FILE_PATH)));
		            String filePath = fc.getSelectedFile().toString();
		            lastStoreFile.write(filePath);
		        	lastStoreFile.close();
		        } catch ( IOException g ) {
		            g.printStackTrace();
		        }
				JOptionPane.showMessageDialog(this, ALERT_SAVE_TEXT,
						ALERT_SAVE_TITLE, JOptionPane.INFORMATION_MESSAGE);
			}
	    }
		else if(e.getSource().equals(this.loadButton) || e.getSource().equals(this.loadMenuItem)) {			
			JFileChooser fc = new JFileChooser(new File(FILE_CHOOSER_OPEN_DIRECTORY));
			fc.setFileFilter(new StoreFileFilter());
			fc.setDialogTitle(FILE_CHOOSER_LOAD_TITLE);
			fc.setApproveButtonText(FILE_CHOOSER_LOAD_BUTTON_TEXT);
			if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.store.caricaMagazzino(fc.getSelectedFile());
				((ProductsArticlesTableModel)this.storeTable.getModel()).fireTableDataChanged();
				JOptionPane.showMessageDialog(this, ALERT_LOAD_TEXT,
						ALERT_LOAD_TITLE, JOptionPane.INFORMATION_MESSAGE);	
			}
			this.updateUI();
		}
		else if(e.getSource().equals(this.addButton) || e.getSource().equals(this.addMenuItem)) {
			JAddProductToStoreDialog jAddProductDialog = new JAddProductToStoreDialog(
					this.mainFrame, this.store, (ProductsArticlesTableModel)this.storeTable.getModel(), 
					this.productsAdded);
			jAddProductDialog.setVisible(true);
		} 
		else if(e.getSource().equals(this.modifyButton) || e.getSource().equals(this.modifyMenuItem)) {
			JSelectProductToModifyDialog jSearchProductDialog = new JSelectProductToModifyDialog(
					this.mainFrame, this.store, (ProductsArticlesTableModel)this.storeTable.getModel(), 
					this.productsAdded);
			jSearchProductDialog.setVisible(true);
		}
		else if(e.getSource().equals(this.deleteButton) || e.getSource().equals(this.deleteMenuItem)) {
			JRemoveProductFromStoreDialog jDeleteProductDialog = new JRemoveProductFromStoreDialog(
					this.mainFrame, this.store, (ProductsArticlesTableModel)this.storeTable.getModel());
			jDeleteProductDialog.setVisible(true);
		}
		else if (e.getSource().equals(this.closeMenuItem)) {
			this.mainFrame.dispose();
		}
	}
	
	private static void copyImage(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}
	
	class JStoreTable extends JTable implements JProductsTable {
		private static final long serialVersionUID = 4734550632778588769L;
		
		private Magazzino store;
		
		private static final int ROW_HEIGHT = 100;
		
		public JStoreTable(Magazzino store) {
			this.store = store;
			
			this.setModel(new ProductsArticlesTableModel(this.store.getArticoli(),
					ROW_HEIGHT,ProductsArticlesTableModel.STORE_MODE));
			this.setRowHeight(ROW_HEIGHT);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
			this.setDefaultRenderer(String.class, centerRenderer);
			this.setBackground(null);
			this.getColumn(ProductsArticlesTableModel.BUTTON_COLUMN).setCellRenderer(
					new RemoveColumnRender(ROW_HEIGHT));
			this.getColumn(ProductsArticlesTableModel.BUTTON_COLUMN).setCellEditor(
					new RemoveColumnEditor(
					this.store.getArticoli(), ROW_HEIGHT));			
			this.setFocusable(false);
			this.setRowSelectionAllowed(false);
		}

		@Override
		public Prodotto getProductAtRow(int row) {
			return this.store.getArticoli().get(row);
		}
	}
}