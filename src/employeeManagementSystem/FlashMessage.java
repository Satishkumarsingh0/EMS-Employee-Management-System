package employeeManagementSystem;
import javax.swing.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import Utility.*;


public class FlashMessage {
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

    public static void flashMessage( String message, int durationMillis) {
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(400, 300);
    	frame.setLocationRelativeTo(null);
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog(frame, "");
        
        dialog.setAlwaysOnTop(true);
        Timer timer = new Timer(durationMillis, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            } 
        });
        timer.setRepeats(false);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                timer.stop();
            }
        });
        timer.start();
        dialog.setVisible(true);
    }
}
