/* Created by Ryan Walden
 *  
 * Sorts a matrix in ascending order by row elements
 * 
 * Example:
 * 
 * 1 3 4       1 2 6
 * 3 5 3  ==>  1 3 4
 * 1 2 6       3 5 3
 * 
 */

public class MatrixInsertionSort {
	public static int[][] matrix = new int[][] {{3,10,4},{1,1012,-18},{3,-12,-101},{67,0,-23},{3,10,5}};
	public static int rows = 5;
	public static int cols = 3;
	
	public static void main (String[] args){
		System.out.println("Unsorted: ");
		printMatrix(matrix);
		for(int c=0;c<cols;c++){
			int currRow = c;
			for(int r=0;r<rows-currRow;r++){
				if(currRow==0 || matrix[currRow][c-1] == matrix[currRow+r][c-1]){ //maybe change first iteration to a mergeSort?
					findSwap(currRow, c);
				}
			}
		}
		System.out.println("\nSorted: ");
		printMatrix(matrix);
	}
	
	public static void findSwap(int currRow, int currCol){
		while(currRow<rows-1){
			for(int r=1;r<rows-currRow;r++){ //this is an insertion sort i believe
				if(matrix[currRow][currCol]>matrix[currRow+r][currCol]){
					if(currCol==0 || matrix[currRow][currCol-1]==matrix[currRow+r][currCol-1])
						swap(currRow, currRow+r);
				}
			}
			currRow++;
		}
	}
	
	public static void swap(int a, int b){
		int temp[] = matrix[a];
		matrix[a] = matrix[b];
		matrix[b] = temp;
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