package grafica;

import java.awt.Dimension;
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
import java.util.logging.Level;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.PlainDocument;
//import javax.xml.datatype.DatatypeConstants.Field;
import java.lang.reflect.*;
//import com.sun.java.util.jar.pack.Package.Class.Field;

import negozio.Magazzino;
import negozio.Prodotto;
import negozio.Promozione;
import negozio.ScontoPercentuale;
import negozio.ScontoTrePerDue;


public class JAddProductPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -6881271668480137290L;
	
	protected Prodotto prodotto;
	private Magazzino magazzino;
	
	/**/
	private JPanel pannelloAggiungi;
	private JPanel pannelloOfferta;
	private JPanel imagePanel;
	/**/
	
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
	
	protected JLabel jImmagineLabel; //
	protected JButton jImmagineButton;
	
	
	
	private JLabel jEmptyLabel1;
	private JLabel jEmptyLabel2;
	
	
	
	private static final int LARGHEZZA_TEXTBOX = 10;
	private static final int N_RIGHE_PANNELLO_AGGIUNGI = 6;
	private static final int N_COLONNE_PANNELLO_AGGIUNGI = 2;
	private static final int DIMENSIONE_ICONA = 120; //
	
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
	private static final String TESTO_IMMAGINE_BUTTON =  "Cambia";
	
	
	
	private static final String TESTO_EMPTY_LABEL = "";
	
	
	
	
	
	
	public JAddProductPanel(Magazzino magazzino)
	{
		this.magazzino = magazzino;
		/* ROBA PER IMMAGINE */
		this.prodotto = new Prodotto("","","","",0,"media/img/immagine_non_disponibile.jpg",0,null);
		this.jImmagineLabel = new JLabel("",SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon(this.ridimensionaImmagine(
				this.prodotto.getImmagine()),this.prodotto.getImmagine().toString());
		if(icon!=null)
		{
			this.jImmagineLabel.setIcon(icon);
		}
		
		/*JPanel*/ imagePanel = new JPanel();
		this.jImmagineButton = new JButton(this.TESTO_IMMAGINE_BUTTON);
		imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.X_AXIS));
		imagePanel.add(Box.createVerticalStrut(10));
		imagePanel.add(this.jImmagineLabel);
		imagePanel.add(Box.createHorizontalStrut(20));
		imagePanel.add(jImmagineButton);
		this.jImmagineButton.setActionCommand("cambia");
		this.jImmagineButton.addActionListener(this);
		imagePanel.add(Box.createVerticalStrut(10));
		/* FINE ROBA PER IMMAGINE */
		
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
		/* CONTROLLO TEXT FIELD PREZZO */
		/*PlainDocument docP = (PlainDocument)this.jPrezzoTextField.getDocument();
		docP.setDocumentFilter(new PriceDocumentFilter());*/
		
		this.jQuantitaLabel = new JLabel(this.TESTO_QUANTITA_LABEL);
		this.jQuantitaTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		/* CONTROLLO TEXT FIELD QUANTITA */
		PlainDocument docQ = (PlainDocument)this.jQuantitaTextField.getDocument();
		docQ.setDocumentFilter(new AmountDocumentFilter());
		
		
		this.jOffertaLabel = new JLabel(this.TESTO_OFFERTA_LABEL);
		this.jPercentualeTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		/* CONTROLLO TEXT FIELD QUANTITA */
		PlainDocument docPC = (PlainDocument)this.jPercentualeTextField.getDocument();
		docPC.setDocumentFilter(new AmountDocumentFilter());
		
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
		
		
		/*JPanel*/ pannelloAggiungi = new JPanel(new GridLayout(N_RIGHE_PANNELLO_AGGIUNGI,N_COLONNE_PANNELLO_AGGIUNGI));
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
		
		
		
		/*JPanel*/ pannelloOfferta = new JPanel(new GridLayout(4,2));
		pannelloOfferta.add(this.jOffertaLabel);
		pannelloOfferta.add(this.jEmptyLabel1);
		pannelloOfferta.add(this.jOffertaNoRadioButton);
		pannelloOfferta.add(this.jEmptyLabel2);
		pannelloOfferta.add(this.jOffertaPercentualeRadioButton);
		pannelloOfferta.add(this.jPercentualeTextField);
		pannelloOfferta.add(this.jOffertaTrePerDueRadioButton);
		
		this.add(imagePanel);
		this.add(pannelloAggiungi);
		this.add(pannelloOfferta);
		this.add(this.jAggiungiButton);
		this.jAggiungiButton.setActionCommand("aggiungi");
		this.jAggiungiButton.addActionListener(this);
		
		
	}


	
	
	
	protected Image ridimensionaImmagine (File immagine) {
		BufferedImage bimg;
		try {
			bimg = ImageIO.read(immagine);
			// Calcolo le giuste dimensioni per l'icona
			int original_width = bimg.getWidth();
		    int original_height = bimg.getHeight();
		    int bound_width = DIMENSIONE_ICONA;
		    int bound_height = DIMENSIONE_ICONA;
		    int new_width = original_width;
		    int new_height = original_height;
		    if (original_width > bound_width) {
		        new_width = bound_width;
		        new_height = (new_width * original_height) / original_width;
		    }
		    if (new_height > bound_height) {
		        new_height = bound_height;
		        new_width = (new_height * original_width) / original_height;
		    }
			
		    // Ridimensiono l'immagine
		    BufferedImage resizedImg = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = resizedImg.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(bimg, 0, 0, new_width, new_height, null);
		    g2.dispose();

		    return resizedImg;
		} catch (IOException e) {
			e.printStackTrace();			
			return null;
		}		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("cambia"))
		{
			//FileSystemView fsv = new DirectoryRestrictedFileSystemView(new File("C:\\Users\\alessio\\ecommerce\\Java-eCommerce\\media\\img"));
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setMultiSelectionEnabled(false);
			ImageIcon newIcon = new ImageIcon(this.ridimensionaImmagine(fc.getSelectedFile()),fc.getSelectedFile().toString());
			if(newIcon!=null)
			{
				this.jImmagineLabel.setIcon(newIcon);
			}
			 this.validate();
		     this.repaint();
			
		}
		else if(e.getActionCommand().equals("aggiungi"))
		{
			ArrayList <Prodotto> articoli = new <Prodotto> ArrayList();
			String prezzo;
			String quantita;
			Promozione promo;
			int controllo = 0;
			articoli = magazzino.getArticoli();
			

			for(int i = 0;i<articoli.size();i++)
			{
				if(articoli.get(i).getCodice().equals(this.jCodiceTextField.getText()))
				{
					JOptionPane.showMessageDialog(this, "Il prodotto che si vuole inserire è gia presente in magazzino","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					controllo = 1;
				}
			}
			
			if(controllo == 0)
			{
				if(this.jOffertaNoRadioButton.isSelected())
				{
					if(this.jCodiceTextField.getText().equals("") || this.jNomeTextField.getText().equals("") || this.jMarcaTextField.getText().equals("") || this.jCategoriaTextField.getText().equals("") ||this.jPrezzoTextField.getText().equals("") || this.jQuantitaTextField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						ImageIcon imgicon = (ImageIcon) this.jImmagineLabel.getIcon();
						
						prezzo = this.jPrezzoTextField.getText();
						quantita = this.jQuantitaTextField.getText();
						this.prodotto = new Prodotto(this.jNomeTextField.getText(),this.jMarcaTextField.getText(),this.jCodiceTextField.getText(),this.jCategoriaTextField.getText(),Float.parseFloat(prezzo),imgicon.getDescription(),Integer.parseInt(quantita));
						magazzino.aggiungiProdotto(prodotto);
						magazzino.salvaMagazzino("media/saves/save21.mag");
						JOptionPane.showMessageDialog(this, "Podotto inserito correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
						System.out.println(prodotto);
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
						ImageIcon imgicon = (ImageIcon) this.jImmagineLabel.getIcon();
						
						promo = new ScontoPercentuale(Integer.parseInt(this.jPercentualeTextField.getText()));
						prezzo = this.jPrezzoTextField.getText();
						quantita = this.jQuantitaTextField.getText();
						this.prodotto = new Prodotto(this.jNomeTextField.getText(),this.jMarcaTextField.getText(),this.jCodiceTextField.getText(),this.jCategoriaTextField.getText(),Float.parseFloat(prezzo),imgicon.getDescription(),Integer.parseInt(quantita),promo);
						magazzino.aggiungiProdotto(prodotto);
						magazzino.salvaMagazzino("media/saves/save21.mag");
						JOptionPane.showMessageDialog(this, "Podotto inserito correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
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
						ImageIcon imgicon = (ImageIcon) this.jImmagineLabel.getIcon();
						
						promo = new ScontoTrePerDue();
						prezzo = this.jPrezzoTextField.getText();
						quantita = this.jQuantitaTextField.getText();
						this.prodotto = new Prodotto(this.jNomeTextField.getText(),this.jMarcaTextField.getText(),this.jCodiceTextField.getText(),this.jCategoriaTextField.getText(),Float.parseFloat(prezzo),imgicon.getDescription(),Integer.parseInt(quantita),promo);
						magazzino.aggiungiProdotto(prodotto);
						magazzino.salvaMagazzino("media/saves/save21.mag");
						JOptionPane.showMessageDialog(this, "Podotto inserito correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Selezionare un'offerta per il prodotto","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
				}
			}

			this.jCodiceTextField.setText("");
			this.jNomeTextField.setText("");
			this.jMarcaTextField.setText("");
			this.jCategoriaTextField.setText("");
			this.jPrezzoTextField.setText("");
			this.jQuantitaTextField.setText("0");
			this.jPercentualeTextField.setText("0");
			this.jOffertaNoRadioButton.setSelected(true);
			this.jOffertaPercentualeRadioButton.setSelected(false);
			this.jOffertaTrePerDueRadioButton.setSelected(false);
			Prodotto p = new Prodotto("","","","",0,"media/img/immagine_non_disponibile.jpg",0,null);
			ImageIcon icon = new ImageIcon(this.ridimensionaImmagine(p.getImmagine()));
			if(icon!=null)
			{
				this.jImmagineLabel.setIcon(icon);
			}

		}
		
		
		
	}
	
}



