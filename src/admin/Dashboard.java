package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import inventory.Index;
import inventory.ViewProduct;

public class Dashboard extends JFrame {

	public Dashboard() {
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

		JLabel productsStatIcon = new JLabel(
				new ImageIcon(new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg")
						.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		productsStatIcon.setIconTextGap(15);
		productsStatIcon.setText("121");
		productsStatIcon.setForeground(Color.white);
		productsStatIcon.setFont(new Font("", Font.BOLD, 25));
		productStatWrapper.add(productsStatIcon);
		breadcrumbContainer.add(productStatWrapper);

		JPanel usersStatWrapper = new JPanel();
		usersStatWrapper.setPreferredSize(new Dimension(150, 100));
		usersStatWrapper.setBackground(Color.orange);

		JLabel usersStatIcon = new JLabel(
				new ImageIcon(new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg")
						.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		usersStatIcon.setIconTextGap(15);
		usersStatIcon.setText("50");
		usersStatIcon.setForeground(Color.white);
		usersStatIcon.setFont(new Font("", Font.BOLD, 25));
		usersStatWrapper.add(usersStatIcon);
		breadcrumbContainer.add(usersStatWrapper);

		JPanel ordersStatIconWrapper = new JPanel();
		ordersStatIconWrapper.setPreferredSize(new Dimension(150, 100));
		ordersStatIconWrapper.setBackground(Color.GREEN);

		JLabel ordersStatIcon = new JLabel(new ImageIcon(new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg")
				.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		ordersStatIcon.setHorizontalAlignment(JLabel.CENTER);
		ordersStatIcon.setIconTextGap(15);
		ordersStatIcon.setText("50");
		ordersStatIcon.setForeground(Color.white);
		ordersStatIcon.setFont(new Font("", Font.BOLD, 25));
		ordersStatIconWrapper.add(ordersStatIcon);
		breadcrumbContainer.add(ordersStatIconWrapper);

		JPanel productsListPanel = new JPanel(new BorderLayout(0, 20));

		JLabel title = new JLabel("Recent Products");
		title.setFont(new Font("MV Boli", Font.BOLD, 25));
		title.setHorizontalAlignment(JLabel.CENTER);
		productsListPanel.add(title, BorderLayout.NORTH);

		// Data to be displayed in the JTable
		String[][] data = { { "Macbook Air", "$300", "21/03/2022" }, { "Macbook Air Pro", "$400", "21/03/2022" },
				{ "Macbook Air Pro", "$400", "21/03/2022" }, { "Macbook Air Pro", "$400", "21/03/2022" },
				{ "Macbook Air Pro", "$400", "21/03/2022" }, { "Macbook Air Pro", "$400", "21/03/2022" },
				{ "Macbook Air Pro", "$400", "21/03/2022" }, { "Macbook Air Pro", "$400", "21/03/2022" }, };

		// Column Names
		String[] columnNames = { "Name", "Price", "Created On" };

		// Initializing the JTable
		JTable productsTable = new JTable(data, columnNames);

		// adding it to JScrollPane
		JScrollPane productsScrollPane = new JScrollPane(productsTable);

		productsListPanel.add(productsScrollPane, BorderLayout.CENTER);

		contentSectionPanel.add(breadcrumbContainer, BorderLayout.NORTH);
		contentSectionPanel.add(productsListPanel, BorderLayout.CENTER);

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("Admin - 	Products");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//										this.setResizable(false);

		this.add(navbar, BorderLayout.NORTH);
		this.add(contentSectionPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setSize(900, 700);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new Dashboard();
	}

}
