package ManageEmployee;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

import Utility.DatabaseConnection;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeePieChart extends JPanel {
    private static final long serialVersionUID = 1L;
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Color blackColor = new Color(34, 40, 49);
    public Color darkGrey = new Color(49, 54, 63);
    public Color skyColor = new Color(118, 171, 174);
    public Color whiteColor = new Color(238, 238, 238);


    public Font f20plain = new Font("Arial", Font.PLAIN, 20);
    public Font f20bold = new Font("Arial", Font.BOLD, 20);

    public Font f18plain = new Font("Arial", Font.PLAIN, 18);
    public Font f18bold = new Font("Arial", Font.BOLD, 18);

    public Font f16plain = new Font("Arial", Font.PLAIN, 16);
    public Font f16bold = new Font("Arial", Font.BOLD, 16);

    private DefaultPieDataset dataset;
    private Timer timer;

    public EmployeePieChart() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        dataset = new DefaultPieDataset();
        updatePiechart();
        JFreeChart chart = ChartFactory.createPieChart("Employee Distribution by Department", dataset, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 300));
        chartPanel.setBackground(darkGrey);
        add(chartPanel);

        timer = new Timer(30000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	updatePiechart();
            }
        });
        timer.start();
    }

    void updatePiechart() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT department, COUNT(*) AS employee_count FROM employee GROUP BY department";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            DefaultPieDataset newDataset = new DefaultPieDataset();

            while (resultSet.next()) {
                String department = resultSet.getString("department");
                int employeeCount = resultSet.getInt("employee_count");
                newDataset.setValue(department, employeeCount);
            }
            dataset = newDataset;
            repaint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
