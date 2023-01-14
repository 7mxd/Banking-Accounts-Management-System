import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.*;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class EstatementView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstatementView frame = new EstatementView();
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
	public EstatementView() {
		setTitle("View E-Statement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel viewFor = new JLabel("View E-statement of an account activity for:");
		viewFor.setFont(new Font("Tahoma", Font.BOLD, 14));
		viewFor.setBounds(10, 10, 373, 13);
		contentPane.add(viewFor);
		
		JButton oneMonth = new JButton("1 Month");
		oneMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		oneMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		oneMonth.setBounds(88, 45, 107, 36);
		contentPane.add(oneMonth);
		
		JButton twoMonth = new JButton("2 Months");
		twoMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		twoMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		twoMonth.setBounds(244, 45, 107, 36);
		contentPane.add(twoMonth);
		
		JButton threeMonth = new JButton("3 Months");
		threeMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		threeMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		threeMonth.setBounds(405, 45, 107, 36);
		contentPane.add(threeMonth);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 589, 410);
		contentPane.add(scrollPane);
		
		JTextArea estatement = new JTextArea();
		estatement.setEditable(false);
		scrollPane.setViewportView(estatement);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(265, 534, 85, 21);
		contentPane.add(close);
	}
	
	public EstatementView(Customer customer) {
		setTitle("View E-Statement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel viewFor = new JLabel("View E-statement of an account activity for:");
		viewFor.setFont(new Font("Tahoma", Font.BOLD, 14));
		viewFor.setBounds(10, 10, 373, 13);
		contentPane.add(viewFor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 589, 410);
		contentPane.add(scrollPane);
		
		JTextArea estatement = new JTextArea();
		estatement.setEditable(false);
		scrollPane.setViewportView(estatement);
		
		LocalDate currentDate = java.time.LocalDate.now();
		LocalDate oneMonthDate = currentDate.minusMonths(1);
		LocalDate twoMonthDate = currentDate.minusMonths(2);
		LocalDate threeMonthDate = currentDate.minusMonths(3);
		
		JButton oneMonth = new JButton("1 Month");
		oneMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File accountActivitiesFile = new File(".\\customers\\customer"+customer.getID()+"\\accountActivities.txt");
				String accountActivitiesText = "";
				
				if (accountActivitiesFile.exists()) {
					Scanner accountActivitiesReader;
					try {
						accountActivitiesReader = new Scanner(accountActivitiesFile);
						
							
						for(LocalDate date = oneMonthDate; date.isBefore(currentDate) || date.isEqual(currentDate); 
								date = date.plusDays(1)) {
							while(accountActivitiesReader.hasNext()) {
									
								LocalDate dateText = LocalDate.parse(accountActivitiesReader.nextLine());
								String activityText = accountActivitiesReader.nextLine();
								
								if (dateText.equals(date)) {
									accountActivitiesText += dateText + "\n";
									accountActivitiesText += activityText + "\n";
								}
							}
							accountActivitiesReader = new Scanner(accountActivitiesFile);
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No Account Activities Found!");
				}
				
				
				
				estatement.setText("E-Statement\nGenerated on: " + currentDate + "\n"
						+ "Account Activities From: " + oneMonthDate + " to: " + currentDate + "\n\n\n"
								+ "Customer ID: " + customer.getID() + "\n"
										+ "Email Adress: " + customer.getEmail() + "\n"
										+ "Phone Number: " + customer.getPhoneNumber() + "\n"
										+ "Account Type: " + customer.getAccountType() + "\n"
										+ "Balance: " + customer.getBalance() + "\n\n\n" +
										"Following are the Account Activities for the Last Month:"
										+ "\n\n" + accountActivitiesText);
			}
		});
		oneMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		oneMonth.setBounds(88, 45, 107, 36);
		contentPane.add(oneMonth);
		
		JButton twoMonth = new JButton("2 Months");
		twoMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File accountActivitiesFile = new File(".\\customers\\customer"+customer.getID()+"\\accountActivities.txt");
				String accountActivitiesText = "";
				
				if (accountActivitiesFile.exists()) {
					Scanner accountActivitiesReader;
					try {
						accountActivitiesReader = new Scanner(accountActivitiesFile);
						
							
						for(LocalDate date = twoMonthDate; date.isBefore(currentDate) || date.isEqual(currentDate); 
								date = date.plusDays(1)) {
							while(accountActivitiesReader.hasNext()) {
									
								LocalDate dateText = LocalDate.parse(accountActivitiesReader.nextLine());
								String activityText = accountActivitiesReader.nextLine();
								
								if (dateText.equals(date)) {
									accountActivitiesText += dateText + "\n";
									accountActivitiesText += activityText + "\n";
								}
							}
							accountActivitiesReader = new Scanner(accountActivitiesFile);
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No Account Activities Found!");
				}
				
				
				
				estatement.setText("E-Statement\nGenerated on: " + currentDate + "\n"
						+ "Account Activities From: " + twoMonthDate + " to: " + currentDate + "\n\n\n"
								+ "Customer ID: " + customer.getID() + "\n"
										+ "Email Adress: " + customer.getEmail() + "\n"
										+ "Phone Number: " + customer.getPhoneNumber() + "\n"
										+ "Account Type: " + customer.getAccountType() + "\n"
										+ "Balance: " + customer.getBalance() + "\n\n\n" +
										"Following are the Account Activities for the Last 2 Months:"
										+ "\n\n" + accountActivitiesText);
			}
		});
		twoMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		twoMonth.setBounds(244, 45, 107, 36);
		contentPane.add(twoMonth);
		
		JButton threeMonth = new JButton("3 Months");
		threeMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File accountActivitiesFile = new File(".\\customers\\customer"+customer.getID()+"\\accountActivities.txt");
				String accountActivitiesText = "";
				
				if (accountActivitiesFile.exists()) {
					Scanner accountActivitiesReader;
					try {
						accountActivitiesReader = new Scanner(accountActivitiesFile);
						
							
						for(LocalDate date = threeMonthDate; date.isBefore(currentDate) || date.isEqual(currentDate); 
								date = date.plusDays(1)) {
							while(accountActivitiesReader.hasNext()) {
									
								LocalDate dateText = LocalDate.parse(accountActivitiesReader.nextLine());
								String activityText = accountActivitiesReader.nextLine();
								
								if (dateText.equals(date)) {
									accountActivitiesText += dateText + "\n";
									accountActivitiesText += activityText + "\n";
								}
							}
							accountActivitiesReader = new Scanner(accountActivitiesFile);
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No Account Activities Found!");
				}
				
				
				
				estatement.setText("E-Statement\nGenerated on: " + currentDate + "\n"
						+ "Account Activities From: " + threeMonthDate + " to: " + currentDate + "\n\n\n"
								+ "Customer ID: " + customer.getID() + "\n"
										+ "Email Adress: " + customer.getEmail() + "\n"
										+ "Phone Number: " + customer.getPhoneNumber() + "\n"
										+ "Account Type: " + customer.getAccountType() + "\n"
										+ "Balance: " + customer.getBalance() + "\n\n\n" +
										"Following are the Account Activities for the Last 3 Months:"
										+ "\n\n" + accountActivitiesText);
			}
		});
		threeMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		threeMonth.setBounds(405, 45, 107, 36);
		contentPane.add(threeMonth);
		
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(265, 534, 85, 21);
		contentPane.add(close);
	}
}
