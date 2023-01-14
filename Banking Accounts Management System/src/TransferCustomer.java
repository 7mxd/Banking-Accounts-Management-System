import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class TransferCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField transferValue;
	private JTextField amountValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferCustomer frame = new TransferCustomer();
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
	public TransferCustomer() {
		setTitle("Transfer to a Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel customerID = new JLabel("Transfer to:");
		customerID.setFont(new Font("Tahoma", Font.BOLD, 14));
		customerID.setBounds(10, 26, 92, 13);
		contentPane.add(customerID);
		
		JLabel amount = new JLabel("Amount:");
		amount.setFont(new Font("Tahoma", Font.BOLD, 14));
		amount.setBounds(10, 65, 92, 25);
		contentPane.add(amount);
		
		transferValue = new JTextField();
		transferValue.setBounds(119, 23, 229, 21);
		contentPane.add(transferValue);
		transferValue.setColumns(10);
		
		amountValue = new JTextField();
		amountValue.setColumns(10);
		amountValue.setBounds(119, 69, 229, 21);
		contentPane.add(amountValue);
		
		JButton transfer = new JButton("Transfer");
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		transfer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transfer.setBounds(66, 109, 85, 21);
		contentPane.add(transfer);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancel.setBounds(178, 110, 85, 21);
		contentPane.add(cancel);
	}
	
	public TransferCustomer(Customer customer) {
		setTitle("Transfer to a Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel customerID = new JLabel("Transfer to:");
		customerID.setFont(new Font("Tahoma", Font.BOLD, 14));
		customerID.setBounds(10, 26, 92, 13);
		contentPane.add(customerID);
		
		JLabel amount = new JLabel("Amount:");
		amount.setFont(new Font("Tahoma", Font.BOLD, 14));
		amount.setBounds(10, 65, 92, 25);
		contentPane.add(amount);
		
		transferValue = new JTextField();
		transferValue.setBounds(119, 23, 229, 21);
		contentPane.add(transferValue);
		transferValue.setColumns(10);
		
		amountValue = new JTextField();
		amountValue.setColumns(10);
		amountValue.setBounds(119, 69, 229, 21);
		contentPane.add(amountValue);
		
		JButton transfer = new JButton("Transfer");
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (amountValue.getText().isEmpty() || transferValue.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please make sure that all fields are filled, and try again!");
					dispose();
				} else {
					try {
						customer.transferAmount(Integer.parseInt(amountValue.getText()), 
								Integer.parseInt(transferValue.getText()));
						dispose();
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		transfer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transfer.setBounds(66, 109, 85, 21);
		contentPane.add(transfer);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancel.setBounds(194, 113, 85, 21);
		contentPane.add(cancel);
	}
}
