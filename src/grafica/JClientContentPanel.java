package grafica;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import negozio.Magazzino;

public class JClientContentPanel extends JPanel {
	private static final long serialVersionUID = -3383648558571677903L;
	
	private JClientControlPanel jClientControlPanel;
	private JShowcasePanel jShowcasePanel;

	public JClientContentPanel(Magazzino magazzino, int larghezza_bacheca) {
		this.jClientControlPanel = new JClientControlPanel();
		this.jClientControlPanel.setBorder(new EtchedBorder ());
		
		this.jShowcasePanel = new JShowcasePanel(magazzino, larghezza_bacheca);

		this.setLayout(new BorderLayout());
		this.add(this.jClientControlPanel, BorderLayout.PAGE_START);
		this.add(this.jShowcasePanel, BorderLayout.PAGE_END);
	}
}
