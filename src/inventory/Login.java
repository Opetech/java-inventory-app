package inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import admin.Dashboard;

public class Login extends JFrame {

	public Login() {
		Includes includes = new Includes();

		// Login Form Panel
		JPanel loginFormPanel = new JPanel();
		loginFormPanel.setPreferredSize(new Dimension(700, 300));
		loginFormPanel.setLayout(null);
		loginFormPanel.setBackground(Color.lightGray);

		// Login Header
		JLabel loginHeader = new JLabel("Account Login");
		loginHeader.setBounds(280, 40, 300, 40);
		loginHeader.setFont(new Font("", Font.BOLD, 20));

		// Login Form
		JTextField usernameField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		JButton submitButton = new JButton("Submit");
		submitButton.setFocusable(false);
		submitButton.addActionListener(null);

		usernameField.setBounds(200, 100, 300, 40);
		usernameField.setText("Enter Username");

		passwordField.setBounds(200, 150, 300, 40);
		passwordField.setText("Enter Password");

		submitButton.setBounds(200, 200, 100, 40);
		submitButton.addActionListener(e -> {
			if (e.getSource() == submitButton) {
				Connection connection = DbConnect.connect();
				// Get details of user that just signed up
				PreparedStatement preparedStatement;
				try {
					preparedStatement = connection
							.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
					preparedStatement.setString(1, usernameField.getText().toLowerCase());
					preparedStatement.setString(2, passwordField.getText());

					ResultSet userResultSet = preparedStatement.executeQuery();

					if (userResultSet.next()) {
						String username = userResultSet.getString("username");
						int userId = userResultSet.getInt("id");
						int userisadmin = userResultSet.getInt("is_admin");

						System.setProperty("userIsLoggedIn", "true");
						System.setProperty("username", username);
						System.setProperty("userId", String.valueOf(userId));

						// Redirect to admin dashboard or user homepage depending on logged in user
						// priviledge
						this.dispose();
						if (userisadmin == 1) {
							Dashboard dashboard = new Dashboard();
						} else {
							Index homepageIndex = new Index();
						}
					} else {
						JOptionPane.showMessageDialog(this, "Wrong Username and Password.");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		loginFormPanel.add(loginHeader);
		loginFormPanel.add(usernameField);
		loginFormPanel.add(passwordField);
		loginFormPanel.add(submitButton);

		this.setSize(700, 500);
		this.setTitle("Admin Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.add(includes.navbar(), BorderLayout.NORTH);
		this.add(loginFormPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
}
