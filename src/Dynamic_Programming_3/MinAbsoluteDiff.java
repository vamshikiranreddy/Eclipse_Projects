package Dynamic_Programming_3;

import java.util.*;

public class MinAbsoluteDiff {

	public static void main(String[] args) {
		int[] arr = { 3, 1, 4, 2, 3};

		System.out.println("Memoization: " + minimumDifferenceMemo(arr));
		System.out.println("Tabulation:  " + minimumDifferenceTab(arr));
	}

	private static int minimumDifferenceTab(int[] arr) {
		int n = arr.length;
		int totalSum = 0;
		for (int num : arr)
			totalSum += num;
		int[][] Dp = new int[n][totalSum + 1];
		for (int[] rows : Dp)
			Arrays.fill(rows, -1);
		for (int i = 0; i <= totalSum; i++)
			recursion(n - 1, i, arr, Dp);
		int min = Integer.MAX_VALUE;
		for (int s1 = 0; s1 <= totalSum; s1++) {
			if (Dp[n - 1][s1] == 1) {
				int s2 = totalSum - s1;
				int diff = Math.abs(s1 - s2);
				min = Math.min(min, diff);
			}
		}
		return min;
	}

	private static boolean recursion(int idx, int target, int[] arr, int[][] Dp) {
		if (target == 0)
			return true;

		if (idx == 0)
			return arr[0] == target;

		if (Dp[idx][target] != -1)
			return Dp[idx][target] == 1;

		boolean nottake = recursion(idx - 1, target, arr, Dp);

		boolean take = false;
		if (arr[idx] <= target)
			take = recursion(idx - 1, target - arr[idx], arr, Dp);

		boolean ans = take || nottake;

		Dp[idx][target] = ans ? 1 : 0;

		return ans;

	}

	private static int minimumDifferenceMemo(int[] arr) {
		int n = arr.length;

		int total = 0;
		for (int num : arr)
			total += num;

		boolean[][] dp = new boolean[n][total + 1];

		// base case: sum = 0 is always true
		for (int i = 0; i < n; i++)
			dp[i][0] = true;

		// first element
		if (arr[0] <= total)
			dp[0][arr[0]] = true;

		// fill dp
		for (int i = 1; i < n; i++) {
			for (int sum = 1; sum <= total; sum++) {
				boolean notTake = dp[i - 1][sum];
				boolean take = false;
				if (arr[i] <= sum)
					take = dp[i - 1][sum - arr[i]];

				dp[i][sum] = take || notTake;
			}
		}

		int minDiff = Integer.MAX_VALUE;

		// check all possible sums 0 → total
		for (int s = 0; s <= total; s++) {
			if (dp[n - 1][s]) {
				int s2 = total - s;
				minDiff = Math.min(minDiff, Math.abs(s - s2));
			}
		}

		return minDiff;
	}

}
