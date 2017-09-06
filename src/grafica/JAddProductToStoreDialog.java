package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.PlainDocument;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;
import negozio.Promozione;
import negozio.ScontoPercentuale;
import negozio.ScontoTrePerDue;

public class JAddProductToStoreDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = -3173951653228607174L;

	private JStoreTable jStoreTable;
	
	private Magazzino store;
	private Prodotto product;
	private HashSet <Prodotto> articlesAdded;
	
	/**/
	private JPanel addPanel;
	private JPanel offerPanel;
	private JPanel imagePanel;
	/**/
	
	private JLabel jCodeLabel;
	private JTextField jCodeTextField;
	private JLabel jNameLabel;
	private JTextField jNameTextField;
	private JLabel jBrandLabel;
	private JTextField jBrandTextField;
	private JLabel jCategoryLabel;
	private JTextField jCategoryTextField;
	private JLabel jPriceLabel;
	private JTextField jPriceTextField;
	private JLabel jAmountLabel;
	private JTextField jAmountTextField;
	private JLabel jOfferLabel;
	private JRadioButton jNoOfferRadioButton;
	private JRadioButton jPercentOfferRadioButton;
	private JRadioButton jThreePerTwoOfferRadioButton;
	private JTextField jPercentTextField;
	private JButton jAddButton;
	private JLabel jImageLabel; 
	private JButton jImageButton;
		
	private JLabel jEmptyLabel1;
	private JLabel jEmptyLabel2;


	private ArrayList<Prodotto> imageToSave = new ArrayList<Prodotto>();
	private ArrayList<Image> image = new ArrayList<Image>();
	
	private static final int WIDTH_TEXTBOX = 10;
	private static final int N_LINES_ADD_PANEL = 6;
	private static final int N_COLUMNS_ADD_PANEL = 2;
	private static final int ICON_DIMENSION = 120;
	private static final int DIMENSION_VERTICAL_STRUT = 10;
	private static final int DIMENSION_HORIZONTAL_STRUT = 20;
	private static final int N_LINES_OFFER_PANEL = 4;
	private static final int N_COLUMNS_OFFER_PANEL = 2;
	private static final int LINE_HEIGHT = 100;
	
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
	private static final String PRICE_TEXT_BOX_VALUE_DEFAULT = "0.00";
	private static final String AMOUNT_TEXT_BOX_VALUE_DEFAULT = "0";
	private static final String NEW_IMAGE_PATH = "media/img/this.products/";
	private static final String RETURN_NEW_IMAGE_PATH = "media\\img\\this.products\\";
	private static final String PRODUCT_ALREADY_EXIST_STRING = 
			"Il prodotto che si vuole inserire è gia presente in magazzino";
	private static final String ALERT_STRING = "Attenzione!";
	private static final String ALL_DATA_STRING = "Inserire tutti i dati correttamente";
	private static final String PRICE_NOT_ZERO_STRING = 
			"Inserire un prezzo diverso da 0 per il prodotto";
	private static final String RIGHT_INSERT_STRING = "Podotto inserito correttamente";
	private static final String PERCENT_REQUEST_STRING = 
			"Inserire il valore della percentuale da scontare";
	private static final String PERCENT_VALUE_NOT_ZERO_STRING = 
			"Inserire un valore della percentuale da scontare diverso da 0";
	private static final String SELECT_OFFER_STRING = "Selezionare un'offerta per il prodotto";
	

	private static final String DEFAULT_IMAGE = "media/img/this.products/immagine_non_disponibile.jpg";
	
	protected static final String TITLE = "Aggiungi prodotto";
	protected static final int MIN_FRAME_HEIGHT = 410;
	protected static final int MIN_FRAME_WIDTH = 300;
	
	public JAddProductToStoreDialog (JFrame mainFrame, Magazzino store, JStoreTable jStoreTable, HashSet <Prodotto> articlesAdded) {
		super(mainFrame,TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.store = store;
		this.jStoreTable = jStoreTable;
		this.articlesAdded = articlesAdded;
		this.setModal(true);
		this.setSize(new Dimension(JAddProductToStoreDialog.MIN_FRAME_WIDTH,JAddProductToStoreDialog.
				MIN_FRAME_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		this.add(this.contentPanel(), BorderLayout.CENTER);
	}
	
	private JPanel contentPanel () {
		/* ROBA PER IMMAGINE */
		this.product = new Prodotto("","","","",0,DEFAULT_IMAGE,0,null);
		this.jImageLabel = new JLabel("",SwingConstants.CENTER);
		
		ImageIcon icon = new ImageIcon(new ResizableIcon(this.product.getImmagine()).
				resizeIcon(ICON_DIMENSION, ICON_DIMENSION),this.product.getImmagine().toString());
		if(icon!=null)
		{
			this.jImageLabel.setIcon(icon);
		}
		
		imagePanel = new JPanel();
		this.jImageButton = new JButton(JAddProductToStoreDialog.IMAGE_BUTTON_TEXT);
		imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.X_AXIS));
		imagePanel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		imagePanel.add(this.jImageLabel);
		imagePanel.add(Box.createHorizontalStrut(DIMENSION_HORIZONTAL_STRUT));
		imagePanel.add(jImageButton);
		this.jImageButton.setActionCommand(IMAGE_BUTTON_ACTION_COMMAND_TEXT);
		this.jImageButton.addActionListener(this);
		imagePanel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		/* FINE ROBA PER IMMAGINE */
		
		this.jCodeLabel = new JLabel(JAddProductToStoreDialog.CODE_LABEL_TEXT);
		this.jCodeTextField = new JTextField(JAddProductToStoreDialog.WIDTH_TEXTBOX);
		this.jNameLabel = new JLabel(JAddProductToStoreDialog.NAME_LABEL_TEXT);
		this.jNameTextField = new JTextField(JAddProductToStoreDialog.WIDTH_TEXTBOX);
		this.jBrandLabel = new JLabel(JAddProductToStoreDialog.BRAND_LABEL_TEXT);
		this.jBrandTextField = new JTextField(JAddProductToStoreDialog.WIDTH_TEXTBOX);
		this.jCategoryLabel = new JLabel(JAddProductToStoreDialog.CATEGORY_LABEL_TEXT);
		this.jCategoryTextField = new JTextField(JAddProductToStoreDialog.WIDTH_TEXTBOX);
		this.jPriceLabel = new JLabel(JAddProductToStoreDialog.PRICE_LABEL_TEXT);
		this.jPriceTextField = new JTextField(PRICE_TEXT_BOX_VALUE_DEFAULT,JAddProductToStoreDialog.WIDTH_TEXTBOX);
		/* check TEXT FIELD price */
		PlainDocument docP = (PlainDocument)this.jPriceTextField.getDocument();
		docP.setDocumentFilter(new PriceDocumentFilter());
		
		this.jAmountLabel = new JLabel(JAddProductToStoreDialog.AMOUNT_LABEL_TEXT);
		this.jAmountTextField = new JTextField(AMOUNT_TEXT_BOX_VALUE_DEFAULT,JAddProductToStoreDialog.WIDTH_TEXTBOX);
		/* check TEXT FIELD amount */
		PlainDocument docQ = (PlainDocument)this.jAmountTextField.getDocument();
		docQ.setDocumentFilter(new AmountDocumentFilter());
		
		
		this.jOfferLabel = new JLabel(JAddProductToStoreDialog.OFFER_LABEL_TEXT);
		this.jPercentTextField = new JTextField(JAddProductToStoreDialog.WIDTH_TEXTBOX);
		/* check TEXT FIELD amount */
		PlainDocument docPC = (PlainDocument)this.jPercentTextField.getDocument();
		docPC.setDocumentFilter(new AmountDocumentFilter());
		
		this.jNoOfferRadioButton = new JRadioButton(JAddProductToStoreDialog.NO_OFFER_RADIO_BUTTON_TEXT);
		this.jPercentOfferRadioButton = new JRadioButton(JAddProductToStoreDialog.
				PERCENT_OFFER_RADIO_BUTTON_TEXT);
		
		this.jThreePerTwoOfferRadioButton = new JRadioButton(JAddProductToStoreDialog.
				THREE_PER_TWO_OFFER_RADIO_BUTTON_TEXT);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.jNoOfferRadioButton);
		buttonGroup.add(this.jPercentOfferRadioButton);
		buttonGroup.add(this.jThreePerTwoOfferRadioButton);
		this.jAddButton = new JButton(JAddProductToStoreDialog.ADD_BUTTON_TEXT);
		
		
		
		
		
		this.jEmptyLabel1 = new JLabel(JAddProductToStoreDialog.EMPTY_LABEL_TEXT);
		this.jEmptyLabel2 = new JLabel(JAddProductToStoreDialog.EMPTY_LABEL_TEXT);

		
		
		
		
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

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(imagePanel);
		contentPanel.add(addPanel);
		contentPanel.add(offerPanel);
		contentPanel.add(this.jAddButton);
		return contentPanel;
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
				newIcon = new ImageIcon(new ResizableIcon(this.product.getImmagine()).
						resizeIcon(ICON_DIMENSION, ICON_DIMENSION),this.product.getImmagine().toString());
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
			articles = this.store.getArticoli();
			

			for(int i = 0;i<articles.size();i++)
			{
				if(articles.get(i).getCodice().equals(this.jCodeTextField.getText()))
				{
					JOptionPane.showMessageDialog(this,
							PRODUCT_ALREADY_EXIST_STRING,
							ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
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
						JOptionPane.showMessageDialog(this, ALL_DATA_STRING,
								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						price = this.jPriceTextField.getText();
						amount = this.jAmountTextField.getText();
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							this.product = new Prodotto(this.jNameTextField.getText(),
									this.jBrandTextField.getText(),this.jCodeTextField.getText(),
									this.jCategoryTextField.getText(),Float.parseFloat(price),
									imgicon.getDescription(),Integer.parseInt(amount));
							
							/* SALVATAGGIO IMMAGINE */		
							this.imageToSave.add(product);
							this.image.add(imgicon.getImage());
							
							this.store.aggiungiProdotto(product);
							JOptionPane.showMessageDialog(this, RIGHT_INSERT_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
	
							this.setVisible(false);
							
							jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(),
									LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
							jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
							jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
							this.store.getArticoli(), LINE_HEIGHT));
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
						JOptionPane.showMessageDialog(this, ALL_DATA_STRING,
								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
					}
					else if(this.jPercentTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this, PERCENT_REQUEST_STRING,
								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
					}
					else if(checkPercent == 0)
					{
						JOptionPane.showMessageDialog(this,
								PERCENT_VALUE_NOT_ZERO_STRING,
								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							this.product = new Prodotto(this.jNameTextField.getText(),
									this.jBrandTextField.getText(),this.jCodeTextField.getText(),
									this.jCategoryTextField.getText(),Float.parseFloat(price),
									imgicon.getDescription(),Integer.parseInt(amount),promo);
							
							/* SALVATAGGIO IMMAGINE */		
							this.imageToSave.add(product);
							this.image.add(imgicon.getImage());

							this.store.aggiungiProdotto(product);
							JOptionPane.showMessageDialog(this, RIGHT_INSERT_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
							
							jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(),
									LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
							jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
							jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
							this.store.getArticoli(), LINE_HEIGHT));
							
							this.setVisible(false);
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
						JOptionPane.showMessageDialog(this, ALL_DATA_STRING,
								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							this.product = new Prodotto(this.jNameTextField.getText(),
									this.jBrandTextField.getText(),this.jCodeTextField.getText(),
									this.jCategoryTextField.getText(),Float.parseFloat(price),
									imgicon.getDescription(),Integer.parseInt(amount),promo);
							
							/* SALVATAGGIO IMMAGINE */		
							this.imageToSave.add(product);
							this.image.add(imgicon.getImage());
		
							this.store.aggiungiProdotto(product);
							JOptionPane.showMessageDialog(this, RIGHT_INSERT_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
							
							jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(),
									LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
							jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
							jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
							this.store.getArticoli(), LINE_HEIGHT));
									
							this.setVisible(false);
						}
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, SELECT_OFFER_STRING,
							ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
}