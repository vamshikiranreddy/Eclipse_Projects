package Dynamic_Programming_5;

public class Longest_Common_SubString {

	public static void main(String[] args) {
		String s1 = "abcjklp";
		String s2 = "zabcklpq";

		// Call Tabulation Method
		int ans1 = lcsTabu(s1, s2);
		System.out.println("Tabulation LCSubstring: " + ans1);

		// Call Space Optimized Method
		int ans2 = lcsSpaceOpt(s1, s2);
		System.out.println("Space Optimized LCSubstring: " + ans2);

	}

	private static int lcsSpaceOpt(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[] prev = new int[n + 1];
		int maxLen = 0;
		for (int i = 1; i <= m; i++) {
			int[] curr = new int[n + 1];
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					curr[j] = 1 + prev[j - 1];
					maxLen = Math.max(maxLen, curr[j]);
				} else {
					curr[j] = 0; // no need of opt1 and opt2 str reducing
				}
			}
			prev = curr;
		}
		return maxLen;
	}

	private static int lcsTabu(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] Dp = new int[m + 1][n + 1];
		int maxLen = 0;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					Dp[i][j] = 1 + Dp[i - 1][j - 1];
					maxLen = Math.max(maxLen, Dp[i][j]);
				} else {
					Dp[i][j] = 0; // no need of opt1 and opt2 str reducing
				}
			}
		}
		return maxLen;
	}

}
