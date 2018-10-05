import java.util.Random;

public class TileColorsUniform {
	public static int WHITE = 1, BLACK = 2, RED = 3, GREEN =4, BLUE = 5;
	public static int[][] tiles = new int[3][3];
	
	public static void main(String[] args) {
		Random rand = new Random();
		int[] colors = {WHITE, BLACK, RED, GREEN, BLUE};
		
		for(int r=0;r<tiles.length;r++){
			for(int c=0;c<tiles[0].length;c++){
				tiles[r][c] = rand.nextInt(colors.length)+1;
			}
		}		
		printMatrix(tiles);
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
