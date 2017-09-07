package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashSet;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;
import negozio.Prodotto;

public class JAddProductToStoreDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = -3173951653228607174L;
	
	private Magazzino store;
	private Prodotto product;
	private HashSet <Prodotto> articlesAdded;

	private JModifyProductPanel modifyProductPanel;
	private JStoreTable jStoreTable;

	protected static final int FRAME_HEIGHT = 450;
	protected static final int FRAME_WIDTH = 300;
	
	private static final String BUTTON_TEXT = "AGGIUNGI";
	private static final String ALERT_ERROR_TITLE = "Attenzione!";
	private static final String ALERT_SUCCESS_TITLE = "Aggiunta avvenuta";
	private static final String EMPTY_FIELDS_TEXT = "Inserire tutti i dati correttamente.";
	private static final String NO_COST_TEXT = "Inserire un prezzo diverso da 0.";
	private static final String EXSISTING_PRODUCT_TEXT = "È già presente nel magazzino un prodotto con questo codice.";
	private static final String SUCCESS_TEXT = "Aggiunta effettuata con successo.";
	
	protected static final String TITLE = "Aggiungi prodotto";
	
	public JAddProductToStoreDialog (JFrame mainFrame, Magazzino store, JStoreTable jStoreTable, HashSet <Prodotto> articlesAdded) {
		super(mainFrame, TITLE, ModalityType.DOCUMENT_MODAL);
		this.store = store;
		this.jStoreTable = jStoreTable;
		this.articlesAdded = articlesAdded;
		this.product = new Prodotto();

		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.modifyProductPanel = new JModifyProductPanel(this.product);
		this.modifyProductPanel.setButtonText(BUTTON_TEXT);
		this.modifyProductPanel.setButtonActionListener(this);

		this.setLayout(new BorderLayout());
		this.add(this.modifyProductPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.modifyProductPanel.getProductName().equals("") && 
				!this.modifyProductPanel.getProductCode().equals("") &&
				!this.modifyProductPanel.getProductBrand().equals("") &&
				!this.modifyProductPanel.getProductCategory().equals("")) {
			if (this.modifyProductPanel.getProductCost() == 0) {
				JOptionPane.showMessageDialog(this, NO_COST_TEXT,
						ALERT_ERROR_TITLE,JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (this.store.getProdotto(this.modifyProductPanel.getProductCode()) != null) {
				JOptionPane.showMessageDialog(this, EXSISTING_PRODUCT_TEXT,
						ALERT_ERROR_TITLE,JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			this.product.setImmagine(this.modifyProductPanel.getImmagine());
			this.product.setCodice(this.modifyProductPanel.getProductCode());
			this.product.setNome(this.modifyProductPanel.getProductName());
			this.product.setCategoria(this.modifyProductPanel.getProductCategory());
			this.product.setMarca(this.modifyProductPanel.getProductBrand());
			this.product.setPrezzo(this.modifyProductPanel.getProductCost());
			this.product.setQuantita(this.modifyProductPanel.getProductAmount());
			this.product.setOfferta(this.modifyProductPanel.getProductOffer());
			this.store.aggiungiProdotto(this.product);
			this.articlesAdded.add(this.product);
			((ArticlesTableModel)jStoreTable.getModel()).fireTableDataChanged();
			
			JOptionPane.showMessageDialog(this, SUCCESS_TEXT,
					ALERT_SUCCESS_TITLE,JOptionPane.INFORMATION_MESSAGE);
			this.dispose ();
		}
		else {
			JOptionPane.showMessageDialog(this, EMPTY_FIELDS_TEXT,
					ALERT_ERROR_TITLE,JOptionPane.INFORMATION_MESSAGE);
		}
	}
//		ArrayList <Prodotto> articles = new ArrayList<Prodotto>();
//		String price;
//		String amount;
//		Promozione promo;
//		int check = 0;
//		articles = this.store.getArticoli();
//		
//
//		for(int i = 0;i<articles.size();i++)
//		{
//			if(articles.get(i).getCodice().equals(this.jCodeTextField.getText()))
//			{
//				JOptionPane.showMessageDialog(this,
//						PRODUCT_ALREADY_EXIST_STRING,
//						ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//				check = 1;
//			}
//		}
//		
//		if(check == 0)
//		{
//			if(this.jNoOfferRadioButton.isSelected())
//			{
//				if(this.jCodeTextField.getText().equals("") ||
//						this.jNameTextField.getText().equals("") ||
//						this.jBrandTextField.getText().equals("") ||
//						this.jCategoryTextField.getText().equals("") ||
//						this.jPriceTextField.getText().equals("") ||
//						this.jAmountTextField.getText().equals(""))
//				{
//					JOptionPane.showMessageDialog(this, ALL_DATA_STRING,
//							ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//				}
//				else
//				{
//					Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
//					ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
//					
//					price = this.jPriceTextField.getText();
//					amount = this.jAmountTextField.getText();
//					if(checkPrice == 0)
//					{
//						JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
//								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//					}
//					else
//					{
//						this.product = new Prodotto(this.jNameTextField.getText(),
//								this.jBrandTextField.getText(),this.jCodeTextField.getText(),
//								this.jCategoryTextField.getText(),Float.parseFloat(price),
//								imgicon.getDescription(),Integer.parseInt(amount));
//						
//						/* SALVATAGGIO IMMAGINE */		
//						this.imageToSave.add(product);
//						this.image.add(imgicon.getImage());
//						
//						this.store.aggiungiProdotto(product);
//						JOptionPane.showMessageDialog(this, RIGHT_INSERT_STRING,
//								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//
//						this.setVisible(false);
//						
//						jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(),
//								LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
//						jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
//						jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
//						this.store.getArticoli(), LINE_HEIGHT));
//					}
//				}
//			}
//			else if(this.jPercentOfferRadioButton.isSelected())
//			{
//				int checkPercent = Integer.valueOf(this.jPercentTextField.getText());
//				if(this.jCodeTextField.getText().equals("") ||
//						this.jNameTextField.getText().equals("") ||
//						this.jBrandTextField.getText().equals("") ||
//						this.jCategoryTextField.getText().equals("") ||
//						this.jPriceTextField.getText().equals("") ||
//						this.jAmountTextField.getText().equals(""))
//				{
//					JOptionPane.showMessageDialog(this, ALL_DATA_STRING,
//							ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//				}
//				else if(this.jPercentTextField.getText().equals(""))
//				{
//					JOptionPane.showMessageDialog(this, PERCENT_REQUEST_STRING,
//							ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//				}
//				else if(checkPercent == 0)
//				{
//					JOptionPane.showMessageDialog(this,
//							PERCENT_VALUE_NOT_ZERO_STRING,
//							ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//				}
//				else
//				{
//					Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
//					ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
//					
//					promo = new ScontoPercentuale(Integer.parseInt(this.jPercentTextField.getText()));
//					price = this.jPriceTextField.getText();
//					amount = this.jAmountTextField.getText();
//					if(checkPrice == 0)
//					{
//						JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
//								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//					}
//					else
//					{
//						this.product = new Prodotto(this.jNameTextField.getText(),
//								this.jBrandTextField.getText(),this.jCodeTextField.getText(),
//								this.jCategoryTextField.getText(),Float.parseFloat(price),
//								imgicon.getDescription(),Integer.parseInt(amount),promo);
//						
//						/* SALVATAGGIO IMMAGINE */		
//						this.imageToSave.add(product);
//						this.image.add(imgicon.getImage());
//
//						this.store.aggiungiProdotto(product);
//						JOptionPane.showMessageDialog(this, RIGHT_INSERT_STRING,
//								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//						
//						jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(),
//								LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
//						jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
//						jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
//						this.store.getArticoli(), LINE_HEIGHT));
//						
//						this.setVisible(false);
//					}
//					
//				}
//			}
//			else if(this.jThreePerTwoOfferRadioButton.isSelected())
//			{
//				if(this.jCodeTextField.getText().equals("") ||
//						this.jNameTextField.getText().equals("") ||
//						this.jBrandTextField.getText().equals("") ||
//						this.jCategoryTextField.getText().equals("") ||
//						this.jPriceTextField.getText().equals("") ||
//						this.jAmountTextField.getText().equals(""))
//				{
//					JOptionPane.showMessageDialog(this, ALL_DATA_STRING,
//							ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//				}
//				else
//				{
//					Float checkPrice = Float.valueOf(this.jPriceTextField.getText());
//					ImageIcon imgicon = (ImageIcon) this.jImageLabel.getIcon();
//					
//					promo = new ScontoTrePerDue();
//					price = this.jPriceTextField.getText();
//					amount = this.jAmountTextField.getText();
//					if(checkPrice == 0)
//					{
//						JOptionPane.showMessageDialog(this, PRICE_NOT_ZERO_STRING,
//								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//					}
//					else
//					{
//						this.product = new Prodotto(this.jNameTextField.getText(),
//								this.jBrandTextField.getText(),this.jCodeTextField.getText(),
//								this.jCategoryTextField.getText(),Float.parseFloat(price),
//								imgicon.getDescription(),Integer.parseInt(amount),promo);
//						
//						/* SALVATAGGIO IMMAGINE */		
//						this.imageToSave.add(product);
//						this.image.add(imgicon.getImage());
//	
//						this.store.aggiungiProdotto(product);
//						JOptionPane.showMessageDialog(this, RIGHT_INSERT_STRING,
//								ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//						
//						jStoreTable.setModel(new ArticlesTableModel(this.store.getArticoli(),
//								LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
//						jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
//						jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
//						this.store.getArticoli(), LINE_HEIGHT));
//								
//						this.setVisible(false);
//					}
//					
//				}
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(this, SELECT_OFFER_STRING,
//						ALERT_STRING,JOptionPane.INFORMATION_MESSAGE);
//			}
//		}
//	}
}