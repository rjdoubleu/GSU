import java.util.*;
public class Fleet{
  private final int ROWS = 10;
  private final int COLS = 10;
  private final int CONVERSION_ERROR = 10;
  private int[][] myGrid = new int[ROWS][COLS];
  private Ship[] myShips;
  public static String position = "";
  public static Random rand = new Random();
  public static Scanner in = new Scanner(System.in);
  public Fleet(Ship[] ships, int[][] grid){
    myShips = ships;
    myGrid = grid;
  }
  
  public Ship[] getShips(){return myShips;}
  
  public int[][] getGrid(){return myGrid;}
  
  public int getRandomRowOrCol(){return rand.nextInt(9);}
  
  public void setAutoPosition(Ship s){
    int[][] grid =  this.getGrid();
    int row = getRandomRowOrCol();
    int col = getRandomRowOrCol();
    double hOrV = rand.nextDouble();
    int length = s.getLength();
    for (int v = 0; v < length; v++){
    	position = convertToLetter(row) + "" + (col+1);
    	if (isPositionValid(position) == false){
    	   	s.erasePositions(getGrid());
    	   	setAutoPosition(s);
        	break;
    	}else if(isPositionOpen(position, true) == false){
    		s.getPositions().remove(position);
    		s.erasePositions(getGrid());
    		setAutoPosition(s);
    		break;
   	    }else{
   	    	grid[row][col] = 1;
    	   	s.addPosition(position);
        	if (hOrV < 0.5)
   	    		row++;
   	    	else if (hOrV > 0.5)
   	    		col++;
   	    }	
    }
  }
  
  public void setPosition(Ship s){
    int[][] grid =  this.getGrid();
    System.out.print("\nChoose your " + s.getName() + "'s position\nEXAMPLE: ");
    
    for (int i = 1; i <= s.getLength(); i++){
      if (i == s.getLength())
        System.out.print("a" + i);
      else
        System.out.print("a" + i + ",");
    }
    System.out.print("\nPOSITIONS: ");
    String inPositions = in.nextLine();
    List <String> arrInPositions = Arrays.asList(inPositions.split(","));
    
    if (arrInPositions.size() != s.getLength()){
      System.out.println("ERROR: Invalid number of positions\n" + s.getName() + " must contain " + s.getLength() + " positions");
      setPosition(s);
    }else if (isLine(arrInPositions) == false){
      setPosition(s);
    }else{
      for (int q = 0; q < arrInPositions.size(); q++){
        String position = arrInPositions.get(q);
        if (isPositionValid(position) && isPositionOpen(position, true)){
          int r = convertToInt(position.substring(0, 1));
          int c = Integer.parseInt(position.substring(1))-1;
          if (grid[r][c] == 0){
        	  s.addPosition(position);
          }else{
            System.out.println("ERROR: Position " + position + " is already taken");
            setPosition(s);
          }
        }
    }
  }
}
    
  public boolean isLine(List<String> positions){
    boolean line = true;
    for (String z: positions){
      String w;
      if(isPositionOpen(z, true)==false){
    	  line = false;
    	  System.out.println(z + " is not a position");
      }
      if (positions.indexOf(z)==(positions.size()-1)){
        w = positions.get((positions.size()-2));
      }else{
        w = positions.get(positions.indexOf(z)+1);
      }
        if (z.equals(w)==false){
          if (z.substring(0,1).equals(w.substring(0,1))){
            int difference = Integer.parseInt(z.substring(1)) - Integer.parseInt(w.substring(1));
            if (Math.abs(difference) != 1){
              line = false;
              System.out.println("ERROR: Positions must be sequential.");
            }
          }
          
          if (z.substring(1).equals(w.substring(1))){
            int difference = convertToInt(z.substring(0,1)) - convertToInt(w.substring(0,1));
            if (Math.abs(difference) != 1){ 
              line = false;
              System.out.println("ERROR: Positions must be sequential.");
            }
          }
          
          if (z.substring(1).equals(w.substring(1)) == false && z.substring(0,1).equals(w.substring(0,1)) == false){
            line = false;
            System.out.println("ERROR: Positions must be sequential.");
          }
        }
      }
    return line;
  }

  public void print2DGrid(Fleet enemy){
    int grid[][] = enemy.getGrid();
    System.out.println("ENEMY FLEET");
    System.out.println("   1  2  3  4  5  6  7  8  9  10 ");
    for (int r = 0; r < grid.length; r++){
      System.out.print(convertToLetter(r) + " ");
      for (int c = 0; c < grid[r].length; c++){
        if (grid[r][c] == -1)
          System.out.print(" H ");
        else if (grid[r][c] == -2)
          System.out.print(" M ");
        else if (grid[r][c] == -3)
          System.out.print(" S ");
        else
          System.out.print(" X ");
      }
      System.out.println();
    }
    System.out.println("=================================\n");
    System.out.println("YOUR FLEET");
    System.out.println("   1  2  3  4  5  6  7  8  9  10 ");
    grid = this.getGrid();
    for (int r = 0; r < grid.length; r++){
      System.out.print(convertToLetter(r) + " ");
      for (int c = 0; c < grid[r].length; c++){
        if (grid[r][c] == 1)
          System.out.print(" O ");
        else if (grid[r][c] == -1)
          System.out.print(" H ");
        else if (grid[r][c] == -3)
          System.out.print(" S ");
        else
          System.out.print(" X ");
      }
      System.out.println();
    }
  }
  
  public String convertToLetter(int a){
    a+=1;
    if (a==1){
      return "A";
    }else if (a==2){
      return "B";
    }else if (a==3){
      return "C";
    }else if (a==4){
      return "D";
    }else if (a==5){
      return "E";
    }else if (a==6){
      return "F";
    }else if (a==7){
      return "G";
    }else if (a==8){
      return "H";
    }else if (a==9){
      return "I";
    }else if (a==10){
      return "J";
    }else{
    	return "K"; //K is for Kill
    }
  }

  public int convertToInt(String lexiShot){
    String lexiPosition = lexiShot.substring(0,1);
    if (lexiPosition.equals("A") || lexiPosition.equals("a"))
      return 0;
    else if (lexiPosition.equals("B") || lexiPosition.equals("b"))
      return 1;
    else if (lexiPosition.equals("C") || lexiPosition.equals("c"))
      return 2;
    else if (lexiPosition.equals("D") || lexiPosition.equals("d"))
      return 3;
    else if (lexiPosition.equals("E") || lexiPosition.equals("e"))
      return 4;
    else if (lexiPosition.equals("F") || lexiPosition.equals("f"))
      return 5;
    else if (lexiPosition.equals("G") || lexiPosition.equals("g"))
      return 6;
    else if (lexiPosition.equals("H") || lexiPosition.equals("h"))
      return 7;
    else if (lexiPosition.equals("I") || lexiPosition.equals("i"))
      return 8;
    else if (lexiPosition.equals("J") || lexiPosition.equals("j"))
      return 9;
    else
      return CONVERSION_ERROR;
  }

  public boolean isHit(String position){
    int[][] grid =  this.getGrid();
    boolean hit = true;
    int row = convertToInt(position.substring(0, 1));
    int col = Integer.parseInt(position.substring(1))-1;
    if (grid[row][col] == 0){
      grid[row][col] = -2;
      System.out.println("Position " + position + " is a miss");
      hit = false;
    }else if (grid[row][col] == -2 || grid[row][col] == -1){
      System.out.println("Position " + position + " has already been choosen, choose again");
      hit = false;
    }else{
      for (Ship s: this.getShips()){
        if ((s.getPositions()).contains(position) && grid[row][col] == 1){ 
          grid[row][col] = -1;
          s.addNumberHits();
          hit = true; 
          if (s.isSunk()){
            for (String pos: s.getPositions()){
              row = convertToInt(pos.substring(0, 1));
              col = Integer.parseInt(pos.substring(1))-1;
              grid[row][col] = -3;
            }
            System.out.println("You sunk the enemy's " + s.getName() + "!");
          }else{
            System.out.println("You hit "  + s.getName() + "!");
          }
        }
      }
    }
    return hit;
  }
  
  public boolean isPositionValid(String position){
	  int row = convertToInt(position.substring(0, 1));
	  int col = Integer.parseInt(position.substring(1))-1;
	  if (col > 9 || row > 9)
	    	return false;
	  else
		  return true;
  }
  
  public boolean isPositionOpen(String position, boolean SETUP_MODE){
    int[][] grid =  this.getGrid();
    boolean ret = true;
    int row = convertToInt(position.substring(0, 1));
    int col = Integer.parseInt(position.substring(1))-1; 
    if (SETUP_MODE && grid[row][col] == 1)
      ret = false;
    else if ((grid[row][col] == 0 || grid[row][col] == 1) && SETUP_MODE == false) //a hit is a -1 or -2
        ret = true;
    return ret;
  }
}