package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddProduct extends JFrame {

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
		
		//Add Product Form
		JTextField productTitle = new JTextField("Product Title");
		productTitle.setBounds(200, 100, 300, 40);
		formPanel.add(productTitle);
		
		JTextField productPrice = new JTextField("Product Price");
		productPrice.setBounds(200, 150, 300, 40);
		formPanel.add(productPrice);
		
		JTextArea productDesc = new JTextArea("Description");
		productDesc.setBounds(200, 200, 300, 200);
		formPanel.add(productDesc);
		
		
		
		JButton submitButton =  new JButton("Submit");
		submitButton.setFocusable(false);
		submitButton.addActionListener(null);
		submitButton.setBounds(200, 420, 100, 40);
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
//		this.pack();
		this.setSize(700, 700);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new AddProduct();
	}

}
