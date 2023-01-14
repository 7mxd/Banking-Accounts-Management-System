import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;



public class TransferUtility extends JFrame {

	private JPanel contentPane;
	private JTextField amountValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferUtility frame = new TransferUtility();
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
	public TransferUtility() {
		setTitle("Tranfer to a Utility");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel to = new JLabel("Transfer to:");
		to.setFont(new Font("Tahoma", Font.BOLD, 14));
		to.setBounds(10, 26, 92, 13);
		contentPane.add(to);
		
		JComboBox utility = new JComboBox();
		utility.setModel(new DefaultComboBoxModel(new String[] {"Water and Electricity", "Etisalat", "Du"}));
		utility.setToolTipText("");
		utility.setFont(new Font("Tahoma", Font.PLAIN, 12));
		utility.setBounds(119, 23, 229, 21);
		contentPane.add(utility);
		
		JLabel amount = new JLabel("Amount:");
		amount.setFont(new Font("Tahoma", Font.BOLD, 14));
		amount.setBounds(10, 71, 92, 13);
		contentPane.add(amount);
		
		JButton transfer = new JButton("Transfer");
		transfer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		transfer.setBounds(79, 112, 85, 21);
		contentPane.add(transfer);
		
		amountValue = new JTextField();
		amountValue.setBounds(119, 69, 229, 21);
		contentPane.add(amountValue);
		amountValue.setColumns(10);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancel.setBounds(194, 113, 85, 21);
		contentPane.add(cancel);
	}
	
	public TransferUtility(Customer customer) {
		setTitle("Tranfer to a Utility");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel to = new JLabel("Transfer to:");
		to.setFont(new Font("Tahoma", Font.BOLD, 14));
		to.setBounds(10, 26, 92, 13);
		contentPane.add(to);
		
		JComboBox utility = new JComboBox();
		utility.setModel(new DefaultComboBoxModel(new String[] {"Water and Electricity", "Etisalat", "Du"}));
		utility.setToolTipText("");
		utility.setFont(new Font("Tahoma", Font.PLAIN, 12));
		utility.setBounds(119, 23, 229, 21);
		contentPane.add(utility);
		
		JLabel amount = new JLabel("Amount:");
		amount.setFont(new Font("Tahoma", Font.BOLD, 14));
		amount.setBounds(10, 71, 92, 13);
		contentPane.add(amount);
		
		amountValue = new JTextField();
		amountValue.setBounds(119, 69, 229, 21);
		contentPane.add(amountValue);
		amountValue.setColumns(10);
		
		JButton transfer = new JButton("Transfer");
		transfer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (amountValue.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please make sure that all fields are filled, and try again!");
					dispose();
				}
				else if (utility.getSelectedIndex() == 0) {
						try {
							customer.transferAmount(Integer.parseInt(amountValue.getText()), 6069, "Water and Electricity");
							dispose();
						} catch (NumberFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				else if(utility.getSelectedIndex() == 1) {
					try {
						customer.transferAmount(Integer.parseInt(amountValue.getText()), 8829, "Etisalat");
						dispose();
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					try {
						customer.transferAmount(Integer.parseInt(amountValue.getText()), 5627, "Du");
						dispose();
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		transfer.setBounds(79, 112, 85, 21);
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
