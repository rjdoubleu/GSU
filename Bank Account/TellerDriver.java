import java.util.Scanner;
public class TellerDriver {
	public static void main(String[]args){
	    BankAccount a = BankAccount.createBankAccount();
	    if (TellerDriver.login(a) == true){
	      a.chargeFee(a, a.getPassword(), 5.00, a.getIdentification());
	      a.withdraw(a.getPassword(), 20.0, a.getIdentification());
	      System.out.println("New Balance: " + a.getBalance());
	    }else{
	      System.out.println("INCORRECT CREDENTIALS");
	    }
	 
	  }
	  
	  public static boolean login(BankAccount a){
	    System.out.println("LOGIN TO ACCOUNT");
	    Scanner in = new Scanner(System.in);
	    System.out.print("MEMBER NUMBER: ");
	    in.next();
	    int identification = Integer.parseInt(in.next());
	    System.out.print("\nPASSWORD:");
	    String password = in.next();
	    in.close();
	    if (password.equals(a.getPassword()) && identification == a.getIdentification())
	      return true;
	    else
	      return false;
	  }
}
