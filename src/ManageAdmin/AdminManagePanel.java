package ManageAdmin;

import javax.swing.*;
import javax.swing.border.Border;

import Utility.Utils;

import java.awt.*;
import java.awt.event.*;


public class AdminManagePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    Dimension screenSize = Utils.screenSize;
    Cursor handCursor = Utils.handCursor;
    Border buttonBorder = BorderFactory.createEmptyBorder();
    Font font20 = Utils.f20bold;
    Color buttonBackgroundColor = new Color(185, 185, 185);

    protected JButton dashboardButton, viewButton, addButton, modifyButton, deleteButton, viewDeletedButton;
    protected JPanel mainPanel, navigationPanel, contentPanel;
    protected CardLayout contentLayout;
    protected JPanel dashboardContent, viewContent, addContent, modifyContent, deleteContent, viewDeletedContentJPanel;
    private String userid;
    public AdminManagePanel(String userid) {
    	this.userid = userid;
        mainPanel = new JPanel(new BorderLayout(4, 4));
        mainPanel.setSize(screenSize.width, screenSize.height);

        navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(205, 205, 205));
        navigationPanel.setPreferredSize(new Dimension(mainPanel.getWidth() - 20, 65));

        dashboardButton = createButton("Dashboard");
        viewButton = createButton("View");
        addButton = createButton("Add");
        modifyButton = createButton("Modify");
        deleteButton = createButton("Delete");
        viewDeletedButton = createButton("View Deleted");

        navigationPanel.add(dashboardButton);
        navigationPanel.add(viewButton);
        navigationPanel.add(addButton);
        navigationPanel.add(modifyButton);
        navigationPanel.add(deleteButton);
        navigationPanel.add(viewDeletedButton);

        navigationPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 2, true));
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);
        contentPanel.setBackground(new Color(225, 225, 255));
        contentPanel.setLayout(contentLayout);
        contentPanel.setPreferredSize(new Dimension(mainPanel.getWidth() - 20, mainPanel.getHeight() - 70));

        dashboardContent = new JPanel();
        viewContent = new JPanel();
        addContent = new JPanel();
        modifyContent = new JPanel();
        deleteContent = new JPanel();
        viewDeletedContentJPanel = new JPanel();

        dashboardContent.setBackground(new Color(225, 225, 255));
        viewContent.setBackground(new Color(225, 225, 255));
        addContent.setBackground(new Color(225, 225, 255));
        modifyContent.setBackground(new Color(225, 225, 255));
        deleteContent.setBackground(new Color(225, 225, 255));
        viewDeletedContentJPanel.setBackground(new Color(225, 225, 255));
        
        contentPanel.add(dashboardContent, "dashboard");
        contentPanel.add(viewContent, "view");
        contentPanel.add(addContent, "add");
        contentPanel.add(modifyContent, "modify");
        contentPanel.add(deleteContent, "delete");
        contentPanel.add(viewDeletedContentJPanel, "viewDeleted");

        JButton btn1 = new JButton("dashboard");
        JButton btn2 = new JButton("view");
        JButton btn3 = new JButton("add");
        JButton btn4 = new JButton("modify");
        JButton btn5 = new JButton("delete");
        JButton btn6 = new JButton("view deleted");

        dashboardContent.add(btn1);
        viewContent.add(btn2);
        addContent.add(btn3);
        modifyContent.add(btn4);
        deleteContent.add(btn5);
        viewDeletedContentJPanel.add(btn6);
        
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
        button.setFont(font20);
        button.setBackground(buttonBackgroundColor);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dashboardButton) {
            contentLayout.show(contentPanel, "dashboard");
        } else if (e.getSource() == viewButton) {
            contentLayout.show(contentPanel, "view");
        } else if (e.getSource() == addButton) {
            contentLayout.show(contentPanel, "add");
        } else if (e.getSource() == modifyButton) {
            contentLayout.show(contentPanel, "modify");
        } else if (e.getSource() == deleteButton) {
            contentLayout.show(contentPanel, "delete");
        } else if (e.getSource() == viewDeletedButton) {
            contentLayout.show(contentPanel, "viewDeleted");
        }
    }
    
}
