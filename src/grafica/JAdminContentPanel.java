package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;

import negozio.Magazzino;  
import negozio.Prodotto;

public class JAdminContentPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -3936116522061776556L;

//	GENERICO
	private Magazzino store;
	
	private ArrayList<Prodotto> imageToSave = new ArrayList<Prodotto>();
	private ArrayList<File> imageToDelete = new ArrayList<File>();
	private ArrayList<Image> image = new ArrayList<Image>();
	
	private ArrayList<Prodotto> imageToSaveFromMod = new ArrayList<Prodotto>();
	private ArrayList<Image> imageFromMod = new ArrayList<Image>();
	private ArrayList<File> imageToDeleteFromMod = new ArrayList<File>();
	
	private ArrayList<String> imagePath = new ArrayList<String>();
	private ArrayList<String> imageName = new ArrayList<String>();
	private ArrayList<String> newPath = new ArrayList<String>();
	
	private ArrayList<String> imagePathMod = new ArrayList<String>();
	private ArrayList<String> imageNameMod = new ArrayList<String>();
	private ArrayList<String> newPathMod = new ArrayList<String>();
	
	private ArrayList<String> imageNameModRemove = new ArrayList<String>();
	private ArrayList<String> imagePathModRemove = new ArrayList<String>();

	private JStoreTable jStoreTable;

	private JFrame mainFrame;
	private JButton jSaveButton;
	private JButton jLoadButton;
	private JButton jAddButton;
	private JButton jModifyButton;
	private JButton jDeleteButton;

	private static final int TOP_MARGIN = 40;
	private static final int RIGHT_MARGIN = 40;
	private static final int LEFT_MARGIN = 40;
	
//	CONTROL PANEL
	private static final int LINE_HEIGHT = 100;
	private static final int BUTTON_HEIGHT = 80; //20
	private static final int BUTTON_WIDTH = 80;
	private static final int HORIZONTAL_MARGIN_LAYOUT = 0;
	private static final int VERTICAL_MARGIN_LAYOUT = 0;
	
	private static final String SAVE_BUTTON_TEXT = "Salva";
	private static final String LOAD_BUTTON_TEXT = "Carica";
	private static final String ADD_BUTTON_TEXT = "Aggiungi";
	private static final String MODIFY_BUTTON_TEXT = "Modifica";
	private static final String DELETE_BUTTON_TEXT = "Elimina";
	
	private static final String SAVE_BUTTON_ACTION_COMMAND_TEXT = "salva";
	private static final String LOAD_BUTTON_ACTION_COMMAND_TEXT = "carica";
	private static final String ADD_BUTTON_ACTION_COMMAND_TEXT = "aggiungi";
	private static final String MODIFY_BUTTON_ACTION_COMMAND_TEXT = "modifica";
	private static final String DELETE_BUTTON_ACTION_COMMAND_TEXT = "elimina";
	private static final String NEW_IMAGE_PATH = "media/img/products/";
	private static final String RETURN_NEW_IMAGE_PATH = "media\\img\\products\\";
	private static final String START_TO_PATH_STRING = "media";
	private static final String FILE_CHOOSER_OPEN_DIRECTORY = "media/saves";
	private static final String FILE_CHOOSER_SET_TEXT = "Salva";
	private static final String DEFAULT_IMAGE_PATH = "media\\img\\immagine_non_disponibile.jpg";
	private static final String FILE_CONFIG_PATH = "media/saves/config";
	
//	menubar	
	private JMenu fileMenu;
	private JMenu managementMenu;
	private JMenuItem closeMenuItem;
	private JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem addMenuItem;
	private JMenuItem modifyMenuItem;
	private JMenuItem deleteMenuItem;


	public static final int MENU_HEIGHT = 22;
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

	public JAdminContentPanel(JFrame mainFrame, Magazzino store)
	{
		this.store = store;  
		this.mainFrame = mainFrame;
		this.jStoreTable = new JStoreTable(this.store);
		this.setLayout(new BorderLayout());
		this.add(this.adminMenuBar(), BorderLayout.PAGE_START);
		this.add(this.contentPanel(), BorderLayout.CENTER);	
	}
	
	private JPanel contentPanel () {

//		this.jStoreTable = new JStoreTable(this.magazzino);
		
		JScrollPane scrollTable = new JScrollPane(this.jStoreTable);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
        JPanel jCartPanel = new JPanel (new BorderLayout());
		jCartPanel.add(Box.createVerticalStrut(TOP_MARGIN), BorderLayout.PAGE_START);
		jCartPanel.add(Box.createHorizontalStrut(LEFT_MARGIN), BorderLayout.WEST);
		jCartPanel.add(scrollTable, BorderLayout.CENTER);
		jCartPanel.add(Box.createHorizontalStrut(RIGHT_MARGIN), BorderLayout.EAST);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(this.controlPanel(), BorderLayout.PAGE_START);
		contentPanel.add(jCartPanel, BorderLayout.CENTER);
		return contentPanel;
	}
	
	private JPanel controlPanel () {
		this.setLayout(new FlowLayout(FlowLayout.LEADING,JAdminContentPanel.HORIZONTAL_MARGIN_LAYOUT,
				JAdminContentPanel.VERTICAL_MARGIN_LAYOUT));

		this.jSaveButton = new JButton(JAdminContentPanel.SAVE_BUTTON_TEXT);
		this.jSaveButton.setPreferredSize(new Dimension(JAdminContentPanel.BUTTON_WIDTH,
				JAdminContentPanel.BUTTON_HEIGHT));
		
		this.jSaveButton.setBorder(new EtchedBorder());
		this.jSaveButton.setActionCommand(SAVE_BUTTON_ACTION_COMMAND_TEXT);
		this.jSaveButton.addActionListener(this);
		
		this.jLoadButton = new JButton(JAdminContentPanel.LOAD_BUTTON_TEXT);
		this.jLoadButton.setPreferredSize(new Dimension(JAdminContentPanel.BUTTON_WIDTH,
				JAdminContentPanel.BUTTON_HEIGHT));
		
		this.jLoadButton.setBorder(new EtchedBorder());
		this.jLoadButton.setActionCommand(LOAD_BUTTON_ACTION_COMMAND_TEXT);
		this.jLoadButton.addActionListener(this);
		
		this.jAddButton = new JButton(JAdminContentPanel.ADD_BUTTON_TEXT);
		this.jAddButton.setPreferredSize(new Dimension(JAdminContentPanel.BUTTON_WIDTH,
				JAdminContentPanel.BUTTON_HEIGHT));
		
		this.jAddButton.setBorder(new EtchedBorder());
		this.jAddButton.setActionCommand(ADD_BUTTON_ACTION_COMMAND_TEXT);
		this.jAddButton.addActionListener(this);
		
		this.jModifyButton = new JButton(JAdminContentPanel.MODIFY_BUTTON_TEXT);
		this.jModifyButton.setPreferredSize(new Dimension(JAdminContentPanel.BUTTON_WIDTH,
				JAdminContentPanel.BUTTON_HEIGHT));
		
		this.jModifyButton.setBorder(new EtchedBorder());
		this.jModifyButton.setActionCommand(MODIFY_BUTTON_ACTION_COMMAND_TEXT);
		this.jModifyButton.addActionListener(this);
		
		this.jDeleteButton = new JButton(JAdminContentPanel.DELETE_BUTTON_TEXT);
		this.jDeleteButton.setPreferredSize(new Dimension(JAdminContentPanel.BUTTON_WIDTH,
				JAdminContentPanel.BUTTON_HEIGHT));
		
		this.jDeleteButton.setBorder(new EtchedBorder());
		this.jDeleteButton.setActionCommand(DELETE_BUTTON_ACTION_COMMAND_TEXT);
		this.jDeleteButton.addActionListener(this);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new EtchedBorder());
		controlPanel.add(this.jSaveButton);
		controlPanel.add(this.jLoadButton);
		controlPanel.add(this.jAddButton);
		controlPanel.add(this.jModifyButton);
		controlPanel.add(this.jDeleteButton);
		return controlPanel;
	}
	
	private JMenuBar adminMenuBar () {
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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(this.getWidth(), MENU_HEIGHT));
		menuBar.add(this.fileMenu);
		menuBar.add(this.managementMenu);
		return menuBar;
	}
	
	class JStoreTable extends JTable implements JProductTable
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
//			this.getColumn(AMOUNT_COLUMN).setCellRenderer(new AmountColumnRender(LINE_HEIGHT));
//			this.getColumn(AMOUNT_COLUMN).setCellEditor(new AmountColumnEditor((ArticlesTableModel) this.getModel(), this.carrello, this.magazzino, LINE_HEIGHT));
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
	
	private ArrayList<String>getImageName(ArrayList<String> imagePath) {
		ArrayList<String>imageName = new ArrayList<String>();
		for(int i = 0;i<imagePath.size();i++)
		{
			String s = imagePath.get(i);
			imageName.add(s.substring(s.lastIndexOf(File.separator)+1));
		}		
		return imageName;
	}
	
	private String getFileName(String string) {
		String fileName = string.substring(string.indexOf(START_TO_PATH_STRING)); //questo per media/..
		return fileName;
	}
	
	private ArrayList<String> copyImage(ArrayList<Image> img,ArrayList<String> string) {
		int check = 0;
		ArrayList<String> returnImage = new ArrayList<String>();
		for(int i = 0;i<img.size();i++)
		{
			Image singleImg = img.get(i);
			String s = string.get(i);
			BufferedImage buffered = (BufferedImage) singleImg;
			try {
			    // save to file
			    File outputfile = new File(NEW_IMAGE_PATH+s);
			    do
			    {
			    	if(outputfile.exists())
				    {
				    	String name = s.substring(0, s.lastIndexOf("."));
				    	String extension = s.substring(s.lastIndexOf("."));
				    	s = name + "1" + extension;
				    	outputfile = new File(NEW_IMAGE_PATH+s);
				    }
			    	else
			    	{
			    		check = 1;
			    	}
			    } while(check != 1);
			    
			    ImageIO.write(buffered, "png", outputfile);
			} catch (IOException f) {
			    f.printStackTrace();
			}
			returnImage.add(RETURN_NEW_IMAGE_PATH + s);
		}
		return returnImage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(SAVE_BUTTON_ACTION_COMMAND_TEXT))
		{	
			
			
			System.out.println("imagetosave: "+imageToSave);
			System.out.println("imagetodelete: "+imageToDelete);
			System.out.println("imagetosavefrommod: "+imageToSaveFromMod);
			System.out.println("imagetodeletefrommod: "+imageToDeleteFromMod);
			
			
			
			JFileChooser fc = new JFileChooser(new File(FILE_CHOOSER_OPEN_DIRECTORY));
			fc.setDialogTitle(FILE_CHOOSER_SET_TEXT);
			fc.setApproveButtonText(FILE_CHOOSER_SET_TEXT);
			/* IMPOSTO LA VISUALIZZAZIONE SOLO DI FILE .MAG */
			fc.setFileFilter(new FileFilter() {
		        public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            }
		            final String name = f.getName();
		            return name.endsWith(".mag");
		        }
		        @Override
		        public String getDescription() {
		            return "*.mag";
		        }
		    });
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == 0)
			{
				
				/* CONTROLLO INCROCIATO */
				for(int i = 0;i<imageToSave.size();i++)
				{
					String img = imageToSave.get(i).getImmagine().toString();
					for(int j = 0;j<imageToDeleteFromMod.size();j++)
					{
						if(imageToDeleteFromMod.get(j).toString().equals(img))
						{
							imageToDeleteFromMod.remove(j);
							imageToSave.remove(i);
						}
						
					}		
				}
				
				
				
				/* controllo immagini doppie */
				for(int i = 0;i<imageToSave.size();i++)
				{
					String img = imageToSave.get(i).getImmagine().toString();
					for(int j = 0;j<imageToSaveFromMod.size();j++)
					{
						String imgMod = imageToSaveFromMod.get(j).getImmagine().toString();
						if(imgMod.equals(img))
						{
							imageToSave.remove(i);
							imageToDeleteFromMod.remove(j);
						}
						
					}		
				}
				
				System.out.println("post imagetosave: "+imageToSave);
				System.out.println("post imagetodelete: "+imageToDelete);
				System.out.println("post imagetosavefrommod: "+imageToSaveFromMod);
				System.out.println("post imagetodeletefrommod: "+imageToDeleteFromMod);

				
				/* controllo immagini doppie modifica */
				for(int i = 0;i<imageToSaveFromMod.size();i++)
				{
					String img = imageToSaveFromMod.get(i).getImmagine().toString();
					for(int j = 0;j<imageToDeleteFromMod.size();j++)
					{
						if(imageToDeleteFromMod.get(j).toString().equals(img))
						{
							imageToDeleteFromMod.remove(j);
							imageToSaveFromMod.remove(i);
						}
						
					}		
				}
	
				/* SALVATAGGIO IMMAGINE */
				if(imageToSave.isEmpty() == false)
				{
					for(int i = 0;i<imageToSave.size();i++)
					{
						imagePath.add(imageToSave.get(i).getImmagine().toString());
					}
					imageName = getImageName(imagePath);
					newPath = copyImage(image,imageName);
					for(int i = 0;i<imageToSave.size();i++)
					{
						imageToSave.get(i).setImmagine(newPath.get(i));
					}
				}
				/* FINE */
			
			/* CANCELLAZIONE IMMAGINI DA DELETE*/
			if(imageToDelete.isEmpty() == false)
			{
				for(int i = 0;i<imageToDelete.size();i++)
				{
					if(imageToDelete.get(i).toString().equals(DEFAULT_IMAGE_PATH))
					{
						System.out.println(" ");
					}
					else
					{
						File file = new File(imageToDelete.get(i).toString());
						if (file.exists()) {
						    file.delete();
						}
					}
					
				}
			}
			
			/* CANCELLAZIONE IMMAGINI DA MODIFY */
			
			if(imageToDeleteFromMod.isEmpty() == false)
			{
				for(int i = 0;i<imageToDeleteFromMod.size();i++)
				{
					if(imageToDeleteFromMod.isEmpty() == false)
					{
						if(imageToDeleteFromMod.get(i).toString().equals(DEFAULT_IMAGE_PATH))
						{
							imageToDeleteFromMod.remove(i);
							i = 0;						
						}
					}
					else
					{
						i = imageToDeleteFromMod.size();
					}
				}
			}
			

			
			if(imageToDeleteFromMod.isEmpty() == false)
			{
				for(int i = 0;i<imageToDeleteFromMod.size();i++)
				{
					File file = new File(imageToDeleteFromMod.get(i).toString());
					if (file.exists()) {
					    file.delete();
					}
				}
			}
			
			/* AGGIUNTA IMMAGINI DA MODIFY */
			if(imageToSaveFromMod.isEmpty() == false)
			{
				for(int i = 0;i<imageToSaveFromMod.size();i++)
				{
					imagePathMod.add(imageToSaveFromMod.get(i).getImmagine().toString());
				}
				imageNameMod = getImageName(imagePathMod);
				newPathMod = copyImage(imageFromMod,imageNameMod);
				for(int i = 0;i<imageToSaveFromMod.size();i++)
				{
					imageToSaveFromMod.get(i).setImmagine(newPathMod.get(i));
				}

			}
			

			
			/* fine decommentare
	
			/* Azzeramento array */		
		
//				emptyList(imageToSave);
//				emptyList(imageToDelete);
//				emptyList(imagePath);
//				emptyList(imageName);
//				emptyList(image);
//				emptyList(imageToSaveFromMod);
//				emptyList(imageFromMod);
//				emptyList(imageToDeleteFromMod);
//				emptyList(newPath);
//				emptyList(imagePathMod);
//				emptyList(imageNameMod);
//				emptyList(newPathMod);
//				emptyList(imageNameModRemove);
//				emptyList(imagePathModRemove);
			imageToSave.clear();
			imageToDelete.clear();
			imagePath.clear();
			imageName.clear();
			image.clear();
			imageToSaveFromMod.clear();
			imageFromMod.clear();
			imageToDeleteFromMod.clear();
			newPath.clear();
			imagePathMod.clear();
			imageNameMod.clear();
			newPathMod.clear();
			imageNameModRemove.clear();
			imagePathModRemove.clear();
			
			

			/* FINE CANCELLAZIONI IMMAGINI */
				store.salvaMagazzino(fc.getSelectedFile());
				BufferedWriter output = null;
		        try {
		            File file = new File(FILE_CONFIG_PATH);
		            output = new BufferedWriter(new FileWriter(file));
		            String fileName = getFileName(fc.getSelectedFile().toString());
		            output.write(fileName);
		        } catch ( IOException g ) {
		            g.printStackTrace();
		        } 
		        try {
					output.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		        

			}
		
	    }
		if(e.getActionCommand().equals(LOAD_BUTTON_ACTION_COMMAND_TEXT))
		{
			
			JFileChooser fc = new JFileChooser(new File(FILE_CHOOSER_OPEN_DIRECTORY));
			/* IMPOSTO LA VISUALIZZAZIONE SOLO DI FILE .MAG */
			fc.setFileFilter(new FileFilter() {
		        public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            }
		            final String name = f.getName();
		            return name.endsWith(".mag");
		        }
		        @Override
		        public String getDescription() {
		            return "*.mag";
		        }
		    });
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == 0)
			{
				store.caricaMagazzino(fc.getSelectedFile());
				jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(),
						LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
				jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
				jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
				this.store.getArticoli(), LINE_HEIGHT));
//					((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
			}			
		}
		if(e.getActionCommand().equals(ADD_BUTTON_ACTION_COMMAND_TEXT))
		{
			JAddProductToStoreDialog jAddProductDialog = new JAddProductToStoreDialog(mainFrame,store,jStoreTable, imageToSave, image);
			jAddProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(MODIFY_BUTTON_ACTION_COMMAND_TEXT))
		{
			JSearchProductDialog jSearchProductDialog = new JSearchProductDialog(mainFrame,store,jStoreTable,imageToDeleteFromMod, 
					imageFromMod, imageToSave, imageToSaveFromMod);
			jSearchProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(DELETE_BUTTON_ACTION_COMMAND_TEXT))
		{
			JRemoveProductFromStoreDialog jDeleteProductDialog = new JRemoveProductFromStoreDialog(mainFrame,store,jStoreTable, imageToDelete);
			jDeleteProductDialog.setVisible(true);
		}
		
		
		
		
		
		
//		DA MENUBAR
		
		
		
		if(e.getActionCommand().equals(SAVE_MENU_ITEM_ACTION_COMMAND_TEXT))
		{	
			JFileChooser fc = new JFileChooser(new File("media/saves"));
			fc.setDialogTitle("Salva");
			fc.setApproveButtonText("Salva");
			/* IMPOSTO LA VISUALIZZAZIONE SOLO DI FILE .MAG */
			fc.setFileFilter(new FileFilter() {
		        public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            }
		            final String name = f.getName();
		            return name.endsWith(".mag");
		        }
		        @Override
		        public String getDescription() {
		            return "*.mag";
		        }
		    });
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == 0)
			{
				
				/* controllo immagini doppie */
				for(int i = 0;i<imageToSave.size();i++)
				{
					String img = imageToSave.get(i).getImmagine().toString();
					for(int j = 0;j<imageToDelete.size();j++)
					{
						if(imageToDelete.get(j).toString().equals(img))
						{
							imageToDelete.remove(j);
							imageToSave.remove(i);
						}
						
					}		
				}
				

				
				/* controllo immagini doppie modifica */
				for(int i = 0;i<imageToSaveFromMod.size();i++)
				{
					String img = imageToSaveFromMod.get(i).getImmagine().toString();
					for(int j = 0;j<imageToDeleteFromMod.size();j++)
					{
						if(imageToDeleteFromMod.get(j).toString().equals(img))
						{
							imageToDeleteFromMod.remove(j);
							imageToSaveFromMod.remove(i);
						}
						
					}		
				}
	
				/* SALVATAGGIO IMMAGINE */
				if(imageToSave.isEmpty() == false)
				{
					for(int i = 0;i<imageToSave.size();i++)
					{
						imagePath.add(imageToSave.get(i).getImmagine().toString());
					}
					imageName = getImageName(imagePath);
					newPath = copyImage(image,imageName);
					for(int i = 0;i<imageToSave.size();i++)
					{
						imageToSave.get(i).setImmagine(newPath.get(i));
					}
				}
				/* FINE */
			
			/* CANCELLAZIONE IMMAGINI DA DELETE*/
			if(imageToDelete.isEmpty() == false)
			{
				for(int i = 0;i<imageToDelete.size();i++)
				{
					if(imageToDelete.get(i).toString().equals("media\\img\\immagine_non_disponibile.jpg"))
					{
						System.out.println(" ");
					}
					else
					{
						File file = new File(imageToDelete.get(i).toString());
						if (file.exists()) {
						    file.delete();
						}
					}
					
				}
			}
			/* AGGIUNTA IMMAGINI DA MODIFY */
			if(imageToSaveFromMod.isEmpty() == false)
			{
				for(int i = 0;i<imageToSaveFromMod.size();i++)
				{
					imagePathMod.add(imageToSaveFromMod.get(i).getImmagine().toString());
				}
				imageNameMod = getImageName(imagePathMod);
				newPathMod = copyImage(imageFromMod,imageNameMod);
				for(int i = 0;i<imageToSaveFromMod.size();i++)
				{
					imageToSaveFromMod.get(i).setImmagine(newPathMod.get(i));
				}

			}
			
			/* CANCELLAZIONE IMMAGINI DA MODIFY */
			
			if(imageToDeleteFromMod.isEmpty() == false)
			{
				for(int i = 0;i<imageToDeleteFromMod.size();i++)
				{
					if(imageToDeleteFromMod.isEmpty() == false)
					{
						if(imageToDeleteFromMod.get(i).toString().equals("media\\img\\immagine_non_disponibile.jpg"))
						{
							imageToDeleteFromMod.remove(i);
							i = 0;						
						}
					}
					else
					{
						i = imageToDeleteFromMod.size();
					}
				}
			}
			

			
			if(imageToDeleteFromMod.isEmpty() == false)
			{
				for(int i = 0;i<imageToDeleteFromMod.size();i++)
				{
					File file = new File(imageToDeleteFromMod.get(i).toString());
					if (file.exists()) {
					    file.delete();
					}
				}
			}
			
			/* fine decommentare
	
			/* Azzeramento array */			
			imageToSave.clear();
			imageToDelete.clear();
			imagePath.clear();
			imageName.clear();
			image.clear();
			imageToSaveFromMod.clear();
			imageFromMod.clear();
			imageToDeleteFromMod.clear();
			newPath.clear();
			imagePathMod.clear();
			imageNameMod.clear();
			newPathMod.clear();
			imageNameModRemove.clear();
			imagePathModRemove.clear();
			

			/* FINE CANCELLAZIONI IMMAGINI */
				store.salvaMagazzino(fc.getSelectedFile());
				BufferedWriter output = null;
		        try {
		            File file = new File("media/saves/config");
		            output = new BufferedWriter(new FileWriter(file));
		            String fileName = getFileName(fc.getSelectedFile().toString());
		            output.write(fileName);
		        } catch ( IOException g ) {
		            g.printStackTrace();
		        } 
		        try {
					output.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		        

			}
		
	    }
		if(e.getActionCommand().equals(LOAD_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			
			JFileChooser fc = new JFileChooser(new File("media/saves"));
			/* IMPOSTO LA VISUALIZZAZIONE SOLO DI FILE .MAG */
			fc.setFileFilter(new FileFilter() {
		        public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            }
		            final String name = f.getName();
		            return name.endsWith(".mag");
		        }
		        @Override
		        public String getDescription() {
		            return "*.mag";
		        }
		    });
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == 0)
			{
				store.caricaMagazzino(fc.getSelectedFile());
				jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(), LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
				jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
				jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
				this.store.getArticoli(), LINE_HEIGHT));
//				((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
			}			
		}
		if(e.getActionCommand().equals(ADD_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JAddProductToStoreDialog jAddProductDialog = new JAddProductToStoreDialog(mainFrame,store,jStoreTable, imageToSave, image);
			jAddProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(MODIFY_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JSearchProductDialog jSearchProductDialog = new JSearchProductDialog(mainFrame,store,jStoreTable,imageToDeleteFromMod, 
					imageFromMod, imageToSave, imageToSaveFromMod);
			jSearchProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(DELETE_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JRemoveProductFromStoreDialog jDeleteProductDialog = new JRemoveProductFromStoreDialog(mainFrame,store,jStoreTable,imageToDelete);
			jDeleteProductDialog.setVisible(true);
		}
	}
}