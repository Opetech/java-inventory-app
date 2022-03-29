package inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Index extends JFrame {

	ArrayList<Integer> cartItems = new ArrayList<>();
	JLabel cartLink;
	JLabel loginLink;
	JLabel registerLink;

	public Index() {
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
		rightNavbarItemsPanel.add(loginLink);
		rightNavbarItemsPanel.add(registerLink);

		navbar.add(leftNavbarItemsPanel);
		navbar.add(rightNavbarItemsPanel);

		// Products Container Panel
		JPanel productsPanel = new JPanel();
		productsPanel.setLayout(new GridLayout(0, 4, 10, 20));
		productsPanel.setBorder(new EmptyBorder(0, 10, 0, 10));

		// Display Products
		for (int i = 0; i < 7; i++) {
			// Product Container
			JPanel productPanel = new JPanel();
			productPanel.setLayout(new BorderLayout());

			JPanel product1 = new JPanel();
			product1.setPreferredSize(new Dimension(100, 250));

			ImageIcon productImg = new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg");

			JLabel productLabel = new JLabel();
			productLabel.setIcon(productImg);

			JPanel productNamePanel = new JPanel();
			JLabel productName = new JLabel("MacBook Air 2020 - $300");

			JPanel productBtnPanel = new JPanel();
			JButton productBtn = new JButton("View Product");
			productBtn.setFocusable(false);
			productBtn.setName(String.valueOf(i));
			productBtn.addActionListener(e -> {
				if(e.getSource() == productBtn) {
					this.dispose();
				}
				ViewProduct viewProduct = new ViewProduct(Integer.valueOf(productBtn.getName()));
			});

			product1.add(productLabel);
			productNamePanel.add(productName);
			productBtnPanel.add(productBtn);

			productPanel.add(product1, BorderLayout.NORTH);
			productPanel.add(productNamePanel, BorderLayout.CENTER);
			productPanel.add(productBtnPanel, BorderLayout.SOUTH);

			productsPanel.add(productPanel);
		}

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("TechHub Inventory");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);

		this.add(navbar, BorderLayout.NORTH);
		this.add(productsPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Index index = new Index();
	}

}
