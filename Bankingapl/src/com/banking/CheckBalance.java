package com.banking;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CheckBalance extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JButton btnNewButton_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CheckBalance frame = new CheckBalance();
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
    public CheckBalance() {
        setTitle("SBI Collect Service - Check Balance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full-screen
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this); // Full-screen mode

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title label
        JLabel lblTitle = new JLabel("Check Balance");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font for full-screen
        lblTitle.setForeground(new Color(0, 102, 204)); // SBI blue
        lblTitle.setBounds(getWidth() / 2 - 150, getHeight() / 6, 300, 50); // Centered horizontally
        contentPane.add(lblTitle);

        JLabel lblNewLabel = new JLabel("Enter Account Number");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text
        lblNewLabel.setForeground(new Color(0, 102, 204)); // SBI blue
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); // Increased font size
        lblNewLabel.setBounds(getWidth() / 2 - 150, getHeight() / 2 - 80, 300, 30); // Centered position
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(getWidth() / 2 - 150, getHeight() / 2 - 40, 300, 30); // Centered position
        contentPane.add(textField);
        textField.setColumns(10);

        // Check Balance button with hover effect
        JButton btnNewButton = new JButton("Check Balance");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20)); // Increased font size
        btnNewButton.setBackground(new Color(0, 102, 204)); // SBI blue
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBounds(getWidth() / 2 - 150, getHeight() / 2 + 20, 300, 40); // Centered position
        contentPane.add(btnNewButton);

        btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButton.setBackground(new Color(0, 51, 153)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButton.setBackground(new Color(0, 102, 204)); // Reset color
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");
                    Statement st = conn.createStatement();
                    String str = "Select amount from customer where Account_number=" + textField.getText().toString() + "";
                    ResultSet rs = st.executeQuery(str);
                    if (rs.next()) {
                        lblNewLabel_1.setText("Balance: " + rs.getString("amount"));
                    } else {
                        lblNewLabel_1.setText("Account not found");
                    }
                    rs.close();
                    st.close();
                    conn.close();
                } catch (Exception e1) {
                    lblNewLabel_1.setText("Error fetching balance");
                    e1.printStackTrace();
                }
            }
        });

        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20)); // Larger font size
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBounds(getWidth() / 2 - 150, getHeight() / 2 + 80, 300, 40); // Centered position
        contentPane.add(lblNewLabel_1);

        // Back button with hover effect
        btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20)); // Larger font size
        btnNewButton_1.setBackground(new Color(0, 102, 204)); // SBI blue
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setBounds(getWidth() / 2 - 150, getHeight() / 2 + 150, 300, 40); // Centered position
        contentPane.add(btnNewButton_1);

        btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButton_1.setBackground(new Color(0, 51, 153)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButton_1.setBackground(new Color(0, 102, 204)); // Reset color
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main m = new Main();
                m.setVisible(true);
                setVisible(false);
            }
        });
    }
}
