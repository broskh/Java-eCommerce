package grafica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/* PROBABILMENTE DA ELIMINARE */
public class StoreFileName extends JLabel{

	private String filename;
	
	public StoreFileName(String filename)
	{
		super(new ImageIcon(filename));
		this.filename = filename;
	}
	
	public String getFilename()
	{
		return filename;
	}
}
