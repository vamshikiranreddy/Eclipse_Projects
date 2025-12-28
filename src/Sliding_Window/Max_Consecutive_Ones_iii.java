package Sliding_Window;

public class Max_Consecutive_Ones_iii {
	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		int k = 2;
		int result = findMaxConsecutiveOnes(nums, k);

		System.out.println("Maximum consecutive ones: " + result);

	}

	private static int findMaxConsecutiveOnes(int[] nums, int k) {
		int n = nums.length;
		int start = 0, end = 0;
		int max = -1, numzeroes = 0;
		while (end < n) {
			if (nums[end] == 0) {
				numzeroes++;
			}
			while (numzeroes > k) {
				if (nums[start] == 0) {
					numzeroes--;
				}
				start++;
			}
			max = Math.max(max, end - start + 1);
			end++;
		}
		return max;
	}
}
