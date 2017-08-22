package grafica;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;

public class JAdminContentPanel extends JPanel {
	private static final long serialVersionUID = -3936116522061776556L;
	private JAdminControlPanel jAdminControlPanel;
	
	private JTable table;
	
	public JAdminContentPanel()
	{
		this.setLayout(new BorderLayout());
		this.jAdminControlPanel = new JAdminControlPanel();
		this.jAdminControlPanel.setBorder(new EtchedBorder());
		this.add(this.jAdminControlPanel, BorderLayout.PAGE_START);
		//this.add(new JPanel(), BorderLayout.PAGE_END);
		
		String[] columnNames = {"nome","cognome","sport"};
		Object [][] data = {{"alessio","zambardi","pallavolo"},{"damiano","emini","clash royale"},{"marco","pevarello","basket"}};
		table = new JTable(data,columnNames);
		TableColumn column = null;
		
		this.add(this.table,BorderLayout.CENTER);
		//column.setPreferredWidth(50);
	}

}