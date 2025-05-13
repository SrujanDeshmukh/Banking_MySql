package com.banking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Detail extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtAccountNumber;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtMobileNo;
    private JTextField txtAddress;
    private JTextField txtAmount;
    private JLabel lblResult;
    private JButton btnCheckDetails;
    private JButton btnUpdateDetails;
    private JButton btnBack;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Detail frame = new Detail();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Detail() {
        setTitle("SBI Collect Service - Account Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full-screen
        setUndecorated(false); // Set to true if you want to remove window borders

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        // GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel lblTitle = new JLabel("SBI Collect Service - Account Details");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(new Color(0, 102, 204)); // SBI Blue
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(lblTitle, gbc);

        gbc.gridwidth = 1; // Reset gridwidth

        // Account Number Label and Text Field
        JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAccountNumber.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lblAccountNumber, gbc);

        txtAccountNumber = new JTextField();
        txtAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(txtAccountNumber, gbc);

        // First Name Label and Text Field
        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFirstName.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(lblFirstName, gbc);

        txtFirstName = new JTextField();
        txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(txtFirstName, gbc);

        // Last Name Label and Text Field
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblLastName.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(lblLastName, gbc);

        txtLastName = new JTextField();
        txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(txtLastName, gbc);

        // Mobile Number Label and Text Field
        JLabel lblMobileNo = new JLabel("Mobile Number:");
        lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMobileNo.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(lblMobileNo, gbc);

        txtMobileNo = new JTextField();
        txtMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPane.add(txtMobileNo, gbc);

        // Address Label and Text Field
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAddress.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPane.add(lblAddress, gbc);

        txtAddress = new JTextField();
        txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 5;
        contentPane.add(txtAddress, gbc);

        // Amount Label and Text Field
        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAmount.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPane.add(lblAmount, gbc);

        txtAmount = new JTextField();
        txtAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 6;
        contentPane.add(txtAmount, gbc);

        // Check Details Button
        btnCheckDetails = new JButton("Check Details");
        btnCheckDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCheckDetails.setBackground(new Color(0, 102, 204));
        btnCheckDetails.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        contentPane.add(btnCheckDetails, gbc);

        // Update Details Button
        btnUpdateDetails = new JButton("Update Details");
        btnUpdateDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnUpdateDetails.setBackground(new Color(0, 102, 204));
        btnUpdateDetails.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 7;
        contentPane.add(btnUpdateDetails, gbc);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBack.setBackground(new Color(0, 102, 204));
        btnBack.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        contentPane.add(btnBack, gbc);

        // Result Label
        lblResult = new JLabel("");
        lblResult.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblResult.setForeground(new Color(0, 102, 204));
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        contentPane.add(lblResult, gbc);

        // Add Hover Effects to Buttons
        addHoverEffect(btnCheckDetails);
        addHoverEffect(btnUpdateDetails);
        addHoverEffect(btnBack);

        // Action Listener for Check Details Button
        btnCheckDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkDetails();
            }
        });

        // Action Listener for Update Details Button
        btnUpdateDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDetails();
            }
        });

        // Action Listener for Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main m = new Main();
                m.setVisible(true);
                setVisible(false);
            }
        });
    }

    /**
     * Adds a hover effect to the given button.
     * @param button The JButton to add the hover effect to.
     */
    private void addHoverEffect(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 51, 153)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204)); // Reset to original color
            }
        });
    }

    /**
     * Method to check and display account details.
     */
    private void checkDetails() {
        String accountNumber = txtAccountNumber.getText().trim();
        if (accountNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an account number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");
            String query = "SELECT First_name, Last_name, mobile_no, Address, amount FROM customer WHERE Account_number = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(accountNumber));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtFirstName.setText(rs.getString("First_name"));
                txtLastName.setText(rs.getString("Last_name"));
                txtMobileNo.setText(rs.getString("mobile_no"));
                txtAddress.setText(rs.getString("Address"));
                txtAmount.setText(rs.getString("amount"));
                lblResult.setText("Details fetched successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Account not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                lblResult.setText("");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Invalid account number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e1) {
            lblResult.setText("Error fetching details.");
            e1.printStackTrace();
        }
    }

    /**
     * Method to update account details.
     */
    private void updateDetails() {
        String accountNumber = txtAccountNumber.getText().trim();
        if (accountNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an account number to update.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String mobileNo = txtMobileNo.getText().trim();
        String address = txtAddress.getText().trim();
        String amountStr = txtAmount.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || mobileNo.isEmpty() || address.isEmpty() || amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields before updating.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int amount = Integer.parseInt(amountStr); // Validate amount

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");
            String query = "UPDATE customer SET First_name = ?, Last_name = ?, mobile_no = ?, Address = ?, amount = ? WHERE Account_number = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, mobileNo);
            ps.setString(4, address);
            ps.setInt(5, amount);
            ps.setInt(6, Integer.parseInt(accountNumber));

            int updatedRows = ps.executeUpdate();
            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(this, "Data Updated Successfully.", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                lblResult.setText("Details updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No records updated. Please check the account number.", "Update Failed", JOptionPane.ERROR_MESSAGE);
                lblResult.setText("");
            }

            ps.close();
            conn.close();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Invalid amount format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e1) {
            lblResult.setText("Error updating details.");
            e1.printStackTrace();
        }
    }
}
