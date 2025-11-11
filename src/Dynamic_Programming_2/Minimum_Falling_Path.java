package Dynamic_Programming_2;

import java.util.*;

public class Minimum_Falling_Path {
	public static void main(String[] args) {
		int[][] matrix = { { 2, 1, 3 }, { 6, 5, 4 }, { 7, 8, 9 } };

		int T_result = Tabulation(matrix);
		int M_result = Memoization(matrix);

		System.out.println("Minimum Falling Path Sum Using Tabulation : " + T_result);
		System.out.println("Minimum Falling Path Sum Using Memoization : " + M_result);
	}

	private static int Memoization(int[][] matrix) {
		int n = matrix.length;
		int[][] Dp = new int[n][n];
		for (int row[] : Dp) {
			Arrays.fill(row, -1);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, Solver(0, i, matrix, Dp));
		}
		return min;
	}

	private static int Solver(int row, int col, int[][] matrix, int[][] Dp) {
		if (col < 0 || col >= matrix[0].length)
			return (int) 1e9;
		if (row == matrix.length - 1)
			return matrix[row][col];
		if (Dp[row][col] != -1)
			return Dp[row][col];

		int down = matrix[row][col] + Solver(row + 1, col, matrix, Dp);
		int downleft = matrix[row][col] + Solver(row + 1, col - 1, matrix, Dp);
		int downright = matrix[row][col] + Solver(row + 1, col + 1, matrix, Dp);
		int ans = Math.min(down, Math.min(downleft, downright));
		return Dp[row][col] = ans;
	}

	private static int Tabulation(int[][] matrix) {
		int n = matrix.length;
		int[][] Dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			Dp[n - 1][i] = matrix[n - 1][i];
		}
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				int down = matrix[i][j] + Dp[i + 1][j];
				int downleft = (j > 0) ? matrix[i][j] + Dp[i + 1][j - 1] : (int) 1e9;
				int downright = (j < n - 1) ? matrix[i][j] + Dp[i + 1][j + 1] : (int) 1e9;
				int ans = Math.min(down, Math.min(downleft, downright));
				Dp[i][j] = ans;
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, Dp[0][i]);
		}
		return min;
	}

}
