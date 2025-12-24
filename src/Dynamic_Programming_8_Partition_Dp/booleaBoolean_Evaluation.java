package Dynamic_Programming_8_Partition_Dp;

public class booleaBoolean_Evaluation {

	public static void main(String[] args) {
		String exp = "F|T^F"; // Example expression

		// Evaluate the expression and find the number of ways to get the result as True
		int Tways = evaluateExpTAB(exp);
		int Mways = evaluateExpMEMO(exp);
		System.out.println("The total number of ways in Tabulation : " + Tways);
		System.out.println("The total number of ways in Memoization : " + Mways);

	}

	static int MOD = 1000000007;

	private static int evaluateExpTAB(String exp) {
		int n = exp.length();
		int[][][] Dp = new int[n + 1][n + 1][2];

		for (int i = 0; i < n; i += 2) {
			if (exp.charAt(i) == 'T') {
				Dp[i][i][1] = 1;
			} else {
				Dp[i][i][0] = 1;
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= n - 1; j++) {
				long ways = 0;
				for (int k = i + 1; k <= j - 1; k += 2) {
					int LeftTrue = Dp[i][k - 1][1];
					int LeftFalse = Dp[i][k - 1][0];
					int RightTrue = Dp[k + 1][j][1];
					int RightFalse = Dp[k + 1][j][0];
					if (exp.charAt(k) == '&') {
						Dp[i][j][1] = (int) ((ways + (LeftTrue * RightTrue)) % MOD);
						Dp[i][j][0] = (int) ((ways + (LeftFalse * RightFalse) + (LeftFalse * RightTrue)
								+ (RightFalse * LeftTrue)) % MOD);
					} else if (exp.charAt(k) == '|') {
						Dp[i][j][1] = (int) ((ways + (LeftTrue * RightTrue) + (LeftFalse * RightTrue)
								+ (RightFalse * LeftTrue)) % MOD);
						Dp[i][j][0] = (int) ((ways + (LeftFalse * RightFalse)) % MOD);
					} else {
						Dp[i][j][1] = (int) ((ways + (LeftFalse * RightTrue) + (RightFalse * LeftTrue)) % MOD);
						Dp[i][j][0] = (int) ((ways + (LeftFalse * RightFalse) + (LeftTrue * RightTrue)) % MOD);
					}
				}
			}
		}
		return Dp[0][n - 1][1];
	}

	private static int evaluateExpMEMO(String exp) {
		int n = exp.length();
		int[][][] Dp = new int[n + 1][n + 1][2];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				for (int k = 0; k <= 1; k++) {
					Dp[i][j][k] = -1;
				}
			}
		}
		return recursion(0, n - 1, exp, 1, Dp);
	}

	private static int recursion(int i, int j, String exp, int isTrue, int[][][] Dp) {
		if (i > j)
			return 0;
		if (i == j) { // only one char remaining
			if (isTrue == 1)
				return exp.charAt(i) == 'T' ? 1 : 0;
			else
				return exp.charAt(i) == 'F' ? 1 : 0;
		}
		if (Dp[i][j][isTrue] != -1)
			return Dp[i][j][isTrue];
		long ways = 0;
		// Partition
		for (int k = i + 1; k <= j - 1; k += 2) {
			long LeftTrue = recursion(i, k - 1, exp, 1, Dp);
			long LeftFalse = recursion(i, k - 1, exp, 0, Dp);
			long RightTrue = recursion(k + 1, j, exp, 1, Dp);
			long RightFalse = recursion(k + 1, j, exp, 0, Dp);
			if (exp.charAt(k) == '&') {
				if (isTrue == 1) {
					ways = (ways + (LeftTrue * RightTrue)) % MOD;
				} else {
					ways = (ways + (LeftFalse * RightFalse) + (LeftFalse * RightTrue) + (RightFalse * LeftTrue)) % MOD;
				}
			} else if (exp.charAt(k) == '|') {
				if (isTrue == 1) {
					ways = (ways + (LeftTrue * RightTrue) + (LeftFalse * RightTrue) + (RightFalse * LeftTrue)) % MOD;
				} else {
					ways = (ways + (LeftFalse * RightFalse)) % MOD;
				}
			} else {
				if (isTrue == 1) {
					ways = (ways + (LeftFalse * RightTrue) + (RightFalse * LeftTrue)) % MOD;
				} else {
					ways = (ways + (LeftFalse * RightFalse) + (LeftTrue * RightTrue)) % MOD;
				}
			}
		}
		return Dp[i][j][isTrue] = (int) ways % MOD;
	}

}
