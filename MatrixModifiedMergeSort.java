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
public class MatrixModifiedMergeSort {
	public static int [][] matrix = new int[][] {{67,0,-23,5,1738},{3,10,4,100,-38},{1738,3,-12,-101,12},{1,1012,-18,-23,216},{1738,3,-12,-101,412},{67,0,-23,1,1738},{3,10,5,69,420}};
	
	public static void main(String[] args){
		System.out.println("Unsorted: ");
		printMatrix(matrix);
		System.out.println("Sorted: ");
		matrix = merge_sort(matrix);
		printMatrix(matrix);
	}
	
	public static int[][] merge_sort(int[][] m){
		if(m.length <= 1){
			return m;
		}
		int midpoint = m.length/2;
		int [][] left = new int[midpoint][];
		int [][] right= new int[m.length - midpoint][];
		
		int [][] result = new int[m.length][m[0].length];
		
		
		for(int i=0;i<midpoint;i++)
			left[i] = m[i];
		
		int x=0;
		for(int i=midpoint;i<m.length;i++){
			right[x] = m[i];
			x++;
		}
		
		left = merge_sort(left);
		right = merge_sort(right);
		
		result = merge(left, right);
		return result;
	}
	
	public static int[][] merge(int[][] left, int[][] right){
		int lengthResult = left.length + right.length;
		int [][] result = new int [lengthResult][left[0].length];
		int indexL = 0;
		int indexR = 0;
		int indexC = 0;
		int indexRes = 0;
		
		while (indexL < left.length || indexR < right.length){
			if(indexL < left.length && indexR < right.length){
				if(left[indexL][indexC] < right[indexR][indexC]){
					result[indexRes] = left[indexL];
					indexL++;
					indexRes++;
				}else if(left[indexL][indexC] == right[indexR][indexC]){
					indexC++;
					while(indexC<left[0].length){
						if(left[indexL][indexC] < right[indexR][indexC]){
							result[indexRes] = left[indexL];
							indexL++;
							indexRes++;
							break;
						}else if(left[indexL][indexC] > right[indexR][indexC]){
							result[indexRes] = right[indexR];
							indexR++;
							indexRes++;
							break;
						}
						indexC++;
					}
					
					if(indexC==left[0].length){
						result[indexRes] = left[indexL];
						indexL++;
						indexRes++;
					}
					
				}else{
					result[indexRes] = right[indexR];
					indexR++;
					indexRes++;
				}
				
				indexC=0; //reset column index
				
			}else if(indexL < left.length){
				result[indexRes] = left[indexL];
				indexL++;
				indexRes++;
			}else if(indexR < right.length){
				result[indexRes] = right[indexR];
				indexR++;
				indexRes++;
			}
		}
		return result;
	}
	
	public static void printMatrix(int[][] m){
		for(int[] row : m)
			printRow(row);
		System.out.println("\n");
	}
	
	public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
	        }
	        System.out.println();
	}
}
