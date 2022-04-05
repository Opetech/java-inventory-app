package inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ViewProduct extends JFrame {
	JLabel cartLink;
	JLabel loginLink;
	JLabel registerLink;
	JLabel userProfileLabel;
	int productId;
	JButton addToCartBtn;

	public ViewProduct(int productId) {
		this.productId = productId;

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
		userProfileLabel = new JLabel("Hi, " + System.getProperty("username"));

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
		} else {

			rightNavbarItemsPanel.add(loginLink);
			rightNavbarItemsPanel.add(registerLink);
		}

		navbar.add(leftNavbarItemsPanel);
		navbar.add(rightNavbarItemsPanel);

		// View Product container
		JPanel productPanel = new JPanel(new GridLayout(0, 1));
		productPanel.setBorder(new EmptyBorder(30, 10, 20, 10));

		// Product Name Display
		JLabel productName = new JLabel("MacBook Air 2020");
		productName.setFont(new Font("MV Boli", Font.BOLD, 25));
		productName.setHorizontalAlignment(JLabel.CENTER);
		productPanel.add(productName);

		// Product Image Display
		JLabel productImg = new JLabel(
				new ImageIcon(new ImageIcon("/C://Users//USER//eclipse-workspace//Inventory//src//macbook.jpg")
						.getImage().getScaledInstance(600, 700, Image.SCALE_DEFAULT)));
		productPanel.add(productImg);
		productImg.setSize(200, 100);

		// Product Description
		JLabel productDescription = new JLabel(
				"<html>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum</html>");
		productPanel.add(productDescription);

		// Product Actions
		JPanel actionsPanel = new JPanel();

		// Create add to cart button
		addToCartBtn = new JButton("Add to Cart");
		addToCartBtn.setFocusable(false);
		addToCartBtn.addActionListener(e -> {
			if (e.getSource() == addToCartBtn) {
				addOrRemoveItemFromCart(productId);
			}
		});

		actionsPanel.add(addToCartBtn);
		productPanel.add(actionsPanel);

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("TechHub Inventory - View Product");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);
		this.add(navbar, BorderLayout.NORTH);
		this.add(productPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
//						this.pack();
		this.setSize(700, 700);
		this.setVisible(true);
	}

	/**
	 * //Add or remove item from cart
	 * 
	 * @param productBtn
	 */
	public void addOrRemoveItemFromCart(int productId) {
		String cartProductsId = System.getProperty("cartProductsId");
		if (cartProductsId == null) {
			System.setProperty("cartProductsId", String.valueOf(productId) + ",");
		} else {
			StringBuilder stringBuilder = new StringBuilder(cartProductsId);
			stringBuilder.append(String.valueOf(productId) + ",");
			System.setProperty("cartProductsId", stringBuilder.toString());
		}

		cartProductsId = System.getProperty("cartProductsId");
		ArrayList<String> productsId = new ArrayList<>(Arrays.asList(cartProductsId.split(",")));
      
		if (productsId.contains(String.valueOf(productId))) {
			productsId.remove(productsId.indexOf(productId));
			cartLink.setText("Carts " + (productsId.size() < 1 ? "" : "(" + productsId.size() + ")"));
			addToCartBtn.setText("Add To Cart");
		} else {
			productsId.add(String.valueOf(productId));
			cartLink.setText("Carts " + ("(" + productsId.size() + ")"));
			addToCartBtn.setText("Remove From Cart");
		}
		System.out.println(productsId);
	}
}
