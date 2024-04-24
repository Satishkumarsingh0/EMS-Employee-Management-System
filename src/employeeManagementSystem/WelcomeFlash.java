package employeeManagementSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ProgressBar extends JProgressBar {
    private static final long serialVersionUID = 1L;
    int time;

    public ProgressBar(int time) {
        this.time = time;
        setValue(0);
        setStringPainted(true);
        setForeground(Color.BLUE);
        setBorder(BorderFactory.createEmptyBorder());
    }

    public void startProgress() {
        Timer timer = new Timer(time, new ActionListener() {
            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                setValue(i);
                if (i >= time) {
                    ((Timer) e.getSource()).stop();
                }
                i += 10;
            }
        });
        timer.start();
    }
}

public class WelcomeFlash {

    public void flash() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);

            JPanel container = new JPanel(new BorderLayout());
            frame.add(container);

            ImageIcon icon1 = new ImageIcon("E:\\\\eclipseWorkspace\\\\Employee Management System (EMS)\\\\src\\\\Utility\\\\logoEMS.png");
            frame.setIconImage(icon1.getImage());

            ImageIcon icon = new ImageIcon("E:\\\\eclipseWorkspace\\\\Employee Management System (EMS)\\\\src\\\\Utility\\\\welcomeImg.png");
            JLabel imgLabel = new JLabel(icon);
            container.add(imgLabel, BorderLayout.CENTER);

            ProgressBar bar = new ProgressBar(200);
            bar.startProgress();
            container.add(bar, BorderLayout.SOUTH);

            frame.setAlwaysOnTop(true);

            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            timer.setRepeats(false);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    timer.stop();
                }
            });
            timer.start();
            frame.setVisible(true);
        });
    }
}
