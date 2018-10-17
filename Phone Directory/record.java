public class record {
	public String myFirstName, myLastName, myPhoneNumber;
	public record(String firstName, String lastName, String phoneNumber){
		myFirstName = firstName;
		myLastName = lastName;
		myPhoneNumber = phoneNumber;
	}
	public String toString(){
		return "First Name: " + myFirstName + "\nLast Name: " + myLastName + "\nPhone Number: " + myPhoneNumber; 
	}
}
