package ManageEmployee;
import javax.swing.*;
import javax.swing.border.Border;
//import javax.swing.event.MouseInputListener;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.*;
//import java.awt.event.*;
import Utility.*;

public class EmployeeManagePanel extends JPanel implements ActionListener, MouseListener {
//	public class EmployeeManagePanel extends JPanel implements MouseInputListener {
	
    private static final long serialVersionUID = 1L;
    
    public Color blackColor = Utils.blackColor;
    public Color darkGrey = Utils.darkGrey;
    public Color skyColor = Utils.skyColor;
    public Color whiteColor = Utils.whiteColor;
    public Color transparent = Utils.transparent;
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
    
    Border buttonBorder = BorderFactory.createEmptyBorder();
    
    private JButton dashboardButton, addButton, modifyButton, deleteButton, viewDeletedButton;
    private JPanel mainPanel, navigationPanel, contentPanel, profilePanel;
    private CardLayout contentLayout;
    private JPanel dashboardContent, addContent, modifyContent, deleteContent, viewDeletedContentJPanel;
    private JButton profileButton;
    String userid;
    private ProfilePopup profilePopup;
    public EmployeeManagePanel(String userid) {
    	this.userid = userid;
    	
        mainPanel = new JPanel(new BorderLayout(2, 2));
        mainPanel.setSize(screenSize.width, screenSize.height);
        
        navigationPanel = new JPanel();
        navigationPanel.setBackground(darkGrey);

        dashboardButton = createButton("Dashboard");
        dashboardButton.setBackground(skyColor);
    	dashboardButton.setForeground(whiteColor);
    	
        addButton = createButton("Add");
        modifyButton = createButton("Modify");
        deleteButton = createButton("Delete");
        viewDeletedButton = createButton("View Deleted");

        profilePanel = new JPanel(new BorderLayout());
        profilePanel.setMinimumSize(new Dimension(90, 40));
        profilePanel.setPreferredSize(new Dimension(130, 50));
        profilePanel.setMaximumSize(new Dimension(200, 60));
        profilePanel.setBackground(darkGrey);
        ImageIcon profileIcon = new ImageIcon("E:\\eclipseWorkspace\\Employee Management System (EMS)\\src\\Utility\\user.png");
        Image profileImage = profileIcon.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(profileImage);
        
        profileButton = new JButton(profileIcon);
        profileButton.setBackground(darkGrey);
        profileButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        profileButton.setCursor(handCursor);
        profileButton.setPreferredSize(new Dimension(50,50));
        profileButton.addActionListener(this);
        
		
        profileButton.addMouseListener(this);

        profilePanel.add(profileButton, BorderLayout.EAST);
        profilePopup = new ProfilePopup(userid);
        
        navigationPanel.add(dashboardButton);
        navigationPanel.add(addButton);
        navigationPanel.add(modifyButton);
        navigationPanel.add(deleteButton);
        navigationPanel.add(viewDeletedButton);
        navigationPanel.add(profilePanel);
        
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);
        contentPanel.setBackground(skyColor);
        contentPanel.setLayout(contentLayout);
        contentPanel.setPreferredSize(new Dimension(mainPanel.getWidth() - 20, mainPanel.getHeight() - 130));

        dashboardContent = new DashboardEmp();
        addContent = new AddEmp();
        modifyContent = new ModifyEmp();
        deleteContent = new DeleteEmp();
        viewDeletedContentJPanel = new ViewDeletedEmp();
   
        contentPanel.add(dashboardContent, "dashboard");
        contentPanel.add(addContent, "add");
        contentPanel.add(modifyContent, "modify");
        contentPanel.add(deleteContent, "delete");
        contentPanel.add(viewDeletedContentJPanel, "viewDeleted");

        contentLayout.show(contentPanel, "dashboard");

        mainPanel.add(navigationPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setMinimumSize(new Dimension(90, 40));
        button.setPreferredSize(new Dimension(130, 50));
        button.setMaximumSize(new Dimension(200, 60));
        button.setCursor(handCursor);
        button.setBorder(buttonBorder);
        button.setFont(f20plain);
        button.setBackground(buttonBackgroundColor);
//        button.addMouseListener(this);
        button.addActionListener(this);
        return button;
    }
    public void resetButtonColor() {
    	dashboardButton.setBackground(buttonBackgroundColor);
    	dashboardButton.setForeground(blackColor);
    	addButton.setBackground(buttonBackgroundColor);
    	addButton.setForeground(blackColor);
    	modifyButton.setBackground(buttonBackgroundColor);
    	modifyButton.setForeground(blackColor);
    	deleteButton.setBackground(buttonBackgroundColor);
    	deleteButton.setForeground(blackColor);
    	viewDeletedButton.setBackground(buttonBackgroundColor);
    	viewDeletedButton.setForeground(blackColor);
		
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dashboardButton) {
        	resetButtonColor();
            contentLayout.show(contentPanel, "dashboard");
			dashboardButton.setBackground(skyColor);
			dashboardButton.setForeground(whiteColor);
        } else if (e.getSource() == addButton) {
        	resetButtonColor();
            contentLayout.show(contentPanel, "add");
            addButton.setBackground(skyColor);
			addButton.setForeground(whiteColor);
        } else if (e.getSource() == modifyButton) {
        	resetButtonColor();
            contentLayout.show(contentPanel, "modify");
            modifyButton.setBackground(skyColor);
			modifyButton.setForeground(whiteColor);
        } else if (e.getSource() == deleteButton) {
        	resetButtonColor();
            contentLayout.show(contentPanel, "delete");
            deleteButton.setBackground(skyColor);
			deleteButton.setForeground(whiteColor);
        } else if (e.getSource() == viewDeletedButton) {
        	resetButtonColor();
            contentLayout.show(contentPanel, "viewDeleted");
            viewDeletedButton.setBackground(skyColor);
			viewDeletedButton.setForeground(whiteColor);
        } 

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

		if(e.getSource() == profileButton) {
			profilePopup.show(profileButton, e.getX()-50, 53);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}  
}
