import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileColorsDisjoint {
	public static int WHITE = 1, BLACK = 2, RED = 3, GREEN =4;
	public static int[][] tiles = new int[5][5];
	
	public static void main(String[] args) {
		Random rand = new Random();
		List<Integer> neighbors = new ArrayList<>(4);
		int[] colors = {WHITE, BLACK, RED, GREEN};
		boolean fullBreak=false;
		for(int r=0;r<tiles.length;r++){
			for(int c=0;c<tiles[0].length;c++){
				if(isEdge(r,c) > -1){
					if(isCorner(r,c) > -1){
						switch(isCorner(r,c)){
						case 0: //topLeft
							neighbors.add(tiles[r+1][c]);
							neighbors.add(tiles[r][c+1]);
							break;
						case 1: //topRight
							neighbors.add(tiles[r+1][c]);
							neighbors.add(tiles[r][c-1]);
							break;
						case 2: //bottomLeft
							neighbors.add(tiles[r-1][c]);
							neighbors.add(tiles[r][c+1]);
							break;
						case 3: //bottomRight
							neighbors.add(tiles[r-1][c]);
							neighbors.add(tiles[r][c-1]);
							break;
						}
					}else{
						switch(isEdge(r,c)){
						case 0: //Top
							neighbors.add(tiles[r][c-1]);
							neighbors.add(tiles[r][c+1]);
							neighbors.add(tiles[r+1][c]);
							break;
						case 1: //Bottom
							neighbors.add(tiles[r][c-1]);
							neighbors.add(tiles[r][c+1]);
							neighbors.add(tiles[r-1][c]);
							break;
						case 2: //Left
							neighbors.add(tiles[r-1][c]);
							neighbors.add(tiles[r][c+1]);
							neighbors.add(tiles[r+1][c]);
							break;
						case 3: //Right
							neighbors.add(tiles[r][c-1]);
							neighbors.add(tiles[r-1][c]);
							neighbors.add(tiles[r+1][c]);
							break;
						}
					}
				}else{
					neighbors.add(tiles[r+1][c]);
					neighbors.add(tiles[r-1][c]);
					neighbors.add(tiles[r][c+1]);
					neighbors.add(tiles[r][c-1]);
				}
				
				tiles[r][c] = rand.nextInt(colors.length)+1;
				if(neighbors.indexOf(tiles[r][c])!=-1){
					fullBreak = true;
					break;
				}
				neighbors.clear();
			}
			if(fullBreak == true)
				break;
		}
		
		printMatrix(tiles);
	}
	
	public static int isEdge(int row, int col){
		if(row == 0)
			return 0; //Top
		else if (row == tiles.length-1)
			return 1; //Bottom
		else if(col == 0)
			return 2; //Left
		else if(col == tiles[0].length-1)
			return 3; //Right
		else
			return -1; //notEdge
	}
	
	public static int isCorner(int row, int col){
		if(row == 0 && col == 0)
			return 0; //topLeft
		else if (row == 0 && col == tiles[0].length-1)
			return 1; //topRight
		else if (row == tiles.length-1 && col == 0)
			return 2; //bottomLeft
		else if (row == tiles.length-1 && col == tiles[0].length-1)
			return 3;//bottomRight
		else
			return -1; //notCorner
		
	} 
	
	public static void printMatrix(int[][] m){
		for(int[] row : m)
			printRow(row);
	}
	
	public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

}
