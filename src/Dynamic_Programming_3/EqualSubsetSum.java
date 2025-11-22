package Dynamic_Programming_3;

import java.util.*;

public class EqualSubsetSum {

    static int[][] memo;

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2};

        System.out.println("Memoization: " + canPartitionMemo(arr));
        System.out.println("Tabulation:  " + canPartitionTab(arr));
    }

    // ---------------- EQUAL SUBSET SUM USING MEMO ----------------

    public static boolean canPartitionMemo(int[] arr) {
        int sum = 0;
        for (int x : arr) sum += x;

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        memo = new int[arr.length][target + 1];

        for (int i = 0; i < arr.length; i++)
            Arrays.fill(memo[i], -1);

        return solve(arr.length - 1, target, arr);
    }

    private static boolean solve(int idx, int target, int[] arr) {
        if (target == 0) return true;
        if (idx == 0) return arr[0] == target;

        if (memo[idx][target] != -1)
            return memo[idx][target] == 1;

        boolean notTake = solve(idx - 1, target, arr);

        boolean take = false;
        if (arr[idx] <= target)
            take = solve(idx - 1, target - arr[idx], arr);

        boolean ans = take || notTake;

        memo[idx][target] = ans ? 1 : 0;

        return ans;
    }

    // ---------------- EQUAL SUBSET SUM USING TABULATION ----------------

    public static boolean canPartitionTab(int[] arr) {
        int sum = 0;
        for (int x : arr) sum += x;

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        int n = arr.length;

        boolean[][] dp = new boolean[n][target + 1];

        // Base case: target = 0
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // Base case: first element
        if (arr[0] <= target)
            dp[0][arr[0]] = true;

        // Fill DP table
        for (int idx = 1; idx < n; idx++) {
            for (int t = 1; t <= target; t++) {

                boolean notTake = dp[idx - 1][t];

                boolean take = false;
                if (arr[idx] <= t)
                    take = dp[idx - 1][t - arr[idx]];

                dp[idx][t] = take || notTake;
            }
        }

        return dp[n - 1][target];
    }
}
