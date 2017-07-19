/* Shout out to http://www.datagenetics.com/blog/december32011/
 * for all the algorithm help
 * 
 * CURRENTLY UNSTABLE, PRIORITY IS TO GET EASY MODE RUNNING
 * RANDOM TARGETING CODE FLAWED
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
  public static char MODE = 'E';
  
  public static void main(String[] args){
    System.out.println("JAVA BATTLESHIP CREATED BY RJDOUBLEU\n\n");
    System.out.println("ENTER PLAYER NAME (NO SPACES):");
    String name = (in.next()).toUpperCase();
    player = new Player(name, new Fleet(pships, pgrid));
    playerFleet = player.getFleet();
    createBoard();
    System.out.println("A.I. SETTING: EASY, MEDIUM, OR HARD (E/M/H) [Default]");
    MODE = in.next().charAt(0);
    if(MODE!='M' || MODE!='H')
    	MODE = 'E';
    String hit = "";
    playGame(hit);
  }
  
  
  
  public static void createBoard(){
	  playerFleet.print2DGrid(enemyFleet);
	  in = new Scanner(System.in);
	  System.out.println("\n" + player.getName() + "'S FLEET|SELECT POSITIONS");
	  System.out.println("Would you like enter ships manually? (Y/N) [Default is automatically]: ");
	  char answer = in.next().charAt(0);

	  if(answer == 'Y' || answer == 'y'){
		  for (Ship s: playerFleet.getShips())
			  playerFleet.setPosition(s);
	  }else{
		  System.out.println("Loading Player Fleet...");
		  for (Ship s: playerFleet.getShips()){
			  playerFleet.setAutoPosition(s);
		  }
	  }
	  
	  System.out.println("Loading Enemy Fleet...");
	  for (Ship x: enemyFleet.getShips()){
		  enemyFleet.setAutoPosition(x);
	  }
  }
  
  public static void playGame(String hit){
	playerFleet.print2DGrid(enemyFleet);
    launchMissle();
    String nHit = autoLaunchMissle(hit);
    //playerFleet.print2DGrid(enemyFleet);
    playGame(nHit);
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
			  return randomTargeting();
		  }else{
		  //Hunt/Target Parity
			  int[] surroundingCells = new int[4]; //make handle for corners 
/*Right*/	  surroundingCells[0] = (playerFleet.getGrid())[Integer.parseInt(hit.substring(1))][playerFleet.convertToInt(hit.substring(0))+1];
/*Left*/	  surroundingCells[1] = (playerFleet.getGrid())[Integer.parseInt(hit.substring(1))][playerFleet.convertToInt(hit.substring(0))-1];
/*Up*/		  surroundingCells[2] = (playerFleet.getGrid())[Integer.parseInt(hit.substring(1))-1][playerFleet.convertToInt(hit.substring(0))-1];
/*Down*/	  surroundingCells[3] = (playerFleet.getGrid())[Integer.parseInt(hit.substring(1))+1][playerFleet.convertToInt(hit.substring(0))-1];
				for(int sc : surroundingCells) {
					if(sc!=-1 && sc!=-2 && sc!=-3){
						String pos = Integer.toString(playerFleet.convertToInt(hit.substring(0))-1) +
								 playerFleet.convertToLetter(Integer.parseInt(hit.substring(1))+1);
						if(playerFleet.isHit(pos)){
							hit = pos;
						break;
						}
					}
				}
		  }
		  /*if( ship is hit but not sunk and cell is next to hit position) 
		   * if (grid contains -1 but isSunk(ship at position) == false and grid position +0,1 +1,0 -1,0 0,-1 open)
		   * 		target cell
		   * else
		   * 	target random
		   */
	 }else if(MODE==HARD_MODE){
	    	//Advanced Probability
	 }else{
	    	randomTargeting();
	 }
	 return hit;
  }
  
  public static String randomTargeting(){
	  int row = playerFleet.getRandomRowOrCol();
	  int col = playerFleet.getRandomRowOrCol();
	  String position = playerFleet.convertToLetter(row) + "" + (col+1);
	  if (playerFleet.isPosition(position) == true){
		  if(playerFleet.isHit(position)==false || MODE=='E'){
			  return "";
		  }
	  }else{
		  playerFleet.isHit(position);
	  }
	  return position;
  }
}
  