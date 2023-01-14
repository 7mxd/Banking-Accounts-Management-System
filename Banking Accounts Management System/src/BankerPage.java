import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;

public class BankerPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankerPage frame = new BankerPage();
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
	public BankerPage() {
		setTitle("Banker Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 219, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome Banker");
		welcomeLabel.setBounds(27, 10, 168, 22);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(welcomeLabel);
		
		JButton requestsButton = new JButton("Requests");	
		requestsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				RegistrationPage RP1 = new RegistrationPage();
				RP1.setVisible(true);
			}
		});
		
		requestsButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		requestsButton.setBounds(118, 407, 85, 31);
		contentPane.add(requestsButton);
		
		JButton requestButton = new JButton("Requests");
		requestButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		requestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestsPage RP1 = new RequestsPage();
				RP1.setVisible(true);
			}
		});
		requestButton.setBounds(42, 59, 121, 36);
		contentPane.add(requestButton);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				SearchPage SP1 = new SearchPage();
				SP1.setVisible(true);
			}
		});
		
		
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		searchButton.setBounds(42, 114, 121, 36);
		contentPane.add(searchButton);
		
		JButton logout = new JButton("logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginPage lp2 = new LoginPage();
				lp2.setVisible(true);
			}
		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logout.setBounds(110, 293, 85, 21);
		contentPane.add(logout);
		
	}
}
