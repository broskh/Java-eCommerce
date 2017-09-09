package grafica;

import java.io.File;

import javax.swing.filechooser.FileFilter;

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