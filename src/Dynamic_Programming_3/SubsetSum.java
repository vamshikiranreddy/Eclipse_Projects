package Dynamic_Programming_3;

import java.util.*;

public class SubsetSum {

    public static void main(String[] args) {
        int[] arr = { 3, 1, 4, 2 };
        int k = 6;

        System.out.println("Memoization: " + subsetSum(arr, k));
        System.out.println("Tabulation:  " + subsetSumTab(arr, k));
    }

    // ------------------ MEMOIZATION -----------------------

    static int Dp[][];

    private static boolean subsetSum(int[] arr, int k) {
        int n = arr.length;
        Dp = new int[n][k + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(Dp[i], -1);
        return recursion(n - 1, k, arr);
    }

    private static boolean recursion(int idx, int target, int[] arr) {

        if (target == 0)
            return true;

        if (idx == 0)
            return arr[0] == target;

        if (Dp[idx][target] != -1)
            return Dp[idx][target] == 1;

        boolean nottake = recursion(idx - 1, target, arr);

        boolean take = false;
        if (arr[idx] <= target)
            take = recursion(idx - 1, target - arr[idx], arr);

        boolean ans = take || nottake;

        Dp[idx][target] = ans ? 1 : 0;

        return ans;
    }

    // ------------------ TABULATION -----------------------

    private static boolean subsetSumTab(int[] arr, int k) {
        int n = arr.length;

        boolean[][] dp = new boolean[n][k + 1];

        // Base case: sum = 0 is always possible
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // First element initialization
        if (arr[0] <= k)
            dp[0][arr[0]] = true;

        // Fill the DP table
        for (int i = 1; i < n; i++) {
            for (int target = 0; target <= k; target++) {

                boolean notTake = dp[i - 1][target];

                boolean take = false;
                if (arr[i] <= target) {
                    take = dp[i - 1][target - arr[i]];
                }

                dp[i][target] = take || notTake;
            }
        }

        return dp[n - 1][k];
    }
}
