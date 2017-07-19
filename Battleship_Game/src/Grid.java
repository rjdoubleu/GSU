public abstract class Grid{
  private int myRows;
  private int myCols;
  private int[][] myGrid;
  
  public Grid(){
    myRows = 10;
    myCols = 10;
    myGrid = new int[myRows][myCols];
  }
  
  public Grid(int rows, int cols){
    myRows = rows;
    myCols = cols;
    myGrid = new int[rows][cols];
  }
}
  
  
  