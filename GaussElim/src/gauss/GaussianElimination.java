package gauss;
import java.util.Scanner;

public class GaussianElimination {
	public static void main(String[] args) {
		System.out.println("Enter row and column size of the matrix (m x n)");
		Scanner stdin = new Scanner(System.in);
		final int ROW_SIZE = stdin.nextInt();
		final int COL_SIZE = stdin.nextInt();
		System.out.println("Enter a " + ROW_SIZE + "x" + COL_SIZE + " matrix to solve");
		double[][] matrix;
		matrix = new double[ROW_SIZE][COL_SIZE];
		for (int i = 0; i < ROW_SIZE; i++) {
			double[] row;
			row = new double[COL_SIZE];
			for (int j = 0; j < COL_SIZE; j++) {
				row[j] = stdin.nextDouble();
			}
			matrix[i] = row;
		}
		printMatrix(matrix);
		boolean solved = false;
		int current = 0;
		while (!solved) {
			//Check if solved (i.e. in RREF)
			if (current == COL_SIZE - 1) {
				solved = true;
			} else if (isInfinite(matrix)) {
				solved = true;
				System.out.println("The matrix has infinitely many solutions.");
			} else if (isInconsistent(matrix)) {
				solved = true;
				System.out.println("The matrix is inconsistent.");
			} else {
				double currentPivot = matrix[current][current];
				for (int i = 0; i < COL_SIZE; i++) {
					matrix[current][i] /= currentPivot;
				}
				printMatrix(matrix);
				for (int i = 0; i < ROW_SIZE; i++) {
					if (i != current) {
						double factor = matrix[i][current];
						for (int j = 0; j < COL_SIZE; j++) {
							matrix[i][j] = -1 * matrix[current][j] * factor + matrix[i][j]; 
						}
					}
				}
				printMatrix(matrix);
				current++;
			}
			
		}
		
		printMatrix(matrix);
		printParametric(matrix);
	}
	static void printParametric(double[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				boolean hasNonzero = false;
				if (mat[i][j] != 0 ) {
					hasNonzero = true;
				}
				if (mat[i][j] != 0) {
					if (j == mat[i].length - 1) {
						System.out.print("= " + mat[i][j]);
					} else {
						if (mat[i][j] == 1) {
							System.out.print("x" + (j + 1) + " ");
						} else {
							System.out.print(mat[i][j] + "x" + (j + 1) + " ");
						}
					}
				} else if (mat[i][j] == 0 && j == mat[i].length - 1 && hasNonzero) {
					System.out.print("= " + mat[i][j]);
				}
			}
			System.out.print("\n");
		}
	}
	static boolean isInconsistent(double[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			int zeroCount = 0;
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == 0) {
					zeroCount++;
				}
			}
			if (zeroCount == mat[i].length - 1 && (mat[i][mat[i].length - 1] > 0 || mat[i][mat[i].length - 1] < 0)) {
				return true;
			}
		}
		return false;
	}
	static boolean isInfinite(double[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			int zeroCount = 0;
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == 0) {
					zeroCount++;
				}
			}
			if (zeroCount == mat[i].length) {
				return true;
			}
		}
		return false;
	}
	static void printMatrix(double[][] mat) {
		System.out.print("---------------\n");
		for (int i = 0; i < mat.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.print("| ");
			System.out.print("\n");
		}
		System.out.print("---------------\n");
	}
}
