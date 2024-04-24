package ManageAdmin;

import javax.swing.*;

import Utility.Utils;
import employeeManagementSystem.FlashMessage;
import employeeManagementSystem.LoginPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeAdminLoginPasswordPanel extends JFrame implements ActionListener {
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
    
	private static final long serialVersionUID = 1L;
	private String userid;
	private JLabel oldPasswordLabel, newPasswordFirstLabel, newPasswordSecondLabel;
	private JPasswordField oldPasswordField, newPasswordFieldFirst, newPasswordFieldSecondField;
	private JCheckBox showPassworCheckBox;
	private JButton submitButton;
	private Container container;
    ImageIcon icon = new ImageIcon("E:\\eclipseWorkspace\\Employee Management System (EMS)\\src\\Utility\\logoEMS.png");

	public ChangeAdminLoginPasswordPanel(String userid) {
		this.userid = userid;
		
		setSize(500, 330);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);

		setResizable(false);
		setIconImage(icon.getImage());
		setTitle("Change Password - " + new Admin(userid).getAdminName(userid));
		container = getContentPane();
		setLayout(null);

		container.setBackground(darkGrey);
		oldPasswordLabel = new JLabel("Old Password:");
		oldPasswordLabel.setBounds(90, 40, 150, 30);
		oldPasswordLabel.setForeground(whiteColor);

		container.add(oldPasswordLabel);

		oldPasswordField = new JPasswordField();
		oldPasswordField.setBounds(240, 40, 150, 30);
		oldPasswordField.setEchoChar('*');
		container.add(oldPasswordField);
		

		newPasswordFirstLabel = new JLabel("New Password:");
		newPasswordFirstLabel.setBounds(90, 80, 150, 30);
		newPasswordFirstLabel.setForeground(whiteColor);
		container.add(newPasswordFirstLabel);

		newPasswordFieldFirst = new JPasswordField();
		newPasswordFieldFirst.setBounds(240, 80, 150, 30);
		newPasswordFieldFirst.setEchoChar('*');
		newPasswordFieldFirst.addActionListener(this);
		container.add(newPasswordFieldFirst);
		
		newPasswordSecondLabel  = new JLabel("Re-enter new Password:");
		newPasswordSecondLabel.setBounds(90, 120, 150, 30);
		newPasswordSecondLabel.setForeground(whiteColor);
		container.add(newPasswordSecondLabel);
		
		newPasswordFieldSecondField = new JPasswordField();
		newPasswordFieldSecondField.setBounds(240, 120, 150, 30);
		newPasswordFieldSecondField.setEchoChar('*');
		newPasswordFieldSecondField.addActionListener(this);
		container.add(newPasswordFieldSecondField);

		showPassworCheckBox = new JCheckBox("Show Password");
		showPassworCheckBox.setBounds(175, 165, 160, 20);
		showPassworCheckBox.setBackground(darkGrey);
		showPassworCheckBox.setForeground(whiteColor);
		showPassworCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		showPassworCheckBox.addActionListener(this);
		container.add(showPassworCheckBox);

		submitButton = new JButton("Submit");
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setBounds(190, 210, 120, 40);
		container.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    char[] oldPasswordChars = oldPasswordField.getPassword(); 
			    String oldPassword = new String(oldPasswordChars);
			    
			    char[] newPasswordFirstChars = newPasswordFieldFirst.getPassword(); 
			    String newPasswordFirst= new String(newPasswordFirstChars);
			    
			    char[] newPasswordSecondChars = newPasswordFieldSecondField.getPassword(); 
			    String newPasswordSecond = new String(newPasswordSecondChars);
			    

			    if (oldPassword.isEmpty() || oldPassword.equals("")) {
			        FlashMessage.flashMessage("Enter Old Password", 2000);
			    } else if (newPasswordFirst.isEmpty() || newPasswordFirst.equals("")) {
			        FlashMessage.flashMessage("Enter new Password", 2000);
			    } else if (newPasswordSecond.isEmpty() || newPasswordSecond.equals("")) {
			        FlashMessage.flashMessage("Re-enter new Password", 2000);
			    } else if (oldPassword.equals(newPasswordFirst)) {
			        FlashMessage.flashMessage("New password cannot be same as previous", 2000);
			    } else if (!newPasswordFirst.equals(newPasswordSecond)) {
			        FlashMessage.flashMessage("New passwords did not match.", 2000);
			    } else if (!(new Admin(userid).getPassword(userid)).equals(oldPassword)) {
			        FlashMessage.flashMessage("Old Password did not match.", 2000);
			    } else {
			        try {
			            int updated = new Admin(userid).setNewPassword(userid, newPasswordFirst);
			            if(updated>0) {
			                FlashMessage.flashMessage("Updated Successfully!!", 2000);
			                dispose();
			                new LoginPanel().setVisible(true); // Corrected line
			            }
			        } catch (Exception e2) {
			            e2.printStackTrace();
			            FlashMessage.flashMessage("Failed to update Password", 2000);
			        }
			    }
			}
		});

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (showPassworCheckBox.isSelected()) {
	        newPasswordFieldSecondField.setEchoChar((char)0);
	    } else {
	        newPasswordFieldSecondField.setEchoChar('*');
	    }
	}
}
