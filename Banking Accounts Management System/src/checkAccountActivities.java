import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.time.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class checkAccountActivities extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkAccountActivities frame = new checkAccountActivities();
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
	public checkAccountActivities() {
		setTitle("Check Account Activities for a Specific Period");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel startDate = new JLabel("Start Date:");
		startDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		startDate.setBounds(106, 12, 88, 17);
		contentPane.add(startDate);
		
		JDateChooser startDateValue = new JDateChooser();
		startDateValue.setBounds(204, 10, 172, 19);
		contentPane.add(startDateValue);
		
		JLabel endDate = new JLabel("End Date:");
		endDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		endDate.setBounds(106, 54, 88, 17);
		contentPane.add(endDate);
		
		JDateChooser endDateValue = new JDateChooser();
		endDateValue.setBounds(204, 52, 172, 19);
		contentPane.add(endDateValue);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		search.setFont(new Font("Tahoma", Font.PLAIN, 12));
		search.setBounds(397, 11, 85, 21);
		contentPane.add(search);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(397, 53, 85, 21);
		contentPane.add(close);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 575, 290);
		contentPane.add(scrollPane);
		
		JTextArea accountActivities = new JTextArea();
		scrollPane.setViewportView(accountActivities);
		accountActivities.setEditable(false);
	}
	
	public checkAccountActivities(Customer customer) {
		setTitle("Check Account Activities for a Specific Period");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel startDate = new JLabel("Start Date:");
		startDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		startDate.setBounds(106, 12, 88, 17);
		contentPane.add(startDate);
		
		JDateChooser startDateValue = new JDateChooser();
		startDateValue.setBounds(204, 10, 172, 19);
		contentPane.add(startDateValue);
		
		JLabel endDate = new JLabel("End Date:");
		endDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		endDate.setBounds(106, 54, 88, 17);
		contentPane.add(endDate);
		
		JDateChooser endDateValue = new JDateChooser();
		endDateValue.setBounds(204, 52, 172, 19);
		contentPane.add(endDateValue);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 575, 290);
		contentPane.add(scrollPane);
		
		JTextArea accountActivities = new JTextArea();
		scrollPane.setViewportView(accountActivities);
		accountActivities.setEditable(false);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate startDateVal = (startDateValue.getDate()).toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				LocalDate endDateVal = (endDateValue.getDate()).toInstant().atZone(ZoneId.systemDefault()).
						toLocalDate();
				File accountActivitiesFile = new File(".\\customers\\customer"+customer.getID()+"\\accountActivities.txt");
				if (accountActivitiesFile.exists()) {
					Scanner accountActivitiesReader;
					String accountActivitiesText = "";
					try {
						accountActivitiesReader = new Scanner(accountActivitiesFile);
						
							
						for(LocalDate date = startDateVal; date.isBefore(endDateVal) || date.isEqual(endDateVal); 
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
						accountActivities.setText(accountActivitiesText);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No Account Activities Found!");
				}
				
			}
		});
		search.setFont(new Font("Tahoma", Font.PLAIN, 12));
		search.setBounds(397, 11, 85, 21);
		contentPane.add(search);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(397, 53, 85, 21);
		contentPane.add(close);
		
	}
}
