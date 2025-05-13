package com.banking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserType extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UserType frame = new UserType();
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
    public UserType() {
        // Set full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Frame title
        setTitle("SBI Collect - Select Role");

        // Use GridBagLayout for centered content
        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE); // Background color

        setContentPane(contentPane);
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Label
        JLabel lblTitle = new JLabel("Select Your Role");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(new Color(0, 102, 204)); // SBI Blue
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 50, 0);  // Top padding
        contentPane.add(lblTitle, gbc);

        // User Button
        JButton btnUser = new JButton("User");
        btnUser.setFont(new Font("Arial", Font.PLAIN, 22));
        btnUser.setPreferredSize(new Dimension(150, 50));
        btnUser.setBackground(new Color(0, 102, 204)); // SBI Blue Color
        btnUser.setForeground(Color.WHITE);
        btnUser.setFocusPainted(false);
        btnUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUser.setBorder(BorderFactory.createEmptyBorder());
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);  // Padding between buttons
        contentPane.add(btnUser, gbc);

        // Admin Button
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.setFont(new Font("Arial", Font.PLAIN, 22));
        btnAdmin.setPreferredSize(new Dimension(150, 50));
        btnAdmin.setBackground(new Color(0, 102, 204)); // SBI Blue Color
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFocusPainted(false);
        btnAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdmin.setBorder(BorderFactory.createEmptyBorder());
        gbc.gridy = 2;
        contentPane.add(btnAdmin, gbc);

        // Event Handlers
        btnUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the user login page
            	login userLogin = new login();
                userLogin.setVisible(true);
                setVisible(false);
            }
        });

        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the admin login page
            	login adminLogin = new login();
                adminLogin.setVisible(true);
                setVisible(false);
            }
        });
        
        // Styling with hover effect
        addButtonHoverEffect(btnUser);
        addButtonHoverEffect(btnAdmin);
    }

    // Method to add hover effect on buttons
    private void addButtonHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 153, 255)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204)); // Default color on exit
            }
        });
    }
}
