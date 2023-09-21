
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BasicCRUD {
	 static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       
        
        Connection connection = null;
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/regex_java"; 
            String jdbcUsername = "root"; 
            String jdbcPassword = "";
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Enter a choice : ");
        int choice = scanner.nextInt();
        
        

        String username = "";
        String name = "";
        String email = "";
        String phoneNumber = "";
        
        	switch(choice) {
        	case 1 : saveUserDetailsToDatabase(connection, username, name, email, phoneNumber);
        				break;

        }
        

        


        

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static void saveUserDetailsToDatabase(Connection connection, String username, String name, String email, String phoneNumber) {
        try {
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
