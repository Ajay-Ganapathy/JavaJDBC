
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Regex {

	 static Scanner scanner = new Scanner(System.in);
	static Connection connection = null;
        
	 
    public static void main(String[] args) {
       
        
    	 try {
             String jdbcUrl = "jdbc:mysql://localhost:3306/regex_java"; 
             String jdbcUsername = "root"; 
             String jdbcPassword = "";
             connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
         } catch (SQLException e) {
             e.printStackTrace();
             System.exit(1);
         }

       while(true) {
    	     
    	   System.out.println("Enter your choice : ");
    	   System.out.println("Press 1 to Add User");
    	   System.out.println("Press 2 to Delete User");
    	   System.out.println("Press 3 to Update User");
    	   System.out.println("Press 4 to View User Details");
    	   System.out.println("Press any other Number to Exit");
           int choice = scanner.nextInt();
    	   switch(choice) {
           case 1 : addDetails();
           		break;
           case 2 : deleteDetail();
           		break;
           case 3 : updateUserDetail();
           		break;
           case 4 : displayDetails();
           		break;
           default : System.exit(1);
			           try {
			               if (connection != null) {
			                   connection.close();
			               }
			           } catch (SQLException e) {
			               e.printStackTrace();
			           }
           }
    	  

           
       }
       
       
       
       
    }

    private static void displayDetails() {
		// TODO Auto-generated method stub
    	try {
    		
    		 String selectSQL = "SELECT * FROM user_details";
        	 PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                 int userId = resultSet.getInt("id");
                 String userEmail = resultSet.getString("email");
                 String userName = resultSet.getString("username");
                 String name = resultSet.getString("name");
                 String phoneNumber = resultSet.getString("phone_number");
                 // Display the user details (you can format this output as needed)
                 System.out.println(" ID: " + userId);
                 System.out.println("Email: " + userEmail);
                 System.out.println("User Name: " + userName);
                 System.out.println(" Name: " + name);
                 System.out.println("Phone Number: " + phoneNumber);
                 System.out.println();
             }
    		
    		
    		 resultSet.close();
             preparedStatement.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	}
    	
	

	private static void updateUserDetail() {
		// TODO Auto-generated method stub
    	
    	System.out.println("Enter the id to Update Details : ");
    	int id = scanner.nextInt();
    	
    	 String username = "";
         String name = "";
         String email = "";
         String phoneNumber = "";
         

         while (true) {
             System.out.print("Enter userName: ");
             username = scanner.nextLine();
             if (isValidUsername(username)) {
                 System.out.println("User Name is Valid");
                 break;
             } else {
                 System.out.println("Invalid UserName: Re-enter the Name! User Name must contain only alphanumeric characters and underscore. It must be minimum 6 characters and maximum 12 characters!");
             }
         }
         

         while (true) {
             System.out.print("Enter Name: ");
             name = scanner.nextLine();
             if (isValidName(name)) {
                 System.out.println("Name is Valid");
                 break;
             } else {
                 System.out.println("Invalid Name");
             }
         }

         while (true) {
             System.out.print("Enter Email Address: ");
             email = scanner.nextLine();
             if (isValidEmail(email)) {
                 System.out.println("Email is Valid");
                 break;
             } else {
                 System.out.println("Invalid Email");
             }
         }

         
         while (true) {
             System.out.print("Enter Phone Number: ");
             phoneNumber = scanner.nextLine();
             if (isValidPhoneNumber(phoneNumber)) {
                 System.out.println("Phone Number is Valid");
                 break;
             } else {
                 System.out.println("Invalid Phone Number");
             }
         }
         	
         updateUser(connection, username, name, email, phoneNumber , id);
		
	}
    
    private static void updateUser(Connection connection, String username, String name, String email, String phoneNumber , int id) {
        try {
        	
            String updateQuery = "UPDATE user_details SET username = ?, name = ? , email = ? , phone_number = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setInt(5, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("No records updated. User with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving user details to the database.");
        }
    }

	private static void deleteDetail() {
		// TODO Auto-generated method stub
    	System.out.println("Enter the id of the Record : ");
    	int recordIdToDelete = scanner.nextInt();
    	try  {
            String deleteSQL = "DELETE FROM user_details WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, recordIdToDelete);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No records deleted. Record with ID " + recordIdToDelete + " not found.");
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getMessage());
        }
		
	}

	private static void addDetails() {
		// TODO Auto-generated method stub
    	
    	 String username = "";
         String name = "";
         String email = "";
         String phoneNumber = "";
         

         while (true) {
             System.out.print("Enter userName: ");
             username = scanner.nextLine();
             if (isValidUsername(username)) {
                 System.out.println("User Name is Valid");
                 break;
             } else {
                 System.out.println("Invalid UserName: Re-enter the Name! User Name must contain only alphanumeric characters and underscore. It must be minimum 6 characters and maximum 12 characters!");
             }
         }
         

         while (true) {
             System.out.print("Enter Name: ");
             name = scanner.nextLine();
             if (isValidName(name)) {
                 System.out.println("Name is Valid");
                 break;
             } else {
                 System.out.println("Invalid Name");
             }
         }

         while (true) {
             System.out.print("Enter Email Address: ");
             email = scanner.nextLine();
             if (isValidEmail(email)) {
                 System.out.println("Email is Valid");
                 break;
             } else {
                 System.out.println("Invalid Email");
             }
         }

         
         while (true) {
             System.out.print("Enter Phone Number: ");
             phoneNumber = scanner.nextLine();
             if (isValidPhoneNumber(phoneNumber)) {
                 System.out.println("Phone Number is Valid");
                 break;
             } else {
                 System.out.println("Invalid Phone Number");
             }
         }


         saveUserDetailsToDatabase(connection, username, name, email, phoneNumber);
		
	}

	private static void saveUserDetailsToDatabase(Connection connection, String username, String name, String email, String phoneNumber) {
        try {
            String insertQuery = "INSERT INTO user_details (username, name, email, phone_number) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phoneNumber);

            preparedStatement.executeUpdate();

            preparedStatement.close();

            System.out.println("User details saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving user details to the database.");
        }
    }

    private static boolean isValidUsername(String username) {
        // Username should only contain alphanumeric characters and underscores.
        String regex = "^[a-zA-Z0-9_]{6,12}$";
        return username.matches(regex);
    }

    public static boolean isValidName(String name){
        String regex = "[a-zA-Z]+\\s*[a-zA-Z\\sa-zA-Z]*$";
        return name.matches(regex);
    }

    private static boolean isValidEmail(String email) {
        // Basic email validation using regular expression.
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        // Phone number validation (simple pattern for demonstration purposes).
        String regex = "^[0-9]{10}$"; // Assumes 10-digit phone number.
        return phoneNumber.matches(regex);
    }
}
