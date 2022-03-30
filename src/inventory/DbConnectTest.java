package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectTest {

	public static void main(String[] args) {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pftp", "root", "");//Establishing connection
			System.out.println("Connected With the database successfully");
			} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
			}

	}

}
