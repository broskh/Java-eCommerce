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

public class JModifyProductDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -5456722239846991776L;
	
	private JStoreTable jStoreTable;
	private JSearchProductDialog jSearchProductDialog;
	private Prodotto product;
	private Magazzino store;
	private HashSet <Prodotto> articlesAdded;
	private HashSet <File> imagesToRemove;
	
	private static final String TITLE = "Modifica prodotto";
	private static final int MIN_HEIGHT_FRAME = 410; //200
	private static final int MIN_WIDTH_FRAME = 300; //400
	
	private Promozione promo;

	private ArrayList<File> imageToDeleteFromMod = new ArrayList<File>();
	private ArrayList<Image> imageFromMod = new ArrayList<Image>();
	private ArrayList<Prodotto> imageToSave = new ArrayList<Prodotto>();	
	private ArrayList<Prodotto> imageToSaveFromMod = new ArrayList<Prodotto>();

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
	private JButton jModifyButton;
	
	
	private JLabel jImageLabel;
	private JButton jImageButton;
	
	private JLabel jEmptyLabel1;
	private JLabel jEmptyLabel2;

	private static final int WIDTH_TEXTBOX = 10;
	private static final int N_LINES_MODIFY_PANEL = 6;
	private static final int N_COLUMNS_MODIFY_PANEL = 2;
	private static final int ICON_DIMENSION = 120;
	private static final int DIMENSION_VERTICAL_STRUT = 10;
	private static final int DIMENSION_HORIZIONTAL_STRUT = 20;
	private static final int N_LINES_OFFER_PANEL = 4;
	private static final int N_COLUMNS_OFFER_PANEL = 2;
	
	private static final String CODE_LABEL_TEXT = "Codice:";
	private static final String NAME_LABEL_TEXT = "Nome:";
	private static final String BRAND_LABEL_TEXT = "Marca:";
	private static final String CATEGORY_LABEL_TEXT = "Categoria:";
	private static final String PRICE_LABEL_TEXT = "Prezzo:";
	private static final String AMOUNT_LABEL_TEXT = "Quantità:";
	private static final String OFFER_LABEL_TEXT = "Offerta:";
	private static final String NO_OFFER_RADIO_BUTTON_TEXT = "No";
	private static final String PERCENT_OFFER_RADIO_BUTTON_TEXT = "Percentuale";
	private static final String THREE_PER_TWO_OFFER_RADIO_BUTTON_TEXT = "3x2";
	private static final String MODIFY_BUTTON_TEXT = "Modifica";
	private static final String IMAGE_BUTTON_TEXT = "Cambia";
	
	private static final String IMAGE_BUTTON_ACTION_COMMAND_TEXT = "cambia";
	private static final String MODIFY_BUTTON_ACTION_COMMAND_TEXT = "modifica";
	private static final String NO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT = "nessuna";
	private static final String PERCENT_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT = "percentuale";
	private static final String THREE_PER_TWO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT = "3x2";
	
	private static final String THREE_PER_TWO_TO_STRING = "3 x 2";
	private static final String ALERT_STRING = "Attenzione!";
	private static final String ALL_DATA_STRING = "Inserire tutti i dati correttamente";
	private static final String PRICE_NOT_ZERO_STRING = 
			"Inserire un prezzo diverso da 0 per il prodotto";
	private static final String CORRECT_MODIFY = "Modifica effettuata con successo";
	private static final String PERCENT_REQUEST_STRING = 
			"Inserire il valore della percentuale da scontare";
	private static final String PERCENT_VALUE_NOT_ZERO_STRING = 
			"Inserire un valore della percentuale da scontare diverso da 0";
	private static final String SELECT_OFFER_STRING = "Selezionare un'offerta per il prodotto";
	private static final String CODE_ALREADY_PRESENT = "Il codice inserito è gia presente nel magazzino per un altro prodotto";
	
	private static final String EMPTY_LABEL_TEXT = "";
	
	
	public JModifyProductDialog(JFrame mainFrame,Prodotto prodotto, Magazzino magazzino,
			JSearchProductDialog jSearchProductDialog,JStoreTable jStoreTable, HashSet <Prodotto> articlesAdded, HashSet <File> imagesToRemove)
	{
		super(mainFrame,TITLE,JDialog.ModalityType.DOCUMENT_MODAL);
		this.setModal(true);
		this.jSearchProductDialog = jSearchProductDialog;
		this.jSearchProductDialog.setVisible(false);
		
		this.product = prodotto;
		this.store = magazzino;

		this.articlesAdded = articlesAdded;
		this.imagesToRemove = imagesToRemove;
		
		this.jStoreTable = jStoreTable;
		
		this.setSize(new Dimension(JModifyProductDialog.MIN_WIDTH_FRAME,
				JModifyProductDialog.MIN_HEIGHT_FRAME));
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());	
		
		this.add(this.contentPanel(), BorderLayout.CENTER);
	}
	
	private JPanel contentPanel () {
		/* ROBA PER IMMAGINE */

		this.jImageLabel = new JLabel("",SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon(new ResizableIcon(this.product.getImmagine()).
				resizeIcon(ICON_DIMENSION, ICON_DIMENSION),this.product.getImmagine().toString());

		if(icon != null)
		{
			this.jImageLabel.setIcon(icon);
		}
		JPanel imagePanel = new JPanel();
		this.jImageButton = new JButton(JModifyProductDialog.IMAGE_BUTTON_TEXT);
		imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.X_AXIS));
		imagePanel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		imagePanel.add(this.jImageLabel);
		imagePanel.add(Box.createHorizontalStrut(DIMENSION_HORIZIONTAL_STRUT));
		imagePanel.add(jImageButton);
		this.jImageButton.setActionCommand(IMAGE_BUTTON_ACTION_COMMAND_TEXT);
		this.jImageButton.addActionListener(this);
		imagePanel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		
		/* FINE ROBA PER IMMAGINE */
		
		this.jCodeLabel = new JLabel(JModifyProductDialog.CODE_LABEL_TEXT);
		this.jCodeTextField = new JTextField(JModifyProductDialog.WIDTH_TEXTBOX);
		jCodeTextField.setText(product.getCodice());
		this.jNameLabel = new JLabel(JModifyProductDialog.NAME_LABEL_TEXT);
		this.jNameTextField = new JTextField(JModifyProductDialog.WIDTH_TEXTBOX);
		jNameTextField.setText(product.getNome());
		this.jBrandLabel = new JLabel(JModifyProductDialog.BRAND_LABEL_TEXT);
		this.jBrandTextField = new JTextField(JModifyProductDialog.WIDTH_TEXTBOX);
		jBrandTextField.setText(product.getMarca());
		this.jCategoryLabel = new JLabel(JModifyProductDialog.CATEGORY_LABEL_TEXT);
		this.jCategoryTextField = new JTextField(JModifyProductDialog.WIDTH_TEXTBOX);
		jCategoryTextField.setText(product.getCategoria());
		this.jPriceLabel = new JLabel(JModifyProductDialog.PRICE_LABEL_TEXT);
		this.jPriceTextField = new JTextField(JModifyProductDialog.WIDTH_TEXTBOX);
		jPriceTextField.setText(Float.toString(product.getPrezzo()));
		/* CONTROLLO TEXT FIELD PREZZO */
		PlainDocument docP = (PlainDocument)this.jPriceTextField.getDocument();
		docP.setDocumentFilter(new PriceDocumentFilter());
		
		this.jAmountLabel = new JLabel(JModifyProductDialog.AMOUNT_LABEL_TEXT);
		this.jAmountTextField = new JTextField(JModifyProductDialog.WIDTH_TEXTBOX);
		jAmountTextField.setText(Integer.toString(product.getQuantita()));
		/* CONTROLLO TEXT FIELD QUANTITA */
		PlainDocument docQ = (PlainDocument)this.jAmountTextField.getDocument();
		docQ.setDocumentFilter(new AmountDocumentFilter());
		
		this.jOfferLabel = new JLabel(JModifyProductDialog.OFFER_LABEL_TEXT);
		this.jPercentTextField = new JTextField(JModifyProductDialog.WIDTH_TEXTBOX);
		this.jNoOfferRadioButton = new JRadioButton(JModifyProductDialog.NO_OFFER_RADIO_BUTTON_TEXT);
		this.jPercentOfferRadioButton = new JRadioButton(JModifyProductDialog.
				PERCENT_OFFER_RADIO_BUTTON_TEXT);
		
		this.jThreePerTwoOfferRadioButton = new JRadioButton(JModifyProductDialog.
				THREE_PER_TWO_OFFER_RADIO_BUTTON_TEXT);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.jNoOfferRadioButton);
		buttonGroup.add(this.jPercentOfferRadioButton);
		buttonGroup.add(this.jThreePerTwoOfferRadioButton);
		this.jModifyButton = new JButton(JModifyProductDialog.MODIFY_BUTTON_TEXT);
		
		this.jEmptyLabel1 = new JLabel(JModifyProductDialog.EMPTY_LABEL_TEXT);
		this.jEmptyLabel2 = new JLabel(JModifyProductDialog.EMPTY_LABEL_TEXT);
		
//		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel modifyPanel = new JPanel(new GridLayout(N_LINES_MODIFY_PANEL,N_COLUMNS_MODIFY_PANEL));
		modifyPanel.add(this.jCodeLabel);
		modifyPanel.add(this.jCodeTextField);
		modifyPanel.add(this.jNameLabel);
		modifyPanel.add(this.jNameTextField);
		modifyPanel.add(this.jBrandLabel);
		modifyPanel.add(this.jBrandTextField);
		modifyPanel.add(this.jCategoryLabel);
		modifyPanel.add(this.jCategoryTextField);
		modifyPanel.add(this.jPriceLabel);
		modifyPanel.add(this.jPriceTextField);
		modifyPanel.add(this.jAmountLabel);
		modifyPanel.add(this.jAmountTextField);
		

		/* Preselezione radio button in base al tipo di offerta presente sul prodotto */
		promo = product.getOfferta();
		
		if(promo == null)
		{
			this.jNoOfferRadioButton.setSelected(true);
			this.jPercentTextField.setEditable(false);
		}
		else if(promo.toString().equals(THREE_PER_TWO_TO_STRING))
		{
			this.jThreePerTwoOfferRadioButton.setSelected(true);
			this.jPercentTextField.setEditable(false);
		}
		else
		{
			ScontoPercentuale sp = (ScontoPercentuale) product.getOfferta();
			this.jPercentOfferRadioButton.setSelected(true);
			this.jPercentTextField.setText(Integer.toString(sp.getPercentuale()));
			/* CONTROLLO TEXT FIELD PERCENTUALE */
			
		}
		PlainDocument docPC = (PlainDocument)this.jPercentTextField.getDocument();
		docPC.setDocumentFilter(new AmountDocumentFilter());
		
		
		JPanel offerPanel = new JPanel(new GridLayout(N_LINES_OFFER_PANEL,N_COLUMNS_OFFER_PANEL));
		offerPanel.add(this.jOfferLabel);
		offerPanel.add(this.jEmptyLabel1);
		offerPanel.add(this.jNoOfferRadioButton);
		offerPanel.add(this.jEmptyLabel2);
		offerPanel.add(this.jPercentOfferRadioButton);
		offerPanel.add(this.jPercentTextField);
		offerPanel.add(this.jThreePerTwoOfferRadioButton);
		
		JPanel contentPanel = new JPanel();
		contentPanel.add(imagePanel);
		contentPanel.add(modifyPanel);
		contentPanel.add(offerPanel);
		contentPanel.add(this.jModifyButton);
		
		this.jModifyButton.setActionCommand(MODIFY_BUTTON_ACTION_COMMAND_TEXT);
		this.jModifyButton.addActionListener(this);
		
		
		/* ACTION COMMAND RADIO BUTTON */
		this.jPercentOfferRadioButton.addActionListener(this);
		this.jPercentOfferRadioButton.setActionCommand(PERCENT_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
		this.jNoOfferRadioButton.addActionListener(this);
		this.jNoOfferRadioButton.setActionCommand(NO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
		this.jThreePerTwoOfferRadioButton.addActionListener(this);
		this.jThreePerTwoOfferRadioButton.setActionCommand(THREE_PER_TWO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
		
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
			
		}
		else if(e.getActionCommand().equals(MODIFY_BUTTON_ACTION_COMMAND_TEXT))
		{
			Prodotto prod = store.getProdotto(product.getCodice());
			Promozione promozione;
			ArrayList <Prodotto> articles = new ArrayList <Prodotto>();
			int check = 0;
			articles = store.getArticoli();			
			if(this.jCodeTextField.getText().equals(product.getCodice()))
			{
				check = 0;
			}
			else
			{
				for(int i = 0;i<articles.size();i++)
				{
					if(articles.get(i).getCodice().equals(this.jCodeTextField.getText()))
					{
						check = 1;
					}
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
						

						this.imageToDeleteFromMod.add(prod.getImmagine());
						
						prod.setCategoria(this.jCategoryTextField.getText());
						prod.setCodice(this.jCodeTextField.getText());
						prod.setMarca(this.jBrandTextField.getText());
						prod.setNome(this.jNameTextField.getText());
						prod.setOfferta(null);
						prod.setImmagine(imgicon.getDescription());
						prod.setPrezzo(Float.valueOf(this.jPriceTextField.getText()));
						prod.setQuantita(Integer.valueOf(this.jAmountTextField.getText()));
						/*save*/
						this.imageToSave.add(prod);
						this.imageFromMod.add(imgicon.getImage());
						/**/
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							if(product.getCategoria().equals(prod.getCategoria()) &&
									product.getCodice().equals(prod.getCodice()) &&
									product.getMarca().equals(prod.getMarca()) &&
									product.getNome().equals(prod.getNome()) &&
									product.getPrezzo() == prod.getPrezzo() &&
									product.getQuantita() == prod.getQuantita())
							{
								JOptionPane.showMessageDialog(this, CORRECT_MODIFY,
										ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
								this.setVisible(false);
								
								((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
							}
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
						JOptionPane.showMessageDialog(this,
								PERCENT_REQUEST_STRING,
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
						promozione = new ScontoPercentuale(Integer.parseInt(this.
								jPercentTextField.getText()));
						
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						this.imageToDeleteFromMod.add(prod.getImmagine());
						
						prod.setCategoria(this.jCategoryTextField.getText());
						prod.setCodice(this.jCodeTextField.getText());
						prod.setMarca(this.jBrandTextField.getText());
						prod.setNome(this.jNameTextField.getText());
						prod.setOfferta(promozione);
						prod.setImmagine(imgicon.getDescription());
						prod.setPrezzo(Float.valueOf(this.jPriceTextField.getText()));
						prod.setQuantita(Integer.valueOf(this.jAmountTextField.getText()));
						/*save*/
						this.imageToSaveFromMod.add(prod);
						this.imageFromMod.add(imgicon.getImage());
						/**/
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							if(product.getCategoria().equals(prod.getCategoria()) &&
									product.getCodice().equals(prod.getCodice()) &&
									product.getMarca().equals(prod.getMarca()) &&
									product.getNome().equals(prod.getNome()) &&
									product.getPrezzo() == prod.getPrezzo() &&
									product.getQuantita() == prod.getQuantita())
							{
								JOptionPane.showMessageDialog(this, CORRECT_MODIFY,
										ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
								this.setVisible(false);
								
								((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
							}
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
						promozione = new ScontoTrePerDue();
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						this.imageToDeleteFromMod.add(prod.getImmagine());

						prod.setCategoria(this.jCategoryTextField.getText());
						prod.setCodice(this.jCodeTextField.getText());
						prod.setMarca(this.jBrandTextField.getText());
						prod.setNome(this.jNameTextField.getText());
						prod.setOfferta(promozione);
						prod.setImmagine(imgicon.getDescription());
						prod.setPrezzo(Float.valueOf(this.jPriceTextField.getText()));
						prod.setQuantita(Integer.valueOf(this.jAmountTextField.getText()));
						/*save*/
						this.imageToSaveFromMod.add(prod);
						this.imageFromMod.add(imgicon.getImage());
						/**/
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
									ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							if(product.getCategoria().equals(prod.getCategoria()) &&
									product.getCodice().equals(prod.getCodice()) &&
									product.getMarca().equals(prod.getMarca()) &&
									product.getNome().equals(prod.getNome()) &&
									product.getPrezzo() == prod.getPrezzo() &&
									product.getQuantita() == prod.getQuantita())
							{
								JOptionPane.showMessageDialog(this, CORRECT_MODIFY,
										ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
								this.setVisible(false);
								
								((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
							}
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, SELECT_OFFER_STRING,
							ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						CODE_ALREADY_PRESENT,
						ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
			}
		}	
	}
}