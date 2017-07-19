public class Player{
  private String myName;
  private Fleet myFleet;
  
  public Player(String name, Fleet fleet){
    myName = name;
    myFleet = fleet;
  }
  
  public Fleet getFleet(){return myFleet;}
  public String getName(){return myName;}
}