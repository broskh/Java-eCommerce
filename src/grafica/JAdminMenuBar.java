package grafica;

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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JAdminMenuBar extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 8251356911457819974L;
	
	private JFrame mainFrame;
	private JStoreTable jStoreTable;
	private Magazzino magazzino;
	
	private JMenu fileMenu;
	private JMenu managementMenu;
	private JMenuItem closeMenuItem;
	private JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem addMenuItem;
	private JMenuItem modifyMenuItem;
	private JMenuItem deleteMenuItem;

	
	private static final int LINE_HEIGHT = 100;
	
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
	private static final String NEW_IMAGE_PATH = "media/img/products/";
	private static final String RETURN_NEW_IMAGE_PATH = "media\\img\\products\\";
	
	private static final String FILE_SAVE_STRING = "media/saves/save21.mag";
	
	
	public static ArrayList<Prodotto> imageToSave = new ArrayList<Prodotto>();
	public static ArrayList<File> imageToDelete = new ArrayList<File>();
	public static ArrayList<Image> image = new ArrayList<Image>();

	
	public static ArrayList<Prodotto> imageToSaveFromMod = new ArrayList<Prodotto>();
	public static ArrayList<Image> imageFromMod = new ArrayList<Image>();
	public static ArrayList<File> imageToDeleteFromMod = new ArrayList<File>();
	
	private ArrayList<String> imagePath = new ArrayList<String>();
	private ArrayList<String> imageName = new ArrayList<String>();
	private ArrayList<String> newPath = new ArrayList<String>();
	
	private ArrayList<String> imagePathMod = new ArrayList<String>();
	private ArrayList<String> imageNameMod = new ArrayList<String>();
	private ArrayList<String> newPathMod = new ArrayList<String>();
	
	private ArrayList<String> imageNameModRemove = new ArrayList<String>();
	private ArrayList<String> imagePathModRemove = new ArrayList<String>();
	
	

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
	
	
	public ArrayList<String>getImageName(ArrayList<String> imagePath)
	{
		ArrayList<String>imageName = new ArrayList<String>();
		for(int i = 0;i<imagePath.size();i++)
		{
			String s = imagePath.get(i);
			imageName.add(s.substring(s.lastIndexOf(File.separator)+1));
		}		
		return imageName;
	}
	
	public ArrayList<String> copyImage(ArrayList<Image> img,ArrayList<String> string)
	{
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
	
	
	public void setProductsImage(ArrayList<Image> img,ArrayList<Prodotto> prod)
	{
		for(int i = 0;i<img.size();i++)
		{
			Prodotto p = prod.get(i);
			Image singleImg = img.get(i);
			p.setImmagine(singleImg.toString());
		}
	}
	
	
	
	public String getFileName(String string)
	{
		String fileName = string.substring(string.indexOf("media")); //questo per media/..
		return fileName;
	}
	
	public void emptyList(ArrayList l)
	{
		for(int i = 0;i<l.size();i++)
		{
			l.remove(i);
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e)
	{
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
			emptyList(imageToSave);
			emptyList(imageToDelete);
			emptyList(imagePath);
			emptyList(imageName);
			emptyList(image);
			emptyList(imageToSaveFromMod);
			emptyList(imageFromMod);
			emptyList(imageToDeleteFromMod);
			emptyList(newPath);
			emptyList(imagePathMod);
			emptyList(imageNameMod);
			emptyList(newPathMod);
			emptyList(imageNameModRemove);
			emptyList(imagePathModRemove);
			

			/* FINE CANCELLAZIONI IMMAGINI */
				magazzino.salvaMagazzino(fc.getSelectedFile());
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
					// TODO Auto-generated catch block
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
				magazzino.caricaMagazzino(fc.getSelectedFile());
				jStoreTable.setModel(new ArticlesTableModel(this.magazzino.getArticoli(), LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
				jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
				jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
				this.magazzino.getArticoli(), LINE_HEIGHT));
//				((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
			}			
		}
		if(e.getActionCommand().equals(ADD_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JAddProductDialog jAddProductDialog = new JAddProductDialog(mainFrame,magazzino,jStoreTable);
			jAddProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(MODIFY_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JSearchProductDialog jSearchProductDialog = new JSearchProductDialog(mainFrame,magazzino,jStoreTable);
			jSearchProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(DELETE_MENU_ITEM_ACTION_COMMAND_TEXT))
		{
			JDeleteProductDialog jDeleteProductDialog = new JDeleteProductDialog(mainFrame,magazzino,jStoreTable);
			jDeleteProductDialog.setVisible(true);
		}
	}
}

