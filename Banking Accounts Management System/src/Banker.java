public class Banker extends Person{
		
	private final String USERNAME;
	private final String PASSWORD;

	public Banker(){ super(); USERNAME = ""; PASSWORD = "";}

	public Banker(String name, String dateOfBirth, String email, String phoneNumber) {
		super(name, dateOfBirth, email, phoneNumber);
		USERNAME = "banker";
		PASSWORD = "banker1234";
	}

	public String getUsername() {return USERNAME;}
	public String getPassword() {return PASSWORD;}

	public String toString() {
		
	return String.format("%s, Username: %s, Password: %s", super.toString(), USERNAME, PASSWORD);
	}
}