package Dynamic_Programming_5;

public class Shortest_Common_SuperSequence {

	public static void main(String[] args) {
		String w1 = "mno";
		String w2 = "nop";

		int tab = ScsTabulation(w1, w2);
		int opt = ScsSpaceOptimized(w1, w2);
		String scs = Printing(w1, w2);

		System.out.println("The Shortest Common SuperSequence is : (Tabulation) = " + tab);
		System.out.println("The Shortest Common SuperSequence is : (Space Optimized) = " + opt);
		System.out.println("The Shortest Common SuperSequence is :  " + scs);

	}

	private static String Printing(String w1, String w2) {
		int m = w1.length();
		int n = w2.length();
		int[][] Dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
					Dp[i][j] = 1 + Dp[i - 1][j - 1];
				} else {
					Dp[i][j] = Math.max(Dp[i - 1][j], Dp[i][j - 1]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int i = m, j = n;
		while (i > 0 && j > 0) {
			if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
				sb.append(w1.charAt(i - 1));
				i--;
				j--;
			} else if (Dp[i - 1][j] > Dp[i][j - 1]) {
				sb.append(w1.charAt(i - 1));
				i--;
			} else {
				sb.append(w2.charAt(j - 1));
				j--;
			}
		}
		while (i > 0) {
			sb.append(w1.charAt(i - 1));
			i--;
		}
		while (j > 0) {
			sb.append(w2.charAt(j - 1));
			j--;
		}
		return sb.reverse().toString();
	}

	private static int ScsSpaceOptimized(String w1, String w2) {
		int m = w1.length();
		int n = w2.length();
		int[] prev = new int[n + 1];

		for (int i = 1; i <= m; i++) {
			int[] curr = new int[n + 1];
			for (int j = 1; j <= n; j++) {
				if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
					curr[j] = 1 + prev[j - 1];
				} else {
					curr[j] = Math.max(prev[j], curr[j - 1]);
				}
			}
			prev = curr;
		}
		int lcs = prev[n];
		return m + n - lcs;
	}

	private static int ScsTabulation(String w1, String w2) {
		int m = w1.length();
		int n = w2.length();
		int[][] Dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
					Dp[i][j] = 1 + Dp[i - 1][j - 1];
				} else {
					Dp[i][j] = Math.max(Dp[i - 1][j], Dp[i][j - 1]);
				}
			}
		}
		int lcs = Dp[m][n];
		return m + n - lcs;
	}

}
