package ManageAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utility.DatabaseConnection;

public class Admin {
	String userid;
	public Admin(String userid) {
		this.userid = userid;
}
    String getPassword(String userid) {
        String oldPasswordString = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String queryString = "select password from admins where userid = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(queryString);
            prepareStatement.setString(1, userid);

            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) { 
                oldPasswordString = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldPasswordString;
    }
    int setNewPassword(String userid, String password) {
    	try (Connection connection = DatabaseConnection.getConnection()) {
    		String queryString = "update admins set password = ? where userid = ?";
    		PreparedStatement prepareStatement = connection.prepareStatement(queryString);
    		prepareStatement.setString(1, password);
    		prepareStatement.setString(2, userid);
    		
    		int updated = prepareStatement.executeUpdate();
    		return updated;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return -1;
    	}
    }
    public String getAdminName(String userid) {
    	String oldPasswordString = null;
    	try (Connection connection = DatabaseConnection.getConnection()) {
    		String queryString = "select name from employee where userid = ?";
    		PreparedStatement prepareStatement = connection.prepareStatement(queryString);
    		prepareStatement.setString(1, userid);
    		
    		ResultSet resultSet = prepareStatement.executeQuery();
    		if (resultSet.next()) { 
    			oldPasswordString = resultSet.getString(1);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return oldPasswordString;
    }
}
