package grafica;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * FileFilter per l'utilizzo di file contenenti le informazioni di un magazzino.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class StoreFileFilter extends FileFilter {
	
	public static final String EXTENSION = ".mag";
	
	public StoreFileFilter () {
		super ();
	}
	
	public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        final String name = f.getName();
        return name.endsWith(StoreFileFilter.EXTENSION);
    }
    @Override
    public String getDescription() {
        return "*" + StoreFileFilter.EXTENSION;
    }
}