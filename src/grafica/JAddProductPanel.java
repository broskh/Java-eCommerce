package grafica;


import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.PlainDocument;



import negozio.Magazzino;
import negozio.Prodotto;
import negozio.Promozione;
import negozio.ScontoPercentuale;
import negozio.ScontoTrePerDue;


public class JAddProductPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -6881271668480137290L;
	
	private JAddProductDialog jAddProductDialog;
	
	protected Prodotto prodotto;
	private Magazzino magazzino;
	
	/**/
	private JPanel addPanel;
	private JPanel offerPanel;
	private JPanel imagePanel;
	/**/
	
	private JLabel jCodeLabel;
	protected JTextField jCodeTextField;
	private JLabel jNameLabel;
	protected JTextField jNameTextField;
	private JLabel jBrandLabel;
	protected JTextField jBrandTextField;
	private JLabel jCategoryLabel;
	protected JTextField jCategoryTextField;
	private JLabel jPriceLabel;
	protected JTextField jPriceTextField;
	private JLabel jAmountLabel;
	protected JTextField jAmountTextField;
	private JLabel jOfferLabel;
	protected JRadioButton jNoOfferRadioButton;
	protected JRadioButton jPercentOfferRadioButton;
	protected JRadioButton jThreePerTwoOfferRadioButton;
	protected JTextField jPercentTextField;
	protected JButton jAddButton;
	protected JLabel jImageLabel; 
	protected JButton jImageButton;
		
	private JLabel jEmptyLabel1;
	private JLabel jEmptyLabel2;
	

	private static final int WIDTH_TEXTBOX = 10;
	private static final int N_LINES_ADD_PANEL = 6;
	private static final int N_COLUMNS_ADD_PANEL = 2;
	private static final int ICON_DIMENSION = 120;
	private static final int DIMENSION_VERTICAL_STRUT = 10;
	private static final int DIMENSION_HORIZONTAL_STRUT = 20;
	private static final int N_LINES_OFFER_PANEL = 4;
	private static final int N_COLUMNS_OFFER_PANEL = 2;
	
	private static final String CODE_LABEL_TEXT = "Codice:";
	private static final String NAME_LABEL_TEXT = "Nome:";
	private static final String BRAND_LABEL_TEXT = "Marca:";
	private static final String CATEGORY_LABEL_TEXT = "Categoria:";
	private static final String PRICE_LABEL_TEXT = "Prezzo:";
	private static final String AMOUNT_LABEL_TEXT = "Quantità: ";
	private static final String OFFER_LABEL_TEXT = "Offerta:";
	private static final String NO_OFFER_RADIO_BUTTON_TEXT = "No";
	private static final String PERCENT_OFFER_RADIO_BUTTON_TEXT = "Percentuale";
	private static final String THREE_PER_TWO_OFFER_RADIO_BUTTON_TEXT = "3x2";
	private static final String ADD_BUTTON_TEXT = "Aggiungi";
	private static final String IMAGE_BUTTON_TEXT =  "Cambia";
	private static final String EMPTY_LABEL_TEXT = "";
	private static final String ADD_BUTTON_ACTION_COMMAND_TEXT = "aggiungi";
	private static final String IMAGE_BUTTON_ACTION_COMMAND_TEXT = "cambia";
	private static final String NO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT = "nessuna";
	private static final String PERCENT_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT = "percentuale";
	private static final String THREE_PER_TWO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT = "3x2";
	
	private static final String DEFAULT_IMAGE = "media/img/products/immagine_non_disponibile.jpg";

	
	
	public JAddProductPanel(Magazzino magazzino, JAddProductDialog jAddProductDialog)
	{
		this.magazzino = magazzino;
		this.jAddProductDialog = jAddProductDialog;
		/* ROBA PER IMMAGINE */
		this.prodotto = new Prodotto("","","","",0,DEFAULT_IMAGE,0,null);
		this.jImageLabel = new JLabel("",SwingConstants.CENTER);
		
		ImageIcon icon = new ImageIcon(new ResizableIcon(this.prodotto.getImmagine()).
				resizeIcon(ICON_DIMENSION, ICON_DIMENSION),this.prodotto.getImmagine().toString());
		if(icon!=null)
		{
			this.jImageLabel.setIcon(icon);
		}
		
		imagePanel = new JPanel();
		this.jImageButton = new JButton(JAddProductPanel.IMAGE_BUTTON_TEXT);
		imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.X_AXIS));
		imagePanel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		imagePanel.add(this.jImageLabel);
		imagePanel.add(Box.createHorizontalStrut(DIMENSION_HORIZONTAL_STRUT));
		imagePanel.add(jImageButton);
		this.jImageButton.setActionCommand(IMAGE_BUTTON_ACTION_COMMAND_TEXT);
		this.jImageButton.addActionListener(this);
		imagePanel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		/* FINE ROBA PER IMMAGINE */
		
		this.jCodeLabel = new JLabel(JAddProductPanel.CODE_LABEL_TEXT);
		this.jCodeTextField = new JTextField(JAddProductPanel.WIDTH_TEXTBOX);
		this.jNameLabel = new JLabel(JAddProductPanel.NAME_LABEL_TEXT);
		this.jNameTextField = new JTextField(JAddProductPanel.WIDTH_TEXTBOX);
		this.jBrandLabel = new JLabel(JAddProductPanel.BRAND_LABEL_TEXT);
		this.jBrandTextField = new JTextField(JAddProductPanel.WIDTH_TEXTBOX);
		this.jCategoryLabel = new JLabel(JAddProductPanel.CATEGORY_LABEL_TEXT);
		this.jCategoryTextField = new JTextField(JAddProductPanel.WIDTH_TEXTBOX);
		this.jPriceLabel = new JLabel(JAddProductPanel.PRICE_LABEL_TEXT);
		this.jPriceTextField = new JTextField("0.00",JAddProductPanel.WIDTH_TEXTBOX);
		/* check TEXT FIELD price */
		PlainDocument docP = (PlainDocument)this.jPriceTextField.getDocument();
		docP.setDocumentFilter(new PriceDocumentFilter());
		
		this.jAmountLabel = new JLabel(JAddProductPanel.AMOUNT_LABEL_TEXT);
		this.jAmountTextField = new JTextField("0",JAddProductPanel.WIDTH_TEXTBOX);
		/* check TEXT FIELD amount */
		PlainDocument docQ = (PlainDocument)this.jAmountTextField.getDocument();
		docQ.setDocumentFilter(new AmountDocumentFilter());
		
		
		this.jOfferLabel = new JLabel(JAddProductPanel.OFFER_LABEL_TEXT);
		this.jPercentTextField = new JTextField(JAddProductPanel.WIDTH_TEXTBOX);
		/* check TEXT FIELD amount */
		PlainDocument docPC = (PlainDocument)this.jPercentTextField.getDocument();
		docPC.setDocumentFilter(new AmountDocumentFilter());
		
		this.jNoOfferRadioButton = new JRadioButton(JAddProductPanel.NO_OFFER_RADIO_BUTTON_TEXT);
		this.jPercentOfferRadioButton = new JRadioButton(JAddProductPanel.
				PERCENT_OFFER_RADIO_BUTTON_TEXT);
		
		this.jThreePerTwoOfferRadioButton = new JRadioButton(JAddProductPanel.
				THREE_PER_TWO_OFFER_RADIO_BUTTON_TEXT);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.jNoOfferRadioButton);
		buttonGroup.add(this.jPercentOfferRadioButton);
		buttonGroup.add(this.jThreePerTwoOfferRadioButton);
		this.jAddButton = new JButton(JAddProductPanel.ADD_BUTTON_TEXT);
		
		
		
		
		
		this.jEmptyLabel1 = new JLabel(JAddProductPanel.EMPTY_LABEL_TEXT);
		this.jEmptyLabel2 = new JLabel(JAddProductPanel.EMPTY_LABEL_TEXT);

		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		addPanel = new JPanel(new GridLayout(N_LINES_ADD_PANEL,N_COLUMNS_ADD_PANEL));
		addPanel.add(this.jCodeLabel);
		addPanel.add(this.jCodeTextField);
		addPanel.add(this.jNameLabel);
		addPanel.add(this.jNameTextField);
		addPanel.add(this.jBrandLabel);
		addPanel.add(this.jBrandTextField);
		addPanel.add(this.jCategoryLabel);
		addPanel.add(this.jCategoryTextField);
		addPanel.add(this.jPriceLabel);
		addPanel.add(this.jPriceTextField);
		addPanel.add(this.jAmountLabel);
		addPanel.add(this.jAmountTextField);
		
		
		
		offerPanel = new JPanel(new GridLayout(N_LINES_OFFER_PANEL,
				N_COLUMNS_OFFER_PANEL));
		
		offerPanel.add(this.jOfferLabel);
		offerPanel.add(this.jEmptyLabel1);
		offerPanel.add(this.jNoOfferRadioButton);
		offerPanel.add(this.jEmptyLabel2);
		offerPanel.add(this.jPercentOfferRadioButton);
		offerPanel.add(this.jPercentTextField);
		offerPanel.add(this.jThreePerTwoOfferRadioButton);
		
		this.add(imagePanel);
		this.add(addPanel);
		this.add(offerPanel);
		this.add(this.jAddButton);
		this.jAddButton.setActionCommand(ADD_BUTTON_ACTION_COMMAND_TEXT);
		this.jAddButton.addActionListener(this);
		
		/* GESTIONE RADIO BUTTON */
		this.jNoOfferRadioButton.setSelected(true);
		this.jPercentTextField.setEditable(false);
		this.jPercentOfferRadioButton.addActionListener(this);
		this.jPercentOfferRadioButton.setActionCommand(PERCENT_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
		this.jNoOfferRadioButton.addActionListener(this);
		this.jNoOfferRadioButton.setActionCommand(NO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
		this.jThreePerTwoOfferRadioButton.addActionListener(this);
		this.jThreePerTwoOfferRadioButton.setActionCommand(THREE_PER_TWO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
	
	}
	
	public String getFileName(String string)
	{

		int lengthString = string.length();
//		System.out.println(lengthString);
		char last = string.charAt(lengthString - 1);
//		System.out.println(last);
//		System.out.println("indice inzizio: "+string.lastIndexOf("\\"));
//		System.out.println("indice fine: "+ string.indexOf((last)+1));
		String fileName = string.substring(string.lastIndexOf("\\"));
		
//		System.out.println(lengthString);
//		System.out.println(fileName);
		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
//		System.out.println(fileName);
		return fileName;
		/* fine roba nuova */
	}
	
	public String copyImage(Image image,String fileName)
	{
		int check = 0;
		BufferedImage buffered = (BufferedImage) image;
		try {
		    // save to file
		    File outputfile = new File("media/img/products/"+fileName);
		    do
		    {
		    	if(outputfile.exists())
			    {
			    	String name = fileName.substring(0, fileName.lastIndexOf("."));
			    	String extension = fileName.substring(fileName.lastIndexOf("."));
//			    	System.out.println(name);
//			    	System.out.println(extension);
			    	fileName = name + "1" + extension;
//			    	System.out.println(fileName);
			    	outputfile = new File("media/img/products/"+fileName);
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
		return ("media\\img\\products\\" + fileName);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(PERCENT_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT))
		{
			this.jPercentTextField.setEditable(true);
			this.jPercentTextField.setText("0");
		}
		else if(e.getActionCommand().equals(NO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT))
		{
			this.jPercentTextField.setEditable(false);
		}
		else if(e.getActionCommand().equals(THREE_PER_TWO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT))
		{
			this.jPercentTextField.setEditable(false);
		}
		else if(e.getActionCommand().equals(IMAGE_BUTTON_ACTION_COMMAND_TEXT))
		{
			ImageIcon newIcon;
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setMultiSelectionEnabled(false);
			if(returnVal == 0)
			{
				newIcon = new ImageIcon(new ResizableIcon(fc.getSelectedFile()).
						resizeIcon(ICON_DIMENSION, ICON_DIMENSION), fc.getSelectedFile().toString());
			}
			else
			{
				newIcon = new ImageIcon(new ResizableIcon(this.prodotto.getImmagine()).
						resizeIcon(ICON_DIMENSION, ICON_DIMENSION),this.prodotto.getImmagine().toString());
			}			
			if(newIcon!=null)
			{
				this.jImageLabel.setIcon(newIcon);
			}
			 this.validate();
		     this.repaint();
			
		}
		else if(e.getActionCommand().equals(ADD_BUTTON_ACTION_COMMAND_TEXT))
		{
			ArrayList <Prodotto> articles = new ArrayList<Prodotto>();
			String price;
			String amount;
			Promozione promo;
			int check = 0;
			articles = magazzino.getArticoli();
			

			for(int i = 0;i<articles.size();i++)
			{
				if(articles.get(i).getCodice().equals(this.jCodeTextField.getText()))
				{
					JOptionPane.showMessageDialog(this,
							"Il prodotto che si vuole inserire è gia presente in magazzino",
							"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					check = 1;
				}
			}
			
			if(check == 0)
			{
				if(this.jNoOfferRadioButton.isSelected())
				{
					if(this.jCodeTextField.getText().equals("") ||
							this.jNameTextField.getText().equals("") ||
							this.jBrandTextField.getText().equals("") ||
							this.jCategoryTextField.getText().equals("") ||
							this.jPriceTextField.getText().equals("") ||
							this.jAmountTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente",
								"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						price = this.jPriceTextField.getText();
						amount = this.jAmountTextField.getText();
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, "Inserire un prezzo diverso da 0 per il prodotto",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							this.prodotto = new Prodotto(this.jNameTextField.getText(),
									this.jBrandTextField.getText(),this.jCodeTextField.getText(),
									this.jCategoryTextField.getText(),Float.parseFloat(price),
									imgicon.getDescription(),Integer.parseInt(amount));
							
							/* SALVATAGGIO IMMAGINE */
							String string = imgicon.getDescription();
							String fileName = this.getFileName(string);							
							Image image = imgicon.getImage();
							String imagePath = this.copyImage(image, fileName);
							prodotto.setImmagine(imagePath);
							
							
							
							magazzino.aggiungiProdotto(prodotto);
							JOptionPane.showMessageDialog(this, "Podotto inserito correttamente",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
							
							

							
							this.jAddProductDialog.setVisible(false);
						}
					}
				}
				else if(this.jPercentOfferRadioButton.isSelected())
				{
					int checkPercent = Integer.valueOf(this.jPercentTextField.getText());
					if(this.jCodeTextField.getText().equals("") ||
							this.jNameTextField.getText().equals("") ||
							this.jBrandTextField.getText().equals("") ||
							this.jCategoryTextField.getText().equals("") ||
							this.jPriceTextField.getText().equals("") ||
							this.jAmountTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente",
								"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(this.jPercentTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this, "Inserire il valore della percentuale da scontare",
								"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(checkPercent == 0)
					{
						JOptionPane.showMessageDialog(this,
								"Inserire un valore della percentuale da scontare diverso da 0",
								"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						promo = new ScontoPercentuale(Integer.parseInt(this.jPercentTextField.getText()));
						price = this.jPriceTextField.getText();
						amount = this.jAmountTextField.getText();
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, "Inserire un prezzo diverso da 0 per il prodotto",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							this.prodotto = new Prodotto(this.jNameTextField.getText(),
									this.jBrandTextField.getText(),this.jCodeTextField.getText(),
									this.jCategoryTextField.getText(),Float.parseFloat(price),
									imgicon.getDescription(),Integer.parseInt(amount),promo);
							
							/* SALVATAGGIO IMMAGINE */
							String string = imgicon.getDescription();
							String fileName = this.getFileName(string);							
							Image image = imgicon.getImage();
							String imagePath = this.copyImage(image, fileName);
							prodotto.setImmagine(imagePath);
							
							magazzino.aggiungiProdotto(prodotto);
							JOptionPane.showMessageDialog(this, "Podotto inserito correttamente",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
							
							this.jAddProductDialog.setVisible(false);
						}
						
					}
				}
				else if(this.jThreePerTwoOfferRadioButton.isSelected())
				{
					if(this.jCodeTextField.getText().equals("") ||
							this.jNameTextField.getText().equals("") ||
							this.jBrandTextField.getText().equals("") ||
							this.jCategoryTextField.getText().equals("") ||
							this.jPriceTextField.getText().equals("") ||
							this.jAmountTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente",
								"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						promo = new ScontoTrePerDue();
						price = this.jPriceTextField.getText();
						amount = this.jAmountTextField.getText();
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, "Inserire un prezzo diverso da 0 per il prodotto",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							this.prodotto = new Prodotto(this.jNameTextField.getText(),
									this.jBrandTextField.getText(),this.jCodeTextField.getText(),
									this.jCategoryTextField.getText(),Float.parseFloat(price),
									imgicon.getDescription(),Integer.parseInt(amount),promo);
							
							/* SALVATAGGIO IMMAGINE */
							String string = imgicon.getDescription();
							String fileName = this.getFileName(string);							
							Image image = imgicon.getImage();
							String imagePath = this.copyImage(image, fileName);
							prodotto.setImmagine(imagePath);
							
							magazzino.aggiungiProdotto(prodotto);
							JOptionPane.showMessageDialog(this, "Podotto inserito correttamente",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
									
							this.jAddProductDialog.setVisible(false);
						}
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Selezionare un'offerta per il prodotto",
							"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
}



