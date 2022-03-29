package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class Users extends JFrame {

	public Users() {
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

		// contentSection container
		JPanel contentSectionPanel = new JPanel();
		contentSectionPanel.setLayout(new BorderLayout(0, 30));

		JPanel breadcrumbContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));

		// Add sub panel to display dashboard details. E.g. Products, Orders and Users
		// Stats
		JPanel productStatWrapper = new JPanel();
		productStatWrapper.setPreferredSize(new Dimension(150, 100));
		productStatWrapper.setBackground(Color.DARK_GRAY);

		JLabel productsStat = new JLabel();
		productsStat.setText("121");
		productsStat.setForeground(Color.white);
		productsStat.setFont(new Font("", Font.BOLD, 25));
//		productsStat.setVerticalTextPosition(JLabel.CENTER);
		productStatWrapper.add(productsStat);
		breadcrumbContainer.add(productStatWrapper);

		JPanel usersStatWrapper = new JPanel();
		usersStatWrapper.setPreferredSize(new Dimension(150, 100));
		usersStatWrapper.setBackground(Color.orange);

		JLabel usersStat = new JLabel();
		usersStat.setText("50");
		usersStat.setForeground(Color.white);
		usersStat.setFont(new Font("", Font.BOLD, 25));
		usersStatWrapper.add(usersStat);
		breadcrumbContainer.add(usersStatWrapper);

		JPanel ordersStatWrapper = new JPanel();
		ordersStatWrapper.setPreferredSize(new Dimension(150, 100));
		ordersStatWrapper.setBackground(Color.GREEN);

		JLabel ordersStat = new JLabel();
		ordersStat.setText("50");
		ordersStat.setForeground(Color.white);
		ordersStat.setFont(new Font("", Font.BOLD, 25));
		ordersStatWrapper.add(ordersStat);
		breadcrumbContainer.add(ordersStatWrapper);
		
		
		JPanel usersListPanel = new JPanel(new BorderLayout(0, 20));

		//Container for table title and add new user button
		JPanel titleBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));
		
		JLabel title = new JLabel("Users");
		title.setFont(new Font("MV Boli", Font.BOLD, 25));
//		title.setHorizontalAlignment(JLabel.CENTER);
		
		JButton addUserBtn = new JButton("+");
		addUserBtn.setFocusable(false);
		addUserBtn.setToolTipText("Add new user");
		
		titleBtnPanel.add(title);
		titleBtnPanel.add(addUserBtn);
		
		usersListPanel.add(titleBtnPanel, BorderLayout.NORTH);
		

		// Data to be displayed in the JTable
		String[][] data = { { "Opeoluwa", "Olagoke", "Tallest", "Male", "21/03/2022" },
				{ "Opeoluwa", "Olagoke", "Tallest", "Male", "21/03/2022" },
				{ "Opeoluwa", "Olagoke", "Tallest", "Male", "21/03/2022" },
				{ "Opeoluwa", "Olagoke", "Tallest", "Male", "21/03/2022" }, };

		// Column Names
		String[] columnNames = { "Firstname", "Lastname", "Username", "Gender", "Created On" };

		// Initializing the JTable
		JTable usersTable = new JTable(data, columnNames);
		
		// adding it to JScrollPane
		JScrollPane usersScrollPane = new JScrollPane(usersTable);
		
		usersListPanel.add(usersScrollPane, BorderLayout.CENTER);

		contentSectionPanel.add(breadcrumbContainer, BorderLayout.NORTH);
		contentSectionPanel.add(usersListPanel, BorderLayout.CENTER);

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("Admin - Users");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//						this.setResizable(false);

		this.add(navbar, BorderLayout.NORTH);
		this.add(contentSectionPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setSize(900, 700);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Users();
	}

}
