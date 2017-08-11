/* Shout out to http://www.datagenetics.com/blog/december32011/
 * for all the algorithm help
 * 
 * currently stable, need to focus on AI and GUI
 */
import java.util.*;
class BattleshipGame{
  private static Ship ca = new Carrier(), bs = new Battleship(), cu = new Cruiser(), de = new Destroyer(), su = new Submarine();
  private static Ship[] ships = {ca, bs, cu, de, su};
  private static int[][] grid = new int[10][10];
  private static Ship pca = new Carrier(), pbs = new Battleship(), pcu = new Cruiser(), pde = new Destroyer(), psu = new Submarine();
  private static Ship[] pships = {pca, pbs, pcu, pde, psu};
  private static int[][] pgrid = new int[10][10];
  public static Scanner in = new Scanner(System.in);
  public static Player enemy = new Player("ENEMY", new Fleet(ships, grid));
  public static Fleet enemyFleet = enemy.getFleet();
  public static Player player;
  public static Fleet playerFleet;
  public static char MODE;
  
  public static void main(String[] args){
    System.out.println("JAVA BATTLESHIP CREATED BY RJDOUBLEU\n\n");
    System.out.println("ENTER PLAYER NAME (NO SPACES):");
    String name = (in.next()).toUpperCase();
    player = new Player(name, new Fleet(pships, pgrid));
    playerFleet = player.getFleet();
    createBoard();
    System.out.println("A.I. SETTING: EASY, MEDIUM, OR HARD (E/M/H) [Default is easy]");
    MODE = Character.toUpperCase((in.next().charAt(0)));
    //make a handler
    String hit = "";
    playGame(hit);
    //need to code play again function
    /*System.out.println("Play again? (Y/N) [Default is no]:");
	  char answer = Character.toUpperCase((in.next().charAt(0)));
	  if(answer=='Y')*/
  }
  
  
  
  public static void createBoard(){
	  playerFleet.print2DGrid(enemyFleet);
	  in = new Scanner(System.in);
	  System.out.println("\n" + player.getName() + "'S FLEET|SELECT POSITIONS");
	  System.out.println("Would you like enter ships manually? (Y/N) [Default is automatically]: ");
	  char answer = Character.toUpperCase((in.next().charAt(0)));

	  if(answer == 'Y' || answer == 'y'){
		  for (Ship s: playerFleet.getShips()){
			  playerFleet.setPosition(s);
		  }
	  }else{
		  System.out.println("Loading Player Fleet...");
		  for (Ship s: playerFleet.getShips()){
			  playerFleet.setAutoPosition(s);
			  System.out.println(s.getPositions());
		  }
	  }
	  
	  System.out.println("Loading Enemy Fleet...");
	  for (Ship x: enemyFleet.getShips()){
		  enemyFleet.setAutoPosition(x);
		  System.out.println(x.getPositions());
	  }
  }
  
  public static void playGame(String hit){
	playerFleet.print2DGrid(enemyFleet);
    launchMissle();
    if(enemyFleet.isSunk()){
    	endGame(true);
    }
    String nHit = autoLaunchMissle(hit);
    if(playerFleet.isSunk()){
    	endGame(false);
    }
    playGame(nHit);
  }
  
  public static void endGame(boolean player_win){
	  if(player_win==true)
		  System.out.println("Enemy fleet has been sunk, " + player.getName() + "'s fleet is victorious!");
	  else
		  System.out.println(player.getName() + "'s fleet has been sunk. The enemy is victorious!");
	  System.exit(0); // should try to play again
  }
  
  public static void launchMissle(){
	  System.out.println("ENTER POSITION TO LAUNCH MISSLE:");
	  String pos = in.next();
	  pos = (pos.substring(0,1)).toUpperCase() + pos.substring(1);
	  enemyFleet.isHit(pos);
  }
  
  public static String autoLaunchMissle(String hit){
	  char MEDIUM_MODE = 'M', HARD_MODE = 'H';
	  System.out.println("Launching...");
	  if(MODE==MEDIUM_MODE){
		  if(hit == ""){
			  String pos = randomTargeting();
			  if(pos=="UNAVAILABLE")
				  autoLaunchMissle("");
			  else if(pos!="")
				  hit = pos;
		  }else{
		  //Hunt/Target Parity
			  System.out.println("Doing a hunt.");
			  String[] surroundingCells = new String[4];
			  if(isCorner(hit)){
				  if(hit=="A1"){
					  surroundingCells[0] = getRight(hit);
					  surroundingCells[1] = getDown(hit);
				  }else if(hit=="J1"){
					  surroundingCells[0] = getRight(hit);
					  surroundingCells[1] = getUp(hit);
				  }else if(hit=="A10"){
					  surroundingCells[0] = getLeft(hit);
					  surroundingCells[1] = getDown(hit);
				  }else{
					  surroundingCells[0] = getLeft(hit);
					  surroundingCells[1] = getUp(hit);
				  }
			  }else if(isEdge(hit)){
				  if(hit.substring(0)=="A"){
					  surroundingCells[0] = getRight(hit);
					  surroundingCells[1] = getLeft(hit);
					  surroundingCells[2] = getDown(hit);
				  }else if(hit.substring(0)=="J"){
					  surroundingCells[0] = getLeft(hit);
					  surroundingCells[1] = getUp(hit);
					  surroundingCells[2] = getDown(hit);
				  }else if(hit.substring(1)=="1"){
					  surroundingCells[0] = getUp(hit);
					  surroundingCells[1] = getDown(hit);
					  surroundingCells[0] = getRight(hit);
				  }else{
					  surroundingCells[0] = getUp(hit);
					  surroundingCells[1] = getDown(hit);
					  surroundingCells[2] = getLeft(hit);
				  }
			  }else{
				  surroundingCells[0] = getRight(hit);
				  surroundingCells[1] = getLeft(hit);
				  surroundingCells[2] = getUp(hit);
				  surroundingCells[3] = getDown(hit);
			  }
			  
			  for(String sc : surroundingCells) {
				  int val = playerFleet.getGrid()[playerFleet.convertToInt(sc.substring(0,1))][Integer.parseInt(sc.substring(1))-1];
				  if(val != -3 && val!= -2 && val !=-1 )
					  //cycle through targets on each turn
					  //if one or more target was hit, find SCs and repeat
					  //if no SCs were hit, move to other target
					  //so you need a list of targets that adjust when a new target is hit
					  //check on each shot if position hit became -3 sunk
					  //if so return to random targeting
					  //unless there's still a hit thats not a sunk
					  //[another hit ship during targeting process]
					  System.out.println("Boom lol");
				  else
					  System.out.println("Caaannnt dew it");
			  }
		  }
	 }else if(MODE==HARD_MODE){
	    	//Advanced Probability
	 }else{
	    	if(randomTargeting()=="UNAVAILABLE")
				  autoLaunchMissle("");
	 }
	 System.out.println(hit);
	 return hit;
  }
  
  public static String getRight(String pos){return pos.substring(0,1) + (Integer.parseInt(pos.substring(1))+1);}
  public static String getLeft(String pos){return pos.substring(0,1) + (Integer.parseInt(pos.substring(1))-1);}
  public static String getUp(String pos){return playerFleet.convertToLetter(playerFleet.convertToInt(pos.substring(0,1))-1) + pos.substring(1);}
  public static String getDown(String pos){return playerFleet.convertToLetter(playerFleet.convertToInt(pos.substring(0,1))+1) + pos.substring(1);}
  
  public static String randomTargeting(){
	  String ret = " ";
	  int row = playerFleet.getRandomRowOrCol();
	  int col = playerFleet.getRandomRowOrCol();
	  String position = playerFleet.convertToLetter(row) + "" + (col+1);
	  if(playerFleet.isPositionOpen(position, false)==true){
		  if(playerFleet.isHit(position)==false || MODE=='E')
			  ret = "";
		  else
			  ret = position;
	  }else
		  ret = "UNAVAILABLE";
	  return ret;
  }
  
  public static boolean isCorner(String pos){
	  int row = playerFleet.convertToInt(pos.substring(0,1));
	  int col = Integer.parseInt(pos.substring(1));
	  if((row == 0 || row == 9) && (col ==0 || col ==9))
		  return true;
	  else
		  return false;
  }
  
  public static boolean isEdge(String pos){
	  int row = playerFleet.convertToInt(pos.substring(0,1));
	  int col = Integer.parseInt(pos.substring(1));
	  if((row == 0 || row == 9 || col ==0 || col ==9) && isCorner(pos)==false)
		  return true;
	  else
		  return false;
  }
}
  