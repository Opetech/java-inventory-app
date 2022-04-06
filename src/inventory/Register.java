package inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame {

	JComboBox<String> gendersField;
	JTextField firstnameField;
	JTextField lastnameField;
	JTextField usernameField;
	JPasswordField passwordField;
	StringBuffer errorMsg = new StringBuffer();

	public Register() {
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

		// Register Form Panel
		JPanel registerFormPanel = new JPanel();
		registerFormPanel.setPreferredSize(new Dimension(700, 300));
		registerFormPanel.setLayout(null);
		registerFormPanel.setBackground(Color.lightGray);

		// Register Header
		JLabel registerHeader = new JLabel("Account Signup");
		registerHeader.setBounds(275, 40, 300, 40);
		registerHeader.setFont(new Font("", Font.BOLD, 20));

		// Registration Form Fields
		firstnameField = new JTextField("Enter Firstname");
		firstnameField.setInputVerifier(new FormValidation("textfield"));
		firstnameField.setBounds(200, 100, 300, 40);
		firstnameField.setText("Enter Firstname");

		lastnameField = new JTextField();
		lastnameField.setInputVerifier(new FormValidation("textfield"));
		lastnameField.setBounds(200, 150, 300, 40);
		lastnameField.setText("Enter Lastname");

		usernameField = new JTextField();
		usernameField.setInputVerifier(new FormValidation("textfield"));
		usernameField.setBounds(200, 200, 300, 40);
		usernameField.setText("Enter Username");

		String[] genderArray = { "Male", "Female" };
		gendersField = new JComboBox<>(genderArray);
		gendersField.setSelectedIndex(0); // Select male as default gender
		gendersField.setBounds(200, 250, 300, 40);

		passwordField = new JPasswordField("passwordfield");
		passwordField.setBounds(200, 300, 300, 40);
		passwordField.setText("Enter Password");

		JButton submitButton = new JButton("Register");
		submitButton.setBounds(200, 350, 100, 40);
		submitButton.setFocusable(false);
		submitButton.addActionListener(e -> {
			if (e.getSource() == submitButton) {
				this.storeUser();
			}
		});

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		registerFormPanel.add(registerHeader);
		registerFormPanel.add(firstnameField);
		registerFormPanel.add(lastnameField);
		registerFormPanel.add(usernameField);
		registerFormPanel.add(gendersField);
		registerFormPanel.add(passwordField);
		registerFormPanel.add(submitButton);

		this.setSize(700, 600);
		this.setTitle("Register");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.add(navbar, BorderLayout.NORTH);
		this.add(registerFormPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void storeUser() {
		Connection connection = DbConnect.connect();
		// Creating PreparedStatement object
		PreparedStatement preparedStatement = null;
		Statement selectStatement = null;

		try {
			// Check If Username exist in database
			preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
			preparedStatement.setString(1, usernameField.getText());
			ResultSet usernameExist = preparedStatement.executeQuery();

			if (usernameExist == null) {
				JOptionPane.showMessageDialog(this, "Sorry! Username provided is taken");
			} else {
				preparedStatement = connection.prepareStatement(
						"insert into users (id, firstname, lastname, username, gender, password, is_admin) values(?,?,?,?,?,?,?)");
				// Setting values for Each Parameter
				preparedStatement.setNull(1, Types.NULL);
				preparedStatement.setString(2, firstnameField.getText());
				preparedStatement.setString(3, lastnameField.getText());
				preparedStatement.setString(4, usernameField.getText());
				preparedStatement.setString(5, (String) gendersField.getSelectedItem());
				preparedStatement.setString(6, passwordField.getText());
				preparedStatement.setInt(7, 0);

				// Executing Query
				preparedStatement.executeUpdate();

				// Get details of user that just signed up
				preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
				preparedStatement.setString(1, usernameField.getText());
				ResultSet userResultSet = preparedStatement.executeQuery();

				String username = null;
				int userId = 0;
				while (userResultSet.next()) {
					username = userResultSet.getString("username");
					userId = userResultSet.getInt("id");
				}

				System.setProperty("userIsLoggedIn", "true");
				System.setProperty("username", username);
				System.setProperty("userId", String.valueOf(userId));
				
				preparedStatement.close();

				this.dispose();
				Index homepageIndex = new Index();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Perform form validation
	 *
	 */
	public class FormValidation extends InputVerifier {
		String formFieldType;

		public FormValidation(String formFieldType) {
			this.formFieldType = formFieldType;
		}

		@Override
		public boolean verify(JComponent input) {
			JTextField tf = formFieldType.equals("textfield") ? (JTextField) input : (JPasswordField) input;
			if (!tf.getText().isEmpty()) {
				return true;
			} else {
				Toolkit.getDefaultToolkit().beep();
				return false;
			}
		}

	}
}
