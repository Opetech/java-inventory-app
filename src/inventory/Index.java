package inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	JLabel userProfileLabel;

	public Index() {
		Includes includes = new Includes();

		// Products Container Panel
		JPanel productsPanel = new JPanel();
		productsPanel.setLayout(new GridLayout(0, 4, 10, 20));
		productsPanel.setBorder(new EmptyBorder(0, 10, 0, 10));

		// Fetch and display products
		Connection connection = DbConnect.connect();
		Statement productsStatement;
		try {
			productsStatement = connection.createStatement();
			ResultSet products = productsStatement.executeQuery("SELECT * FROM products");
			while (products.next()) {
				// Product Container
				JPanel productPanel = new JPanel();
				productPanel.setLayout(new BorderLayout());

				JPanel product1 = new JPanel();
				product1.setPreferredSize(new Dimension(100, 250));

				ImageIcon productImg = new ImageIcon(products.getString("image_path"));

				JLabel productLabel = new JLabel();
				productLabel.setIcon(productImg);

				JPanel productNamePanel = new JPanel();
				JLabel productName = new JLabel(products.getString("name") + " - $" + products.getString("price"));

				JPanel productBtnPanel = new JPanel();
				JButton productBtn = new JButton("View Product");
				productBtn.setFocusable(false);
				productBtn.setName(String.valueOf(products.getInt("id")));
				productBtn.addActionListener(e -> {
					if (e.getSource() == productBtn) {
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
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("TechHub Inventory");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(includes.navbar(), BorderLayout.NORTH);
		this.add(productsPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setSize(700, 700);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Index index = new Index();
	}

}
