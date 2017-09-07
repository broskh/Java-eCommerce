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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;

import negozio.Magazzino;  
import negozio.Prodotto;

public class JAdminContentPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -3936116522061776556L;

//	GENERICO
	private Magazzino store;
	private HashSet <Prodotto> articlesAdded;
	private HashSet <File> imagesToRemove;

	private JStoreTable jStoreTable;
	private JFrame mainFrame;
	private JButton jSaveButton;
	private JButton jLoadButton;
	private JButton jAddButton;
	private JButton jModifyButton;
	private JButton jDeleteButton;
	private JMenuItem closeMenuItem;
	private JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem addMenuItem;
	private JMenuItem modifyMenuItem;
	private JMenuItem deleteMenuItem;

	private static final int CONTENTPANEL_TOP_MARGIN = 40;
	private static final int CONTENTPANEL_SIDE_MARGIN = 40;

	private static final int MENUBAR_HEIGHT = 22;
	private static final int CONTROLPANEL_BUTTON_SIZE = 80;

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
	
//	DA CONTROLLARE
	private static final String NEW_IMAGE_FOLDER = "media/img/products/";
	private static final String FILE_CHOOSER_OPEN_DIRECTORY = "media/saves";
	private static final String LAST_STORE_FILE_PATH = "media/saves/last";
//	private static final String RETURN_NEW_IMAGE_PATH = "media\\img\\products\\";
//	private static final String START_TO_PATH_STRING = "media";

	public JAdminContentPanel(JFrame mainFrame, Magazzino store) {
		this.articlesAdded = new HashSet<>();
		this.imagesToRemove = new HashSet<>();
		this.store = store;  
		this.mainFrame = mainFrame;
		this.setLayout(new BorderLayout());
		this.add(this.adminMenuBar(), BorderLayout.PAGE_START);
		this.add(this.contentPanel(), BorderLayout.CENTER);	
	}
	
	private JPanel contentPanel () {
		this.jStoreTable = new JStoreTable(this.store);
		JScrollPane scrollTable = new JScrollPane(this.jStoreTable);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
        JPanel jCartPanel = new JPanel (new BorderLayout());
		jCartPanel.add(Box.createVerticalStrut(CONTENTPANEL_TOP_MARGIN), BorderLayout.PAGE_START);
		jCartPanel.add(Box.createHorizontalStrut(CONTENTPANEL_SIDE_MARGIN), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(CONTENTPANEL_SIDE_MARGIN), BorderLayout.EAST);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(this.controlPanel(), BorderLayout.PAGE_START);
		contentPanel.add(jCartPanel, BorderLayout.CENTER);
		return contentPanel;
	}
	
	private JPanel controlPanel () {
		this.jSaveButton = new JButton(JAdminContentPanel.SAVE_BUTTON_TEXT);
		this.jSaveButton.setPreferredSize(new Dimension(JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE,
				JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE));		
		this.jSaveButton.setBorder(new EtchedBorder());
		this.jSaveButton.addActionListener(this);
		
		this.jLoadButton = new JButton(JAdminContentPanel.LOAD_BUTTON_TEXT);
		this.jLoadButton.setPreferredSize(new Dimension(JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE,
				JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE));		
		this.jLoadButton.setBorder(new EtchedBorder());
		this.jLoadButton.addActionListener(this);
		
		this.jAddButton = new JButton(JAdminContentPanel.ADD_BUTTON_TEXT);
		this.jAddButton.setPreferredSize(new Dimension(JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE,
				JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE));		
		this.jAddButton.setBorder(new EtchedBorder());
		this.jAddButton.addActionListener(this);
		
		this.jModifyButton = new JButton(JAdminContentPanel.MODIFY_BUTTON_TEXT);
		this.jModifyButton.setPreferredSize(new Dimension(JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE,
				JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE));		
		this.jModifyButton.setBorder(new EtchedBorder());
		this.jModifyButton.addActionListener(this);
		
		this.jDeleteButton = new JButton(JAdminContentPanel.DELETE_BUTTON_TEXT);
		this.jDeleteButton.setPreferredSize(new Dimension(JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE,
				JAdminContentPanel.CONTROLPANEL_BUTTON_SIZE));		
		this.jDeleteButton.setBorder(new EtchedBorder());
		this.jDeleteButton.addActionListener(this);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		controlPanel.setBorder(new EtchedBorder());
		controlPanel.add(this.jSaveButton);
		controlPanel.add(this.jLoadButton);
		controlPanel.add(this.jAddButton);
		controlPanel.add(this.jModifyButton);
		controlPanel.add(this.jDeleteButton);
		return controlPanel;
	}
	
	private JMenuBar adminMenuBar () {
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
	
//	private ArrayList<String>getImageName(ArrayList<String> imagePath) {
//		ArrayList<String>imageName = new ArrayList<String>();
//		for(int i = 0;i<imagePath.size();i++)
//		{
//			String s = imagePath.get(i);
//			imageName.add(s.substring(s.lastIndexOf(File.separator)+1));
//		}		
//		return imageName;
//	}
//	
//	private String getFileName(String string) {
//		String fileName = string.substring(string.indexOf(START_TO_PATH_STRING)); //questo per media/..
//		return fileName;
//	}
	
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
			newFile = new File (NEW_IMAGE_FOLDER + newFileName + "_" + i + extension);
		} while (newFile.exists());
		return newFile;
	}
	
//	private ArrayList<String> copyImage(ArrayList<Image> img,ArrayList<String> string) {
//		int check = 0;
//		ArrayList<String> returnImage = new ArrayList<String>();
//		for(int i = 0;i<img.size();i++)
//		{
//			Image singleImg = img.get(i);
//			String s = string.get(i);
//			BufferedImage buffered = (BufferedImage) singleImg;
//			try {
//			    // save to file
//			    File outputfile = new File(NEW_IMAGE_FOLDER+s);
//			    do
//			    {
//			    	if(outputfile.exists())
//				    {
//				    	String name = s.substring(0, s.lastIndexOf("."));
//				    	String extension = s.substring(s.lastIndexOf("."));
//				    	s = name + "1" + extension;
//				    	outputfile = new File(NEW_IMAGE_FOLDER+s);
//				    }
//			    	else
//			    	{
//			    		check = 1;
//			    	}
//			    } while(check != 1);
//			    
//			    ImageIO.write(buffered, "png", outputfile);
//			} catch (IOException f) {
//			    f.printStackTrace();
//			}
//			returnImage.add(RETURN_NEW_IMAGE_PATH + s);
//		}
//		return returnImage;
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.jSaveButton) || e.getSource().equals(this.saveMenuItem)) {	
			JFileChooser fc = new JFileChooser(new File(FILE_CHOOSER_OPEN_DIRECTORY));
			fc.setDialogTitle(FILE_CHOOSER_SAVE_TITLE);
			fc.setApproveButtonText(FILE_CHOOSER_SAVE_BUTTON_TEXT);
			fc.setFileFilter(new StoreFileFilter());
			if(fc.showOpenDialog(this) == 0) {
//				/* CONTROLLO INCROCIATO */ 
//				for(int i = 0;i<imageToSave.size();i++) {
//					String img = imageToSave.get(i).getImmagine().toString();
//					for(int j = 0;j<imageToDeleteFromMod.size();j++) {
//						if(imageToDeleteFromMod.get(j).toString().equals(img)) {
//							imageToDeleteFromMod.remove(j);
//							imageToSave.remove(i);
//						}
//						
//					}		
//				}
//				/* controllo immagini doppie */
//				for(int i = 0;i<imageToSave.size();i++) {
//					String img = imageToSave.get(i).getImmagine().toString();
//					for(int j = 0;j<imageToSaveFromMod.size();j++) {
//						String imgMod = imageToSaveFromMod.get(j).getImmagine().toString();
//						if(imgMod.equals(img)) {
//							imageToSave.remove(i);
//							imageToDeleteFromMod.remove(j);
//						}						
//					}		
//				}				
//				/* controllo immagini doppie modifica */
//				for(int i = 0;i<imageToSaveFromMod.size();i++) {
//					String img = imageToSaveFromMod.get(i).getImmagine().toString();
//					for(int j = 0;j<imageToDeleteFromMod.size();j++) {
//						if(imageToDeleteFromMod.get(j).toString().equals(img)) {
//							imageToDeleteFromMod.remove(j);
//							imageToSaveFromMod.remove(i);
//						}
//					}		
//				}	
//				/* SALVATAGGIO IMMAGINE */
//				if(imageToSave.isEmpty() == false) {
//					for(int i = 0;i<imageToSave.size();i++) {
//						imagePath.add(imageToSave.get(i).getImmagine().toString());
//					}
//					imageName = getImageName(imagePath);
//					newPath = copyImage(image,imageName);
//					for(int i = 0;i<imageToSave.size();i++) {
//						imageToSave.get(i).setImmagine(newPath.get(i));
//					}
//				}
				/* FINE */
				/* CANCELLAZIONE IMMAGINI DA DELETE*/
//				if(imageToDelete.isEmpty() == false) {
//					for(int i = 0;i<imageToDelete.size();i++) {
//						if(imageToDelete.get(i).toString().equals(Prodotto.IMMAGINE_DEFAULT)) {
//							System.out.println(" ");
//						}
//						else {
//							File file = new File(imageToDelete.get(i).toString());
//							if (file.exists()) {
//							    file.delete();
//							}
//						}					
//					}
//				}			
//				/* CANCELLAZIONE IMMAGINI DA MODIFY */			
//				if(imageToDeleteFromMod.isEmpty() == false) {
//					for(int i = 0;i<imageToDeleteFromMod.size();i++) {
//						if(imageToDeleteFromMod.isEmpty() == false) {
//							if(imageToDeleteFromMod.get(i).toString().equals(Prodotto.IMMAGINE_DEFAULT)) {
//								imageToDeleteFromMod.remove(i);
//								i = 0;						
//							}
//						}
//						else {
//							i = imageToDeleteFromMod.size();
//						}
//					}
//				}
//				if(imageToDeleteFromMod.isEmpty() == false) {
//					for(int i = 0;i<imageToDeleteFromMod.size();i++) {
//						File file = new File(imageToDeleteFromMod.get(i).toString());
//						if (file.exists()) {
//						    file.delete();
//						}
//					}
//				}
//				/* AGGIUNTA IMMAGINI DA MODIFY */
//				if(imageToSaveFromMod.isEmpty() == false) {
//					for(int i = 0;i<imageToSaveFromMod.size();i++) {
//						imagePathMod.add(imageToSaveFromMod.get(i).getImmagine().toString());
//					}
//					imageNameMod = getImageName(imagePathMod);
//					newPathMod = copyImage(imageFromMod,imageNameMod);
//					for(int i = 0;i<imageToSaveFromMod.size();i++) {
//						imageToSaveFromMod.get(i).setImmagine(newPathMod.get(i));
//					}
//				}
				
//				RIMOZIONE IMMAGINI INUTILIZZATE
				for (File immagine : this.imagesToRemove) {
					if (immagine.exists()) {
						immagine.delete();
					}
				}
//				AGGIUNTA NUOVE IMMAGINI
				for (Prodotto articolo : this.articlesAdded) {
					File newFile = this.getNewImageFile(articolo.getImmagine());
					try {
						JAdminContentPanel.copyImage(articolo.getImmagine(), newFile);
						articolo.setImmagine(newFile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
//				PULISCO LISTE
				this.imagesToRemove.clear();
				this.articlesAdded.clear();
				
				/* Azzeramento array */
//				imageToSave.clear();
//				imageToDelete.clear();
//				imagePath.clear();
//				imageName.clear();
//				image.clear();
//				imageToSaveFromMod.clear();
//				imageFromMod.clear();
//				imageToDeleteFromMod.clear();
//				newPath.clear();
//				imagePathMod.clear();
//				imageNameMod.clear();
//				newPathMod.clear();
//				/* FINE CANCELLAZIONI IMMAGINI */
				store.salvaMagazzino(fc.getSelectedFile());
				BufferedWriter lastStoreFile = null;
		        try {
		        	lastStoreFile = new BufferedWriter(new FileWriter(new File(LAST_STORE_FILE_PATH)));
		            String filePath = fc.getSelectedFile().toString();
		            lastStoreFile.write(filePath);
		        	lastStoreFile.close();
		        } catch ( IOException g ) {
		            g.printStackTrace();
		        }
			}
	    }
		else if(e.getSource().equals(this.jLoadButton) || e.getSource().equals(this.loadMenuItem)) {			
			JFileChooser fc = new JFileChooser(new File(FILE_CHOOSER_OPEN_DIRECTORY));
			fc.setFileFilter(new StoreFileFilter());
			fc.setDialogTitle(FILE_CHOOSER_LOAD_TITLE);
			fc.setApproveButtonText(FILE_CHOOSER_LOAD_BUTTON_TEXT);
			if(fc.showOpenDialog(this) == 0) {
				this.store.caricaMagazzino(fc.getSelectedFile());
				this.jStoreTable = new JStoreTable(this.store);
//				jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(),
//						LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
//				jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
//				jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
//				this.store.getArticoli(), LINE_HEIGHT));
			}			
		}
		else if(e.getSource().equals(this.jAddButton) || e.getSource().equals(this.addMenuItem)) {
			JAddProductToStoreDialog jAddProductDialog = new JAddProductToStoreDialog(this.mainFrame, this.store, this.jStoreTable, this.articlesAdded);
			jAddProductDialog.setVisible(true);
		} 
		else if(e.getSource().equals(this.jModifyButton) || e.getSource().equals(this.modifyMenuItem)) {
			JSelectProductToModifyDialog jSearchProductDialog = new JSelectProductToModifyDialog(this.mainFrame, this.store, this.jStoreTable, this.articlesAdded, this.imagesToRemove);
			jSearchProductDialog.setVisible(true);
		}
		else if(e.getSource().equals(this.jDeleteButton) || e.getSource().equals(this.deleteMenuItem)) {
			JRemoveProductFromStoreDialog jDeleteProductDialog = new JRemoveProductFromStoreDialog(this.mainFrame, this.store, this.jStoreTable, this.imagesToRemove);
			jDeleteProductDialog.setVisible(true);
		}
		else if (e.getSource().equals(this.closeMenuItem)) {
			this.mainFrame.dispose();
		}
//		if(e.getActionCommand().equals(SAVE_MENU_ITEM_ACTION_COMMAND_TEXT))
//		{	
//			JFileChooser fc = new JFileChooser(new File("media/saves"));
//			fc.setDialogTitle("Salva");
//			fc.setApproveButtonText("Salva");
//			/* IMPOSTO LA VISUALIZZAZIONE SOLO DI FILE .MAG */
//			fc.setFileFilter(new FileFilter() {
//		        public boolean accept(File f) {
//		            if (f.isDirectory()) {
//		                return true;
//		            }
//		            final String name = f.getName();
//		            return name.endsWith(".mag");
//		        }
//		        @Override
//		        public String getDescription() {
//		            return "*.mag";
//		        }
//		    });
//			int returnVal = fc.showOpenDialog(this);
//			if(returnVal == 0)
//			{
//				
//				/* controllo immagini doppie */
//				for(int i = 0;i<imageToSave.size();i++)
//				{
//					String img = imageToSave.get(i).getImmagine().toString();
//					for(int j = 0;j<imageToDelete.size();j++)
//					{
//						if(imageToDelete.get(j).toString().equals(img))
//						{
//							imageToDelete.remove(j);
//							imageToSave.remove(i);
//						}
//						
//					}		
//				}
//				
//
//				
//				/* controllo immagini doppie modifica */
//				for(int i = 0;i<imageToSaveFromMod.size();i++)
//				{
//					String img = imageToSaveFromMod.get(i).getImmagine().toString();
//					for(int j = 0;j<imageToDeleteFromMod.size();j++)
//					{
//						if(imageToDeleteFromMod.get(j).toString().equals(img))
//						{
//							imageToDeleteFromMod.remove(j);
//							imageToSaveFromMod.remove(i);
//						}
//						
//					}		
//				}
//	
//				/* SALVATAGGIO IMMAGINE */
//				if(imageToSave.isEmpty() == false)
//				{
//					for(int i = 0;i<imageToSave.size();i++)
//					{
//						imagePath.add(imageToSave.get(i).getImmagine().toString());
//					}
//					imageName = getImageName(imagePath);
//					newPath = copyImage(image,imageName);
//					for(int i = 0;i<imageToSave.size();i++)
//					{
//						imageToSave.get(i).setImmagine(newPath.get(i));
//					}
//				}
//				/* FINE */
//			
//			/* CANCELLAZIONE IMMAGINI DA DELETE*/
//			if(imageToDelete.isEmpty() == false)
//			{
//				for(int i = 0;i<imageToDelete.size();i++)
//				{
//					if(imageToDelete.get(i).toString().equals("media\\img\\immagine_non_disponibile.jpg"))
//					{
//						System.out.println(" ");
//					}
//					else
//					{
//						File file = new File(imageToDelete.get(i).toString());
//						if (file.exists()) {
//						    file.delete();
//						}
//					}
//					
//				}
//			}
//			/* AGGIUNTA IMMAGINI DA MODIFY */
//			if(imageToSaveFromMod.isEmpty() == false)
//			{
//				for(int i = 0;i<imageToSaveFromMod.size();i++)
//				{
//					imagePathMod.add(imageToSaveFromMod.get(i).getImmagine().toString());
//				}
//				imageNameMod = getImageName(imagePathMod);
//				newPathMod = copyImage(imageFromMod,imageNameMod);
//				for(int i = 0;i<imageToSaveFromMod.size();i++)
//				{
//					imageToSaveFromMod.get(i).setImmagine(newPathMod.get(i));
//				}
//
//			}
//			
//			/* CANCELLAZIONE IMMAGINI DA MODIFY */
//			
//			if(imageToDeleteFromMod.isEmpty() == false)
//			{
//				for(int i = 0;i<imageToDeleteFromMod.size();i++)
//				{
//					if(imageToDeleteFromMod.isEmpty() == false)
//					{
//						if(imageToDeleteFromMod.get(i).toString().equals("media\\img\\immagine_non_disponibile.jpg"))
//						{
//							imageToDeleteFromMod.remove(i);
//							i = 0;						
//						}
//					}
//					else
//					{
//						i = imageToDeleteFromMod.size();
//					}
//				}
//			}
//			
//
//			
//			if(imageToDeleteFromMod.isEmpty() == false)
//			{
//				for(int i = 0;i<imageToDeleteFromMod.size();i++)
//				{
//					File file = new File(imageToDeleteFromMod.get(i).toString());
//					if (file.exists()) {
//					    file.delete();
//					}
//				}
//			}
//			
//			/* fine decommentare
//	
//			/* Azzeramento array */			
//			imageToSave.clear();
//			imageToDelete.clear();
//			imagePath.clear();
//			imageName.clear();
//			image.clear();
//			imageToSaveFromMod.clear();
//			imageFromMod.clear();
//			imageToDeleteFromMod.clear();
//			newPath.clear();
//			imagePathMod.clear();
//			imageNameMod.clear();
//			newPathMod.clear();
//			imageNameModRemove.clear();
//			imagePathModRemove.clear();
//			
//
//			/* FINE CANCELLAZIONI IMMAGINI */
//				store.salvaMagazzino(fc.getSelectedFile());
//				BufferedWriter output = null;
//		        try {
//		            File file = new File("media/saves/config");
//		            output = new BufferedWriter(new FileWriter(file));
//		            String fileName = getFileName(fc.getSelectedFile().toString());
//		            output.write(fileName);
//		        } catch ( IOException g ) {
//		            g.printStackTrace();
//		        } 
//		        try {
//					output.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//	    }
	}
	
	private static void copyImage(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}
	
	class JStoreTable extends JTable implements JProductsTable
	{
		private static final long serialVersionUID = 4734550632778588769L;
		
		private Magazzino magazzino;
		
		private static final int LINE_HEIGHT = 100;
		//private static final String AMOUNT_COLUMN = "Quantità";
		private static final String BUTTON_COLUMN = "";
		
		public JStoreTable(Magazzino magazzino)
		{
			this.magazzino = magazzino;
			
			this.setModel(new ArticlesTableModel(this.magazzino.getArticoli(),
					LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
			this.setRowHeight(LINE_HEIGHT);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
			this.setDefaultRenderer(String.class, centerRenderer);
			this.setBackground(null);
			this.getColumn(BUTTON_COLUMN).setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
			this.getColumn(BUTTON_COLUMN).setCellEditor(new RemoveColumnEditor(
					this.magazzino.getArticoli(), LINE_HEIGHT));
			
			this.setFocusable(false);
			this.setRowSelectionAllowed(false);
		}

		@Override
		public Prodotto getProductAtRow(int row) {
			return this.magazzino.getArticoli().get(row);
		}
		
		public JStoreTable getTable()
		{
			return jStoreTable;
		}
	}
}