import java.util.Date;

public class Person {
 	private String name, email, phoneNumber;
 	private String dateOfBirth;

	public Person() {}

 	public Person(String name, String dateOfBirth, String email, String phoneNumber) {
 		this.name = name;
 		this.dateOfBirth = dateOfBirth;
 		this.email = email;
 		this.phoneNumber = phoneNumber;
 	}
 	
 	public void setName(String name) {this.name = name;}
 	public void setDateOfBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}
 	public void setEmail(String email) {this.email = email;}
 	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
 	
 	
 	public String getName() {return this.name;}
	public String getDateOfBirth() {return this.dateOfBirth;}
	public String getEmail() {return this.email;}
	public String getPhoneNumber() {return this.phoneNumber;}
	 
	public String toString() {
		return String.format("Name: %s, Date of Birth: %s, Email Address: %s, Phone Number: %s", name, dateOfBirth, email, phoneNumber);
	}
 	

}
