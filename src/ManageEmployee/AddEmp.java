package ManageEmployee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import employeeManagementSystem.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import Utility.*;

public class AddEmp extends JPanel implements ActionListener, MouseInputListener{

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


    private JPanel centralPanel;
    private JPanel idPanel, namePanel, phonePanel, departmentPanel, levelPanel, salaryPanel, dobPanel, addressPanel, buttonPanel;
    private JLabel idLabel, nameLabel, phoneLabel, departmentLabel, levelLabel, salaryLabel, dobLabel, addressLabel;
    private JTextField idField, nameField, phoneField, salaryField, dobField;
    private JComboBox<String> departmentBox, levelBox;
    private JTextArea addressArea;
    private JButton submitButton;
    private String userid, name, phone, department, level, salary, dob, address;
    
    Employee employee = new Employee();

    public AddEmp() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500,500));
        setBackground(darkGrey);
        centralPanel = new JPanel(new GridLayout(9,1)); 
        centralPanel.setPreferredSize(new Dimension(500,500));
        centralPanel.setBackground(darkGrey);
        
        idLabel = createLabel("Employee ID: ");
        nameLabel = createLabel("Name: ");
        phoneLabel = createLabel("Phone: ");
        departmentLabel = createLabel("Department: ");
        levelLabel = createLabel("Level: ");
        salaryLabel = createLabel("Salary: ");
        dobLabel = createLabel("DOB(yyyy-mm-dd): ");
        addressLabel = createLabel("Address: ");
        
        idField = createTextField();
        nameField = createTextField();
        phoneField = createTextField();
        salaryField = createTextField();
        dobField = createTextField();
        
        String[] departments = {"Human Resources","Sales","Accounting","Marketing","IT","Customer Support"};
        String[] levels = {"Entry","Staff","Manager","Senior Manager","Executive"};
        
        departmentBox = new JComboBox<>(departments);
        departmentBox.setBackground(darkGrey);
        departmentBox.setForeground(whiteColor);
        departmentBox.setCursor(handCursor);
        
        levelBox = new JComboBox<>(levels);
        levelBox.setBackground(darkGrey);
        levelBox.setForeground(whiteColor);
        levelBox.setCursor(handCursor);
        
        addressArea = new JTextArea(3, 15);
        addressArea.setLineWrap(true);
        addressArea.setFont(f16plain);
        addressArea.setBackground(darkGrey);
        addressArea.setForeground(whiteColor);
        
        idPanel = createSubPanel();
        idPanel.add(idLabel);
        idPanel.add(idField);
        
        namePanel = createSubPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        
        phonePanel = createSubPanel();
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);
        
        departmentPanel = createSubPanel();
        departmentPanel.add(departmentLabel);
        departmentPanel.add(departmentBox); 
        
        levelPanel = createSubPanel();
        levelPanel.add(levelLabel);
        levelPanel.add(levelBox);
        
        salaryPanel = createSubPanel();
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);
        
        dobPanel = createSubPanel();
        dobPanel.add(dobLabel);
        dobPanel.add(dobField);
        
        addressPanel = createSubPanel();
        addressPanel.add(addressLabel);
        addressPanel.add(new JScrollPane(addressArea));
        
//        bottomPanel.setPreferredSize(new Dimension((int)(screenSize.getWidth()*0.6), (int)(screenSize.getHeight()*0.7)));
        buttonPanel = createSubPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(darkGrey);
//        buttonPanel.setBorder(new EmptyBorder(15,10,15,10));
        
        submitButton = new JButton("Submit");
        submitButton.setFont(f18bold);
        submitButton.setBackground(buttonBackgroundColor);
        submitButton.setForeground(blackColor);
        submitButton.setCursor(handCursor);
        submitButton.addMouseListener(this);
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);
        
        centralPanel.add(idPanel);
        centralPanel.add(namePanel);
        centralPanel.add(phonePanel);
        centralPanel.add(departmentPanel);
        centralPanel.add(levelPanel);
        centralPanel.add(salaryPanel);
        centralPanel.add(dobPanel);
        centralPanel.add(addressPanel);
        centralPanel.add(buttonPanel);
        centralPanel.setBorder(new EmptyBorder((int)(screenSize.getWidth()/10), (int)(screenSize.getWidth()/3), (int)(screenSize.getWidth()/8), (int)(screenSize.getWidth()/3)));
        add(centralPanel, BorderLayout.CENTER);
    }
    
    private JLabel createLabel(String labelName) {
        JLabel label = new JLabel(labelName);
        label.setFont(f18plain);
        label.setForeground(whiteColor);
        label.setPreferredSize(new Dimension(120, 25));
        return label;
    }
    private JPanel createSubPanel() {
    	JPanel panel = new JPanel(new GridLayout(1,2));
//    	panel.setPreferredSize(new Dimension(500,500));
    	panel.setBackground(darkGrey);
    	panel.setBorder(new EmptyBorder(0, 10, 0, 10) );
    	
    	return panel;
    }
    
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setCursor(textCursor);
        textField.setFont(f18plain);
        textField.setBackground(darkGrey);
        textField.setForeground(whiteColor);
        textField.setPreferredSize(new Dimension(120, 25));
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            userid = idField.getText().trim();
            name = nameField.getText().trim();
            phone = phoneField.getText().trim();
            department = departmentBox.getSelectedItem().toString();
            level = levelBox.getSelectedItem().toString();
            salary = salaryField.getText().trim();
            dob = dobField.getText().trim();
            address = addressArea.getText().trim();

            Long phoneLong = getLongValue(phone);
            Long salaryLong = getLongValue(salary);
            
            dob = setDOB(dob);
            if (validateInput(userid, name, phone, salary, dob, address, phoneLong, salaryLong)) {
                boolean addedEmp = employee.addEmployee(userid, name, phoneLong, department, level, salaryLong, dob, address);
                if (addedEmp) {
                    FlashMessage.flashMessage("Employee added Successfully!!", 2000);
                    clearFields();
                    EmployeePieChart employeePieChart = new EmployeePieChart();
					employeePieChart.updatePiechart();
                } else {
                    FlashMessage.flashMessage("Failed to add Employee", 2000);
                }
            }
        }
    }

    private String setDOB(String dob2) {
		return dob2.substring(0, 4)+"-"+dob2.substring(5, 7)+"-"+dob2.substring(8);
		
	}

	private Long getLongValue(String value) {
        if (!value.isEmpty()) {
            return Long.parseLong(value);
        }
        return 0L;
    }

    private boolean validateInput(String userid, String name, String phone, String salary, String dob, String address, Long phoneLong, Long salaryLong) {
        if (userid.isEmpty()) {
            FlashMessage.flashMessage("Enter Employee ID", 2000);
            return false;
        } else if(employee.searchEmployee(userid)){
			FlashMessage.flashMessage("Employee with \""+userid+"\"already exists", 2000);
		} else if (name.isEmpty()) {
            FlashMessage.flashMessage("Enter Employee Name", 2000);
            return false;
        } else if (phone.isEmpty() || phone.length() < 10) {
            FlashMessage.flashMessage("Enter Phone Number\n with minimum 10 digits", 2000);
            return false;
        } else if (salary.isEmpty() || salaryLong < 10000) {
            FlashMessage.flashMessage("Enter Salary \nMinimum Salary 10000", 2000);
            return false;
        } else if (dob.isEmpty() || !isValidDOB(dob)) {
            FlashMessage.flashMessage("Enter valid DOB", 2000);
            return false;
        } else if (address.isEmpty()) {
            FlashMessage.flashMessage("Enter Address", 2000);
            return false;
        } else if (address.length() >= 50) {
            FlashMessage.flashMessage("Address size 50 characters", 2000);
            return false;
        } 
        return true;
    }


    private boolean isValidDOB(String dob) {
        try {
            LocalDate date = LocalDate.parse(dob);
            LocalDate now = LocalDate.now();

            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();

            if (year < 1950 || year > (now.getYear() - 17)) {
                FlashMessage.flashMessage("Minimum age required \"18 years\"", 2000);
                return false;
            }
            if (month > 12 || month < 1) {
                FlashMessage.flashMessage("Invalid Month", 2000);
                return false;
            }
            if (day > 31 || day < 1) {
                FlashMessage.flashMessage("Invalid Date", 2000);
                return false;
            }
            if ((month == 2 && day > 29) ||
                (month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                FlashMessage.flashMessage("Enter valid date\n Days exceeded in the month", 2000);
                return false;
            }
        } catch (DateTimeParseException e) {
            FlashMessage.flashMessage("Invalid date format. Please use yyyy-mm-dd", 2000);
            return false;
        }
        return true;
    }


    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
        departmentBox.setSelectedIndex(0);
        levelBox.setSelectedIndex(0);
        salaryField.setText("");
        dobField.setText("");
        addressArea.setText("");
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
		submitButton.setBackground(skyColor);
		submitButton.setForeground(whiteColor);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		submitButton.setBackground(buttonBackgroundColor);
		submitButton.setForeground(blackColor);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}
