package com.banking;
	import java.awt.*;
	import javax.swing.*;
	import javax.swing.border.EmptyBorder;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

	public class Loan extends JFrame {

	    private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private JTextField textFieldAc;
//	    private JTextField textFieldSt;
	    private JTextField textFieldAM;
	    private JTextField textFieldLoanType;

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
	    public Loan() {
	        setTitle("SBI Collect Service ");
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

	        textFieldAc = new JTextField(15);
	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        contentPane.add(textFieldAc, gbc);

	        // First Name Label and Text Field
	        JLabel lblFirstName = new JLabel("Status");
	        lblFirstName.setFont(labelFont);
	        lblFirstName.setForeground(labelColor);
	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        contentPane.add(lblFirstName, gbc);
	        
	        JLabel Status = new JLabel("Pending");
	        Status.setFont(labelFont);
	        Status.setForeground(labelColor);
	        gbc.gridx = 1;
	        gbc.gridy = 2;
	        contentPane.add(Status, gbc);

	        
	     // Amount Label and Text Field
	        JLabel lblAmount = new JLabel("Amount");
	        lblAmount.setFont(labelFont);
	        lblAmount.setForeground(labelColor);
	        gbc.gridx = 0;
	        gbc.gridy = 6;
	        contentPane.add(lblAmount, gbc);

	        textFieldAM = new JTextField(15);
	        gbc.gridx = 1;
	        gbc.gridy = 6;
	        contentPane.add(textFieldAM, gbc);

	        

	        // Address Label and Text Field
	        JLabel lblAddress = new JLabel("LoanType");
	        lblAddress.setFont(labelFont);
	        lblAddress.setForeground(labelColor);
	        gbc.gridx = 0;
	        gbc.gridy = 5;
	        contentPane.add(lblAddress, gbc);

	        textFieldLoanType = new JTextField(15);
	        gbc.gridx = 1;
	        gbc.gridy = 5;
	        contentPane.add(textFieldLoanType, gbc);

	        

	        // Create Account Button with hover effect
	        JButton btnCreateAccount = new JButton("Submit");
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
	                    String query = "insert into loans values(?,?,?,?)";
	                    PreparedStatement ps = conn.prepareStatement(query);
	                    ps.setInt(1, Integer.parseInt(textFieldAc.getText()));
	                    ps.setString(2, "Pending");
	                    ps.setInt(3, Integer.parseInt(textFieldAM.getText()));
	                    ps.setString(4, textFieldLoanType.getText());

	                    int i = ps.executeUpdate();
	                    JOptionPane.showMessageDialog(btnCreateAccount, "Loan Submit Successfully");

	                    btnCreateAccount.setVisible(false);
	                    ps.close();
	                    conn.close();
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }
	            }
	        });
	        
	        
	        JButton Refresh = new JButton("Refresh");
	        Refresh.setFont(new Font("Tahoma", Font.BOLD, 14));
	        Refresh.setBackground(new Color(0, 102, 204));
	        Refresh.setForeground(Color.WHITE);
	        gbc.gridx = 1;
	        gbc.gridy = 7;
	        contentPane.add(Refresh, gbc);

	        // Add hover effect
	        Refresh.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	            	Refresh.setBackground(new Color(0, 51, 153)); // Darker on hover
	            }

	            public void mouseExited(java.awt.event.MouseEvent evt) {
	            	Refresh.setBackground(new Color(0, 102, 204)); // Reset color
	            }
	        });

	        Refresh.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    Class.forName("com.mysql.cj.jdbc.Driver");
	                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankData", "root", "Krishna@1234");
	                    
	                    String query = "SELECT Status FROM Loans WHERE Account_number = ?";
	                    PreparedStatement ps = conn.prepareStatement(query);
	                    ps.setInt(1, Integer.parseInt(textFieldAc.getText()));

	                    ResultSet rs = ps.executeQuery();
	                    if (rs.next()) {
	                        String loanStatus = rs.getString("Status");
	                        Status.setText(loanStatus);  // Update loan status in the field
	                    } else {
	                        JOptionPane.showMessageDialog(null, "No loan found for this account number.");
	                    }

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
	            	Userview m = new Userview();
	                m.setVisible(true);
	                setVisible(false);
	            }
	        });
	    }
	}

