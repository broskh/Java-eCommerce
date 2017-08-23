package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negozio.Magazzino;
import negozio.Prodotto;
import negozio.Promozione;
import negozio.ScontoPercentuale;
import negozio.ScontoTrePerDue;

public class JAddProductPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	
	private Prodotto prodotto;
	private Magazzino magazzino;
	
	
	private JLabel jCodiceLabel;
	protected JTextField jCodiceTextField;
	private JLabel jNomeLabel;
	protected JTextField jNomeTextField;
	private JLabel jMarcaLabel;
	protected JTextField jMarcaTextField;
	private JLabel jCategoriaLabel;
	protected JTextField jCategoriaTextField;
	private JLabel jPrezzoLabel;
	protected JTextField jPrezzoTextField;
	private JLabel jQuantitaLabel;
	protected JTextField jQuantitaTextField;
	private JLabel jOffertaLabel;
	protected JRadioButton jOffertaNoRadioButton;
	protected JRadioButton jOffertaPercentualeRadioButton;
	protected JRadioButton jOffertaTrePerDueRadioButton;
	protected JTextField jPercentualeTextField;
	protected JButton jAggiungiButton;
	
	
	private JLabel jEmptyLabel1;
	private JLabel jEmptyLabel2;
	
	
	
	private static final int LARGHEZZA_TEXTBOX = 10;
	private static final int N_RIGHE_PANNELLO_AGGIUNGI = 6;
	private static final int N_COLONNE_PANNELLO_AGGIUNGI = 2;
	
	
	private static final String TESTO_CODICE_LABEL = "Codice:";
	private static final String TESTO_NOME_LABEL = "Nome:";
	private static final String TESTO_MARCA_LABEL = "Marca:";
	private static final String TESTO_CATEGORIA_LABEL = "Categoria:";
	private static final String TESTO_PREZZO_LABEL = "Prezzo:";
	private static final String TESTO_QUANTITA_LABEL = "Quantità: ";
	private static final String TESTO_OFFERTA_LABEL = "Offerta:";
	private static final String TESTO_OFFERTA_NO_RADIO_BUTTON = "No";
	private static final String TESTO_OFFERTA_PERCENTUALE_RADIO_BUTTON = "Percentuale";
	private static final String TESTO_OFFERTA_TRE_PER_DUE_RADIO_BUTTON = "3x2";
	private static final String TESTO_AGGIUNGI_BUTTON = "Aggiungi";
	
	
	private static final String TESTO_EMPTY_LABEL = "";
	
	
	
	
	
	
	public JAddProductPanel()
	{
		
		
		this.jCodiceLabel = new JLabel(this.TESTO_CODICE_LABEL);
		this.jCodiceTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		this.jNomeLabel = new JLabel(this.TESTO_NOME_LABEL);
		this.jNomeTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		this.jMarcaLabel = new JLabel(this.TESTO_MARCA_LABEL);
		this.jMarcaTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		this.jCategoriaLabel = new JLabel(this.TESTO_CATEGORIA_LABEL);
		this.jCategoriaTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		this.jPrezzoLabel = new JLabel(this.TESTO_PREZZO_LABEL);
		this.jPrezzoTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		this.jQuantitaLabel = new JLabel(this.TESTO_QUANTITA_LABEL);
		this.jQuantitaTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		this.jOffertaLabel = new JLabel(this.TESTO_OFFERTA_LABEL);
		this.jPercentualeTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		this.jOffertaNoRadioButton = new JRadioButton(this.TESTO_OFFERTA_NO_RADIO_BUTTON);
		this.jOffertaPercentualeRadioButton = new JRadioButton(this.TESTO_OFFERTA_PERCENTUALE_RADIO_BUTTON);
		this.jOffertaTrePerDueRadioButton = new JRadioButton(this.TESTO_OFFERTA_TRE_PER_DUE_RADIO_BUTTON);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.jOffertaNoRadioButton);
		buttonGroup.add(this.jOffertaPercentualeRadioButton);
		buttonGroup.add(this.jOffertaTrePerDueRadioButton);
		this.jAggiungiButton = new JButton(this.TESTO_AGGIUNGI_BUTTON);
		

		
		
		this.jEmptyLabel1 = new JLabel(this.TESTO_EMPTY_LABEL);
		this.jEmptyLabel2 = new JLabel(this.TESTO_EMPTY_LABEL);

		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		JPanel pannelloAggiungi = new JPanel(new GridLayout(N_RIGHE_PANNELLO_AGGIUNGI,N_COLONNE_PANNELLO_AGGIUNGI));
		pannelloAggiungi.add(this.jCodiceLabel);
		pannelloAggiungi.add(this.jCodiceTextField);
		pannelloAggiungi.add(this.jNomeLabel);
		pannelloAggiungi.add(this.jNomeTextField);
		pannelloAggiungi.add(this.jMarcaLabel);
		pannelloAggiungi.add(this.jMarcaTextField);
		pannelloAggiungi.add(this.jCategoriaLabel);
		pannelloAggiungi.add(this.jCategoriaTextField);
		pannelloAggiungi.add(this.jPrezzoLabel);
		pannelloAggiungi.add(this.jPrezzoTextField);
		pannelloAggiungi.add(this.jQuantitaLabel);
		pannelloAggiungi.add(this.jQuantitaTextField);
		
		
		
		JPanel pannelloOfferta = new JPanel(new GridLayout(4,2));
		pannelloOfferta.add(this.jOffertaLabel);
		pannelloOfferta.add(this.jEmptyLabel1);
		pannelloOfferta.add(this.jOffertaNoRadioButton);
		pannelloOfferta.add(this.jEmptyLabel2);
		pannelloOfferta.add(this.jOffertaPercentualeRadioButton);
		pannelloOfferta.add(this.jPercentualeTextField);
		pannelloOfferta.add(this.jOffertaTrePerDueRadioButton);
		
		this.add(pannelloAggiungi);
		this.add(pannelloOfferta);
		this.add(this.jAggiungiButton);
		this.jAggiungiButton.setActionCommand("aggiungi");
		this.jAggiungiButton.addActionListener(this);
	}






	@Override
	public void actionPerformed(ActionEvent e) {
		
		String prezzo;
		String quantita;
		Promozione promo;
		if(this.jOffertaNoRadioButton.isSelected())
		{
			if(this.jCodiceTextField.getText().equals("") || this.jNomeTextField.getText().equals("") || this.jMarcaTextField.getText().equals("") || this.jCategoriaTextField.getText().equals("") ||this.jPrezzoTextField.getText().equals("") || this.jQuantitaTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				prezzo = this.jPrezzoTextField.getText();
				quantita = this.jQuantitaTextField.getText();
				File f = null;
				this.prodotto = new Prodotto(this.jNomeTextField.getText(),this.jMarcaTextField.getText(),this.jCodiceTextField.getText(),this.jCategoriaTextField.getText(),Float.parseFloat(prezzo),f,Integer.parseInt(quantita));
				aggiungiProd(magazzino,prodotto);
			}
		}
		else if(this.jOffertaPercentualeRadioButton.isSelected())
		{
			if(this.jCodiceTextField.getText().equals("") || this.jNomeTextField.getText().equals("") || this.jMarcaTextField.getText().equals("") || this.jCategoriaTextField.getText().equals("") ||this.jPrezzoTextField.getText().equals("") || this.jQuantitaTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
			else if(this.jPercentualeTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Inserire il valore della percentuale da scontare","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				promo = new ScontoPercentuale(Integer.parseInt(this.jPercentualeTextField.getText()));
				prezzo = this.jPrezzoTextField.getText();
				quantita = this.jQuantitaTextField.getText();
				File f = null;
				this.prodotto = new Prodotto(this.jNomeTextField.getText(),this.jMarcaTextField.getText(),this.jCodiceTextField.getText(),this.jCategoriaTextField.getText(),Float.parseFloat(prezzo),f,Integer.parseInt(quantita),promo);
				aggiungiProd(magazzino,prodotto);
			}
		}
		else if(this.jOffertaTrePerDueRadioButton.isSelected())
		{
			if(this.jCodiceTextField.getText().equals("") || this.jNomeTextField.getText().equals("") || this.jMarcaTextField.getText().equals("") || this.jCategoriaTextField.getText().equals("") ||this.jPrezzoTextField.getText().equals("") || this.jQuantitaTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				promo = new ScontoTrePerDue();
				prezzo = this.jPrezzoTextField.getText();
				quantita = this.jQuantitaTextField.getText();
				File f = null;
				this.prodotto = new Prodotto(this.jNomeTextField.getText(),this.jMarcaTextField.getText(),this.jCodiceTextField.getText(),this.jCategoriaTextField.getText(),Float.parseFloat(prezzo),f,Integer.parseInt(quantita),promo);
				aggiungiProd(magazzino,prodotto);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Selezionare un'offerta per il prodotto","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
		}
	}






	private void aggiungiProd(Magazzino magazzino, Prodotto prodotto) 
	{
		magazzino.aggiungiProdotto(prodotto);
	}
}



