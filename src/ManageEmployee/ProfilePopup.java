package ManageEmployee;

import javax.swing.*;
import javax.swing.border.*;
import ManageAdmin.*;
import Utility.Utils;
import java.awt.*;
import java.awt.event.*;


//public class AdminProfile extends JPopupMenu implements ActionListener, MouseListener{
	public class ProfilePopup extends JPopupMenu{
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
	    
	    private JMenuItem exitItem, changeLoginPassword; 
	    private JLabel useridLabel;
	    public ProfilePopup(String userID) {
	    	setBackground(darkGrey);
	    	String adminName= new Admin(userID).getAdminName(userID);
	    	useridLabel = new JLabel(adminName);
	    	useridLabel.setPreferredSize(new Dimension(90, 30));
	    	useridLabel.setCursor(handCursor);
	    	useridLabel.setBorder(new EmptyBorder(15, 10, 5, 0));
	    	useridLabel.setBackground(darkGrey);
	    	useridLabel.setForeground(whiteColor);
	    	useridLabel.setFont(f16bold);
	    	
	    	changeLoginPassword = createMenuItem("Change Password");
	    	changeLoginPassword.addActionListener(new ActionListener() {
	    		
	    		@Override
	    		public void actionPerformed(ActionEvent e) {
	    			new ChangeAdminLoginPasswordPanel(userID);
	    		};
	    		
	    	});
	    		
	    
	    	exitItem = createMenuItem("Exit");
	    	exitItem.addActionListener(new ActionListener() {
	    		
	    		@Override
	    		public void actionPerformed(ActionEvent e) {
	    			System.gc();
					System.exit(0);
	    		};
	    		
	    	});
	    	add(useridLabel);
	    	add(changeLoginPassword);
	    	add(exitItem);
	    	
	}
    private JMenuItem createMenuItem(String label) {
    	JMenuItem menuItem = new JMenuItem(label);
    	menuItem.setPreferredSize(new Dimension(90, 30));
    	menuItem.setCursor(handCursor);
    	menuItem.setBorder(buttonBorder);
    	menuItem.setBackground(darkGrey);
    	menuItem.setForeground(whiteColor);
    	menuItem.setFont(f16plain);
        return menuItem;
    }
}
