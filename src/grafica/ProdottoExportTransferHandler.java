package grafica;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import negozio.Prodotto;

/**
 * Classe per inviare un oggetto Prodotto tramite drag & drop.
 * 
 * @author Alessio Scheri
 * @version 1.0
 *
 */
public class ProdottoExportTransferHandler extends TransferHandler {
    private static final long serialVersionUID = -3689725432297463459L;

    private Prodotto transferableProduct;
    private JTextField amountTextfield;
    
    public static final DataFlavor SUPPORTED_DATE_FLAVOR = Prodotto.getDataFlavor();

    public ProdottoExportTransferHandler(Prodotto transferableProduct, JTextField amountTextfield) {
    	this.transferableProduct = transferableProduct;
        this.amountTextfield = amountTextfield;
    }

    public Prodotto getValue() {
    	try {
			Prodotto product = this.transferableProduct.clone();
			product.setQuantita(Integer.parseInt(this.amountTextfield.getText()));
            return product;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = this.getValue();
        return t;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
    }
}