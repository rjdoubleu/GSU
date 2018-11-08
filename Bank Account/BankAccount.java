import java.util.Scanner;
class BankAccount{
  private String myPassword;
  private int myIdentification;
  private double myBalance;
  private static double intRate;
  public static final double OVERDRAWN_PENALTY = 20.00;
 
  //Constructor Method (Default Constructor)
  
  /*invoked by
   * BankAccount b = new BankAccount();
   */
  
  public BankAccount(){
    myPassword = "";
    myBalance = 0.0;
    myIdentification = 0;
  }
  
  //Constructor Method (Creates BankAccount object with specified password, balance, and identification
  
  /*invoked by
   * BankAccount b = new BankAccount(password, balance, identification);
   */
  
  public BankAccount(String password, double balance, int identification){
    myPassword = password;
    myBalance = balance;
    myIdentification = identification;
  }
  
  //Accesor Method (Returns balance of specific object)
  
  /*invoked by
   * BankAccount b = new BankAccount();
   * b.getBalance();
   */
  
  public double getBalance(){
    return myBalance;
  }
  
  //Accesor Method (Returns identification of specific object)
  public int getIdentification(){
    return myIdentification;
  }
  //Accesor Method (Returns password of specific object)
  public String getPassword(){
    return myPassword;
  }
  
  //Mutator Method (deposits amount in BankAccount with given identification and password)
  
  /*invoked by
   * BankAccount b = new BankAccount();
   * b.deposit(password, amount, indentification);
   */
  
  public void deposit(String password, double amount, int identification){
    if (!password.equals(myPassword)||identification != myIdentification){
      //throw an exception
  }else{
      myBalance += amount;
    }
  }
  
  //Mutator Method (withdraws amount in BankAccount with given identification and password)
  public void withdraw(String password, double amount, int identification){
    if (!password.equals(myPassword)||identification != myIdentification){
      //throw an exception
    }else{
      myBalance -= amount; //allows negative balance
      if (myBalance < 0)
        myBalance -= OVERDRAWN_PENALTY;
    }
  }
  
  //Static Method (preforms an operation on the entire Class, not just an object; returns interest rate)
  
  /*invoked by
   * double interestRate = BankAccount.getInterestRate();
   */
  
  public static double getInterestRate(){
    Scanner in = new Scanner(System.in);
    System.out.println("ENTER INTEREST RATE FOR BANK ACCOUNT");
    System.out.println("ENTER IN DECIMAL FORM:");
    intRate = in.nextDouble();
    in.close();
    return intRate;
  }
  
  //Static Method
  
  /*invoked  by
   * BankAccount newAccount = BankAccount.createBankAccount;
   */
  
  public static BankAccount createBankAccount(){
    Scanner a = new Scanner(System.in);
    System.out.println("CREATE AN ACCOUNT");
    System.out.println("ENTER IDENTIFICATION (EXAMPLE: 4657890):");
    int identification = a.nextInt();
    
    System.out.println("ENTER PASSWORD.");
    System.out.println("EXAMPLE: helloWorld");
    String password = a.next();
    
    System.out.println("ENTER BALANCE.");
    System.out.println("EXAMPLE: 1000.0");
    double balance = a.nextDouble();
    a.close();
    BankAccount b = new BankAccount(password, balance, identification);
    return b;
  }
  
  //Mutator
  public void chargeFee(BankAccount c, String password, double fee, int identification){
    final double MIN_BALANCE = 10.00;
    if (identification == c.getIdentification() && password.equals(c.getPassword())){
      if (c.getBalance() < MIN_BALANCE){
        c.withdraw(c.getPassword(), fee, c.getIdentification());
      }
    }
  }
}
