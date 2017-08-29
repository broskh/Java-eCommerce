package grafica;

import grafica.JAddProductFrame;
import grafica.JModifyProductFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import grafica.JUserFrame;
import negozio.Magazzino;

public class JAdminControlPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6258711606843235850L;
	
	protected static final int ALTEZZA_BOTTONE = 20;
	protected static final int LARGHEZZA_BOTTONE = 80;
	protected static final int MARGINE_ORIZZONTALE_LAYOUT = 0;
	protected static final int MARGINE_VERTICALE_LAYOUT = 0;
	
	private static final String testoSalvaButton = "Salva";
	private static final String testoCaricaButton = "Carica";
	private static final String testoAggiungiButton = "Aggiungi";
	private static final String testoModifcaButton = "Modifica";
	private static final String testoEliminaButton = "Elimina";
	
	private static final String FILE_SAVE_STRING = "media/saves/save21.mag";
	
	private Magazzino magazzino;
	
	private JButton jSalvaButton;
	private JButton jCaricaButton;
	private JButton jAggiungiButton;
	private JButton jModificaButton;
	private JButton jEliminaButton;
	
	public JAdminControlPanel(Magazzino magazzino)
	{
		
		this.magazzino = magazzino;
		this.setLayout(new FlowLayout(FlowLayout.LEADING,this.MARGINE_ORIZZONTALE_LAYOUT,this.MARGINE_VERTICALE_LAYOUT));
		
		this.jSalvaButton = new JButton(this.testoSalvaButton);
		this.jSalvaButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jSalvaButton.setBorder(new EtchedBorder());
		this.jSalvaButton.setActionCommand("Salva");
		this.jSalvaButton.addActionListener(this);
		
		this.jCaricaButton = new JButton(this.testoCaricaButton);
		this.jCaricaButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jCaricaButton.setBorder(new EtchedBorder());
		this.jCaricaButton.setActionCommand("Carica");
		this.jCaricaButton.addActionListener(this);
		
		this.jAggiungiButton = new JButton(this.testoAggiungiButton);
		this.jAggiungiButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jAggiungiButton.setBorder(new EtchedBorder());
		this.jAggiungiButton.setActionCommand("Aggiungi");
		this.jAggiungiButton.addActionListener(this);
		
		this.jModificaButton = new JButton(this.testoModifcaButton);
		this.jModificaButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jModificaButton.setBorder(new EtchedBorder());
		this.jModificaButton.setActionCommand("Modifica");
		this.jModificaButton.addActionListener(this);
		
		this.jEliminaButton = new JButton(this.testoEliminaButton);
		this.jEliminaButton.setPreferredSize(new Dimension(this.LARGHEZZA_BOTTONE,this.ALTEZZA_BOTTONE));
		this.jEliminaButton.setBorder(new EtchedBorder());
		this.jEliminaButton.setActionCommand("Elimina");
		this.jEliminaButton.addActionListener(this);
		
		
		this.add(this.jSalvaButton);
		this.add(this.jCaricaButton);
		this.add(this.jAggiungiButton);
		this.add(this.jModificaButton);
		this.add(this.jEliminaButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		
		if(e.getActionCommand().equals("salva"))
		{
			magazzino.salvaMagazzino(FILE_SAVE_STRING);
		}
		if(e.getActionCommand().equals("Carica"))
		{
			
			/*JFileChooser fc = new JFileChooser();
			
			int returnVal = fc.showOpenDialog(this);
			magazzino.caricaMagazzino(fc.getSelectedFile());
			this.validate();
	        this.repaint();*/
	        
			
			
			
			
			//this.jCaricaButton.setText("upload");
		}
		if(e.getActionCommand().equals("Aggiungi"))
		{
			/* FUNZIONANTE */
			JAddProductFrame jAddProductFrame = new JAddProductFrame(magazzino);
			jAddProductFrame.setVisible(true);
			/* FINE FUNZIONANTE */
		}
		if(e.getActionCommand().equals("Modifica"))
		{
			JSearchProductFrame jSearchProductFrame = new JSearchProductFrame(magazzino);
			jSearchProductFrame.setVisible(true);
		}
		if(e.getActionCommand().equals("Elimina"))
		{
			JDeleteProductFrame jDeleteProductFrame = new JDeleteProductFrame(magazzino);
			jDeleteProductFrame.setVisible(true);
		}
	}

	
}

