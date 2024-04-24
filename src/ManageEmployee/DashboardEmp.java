package ManageEmployee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import Utility.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DashboardEmp extends JPanel implements ActionListener {
    
	public Color blackColor = new Color(109, 109,109);
	public Color darkGrey = new Color(49, 54, 63);
	public Color skyColor = new Color(118, 171, 174);
	public Color whiteColor = new Color(238, 238, 238);
	

    public Font f20plain = new Font("Arial", Font.PLAIN, 20);
    public Font f20bold = new Font("Arial", Font.BOLD, 20);

    public Font f18plain = new Font("Arial", Font.PLAIN, 18);
    public Font f18bold = new Font("Arial", Font.BOLD, 18);

    public Font f16plain = new Font("Arial", Font.PLAIN, 16);
    public Font f16bold = new Font("Arial", Font.BOLD, 16);

    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    private static final long serialVersionUID = 1L;
    
    private JPanel leftPanel, rightPanel, radioButtonPanel;
    private JLabel totalEmployee, selectDepartmentLabel;
    private JRadioButton allDeptRadio, humanResourceRadio, salesRadio, accountingRadio, marketingRadio, itRadio,
            customerSupportRadioButton;
    private ButtonGroup deptButtonGroup;
    private String deptString = "all";
    private Object data[][];

    private JTable dataTable;
    private Employee employee = new Employee();

    public DashboardEmp() {
        setLayout(new BorderLayout());

        leftPanel = new JPanel(new FlowLayout());
        leftPanel.setPreferredSize(new Dimension((int) (screenSize.getWidth() * 0.25),
                (int) (screenSize.getHeight() * 0.75)));
        leftPanel.setBackground(darkGrey);

        int employeesTotal = getEmployeeCount();
        
        totalEmployee = new JLabel("Total Employees: " + employeesTotal);
        totalEmployee.setFont(f20bold);
        totalEmployee.setForeground(skyColor);
        totalEmployee.setBorder(new EmptyBorder(10, 0, 15, 0));
        leftPanel.add(totalEmployee);

        EmployeePieChart pieChart = new EmployeePieChart();
        leftPanel.add(pieChart);

        selectDepartmentLabel = new JLabel("Select Department: ");
        selectDepartmentLabel.setFont(f18bold);
        selectDepartmentLabel.setForeground(skyColor);
        selectDepartmentLabel.setBorder(new EmptyBorder(5, 0, 5, 0));
        leftPanel.add(selectDepartmentLabel);

        allDeptRadio = createRadioButton("All Department");
        humanResourceRadio = createRadioButton("Human Resource");
        salesRadio = createRadioButton("Sales");
        accountingRadio = createRadioButton("Accounting");
        marketingRadio = createRadioButton("Marketing");
        itRadio = createRadioButton("IT");
        customerSupportRadioButton = createRadioButton("Customer Support");

        deptButtonGroup = new ButtonGroup();
        deptButtonGroup.add(allDeptRadio);
        deptButtonGroup.add(humanResourceRadio);
        deptButtonGroup.add(salesRadio);
        deptButtonGroup.add(accountingRadio);
        deptButtonGroup.add(marketingRadio);
        deptButtonGroup.add(itRadio);
        deptButtonGroup.add(customerSupportRadioButton);
        allDeptRadio.setSelected(true);

        radioButtonPanel = new JPanel(new GridLayout(7, 1));
        radioButtonPanel.setBackground(whiteColor);
        radioButtonPanel.setBorder(new EmptyBorder(5,50,5,50));
        radioButtonPanel.add(allDeptRadio);
        radioButtonPanel.add(humanResourceRadio);
        radioButtonPanel.add(salesRadio);
        radioButtonPanel.add(accountingRadio);
        radioButtonPanel.add(marketingRadio);
        radioButtonPanel.add(itRadio);
        radioButtonPanel.add(customerSupportRadioButton);

        leftPanel.add(radioButtonPanel);

        rightPanel = new JPanel(new BorderLayout());
//        rightPanel.setBackground(darkGrey);
        rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Employee Details: all", TitledBorder.CENTER, TitledBorder.TOP));
        String columnName[] = { "ID", "Name", "Phone", "Department", "Level", "Salary", "DOB", "Address" };
        data = employee.viewEmployee(deptString);

        dataTable = new JTable(data, columnName) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dataTable.setFont(f16plain);
        dataTable.setRowHeight(25);
//        dataTable.setBackground(darkGrey);
//        dataTable.setForeground(whiteColor);
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        dataTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        dataTable.setBorder(new EmptyBorder(5,5,5,5));

        JTableHeader header = dataTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));

        JScrollPane tableScrollPane = new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        rightPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private JRadioButton createRadioButton(String label) {
        JRadioButton button = new JRadioButton(label);
        button.setCursor(handCursor);
        button.setFont(f18plain);
        button.setBackground(whiteColor);
        button.addActionListener(this);
        return button;
    }

    private int getEmployeeCount() {
        int count = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            String selectCountString = "select count(userid) as count from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(selectCountString);
            ResultSet counts = preparedStatement.executeQuery();
            if (counts.next()) {
                count = counts.getInt("count");
            }
            counts.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.print("Error in getting employee count");
            e.printStackTrace();
            count = -1;
        }
        return count;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (allDeptRadio.isSelected()) {
            deptString = "all";
        } else if (humanResourceRadio.isSelected()) {
            deptString = "human resources";
        } else if (salesRadio.isSelected()) {
            deptString = "sales";
        } else if (accountingRadio.isSelected()) {
            deptString = "accounting";
        } else if (marketingRadio.isSelected()) {
            deptString = "marketing";
        } else if (itRadio.isSelected()) {
            deptString = "it";
        } else if (customerSupportRadioButton.isSelected()) {
            deptString = "customer support";
        }
        updateDataTable(deptString);
    }
    
    protected String getSelectedDept() {
    	if (allDeptRadio.isSelected()) {
            return "all";
        } else if (humanResourceRadio.isSelected()) {
            return "human resources";
        } else if (salesRadio.isSelected()) {
        	return "sales";
        } else if (accountingRadio.isSelected()) {
        	return "accounting";
        } else if (marketingRadio.isSelected()) {
            return "marketing";
        } else if (itRadio.isSelected()) {
        	return "it";
        } else if (customerSupportRadioButton.isSelected()) {
        	return "customer support";
        }
        else return "all";
		
	}
    protected void updateDataTable(String department) {
        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();
            rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Employee Details: "+ department, TitledBorder.CENTER, TitledBorder.TOP));
            String[] columnName = { "ID", "Name", "Phone", "Department", "Level", "Salary", "DOB", "Address" };
            Object[][] data = employee.viewEmployee(department);
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
            rightPanel.add(tableScrollPane, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }
}
