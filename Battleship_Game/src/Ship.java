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
  public void addPosition(String pos){myPositions.add(pos);}
  public void erasePositions(int[][] grid){
	  for(int i=0; i< this.myPositions.size();i++){
		  int row = convertToInt(this.myPositions.get(i).substring(0,1));
		  int col =  Integer.parseInt(this.myPositions.get(i).substring(1))-1;
		  grid[row][col] = 0;
	  }
	  myPositions.clear();
	  
	  }
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