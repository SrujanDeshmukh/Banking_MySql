

package com.banking;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserDeleteAccount extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UserDeleteAccount frame = new UserDeleteAccount();
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
    public UserDeleteAccount() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Full-screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true); // Optional for a cleaner look

        // Set layout and center content
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label for deleting account
        JLabel lblNewLabel = new JLabel("Enter Account To Delete");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(0, 102, 204)); // SBI Blue
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(lblNewLabel, gbc);

        // TextField for Account number
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(textField, gbc);
        textField.setColumns(20);

        // Delete Account Button
        JButton btnNewButton = new JButton("Delete Account");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBackground(new Color(0, 102, 204)); // SBI Blue
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFocusPainted(false);

        // Hover effect for Delete Account button
        btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButton.setBackground(new Color(0, 51, 153)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButton.setBackground(new Color(0, 102, 204)); // Reset to original color
            }
        });

        // Delete ActionListener
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");
                    String query = "DELETE FROM customer WHERE Account_number = ?";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, textField.getText());
                    int deleted = ps.executeUpdate();
                    
                    if (deleted > 0) {
                        JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Account Not Found");
                    }
                    ps.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPane.add(btnNewButton, gbc);

        // Back Button
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton_1.setBackground(new Color(0, 102, 204)); // SBI Blue
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFocusPainted(false);

        // Hover effect for Back button
        btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButton_1.setBackground(new Color(0, 51, 153)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButton_1.setBackground(new Color(0, 102, 204)); // Reset to original color
            }
        });

        // Back button action
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Userview m = new Userview();
                m.setVisible(true);
                setVisible(false);
            }
        });

        gbc.gridy = 2;
        contentPane.add(btnNewButton_1, gbc);
    }
}

