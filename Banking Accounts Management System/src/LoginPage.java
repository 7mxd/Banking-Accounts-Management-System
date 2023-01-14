import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

public class LoginPage extends JFrame {
	
	// Variables for the LoginPage class
	private JPanel contentPane;
	private JTextField userText;
	private JLabel pass;
	private JButton register;
	private JLabel registerationInstructions;
	private JPasswordField passText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Banking Accounts Management System");
		title.setBounds(33, 10, 369, 22);
		title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(title);
		
		JLabel user = new JLabel("Username:");
		user.setFont(new Font("Tahoma", Font.BOLD, 16));
		user.setBounds(65, 78, 121, 31);
		contentPane.add(user);
		
		userText = new JTextField();
		userText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userText.setBounds(175, 82, 204, 27);
		contentPane.add(userText);
		userText.setColumns(10);
		
		pass = new JLabel("Password:");
		pass.setFont(new Font("Tahoma", Font.BOLD, 16));
		pass.setBounds(65, 140, 121, 31);
		contentPane.add(pass);
		
		Banker b1 = new Banker("banker", "", "", "");
		
		// Login Button Working ; Will have future modifications!
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter writer = null;
				int userID = 0;
				// I used JPassField instead of JTextField, so I am using passText.getPassword() which returns a 
				// char to declare a string, and check if it is equal to the banker password.
				String password = new String (passText.getPassword());
				if (userText.getText().equals(b1.getUsername()) && password.equals(b1.getPassword())) {
					JOptionPane.showMessageDialog(null, "Logged in successfully!");
					BankerPage BP1 = new BankerPage();
					BP1.setVisible(true);
					dispose();
				}
				
				else {
					boolean flag = false;
					try {
						Scanner userpass = new Scanner(new FileReader("userpass.txt"));
						while (userpass.hasNext()) {
							int ID = userpass.nextInt();
							String user = userpass.next();
							String pass = userpass.next();
							if ((userText.getText().equalsIgnoreCase(user)) && (password.equals(pass))) {
								flag = true;
								userID = ID;
							}
						}
						
						if (flag == true) {
							JOptionPane.showMessageDialog(null, "Logged in successfully!");
							Scanner details = new Scanner(new FileReader(".\\customers\\customer"+userID+"\\details.txt"));
							PrintWriter write = new PrintWriter("login.txt");
							while (details.hasNextLine()) {
								write.println(details.nextLine());
							}
							details.close();
							write.close();
							
							CustomerPage cp1 = new CustomerPage();
							cp1.setVisible(true);
							
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Wrong Login or Password! Try Again!");
							userText.setText(""); 
							passText.setText("");
						}
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		login.setFont(new Font("Tahoma", Font.PLAIN, 12));
		login.setBounds(175, 209, 85, 31);
		contentPane.add(login);
		
		register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationPage RP1 = new RegistrationPage();
				RP1.setVisible(true);
			}
			
		
		});
		register.setFont(new Font("Tahoma", Font.PLAIN, 12));
		register.setBounds(175, 273, 85, 31);
		contentPane.add(register);
		
		registerationInstructions = new JLabel("No account? Register below!");
		registerationInstructions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		registerationInstructions.setBounds(140, 250, 169, 13);
		contentPane.add(registerationInstructions);
		
		passText = new JPasswordField();
		passText.setBounds(175, 144, 204, 27);
		contentPane.add(passText);
	}
}
