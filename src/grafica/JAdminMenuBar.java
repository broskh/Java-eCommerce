package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import negozio.Magazzino;
import utenza.Amministratore;
import utenza.Utente;

public class JAdminMenuBar extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 8251356911457819974L;
	private JMenu fileMenu;
	private JMenu gestioneMenu;
	private JMenuItem closeMenuItem;
	private JMenuItem caricaMenuItem;
	private JMenuItem salvaMenuItem;
	private JMenuItem aggiungiMenuItem;
	private JMenuItem modificaMenuItem;
	private JMenuItem eliminaMenuItem;
	
	private Magazzino magazzino;
	
	private static final String FILE_SAVE_STRING = "media/saves/save21.mag";

	public JAdminMenuBar(Magazzino magazzino)
	{
		this.magazzino = magazzino;
		this.fileMenu = new JMenu("File");
		this.gestioneMenu = new JMenu("Gestione");
		
		this.closeMenuItem = new JMenuItem("Chiudi");
		this.closeMenuItem.setActionCommand("chiudi");
		this.closeMenuItem.addActionListener(this);
		
		this.caricaMenuItem = new JMenuItem("Carica");
		this.caricaMenuItem.setActionCommand("carica");
		this.caricaMenuItem.addActionListener(this);
		
		this.salvaMenuItem = new JMenuItem("Salva");
		this.salvaMenuItem.setActionCommand("salva");
		this.salvaMenuItem.addActionListener(this);
		
		this.fileMenu.add(this.caricaMenuItem);
		this.fileMenu.add(this.salvaMenuItem);
		this.fileMenu.add(this.closeMenuItem);
		
		
		this.aggiungiMenuItem = new JMenuItem("Aggiungi");
		this.aggiungiMenuItem.setActionCommand("aggiungi");
		this.aggiungiMenuItem.addActionListener(this);
		
		this.modificaMenuItem = new JMenuItem("Modifica");
		this.modificaMenuItem.setActionCommand("modifica");
		this.modificaMenuItem.addActionListener(this);
		
		this.eliminaMenuItem = new JMenuItem("Elimina");
		this.eliminaMenuItem.setActionCommand("elimina");
		this.eliminaMenuItem.addActionListener(this);
		
		
		this.gestioneMenu.add(this.aggiungiMenuItem);
		this.gestioneMenu.add(this.modificaMenuItem);
		this.gestioneMenu.add(this.eliminaMenuItem);
		this.add(this.fileMenu);
		this.add(this.gestioneMenu);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("carica"))
		{
			
			magazzino.caricaMagazzino("media/saves/magazzino.mag");
			
			System.out.println(magazzino);
		}
		if(e.getActionCommand().equals("salva"))
		{
			magazzino.salvaMagazzino(FILE_SAVE_STRING);
		}
		if(e.getActionCommand().equals("chiudi"))
		{
			SwingUtilities.getWindowAncestor(this).dispose();
		}
		if (e.getActionCommand().equals("aggiungi"))
		{
			JAddProductFrame jAddProductFrame = new JAddProductFrame(magazzino);
			jAddProductFrame.setVisible(true);
		}
		if (e.getActionCommand().equals("modifica"))
		{
			JSearchProductFrame jSearchProductFrame = new JSearchProductFrame(magazzino);
			jSearchProductFrame.setVisible(true);
			System.out.println(magazzino);
		}
		if (e.getActionCommand().equals("elimina"))
		{
			JDeleteProductFrame jDeleteProductFrame = new JDeleteProductFrame(magazzino);
			jDeleteProductFrame.setVisible(true);
		}
		
		
	}
	
}

