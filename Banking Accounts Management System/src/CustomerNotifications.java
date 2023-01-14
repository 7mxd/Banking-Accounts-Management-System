import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.*;

import java.io.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerNotifications extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerNotifications frame = new CustomerNotifications();
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
	public CustomerNotifications() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel unread = new JLabel("Unread Notifications:");
		unread.setFont(new Font("Tahoma", Font.BOLD, 14));
		unread.setBounds(10, 10, 154, 13);
		contentPane.add(unread);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 416, 195);
		contentPane.add(scrollPane);
		
		JTextArea unreadNotifications = new JTextArea();
		unreadNotifications.setForeground(new Color(255, 0, 0));
		unreadNotifications.setEditable(false);
		scrollPane.setViewportView(unreadNotifications);
		
		JLabel read = new JLabel("Read Notifications:");
		read.setFont(new Font("Tahoma", Font.BOLD, 14));
		read.setBounds(10, 251, 154, 13);
		contentPane.add(read);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 274, 414, 193);
		contentPane.add(scrollPane_1);
		
		JTextArea readNotifications = new JTextArea();
		readNotifications.setEditable(false);
		scrollPane_1.setViewportView(readNotifications);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(174, 486, 85, 23);
		contentPane.add(close);
	}
	
	public CustomerNotifications(Customer customer) throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel unread = new JLabel("Unread Notifications:");
		unread.setFont(new Font("Tahoma", Font.BOLD, 14));
		unread.setBounds(10, 10, 154, 13);
		contentPane.add(unread);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 416, 195);
		contentPane.add(scrollPane);
		
		JTextArea unreadNotifications = new JTextArea();
		unreadNotifications.setForeground(new Color(255, 0, 0));
		unreadNotifications.setEditable(false);
		scrollPane.setViewportView(unreadNotifications);
		
		File unreadNotificationsFile = new File(".\\customers\\customer" + customer.getID() + "\\unreadNotifications.txt");
		Scanner unreadNotificationsReader = new Scanner(new FileReader(unreadNotificationsFile));
		String unreadNotificationsString = "";
		while (unreadNotificationsReader.hasNext())
			unreadNotificationsString += unreadNotificationsReader.nextLine()+"\n";
		unreadNotifications.setText(unreadNotificationsString);
		unreadNotificationsReader.close();
		
		JLabel read = new JLabel("Read Notifications:");
		read.setFont(new Font("Tahoma", Font.BOLD, 14));
		read.setBounds(10, 251, 154, 13);
		contentPane.add(read);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 274, 414, 193);
		contentPane.add(scrollPane_1);
		
		JTextArea readNotifications = new JTextArea();
		readNotifications.setEditable(false);
		scrollPane_1.setViewportView(readNotifications);
		
		File readNotificationsFile = new File(".\\customers\\customer" + customer.getID() + "\\readNotifications.txt");
		if (readNotificationsFile.exists()) {
			Scanner readNotificationsReader = new Scanner(new FileReader(readNotificationsFile));
			String readNotificationsString = "";
			while (readNotificationsReader.hasNext())
				readNotificationsString += readNotificationsReader.nextLine()+"\n";
			readNotifications.setText(readNotificationsString);
			readNotificationsReader.close();
		} else {
			readNotifications.setText("");
		}
		
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Scanner readUnreadNotifications = new Scanner(new FileReader(unreadNotificationsFile));
					// Read unread notifications to the read notifications
					FileWriter writeUnreadNotifications = new FileWriter(readNotificationsFile, true);
					String unreadToRead = "";
					while(readUnreadNotifications.hasNext()) {
						unreadToRead += readUnreadNotifications.nextLine() + "\n";
					}
					writeUnreadNotifications.write(unreadToRead);
					// Empty the unread notifications
					PrintWriter unreadNotificationsEmptier = new PrintWriter(unreadNotificationsFile);
					unreadNotificationsEmptier.print("");
					
					unreadNotificationsEmptier.close();
					readUnreadNotifications.close();
					writeUnreadNotifications.close();
					
					
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(162, 496, 85, 23);
		contentPane.add(close);
	}
}
