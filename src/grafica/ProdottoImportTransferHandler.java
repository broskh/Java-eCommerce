package grafica;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JButton;
import javax.swing.TransferHandler;

import negozio.GestioneProdotti;
import negozio.Prodotto;

public class ProdottoImportTransferHandler extends TransferHandler {
	private static final long serialVersionUID = 7407303027786470664L;

	private GestioneProdotti productsManager;
	
    public static final DataFlavor SUPPORTED_DATE_FLAVOR = Prodotto.getDataFlavor();

    public ProdottoImportTransferHandler(GestioneProdotti productsManager) {
    	this.productsManager = productsManager;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
    }

    @Override
    public boolean importData(TransferSupport support) {
        boolean accept = false;
        if (canImport(support)) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                if (value instanceof Prodotto) {
                    Component component = support.getComponent();
                    if (component instanceof JButton) {
                        this.productsManager.aggiungiProdotto((Prodotto)value);
                        accept = true;
                    }
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        return accept;
    }
}