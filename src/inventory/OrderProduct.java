package inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class OrderProduct extends JFrame {

	ArrayList<Integer> cartItems = new ArrayList<>();
	JLabel cartLink;
	JLabel loginLink;
	JLabel registerLink;
	JLabel userProfileLabel;
	int productId;
	JButton addToCartBtn;

	public OrderProduct() {

	}

	public void displayCart() {
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

		cartLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				OrderProduct cart = new OrderProduct();
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

		rightNavbarItemsPanel.add(cartLink);
		if (System.getProperty("userIsLoggedIn") == "true") {
		    rightNavbarItemsPanel.add(userProfileLabel);
		}else {
			
			rightNavbarItemsPanel.add(loginLink);
			rightNavbarItemsPanel.add(registerLink);
		}

		navbar.add(leftNavbarItemsPanel);
		navbar.add(rightNavbarItemsPanel);

		// View Product container
		JPanel cartPanel = new JPanel(new GridLayout(1, 2, 20, 20));
		cartPanel.setBorder(new EmptyBorder(30, 10, 20, 10));

//		// Header Panel Title
//		JPanel headerTitle = new JPanel();
//		JLabel title = new JLabel("Your Cart Items");
//		title.setFont(new Font("MV Boli", Font.BOLD, 20));
//		title.setHorizontalAlignment(JLabel.CENTER);
//		headerTitle.add(title);
//		cartPanel.add(headerTitle);

		// Cart Items
		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(0, 1));

		for (int i = 0; i < 4; i++) {
			// Product Item
			JLabel productItem = new JLabel(
					new ImageIcon(new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg")
							.getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT)));
			productItem.setText("<html><p>Macbook Air</p><p></p> <p>$300</p></html>");
			productItem.setIconTextGap(15);
			itemPanel.add(productItem);

			JPanel itemQuantityPanel = new JPanel();
			JButton itemQuantityAddBtn = new JButton("+");
			itemQuantityAddBtn.setFocusable(false);
			JLabel itemQuantity = new JLabel("1");
			JButton itemQuantityReduceBtn = new JButton("-");
			itemQuantityReduceBtn.setFocusable(false);

			itemQuantityPanel.add(itemQuantityReduceBtn);
			itemQuantityPanel.add(itemQuantity);
			itemQuantityPanel.add(itemQuantityAddBtn);

			itemPanel.add(itemQuantityPanel);
		}

		cartPanel.add(itemPanel);

		// Purchase Products Action
		JPanel actionsPanel = new JPanel();
		actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));

		// Total Price Label
		JLabel totalPrice = new JLabel();
		totalPrice.setText("Total Amount: $1200");
		totalPrice.setBorder(new EmptyBorder(5, 0, 40, 0));
		totalPrice.setFont(new Font("", Font.BOLD, 15));

		// Create Buy Item button
		JButton orderBtn = new JButton("Buy Item");
		orderBtn.setBackground(Color.ORANGE);
		orderBtn.setSize(100, 200);
		orderBtn.addActionListener(null);
		orderBtn.setFocusable(false);
		orderBtn.addActionListener(e -> {
			if (e.getSource() == orderBtn) {
				JOptionPane.showMessageDialog(this, "Your Order Has Been Placed Successfully", "Success",
						JOptionPane.DEFAULT_OPTION);
			}
		});

		actionsPanel.add(totalPrice);
		actionsPanel.add(orderBtn);

		cartPanel.add(actionsPanel);

		JScrollPane sp = new JScrollPane(cartPanel);

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("TechHub Inventory - View Product");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(navbar, BorderLayout.NORTH);
		this.add(sp, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setSize(700, 700);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.displayCart();
	}

}
