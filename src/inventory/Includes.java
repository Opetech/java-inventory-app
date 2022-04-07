package inventory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Includes {
	JLabel cartLink;
	JLabel loginLink;
	JLabel registerLink;
	JLabel userProfileLabel;

	public Component navbar() {
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

		appTitle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Index homepage = new Index();
			}
		});

		cartLink = new JLabel("Cart");
		userProfileLabel = new JLabel("Hi, " + System.getProperty("username"));
		loginLink = new JLabel("Login");
		registerLink = new JLabel("Register");

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

		cartLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				OrderProduct cart = new OrderProduct();
			}
		});

		leftNavbarItemsPanel.add(appTitle);

		rightNavbarItemsPanel.add(cartLink);
		if (System.getProperty("userIsLoggedIn") == "true") {
			rightNavbarItemsPanel.add(userProfileLabel);
		} else {

			rightNavbarItemsPanel.add(loginLink);
			rightNavbarItemsPanel.add(registerLink);
		}

		navbar.add(leftNavbarItemsPanel);
		navbar.add(rightNavbarItemsPanel);
		
		return navbar;
	}
}
