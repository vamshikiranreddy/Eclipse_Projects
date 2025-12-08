package Dynamic_Programming_5;

import java.util.*;

public class WildCard {

	public static void main(String[] args) {
		String s1 = "*a*b";
		String s2 = "adceb";

		boolean memoAns = isMatchMemo(s1, s2);
		System.out.println("Wildcard Match (Memo): " + memoAns);

		boolean taboAns = isMatchTabulation(s1, s2);
		System.out.println("Wildcard Match (Tabulation): " + taboAns);

		boolean optAns = isMatchSpaceOptimized(s1, s2);
		System.out.println("Wildcard Match (Space Optimized): " + optAns);

	}

	public static boolean isMatchSpaceOptimized(String p, String s) {

		int m = p.length();
		int n = s.length();

		boolean[] prev = new boolean[n + 1];
		boolean[] curr = new boolean[n + 1];

		prev[0] = true;

		// First row: pattern starting with '*'
		for (int i = 1; i <= m; i++) {
			curr[0] = prev[0] && p.charAt(i - 1) == '*';

			for (int j = 1; j <= n; j++) {

				char pc = p.charAt(i - 1);
				char sc = s.charAt(j - 1);

				if (pc == sc || pc == '?') {
					curr[j] = prev[j - 1];
				} else if (pc == '*') {
					curr[j] = prev[j] || curr[j - 1];
				} else {
					curr[j] = false;
				}
			}

			prev = curr.clone();
		}

		return prev[n];
	}

	private static boolean isMatchTabulation(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		boolean[][] Dp = new boolean[m + 1][n + 1];

		Dp[0][0] = true;
		for (int j = 1; j <= n; j++)
			Dp[0][j] = false;
		for (int i = 0; i <= m; i++) {
			boolean flag = true;
			for (int idx = 0; idx < i; idx++) {
				if (s1.charAt(idx) != '*') {
					flag = false;
				}
			}
			Dp[i][0] = flag;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1) || s1.charAt(i - 1) == '?') {
					Dp[i][j] = Dp[i - 1][j - 1];
				} else if (s1.charAt(i - 1) == '*') {
					Dp[i][j] = Dp[i - 1][j] || Dp[i][j - 1];
				} else {
					Dp[i][j] = false;
				}
			}
		}
		return Dp[m][n];
	}

	private static boolean isMatchMemo(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] Dp = new int[m][n];
		for (int[] row : Dp)
			Arrays.fill(row, -1);
		return recursion(m - 1, n - 1, s1, s2, Dp);
	}

	private static boolean recursion(int i, int j, String s1, String s2, int[][] Dp) {
		if (i < 0 && j < 0)
			return true;
		if (i < 0 && j >= 0)
			return false;
		if (i >= 0 && j < 0) {
			for (int idx = 0; idx <= i; idx++) {
				if (s1.charAt(idx) != '*')
					return false;
			}
			return true;
		}
		if (Dp[i][j] != -1)
			return Dp[i][j] == 1;

		boolean ans = false;

		if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {
			ans = recursion(i - 1, j - 1, s1, s2, Dp);
		} else if (s1.charAt(i) == '*') {
			ans = recursion(i - 1, j, s1, s2, Dp) || recursion(i, j - 1, s1, s2, Dp);
		} else {
			Dp[i][j] = 0;
		}
		Dp[i][j] = ans ? 1 : 0;
		return ans;
	}

}
