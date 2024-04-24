package ManageEmployee;

import java.awt.*;
import java.awt.event.*;
import Utility.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.JTableHeader;

import employeeManagementSystem.FlashMessage;

public class ViewDeletedEmp extends JPanel implements ActionListener, MouseInputListener{
	private static final long serialVersionUID = 1L;
	public Color blackColor = Utils.blackColor;
	public Color darkGrey = Utils.darkGrey;
	public Color skyColor = Utils.skyColor;
	public Color whiteColor = Utils.whiteColor;
    public Color buttonBackgroundColor = whiteColor;
    public Font f20plain = Utils.f20plain;
    public Font f20bold = Utils.f20bold;
    public Font f18plain = Utils.f18plain;
    public Font f18bold = Utils.f18bold;
    public Font f16plain = Utils.f16plain;
    public Font f16bold = Utils.f16bold;
    public Dimension screenSize = Utils.screenSize;
    public Cursor handCursor = Utils.handCursor;
    public Cursor textCursor = Utils.textCursor;

    private JPanel topPanel, centerPanel;
    
    private String userid = "";
    private Object data[][];

    private JTable dataTable;
    private Employee employee = new Employee();
    
    private JButton deleteButton, refreshButton, retrieveButton;
    private JTextField idField;
    public ViewDeletedEmp() {
    	setLayout(new BorderLayout());
    	
    	topPanel = new JPanel(new FlowLayout());
    	topPanel.setBackground(darkGrey);
    	topPanel.setBorder(new EmptyBorder(10,0,10,0));
    	
    	idField = new JTextField("Enter Employee ID");
    	idField.setCursor(textCursor);
    	idField.setBorder(new EmptyBorder(5,15,5,15));
    	idField.setFont(new Font("Verdana",Font.ITALIC,15));
    	idField.setPreferredSize(new Dimension(180, 30));
    	idField.setBackground(whiteColor);
        
    	deleteButton = createButton("Delete Permanently");
    	retrieveButton = createButton("Restore Employee");
    	refreshButton = createButton("Refresh Table");
    	
    	topPanel.add(refreshButton);
    	topPanel.add(idField);
    	topPanel.add(retrieveButton);
    	topPanel.add(deleteButton);
    	    	
    	centerPanel = new JPanel(new BorderLayout());
    	centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
              "Deleted Employees", TitledBorder.CENTER, TitledBorder.TOP));
    	String columnName[] = { "ID", "Name", "Phone", "Department", "Level", "Salary", "DOB", "Address" };
    	data = employee.viewDeletedEmployee();
    	
    	dataTable = new JTable(data, columnName) {
    		private static final long serialVersionUID = 1L;
    		
    		@Override
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    	};
    	dataTable.setFont(f16plain);
    	dataTable.setRowHeight(25);
    	dataTable.getColumnModel().getColumn(0).setPreferredWidth(30);
    	dataTable.getColumnModel().getColumn(3).setPreferredWidth(90);
    	dataTable.setBorder(new EmptyBorder(5,5,5,5));

    	JTableHeader header = dataTable.getTableHeader();
    	header.setFont(new Font("Arial", Font.BOLD, 16));
    	
    	JScrollPane tableScrollPane = new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
              JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    	centerPanel.add(tableScrollPane, BorderLayout.CENTER);
      
    	add(topPanel, BorderLayout.NORTH);
    	add(centerPanel, BorderLayout.CENTER);
	}
    private JButton createButton(String buttonLabel) {
    	JButton button = new JButton(buttonLabel);
        button.setCursor(handCursor);
        button.setBorder(new EmptyBorder(5,15,5,15));
        button.setFont(f16bold);
        button.setBackground(buttonBackgroundColor);
        button.setForeground(blackColor);
        button.addActionListener(this);
        button.addMouseListener(this);
        return button;
    }
    protected void updateDeletedEmployeeTable() {
        SwingUtilities.invokeLater(() -> {
            centerPanel.removeAll();
            centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Deleted Employees ", TitledBorder.CENTER, TitledBorder.TOP));
            String[] columnName = { "ID", "Name", "Phone", "Department", "Level", "Salary", "DOB", "Address" };
            Object[][] data = employee.viewDeletedEmployee();
            JTable dataTable = new JTable(data, columnName) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            dataTable.setFont(f16plain);
            dataTable.setRowHeight(25);
            dataTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            dataTable.getColumnModel().getColumn(3).setPreferredWidth(90);
            JTableHeader header = dataTable.getTableHeader();
            header.setFont(new Font("Arial", Font.BOLD, 16));
            JScrollPane tableScrollPane = new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            centerPanel.add(tableScrollPane, BorderLayout.CENTER);
            centerPanel.revalidate();
            centerPanel.repaint();
        });
    }
    private void resetButton() {
  
			refreshButton.setBackground(buttonBackgroundColor);
			refreshButton.setForeground(blackColor);
			deleteButton.setBackground(buttonBackgroundColor);
			deleteButton.setForeground(blackColor);
			retrieveButton.setBackground(buttonBackgroundColor);
			retrieveButton.setForeground(blackColor);
	}
    @Override
    public void actionPerformed(ActionEvent e) {
    	userid = idField.getText().trim();
    	if(e.getSource() == refreshButton) {

    		updateDeletedEmployeeTable();
    	}
    	if(e.getSource() == deleteButton) {
    		if(userid.isEmpty() || userid == null || userid.equalsIgnoreCase("Employee ID")) {
    			FlashMessage.flashMessage("Enter valid employee ID", 2000);
    		} else {
				
    		Boolean isDeleted =  employee.deleteDeletedEmployee(userid);
    		if (isDeleted) {
				FlashMessage.flashMessage(userid + " deleted permanently", 2000);
				updateDeletedEmployeeTable();
			} else {
				FlashMessage.flashMessage("Failed to delete "+ userid, 2000);
			}
				
			}
    		updateDeletedEmployeeTable();
    	}
    	if(e.getSource() == retrieveButton) {
    		if(userid.isEmpty() || userid == null || userid.equalsIgnoreCase("Employee ID")) {
    			FlashMessage.flashMessage("Enter valid employee ID", 2000);
                EmployeePieChart employeePieChart = new EmployeePieChart();
				employeePieChart.updatePiechart();
    		} else {
				
    		Boolean isRetrieved =  employee.retrieveEmployee(userid);
    		if (isRetrieved) {
    			updateDeletedEmployeeTable();
    			FlashMessage.flashMessage("Restored Employee: \""+userid +" \"", 2000);
    		} else {
    			FlashMessage.flashMessage("Failed to Restore: \""+userid +" \"", 2000);
    			
    		}
    		}
    	}
    	updateDeletedEmployeeTable();
    	
    	
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == refreshButton ) {
			resetButton();
			refreshButton.setBackground(skyColor);
			refreshButton.setForeground(whiteColor);
		}
		if (e.getSource() == deleteButton ) {
			resetButton();
			deleteButton.setBackground(skyColor);
			deleteButton.setForeground(whiteColor);
		}
		if (e.getSource() == retrieveButton ) {
			resetButton();
			retrieveButton.setBackground(skyColor);
			retrieveButton.setForeground(whiteColor);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		resetButton();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}
}
