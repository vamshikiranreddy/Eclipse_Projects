package Dynamic_Programming_5;

public class Minimum_Insertions_to_Make_palindrome {

	public static void main(String[] args) {
		String s = "abcaa";
		int lpsTab = lcsTabulation(s);
		int lpsOpt = lcsSpaceOptimized(s);

		System.out.println("LPS (Tabulation)      = " + lpsTab);
		System.out.println("LPS (Space Optimized) = " + lpsOpt);

	}

	private static int lcsSpaceOptimized(String s1) {
		String s2 = new StringBuilder(s1).reverse().toString();
		int m = s1.length();
		int n = s2.length();
		int[] prev = new int[n + 1];
		for (int i = 1; i <= m; i++) {
			int[] curr = new int[m + 1];
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					curr[j] = 1 + prev[j - 1];
				} else {
					curr[j] = Math.max(prev[j], curr[j - 1]);
				}
			}
			prev = curr;
		}
		return n - prev[n];
	}

	private static int lcsTabulation(String s1) {
		String s2 = new StringBuilder(s1).reverse().toString();
		int m = s1.length();
		int n = s2.length();
		int[][] Dp = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					Dp[i][j] = 1 + Dp[i - 1][j - 1];
				} else {
					Dp[i][j] = Math.max(Dp[i - 1][j], Dp[i][j - 1]);
				}
			}
		}
		return n - Dp[m][n];
	}

}
