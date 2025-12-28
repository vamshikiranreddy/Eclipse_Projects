package BST_2;

public class Unique_BSTs {

	public static void main(String[] args) {
		int n = 3;

		Unique_BSTs obj = new Unique_BSTs();
		int ans = obj.numTrees(n);

		System.out.println("Number of Unique BSTs : " + ans);
	}

	public int numTrees(int n) {
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;

		for (int nodes = 2; nodes <= n; nodes++) {
			int total = 0;
			for (int pivot = 1; pivot <= nodes; pivot++) {
				int left = pivot - 1;
				int right = nodes - pivot;
				total += dp[left] * dp[right];
			}
			dp[nodes] = total;
		}
		return dp[n];
	}
}
