package employeeManagementSystem;

import java.awt.*;
import javax.swing.*;

import ManageAdmin.*;
import ManageEmployee.*;
import Utility.*;

class MainApplication extends JFrame {
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

	JTabbedPane tabbedPane;
	EmployeeManagePanel employees;
	AdminManagePanel admins;
	boolean isAdmin;
	String userid;
	ImageIcon icon = new ImageIcon("E:\\eclipseWorkspace\\Employee Management System (EMS)\\src\\Utility\\logoEMS.png");

	MainApplication(boolean isAdmin, String userid) {
		this.isAdmin = isAdmin;

		setTitle("EMS-Employee Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSize.width, screenSize.height);
		setMinimumSize(new Dimension((int) (screenSize.getWidth() * 0.75), (int) (screenSize.getHeight() * 0.75)));
		setLocationRelativeTo(null);
		setBackground(Color.DARK_GRAY);
		setIconImage(icon.getImage());
		tabbedPane = new JTabbedPane();

		employees = new EmployeeManagePanel(userid);

		tabbedPane.addTab("Manage Employees", employees);
		if (isAdmin) {
			admins = new AdminManagePanel(userid);
			tabbedPane.addTab("Manage Admins", admins);
		}

		add(tabbedPane);

		setVisible(true);
	}
}

public class EmployeeManagementSystem {

	public static void main(String[] args) {
		try {
			try {
				WelcomeFlash welcomeflash = new WelcomeFlash();
				welcomeflash.flash();

			} catch (Exception e) {
				System.out.println("Error occurred in Admin Panel");
			} finally {
				new LoginPanel();
			}

		} catch (Exception e) {
			System.out.println("Error in Welcome Flash");
			e.printStackTrace();
		}
	}

}
