package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import utenza.Cliente;

public class JClientControlPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -8385562955958262505L;
	
	private JComboBox <String> filterTypeComboBox;
	private JTextField filterTextField;
	private JButton filterButton;
	private JButton cartButton;
	
	private Cliente cliente;

	protected static final int ALTEZZA = 80;

	private static final int MARGINE_LABEL = 20;
	private static final int MARGINE_DESTRO = 20;
	private static final int MARGINE_SINISTRO = 20;
	private static final int MARGINE_ORIZZONTALE_LAYOUT = 80;

	private static final String FILTER_TYPE_LABEL = "Filtra per:";
	private static final String FILTER_STRING_LABEL = "Stringa di ricerca:";
	private static final String FILTER_BUTTON_TEXT = "Filtra";
	private static final String CART_BUTTON_TEXT = "Carrello";	
	protected static final String[] FILTER_TYPE_STRINGS = { "Nome", "Marca", "Codice", "Categoria", "Prezzo", "Quantità" };
	private static final String CART_IMAGE_PATH = "media/img/cart.png";

	public JClientControlPanel (Cliente cliente) {
		this.cliente = cliente;
		
		JPanel leftPanel = new JPanel ();
		
		JPanel filterTypePanel = new JPanel ();
		JLabel filterTypeLabel = new JLabel (FILTER_TYPE_LABEL);
		filterTypePanel.setLayout (new BorderLayout ());
		filterTypePanel.add (filterTypeLabel, BorderLayout.PAGE_START);
		filterTypePanel.add (Box.createVerticalStrut (MARGINE_LABEL));
		this.filterTypeComboBox = new JComboBox <String> (FILTER_TYPE_STRINGS);
		filterTypePanel.add (this.filterTypeComboBox, BorderLayout.PAGE_END);
		
		JPanel filterStringPanel = new JPanel ();
		JLabel filterStringLabel = new JLabel (FILTER_STRING_LABEL);
		filterStringPanel.setLayout(new BorderLayout ());
		filterStringPanel.add (filterStringLabel, BorderLayout.PAGE_START);
		filterStringPanel.add (Box.createVerticalStrut (MARGINE_LABEL));
		this.filterTextField = new JTextField();
		filterStringPanel.add (this.filterTextField, BorderLayout.PAGE_END);
		
		this.filterButton = new JButton (FILTER_BUTTON_TEXT);
	
		leftPanel.add (Box.createRigidArea(new Dimension(MARGINE_SINISTRO, ALTEZZA)));
		leftPanel.add (filterTypePanel);
		leftPanel.add (Box.createHorizontalStrut(MARGINE_ORIZZONTALE_LAYOUT));
		leftPanel.add (filterStringPanel);
		leftPanel.add (Box.createHorizontalStrut(MARGINE_ORIZZONTALE_LAYOUT));
		leftPanel.add (this.filterButton);
		
		JPanel rightPanel = new JPanel ();
		
		this.cartButton = new JButton ();
		try {
		    Image img = ImageIO.read(new File (CART_IMAGE_PATH));
		    this.cartButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			this.cartButton.setText(CART_BUTTON_TEXT);
		}
		this.cartButton.addActionListener(this);
		
		rightPanel.add (this.cartButton);
		rightPanel.add (Box.createRigidArea(new Dimension(MARGINE_DESTRO, ALTEZZA)));
		
		this.setLayout(new BorderLayout());
		this.add (leftPanel, BorderLayout.WEST);
		this.add (rightPanel, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.cartButton)) {
			JCartDialog cartDialog = new JCartDialog((JFrame) SwingUtilities.getWindowAncestor(this), this.cliente.getCarrello ());
			cartDialog.setVisible(true);			
		}
	}
}