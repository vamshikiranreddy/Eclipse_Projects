package Dynamic_Programming_5;

import java.util.*;

public class Distinct_Subsequences {
	public static void main(String[] args) {

		String s = "babgbag";
		String t = "bag";

		int memoAns = numDistinctMemo(s, t);
		System.out.println("Distinct Subsequences (Memo): " + memoAns);

		int taboAns = numDistinctTabo(s, t);
		System.out.println("Distinct Subsequences (Tabulation): " + taboAns);

		int optAns = numDistinctSpaceOpt(s, t);
		System.out.println("Distinct Subsequences (Space Optimized): " + optAns);
	}

	private static int numDistinctSpaceOpt(String s, String t) {
		int m = s.length();
		int n = t.length();

		int[] prev = new int[n + 1];
		prev[0] = 1; // empty t can always be formed

		for (int i = 1; i <= m; i++) {
			int[] curr = new int[n + 1];
			curr[0] = 1; // empty t

			for (int j = 1; j <= n; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					curr[j] = prev[j - 1] + prev[j];
				} else {
					curr[j] = prev[j];
				}
			}
			prev = curr;
		}
		return prev[n];
	}

	private static int numDistinctTabo(String s, String t) {
		int m = s.length();
		int n = t.length();
		int[][] Dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++)
			Dp[i][0] = 1;// if string 2 is empty
		for (int i = 1; i <= n; i++)
			Dp[0][i] = 0;// if string 1 is empty
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					Dp[i][j] = Dp[i - 1][j - 1] + Dp[i - 1][j];
				} else {
					Dp[i][j] = Dp[i - 1][j];
				}
			}
		}
		return Dp[m][n];
	}

	private static int numDistinctMemo(String s, String t) {
		int m = s.length();
		int n = t.length();
		int[][] Dp = new int[m + 1][n + 1];
		for (int[] row : Dp)
			Arrays.fill(row, -1);
		return recursion(m - 1, n - 1, s, t, Dp);
	}

	private static int recursion(int i, int j, String s, String t, int[][] Dp) {
		if (j < 0)
			return 1;
		if (i < 0)
			return 0;
		if (Dp[i][j] != -1)
			return Dp[i][j];
		if (s.charAt(i) == t.charAt(j)) {
			return Dp[i][j] = recursion(i - 1, j - 1, s, t, Dp) + recursion(i - 1, j, s, t, Dp);
		}
		return Dp[i][j] = recursion(i - 1, j, s, t, Dp);
	}

}
