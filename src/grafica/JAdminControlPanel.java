package grafica;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import grafica.JAdminContentPanel.JStoreTable;
import negozio.Magazzino;

public class JAdminControlPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6258711606843235850L;
	
	private JFrame mainFrame;
	private JStoreTable jStoreTable;


	private static final int LINE_HEIGHT = 100;
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

	
	private Magazzino magazzino;
	
	private JButton jSaveButton;
	private JButton jLoadButton;
	private JButton jAddButton;
	private JButton jModifyButton;
	private JButton jDeleteButton;
	
	public JAdminControlPanel(Magazzino magazzino,JFrame mainFrame,JStoreTable jStoreTable)
	{
		this.magazzino = magazzino;
		this.jStoreTable = jStoreTable;
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
	
	
	
	public String getFileName(String string)
	{
		String fileName = string.substring(string.indexOf("media")); //questo per media/..
		return fileName;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(SAVE_BUTTON_ACTION_COMMAND_TEXT))
		{
			JFileChooser fc = new JFileChooser(new File("media/saves"));
			/* IMPOSTO LA VISUALIZZAZIONE SOLO DI FILE .MAG */
			fc.setFileFilter(new FileFilter() {
		        public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            }
		            final String name = f.getName();
		            return name.endsWith(".mag");
		        }
		        @Override
		        public String getDescription() {
		            return "*.mag";
		        }
		    });
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == 0)
			{
				magazzino.salvaMagazzino(fc.getSelectedFile());
				BufferedWriter output = null;
		        try {
		            File file = new File("media/saves/config");
		            output = new BufferedWriter(new FileWriter(file));
		            String fileName = getFileName(fc.getSelectedFile().toString());
		            output.write(fileName);
		        } catch ( IOException g ) {
		            g.printStackTrace();
		        } 
		        try {
					output.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
      
	    }
		if(e.getActionCommand().equals(LOAD_BUTTON_ACTION_COMMAND_TEXT))
		{
			
			JFileChooser fc = new JFileChooser(new File("media/saves"));
			/* IMPOSTO LA VISUALIZZAZIONE SOLO DI FILE .MAG */
			fc.setFileFilter(new FileFilter() {
		        public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            }
		            final String name = f.getName();
		            return name.endsWith(".mag");
		        }
		        @Override
		        public String getDescription() {
		            return "*.mag";
		        }
		    });
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == 0)
			{
				magazzino.caricaMagazzino(fc.getSelectedFile());
				System.out.println(fc.getSelectedFile().toString());
				jStoreTable.setModel(new ArticlesTableModel(this.magazzino.getArticoli(), LINE_HEIGHT,ArticlesTableModel.STORE_MODE));
				jStoreTable.getColumn("").setCellRenderer(new RemoveColumnRender(LINE_HEIGHT));
				jStoreTable.getColumn("").setCellEditor(new RemoveColumnEditor(
				this.magazzino.getArticoli(), LINE_HEIGHT));
			}			
			System.out.println(magazzino);
			

			
			
			
			
			
			

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

