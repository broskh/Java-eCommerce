package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.PlainDocument;

import negozio.Prodotto;
import negozio.Promozione;
import negozio.ScontoPercentuale;
import negozio.ScontoTrePerDue;

public class JModifyProductPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 3050586973997645118L;

	private Prodotto product;

	private JLabel imageLabel; 
	private JButton imageButton;
	private JTextField codeTextField;
	private JTextField nameTextField;
	private JTextField brandTextField;
	private JTextField categoryTextField;
	private JTextField costTextField;
	private JTextField amountTextField;
	private JTextField percentTextField;
	private JButton modifyButton;
	private JRadioButton noOfferRadioButton;
	private JRadioButton percentOfferRadioButton;
	private JRadioButton threePerTwoOfferRadioButton;

	private static final int ICON_SIZE = 120;
	private static final int TEXTBOX_WIDTH = 10;
	private static final int IMAGE_SPACE = 40;
	
	private static final int ATTRIBUTESPANEL_ROWS = 10;
	private static final int ATTIRUBTESPANEL_COLUMNS = 2;

	private static final String IMAGE_BUTTON_TEXT =  "Cambia";
	private static final String IMAGE_LABEL_TEXT = "Immagine non disponibile";
	private static final String CODE_LABEL_TEXT = "Codice:";
	private static final String NAME_LABEL_TEXT = "Nome:";
	private static final String BRAND_LABEL_TEXT = "Marca:";
	private static final String CATEGORY_LABEL_TEXT = "Categoria:";
	private static final String PRICE_LABEL_TEXT = "Prezzo:";
	private static final String AMOUNT_LABEL_TEXT = "Quantità: ";
	private static final String OFFER_LABEL_TEXT = "Offerta:";
	private static final String NO_OFFER_TEXT = "Nessuna";
	private static final String PERCENT_OFFER_TEXT = "Percentuale";
	private static final String THREE_PER_TWO_OFFER__TEXT = "3x2";

	private static final String DEFAULT_PERCENT_VALUE = "0";
	
	public JModifyProductPanel () {
		this (new Prodotto ()); 
	}
	
	public JModifyProductPanel(Prodotto product) {
		this.product = product;
		
		this.imageLabel = new JLabel("",SwingConstants.CENTER);		
		ImageIcon icon;
		try {
			icon = new ImageIcon(new ResizableIcon(this.product.getImmagine()).
					resizeIcon(ICON_SIZE, ICON_SIZE), this.product.getImmagine().toString());
			this.imageLabel.setIcon(icon);
		} catch (IOException e) {
			this.imageLabel.setText(IMAGE_LABEL_TEXT);
		}
		this.imageButton = new JButton(IMAGE_BUTTON_TEXT);
		this.imageButton.addActionListener(this);
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.X_AXIS));
		imagePanel.add(this.imageLabel);
		imagePanel.add(Box.createRigidArea(new Dimension(IMAGE_SPACE, ICON_SIZE)));
		imagePanel.add(imageButton);
		
		JLabel codeLabel = new JLabel(CODE_LABEL_TEXT);
		this.codeTextField = new JTextField(this.product.getCodice(), TEXTBOX_WIDTH);
		JLabel nameLabel = new JLabel(NAME_LABEL_TEXT);
		this.nameTextField = new JTextField(this.product.getNome(), TEXTBOX_WIDTH);
		JLabel brandLabel = new JLabel(BRAND_LABEL_TEXT);
		this.brandTextField = new JTextField(this.product.getMarca(), TEXTBOX_WIDTH);
		JLabel categoryLabel = new JLabel(CATEGORY_LABEL_TEXT);
		this.categoryTextField = new JTextField(this.product.getCategoria(), TEXTBOX_WIDTH);
		JLabel costLabel = new JLabel(PRICE_LABEL_TEXT);
		this.costTextField = new JTextField(Float.toString(this.product.getPrezzo()), TEXTBOX_WIDTH);
		PlainDocument pdCost = (PlainDocument)this.costTextField.getDocument();
		pdCost.setDocumentFilter(new PriceDocumentFilter());
		JLabel amountLabel = new JLabel(AMOUNT_LABEL_TEXT);
		this.amountTextField = new JTextField(Integer.toString(this.product.getQuantita()),TEXTBOX_WIDTH);
		PlainDocument pdAmount = (PlainDocument)this.amountTextField.getDocument();
		pdAmount.setDocumentFilter(new AmountDocumentFilter());
		
		JLabel offerLabel = new JLabel(OFFER_LABEL_TEXT);
		this.percentTextField = new JTextField(TEXTBOX_WIDTH);
		PlainDocument docPC = (PlainDocument)this.percentTextField.getDocument();
		docPC.setDocumentFilter(new AmountDocumentFilter(100));
		this.percentTextField.setText(DEFAULT_PERCENT_VALUE);
		this.noOfferRadioButton = new JRadioButton(NO_OFFER_TEXT);
		this.noOfferRadioButton.addActionListener(this);
		this.percentOfferRadioButton = new JRadioButton(
				PERCENT_OFFER_TEXT);		
		this.percentOfferRadioButton.addActionListener(this);
		this.threePerTwoOfferRadioButton = new JRadioButton(
				THREE_PER_TWO_OFFER__TEXT);		
		this.threePerTwoOfferRadioButton.addActionListener(this);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.noOfferRadioButton);
		buttonGroup.add(this.percentOfferRadioButton);
		buttonGroup.add(this.threePerTwoOfferRadioButton);
		this.selectCorrectOffer ();
		this.modifyButton = new JButton();
		
		JPanel fieldsPanel = new JPanel(new GridLayout(ATTRIBUTESPANEL_ROWS,ATTIRUBTESPANEL_COLUMNS));
		fieldsPanel.add(codeLabel);
		fieldsPanel.add(this.codeTextField);
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(this.nameTextField);
		fieldsPanel.add(brandLabel);
		fieldsPanel.add(this.brandTextField);
		fieldsPanel.add(categoryLabel);
		fieldsPanel.add(this.categoryTextField);
		fieldsPanel.add(costLabel);
		fieldsPanel.add(this.costTextField);
		fieldsPanel.add(amountLabel);
		fieldsPanel.add(this.amountTextField);
		fieldsPanel.add(offerLabel);
		fieldsPanel.add(Box.createGlue());
		fieldsPanel.add(this.noOfferRadioButton);
		fieldsPanel.add(Box.createGlue());
		fieldsPanel.add(this.percentOfferRadioButton);
		fieldsPanel.add(this.percentTextField);
		fieldsPanel.add(this.threePerTwoOfferRadioButton);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(imagePanel);
		this.add(fieldsPanel);
		this.add(this.modifyButton);
	}
	
	private void selectCorrectOffer () {
		if(this.product.getOfferta() == null)
		{
			this.noOfferRadioButton.setSelected(true);
			this.percentTextField.setEnabled(false);
		}
		else if(this.product.getOfferta().getClass().equals(ScontoTrePerDue.class))
		{
			this.threePerTwoOfferRadioButton.setSelected(true);
			this.percentTextField.setEnabled(false);
		}
		else if (this.product.getOfferta().getClass().equals(ScontoPercentuale.class))
		{
			this.percentOfferRadioButton.setSelected(true);
			this.percentTextField.setText(Integer.toString(((ScontoPercentuale) this.product.getOfferta()).getPercentuale()));
		}
	}
	
	public File getImmagine () {
		return new File (((ImageIcon)this.imageLabel.getIcon()).getDescription());
	}
	
	public String getProductCode () {
		return this.codeTextField.getText();
	}
	
	public String getProductName () {
		return this.nameTextField.getText();
	}
	
	public String getProductBrand () {
		return this.brandTextField.getText();
	}
	
	public String getProductCategory () {
		return this.categoryTextField.getText();
	}
	
	public float getProductCost () {
		return Float.parseFloat(this.costTextField.getText());
	}
	
	public int getProductAmount () {
		return Integer.parseInt(this.amountTextField.getText());
	}
	
	public Promozione getProductOffer () {
		if (this.noOfferRadioButton.isSelected()) {
			return null;
		}
		else if (this.percentOfferRadioButton.isSelected()) {
			int percent = Integer.parseInt(this.percentTextField.getText());
			if (percent != 0) {
				return new ScontoPercentuale(percent);
			}
			else {
				return null;
			}
		}
		else if (this.threePerTwoOfferRadioButton.isSelected()) {
			return new ScontoTrePerDue();
		}
		return null;
	}
	
	public void setButtonActionListener (ActionListener actionListener) {
		this.modifyButton.addActionListener(actionListener);
	}
	
	public void setButtonText (String buttonText) {
		this.modifyButton.setText(buttonText);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.percentOfferRadioButton))
		{
			this.percentTextField.setEnabled(true);
		}
		else if(e.getSource().equals(this.noOfferRadioButton))
		{
			this.percentTextField.setEnabled(false);
		}
		else if(e.getSource().equals(this.threePerTwoOfferRadioButton))
		{
			this.percentTextField.setEnabled(false);
		}
		else if(e.getSource().equals(this.imageButton)) //DA CONTROLLARE
		{
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setMultiSelectionEnabled(false);
			if(returnVal == 0)
			{
				ResizableIcon newIcon;
				try {
					newIcon = new ResizableIcon(
							fc.getSelectedFile(),ICON_SIZE, ICON_SIZE);
					this.imageLabel.setIcon(new ImageIcon(
							newIcon.getBufferedImage(), fc.getSelectedFile().toString()));
					this.updateUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block IMMAGINE NON CORRETTA
					e1.printStackTrace();
				}
			}			
		}
	}
}
