package admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminIncludes {

	JLabel productsStatIcon;
	JLabel usersStatIcon;
	JLabel ordersStatIcon;
	
	/**
	 * 
	 * @return Jpanel component that contains admin navbar
	 */
	public static Component navbar() {
		// NavBar Container Panel
		JPanel navbar = new JPanel();
		navbar.setLayout(new GridLayout(1, 1));
		navbar.setPreferredSize(new Dimension(700, 45));
		navbar.setBackground(Color.blue);

		// Navbar items Panel
		JPanel leftNavbarItemsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		JPanel rightNavbarItemsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

		// Navbar Items
		JLabel appTitle = new JLabel("Dashboard");
		appTitle.setFont(new Font("San Serif", Font.BOLD, 18));

		appTitle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Dashboard dashboard = new Dashboard();
			}
		});

		JLabel usersLink = new JLabel("Users");
		usersLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Users users = new Users();
			}
		});

		JLabel productsLink = new JLabel("Products");
		productsLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Products products = new Products();
			}
		});

		JLabel logoutLink = new JLabel("Logout");

		leftNavbarItemsPanel.add(appTitle);

		rightNavbarItemsPanel.add(productsLink);
		rightNavbarItemsPanel.add(usersLink);
		rightNavbarItemsPanel.add(logoutLink);

		navbar.add(leftNavbarItemsPanel);
		navbar.add(rightNavbarItemsPanel);

		return navbar;
	}
	

	/**
	 * @return Jpanel component that contains program stats (number of products, users and orders)
	 */
	public Component stats() {
		JPanel statsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));

		// Add sub panel to display dashboard details. E.g. Products, Orders and Users
		// Stats
		JPanel productStatWrapper = new JPanel();
		productStatWrapper.setPreferredSize(new Dimension(150, 100));
		productStatWrapper.setBackground(Color.DARK_GRAY);

		productsStatIcon = new JLabel(
				new ImageIcon(new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg")
						.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		productsStatIcon.setIconTextGap(15);
		productsStatIcon.setText("0");
		productsStatIcon.setForeground(Color.white);
		productsStatIcon.setFont(new Font("", Font.BOLD, 25));
		productStatWrapper.add(productsStatIcon);
		statsContainer.add(productStatWrapper);

		JPanel usersStatWrapper = new JPanel();
		usersStatWrapper.setPreferredSize(new Dimension(150, 100));
		usersStatWrapper.setBackground(Color.orange);

		usersStatIcon = new JLabel(
				new ImageIcon(new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg")
						.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		usersStatIcon.setIconTextGap(15);
		usersStatIcon.setText("0");
		usersStatIcon.setForeground(Color.white);
		usersStatIcon.setFont(new Font("", Font.BOLD, 25));
		usersStatWrapper.add(usersStatIcon);
		statsContainer.add(usersStatWrapper);

		JPanel ordersStatIconWrapper = new JPanel();
		ordersStatIconWrapper.setPreferredSize(new Dimension(150, 100));
		ordersStatIconWrapper.setBackground(Color.GREEN);

		ordersStatIcon = new JLabel(
				new ImageIcon(new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg")
						.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		ordersStatIcon.setHorizontalAlignment(JLabel.CENTER);
		ordersStatIcon.setIconTextGap(15);
		ordersStatIcon.setText("0");
		ordersStatIcon.setForeground(Color.white);
		ordersStatIcon.setFont(new Font("", Font.BOLD, 25));
		ordersStatIconWrapper.add(ordersStatIcon);
		statsContainer.add(ordersStatIconWrapper);

		return statsContainer;
	}
	
	/**
	 * 
	 * @param numOfProduct
	 */
	public void setProductStatLabel(int numOfProduct) {
		productsStatIcon.setText(String.valueOf(numOfProduct));
	}
	
	/**
	 * 
	 * @param numOfUser
	 */
	public void setUsersStatLabel(int numOfUser) {
		usersStatIcon.setText(String.valueOf(numOfUser));
	}
	
	/**
	 * 
	 * @param numOfOrders
	 */
	public void setOrdersStatLabel(int numOfOrders) {
		ordersStatIcon.setText(String.valueOf(numOfOrders));
	}
}
