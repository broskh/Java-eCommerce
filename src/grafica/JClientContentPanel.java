package grafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import negozio.Magazzino;
import negozio.Prodotto;

import utenza.Cliente;

public class JClientContentPanel extends JPanel {
	private static final long serialVersionUID = -3383648558571677903L;

	private Magazzino magazzino;
	private Cliente cliente;
	
	private JClientControlPanel jClientControlPanel;
	private JPanel mainPanel;
	private JPanel showcasePanel;
	private ArrayList <JArticlePanel> articoli;
	
	private static final int LARGHEZZA_MARGINE_DESTRO = 40;
	private static final int LARGHEZZA_MARGINE_SINISTRO = 40;	
	private static final int ALTEZZA_MARGINE_SUPERIORE = 40;	
	private static final int ALTEZZA_MARGINE_INFERIORE = 40;	
	private static final int MARGINE_ARTICOLI = 20;

	public JClientContentPanel(Magazzino magazzino, Cliente cliente, int larghezzaBacheca) {
		this.magazzino = magazzino;
		this.cliente = cliente;
		
		this.jClientControlPanel = new JClientControlPanel(this.cliente);
		this.jClientControlPanel.setBorder(new EtchedBorder ());
		
		Iterator <Prodotto> itr = this.magazzino.getArticoli().iterator();	
		this.articoli = new ArrayList <JArticlePanel> ();
		while(itr.hasNext()) {
			Prodotto prodotto = itr.next();
			JArticlePanel article = new JArticlePanel(prodotto, this.cliente);
			this.articoli.add(article);
		}
		this.showcasePanel = new JPanel();
		this.mainPanel = new JPanel(new BorderLayout());
		this.mainPanel.add(Box.createVerticalStrut(ALTEZZA_MARGINE_SUPERIORE), BorderLayout.PAGE_START);
		this.mainPanel.add(Box.createHorizontalStrut(LARGHEZZA_MARGINE_SINISTRO), BorderLayout.WEST);
		this.mainPanel.add(this.showcasePanel, BorderLayout.CENTER);
		this.mainPanel.add(Box.createHorizontalStrut(LARGHEZZA_MARGINE_DESTRO), BorderLayout.EAST);
		this.mainPanel.add(Box.createVerticalStrut(ALTEZZA_MARGINE_INFERIORE), BorderLayout.PAGE_END);
		this.aggiornaArticoli(larghezzaBacheca);
		JScrollPane scrollPanel = new JScrollPane(this.mainPanel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setBorder(null);
		
		this.setLayout(new BorderLayout());
		this.add(this.jClientControlPanel, BorderLayout.PAGE_START);
		this.add(scrollPanel, BorderLayout.CENTER);
	}
	
	public void aggiornaArticoli (int larghezzaBacheca) {
		larghezzaBacheca -= LARGHEZZA_MARGINE_DESTRO - LARGHEZZA_MARGINE_SINISTRO;
		int nColonne = larghezzaBacheca / (JArticlePanel.LARGHEZZA_DEFAULT + MARGINE_ARTICOLI);
//		if (larghezzaBacheca % JArticlePanel.LARGHEZZA_DEFAULT != 0) {
//			nColonne++;
//		}
		int nRighe = this.magazzino.getArticoli().size() / nColonne;
		if (this.magazzino.getArticoli().size() % nColonne != 0) {
			nRighe++;
		}
//		int nRighe = altezzaContentPanel / JArticlePanel.ALTEZZA_DEFAULT;

		this.mainPanel.remove(this.showcasePanel);
		this.showcasePanel = new JPanel(new GridLayout(nRighe, nColonne, MARGINE_ARTICOLI, MARGINE_ARTICOLI));
		Iterator <JArticlePanel> itr = this.articoli.iterator();
		while (itr.hasNext()) {
			JArticlePanel jArticlePanel = itr.next();
			this.showcasePanel.add(jArticlePanel);
		}
		this.mainPanel.add(this.showcasePanel, BorderLayout.CENTER);
	}
}