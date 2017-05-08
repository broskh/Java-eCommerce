package grafica;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class JClientControlPanel extends JPanel {
	private static final long serialVersionUID = -8385562955958262505L;

	protected static final int ALTEZZA_BOTTONE = 80;
	protected static final int LARGHEZZA_BOTTONE = 80;
	
	protected static final int MARGINE_ORIZZONTALE_LAYOUT = 0;
	protected static final int MARGINE_VERTICALE_LAYOUT = 0;
	
	private JButton button;

	public JClientControlPanel() {
		this.setLayout(new FlowLayout(FlowLayout.LEADING, JClientControlPanel.MARGINE_ORIZZONTALE_LAYOUT, JClientControlPanel.MARGINE_VERTICALE_LAYOUT));
		this.button = new JButton ("Miao");
		this.button.setPreferredSize(new Dimension(JClientControlPanel.LARGHEZZA_BOTTONE, JClientControlPanel.ALTEZZA_BOTTONE));
		this.button.setBorder(new EtchedBorder ());
		this.add(this.button);
	}
}
