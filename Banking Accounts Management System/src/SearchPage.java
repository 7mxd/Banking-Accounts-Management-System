import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class SearchPage extends JFrame {

	private JPanel contentPane;
	private JTextField IDValue;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPage frame = new SearchPage();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public SearchPage() {
		setTitle("Search Tab");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel enterIDLabel = new JLabel("Enter the customer ID:");
		enterIDLabel.setBounds(133, 11, 218, 25);
		enterIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(enterIDLabel);
		
		IDValue = new JTextField();
		IDValue.setBounds(133, 47, 108, 31);
		contentPane.add(IDValue);
		IDValue.setColumns(10);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 464, 222);
		contentPane.add(scrollPane);
		
		JTextArea transactionsArea = new JTextArea();
		transactionsArea.setEditable(false);
		scrollPane.setViewportView(transactionsArea);
		transactionsArea.setVisible(true);
		
		JButton transactionsButton = new JButton("View transaction history");
		transactionsButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		transactionsButton.setBounds(10, 89, 218, 31);
		transactionsButton.setEnabled(false);
		contentPane.add(transactionsButton);
		
		
		JButton accountDetailsButton = new JButton("View account Details");
		accountDetailsButton.setEnabled(false);
		accountDetailsButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		accountDetailsButton.setBounds(238, 89, 236, 31);
		contentPane.add(accountDetailsButton);
		accountDetailsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				transactionsArea.setText("");
				String ID = IDValue.getText();
				File details = new File(".\\customers\\customer"+ID+"\\details.txt");
				Scanner reader;
				
				try {
					reader = new Scanner(details);
					String  text;
					String[] textNew;
					while(reader.hasNext()) {
						 text = reader.nextLine();
						 textNew = text.split(",");
							for(String s: textNew) {
								transactionsArea.append(s+"\n");
								}
					}
			
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				}

			
		});
		
		
		transactionsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				transactionsArea.setText("");
				String ID = IDValue.getText();
				File transactions = new File(".\\customers\\customer"+ID+"\\accountActivities.txt");
				Scanner reader;
				if(transactions.exists()) {
				try {
					reader = new Scanner(transactions);
					while(reader.hasNext()) {
						String date = reader.next();
						String text = reader.nextLine();
						transactionsArea.append(date+" "+text+"\n");
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				}
				else {

					JOptionPane.showMessageDialog(null, "This account has no transactions.",  "No Transactions", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});


		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String ID = IDValue.getText();
				File fileDirectory = new File(".\\customers\\customer"+ID);
				if(fileDirectory.exists()) { 
					transactionsButton.setEnabled(true);
					accountDetailsButton.setEnabled(true);
					transactionsArea.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "Sorry, customer ID is incorrect!",  "File Not Found", JOptionPane.ERROR_MESSAGE);
					transactionsArea.setText("");
					transactionsButton.setEnabled(false);
					accountDetailsButton.setEnabled(false);
				}
			}
		});
		searchButton.setBounds(246, 47, 95, 31);
		contentPane.add(searchButton);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(200, 367, 85, 21);
		contentPane.add(close);
		

		


	}
}
