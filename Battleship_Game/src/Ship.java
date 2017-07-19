import java.util.*;
public class Ship extends Fleet{
  private String myName;
  private int myLength, myNumberHits;
  private List<String> myPositions;
  
  public Ship(String name, int length, int numberHits, List<String> positions){
    super(null, null);
    myName = name;
    myLength = length;
    myNumberHits = numberHits;
    myPositions = positions;
  }
  
  public String getName(){return myName;}
  public int getLength(){return myLength;}
  public void addNumberHits(){myNumberHits++;}
  public int getNumberHits(){return myNumberHits;}
  public void addPosition(String pos, int row, int col, Fleet f){
    myPositions.add(pos);
    int[][] grid = f.getGrid();
    grid[row][col] = 1;
  }
  public void erasePositions(){myPositions = new ArrayList<String>(this.getLength()-1);}
  public List<String> getPositions(){return myPositions;}
  
  public boolean isSunk(){
	  if(this.myNumberHits == this.myLength)
		  return true;
	  else
		  return false;
  }
  
  public boolean isHit(){
	  if(this.myNumberHits!=0)
		  return true;
	  else
		  return false;
  }
}