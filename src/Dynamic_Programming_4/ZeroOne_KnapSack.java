package Dynamic_Programming_4;

import java.util.*;

public class ZeroOne_KnapSack {

	public static void main(String[] args) {
		int[] wt = { 1, 2, 4, 5 };
		int[] val = { 5, 4, 8, 6 };
		int W = 5; // capacity
		int memoAns = knapSackMemo(W, wt, val);
		System.out.println("Memoization Result: " + memoAns);

		// Call tabulation method
		int tabAns = knapSackTab(W, wt, val);
		System.out.println("Tabulation Result:  " + tabAns);

	}

	private static int knapSackMemo(int w, int[] wt, int[] val) {
		int n = wt.length;
		int[][] Dp = new int[n][w + 1];
		for (int[] rows : Dp)
			Arrays.fill(rows, -1);
		return recursion(n - 1, w, wt, val, Dp);
	}

	private static int recursion(int idx, int w, int[] wt, int[] val, int[][] Dp) {
		if (idx == 0) {
			if (wt[idx] <= w)
				return val[idx];
			else
				return 0;
		}
		if (Dp[idx][w] != -1)
			return Dp[idx][w];
		int nottake = 0 + recursion(idx - 1, w, wt, val, Dp);
		int take = Integer.MIN_VALUE;
		if (wt[idx] <= w) {
			take = val[idx] + recursion(idx - 1, w - wt[idx], wt, val, Dp);
		}
		return Dp[idx][w] = Math.max(take, nottake);
	}

	private static int knapSackTab(int W, int[] wt, int[] val) {
		int n = wt.length;
		int[][] Dp = new int[n][W + 1];
		for (int w = 0; w <= W; w++) {
			if (wt[0] <= W)
				Dp[0][w] = val[0];
		}
		for (int i = 1; i < n; i++) {
			for (int w = 1; w <= W; w++) {
				int nottake = Dp[i - 1][w];
				int take = Integer.MIN_VALUE;
				if (wt[i] <= w) {
					take = val[i] + Dp[i - 1][w - wt[i]];
				}
				Dp[i][w] = Math.max(nottake, take);
			}
		}
		return Dp[n - 1][W];
	}
}
