package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class JAdminControlPanel extends JPanel {
	private static final long serialVersionUID = -6258711606843235850L;
	
	protected static final int ALTEZZA_BOTTONE = 80;
	protected static final int LARGHEZZA_BOTTONE = 80;
	protected static final int MARGINE_ORIZZONTALE_LAYOUT = 0;
	protected static final int MARGINE_VERTICALE_LAYOUT = 0;
	private JButton jSalvaButton;
	private JButton jCaricaButton;
	private JButton jAggiungiButton;
	private JButton jModificaBtton;
	private JButton jEliminaButton;
	public JAdminControlPanel()
	{
		this.setLayout(new FlowLayout(FlowLayout.LEADING,this.MARGINE_ORIZZONTALE_LAYOUT,this.MARGINE_VERTICALE_LAYOUT));
		
		this.jSalvaButton = new JButton("Salva");
		this.jSalvaButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jSalvaButton.setBorder(new EtchedBorder());
		
		this.jCaricaButton = new JButton("Carica");
		this.jCaricaButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jCaricaButton.setBorder(new EtchedBorder());
		
		this.jAggiungiButton = new JButton("Aggiungi");
		this.jAggiungiButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jAggiungiButton.setBorder(new EtchedBorder());
		
		this.jModificaBtton = new JButton("Modifica");
		this.jModificaBtton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jModificaBtton.setBorder(new EtchedBorder());
		
		this.jEliminaButton = new JButton("Elimina");
		this.jEliminaButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jEliminaButton.setBorder(new EtchedBorder());
		
		
		this.add(this.jSalvaButton);
		this.add(this.jCaricaButton);
		this.add(this.jAggiungiButton);
		this.add(this.jModificaBtton);
		this.add(this.jEliminaButton);
	}
}

