package Dynamic_Programming_2;

import java.util.*;

public class Triangle_Min_Path {
	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(3, 4));
		triangle.add(Arrays.asList(6, 5, 7));
		triangle.add(Arrays.asList(4, 1, 8, 12));

		int memoResult = Min_Path_M(triangle);
		int tabuResult = Min_Path_T(triangle);

		System.out.println("Minimum Path Sum Using Memoization : " + memoResult);
		System.out.println("Minimum Path Sum Using Tabulation  : " + tabuResult);
	}

	private static int Min_Path_T(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[][] Dp = new int[n][n];
		for (int i = 0; i <= n - 1; i++) {
			Dp[n - 1][i] = triangle.get(n - 1).get(i);
		}
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				int up = triangle.get(i).get(j) + Dp[i + 1][j];
				int diagonal = triangle.get(i).get(j) + Dp[i + 1][j + 1];
				Dp[i][j] = Math.min(up, diagonal);
			}
		}
		return Dp[0][0];
	}

	private static int Min_Path_M(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[][] Dp = new int[n][n];
		for (int[] row : Dp) {
			Arrays.fill(row, -1);
		}
		return Solve(0, 0, triangle, Dp);
	}

	private static int Solve(int i, int j, List<List<Integer>> triangle, int[][] Dp) {
		if (i == triangle.size() - 1)
			return triangle.get(i).get(j);
		if (Dp[i][j] != -1)
			return Dp[i][j];
		int down = triangle.get(i).get(j) + Solve(i + 1, j, triangle, Dp);
		int diagonal = triangle.get(i).get(j) + Solve(i + 1, j + 1, triangle, Dp);
		return Dp[i][j] = Math.min(down, diagonal);
	}

}
