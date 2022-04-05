package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// Establishing connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java-inventory-app", "root", "");
		} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}

		return connection;

	}

	public static void main(String[] args) {
		DbConnect.connect();
	}

}
