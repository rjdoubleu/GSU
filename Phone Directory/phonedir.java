import java.util.LinkedList;
import java.util.Scanner;
public class phonedir{
 public static final String SHOW="a", DELETE="d", CHANGE_FN="f", CHANGE_LN="l", ADD="n", CHANGE_PN="p", QUIT="q", SELECT="s";
 public static LinkedList<record> phonedir = new LinkedList<record>();
 public static record current = null;
 
 //Main driver
 //Runs the menu display, U.I.
 public static void main(String[] args){
  Scanner in = new Scanner(System.in);
  System.out.println("\n\nPhone Directory\n\nShow All Records: a\nDelete Record: d\nChange First Name: f\nChange Last Name: l\nAdd Record : n\n"
    + "Change Phone Number : p\nQuit : q\nSelect : s\n\n");
  String ans = in.next();
  //;
  if(current != null){
   switch(ans){
    case SHOW : 
     showRecords();
     break;
    case ADD : 
     addRecord();
     break;
    case QUIT : 
     quit();
     break;
    case DELETE: 
     deleteRecord(current);
     break;
    case CHANGE_FN : 
     changeFirstName(current);
     break;
    case CHANGE_LN : 
     changeLastName(current);
     break;
    case CHANGE_PN : 
     changePhoneNumber(current);
     break;
    case SELECT : 
     selectRecord();
     break;
    default : 
     System.out.println("Incorrect option, try again.");
     break;
   }
  }else{
   switch(ans){
    case SHOW : 
     showRecords();
     break;
    case ADD : 
     addRecord();
     break;
    case SELECT : 
     selectRecord();
     break;
    case QUIT : 
     quit();
     break;
    default :
     System.out.println("You have not selected a record, you may only add, select or quit. Try again.");
     break;
   }
  }
  main(null);
 }
 
 public static void showRecords(){
  for(record r: phonedir){
   System.out.println(r.toString()+"\n");
  }
 }
 
 //Mutator
 //Adds a new record object to the phone directory LinkedList
 public static void addRecord(){
  Scanner in = new Scanner(System.in);
  System.out.println("First Name: ");
  String firstName = in.nextLine();
  System.out.println("Last Name: ");
  String lastName = in.nextLine();
  String phoneNumber = "";
  do{
	  System.out.println("Phone Number: ");
	  phoneNumber = in.nextLine();
  }while(phoneNumber.length() != 10);
  phonedir.add(new record(firstName, lastName, phoneNumber));
 }
 
 //Mutator
 //Removes a record object from the phone directory LinkedList
 public static void deleteRecord(record current){
  phonedir.remove(current);
  current = null;
 }
 
 //Mutator
 //Changes the first name of a selected record object
 public static void changeFirstName(record current){
  Scanner in = new Scanner(System.in);
  System.out.println("\nEnter a new first name for record number " + (phonedir.indexOf(current)+1) + "\n");
  String firstName = in.next();
  current.myFirstName = firstName;
 }
 
//Mutator
//Changes the last name of a selected record object
 public static void changeLastName(record current){
  Scanner in = new Scanner(System.in);
  System.out.println("\nEnter a new last name for record number " + (phonedir.indexOf(current)+1) + "\n");
  String lastName = in.nextLine();
  current.myLastName = lastName;
 }
 
//Mutator
//Changes the phone number of a selected record object
 public static void changePhoneNumber(record current){
  Scanner in = new Scanner(System.in);
  System.out.println("\nEnter a new phone number for record number " + (phonedir.indexOf(current)+1) + "\n");
  String phoneNumber = in.nextLine();
  current.myPhoneNumber = phoneNumber;
 }
 
//Accessor
//Selects a record object contained in the LinkedList 
 public static void selectRecord(){
  Scanner in = new Scanner(System.in);
  System.out.println("\nEnter Record Number you wish to select: ");
  int recordNumber = in.nextInt();
  if(phonedir.size()==0){
   System.out.println("There are no records.");
  }else if(recordNumber > phonedir.size() || recordNumber < 1){
   System.out.println("Incorrect Record Number, try again.");
   selectRecord();
  }else{
   current = phonedir.get(recordNumber-1);
  }
 }
 
 //Exits the program 
 public static void quit(){
  System.exit(0);
 }
}
