package ManageEmployee;

import java.sql.*;
import java.util.*;

import Utility.DatabaseConnection;

public class Employee {
	
	Object[] getEmployee(String userid) {
	    userid = userid.toLowerCase().trim(); // Assign back the result of string manipulation to userid
	    if (searchEmployee(userid)) {
	        String queryString = null;
	        try {
	            Connection connection = DatabaseConnection.getConnection();
	            PreparedStatement preparedStatement;

	            queryString = "select userid, name, phone, department, level, salary, dob, address from employee where userid = ?";
	            preparedStatement = connection.prepareStatement(queryString);
	            preparedStatement.setString(1, userid);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            Object[] rows = null;
	            try {
	                // Get the column count
	                ResultSetMetaData metaData = resultSet.getMetaData();
	                int columnCount = metaData.getColumnCount();
	                rows = new Object[columnCount]; // Initialize rows with correct size

	                while (resultSet.next()) {
	                    for (int i = 1; i <= columnCount; i++) {
	                        Object rowData = resultSet.getObject(i);
	                        rows[i - 1] = rowData;
	                    }
	                }
	                return rows;
	            } catch (Exception e) {
	                e.printStackTrace();
	                return null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    } else {
	        return null;
	    }
	}

    /*
     * searching if employee exists
     */
    public boolean searchEmployee(String userid) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String searchQuery = "select userid, name from employee where userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setString(1, userid);
            ResultSet searchedEmployees = preparedStatement.executeQuery();
            return searchedEmployees.next();
        } catch (Exception e) {
            System.out.println("Error in searchEmployee");
            e.printStackTrace();
            return false;
        }
    }
    /*
     * searching deleted employee exists
     */
    public boolean searchDeletedEmployee(String userid) {
    	try {
    		Connection connection = DatabaseConnection.getConnection();
    		String searchQuery = "select userid, name from deletedemployee where userid = ?";
    		PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
    		preparedStatement.setString(1, userid);
    		ResultSet searchedEmployees = preparedStatement.executeQuery();
    		return searchedEmployees.next();
    	} catch (Exception e) {
    		System.out.println("Error in searchDeletedEmployee");
    		e.printStackTrace();
    		return false;
    	}
    }

    /*
     * view employee method
     */
    public Object[][] viewEmployee(String department) {
        department = department.toLowerCase(); 
        String queryString = null;
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement;

            if (department.equalsIgnoreCase("all")) {
                queryString = "select userid, name, phone, department, level, salary, dob, address from employee";
                preparedStatement = connection.prepareStatement(queryString);
            } else {
                queryString = "select userid, name, phone, department, level, salary, dob, address from employee where department = ?";
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, department);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
        
            List<Object[]> rows = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = resultSet.getObject(i);
                    }
                    rows.add(rowData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Object[][] resultArray = new Object[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                resultArray[i] = rows.get(i);
            }
            return resultArray;

        } catch (Exception e) {
            System.out.println("Failed to view employee");
            e.printStackTrace();
            return null;
        }
    }
    /*
     * view employee method
     */
    public Object[][] viewDeletedEmployee() {
    	String queryString = "select userid, name, phone, department, level, salary, dob, address from deletedemployee";
    	try {
			
		
    	Connection connection = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);

		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		List<Object[]> rows = new ArrayList<>();
    		try {
    			while (resultSet.next()) {
    				int columnCount = resultSet.getMetaData().getColumnCount();
    				Object[] rowData = new Object[columnCount];
    				for (int i = 1; i <= columnCount; i++) {
    					rowData[i - 1] = resultSet.getObject(i);
    				}
    				rows.add(rowData);
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		Object[][] resultArray = new Object[rows.size()][];
    		for (int i = 0; i < rows.size(); i++) {
    			resultArray[i] = rows.get(i);
    		}
    		return resultArray;
    	} catch (Exception ee) {
    		ee.printStackTrace();
    		return null;
		}
    }

    /*
     * add employee method
     */
    public boolean addEmployee(String userid, String name, long phone, String department, String level, long salary, String dob, String address) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (searchEmployee(userid)) {
                System.out.println("Employee already exists");
                return false;
            } else {
                String queryString = "insert into employee (userid, name, phone, department, level, salary, dob, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, userid);
                preparedStatement.setString(2, name);
                preparedStatement.setLong(3, phone);
                preparedStatement.setString(4, department);
                preparedStatement.setString(5, level);
                preparedStatement.setLong(6, salary);
                preparedStatement.setString(7, dob);
                preparedStatement.setString(8, address);

                int inserted = preparedStatement.executeUpdate();
                if (inserted > 0) {
                    System.out.println("Employee added successfully");
                    return true;
                } else {
                    System.out.println("Failed to add employee");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in adding employee");
            e.printStackTrace();
            return false;
        }
    }

    /*
     * modifying employee
     */
    public boolean modifyEmployee(String userid, String name, long phone, String department, String level, long salary, String dob, String address) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (!searchEmployee(userid)) {
                System.out.println("Employee does not exist");
                return false;
            } else {
                String queryString = "UPDATE employee SET name=?, phone=?, department=?, level=?, salary=?, dob=?, address=? WHERE userid=?";
                PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, phone);
                preparedStatement.setString(3, department);
                preparedStatement.setString(4, level);
                preparedStatement.setLong(5, salary);
                preparedStatement.setString(6, dob);
                preparedStatement.setString(7, address);
                preparedStatement.setString(8, userid);

                int updated = preparedStatement.executeUpdate();
                if (updated > 0) {
//                    System.out.println("Employee data updated successfully");
                    return true;
                } else {
//                    System.out.println("Failed to update employee data");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in updating employee data");
            e.printStackTrace();
            return false;
        }
    }

    /*
     * delete employee
     */
  public boolean deleteEmployee(String userid) {
    	

        try {
            if (searchEmployee(userid)) {
                Connection connection = DatabaseConnection.getConnection();
                String insertToDeletedEmployee = "insert into deletedemployee select * from employee where userid = ? ";
                PreparedStatement insertStatement = connection.prepareStatement(insertToDeletedEmployee);
                insertStatement.setString(1, userid);
                int insertedtodeletedEmployee = insertStatement.executeUpdate();
                if (insertedtodeletedEmployee > 0) {
					
                	String deleteEmpQuery = "delete from employee where userid = ?";
                	PreparedStatement preparedStatement = connection.prepareStatement(deleteEmpQuery);
                	preparedStatement.setString(1, userid);
                	int deleteCount = preparedStatement.executeUpdate();
                	System.out.println("Employee deleted successfully");
                	connection.close();
                	return deleteCount > 0;
				} else {
					System.out.println("error in inserting to deletedemployee table");
					return false;
				}
            } else {
                System.out.println("Employee does not exist");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to delete employee\n Admin can not be deleted by an admin");
            e.printStackTrace();
            return false;
        }
    }
  /*
   * delete employee
   */
  public boolean deleteDeletedEmployee(String userid) {
	  userid.trim();
	  
	  try {
		  if (searchDeletedEmployee(userid)) {
			  Connection connection = DatabaseConnection.getConnection();
				  String deleteEmpQuery = "delete from deletedemployee where userid = ?";
				  PreparedStatement preparedStatement = connection.prepareStatement(deleteEmpQuery);
				  preparedStatement.setString(1, userid);
				  int deleteCount = preparedStatement.executeUpdate();
				  System.out.println("Employee deleted from deletedEmployee table successfully");
				  connection.close();
				  return deleteCount > 0;
			  } else {
				  System.out.println("error in inserting to deletedemployee table");
				  return false;
			  }
	  } catch (Exception e) {
		  System.out.println("Failed to delete employee");
		  e.printStackTrace();
		  return false;
	  }
  }

    
    public boolean retrieveEmployee(String userid) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Check if the user exists in deletedemployee table
            String checkDeletedQuery = "SELECT * FROM deletedemployee WHERE userid = ?";
            PreparedStatement checkDeletedStatement = connection.prepareStatement(checkDeletedQuery);
            checkDeletedStatement.setString(1, userid);
            ResultSet deletedResult = checkDeletedStatement.executeQuery();

            if (deletedResult.next()) { 
            	// If user exists in deletedemployee table
                // Retrieve user data
                String name = deletedResult.getString("name");
                long phone = deletedResult.getLong("phone");
                String department = deletedResult.getString("department");
                String level = deletedResult.getString("level");
                long salary = deletedResult.getLong("salary");
                String dob = deletedResult.getString("dob");
                String address = deletedResult.getString("address");

                // Insert user data into employee table
                String insertQuery = "INSERT INTO employee (userid, name, phone, department, level, salary, dob, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, userid);
                insertStatement.setString(2, name);
                insertStatement.setLong(3, phone);
                insertStatement.setString(4, department);
                insertStatement.setString(5, level);
                insertStatement.setLong(6, salary);
                insertStatement.setString(7, dob);
                insertStatement.setString(8, address);
                int inserted = insertStatement.executeUpdate();

                if (inserted > 0) {
                    System.out.println("Employee retrieved successfully");

                    // Delete entry from deletedemployee table
                    String deleteQuery = "DELETE FROM deletedemployee WHERE userid = ?";
                    PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                    deleteStatement.setString(1, userid);
                    int deleted = deleteStatement.executeUpdate();

                    if (deleted > 0) {
                        System.out.println("Employee data deleted from deletedemployee table");
                    } else {
                        System.out.println("Failed to delete employee data from deletedemployee table");
                    }

                    return true;
                } else {
                    System.out.println("Failed to add retrieved employee to employee table");
                    return false;
                }
            } else {
                System.out.println("Employee not found in deletedemployee table");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error in retrieving employee");
            e.printStackTrace();
            return false;
        }
    }


   
    //    public static void main(String[] args) {
//        Employee employee = new Employee();
        // Test methods
//        employee.addEmployee("e01", "Tanuj", 1234567890, "sales", "entry", 20000, "2007-01-01", "sitamarhi");
//        employee.viewEmployee("all");
//        employee.deleteEmployee("e05");
//        employee.retrieveEmployee("e01");
        // employee.modifyEmployee("e01", "Tanuj Kumar", 1234567890, "sales", "entry", 20000, "2007-01-01", "Pupri");
//    }
}
