import java.util.ArrayList;
import java.util.List;

public class TileColors {
	public static int WHITE = 1, BLACK = 2;
	public static int[][] tiles = new int[3][3];
	
	public static void main(String[] args) {
		
		List<Integer> neighbors = new ArrayList<>(4);
		int[] colors = {WHITE, BLACK};
		
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
				
				for(int color : colors){
					if(neighbors.indexOf(color) == -1)
						tiles[r][c] = color;
				}
				neighbors.clear();
			}
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
