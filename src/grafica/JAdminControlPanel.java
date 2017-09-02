package grafica;


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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JAdminControlPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6258711606843235850L;
	
	private JFrame mainFrame;
	private JStoreTable jStoreTable;


	private static final int LINE_HEIGHT = 100;
	protected static final int BUTTON_HEIGHT = 80; //20
	protected static final int BUTTON_WIDTH = 80;
	protected static final int HORIZONTAL_MARGIN_LAYOUT = 0;
	protected static final int VERTICAL_MARGIN_LAYOUT = 0;
	
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

	
	private Magazzino magazzino;
	
	private JButton jSaveButton;
	private JButton jLoadButton;
	private JButton jAddButton;
	private JButton jModifyButton;
	private JButton jDeleteButton;
	
	
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


	public JAdminControlPanel()
	{
		
	}
	

	public JAdminControlPanel(Magazzino magazzino,JFrame mainFrame,JStoreTable jStoreTable)
	{
		this.magazzino = magazzino;
		this.jStoreTable = jStoreTable;
		this.setLayout(new FlowLayout(FlowLayout.LEADING,JAdminControlPanel.HORIZONTAL_MARGIN_LAYOUT,
				JAdminControlPanel.VERTICAL_MARGIN_LAYOUT));

		this.jSaveButton = new JButton(JAdminControlPanel.SAVE_BUTTON_TEXT);
		this.jSaveButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jSaveButton.setBorder(new EtchedBorder());
		this.jSaveButton.setActionCommand(SAVE_BUTTON_ACTION_COMMAND_TEXT);
		this.jSaveButton.addActionListener(this);
		
		this.jLoadButton = new JButton(JAdminControlPanel.LOAD_BUTTON_TEXT);
		this.jLoadButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jLoadButton.setBorder(new EtchedBorder());
		this.jLoadButton.setActionCommand(LOAD_BUTTON_ACTION_COMMAND_TEXT);
		this.jLoadButton.addActionListener(this);
		
		this.jAddButton = new JButton(JAdminControlPanel.ADD_BUTTON_TEXT);
		this.jAddButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jAddButton.setBorder(new EtchedBorder());
		this.jAddButton.setActionCommand(ADD_BUTTON_ACTION_COMMAND_TEXT);
		this.jAddButton.addActionListener(this);
		
		this.jModifyButton = new JButton(JAdminControlPanel.MODIFY_BUTTON_TEXT);
		this.jModifyButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jModifyButton.setBorder(new EtchedBorder());
		this.jModifyButton.setActionCommand(MODIFY_BUTTON_ACTION_COMMAND_TEXT);
		this.jModifyButton.addActionListener(this);
		
		this.jDeleteButton = new JButton(JAdminControlPanel.DELETE_BUTTON_TEXT);
		this.jDeleteButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jDeleteButton.setBorder(new EtchedBorder());
		this.jDeleteButton.setActionCommand(DELETE_BUTTON_ACTION_COMMAND_TEXT);
		this.jDeleteButton.addActionListener(this);
		
		
		this.add(this.jSaveButton);
		this.add(this.jLoadButton);
		this.add(this.jAddButton);
		this.add(this.jModifyButton);
		this.add(this.jDeleteButton);
		
	}
	

	public void getImage(Image i)
	{
		image.add(i);
	}
	
	public void getImageFromMod(Image i)
	{
		imageFromMod.add(i);
	}
	public void addToDeleteListMod(File f)
	{
		imageToDeleteFromMod.add(f);
	}
	
	public void addToDeleteList(File f)
	{
		imageToDelete.add(f);
	}
	
	public void addToSaveList(Prodotto p)
	{
		imageToSave.add(p);
	}
	public void addToSaveListMod(Prodotto p)
	{
		imageToSaveFromMod.add(p);
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
		String fileName = string.substring(string.indexOf(START_TO_PATH_STRING)); //questo per media/..
		return fileName;
	}
	
	public void emptyList(ArrayList<?> l)
	{
		for(int i = 0;i<l.size();i++)
		{
			l.remove(i);
		}
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
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
		
//			emptyList(imageToSave);
//			emptyList(imageToDelete);
//			emptyList(imagePath);
//			emptyList(imageName);
//			emptyList(image);
//			emptyList(imageToSaveFromMod);
//			emptyList(imageFromMod);
//			emptyList(imageToDeleteFromMod);
//			emptyList(newPath);
//			emptyList(imagePathMod);
//			emptyList(imageNameMod);
//			emptyList(newPathMod);
//			emptyList(imageNameModRemove);
//			emptyList(imagePathModRemove);
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
				magazzino.salvaMagazzino(fc.getSelectedFile());
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
					// TODO Auto-generated catch block
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
				magazzino.caricaMagazzino(fc.getSelectedFile());
				jStoreTable.setModel(new ArticlesTableModel(this.magazzino.getArticoli(),
						LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
				jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
				jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
				this.magazzino.getArticoli(), LINE_HEIGHT));
//				((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
			}			
		}
		if(e.getActionCommand().equals(ADD_BUTTON_ACTION_COMMAND_TEXT))
		{
			JAddProductDialog jAddProductDialog = new JAddProductDialog(mainFrame,magazzino,jStoreTable);
			jAddProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(MODIFY_BUTTON_ACTION_COMMAND_TEXT))
		{
			JSearchProductDialog jSearchProductDialog = new JSearchProductDialog(mainFrame,magazzino,jStoreTable);
			jSearchProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(DELETE_BUTTON_ACTION_COMMAND_TEXT))
		{
			JDeleteProductDialog jDeleteProductDialog = new JDeleteProductDialog(mainFrame,magazzino,jStoreTable);
			jDeleteProductDialog.setVisible(true);
		}
	}

	
}

