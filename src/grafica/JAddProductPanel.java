package grafica;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JAddProductPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String testoLabel = "Prova";
	
	private JLabel jTitolo;
	
	public JAddProductPanel()
	{
		this.jTitolo = new JLabel(this.testoLabel);
		this.add(jTitolo);
	}
}



