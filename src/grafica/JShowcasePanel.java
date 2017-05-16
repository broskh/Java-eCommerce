package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
public class JShowcasePanel extends JPanel {
	private static final long serialVersionUID = -3383648558571677903L;
	
	private JButton leftButton;
	private JButton rightButton;

	private static final int ALTEZZA_NAVIGAZIONE = 50;
	private static final int LARGHEZZA_MARGINE_DESTRO_NAVIGAZIONE = 120;
	private static final int LARGHEZZA_MARGINE_SINISTRO_NAVIGAZIONE = 120;
	private static final int LARGHEZZA_MARGINE_DESTRO = 40;
	private static final int LARGHEZZA_MARGINE_SINISTRO = 40;

	private static final int DIMENSIONE_FONT_FRECCE = 25;
	private static final String TESTO_FRECCIA_SINISTRA = "<";
	private static final String TESTO_FRECCIA_DESTRA = "<";
	private static final String FONT_FRECCE = "Arial";
	private static final String LEFT_IMAGE_PATH = "media/img/left.png";
	private static final String RIGHT_IMAGE_PATH = "media/img/right.png";

	public JShowcasePanel() {
		JPanel showcasePanel = new JPanel (new GridLayout(3, 7, 20, 20));
		for (int i = 0; i < 21; i++) {
//			articlePanel.setPreferredSize(new Dimension(250, 300));
			
			showcasePanel.add(new JArticlePanel());
		}
		
		JPanel mainPanel = new JPanel (new BorderLayout());
		mainPanel.add(Box.createHorizontalStrut(LARGHEZZA_MARGINE_SINISTRO), BorderLayout.WEST);
		mainPanel.add(showcasePanel, BorderLayout.CENTER);
		mainPanel.add(Box.createHorizontalStrut(LARGHEZZA_MARGINE_DESTRO), BorderLayout.EAST);

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
		rightPanel.add (this.rightButton);
		rightPanel.add (Box.createRigidArea(new Dimension(LARGHEZZA_MARGINE_DESTRO_NAVIGAZIONE, ALTEZZA_NAVIGAZIONE)));
		
		JPanel navigationPanel = new JPanel ();
		navigationPanel.setLayout (new BorderLayout());
		navigationPanel.add(leftPanel, BorderLayout.WEST);
		navigationPanel.add(rightPanel, BorderLayout.EAST);

		this.setLayout (new BorderLayout());
		this.add (mainPanel, BorderLayout.PAGE_START);
		this.add (navigationPanel, BorderLayout.PAGE_END);
	}
}