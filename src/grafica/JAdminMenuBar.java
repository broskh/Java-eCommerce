package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

	public JAdminMenuBar()
	{
		this.fileMenu = new JMenu("File");
		this.gestioneMenu = new JMenu("Gestione");
		this.closeMenuItem = new JMenuItem("Chiudi");
		this.caricaMenuItem = new JMenuItem("Carica");
		this.salvaMenuItem = new JMenuItem("Salva");
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
		if (e.getActionCommand().equals("aggiungi"))
		{
			JAddProductFrame jAddProductFrame = new JAddProductFrame();
			jAddProductFrame.setVisible(true);
		}
		if (e.getActionCommand().equals("modifica"))
		{
			JSearchProductFrame jSearchProductFrame = new JSearchProductFrame();
			jSearchProductFrame.setVisible(true);
		}
		if (e.getActionCommand().equals("elimina"))
		{
			JSearchProductFrame jSearchProductFrame = new JSearchProductFrame();
			jSearchProductFrame.setVisible(true);
		}
		
		
	}
	
}

