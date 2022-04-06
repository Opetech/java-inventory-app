package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import inventory.DbConnect;

public class AddProduct extends JFrame {
	String selectedFilePath;

	public AddProduct() {
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
		JLabel loginLink = new JLabel("Carts");
		JLabel registerLink = new JLabel("Hi! User");

		leftNavbarItemsPanel.add(appTitle);
		rightNavbarItemsPanel.add(loginLink);
		rightNavbarItemsPanel.add(registerLink);

		navbar.add(leftNavbarItemsPanel);
		navbar.add(rightNavbarItemsPanel);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(null);

		JLabel title = new JLabel("Add Product");
		title.setFont(new Font("", Font.BOLD, 22));
		title.setBounds(200, 10, 300, 40);
		formPanel.add(title);

		// Add Product Form
		JTextField productTitle = new JTextField("Product Title");
		productTitle.setBounds(200, 100, 300, 40);
		formPanel.add(productTitle);
		productTitle.setInputVerifier(new FormValidation<JTextField>(new JTextField()));

		JTextField productPrice = new JTextField("Product Price");
		productPrice.setBounds(200, 150, 300, 40);
		formPanel.add(productPrice);
		productPrice.setInputVerifier(new FormValidation<JTextField>(new JTextField()));

		JTextArea productDesc = new JTextArea("Description");
		productDesc.setBounds(200, 200, 300, 200);
		formPanel.add(productDesc);
		productDesc.setInputVerifier(new FormValidation<JTextArea>(new JTextArea()));

		JButton selectImageBtn = new JButton("Choose File");
		selectImageBtn.setBounds(200, 420, 150, 30);
		selectImageBtn.addActionListener(e -> {
			if (e.getSource() == selectImageBtn) {
				JFileChooser chooseFile = new JFileChooser();
				// Process image file select and extract file path 
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG &amp; GIF Images", "jpg", "png",
						"gif");
				chooseFile.setFileFilter(filter);
				int returnVal = chooseFile.showOpenDialog(this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {

					selectedFilePath = chooseFile.getSelectedFile().getAbsolutePath();
				}
			}
		});
		formPanel.add(selectImageBtn);

		JButton submitButton = new JButton("Submit");
		submitButton.setFocusable(false);
		submitButton.addActionListener(e -> {
			if (selectedFilePath == null) {
				JOptionPane.showMessageDialog(this, "Cover image must be selected");
			} else {
				Connection connection = DbConnect.connect();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO products(id, name, description, price, image_path, created_at) values(?,?,?,?,?,?)");
					preparedStatement.setNull(1, Types.NULL);
					preparedStatement.setString(2, productTitle.getText());
					preparedStatement.setString(3, productDesc.getText());
					preparedStatement.setDouble(4, Double.valueOf(productPrice.getText()));
					preparedStatement.setString(5, selectedFilePath);
					preparedStatement.setString(6, "2022-4-6");

					preparedStatement.executeUpdate();
					
				  JOptionPane.showMessageDialog(this, "Product Added Successfully");
				  
				  //Redirect to Admin Dashboard
                  this.dispose();
                  Dashboard dashboard =  new Dashboard();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		submitButton.setBounds(200, 470, 100, 40);
		formPanel.add(submitButton);

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("TechHub Inventory - Add Product");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(navbar, BorderLayout.NORTH);
		this.add(formPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setSize(700, 700);
		this.setVisible(true);
	}

	/**
	 * Perform form validation
	 *
	 */
	public class FormValidation<T> extends InputVerifier {
		T formFieldType;
		
		public FormValidation(T formFieldType) {
			this.formFieldType = formFieldType;
		}

		@Override
		public boolean verify(JComponent input) {
			
			if (formFieldType instanceof JTextField) {
				JTextField tf = (JTextField) input;
				if (!tf.getText().isEmpty()) {
					return true;
				} else {
					Toolkit.getDefaultToolkit().beep();
					return false;
				}
			} else {
				JTextArea tf = (JTextArea) input;
				if (!tf.getText().isEmpty()) {
					return true;
				} else {
					Toolkit.getDefaultToolkit().beep();
					return false;
				}
			}
		}

	}

}
