package com.banking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdminLoanView extends JFrame {

    private JPanel contentPane;
    private JPanel cardPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminLoanView frame = new AdminLoanView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminLoanView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Launch in full-screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true); // Optional if you want a frameless window

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Header panel (for optional logo or app name and Back button)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 102, 204)); // Professional blue
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 40));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 69, 0)); // Orange color for the back button
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Add action for the back button (For now, it just closes the current window)
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Here you can define what happens on back, like navigating to another page.
                // For now, it simply closes the window.
            	Main m = new Main();
                m.setVisible(true);
                setVisible(false);
                // Closes the current window and returns to the previous one
            }
        });

        headerPanel.add(backButton, BorderLayout.WEST); // Add Back button to the left side of header panel

        JLabel headerLabel = new JLabel("Loan Approval Dashboard", SwingConstants.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel, BorderLayout.CENTER); // Center the label

        contentPane.add(headerPanel, BorderLayout.NORTH); // Add header panel to the top

        // Center Panel (Scrollable Card View)
        JScrollPane scrollPane = new JScrollPane();
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());  // Center cards
        scrollPane.setViewportView(cardPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Fetch and Display Loans in Card View
        fetchAndDisplayLoans();
    }

    // Fetch loan data from the database and create cards for each loan
    private void fetchAndDisplayLoans() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");

            String query = "SELECT Account_number, Status, amount, loan_type FROM loans WHERE Status = 'Pending'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = GridBagConstraints.RELATIVE;
            gbc.insets = new Insets(15, 15, 15, 15); // Space between cards

            // Iterate through the result set and create a card for each loan
            while (rs.next()) {
                String accountNumber = rs.getString("Account_number");
                String loanStatus = rs.getString("Status");
                String loanType = rs.getString("loan_type");
                double loanAmount = rs.getDouble("amount");

                // Create a card for the loan
                JPanel card = createLoanCard(accountNumber, loanStatus, loanType, loanAmount);
                cardPanel.add(card, gbc);
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create a card for each loan with professional design
    private JPanel createLoanCard(String accountNumber, String loanStatus, String loanType, double loanAmount) {
        JPanel card = new JPanel();
        card.setLayout(new GridLayout(5, 2, 10, 10)); 
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Padding inside the card
        card.setPreferredSize(new Dimension(400, 200)); // Smaller card size
        card.setBackground(Color.WHITE); // White background for contrast

        // Add shadow and rounded corners
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Loan details
        card.add(new JLabel("Account Number:"));
        card.add(new JLabel(accountNumber));

        card.add(new JLabel("Loan Status:"));
        card.add(new JLabel(loanStatus));

        card.add(new JLabel("Loan Type:"));
        card.add(new JLabel(loanType));

        card.add(new JLabel("Loan Amount:"));
        card.add(new JLabel(String.format("$%.2f", loanAmount)));

        // Approve Button with hover effect
        JButton approveBtn = new JButton("Approve Loan");
        approveBtn.setBackground(new Color(0, 102, 204)); // Professional button color
        approveBtn.setForeground(Color.WHITE);
        approveBtn.setFocusPainted(false);
        approveBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        approveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveLoan(accountNumber);
            }
        });

        // Add hover effect for button
        approveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                approveBtn.setBackground(new Color(0, 51, 153)); // Darker on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                approveBtn.setBackground(new Color(0, 102, 204)); // Reset color
            }
        });

        card.add(new JLabel(""));  // Empty label to balance the grid layout
        card.add(approveBtn);

        return card;
    }

    // Action to Approve the Loan
    private void approveLoan(String accountNumber) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");

            String updateQuery = "UPDATE loans SET Status = 'Approved' WHERE Account_number = ?";
            PreparedStatement ps = conn.prepareStatement(updateQuery);
            ps.setString(1, accountNumber);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Loan Approved for Account: " + accountNumber);

            ps.close();
            conn.close();

            // Refresh the view after approval
            cardPanel.removeAll();  // Clear the card panel
            fetchAndDisplayLoans(); // Reload the loans

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
