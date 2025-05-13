package com.banking;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class seealldata extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    seealldata frame = new seealldata();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public seealldata() {
        // Full-screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Button to Display Data
        JButton btnNewButton = new JButton("Display Data");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(0, 102, 204)); // SBI Blue

        // Add hover effect
        btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButton.setBackground(new Color(0, 51, 153)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButton.setBackground(new Color(0, 102, 204)); // Reset to original color
            }
        });

        btnNewButton.setBounds(10, 11, 150, 30);
        contentPane.add(btnNewButton);

        // Scrollable Table to Display Data
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 60, 1200, 600); // Adjusted for full-screen size
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Button to Go Back to Main Menu
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setBackground(new Color(0, 102, 204)); // SBI Blue

        // Add hover effect
        btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButton_1.setBackground(new Color(0, 51, 153)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButton_1.setBackground(new Color(0, 102, 204)); // Reset to original color
            }
        });

        btnNewButton_1.setBounds(10, 680, 150, 30); // Positioned for full-screen
        contentPane.add(btnNewButton_1);

        // Action listener for Display Data button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchAndDisplayData();
            }
        });

        // Action listener for Back button
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main m = new Main();
                m.setVisible(true);
                setVisible(false);
            }
        });
    }

    // Method to fetch and display data from the database
    private void fetchAndDisplayData() {
        try {
            // Database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");
            Statement st = conn.createStatement();
            String query = "SELECT * FROM customer";
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            // Table model to hold data
            DefaultTableModel model = new DefaultTableModel();
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            // Adding rows to table
            String Account_number, First_name, Last_name, mobile_no, Address, amount;
            while (rs.next()) {
                Account_number = rs.getString(1);
                First_name = rs.getString(2);
                Last_name = rs.getString(3);
                mobile_no = rs.getString(4);
                Address = rs.getString(5);
                amount = rs.getString(6);
                String[] row = { Account_number, First_name, Last_name, mobile_no, Address, amount };
                model.addRow(row);
            }
            table.setModel(model);

            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
