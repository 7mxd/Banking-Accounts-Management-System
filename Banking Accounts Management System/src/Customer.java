import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Customer extends Person {
	
	private String accountType, username, password;
	private int Salary, balance, ID;
	int min = 1001;
	int max = 9999;
	
	public Customer() { super(); }
	
	public Customer(String n, String d, String e, String p, int Salary, String accountType, String username, String password, int balance) {
		super(n,d,e,p);
		this.Salary = Salary;
		this.accountType = accountType;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.ID = (int)Math.floor(Math.random()*(max-min+1)+min);
	}
	
	public void setSalary(int Salary) {this.Salary = Salary;}
	public void setAccountType(String accountType) {this.accountType = accountType;}
	public void setUserName(String userName) {this.username = userName;}
	public void setPassword(String password) {this.password = password;}
	public void setID(int ID) {this.ID = ID;};
	public void setBalance(int balance) {this.balance = balance;}
	
	public int getSalary() {return this.Salary;}
	public String getAccountType() {return this.accountType;}
	public String getUserName() {return this.username;}
	public String getPassword() {return this.password;}
	public int getID() {return this.ID;}
	public int getBalance() {return this.balance;}
	
	public String toString() {
		return String.format("%s, Salary: %d, Account Type: %s, Username: %s, Password: %s, Balance: %d, CustomerID: %d", super.toString(), Salary, accountType, username, password, balance, ID);
	}
	
	public String displayUserPass() {
		return String.format("%s %s %s", ID, username, password);
	}
	
	public void transferAmount(int transferAmount, int transferTo, String transferToName) throws IOException {
		
		// I know that the code looks bad, but I would say that that's the logic I got in mind.
		
		if (transferAmount > this.balance) {
			JOptionPane.showMessageDialog(null, "Dear customer, no enough balance to transfer!");
		} else {
			File receiverDetails = new File(".\\customers\\customer" + transferTo + "\\details.txt");		// Anyways, this part of the code
			String receiverDetailsModified = "";													// reads the details of the customer
			Scanner receiverDetailsReader = new Scanner(new FileReader(receiverDetails));   		// that will receive the money and 
			while(receiverDetailsReader.hasNext()) {                                        		// will reprint it in another file 
				String [] line = receiverDetailsReader.nextLine().split(", "); 						// without the "," 
				for(String word : line)   
					receiverDetailsModified += word + "\n";
			}
			receiverDetailsReader.close();		
			
			Scanner receiverDetailsModifiedReader = new Scanner(receiverDetailsModified); 				  // Next, we will read all 
			receiverDetailsModifiedReader.next();														  // details to create 
			String receiverName = "";
			Scanner detailsLine = new Scanner(receiverDetailsModifiedReader.nextLine());
			while(detailsLine.hasNext()) {
				receiverName += detailsLine.next() + " ";
			}									  // a new Customer with the 
			receiverDetailsModifiedReader.next();													      // details we saved.
			receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			String receiverDob = receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			String receiverEmail = receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			String receiverNumber = receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			int receiverSalary = receiverDetailsModifiedReader.nextInt();
			receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			String receiverAccountType = receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			String receiverUsername = receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			String receiverPassword = receiverDetailsModifiedReader.next();
			receiverDetailsModifiedReader.next();
			int receiverBalance = receiverDetailsModifiedReader.nextInt();
			receiverDetailsModifiedReader.next();
			int receiverID = receiverDetailsModifiedReader.nextInt();
			receiverDetailsModifiedReader.close();
			
			Customer customer2 = new Customer(receiverName, receiverDob , receiverEmail, receiverNumber, receiverSalary, 
					receiverAccountType, receiverUsername, receiverPassword, receiverBalance);
			customer2.setID(receiverID);
			
			customer2.setBalance(customer2.getBalance() + transferAmount); // Here we are adding the transfered money to the customer 
			
			PrintWriter receiverDetailsWriter = new PrintWriter(receiverDetails); // Here we are rewriting the details with 
			receiverDetailsWriter.println(customer2);							  // new details
			receiverDetailsWriter.close();
			
			// Here we write a notification in the unreadNotifications file
			File notificationsFile = new File(".\\customers\\customer" + transferTo + "\\unreadNotifications.txt");	 		
			FileWriter notificationsWriter = new FileWriter(notificationsFile, true);							 				
			notificationsWriter.write("Sender name: " + this.getName() + " with ID: " + this.ID + " successfully sent " + transferAmount + "!\n");
			notificationsWriter.close();																	 			
			
			// Here we write a notification in the accountActivities file
			File receiverActivitiesFile = new File(".\\customers\\customer" + transferTo + "\\accountActivities.txt");
			FileWriter receiverActivitiesWriter = new FileWriter(receiverActivitiesFile, true);							 	
			receiverActivitiesWriter.write(java.time.LocalDate.now() + "\n" + "Sender name: " + this.getName() + "with ID: " + this.ID + 
					" successfully sent " + transferAmount + "!\n");	
			receiverActivitiesWriter.close();																	 		
			
			this.balance -= transferAmount; // Decreases balance of sender
			
			File accountActivitiesFile = new File(".\\customers\\customer"+this.ID+"\\accountActivities.txt"); 	// Prints an activity
			FileWriter accountActivitiesWriter = new FileWriter(accountActivitiesFile, true);						// with the date
			accountActivitiesWriter.write(java.time.LocalDate.now() + "\n" + transferAmount + " has successfully transferred to " 
			+ transferToName + "\n");
			accountActivitiesWriter.close();
			
			//File accountNotificationsFile = new File(".\\customers\\customer"+this.ID+"\\unreadNotifications.txt"); 	// Write a notification
			//FileWriter accountNotificationsWriter = new FileWriter(accountNotificationsFile, true);						// for the sender
			//accountNotificationsWriter.write(transferAmount + " has successfully transferred to " + transferToName + "\n");
			//accountNotificationsWriter.close();
			
			JOptionPane.showMessageDialog(null, transferAmount + " has successfully transferred to " + transferToName);	// JOption with a 
																													// message
			File detailsFile = new File(".\\customers\\customer"+this.ID+"\\details.txt");
			PrintWriter detailsPrinter = new PrintWriter(detailsFile); 									
			detailsPrinter.println(this.toString());													// Reprint details after balance change
			detailsPrinter.close();
		}
	}
	
	public void transferAmount(int transferAmount, int transferTo) throws IOException {
		
		// I know that the code looks bad, but I would say that that's the logic I got in mind.
		
		if (transferAmount > this.balance) {
			JOptionPane.showMessageDialog(null, "Dear customer, no enough balance to transfer!");
		} else {
			File customerFolder = new File(".\\customers\\customer" + transferTo);
			if (customerFolder.exists()) {
				File receiverDetails = new File(".\\customers\\customer" + transferTo + "\\details.txt");			
				String receiverDetailsModified = "";															
				Scanner receiverDetailsReader = new Scanner(new FileReader(receiverDetails));   					
				while(receiverDetailsReader.hasNext()) {                                        				
					String [] line = receiverDetailsReader.nextLine().split(", "); 								
					for(String word : line)   
						receiverDetailsModified += word + "\n";
				}
				receiverDetailsReader.close();		
				
				Scanner receiverDetailsModifiedReader = new Scanner(receiverDetailsModified); 				  
				receiverDetailsModifiedReader.next();														  
				String receiverName = "";
				Scanner detailsLine = new Scanner(receiverDetailsModifiedReader.nextLine());
				while(detailsLine.hasNext()) {
					receiverName += detailsLine.next() + " ";
				}									
				receiverDetailsModifiedReader.next();													      
				receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				String receiverDob = receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				String receiverEmail = receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				String receiverNumber = receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				int receiverSalary = receiverDetailsModifiedReader.nextInt();
				receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				String receiverAccountType = receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				String receiverUsername = receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				String receiverPassword = receiverDetailsModifiedReader.next();
				receiverDetailsModifiedReader.next();
				int receiverBalance = receiverDetailsModifiedReader.nextInt();
				receiverDetailsModifiedReader.next();
				int receiverID = receiverDetailsModifiedReader.nextInt();
				receiverDetailsModifiedReader.close();
				
				Customer customer2 = new Customer(receiverName, receiverDob , receiverEmail, receiverNumber, receiverSalary, 
						receiverAccountType, receiverUsername, receiverPassword, receiverBalance);
				customer2.setID(receiverID);
				
				customer2.setBalance(customer2.getBalance() + transferAmount); 
				
				PrintWriter receiverDetailsWriter = new PrintWriter(receiverDetails); 
				receiverDetailsWriter.println(customer2);							  
				receiverDetailsWriter.close();
				
				File notificationsFile = new File(".\\customers\\customer" + transferTo + "\\unreadNotifications.txt");	 		
				FileWriter notificationsWriter = new FileWriter(notificationsFile, true);							 				
				notificationsWriter.write("Sender name: " + this.getName() + " with ID: " + this.ID + " successfully sent " + transferAmount + "!\n");
				notificationsWriter.close();																	 			
				
				File receiverActivitiesFile = new File(".\\customers\\customer" + transferTo + "\\accountActivities.txt");
				FileWriter receiverActivitiesWriter = new FileWriter(receiverActivitiesFile, true);							 	
				receiverActivitiesWriter.write(java.time.LocalDate.now() + "\n" + "Sender name: " + this.getName() + "with ID: " + this.ID + 
						" successfully sent " + transferAmount + "!\n");	
				receiverActivitiesWriter.close();																	 		
				
				this.balance -= transferAmount; 
				
				File accountActivitiesFile = new File(".\\customers\\customer"+this.ID+"\\accountActivities.txt"); 	
				FileWriter accountActivitiesPrinter = new FileWriter(accountActivitiesFile, true);						
				accountActivitiesPrinter.write(java.time.LocalDate.now() + "\n" + transferAmount + " has successfully transferred to "
						+ "customerID: " + customer2.ID + "\n");
				accountActivitiesPrinter.close();
				
				//File accountNotificationsFile = new File(".\\customers\\customer"+this.ID+"\\unreadNotifications.txt"); 	// Write a notification
				//FileWriter accountNotificationsWriter = new FileWriter(accountNotificationsFile, true);						// for the sender
				//accountNotificationsWriter.write(transferAmount + " has successfully transferred to customerID:" + customer2.ID + "\n");
				//accountNotificationsWriter.close();
				
				JOptionPane.showMessageDialog(null, transferAmount + " has successfully transferred to " 
				+ "customerID: " + customer2.ID + "\n");	
																														
				File detailsFile = new File(".\\customers\\customer"+this.ID+"\\details.txt");
				PrintWriter detailsPrinter = new PrintWriter(detailsFile); 									
				detailsPrinter.println(this.toString());													
				detailsPrinter.close();
			} else {
				JOptionPane.showMessageDialog(null, "Wrong ID! Try again!");
			}
		}
	}
	
	public void interest(int amount){
		if (accountType.equals("savings")) {
			Timer twoMinutes = new Timer();
			
			TimerTask addInterest = new TimerTask() {

				@Override
				public void run() {
					balance += amount;
					File unreadNotificationsFile = new File(".\\customers\\customer"+ID+"\\unreadNotifications.txt");
					FileWriter writeNotification;
					try {
						writeNotification = new FileWriter(unreadNotificationsFile, true);
						writeNotification.write("Amount of interest: " + amount + " has been deposited.\n");
						writeNotification.close();
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			};
			
			twoMinutes.scheduleAtFixedRate(addInterest, 0, 120000);
		}
	}
}
