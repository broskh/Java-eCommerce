package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import negozio.Magazzino;

public class JShowcasePanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -3383648558571677903L;
	
	private Magazzino magazzino;
	private JButton leftButton;
	private JButton rightButton;
	private int primoElemento;
	private int nArticoliVisualizzati;

	private static final int ALTEZZA_NAVIGAZIONE = 50;
	private static final int LARGHEZZA_MARGINE_DESTRO_NAVIGAZIONE = 120;
	private static final int LARGHEZZA_MARGINE_SINISTRO_NAVIGAZIONE = 120;
	private static final int LARGHEZZA_MARGINE_DESTRO = 40;
	private static final int LARGHEZZA_MARGINE_SINISTRO = 40;
	private static final int DIMENSIONE_FONT_FRECCE = 25;
	
	private static final int MARGINE_ARTICOLI = 20;
	
	private static final String TESTO_FRECCIA_SINISTRA = "<";
	private static final String TESTO_FRECCIA_DESTRA = "<";
	private static final String FONT_FRECCE = "Arial";
	
	private static final String LEFT_IMAGE_PATH = "media/img/left.png";
	private static final String RIGHT_IMAGE_PATH = "media/img/right.png";

	public JShowcasePanel(Magazzino magazzino, int larghezza_bacheca, int altezza_bacheca) {
		this.magazzino = magazzino;
		this.primoElemento = 0;
		this.mostraArticoli(larghezza_bacheca, altezza_bacheca);
	}
	
	private void mostraArticoli (int larghezza_bacheca, int altezzaBacheca) {
		JPanel leftPanel = new JPanel ();
		leftPanel.add (Box.createRigidArea(new Dimension(LARGHEZZA_MARGINE_SINISTRO_NAVIGAZIONE, ALTEZZA_NAVIGAZIONE)));
		this.leftButton = new JButton ();
		try {
		    Image leftImage = ImageIO.read(new File (LEFT_IMAGE_PATH));
		    this.leftButton.setIcon(new ImageIcon(leftImage));
		} catch (Exception ex) {
			this.leftButton.setText(TESTO_FRECCIA_SINISTRA);
			this.leftButton.setFont(new Font(FONT_FRECCE, Font.PLAIN, DIMENSIONE_FONT_FRECCE));
		}
		this.leftButton.addActionListener(this);
		leftPanel.add (this.leftButton);
		
		JPanel rightPanel = new JPanel ();
		this.rightButton = new JButton ();
		try {
		    Image rightImage = ImageIO.read(new File (RIGHT_IMAGE_PATH));
		    this.rightButton.setIcon(new ImageIcon(rightImage));
		} catch (Exception ex) {
			this.rightButton.setText(TESTO_FRECCIA_DESTRA);
			this.rightButton.setFont(new Font(FONT_FRECCE, Font.PLAIN, DIMENSIONE_FONT_FRECCE));
		}
		this.rightButton.addActionListener(this);
		rightPanel.add (this.rightButton);
		rightPanel.add (Box.createRigidArea(new Dimension(LARGHEZZA_MARGINE_DESTRO_NAVIGAZIONE, ALTEZZA_NAVIGAZIONE)));
		
		JPanel navigationPanel = new JPanel ();
		navigationPanel.setLayout (new BorderLayout());
		navigationPanel.add(leftPanel, BorderLayout.WEST);
		navigationPanel.add(rightPanel, BorderLayout.EAST);
		
		altezzaBacheca -= JShowcasePanel.ALTEZZA_NAVIGAZIONE;
		int nRighe = altezzaBacheca / JArticlePanel.ALTEZZA_DEFAULT;
		int nColonne = larghezza_bacheca / JArticlePanel.LARGHEZZA_DEFAULT;
		JPanel showcasePanel = new JPanel (new GridLayout(nRighe, nColonne, MARGINE_ARTICOLI, MARGINE_ARTICOLI));
		this.nArticoliVisualizzati = nRighe * nColonne;
		for (int i = this.primoElemento; i < this.primoElemento + this.nArticoliVisualizzati; i++) {			
			JArticlePanel article = new JArticlePanel(this.magazzino.getArticoli().get(i));
			showcasePanel.add(article);
		}
		
		JPanel mainPanel = new JPanel (new BorderLayout());
		mainPanel.add(Box.createHorizontalStrut(LARGHEZZA_MARGINE_SINISTRO), BorderLayout.WEST);
		mainPanel.add(showcasePanel, BorderLayout.CENTER);
		mainPanel.add(Box.createHorizontalStrut(LARGHEZZA_MARGINE_DESTRO), BorderLayout.EAST);

		this.setLayout (new BorderLayout());
		this.add (mainPanel, BorderLayout.PAGE_START);
		this.add (navigationPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.leftButton) && this.primoElemento != 0) {
			this.primoElemento -= this.nArticoliVisualizzati;
			if (this.primoElemento < 0) {
				this.primoElemento = 0;
			}
		}
		else if (e.getSource().equals(this.rightButton) && (this.primoElemento + this.nArticoliVisualizzati) < this.magazzino.getArticoli().size()) {
			this.primoElemento += this.nArticoliVisualizzati;
			if (this.primoElemento > this.magazzino.getArticoli().size() - this.nArticoliVisualizzati) {
				this.primoElemento = this.magazzino.getArticoli().size() - this.nArticoliVisualizzati;
			}
		}
	}
}