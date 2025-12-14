package Dynamic_Programming_6_Stocks;

import java.util.*;

public class Best_Time_to_Buy_and_Sell_Stock_Fee {

	public static void main(String[] args) {
		int[] prices = { 1, 3, 2, 8, 4, 9 };
		int fee = 2;

		int memoAns = maxProfitMemo(prices, fee);
		System.out.println("Max Profit (Memo): " + memoAns);

		int taboAns = maxProfitTabulation(prices, fee);
		System.out.println("Max Profit (Tabulation): " + taboAns);

		int optAns = maxProfitSpaceOptimized(prices, fee);
		System.out.println("Max Profit (Space Optimized): " + optAns);
	}

	private static int maxProfitSpaceOptimized(int[] prices, int fee) {
		int n = prices.length;
		int[] after = new int[2];
		for (int idx = n - 1; idx >= 0; idx--) {
			int[] curr = new int[2];
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					int select = -prices[idx] + after[0];
					int skip = after[1];
					curr[buy] = Math.max(select, skip);
				} else {
					int sell = (prices[idx] - fee) + after[1];
					int skip = after[0];
					curr[buy] = Math.max(sell, skip);
				}
			}
			after = curr;
		}
		return after[1];
	}

	private static int maxProfitTabulation(int[] prices, int fee) {
		int n = prices.length;
		int[][] Dp = new int[n + 1][2];
		for (int idx = n - 1; idx >= 0; idx--) {
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					int select = -prices[idx] + Dp[idx + 1][0];
					int skip = Dp[idx + 1][1];
					Dp[idx][buy] = Math.max(select, skip);
				} else {
					int sell = (prices[idx] - fee) + Dp[idx + 1][1];
					int skip = Dp[idx + 1][0];
					Dp[idx][buy] = Math.max(sell, skip);
				}
			}
		}
		return Dp[0][1];
	}

	private static int maxProfitMemo(int[] prices, int fee) {
		int n = prices.length;
		int[][] Dp = new int[n][2];
		for (int[] row : Dp)
			Arrays.fill(row, -1);
		return recursion(0, 1, prices, fee, Dp);
	}

	private static int recursion(int idx, int buy, int[] prices, int fee, int[][] Dp) {
		int n = prices.length;
		if (idx == n)
			return 0;
		if (Dp[idx][buy] != -1)
			return Dp[idx][buy];
		if (buy == 1) {
			int select = -prices[idx] + recursion(idx + 1, 0, prices, fee, Dp);
			int skip = recursion(idx + 1, 1, prices, fee, Dp);
			return Dp[idx][buy] = Math.max(select, skip);
		} else {
			int sell = (prices[idx] - fee) + recursion(idx + 1, 1, prices, fee, Dp);
			int skip = recursion(idx + 1, 0, prices, fee, Dp);
			return Dp[idx][buy] = Math.max(sell, skip);
		}
	}

}
