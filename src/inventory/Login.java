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

public class Login extends JFrame {

	public Login() {

		// NavBar Container Panel
		JPanel navbar = new JPanel();
		navbar.setLayout(new GridLayout(1, 1));
		navbar.setPreferredSize(new Dimension(700, 45));
		navbar.setBackground(Color.blue);

		// Navbar items Panel
		JPanel leftNavbarItemsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		JPanel rightNavbarItemsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

		// Navbar Items
		JLabel appTitle = new JLabel("TechHub Inventory");
		appTitle.setFont(new Font("San Serif", Font.BOLD, 18));
		JLabel loginLink = new JLabel("Login");
		JLabel registerLink = new JLabel("Register");

		appTitle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Index homepage = new Index();
			}
		});

		registerLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Register registerPage = new Register();
			}
		});

		loginLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Login loginPage = new Login();
			}
		});

		leftNavbarItemsPanel.add(appTitle);
		rightNavbarItemsPanel.add(loginLink);
		rightNavbarItemsPanel.add(registerLink);

		navbar.add(leftNavbarItemsPanel);
		navbar.add(rightNavbarItemsPanel);

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
					preparedStatement.setString(1, usernameField.getText());
					preparedStatement.setString(2, passwordField.getText());

					ResultSet userResultSet = preparedStatement.executeQuery();

					if (!userResultSet.next()) {
						JOptionPane.showMessageDialog(this, "Wrong Username and Password.");
					} else {
						String username = null;
						int userId = 0;
						while (userResultSet.next()) {
							username = userResultSet.getString("username");
							userId = userResultSet.getInt("id");
						}

						System.setProperty("userIsLoggedIn", "true");
						System.setProperty("username", username);
						System.setProperty("userId", String.valueOf(userId));
						
						this.dispose();
						Index homepageIndex = new Index();
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

		this.add(navbar, BorderLayout.NORTH);
		this.add(loginFormPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
}
