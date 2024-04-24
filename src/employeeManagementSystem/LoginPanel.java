package employeeManagementSystem;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Utility.*;

public class LoginPanel extends JFrame {
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
    
	private JTextField userIdField;
    private JPasswordField passwordField;
    private JLabel userIdLabel, passwordLabel;
    private JCheckBox asSuperAdminBox;
    private JButton loginButton;
    Container container;	
    ImageIcon icon = new ImageIcon("E:\\eclipseWorkspace\\Employee Management System (EMS)\\src\\Utility\\logoEMS.png");
    public LoginPanel() {
        setTitle("EMS - Admin Login");
        setSize(500, 300);       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setResizable(false);
        setIconImage(icon.getImage());
        
        container = getContentPane();
        setLayout(null);
                
        container.setBackground(darkGrey);
        userIdLabel = new JLabel("Admin ID:");
        userIdLabel.setBounds(130,40,150,30);
        userIdLabel.setForeground(whiteColor);
        
        container.add(userIdLabel);
                
        userIdField = new JTextField();
        userIdField.setBounds(200,40,150,30);
        container.add(userIdField);
        
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(130,80,150,30);
        passwordLabel.setForeground(whiteColor);
        container.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(200,80,150,30);
        passwordField.setEchoChar('*');
        container.add(passwordField);
        
        asSuperAdminBox = new JCheckBox("Log in as Super Admin");
        asSuperAdminBox.setBounds(175,125, 160,20);
        asSuperAdminBox.setBackground(darkGrey);
        asSuperAdminBox.setForeground(whiteColor);
        asSuperAdminBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(asSuperAdminBox);
        
        loginButton = new JButton("Login");
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        loginButton.setBounds(190, 170, 120, 40);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userIdField.getText().trim();
                String password = String.valueOf(passwordField.getPassword());
                if ((username == null || username.equals("")) && (password== null || password.equals("")) ) {
                    FlashMessage.flashMessage("Enter Admin ID and Password!",2000);
				}
                else if(username == null || username.equals("")) {
                    FlashMessage.flashMessage("Enter Admin ID!",2000);
                } else if (password== null || password.equals("")) {
                    FlashMessage.flashMessage("Enter Password!",2000);
                } else if(asSuperAdminBox.isSelected()) {
                	 if (validateSuperAdmin(username, password)) {
                         FlashMessage.flashMessage("Logged in Successfully!",2000);
                         dispose();
                         SwingUtilities.invokeLater(() -> {
                             new MainApplication(true, username);
                         });
                	 } else {
                		 FlashMessage.flashMessage("Your are not a Super Admin", 2000);
                	 }
                	
                } else if (validateAdmin(username, password)) {
                    FlashMessage.flashMessage("Logged in Successfully!",2000);
                    dispose();
                    MainApplication mainApplication =  new MainApplication(false, username);
                    mainApplication.setVisible(true);
                } else {
                	FlashMessage.flashMessage("Invalid Admin ID or Password!",2000);
                }
            }
        });

        container.add(passwordLabel);
        container.add(passwordField);
        container.add(loginButton);

        setVisible(true);
    }

    private boolean validateAdmin(String username, String password) {
        try {
        	Connection connection = DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admins WHERE userid = ? AND password = ?"); 

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private boolean validateSuperAdmin(String username, String password) {
        try {
        	Connection connection = DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM superAdmin WHERE userid = ? AND password = ?"); 

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
