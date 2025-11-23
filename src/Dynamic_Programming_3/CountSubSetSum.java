package Dynamic_Programming_3;

import java.util.*;

public class CountSubSetSum {
	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 3 };
		int sum = 6;

		System.out.println("Memoization: " + countSubsetsMemo(arr, sum));
		System.out.println("Tabulation: " + countSubsetsTab(arr, sum));

	}

	private static int countSubsetsTab(int[] nums, int target) {
		int n = nums.length;
		int[][] Dp = new int[n][target + 1];
		for (int i = 0; i < n; i++)
			Dp[i][0] = 1;
		Dp[0][nums[0]] = 1;
		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= target; j++) {
				int nottake = Dp[i - 1][j];
				int take = 0;
				if (nums[i] <= j) {
					take = Dp[i - 1][j - nums[i]];
				}
				Dp[i][j] = take + nottake;
			}
		}
		return Dp[n - 1][target];
	}

	private static int countSubsetsMemo(int[] nums, int sum) {
		int n = nums.length;
		int[][] Dp = new int[n][sum + 1];
		for (int[] rows : Dp)
			Arrays.fill(rows, -1);
		return recursion(0, sum, nums, Dp);
	}

	private static int recursion(int idx, int target, int[] nums, int[][] Dp) {
		int n = nums.length;
		if (idx == n) {
			return target == 0 ? 1 : 0;
		}
		if (Dp[idx][target] != -1)
			return Dp[idx][target];
		int nottake = recursion(idx + 1, target, nums, Dp);
		int take = 0;
		if (nums[idx] <= target) {
			take = recursion(idx + 1, target - nums[idx], nums, Dp);
		}
		return Dp[idx][target] = take + nottake;
	}
}
