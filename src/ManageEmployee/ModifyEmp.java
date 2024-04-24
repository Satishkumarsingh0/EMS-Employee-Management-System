package ManageEmployee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;
import Utility.*;

import employeeManagementSystem.FlashMessage;

public class ModifyEmp extends JPanel implements ActionListener, MouseInputListener{
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

    
    private JPanel topPanel, centralPanel, centralContentPanel;
    private JButton updateButton, viewButton;
    private JPanel idPanel, namePanel, phonePanel, departmentPanel, levelPanel, salaryPanel, dobPanel, addressPanel;
    private JLabel idLabel, nameLabel, phoneLabel, departmentLabel, levelLabel, salaryLabel, dobLabel, addressLabel;
    private JTextField searchId, idField, nameField, phoneField, salaryField, dobField;
    private JComboBox<String> departmentBox, levelBox;
    private JTextArea addressArea;
    private String empSearchString, id, name, department, level, dob, address;
    private Long salary, phone;
    public ModifyEmp() {
    	setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500,500));
        setBackground(darkGrey);
        
    	topPanel = new JPanel(new FlowLayout());
    	topPanel.setBackground(darkGrey);
    	topPanel.setBorder(new EmptyBorder(10,0,10,0));
    	
    	searchId = new JTextField("Enter Employee ID");
    	searchId.setCursor(textCursor);
    	searchId.setBorder(new EmptyBorder(5,15,5,15));
    	searchId.setFont(new Font("Verdana",Font.ITALIC,15));
    	searchId.setPreferredSize(new Dimension(180, 30));
    	searchId.setBackground(whiteColor);
        
    	updateButton = createButton("Update Employee");
    	viewButton = createButton("Get Employee Data");
    	
    	topPanel.add(viewButton);
    	topPanel.add(searchId);
    	topPanel.add(updateButton);
    	    	
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
        addressArea.setWrapStyleWord(true);
        addressArea.setFont(f16plain);
        addressArea.setBackground(darkGrey);
        addressArea.setForeground(whiteColor);
        addressArea.setEditable(true);
        
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
    	
//        centralPanel.add(idPanel);
        centralPanel.add(namePanel);
        centralPanel.add(phonePanel);
        centralPanel.add(departmentPanel);
        centralPanel.add(levelPanel);
        centralPanel.add(salaryPanel);
        centralPanel.add(dobPanel);
        centralPanel.add(addressPanel);
        centralPanel.setBorder(new EmptyBorder((int)(screenSize.getWidth()/15), (int)(screenSize.getWidth()/3), (int)(screenSize.getWidth()/12), (int)(screenSize.getWidth()/3)));
        
    	centralContentPanel = new JPanel(new BorderLayout());
        centralContentPanel.add(centralPanel);
        centralContentPanel.setBackground(darkGrey);
        Border topBorder = BorderFactory.createMatteBorder(2, 0, 0, 0, whiteColor);
        centralContentPanel.setBorder(topBorder);
        
    	add(topPanel, BorderLayout.NORTH);
    	add(centralContentPanel, BorderLayout.CENTER);
    	
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

    private JLabel createLabel(String labelName) {
        JLabel label = new JLabel(labelName);
        label.setFont(f18plain);
        label.setForeground(whiteColor);
        label.setPreferredSize(new Dimension(120, 25));
        return label;
    }
    private JPanel createSubPanel() {
    	JPanel panel = new JPanel(new GridLayout(1,2));
    	panel.setPreferredSize(new Dimension(500,500));
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
        textField.setEditable(true);
        return textField;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewButton) {
            empSearchString = searchId.getText().trim();
            try {
                Employee employee = new Employee();
                Object[] emp = employee.getEmployee(empSearchString);
                if(emp != null) {
                    id = (String) emp[0];
                    name = (String) emp[1];
                    phone = Long.parseLong(emp[2].toString());
                    department = (String) emp[3];
                    level = (String) emp[4];
                    salary = Long.parseLong(emp[5].toString());
                    dob = emp[6].toString();
                    address = (String) emp[7];

                    idField.setText(id);
                    nameField.setText(name);
                    phoneField.setText(String.valueOf(phone));
                    departmentBox.setSelectedItem(department);
                    levelBox.setSelectedItem(level);
                    salaryField.setText(String.valueOf(salary));
                    dobField.setText(dob);
                    addressArea.setText(address);
                } else {
                    FlashMessage.flashMessage("Employee not found!", 2000);
                }
            } catch (NumberFormatException ex) {
                FlashMessage.flashMessage("Invalid input for phone number or salary!", 2000);
                ex.printStackTrace();
            } catch (Exception ex) {
                FlashMessage.flashMessage("An error occurred while retrieving employee data!", 2000);
                ex.printStackTrace();
            }
        }

        if (e.getSource() == updateButton) {
            try {
                empSearchString = searchId.getText().trim();
                Employee employee = new Employee();
                id = searchId.getText().trim();
                name = nameField.getText().trim();
                phone = Long.parseLong(phoneField.getText().trim());
                department = departmentBox.getSelectedItem().toString();
                level = levelBox.getSelectedItem().toString();
                salary = Long.parseLong(salaryField.getText().trim());
                address = addressArea.getText().trim();
                boolean modifiedBoolean = employee.modifyEmployee(id, name, phone, department, level, salary, dob, address);
                if (modifiedBoolean) {
                    FlashMessage.flashMessage("Employee Updated Successfully!", 2000);
                    clearFields();
                } else {
                    FlashMessage.flashMessage("Employee not found", 2000);
                }
            } catch (NumberFormatException ex) {
                FlashMessage.flashMessage("Invalid input for phone number or salary!", 2000);
                ex.printStackTrace();
            } catch (Exception ex) {
                FlashMessage.flashMessage("An error occurred while updating employee data!", 2000);
                ex.printStackTrace();
            }
        }
    }

    private void clearFields() {
        searchId.setText("");
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
		if (e.getSource() == viewButton) {
			viewButton.setBackground(skyColor);
			viewButton.setForeground(whiteColor);
			
		}
		if (e.getSource() == updateButton) {
			updateButton.setBackground(skyColor);
			updateButton.setForeground(whiteColor);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == viewButton) {
			viewButton.setBackground(buttonBackgroundColor);
			viewButton.setForeground(blackColor);
			
		}
		if (e.getSource() == updateButton) {
			updateButton.setBackground(buttonBackgroundColor);
			updateButton.setForeground(blackColor);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}