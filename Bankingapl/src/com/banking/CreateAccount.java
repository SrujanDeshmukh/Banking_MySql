package com.banking;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class CreateAccount extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateAccount frame = new CreateAccount();
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
    public CreateAccount() {
        setTitle("SBI Collect Service - Create Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Full screen window
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Create the content pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        // GridBagConstraints for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Title label setup
        JLabel lblTitle = new JLabel("SBI Collect Service - Create Account");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(new Color(0, 102, 204)); // SBI style blue
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 30, 10);
        contentPane.add(lblTitle, gbc);

        // Font and Color Setup for Labels
        Font labelFont = new Font("Tahoma", Font.BOLD, 14);
        Color labelColor = new Color(0, 102, 204); // SBI blue

        // Account Number Label and Text Field
        JLabel lblAccountNumber = new JLabel("Account Number");
        lblAccountNumber.setFont(labelFont);
        lblAccountNumber.setForeground(labelColor);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lblAccountNumber, gbc);

        textField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(textField, gbc);

        // First Name Label and Text Field
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setFont(labelFont);
        lblFirstName.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(lblFirstName, gbc);

        textField_1 = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(textField_1, gbc);

        // Last Name Label and Text Field
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setFont(labelFont);
        lblLastName.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(lblLastName, gbc);

        textField_2 = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(textField_2, gbc);

        // Contact Number Label and Text Field
        JLabel lblContactNumber = new JLabel("Contact Number");
        lblContactNumber.setFont(labelFont);
        lblContactNumber.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(lblContactNumber, gbc);

        textField_3 = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPane.add(textField_3, gbc);

        // Address Label and Text Field
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(labelFont);
        lblAddress.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPane.add(lblAddress, gbc);

        textField_4 = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        contentPane.add(textField_4, gbc);

        // Amount Label and Text Field
        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setFont(labelFont);
        lblAmount.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPane.add(lblAmount, gbc);

        textField_5 = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 6;
        contentPane.add(textField_5, gbc);

        // Create Account Button with hover effect
        JButton btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCreateAccount.setBackground(new Color(0, 102, 204));
        btnCreateAccount.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 7;
        contentPane.add(btnCreateAccount, gbc);

        // Add hover effect
        btnCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCreateAccount.setBackground(new Color(0, 51, 153)); // Darker on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCreateAccount.setBackground(new Color(0, 102, 204)); // Reset color
            }
        });

        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");
                    String query = "insert into customer values(?,?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setInt(1, Integer.parseInt(textField.getText()));
                    ps.setString(2, textField_1.getText());
                    ps.setString(3, textField_2.getText());
                    ps.setInt(4, Integer.parseInt(textField_3.getText()));
                    ps.setString(5, textField_4.getText());
                    ps.setInt(6, Integer.parseInt(textField_5.getText()));

                    int i = ps.executeUpdate();
                    JOptionPane.showMessageDialog(btnCreateAccount, "Account Created Successfully");

                    ps.close();
                    conn.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Back Button with hover effect
        
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setBackground(new Color(0, 102, 204));
        btnBack.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 7;
        contentPane.add(btnBack, gbc);

        // Add hover effect to Back button
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBack.setBackground(new Color(0, 51, 153)); // Darker on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBack.setBackground(new Color(0, 102, 204)); // Reset color
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main m = new Main();
                m.setVisible(true);
                setVisible(false);
            }
        });
    }
}
