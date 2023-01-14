import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CustomerPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerPage frame = new CustomerPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public CustomerPage() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String detailsText = ""; 
																					// To clarify, we have three variables with
																					// the word "details", so loginDetails is a
			Scanner loginDetails = new Scanner(new FileReader("login.txt"));		// Scanner with login.txt, detailsText is a 
																					// String with same text in (login.txt) but			
			while(loginDetails.hasNext()) {											// without "," so we can easily read it line
				String [] line = loginDetails.nextLine().split(", ");           	// by line, and details is a Scanner that we 
				for(String word : line)                                         	// use to initiate our customer.
					detailsText += word + "\n";
			}
			
			loginDetails.close();
			
			Scanner details = new Scanner(detailsText);
			
			// Create new customer account 
			
			details.next();
			String name = "";
			Scanner detailsLine = new Scanner(details.nextLine());
			while(detailsLine.hasNext()) {
				name += detailsLine.next() + " ";
			}
			details.next();
			details.next();
			details.next();
			String dob = details.next();
			details.next();
			details.next();
			String email = details.next();
			details.next();
			details.next();
			String number = details.next();
			details.next();
			int salary = details.nextInt();
			details.next();
			details.next();
			String accountType = details.next();
			details.next();
			String username = details.next();
			details.next();
			String password = details.next();
			details.next();
			int balance = details.nextInt();
			details.next();
			int ID = details.nextInt();
			details.close();
			
			List <Customer> customerList = new ArrayList<>();
			Customer customer = new Customer(name, dob , email, number, salary, accountType, username, password, balance);
			customer.setID(ID);
			customerList.add(customer);
			
			File deletedFile = new File(".\\login.txt");
			deletedFile.delete();
		
		
		
		JLabel lblNewLabel = new JLabel("Welcome " + customer.getName()+"!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 284, 28);
		contentPane.add(lblNewLabel);
		
		JLabel servicesList = new JLabel("Select the Service:");
		servicesList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		servicesList.setBounds(10, 48, 168, 13);
		contentPane.add(servicesList);
		
		JButton viewDetails = new JButton("View Account Details");
		viewDetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
		viewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String customerDetails = String.format("%s",customer);			// In these lines, what I am doing is that I am 
				Scanner textReader = new Scanner(customerDetails);				// taking the toString from the customer by 
				String detailsText = "";										// String.format("s", customer), and I read it by a 
				while(textReader.hasNext()) {									// scanner. Then, I go through a while loop to
					String [] next = textReader.nextLine().split(", ");			// print the text line by line without ",".
					for(String word : next)										// At the end, I will print the text in JOptionPane
					detailsText += word + "\n";
				}
				textReader.close();
				
				JOptionPane.showMessageDialog(null, detailsText);
			}
		});
		viewDetails.setBounds(57, 71, 189, 21);
		contentPane.add(viewDetails);
		
		JButton logout = new JButton("logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage lp1 = new LoginPage();
				lp1.setVisible(true);
				dispose();
			}
		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logout.setBounds(180, 480, 104, 21);
		contentPane.add(logout);
		
		JButton transferUtility = new JButton("Transfer to a Utility");
		transferUtility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferUtility tu1 = new TransferUtility(customer);
				tu1.setVisible(true);
			}
		});
		transferUtility.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transferUtility.setBounds(57, 104, 189, 21);
		contentPane.add(transferUtility);
		
		JButton payUni = new JButton("Pay the University Fees");
		payUni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uniFees = JOptionPane.showInputDialog(null, "Amount to be Transferred to the University:");
				
				if (uniFees == null || uniFees.equals(""))
					JOptionPane.showMessageDialog(null, "Please Enter a Correct Value, and Try Again!");
				else
					try {
						customer.transferAmount(Integer.parseInt(uniFees), 6788, "University");
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
			}
		});
		payUni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		payUni.setBounds(57, 135, 189, 21);
		contentPane.add(payUni);
		
		JButton transferCustomer = new JButton("Transfer to Other Customer");
		transferCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferCustomer tc1 = new TransferCustomer(customer);
				tc1.setVisible(true);
			}
		});
		transferCustomer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transferCustomer.setBounds(57, 166, 189, 21);
		contentPane.add(transferCustomer);
		
		JButton notifications = new JButton("notifications");
		notifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File NotificationsFile = new File(".\\customers\\customer"+customer.getID()+"\\unreadNotifications.txt");
				if (!NotificationsFile.exists()) {
					JOptionPane.showMessageDialog(null, "No Notifications Yet! Try Again Later!");
				} else {
					CustomerNotifications cn1;
					try {
						cn1 = new CustomerNotifications(customer);
						cn1.setVisible(true);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		notifications.setFont(new Font("Tahoma", Font.PLAIN, 12));
		notifications.setBounds(10, 480, 111, 21);
		contentPane.add(notifications);
		
		JButton requestLoan = new JButton("Request a Loan");
		requestLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loanValue = JOptionPane.showInputDialog(null, "Enter the Amount of Loan:");
				if (loanValue == null || loanValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter a Correct Value, and Try Again!");
				} else {
					File loanRequestFile = new File(".\\requests\\loanRequest" +customer.getID()+ ".txt");
					JOptionPane.showMessageDialog(null, "The loan request has been submitted.");
					try {
						PrintWriter requestWriter = new PrintWriter(loanRequestFile);
						requestWriter.println("CustomerID: " + customer.getID() + " requested a loan of amount: " + 
						Integer.parseInt(loanValue));
						requestWriter.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		requestLoan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		requestLoan.setBounds(57, 199, 189, 21);
		contentPane.add(requestLoan);
		
		JButton checkActivities = new JButton("Check Account Activities");
		checkActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAccountActivities caa1 = new checkAccountActivities(customer);
				caa1.setVisible(true);
			}
		});
		checkActivities.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkActivities.setBounds(57, 230, 189, 21);
		contentPane.add(checkActivities);
		
		
		customer.interest(125); // For saving accounts, adds interest!
		
		JButton estatement = new JButton("View E-Statement");
		estatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstatementView ev1 = new EstatementView(customer);
				ev1.setVisible(true);
			}
		});
		estatement.setFont(new Font("Tahoma", Font.PLAIN, 12));
		estatement.setBounds(57, 262, 189, 21);
		contentPane.add(estatement);
	}
}
