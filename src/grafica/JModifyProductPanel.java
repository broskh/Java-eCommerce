package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



import negozio.Prodotto;

public class JModifyProductPanel extends JPanel {

private static final long serialVersionUID = 1L;
	
	
	
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
	private JLabel jOffertaLabel;
	protected JRadioButton jOffertaNoRadioButton;
	protected JRadioButton jOffertaPercentualeRadioButton;
	protected JRadioButton jOffertaTrePerDueRadioButton;
	protected JTextField jPercentualeTextField;
	protected JButton jModificaButton;
	
	
	private JLabel jEmptyLabel1;
	private JLabel jEmptyLabel2;
	
	
	/* aggiungo un prodotto a caso */
	private Prodotto p = new Prodotto();
	
	
	private static final int LARGHEZZA_TEXTBOX = 10;
	private static final int N_RIGHE_PANNELLO_MODIFICA = 5;
	private static final int N_COLONNE_PANNELLO_MODIFICA = 2;
	
	
	private static final String TESTO_CODICE_LABEL = "Codice:";
	private static final String TESTO_NOME_LABEL = "Nome:";
	private static final String TESTO_MARCA_LABEL = "Marca:";
	private static final String TESTO_CATEGORIA_LABEL = "Categoria:";
	private static final String TESTO_PREZZO_LABEL = "Prezzo:";
	private static final String TESTO_OFFERTA_LABEL = "Offerta:";
	private static final String TESTO_OFFERTA_NO_RADIO_BUTTON = "No";
	private static final String TESTO_OFFERTA_PERCENTUALE_RADIO_BUTTON = "Percentuale";
	private static final String TESTO_OFFERTA_TRE_PER_DUE_RADIO_BUTTON = "3x2";
	private static final String TESTO_MODIFICA_BUTTON = "Modifica";
	
	
	private static final String TESTO_EMPTY_LABEL = "";
	
	
	
	
	public JModifyProductPanel(Prodotto p)
	{
		
		/* inizializzo il prodotto creato da me */
		/*p.setNome("kd35");
		p.setMarca("Adidas");
		p.setCodice("123456");
		p.setCategoria("Scarpe");
		p.setPrezzo(65);*/
		//p.setImmagine(new File("media/img/immagine_non_disponibile.jpg"));
		
		
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
		
		
		
		JPanel pannelloOfferta = new JPanel(new GridLayout(4,2));
		pannelloOfferta.add(this.jOffertaLabel);
		pannelloOfferta.add(this.jEmptyLabel1);
		pannelloOfferta.add(this.jOffertaNoRadioButton);
		pannelloOfferta.add(this.jEmptyLabel2);
		pannelloOfferta.add(this.jOffertaPercentualeRadioButton);
		pannelloOfferta.add(this.jPercentualeTextField);
		pannelloOfferta.add(this.jOffertaTrePerDueRadioButton);
		
		this.add(pannelloModifica);
		this.add(pannelloOfferta);
		this.add(this.jModificaButton);
	}
}
