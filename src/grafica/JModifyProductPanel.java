package grafica;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class JModifyProductPanel extends JPanel implements ActionListener {

private static final long serialVersionUID = 1L;
	
	private JModifyProductDialog jModifyProductDialog;

	private Magazzino magazzino;
	private Prodotto p;
	private Promozione promo;

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
	protected JButton jModifyButton;
	
	
	private JLabel jImageLabel;
	protected JButton jImageButton;
	
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
	private static final String AMOUNT_LABEL_TEXT = "Quantitï¿½:";
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
	
	private static final String EMPTY_LABEL_TEXT = "";
	
	public JModifyProductPanel(JModifyProductDialog jModifyProductDialog,Prodotto p, Magazzino magazzino)
	{
		this.jModifyProductDialog = jModifyProductDialog;
		this.p = p;
		this.magazzino = magazzino;
		/* ROBA PER IMMAGINE */

		this.jImageLabel = new JLabel("",SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon(new ResizableIcon(this.p.getImmagine()).
				resizeIcon(ICON_DIMENSION, ICON_DIMENSION),this.p.getImmagine().toString());

		if(icon != null)
		{
			this.jImageLabel.setIcon(icon);
		}
		JPanel imagePanel = new JPanel();
		this.jImageButton = new JButton(JModifyProductPanel.IMAGE_BUTTON_TEXT);
		imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.X_AXIS));
		imagePanel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		imagePanel.add(this.jImageLabel);
		imagePanel.add(Box.createHorizontalStrut(DIMENSION_HORIZIONTAL_STRUT));
		imagePanel.add(jImageButton);
		this.jImageButton.setActionCommand(IMAGE_BUTTON_ACTION_COMMAND_TEXT);
		this.jImageButton.addActionListener(this);
		imagePanel.add(Box.createVerticalStrut(DIMENSION_VERTICAL_STRUT));
		
		/* FINE ROBA PER IMMAGINE */
		
		this.jCodeLabel = new JLabel(JModifyProductPanel.CODE_LABEL_TEXT);
		this.jCodeTextField = new JTextField(JModifyProductPanel.WIDTH_TEXTBOX);
		jCodeTextField.setText(p.getCodice());
		this.jNameLabel = new JLabel(JModifyProductPanel.NAME_LABEL_TEXT);
		this.jNameTextField = new JTextField(JModifyProductPanel.WIDTH_TEXTBOX);
		jNameTextField.setText(p.getNome());
		this.jBrandLabel = new JLabel(JModifyProductPanel.BRAND_LABEL_TEXT);
		this.jBrandTextField = new JTextField(JModifyProductPanel.WIDTH_TEXTBOX);
		jBrandTextField.setText(p.getMarca());
		this.jCategoryLabel = new JLabel(JModifyProductPanel.CATEGORY_LABEL_TEXT);
		this.jCategoryTextField = new JTextField(JModifyProductPanel.WIDTH_TEXTBOX);
		jCategoryTextField.setText(p.getCategoria());
		this.jPriceLabel = new JLabel(JModifyProductPanel.PRICE_LABEL_TEXT);
		this.jPriceTextField = new JTextField(JModifyProductPanel.WIDTH_TEXTBOX);
		jPriceTextField.setText(Float.toString(p.getPrezzo()));
		/* CONTROLLO TEXT FIELD PREZZO */
		PlainDocument docP = (PlainDocument)this.jPriceTextField.getDocument();
		docP.setDocumentFilter(new PriceDocumentFilter());
		
		this.jAmountLabel = new JLabel(JModifyProductPanel.AMOUNT_LABEL_TEXT);
		this.jAmountTextField = new JTextField(JModifyProductPanel.WIDTH_TEXTBOX);
		jAmountTextField.setText(Integer.toString(p.getQuantita()));
		/* CONTROLLO TEXT FIELD QUANTITA */
		PlainDocument docQ = (PlainDocument)this.jAmountTextField.getDocument();
		docQ.setDocumentFilter(new AmountDocumentFilter());
		
		this.jOfferLabel = new JLabel(JModifyProductPanel.OFFER_LABEL_TEXT);
		this.jPercentTextField = new JTextField(JModifyProductPanel.WIDTH_TEXTBOX);
		this.jNoOfferRadioButton = new JRadioButton(JModifyProductPanel.NO_OFFER_RADIO_BUTTON_TEXT);
		this.jPercentOfferRadioButton = new JRadioButton(JModifyProductPanel.
				PERCENT_OFFER_RADIO_BUTTON_TEXT);
		
		this.jThreePerTwoOfferRadioButton = new JRadioButton(JModifyProductPanel.
				THREE_PER_TWO_OFFER_RADIO_BUTTON_TEXT);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.jNoOfferRadioButton);
		buttonGroup.add(this.jPercentOfferRadioButton);
		buttonGroup.add(this.jThreePerTwoOfferRadioButton);
		this.jModifyButton = new JButton(JModifyProductPanel.MODIFY_BUTTON_TEXT);
		
		this.jEmptyLabel1 = new JLabel(JModifyProductPanel.EMPTY_LABEL_TEXT);
		this.jEmptyLabel2 = new JLabel(JModifyProductPanel.EMPTY_LABEL_TEXT);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
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
		promo = p.getOfferta();
		
		if(promo == null)
		{
			this.jNoOfferRadioButton.setSelected(true);
			this.jPercentTextField.setEditable(false);
		}
		else if(promo.toString().equals("3 x 2"))
		{
			this.jThreePerTwoOfferRadioButton.setSelected(true);
			this.jPercentTextField.setEditable(false);
		}
		else
		{
			ScontoPercentuale sp = (ScontoPercentuale) p.getOfferta();
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
		
		this.add(imagePanel);
		this.add(modifyPanel);
		this.add(offerPanel);
		this.add(this.jModifyButton);
		this.jModifyButton.setActionCommand(MODIFY_BUTTON_ACTION_COMMAND_TEXT);
		this.jModifyButton.addActionListener(this);
		
		
		/* ACTION COMMAND RADIO BUTTON */
		this.jPercentOfferRadioButton.addActionListener(this);
		this.jPercentOfferRadioButton.setActionCommand(PERCENT_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
		this.jNoOfferRadioButton.addActionListener(this);
		this.jNoOfferRadioButton.setActionCommand(NO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
		this.jThreePerTwoOfferRadioButton.addActionListener(this);
		this.jThreePerTwoOfferRadioButton.setActionCommand(THREE_PER_TWO_OFFER_RADIO_BUTTON_ACTION_COMMAND_TEXT);
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
				newIcon = new ImageIcon(new ResizableIcon(this.p.getImmagine()).
						resizeIcon(ICON_DIMENSION, ICON_DIMENSION),this.p.getImmagine().toString());
			}
	
			if(newIcon!=null)
			{
				this.jImageLabel.setIcon(newIcon);
			}
			
		}
		else if(e.getActionCommand().equals(MODIFY_BUTTON_ACTION_COMMAND_TEXT))
		{
			Prodotto prod = magazzino.getProdotto(p.getCodice());
			Promozione promozione;
			ArrayList <Prodotto> articles = new ArrayList <Prodotto>();
			int check = 0;
			articles = magazzino.getArticoli();			
			if(this.jCodeTextField.getText().equals(p.getCodice()))
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
						JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente",
								"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{	
						Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						prod.setCategoria(this.jCategoryTextField.getText());
						prod.setCodice(this.jCodeTextField.getText());
						prod.setMarca(this.jBrandTextField.getText());
						prod.setNome(this.jNameTextField.getText());
						prod.setOfferta(null);
						prod.setImmagine(imgicon.getDescription());
						prod.setPrezzo(Float.valueOf(this.jPriceTextField.getText()));
						prod.setQuantita(Integer.valueOf(this.jAmountTextField.getText()));
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, "Inserire un prezzo diverso da 0 per il prodotto",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							magazzino.salvaMagazzino("media/saves/save21.mag");
							if(p.getCategoria().equals(prod.getCategoria()) &&
									p.getCodice().equals(prod.getCodice()) &&
									p.getMarca().equals(prod.getMarca()) &&
									p.getNome().equals(prod.getNome()) &&
									p.getPrezzo() == prod.getPrezzo() &&
									p.getQuantita() == prod.getQuantita())
							{
								JOptionPane.showMessageDialog(this, "Modifica effettuata con successo",
										"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
								this.jModifyProductDialog.setVisible(false);
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
						JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente",
								"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(this.jPercentTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this,
								"Inserire il valore della percentuale da scontare",
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
						promozione = new ScontoPercentuale(Integer.parseInt(this.
								jPercentTextField.getText()));
						
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						prod.setCategoria(this.jCategoryTextField.getText());
						prod.setCodice(this.jCodeTextField.getText());
						prod.setMarca(this.jBrandTextField.getText());
						prod.setNome(this.jNameTextField.getText());
						prod.setOfferta(promozione);
						prod.setImmagine(imgicon.getDescription());
						prod.setPrezzo(Float.valueOf(this.jPriceTextField.getText()));
						prod.setQuantita(Integer.valueOf(this.jAmountTextField.getText()));
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, "Inserire un prezzo diverso da 0 per il prodotto",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							magazzino.salvaMagazzino("media/saves/save21.mag");
							if(p.getCategoria().equals(prod.getCategoria()) &&
									p.getCodice().equals(prod.getCodice()) &&
									p.getMarca().equals(prod.getMarca()) &&
									p.getNome().equals(prod.getNome()) &&
									p.getPrezzo() == prod.getPrezzo() &&
									p.getQuantita() == prod.getQuantita())
							{
								JOptionPane.showMessageDialog(this, "Modifica effettuata con successo",
										"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
								this.jModifyProductDialog.setVisible(false);
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
						JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente",
								"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
						promozione = new ScontoTrePerDue();
						ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
						
						prod.setCategoria(this.jCategoryTextField.getText());
						prod.setCodice(this.jCodeTextField.getText());
						prod.setMarca(this.jBrandTextField.getText());
						prod.setNome(this.jNameTextField.getText());
						prod.setOfferta(promozione);
						prod.setImmagine(imgicon.getDescription());
						prod.setPrezzo(Float.valueOf(this.jPriceTextField.getText()));
						prod.setQuantita(Integer.valueOf(this.jAmountTextField.getText()));
						if(checkPrice == 0)
						{
							JOptionPane.showMessageDialog(this, "Inserire un prezzo diverso da 0 per il prodotto",
									"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							magazzino.salvaMagazzino("media/saves/save21.mag");
							if(p.getCategoria().equals(prod.getCategoria()) &&
									p.getCodice().equals(prod.getCodice()) &&
									p.getMarca().equals(prod.getMarca()) &&
									p.getNome().equals(prod.getNome()) &&
									p.getPrezzo() == prod.getPrezzo() &&
									p.getQuantita() == prod.getQuantita())
							{
								JOptionPane.showMessageDialog(this, "Modifica effettuata con successo",
										"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
								this.jModifyProductDialog.setVisible(false);
							}
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Selezionare un'offerta per il prodotto",
							"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Il codice inserito è gia presente nel magazzino per un altro prodotto",
						"Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
		}	
	}
}
