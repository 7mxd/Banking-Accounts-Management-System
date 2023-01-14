import java.awt.EventQueue;
import java.util.*;
import java.io.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RequestsPage extends JFrame {

	private JPanel contentPane;
	private JTextArea requestDetails;
	private JLabel selectLabel;
	private JButton selectButton;
	private JButton approveButton;
	private JButton rejectButton;
	private JButton close;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestsPage frame = new RequestsPage();
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
	public RequestsPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		List <Customer> CustomerList = new ArrayList<>();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		requestDetails = new JTextArea();
		requestDetails.setEditable(false);
		requestDetails.setBounds(10, 84, 636, 238);
		
		File files = new File(".\\requests");
		String [] fileName = files.list(); // Makes an array of the file names 
		
		JComboBox requestsList = new JComboBox();
		requestsList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		requestsList.setBounds(248, 33, 260, 22);
		requestsList.setModel(new DefaultComboBoxModel(fileName)); // Take names of files from the array above
		contentPane.add(requestsList);
		requestsList.setSelectedIndex(-1);
		
		selectLabel = new JLabel("Select a customer's request:");
		selectLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		selectLabel.setBounds(10, 36, 246, 14);
		contentPane.add(selectLabel);
		
		requestDetails.setText("");
		contentPane.add(requestDetails);
		requestDetails.setColumns(10);
		
		selectButton = new JButton("Select");
		selectButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestDetails.setText("");
				if (!(requestsList.getSelectedItem() == null)) {
			try {
				Scanner reader = new Scanner(new FileReader(".\\requests\\" + requestsList.getSelectedItem()));
				while(reader.hasNext()) {
					String [] next = reader.nextLine().split(", ");
					for(String word : next)
					requestDetails.append(word + "\n");
				}
				reader.close();
				
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
		}
			}
		});
		selectButton.setBounds(535, 33, 89, 23);
		contentPane.add(selectButton);
		
		approveButton = new JButton("Approve");
		approveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (( (String)(requestsList.getSelectedItem()) ).substring(0,11).equals("accountOpen")) {
					PrintWriter writer = null;
					Scanner details = new Scanner(requestDetails.getText());
					
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
					
					
					Customer c2 = new Customer(name, dob , email, number, salary, accountType, username, password, balance);
					c2.setID(ID);
					CustomerList.add(c2);
					
					
					try {
						
						String folderName = "customer"+c2.getID();
						
						File customerFolder = new File(".\\customers\\"+folderName);
						customerFolder.mkdir();
						
						FileWriter file = new FileWriter(".\\customers\\"+folderName+"\\details.txt", true);
						writer = new PrintWriter(file);
						writer.println(c2);
						writer.close();
						
						FileWriter file2 = new FileWriter(".\\userpass.txt", true);
						writer = new PrintWriter(file2);
						writer.println(c2.displayUserPass());
						writer.close();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					// Delete Request File
					
					String fileName = ".\\requests\\" + requestsList.getSelectedItem();
					File deletedFile = new File(fileName);
					if (deletedFile.delete()) {
						JOptionPane.showMessageDialog(null, "Customer" + c2.getID() + " approved successfully!");
						 requestDetails.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "File failed to delete!");
					}
					dispose();
				} 
				
				else if(( (String)(requestsList.getSelectedItem()) ).substring(0,11).equals("loanRequest")) {
					


					
					String IDNumber = ((String)(requestsList.getSelectedItem())).replaceAll("[^0-9]", ""); // Getting ID number from text file name
					PrintWriter writer2 = null;
					FileWriter loanFile;
					
					File fileName2 = new File(".\\customers\\customer"+IDNumber+"\\details.txt");
					Scanner loanScanner;
					try {
						loanScanner = new Scanner(fileName2);
						String line = loanScanner.nextLine();
						
						String [] data = line.split("[,]", 0);
						
						String name = (data[0].replace("Name: ", ""));
						String dob = (data[1].replace("Date of Birth: ", "")); 
						String email = (data[2].replace("Email Address: ", ""));
						String number =( data[3].replace("Phone Number: ", ""));
						int salary = Integer.parseInt((data[4]).replaceAll("[^0-9]", ""));
						String accountType = (data[5].replace("Account Type: ", ""));
						String username = (data[6].replace("Username: ", ""));
						String password = (data[7].replace("Password: ", ""));
						int balance = Integer.parseInt((data[8]).replaceAll("[^0-9]", ""));
						int ID = Integer.parseInt((data[9]).replaceAll("[^0-9]", ""));
						
						Customer c2 = new Customer(name, dob , email, number, salary, accountType, username, password, balance);
						c2.setID(ID);
						
						
						//printing loan massege into the loan text file
						
						try {
							
							loanFile = new FileWriter(".\\customers\\customer"+IDNumber+"\\loan.txt", true);
							writer2 = new PrintWriter(loanFile);
							writer2.println(requestDetails.getText());
							String loanAmountString = requestDetails.getText().replaceAll("[^0-9]", "");
							loanAmountString = loanAmountString.replace(Integer.toString(c2.getID()), "");
							
							
							int loanAmount = Integer.parseInt(loanAmountString);
							
							c2.setBalance(balance + loanAmount);
							
							writer2.close();
							
							try {
								PrintWriter writer = null;
								
								String folderName = "customer"+c2.getID();
								
								File customerFolder = new File(".\\customers\\"+folderName);
								customerFolder.mkdir();
								
								FileWriter file = new FileWriter(".\\customers\\"+folderName+"\\details.txt", false);
								
								//write details to text file
								writer = new PrintWriter(file);
								writer.println(c2);
								writer.close();
								
								
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
		
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						
					} catch (FileNotFoundException e2) {

						e2.printStackTrace();
					} 
					


					
					
					


					
					
			

					
					// Delete Request File
					
					String fileName = ".\\requests\\" + requestsList.getSelectedItem();
					File deletedFile = new File(fileName);
					if (deletedFile.delete()) {
						JOptionPane.showMessageDialog(null, "Loan request approved!");
						 requestDetails.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "File failed to delete!");
					}
					dispose();
		
					
				}
				
				
				
				
			}
		});
		approveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		approveButton.setBounds(196, 351, 128, 23);
		contentPane.add(approveButton);
		
		rejectButton = new JButton("Decline");
		rejectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String fileName = ".\\requests\\" + requestsList.getSelectedItem();
					File deletedFile = new File(fileName);
					if (deletedFile.delete()) {
						JOptionPane.showMessageDialog(null, "File: " + requestsList.getSelectedItem() + " deleted successfully!");
						 requestDetails.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "File failed to delete!");
					}
					dispose();
			}
		});
		rejectButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rejectButton.setBounds(334, 351, 128, 23);
		contentPane.add(rejectButton);
		
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(291, 390, 69, 23);
		contentPane.add(close);

	}
}
