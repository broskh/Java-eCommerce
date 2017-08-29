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
	
	
	private Magazzino magazzino;
	private Prodotto p;
	private Promozione promo;


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
	protected JButton jModificaButton;
	
	
	private JLabel jImmagineLabel;
	protected JButton jImmagineButton;
	
	private JLabel jEmptyLabel1;
	private JLabel jEmptyLabel2;
	
	
	
	
	
	
	private static final int LARGHEZZA_TEXTBOX = 10;
	private static final int N_RIGHE_PANNELLO_MODIFICA = 6;
	private static final int N_COLONNE_PANNELLO_MODIFICA = 2;
	private static final int DIMENSIONE_ICONA = 120; //
	
	private static final String TESTO_CODICE_LABEL = "Codice:";
	private static final String TESTO_NOME_LABEL = "Nome:";
	private static final String TESTO_MARCA_LABEL = "Marca:";
	private static final String TESTO_CATEGORIA_LABEL = "Categoria:";
	private static final String TESTO_PREZZO_LABEL = "Prezzo:";
	private static final String TESTO_QUANTITA_LABEL = "Quantit�:";
	private static final String TESTO_OFFERTA_LABEL = "Offerta:";
	private static final String TESTO_OFFERTA_NO_RADIO_BUTTON = "No";
	private static final String TESTO_OFFERTA_PERCENTUALE_RADIO_BUTTON = "Percentuale";
	private static final String TESTO_OFFERTA_TRE_PER_DUE_RADIO_BUTTON = "3x2";
	private static final String TESTO_MODIFICA_BUTTON = "Modifica";
	private static final String TESTO_IMMAGINE_BUTTON = "Cambia";
	
	
	private static final String TESTO_EMPTY_LABEL = "";
	
	
	
	
	public JModifyProductPanel(Prodotto p, Magazzino magazzino)
	{
		
		
		//p.setImmagine(new File("media/img/immagine_non_disponibile.jpg"));
		this.p = p;
		this.magazzino = magazzino;
		
		/* ROBA PER IMMAGINE */
		
		this.jImmagineLabel = new JLabel("",SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon(this.ridimensionaImmagine(this.p.getImmagine()),this.p.getImmagine().toString());
		if(icon != null)
		{
			this.jImmagineLabel.setIcon(icon);
		}
		JPanel imagePanel = new JPanel();
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
		jCodiceTextField.setText(p.getCodice());
		this.jNomeLabel = new JLabel(this.TESTO_NOME_LABEL);
		this.jNomeTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		jNomeTextField.setText(p.getNome());
		this.jMarcaLabel = new JLabel(this.TESTO_MARCA_LABEL);
		this.jMarcaTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		jMarcaTextField.setText(p.getMarca());
		this.jCategoriaLabel = new JLabel(this.TESTO_CATEGORIA_LABEL);
		this.jCategoriaTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		jCategoriaTextField.setText(p.getCategoria());
		this.jPrezzoLabel = new JLabel(this.TESTO_PREZZO_LABEL);
		this.jPrezzoTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		jPrezzoTextField.setText(Float.toString(p.getPrezzo()));
		/* CONTROLLO TEXT FIELD PREZZO */
		/*PlainDocument docP = (PlainDocument)this.jPrezzoTextField.getDocument();
		docP.setDocumentFilter(new PriceDocumentFilter());*/
		
		this.jQuantitaLabel = new JLabel(this.TESTO_QUANTITA_LABEL);
		this.jQuantitaTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		jQuantitaTextField.setText(Integer.toString(p.getQuantita()));
		/* CONTROLLO TEXT FIELD QUANTITA */
		PlainDocument docQ = (PlainDocument)this.jQuantitaTextField.getDocument();
		docQ.setDocumentFilter(new AmountDocumentFilter());
		
		this.jOffertaLabel = new JLabel(this.TESTO_OFFERTA_LABEL);
		this.jPercentualeTextField = new JTextField(this.LARGHEZZA_TEXTBOX);
		this.jOffertaNoRadioButton = new JRadioButton(this.TESTO_OFFERTA_NO_RADIO_BUTTON);
		this.jOffertaPercentualeRadioButton = new JRadioButton(this.TESTO_OFFERTA_PERCENTUALE_RADIO_BUTTON);
		this.jOffertaTrePerDueRadioButton = new JRadioButton(this.TESTO_OFFERTA_TRE_PER_DUE_RADIO_BUTTON);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.jOffertaNoRadioButton);
		buttonGroup.add(this.jOffertaPercentualeRadioButton);
		buttonGroup.add(this.jOffertaTrePerDueRadioButton);
		this.jModificaButton = new JButton(this.TESTO_MODIFICA_BUTTON);
		this.jModificaButton.addActionListener(this);
	

		
		
		this.jEmptyLabel1 = new JLabel(this.TESTO_EMPTY_LABEL);
		this.jEmptyLabel2 = new JLabel(this.TESTO_EMPTY_LABEL);

		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		JPanel pannelloModifica = new JPanel(new GridLayout(N_RIGHE_PANNELLO_MODIFICA,N_COLONNE_PANNELLO_MODIFICA));
		pannelloModifica.add(this.jCodiceLabel);
		pannelloModifica.add(this.jCodiceTextField);
		pannelloModifica.add(this.jNomeLabel);
		pannelloModifica.add(this.jNomeTextField);
		pannelloModifica.add(this.jMarcaLabel);
		pannelloModifica.add(this.jMarcaTextField);
		pannelloModifica.add(this.jCategoriaLabel);
		pannelloModifica.add(this.jCategoriaTextField);
		pannelloModifica.add(this.jPrezzoLabel);
		pannelloModifica.add(this.jPrezzoTextField);
		pannelloModifica.add(this.jQuantitaLabel);
		pannelloModifica.add(this.jQuantitaTextField);
		

		/* Preselezione radio button in base al tipo di offerta presente sul prodotto */
		promo = p.getOfferta();
		
		if(promo == null)
		{
			this.jOffertaNoRadioButton.setSelected(true);
		}
		else if(promo.toString().equals("3 x 2"))
		{
			this.jOffertaTrePerDueRadioButton.setSelected(true);
		}
		else
		{
			ScontoPercentuale sp = (ScontoPercentuale) p.getOfferta();
			this.jOffertaPercentualeRadioButton.setSelected(true);
			this.jPercentualeTextField.setText(Integer.toString(sp.getPercentuale()));
			/* CONTROLLO TEXT FIELD QUANTITA */
			
		}
		PlainDocument docPC = (PlainDocument)this.jPercentualeTextField.getDocument();
		docPC.setDocumentFilter(new AmountDocumentFilter());
		
		
		
		JPanel pannelloOfferta = new JPanel(new GridLayout(4,2));
		pannelloOfferta.add(this.jOffertaLabel);
		pannelloOfferta.add(this.jEmptyLabel1);
		pannelloOfferta.add(this.jOffertaNoRadioButton);
		pannelloOfferta.add(this.jEmptyLabel2);
		pannelloOfferta.add(this.jOffertaPercentualeRadioButton);
		pannelloOfferta.add(this.jPercentualeTextField);
		pannelloOfferta.add(this.jOffertaTrePerDueRadioButton);
		
		this.add(imagePanel);
		this.add(pannelloModifica);
		this.add(pannelloOfferta);
		this.add(this.jModificaButton);
		this.jModificaButton.setActionCommand("modifica");
		this.jModificaButton.addActionListener(this);
	}


	
	private Image ridimensionaImmagine (File immagine) {
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
			ImageIcon newIcon = new ImageIcon(new ResizableIcon(fc.getSelectedFile()).resizeIcon(DIMENSIONE_ICONA, DIMENSIONE_ICONA));
			if(newIcon!=null)
			{
				this.jImmagineLabel.setIcon(newIcon);
			}
			
		}
		else if(e.getActionCommand().equals("modifica"))
		{
			Prodotto prod = magazzino.getProdotto(p.getCodice());
			Promozione promozione;
			if(this.jOffertaNoRadioButton.isSelected())
			{
				if(this.jCodiceTextField.getText().equals("") || this.jNomeTextField.getText().equals("") || this.jMarcaTextField.getText().equals("") || this.jCategoriaTextField.getText().equals("") ||this.jPrezzoTextField.getText().equals("") || this.jQuantitaTextField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this, "Inserire tutti i dati correttamente","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//Prodotto prod = magazzino.getProdotto(p.getCodice());
					ImageIcon imgicon = (ImageIcon) this.jImmagineLabel.getIcon();
					
					prod.setCategoria(this.jCategoriaTextField.getText());
					prod.setCodice(this.jCodiceTextField.getText());
					prod.setMarca(this.jMarcaTextField.getText());
					prod.setNome(this.jNomeTextField.getText());
					prod.setOfferta(null);
					prod.setImmagine(imgicon.getDescription()); //settare immagine con descriptor
					prod.setPrezzo(Float.valueOf(this.jPrezzoTextField.getText()));
					prod.setQuantita(Integer.valueOf(this.jQuantitaTextField.getText()));
					magazzino.salvaMagazzino("media/saves/save21.mag");
					if(p.getCategoria().equals(prod.getCategoria()) && p.getCodice().equals(prod.getCodice()) && p.getMarca().equals(prod.getMarca()) && p.getNome().equals(prod.getNome()) && p.getPrezzo() == prod.getPrezzo() && p.getQuantita() == prod.getQuantita())
					{
						JOptionPane.showMessageDialog(this, "Modifica effettuata con successo","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
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
					promozione = new ScontoPercentuale(Integer.parseInt(this.jPercentualeTextField.getText()));
					//Prodotto prod = magazzino.getProdotto(p.getCodice());
					
					ImageIcon imgicon = (ImageIcon) this.jImmagineLabel.getIcon();
					
					prod.setCategoria(this.jCategoriaTextField.getText());
					prod.setCodice(this.jCodiceTextField.getText());
					prod.setMarca(this.jMarcaTextField.getText());
					prod.setNome(this.jNomeTextField.getText());
					prod.setOfferta(promozione);
					prod.setImmagine(imgicon.getDescription());
					prod.setPrezzo(Float.valueOf(this.jPrezzoTextField.getText()));
					prod.setQuantita(Integer.valueOf(this.jQuantitaTextField.getText()));
					magazzino.salvaMagazzino("media/saves/save21.mag");
					if(p.getCategoria().equals(prod.getCategoria()) && p.getCodice().equals(prod.getCodice()) && p.getMarca().equals(prod.getMarca()) && p.getNome().equals(prod.getNome()) && p.getPrezzo() == prod.getPrezzo() && p.getQuantita() == prod.getQuantita())
					{
						JOptionPane.showMessageDialog(this, "Modifica effettuata con successo","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
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
					promozione = new ScontoTrePerDue();
					//Prodotto prod = magazzino.getProdotto(p.getCodice());
					
					ImageIcon imgicon = (ImageIcon) this.jImmagineLabel.getIcon();
					
					prod.setCategoria(this.jCategoriaTextField.getText());
					prod.setCodice(this.jCodiceTextField.getText());
					prod.setMarca(this.jMarcaTextField.getText());
					prod.setNome(this.jNomeTextField.getText());
					prod.setOfferta(promozione);
					prod.setImmagine(imgicon.getDescription());
					prod.setPrezzo(Float.valueOf(this.jPrezzoTextField.getText()));
					prod.setQuantita(Integer.valueOf(this.jQuantitaTextField.getText()));
					magazzino.salvaMagazzino("media/saves/save21.mag");
					if(p.getCategoria().equals(prod.getCategoria()) && p.getCodice().equals(prod.getCodice()) && p.getMarca().equals(prod.getMarca()) && p.getNome().equals(prod.getNome()) && p.getPrezzo() == prod.getPrezzo() && p.getQuantita() == prod.getQuantita())
					{
						JOptionPane.showMessageDialog(this, "Modifica effettuata con successo","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Selezionare un'offerta per il prodotto","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		

		
		
		
		
		
		
		
	}
}
