package Dynamic_Programming_6_Stocks;

import java.util.Arrays;

public class Best_Time_to_Buy_and_Sell_Stock_Four {
	public static void main(String[] args) {

		int[] prices = { 3, 2, 6, 5, 0, 3 };
		int k = 2;

		int memoAns = maxProfitMemo(prices, k);
		System.out.println("Max Profit (Memo): " + memoAns);

		int taboAns = maxProfitTabulation(prices, k);
		System.out.println("Max Profit (Tabulation): " + taboAns);

		int optAns = maxProfitSpaceOptimized(prices, k);
		System.out.println("Max Profit (Space Optimized): " + optAns);
	}

	private static int maxProfitSpaceOptimized(int[] prices, int k) {
		int n = prices.length;

		int[][] ahead = new int[2][k + 1];
		int[][] curr = new int[2][k + 1];

		for (int idx = n - 1; idx >= 0; idx--) {
			for (int buy = 0; buy <= 1; buy++) {
				for (int count = 0; count <= k; count++) {

					if (count == k) {
						curr[buy][count] = 0;
						continue;
					}

					if (buy == 1) { // can buy
						int choose = -prices[idx] + ahead[0][count];
						int skip = ahead[1][count];
						curr[buy][count] = Math.max(choose, skip);
					} else { // can sell
						int sell = prices[idx] + ahead[1][count + 1];
						int skip = ahead[0][count];
						curr[buy][count] = Math.max(sell, skip);
					}
				}
			}
			// move curr → ahead
			for (int b = 0; b <= 1; b++)
				for (int c = 0; c <= k; c++)
					ahead[b][c] = curr[b][c];
		}

		return ahead[1][0];
	}

	private static int maxProfitTabulation(int[] prices, int k) {
		int n = prices.length;
		int[][][] Dp = new int[n + 1][2][k + 1];
		// Base case: dp array is already initialized to 0, covering the base case.
		for (int idx = n - 1; idx >= 0; idx--) {
			for (int buy = 0; buy <= 1; buy++) {
				for (int count = 0; count <= k; count++) {
					if (count == k) {
						Dp[idx][buy][count] = 0;
						continue;
					}
					if (buy == 1) {
						int choose = -prices[idx] + Dp[idx + 1][0][count];
						int skip = Dp[idx + 1][1][count];
						Dp[idx][buy][count] = Math.max(choose, skip);
					} else {
						int sell = prices[idx] + Dp[idx + 1][1][count + 1];
						int skip = Dp[idx + 1][0][count];
						Dp[idx][buy][count] = Math.max(sell, skip);
					}
				}
			}
		}
		return Dp[0][1][0];
	}

	private static int maxProfitMemo(int[] prices, int k) {
		int n = prices.length;
		int[][][] Dp = new int[n][2][k];
		for (int i = 0; i < n; i++) {
			for (int buy = 0; buy <= 1; buy++) {
				Arrays.fill(Dp[i][buy], -1);
			}
		}
		return recursion(0, 1, 0, prices, k, Dp);
	}

	private static int recursion(int idx, int buy, int count, int[] prices, int k, int[][][] Dp) {
		int n = prices.length;
		if (idx == n)
			return 0;
		if (count == k)
			return 0;
		if (Dp[idx][buy][count] != -1)
			return Dp[idx][buy][count];
		if (buy == 1) {
			int choose = -prices[idx] + recursion(idx + 1, 0, count, prices, k, Dp);
			int skip = recursion(idx + 1, 1, count, prices, k, Dp);
			return Dp[idx][buy][count] = Math.max(choose, skip);
		} else {
			int sell = prices[idx] + recursion(idx + 1, 1, count + 1, prices, k, Dp);
			int skip = recursion(idx + 1, 0, count, prices, k, Dp);
			return Dp[idx][buy][count] = Math.max(sell, skip);
		}
	}
}
