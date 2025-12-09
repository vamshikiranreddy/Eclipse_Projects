package Dynamic_Programming_6_Stocks;

import java.util.*;

public class Best_Time_to_Buy_and_Sell_Stock_Two {
	public static void main(String[] args) {

		int[] prices = { 7, 1, 5, 3, 6, 4 };

		int memoAns = maxProfitMemo(prices);
		System.out.println("Max Profit (Memo): " + memoAns);

		int taboAns = maxProfitTabulation(prices);
		System.out.println("Max Profit (Tabulation): " + taboAns);

		int optAns = maxProfitSpaceOptimized(prices);
		System.out.println("Max Profit (Space Optimized): " + optAns);
	}

	private static int maxProfitSpaceOptimized(int[] prices) {
		int n = prices.length;
		int[] ahead = new int[2];
		ahead[0] = ahead[1] = 0;
		for (int idx = n - 1; idx >= 0; idx--) {
			int[] curr = new int[2];
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					int take = -prices[idx] + ahead[0];
					int nottake = ahead[1];
					curr[buy] = Math.max(nottake, take);
				} else {
					int selling = prices[idx] + ahead[1];
					int notselling = ahead[0];
					curr[buy] = Math.max(notselling, selling);
				}
			}
			ahead = curr;
		}
		return ahead[1];
	}

	private static int maxProfitTabulation(int[] prices) {

		int n = prices.length;
		int[][] Dp = new int[n + 1][2];
		Dp[n][0] = Dp[n][1] = 0;
		for (int idx = n - 1; idx >= 0; idx--) {
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					int take = -prices[idx] + Dp[idx + 1][0];
					int nottake = Dp[idx + 1][1];
					Dp[idx][buy] = Math.max(nottake, take);
				} else {
					int selling = prices[idx] + Dp[idx + 1][1];
					int notselling = Dp[idx + 1][0];
					Dp[idx][buy] = Math.max(notselling, selling);
				}
			}
		}
		return Dp[0][1];
	}

	private static int maxProfitMemo(int[] prices) {
		int n = prices.length;
		int[][] Dp = new int[n][2];
		for (int row[] : Dp)
			Arrays.fill(row, -1);
		return recursion(0, 1, prices, Dp);
	}

	private static int recursion(int idx, int buy, int[] prices, int[][] Dp) {
		// buy == 1 (can buy) || buy == 0 (can't buy already own's a stock
		if (idx == prices.length)
			return 0;
		if (Dp[idx][buy] != -1)
			return Dp[idx][buy];
		if (buy == 1) {
			int take = -prices[idx] + recursion(idx + 1, 0, prices, Dp);
			int nottake = recursion(idx + 1, 1, prices, Dp);
			return Dp[idx][buy] = Math.max(nottake, take);
		} else {
			int selling = prices[idx] + recursion(idx + 1, 1, prices, Dp);
			int notselling = recursion(idx + 1, 0, prices, Dp);
			return Dp[idx][buy] = Math.max(notselling, selling);
		}
	}
}
