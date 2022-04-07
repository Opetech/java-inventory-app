package admin;

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
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import inventory.DbConnect;

public class Products extends JFrame {

	public Products() {
		AdminIncludes adminIncludes = new AdminIncludes(); 

		// contentSection container
		JPanel contentSectionPanel = new JPanel();
		contentSectionPanel.setLayout(new BorderLayout(0, 30));
		contentSectionPanel.add(adminIncludes.stats(), BorderLayout.NORTH);
		

		JPanel productsListPanel = new JPanel(new BorderLayout(0, 20));

		// Container for table title and add new user button
		JPanel titleBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));

		JLabel title = new JLabel("Product");
		title.setFont(new Font("MV Boli", Font.BOLD, 25));

		JButton addProductBtn = new JButton("+");
		addProductBtn.setFocusable(false);
		addProductBtn.setToolTipText("Add new product");
		addProductBtn.addActionListener(e -> {
			if (e.getSource() == addProductBtn) {
				this.dispose();
				AddProduct addProductsPage = new AddProduct();
			}
		});

		titleBtnPanel.add(title);
		titleBtnPanel.add(addProductBtn);

		productsListPanel.add(titleBtnPanel, BorderLayout.NORTH);

		Connection connection = DbConnect.connect();
		Statement productStatement;
		// Data to be displayed in the JTable
		String[][] data = new String[10][3];

		// Column Names
		String[] columnNames = { "Name", "Price", "Created On" };
		JScrollPane productsScrollPane = null;
		try {
			// Select all products and load into products list table
			productStatement = connection.createStatement();
			ResultSet products = productStatement.executeQuery("SELECT * FROM products");

			int i = 0;
			while (products.next()) {
				data[i][0] = products.getString("name");
				data[i][1] = products.getString("price");
				data[i][2] = products.getDate("created_at") + "";
				i++;
			}			
			productStatement.close();

			// Initializing the JTable
			JTable productsTable = new JTable(data, columnNames);

			// adding table to JScrollPane to allow scrolling
			productsScrollPane = new JScrollPane(productsTable);
		} catch (Exception e) {
			e.printStackTrace();
		}

		productsListPanel.add(productsScrollPane, BorderLayout.CENTER);
		contentSectionPanel.add(productsListPanel, BorderLayout.CENTER);

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("Admin - 	Products");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(AdminIncludes.navbar(), BorderLayout.NORTH);
		this.add(contentSectionPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setSize(900, 700);
		this.setVisible(true);
	}
}
