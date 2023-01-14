import java.awt.EventQueue;
import java.io.*;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JPasswordField;

public class RegistrationPage extends JFrame {

	private JPanel contentPane;
	private JTextField nameText;
	private JTextField emailText;
	private JTextField numVal1;
	private JTextField numVal2;
	private JTextField numVal3;
	private JTextField salaryValue;
	private JTextField usernameValue;
	private JPasswordField passwordValue;
	private JTextField balanceValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationPage frame = new RegistrationPage();
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
	public RegistrationPage() {
		setTitle("Registration Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Account Registration");
		title.setBounds(118, 10, 196, 22);
		title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(title);
		
		JLabel name = new JLabel("Name:");
		name.setFont(new Font("Tahoma", Font.BOLD, 16));
		name.setBounds(10, 71, 80, 13);
		contentPane.add(name);
		
		nameText = new JTextField();
		nameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameText.setColumns(10);
		nameText.setBounds(166, 65, 250, 27);
		contentPane.add(nameText);
		
		JLabel accountType = new JLabel("Account Type:");
		accountType.setFont(new Font("Tahoma", Font.BOLD, 16));
		accountType.setBounds(10, 353, 146, 20);
		contentPane.add(accountType);
		
		JComboBox accountTypeValue = new JComboBox();
		accountTypeValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		accountTypeValue.setModel(new DefaultComboBoxModel(new String[] {"Current Account", "Saving Account"}));
		accountTypeValue.setSelectedIndex(0);
		accountTypeValue.setBounds(166, 349, 250, 27);
		contentPane.add(accountTypeValue);
		
		JLabel email = new JLabel("Email Address:");
		email.setFont(new Font("Tahoma", Font.BOLD, 16));
		email.setBounds(10, 404, 146, 13);
		contentPane.add(email);
		
		emailText = new JTextField();
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		emailText.setColumns(10);
		emailText.setBounds(166, 397, 250, 27);
		contentPane.add(emailText);
		
		JLabel phone = new JLabel("Phone Number:");
		phone.setFont(new Font("Tahoma", Font.BOLD, 16));
		phone.setBounds(10, 453, 146, 20);
		contentPane.add(phone);
		
		numVal1 = new JTextField();
		numVal1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numVal1.setColumns(10);
		numVal1.setBounds(181, 449, 38, 27);
		contentPane.add(numVal1);
		
		JLabel num1 = new JLabel("+");
		num1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		num1.setBounds(166, 456, 45, 13);
		contentPane.add(num1);
		
		JLabel num2 = new JLabel("-");
		num2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		num2.setBounds(225, 456, 15, 13);
		contentPane.add(num2);
		
		numVal2 = new JTextField();
		numVal2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numVal2.setColumns(10);
		numVal2.setBounds(235, 449, 24, 27);
		contentPane.add(numVal2);
		
		numVal3 = new JTextField();
		numVal3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numVal3.setColumns(10);
		numVal3.setBounds(264, 449, 152, 27);
		contentPane.add(numVal3);
		
		salaryValue = new JTextField();
		salaryValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		salaryValue.setColumns(10);
		salaryValue.setBounds(166, 250, 250, 27);
		contentPane.add(salaryValue);
		
		JLabel salary = new JLabel("Salary:");
		salary.setFont(new Font("Tahoma", Font.BOLD, 16));
		salary.setBounds(10, 255, 80, 13);
		contentPane.add(salary);
		
		JLabel dob = new JLabel("Date of Birth:");
		dob.setFont(new Font("Tahoma", Font.BOLD, 16));
		dob.setBounds(10, 208, 166, 13);
		contentPane.add(dob);
		
		JDateChooser dobValue = new JDateChooser();
		dobValue.setBounds(166, 201, 250, 27);
		contentPane.add(dobValue);
		
		usernameValue = new JTextField();
		usernameValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameValue.setColumns(10);
		usernameValue.setBounds(166, 103, 250, 27);
		contentPane.add(usernameValue);
		
		JLabel user = new JLabel("Username:");
		user.setFont(new Font("Tahoma", Font.BOLD, 16));
		user.setBounds(10, 108, 146, 13);
		contentPane.add(user);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Tahoma", Font.BOLD, 16));
		password.setBounds(10, 159, 80, 13);
		contentPane.add(password);
		
		passwordValue = new JPasswordField();
		passwordValue.setBounds(166, 154, 248, 27);
		contentPane.add(passwordValue);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			// if any of the textfields is empty, show an error!
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordValue.getPassword());
				int balance = Integer.parseInt(balanceValue.getText());
				
				
				if (nameText.getText().isEmpty() || dobValue.getDate() == null|| salaryValue.getText().isEmpty() || 
						emailText.getText().isEmpty() || numVal1.getText().isEmpty() || 	
						numVal2.getText().isEmpty() || numVal3.getText().isEmpty() || usernameValue.getText().isEmpty() || 
						password.isEmpty() || balanceValue.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please make sure you fill up all fields, and try again!");
				}
				// if number is less than 7 numbers, show an error! 
				// Example: +971 56 (9135351) <- THAT NUMBER BETWEEN BRACKETS SHOWN BE OF SEVEN NUMBERS
				else if (numVal3.getText().length() != 7) {
					JOptionPane.showMessageDialog(null, "Please make sure you entered a correct number, and try again!");
				}
				
				else if (!emailText.getText().contains("@") || !emailText.getText().contains(".")) {
					JOptionPane.showMessageDialog(null, "Please make sure you entered a correct email, and try again!");
				}
				
				else {
					// Declaring the PrintWriter (Will be initialized later!)
					PrintWriter writer = null;
					
					boolean flag = false;
					
					try {
						
						Scanner userpass = new Scanner(new FileReader("userpass.txt"));
						while (userpass.hasNext()) {
							int ID = userpass.nextInt();
							String user = userpass.next();
							String pass = userpass.next();
							if ((usernameValue.getText().equalsIgnoreCase(user))) {
								flag = true;
							}
						}
						
						if (flag == false ) {
						
							String PhoneNumber = numVal1.getText()+	numVal2.getText() + numVal3.getText(); // Combines the numbers from 3 text fields!
							String accountType = "";
						
							if(accountTypeValue.getSelectedIndex() == 0) { // 
							 accountType = "current";
							}
							else {
							 accountType = "savings";
							}
						 
							int salary = Integer.parseInt(salaryValue.getText());
						
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String startDateString = dateFormat.format(dobValue.getDate());
						
							List <Customer> requestedCustomerList = new ArrayList<>();
							Customer c1 = new Customer(nameText.getText(), startDateString , emailText.getText(), PhoneNumber, salary, accountType, usernameValue.getText(), password, balance);
							requestedCustomerList.add(c1);
						
							JOptionPane.showMessageDialog(null, "Your registration request have been sent");
							dispose();
						
							String fileName = "accountOpenRequest"+c1.getID()+".txt";
						
							FileWriter file = new FileWriter(".\\requests\\"+fileName, true);
							writer = new PrintWriter(file);
							writer.println(c1);
							writer.close();
						
							//writer = new PrintWriter("requests_userpass.txt");
							//writer.println(c1.displayUserPass());
							//writer.close();
						}else {
							JOptionPane.showMessageDialog(null, "Username is used. Please pick another username and try again!");
						}
						
						
						
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		register.setFont(new Font("Tahoma", Font.PLAIN, 12));
		register.setBounds(118, 504, 85, 31);
		contentPane.add(register);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancel.setBounds(213, 504, 85, 31);
		contentPane.add(cancel);
		
		balanceValue = new JTextField();
		balanceValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		balanceValue.setColumns(10);
		balanceValue.setBounds(166, 300, 250, 27);
		contentPane.add(balanceValue);
		
		JLabel balance = new JLabel("Balance:");
		balance.setFont(new Font("Tahoma", Font.BOLD, 16));
		balance.setBounds(10, 306, 80, 13);
		contentPane.add(balance);
		

	}
}
