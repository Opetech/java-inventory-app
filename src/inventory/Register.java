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

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame {

	JComboBox<String> gendersField;
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
		JTextField firstnameField = new JTextField();
		firstnameField.setInputVerifier(new FormValidation("Firstname"));

		JTextField lastnameField = new JTextField();
		JTextField usernameField = new JTextField();
		String[] genderArray = { "Male", "Female" };

		gendersField = new JComboBox<>(genderArray);
		gendersField.setSelectedIndex(0); // Select male as default gender

		JPasswordField passwordField = new JPasswordField();
		JButton submitButton = new JButton("Register");

		submitButton.setFocusable(false);
		submitButton.addActionListener(e -> {
			if (e.getSource() == submitButton) {
			    System.out.println(errorMsg);
			}
		});

		firstnameField.setBounds(200, 100, 300, 40);
		lastnameField.setText("Enter Firstname");
		
		lastnameField.setBounds(200, 150, 300, 40);
		lastnameField.setText("Enter Lastname");

		usernameField.setBounds(200, 200, 300, 40);
		usernameField.setText("Enter Username");

		gendersField.setBounds(200, 250, 300, 40);

		passwordField.setBounds(200, 300, 300, 40);
		passwordField.setText("Enter Password");

		submitButton.setBounds(200, 350, 100, 40);

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

	/**
	 * Perform form validation
	 *
	 */
	public class FormValidation extends InputVerifier {
		String textFieldName;
			
		public FormValidation(String textFieldName) {
			this.textFieldName = textFieldName;
		}

		@Override
		public boolean verify(JComponent input) {
			Toolkit.getDefaultToolkit().beep();
			JTextField tf = (JTextField) input;
			if (!tf.getText().isEmpty()) {
				return true;
			} else {
				errorMsg.append(textFieldName + "cannot be empty \n");
				return false;
			}
		}

	}

	public static void main(String[] args) {
		new Register();
	}

}
