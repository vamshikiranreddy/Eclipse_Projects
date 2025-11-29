package Dynamic_Programming_4;
import java.util.*;
public class RodCutting {

	public static void main(String[] args) {

		int[] price = { 2, 5, 7, 8, 10 }; // price[i] = price of rod length i+1
		int N = 5; // total rod length

		System.out.println("Memoization Result : " + cutRodMemo(price, N));
		System.out.println("Tabulization Result : " + cutRodTab(price, N));
		System.out.println("Space Optimized Result : " + cutRodOpt(price, N));
	}

	private static int cutRodMemo(int[] price, int N) {
		int n = price.length;
		int[][] dp = new int[n][N + 1];

		for (int[] row : dp)
			java.util.Arrays.fill(row, -1);

		return solve(n - 1, N, price, dp);
	}

	private static int solve(int idx, int len, int[] price, int[][] dp) {
		if (idx == 0) {
			return len * price[0]; // can take piece of length 1 repeatedly
		}

		if (dp[idx][len] != -1)
			return dp[idx][len];

		int notTake = solve(idx - 1, len, price, dp);

		int take = Integer.MIN_VALUE;
		int rodLength = idx + 1;

		if (rodLength <= len) {
			take = price[idx] + solve(idx, len - rodLength, price, dp);
		}

		return dp[idx][len] = Math.max(take, notTake);
	}

	private static int cutRodTab(int[] price, int N) {
		int n = price.length;
		int[][] dp = new int[n][N + 1];

		// Base case: using only piece of length 1
		for (int len = 0; len <= N; len++) {
			dp[0][len] = len * price[0];
		}

		for (int idx = 1; idx < n; idx++) {
			for (int len = 0; len <= N; len++) {

				int notTake = dp[idx - 1][len];

				int take = Integer.MIN_VALUE;
				int rodLength = idx + 1;

				if (rodLength <= len) {
					take = price[idx] + dp[idx][len - rodLength]; // unbounded
				}

				dp[idx][len] = Math.max(take, notTake);
			}
		}

		return dp[n - 1][N];
	}

	private static int cutRodOpt(int[] price, int N) {
		int n = price.length;
		int[] dp = new int[N + 1];

		// Base case
		for (int len = 0; len <= N; len++) {
			dp[len] = len * price[0];
		}

		for (int idx = 1; idx < n; idx++) {
			for (int len = 0; len <= N; len++) {

				int rodLength = idx + 1;

				if (rodLength <= len) {
					dp[len] = Math.max(dp[len], price[idx] + dp[len - rodLength]);
				}
			}
		}

		return dp[N];
	}
}
