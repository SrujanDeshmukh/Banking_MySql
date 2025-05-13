package com.banking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField t1;
    private JPasswordField t2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
         
            	try {
                    login frame = new login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public login() {
        // Set window to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SBI Collect - Login Form");

        // Use GridBagLayout for centered content
        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        GridBagConstraints gbc = new GridBagConstraints();

        // Add title label
        JLabel lblTitle = new JLabel("SBI Collect - Login");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(new Color(0, 102, 204)); // SBI Blue color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 50, 0); // Padding
        contentPane.add(lblTitle, gbc);

        // Add Login ID label
        JLabel lblLoginId = new JLabel("Login ID:");
        lblLoginId.setFont(new Font("Arial", Font.BOLD, 16));
        lblLoginId.setForeground(new Color(0, 102, 204));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        contentPane.add(lblLoginId, gbc);

        // Add Login ID text field
        t1 = new JTextField();
        t1.setPreferredSize(new Dimension(300, 40));
        gbc.gridy = 2;
        contentPane.add(t1, gbc);

        // Add PIN code label
        JLabel lblPinCode = new JLabel("PIN CODE:");
        lblPinCode.setFont(new Font("Arial", Font.BOLD, 16));
        lblPinCode.setForeground(new Color(0, 102, 204));
        gbc.gridy = 3;
        contentPane.add(lblPinCode, gbc);

        // Add PIN code field
        t2 = new JPasswordField();
        t2.setPreferredSize(new Dimension(300, 40));
        gbc.gridy = 4;
        contentPane.add(t2, gbc);

        // Add Login button
        JButton btnLogin = new JButton("LOGIN");
        JButton btnBack = new JButton("Back ?");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setBackground(new Color(0, 102, 204)); // SBI Blue
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(150, 40));
        gbc.gridy = 5;
        gbc.insets = new Insets(20, 0, 0, 0);
        contentPane.add(btnLogin, gbc);

        btnBack.addActionListener(e -> {
        	UserType userType = new UserType();  // Open home page
        	userType.setVisible(true);
            setVisible(false);  // Hide login page
        });
        
        // Button hover effect
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(51, 153, 255)); // Lighten on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(0, 102, 204)); // Back to default
            }
        });

        // Login button action
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loginId = t1.getText();
                String pinCode = new String(t2.getPassword());
                if (loginId.equals("Admin") && pinCode.equals("1234")) {
                    Main m = new Main();
                    m.setVisible(true);
                    setVisible(false);
                }
                else if(loginId.equals("User1") && pinCode.equals("User1")
                		||loginId.equals("User2") && pinCode.equals("User2")
                		)
                {
                	Userview u = new Userview();
                    u.setVisible(true);
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid login credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
