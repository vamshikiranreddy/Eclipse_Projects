package Dynamic_Programming_4;

import java.util.*;

public class CoinChange {

	public static void main(String[] args) {
		int[] coins = { 1, 2, 5 };
		int amount = 11;

		System.out.println("Memoization Result : " + coinChangeMemo(coins, amount));
		System.out.println("Tabulization Result : " + coinChangeTab(coins, amount));
		System.out.println("Space Optimized Result : " + coinChangeOpt(coins, amount));
	}

	private static int coinChangeTab(int[] coins, int target) {
		int n = coins.length;
		int[][] Dp = new int[n][target + 1];
		// Base Case
		for (int t = 0; t <= target; t++) {
			if (t % coins[0] == 0)
				Dp[0][t] = t / coins[0];
			else
				Dp[0][t] = (int) 1e9;
		}
		for (int idx = 1; idx < n; idx++) {
			for (int t = 0; t <= target; t++) {
				int nottake = Dp[idx - 1][t];
				int take = (int) 1e9;
				if (coins[idx] <= t) {
					take = 1 + Dp[idx][t - coins[idx]];
				}
				Dp[idx][t] = Math.min(nottake, take);
			}
		}
		int ans = Dp[n - 1][target];
		return ans >= 1e9 ? -1 : ans;
	}

	private static int coinChangeMemo(int[] coins, int amount) {
		int n = coins.length;
		int[][] Dp = new int[n][amount + 1];
		for (int[] row : Dp)
			Arrays.fill(row, -1);
		int ans = recursion(n - 1, amount, coins, Dp);
		return ans >= (int) 1e9 ? -1 : ans;
	}

	private static int recursion(int idx, int target, int[] coins, int[][] Dp) {
		if (idx == 0) {
			if (target % coins[idx] == 0)
				return target / coins[idx];
			else
				return (int) 1e9;
		}
		if (Dp[idx][target] != -1)
			return Dp[idx][target];
		int nottake = recursion(idx - 1, target, coins, Dp);
		int take = Integer.MAX_VALUE;
		if (coins[idx] <= target) {
			take = 1 + recursion(idx, target - coins[idx], coins, Dp);
		}
		return Dp[idx][target] = Math.min(nottake, take);
	}

	private static int coinChangeOpt(int[] coins, int target) {
		int n = coins.length;
		int[] prev = new int[target + 1];
		// Base Case
		for (int t = 0; t <= target; t++) {
			if (t % coins[0] == 0)
				prev[t] = t / coins[0];
			else
				prev[t] = (int) 1e9;
		}
		for (int idx = 1; idx < n; idx++) {
			int[] curr = new int[target + 1];
			for (int t = 0; t <= target; t++) {
				int nottake = prev[t];
				int take = (int) 1e9;
				if (coins[idx] <= t) {
					take = 1 + curr[t - coins[idx]];
				}
				curr[t] = Math.min(nottake, take);
			}
			prev = curr;
		}
		int ans = prev[target];
		return ans >= 1e9 ? -1 : ans;
	}

}
