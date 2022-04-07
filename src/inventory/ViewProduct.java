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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		Includes includes = new Includes();
		this.productId = productId;
		JPanel productPanel = null;

		// Fetch product from database
		try {
			Connection connection = DbConnect.connect();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
			preparedStatement.setInt(1, productId);
			ResultSet product = preparedStatement.executeQuery();
			product.next();

			// View Product container
			productPanel = new JPanel(new GridLayout(0, 1));
			productPanel.setBorder(new EmptyBorder(30, 10, 20, 10));

			// Product Name Display
			JLabel productName = new JLabel(product.getString("name"));
			productName.setFont(new Font("MV Boli", Font.BOLD, 25));
			productName.setHorizontalAlignment(JLabel.CENTER);
			productPanel.add(productName);

			// Product Image Display
			JLabel productImg;

			productImg = new JLabel(new ImageIcon(new ImageIcon(product.getString("image_path")).getImage()
					.getScaledInstance(600, 700, Image.SCALE_DEFAULT)));

			productPanel.add(productImg);
			productImg.setSize(200, 100);

			// Product Description
			JLabel productDescription = new JLabel("<html>" + product.getString("description") + "</html>");
			productPanel.add(productDescription);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		this.add(includes.navbar(), BorderLayout.NORTH);
		this.add(productPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
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
