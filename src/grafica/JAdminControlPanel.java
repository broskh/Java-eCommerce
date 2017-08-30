package grafica;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import negozio.Magazzino;

public class JAdminControlPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6258711606843235850L;
	
	private JFrame mainFrame;
	
	protected static final int BUTTON_HEIGHT = 80; //20
	protected static final int BUTTON_WIDTH = 80;
	protected static final int HORIZONTAL_MARGIN_LAYOUT = 0;
	protected static final int VERTICAL_MARGIN_LAYOUT = 0;
	
	private static final String SAVE_BUTTON_TEXT = "Salva";
	private static final String LOAD_BUTTON_TEXT = "Carica";
	private static final String ADD_BUTTON_TEXT = "Aggiungi";
	private static final String MODIFY_BUTTON_TEXT = "Modifica";
	private static final String DELETE_BUTTON_TEXT = "Elimina";
	
	private static final String SAVE_BUTTON_ACTION_COMMAND_TEXT = "salva";
	private static final String LOAD_BUTTON_ACTION_COMMAND_TEXT = "carica";
	private static final String ADD_BUTTON_ACTION_COMMAND_TEXT = "aggiungi";
	private static final String MODIFY_BUTTON_ACTION_COMMAND_TEXT = "modifica";
	private static final String DELETE_BUTTON_ACTION_COMMAND_TEXT = "elimina";
	
	
	
	private static final String FILE_SAVE_STRING = "media/saves/save21.mag";
	
	private Magazzino magazzino;
	
	private JButton jSaveButton;
	private JButton jLoadButton;
	private JButton jAddButton;
	private JButton jModifyButton;
	private JButton jDeleteButton;
	
	public JAdminControlPanel(Magazzino magazzino,JFrame mainFrame)
	{
		
		this.magazzino = magazzino;
		this.setLayout(new FlowLayout(FlowLayout.LEADING,JAdminControlPanel.HORIZONTAL_MARGIN_LAYOUT,
				JAdminControlPanel.VERTICAL_MARGIN_LAYOUT));
		
		this.jSaveButton = new JButton(JAdminControlPanel.SAVE_BUTTON_TEXT);
		this.jSaveButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jSaveButton.setBorder(new EtchedBorder());
		this.jSaveButton.setActionCommand(SAVE_BUTTON_ACTION_COMMAND_TEXT);
		this.jSaveButton.addActionListener(this);
		
		this.jLoadButton = new JButton(JAdminControlPanel.LOAD_BUTTON_TEXT);
		this.jLoadButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jLoadButton.setBorder(new EtchedBorder());
		this.jLoadButton.setActionCommand(LOAD_BUTTON_ACTION_COMMAND_TEXT);
		this.jLoadButton.addActionListener(this);
		
		this.jAddButton = new JButton(JAdminControlPanel.ADD_BUTTON_TEXT);
		this.jAddButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jAddButton.setBorder(new EtchedBorder());
		this.jAddButton.setActionCommand(ADD_BUTTON_ACTION_COMMAND_TEXT);
		this.jAddButton.addActionListener(this);
		
		this.jModifyButton = new JButton(JAdminControlPanel.MODIFY_BUTTON_TEXT);
		this.jModifyButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jModifyButton.setBorder(new EtchedBorder());
		this.jModifyButton.setActionCommand(MODIFY_BUTTON_ACTION_COMMAND_TEXT);
		this.jModifyButton.addActionListener(this);
		
		this.jDeleteButton = new JButton(JAdminControlPanel.DELETE_BUTTON_TEXT);
		this.jDeleteButton.setPreferredSize(new Dimension(JAdminControlPanel.BUTTON_WIDTH,
				JAdminControlPanel.BUTTON_HEIGHT));
		
		this.jDeleteButton.setBorder(new EtchedBorder());
		this.jDeleteButton.setActionCommand(DELETE_BUTTON_ACTION_COMMAND_TEXT);
		this.jDeleteButton.addActionListener(this);
		
		
		this.add(this.jSaveButton);
		this.add(this.jLoadButton);
		this.add(this.jAddButton);
		this.add(this.jModifyButton);
		this.add(this.jDeleteButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		
		if(e.getActionCommand().equals(SAVE_BUTTON_ACTION_COMMAND_TEXT))
		{
			magazzino.salvaMagazzino(FILE_SAVE_STRING);
		}
		if(e.getActionCommand().equals(LOAD_BUTTON_ACTION_COMMAND_TEXT))
		{
			
			/*JFileChooser fc = new JFileChooser();
			
			int returnVal = fc.showOpenDialog(this);
			magazzino.caricaMagazzino(fc.getSelectedFile());
			this.validate();
	        this.repaint();*/
	        
			
			
			
			
			//this.jLoadButton.setText("upload");
		}
		if(e.getActionCommand().equals(ADD_BUTTON_ACTION_COMMAND_TEXT))
		{
			JAddProductDialog jAddProductDialog = new JAddProductDialog(mainFrame,magazzino);
			jAddProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(MODIFY_BUTTON_ACTION_COMMAND_TEXT))
		{
			JSearchProductDialog jSearchProductDialog = new JSearchProductDialog(mainFrame,magazzino);
			jSearchProductDialog.setVisible(true);
		}
		if(e.getActionCommand().equals(DELETE_BUTTON_ACTION_COMMAND_TEXT))
		{
			JDeleteProductDialog jDeleteProductDialog = new JDeleteProductDialog(mainFrame,magazzino);
			jDeleteProductDialog.setVisible(true);
		}
	}

	
}

