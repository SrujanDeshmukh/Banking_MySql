package com.banking;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Userview extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	Userview frame = new Userview();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                
            }
        });
    }

    /**
     * Create the frame.
     */
    public Userview() {
        // Set the frame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SBI Collect");

        // Panel setup
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Left-side image panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JLabel lblImage = new JLabel("");
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("path/to/your/image.jpg"); // Replace with the path to your image
        lblImage.setIcon(icon);
        leftPanel.add(lblImage, BorderLayout.CENTER);

        contentPane.add(leftPanel, BorderLayout.WEST);

        // Center panel for buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());  // Using GridBagLayout to center the buttons
        contentPane.add(centerPanel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding around buttons

        // Create buttons with centered alignment
        JButton btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setFont(new Font("Arial", Font.PLAIN, 22));
        btnCreateAccount.setPreferredSize(new Dimension(250, 50));
        btnCreateAccount.setBackground(new Color(0, 102, 204)); // SBI Blue Color
        btnCreateAccount.setForeground(Color.WHITE);
        btnCreateAccount.setFocusPainted(false);
        btnCreateAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCreateAccount.setBorder(BorderFactory.createEmptyBorder());
       
        btnCreateAccount.addActionListener(e -> {
        	
        	userCreateacc CA = new userCreateacc();
            CA.setVisible(true);
            setVisible(false);
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(btnCreateAccount, gbc);

        JButton btnCheckBalance = new JButton("Check Balance");
        btnCheckBalance.setFont(new Font("Arial", Font.PLAIN, 22));
        btnCheckBalance.setPreferredSize(new Dimension(250, 50));
        btnCheckBalance.setBackground(new Color(0, 102, 204)); // SBI Blue Color
        btnCheckBalance.setForeground(Color.WHITE);
        btnCheckBalance.setFocusPainted(false);
        btnCheckBalance.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCheckBalance.setBorder(BorderFactory.createEmptyBorder());
       
        btnCheckBalance.addActionListener(e -> {
        	UserCheckBalance CB = new UserCheckBalance();
            CB.setVisible(true);
            
            setVisible(false);
        });
        gbc.gridy = 1;
        centerPanel.add(btnCheckBalance, gbc);

//        JButton btnViewDetails = new JButton("View Acc Details");
//        btnViewDetails.setFont(new Font("Arial", Font.PLAIN, 22));
//        btnViewDetails.setPreferredSize(new Dimension(250, 50));
//        btnViewDetails.setBackground(new Color(0, 102, 204)); // SBI Blue Color
//        btnViewDetails.setForeground(Color.WHITE);
//        btnViewDetails.setFocusPainted(false);
//        btnViewDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btnViewDetails.setBorder(BorderFactory.createEmptyBorder());
//       
//        btnViewDetails.addActionListener(e -> {
//            Detail d = new Detail();
//            d.setVisible(true);
//            setVisible(false);
//        });
//        gbc.gridy = 2;
//        centerPanel.add(btnViewDetails, gbc);

        JButton btnDeleteAccount = new JButton("Delete Account");
        
        btnDeleteAccount.setFont(new Font("Arial", Font.PLAIN, 22));
        btnDeleteAccount.setPreferredSize(new Dimension(250, 50));
        btnDeleteAccount.setBackground(new Color(0, 102, 204)); // SBI Blue Color
        btnDeleteAccount.setForeground(Color.WHITE);
        btnDeleteAccount.setFocusPainted(false);
        btnDeleteAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeleteAccount.setBorder(BorderFactory.createEmptyBorder());
       
        
        btnDeleteAccount.addActionListener(e -> {
        	UserDeleteAccount ui = new UserDeleteAccount();
            ui.setVisible(true);
            setVisible(false);
        });
        gbc.gridy = 3;
        centerPanel.add(btnDeleteAccount, gbc);

        JButton BtnLoan = new JButton("Loan");
        BtnLoan.setFont(new Font("Arial", Font.PLAIN, 22));
        BtnLoan.setPreferredSize(new Dimension(250, 50));
        BtnLoan.setBackground(new Color(0, 102, 204)); // SBI Blue Color
        BtnLoan.setForeground(Color.WHITE);
        BtnLoan.setFocusPainted(false);
        
        BtnLoan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        BtnLoan.setBorder(BorderFactory.createEmptyBorder());
       
        BtnLoan.addActionListener(e -> {
Loan sa = new Loan();
            sa.setVisible(true);
            setVisible(false);
        });
        gbc.gridy = 4;
        centerPanel.add(BtnLoan, gbc);

        // Header setup
        JLabel headerLabel = new JLabel("SBI Collect Services");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        headerLabel.setForeground(new Color(0, 51, 153));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(headerLabel, BorderLayout.NORTH);
    }
}
