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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import inventory.DbConnect;

public class Users extends JFrame {

	public Users() {
		AdminIncludes adminIncludes = new AdminIncludes();

		// contentSection container
		JPanel contentSectionPanel = new JPanel();
		contentSectionPanel.setLayout(new BorderLayout(0, 30));
		contentSectionPanel.add(adminIncludes.stats(), BorderLayout.NORTH);

		// User table list container
		JPanel usersListPanel = new JPanel(new BorderLayout(0, 20));

		// Container for table title and add new user button
		JPanel titleBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));

		JLabel title = new JLabel("Users");
		title.setFont(new Font("MV Boli", Font.BOLD, 25));

//		JButton addUserBtn = new JButton("+");
//		addUserBtn.setFocusable(false);
//		addUserBtn.setToolTipText("Add new user");
//		addUserBtn.addActionListener(e -> {
//			if (e.getSource() == addUserBtn) {
//				this.dispose();
//				Users usersPage = new Users();
//			}
//		});
//
		titleBtnPanel.add(title);
//		titleBtnPanel.add(addUserBtn);
//
		usersListPanel.add(titleBtnPanel, BorderLayout.NORTH);

		Connection connection = DbConnect.connect();
		Statement userStatement;
		// Data to be displayed in the JTable
		String[][] data = new String[10][3];

		// Column Names
		String[] columnNames = { "Firstname", "Lastname", "Gender"};
		JScrollPane usersScrollPane = null;
		try {
			// Select all users and load into users list table
			userStatement = connection.createStatement();
			ResultSet users = userStatement.executeQuery("SELECT * FROM users");

			int i = 0;
			while (users.next()) {
				data[i][0] = users.getString("firstname");
				data[i][1] = users.getString("lastname");
				data[i][2] = users.getString("gender");
				i++;
			}
			userStatement.close();

			// Initializing the JTable
			JTable usersTable = new JTable(data, columnNames);

			// adding table to JScrollPane to allow scrolling
			usersScrollPane = new JScrollPane(usersTable);
		} catch (Exception e) {
			e.printStackTrace();
		}

		usersListPanel.add(usersScrollPane, BorderLayout.CENTER);
		contentSectionPanel.add(usersListPanel, BorderLayout.CENTER);

		// Footer
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.lightGray);
		JLabel footerText = new JLabel("Copyright @2022");
		footerText.setForeground(Color.black);

		footerPanel.add(footerText);

		this.setTitle("Admin - Users");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(AdminIncludes.navbar(), BorderLayout.NORTH);
		this.add(contentSectionPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);
		this.setSize(900, 700);
		this.setVisible(true);
	}
}
