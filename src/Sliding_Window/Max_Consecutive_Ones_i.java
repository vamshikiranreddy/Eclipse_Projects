package Sliding_Window;

public class Max_Consecutive_Ones_i {

	public static void main(String[] args) {

		int[] nums = { 1, 1, 0, 1, 1, 1 };
		int result = findMaxConsecutiveOnes(nums);

		System.out.println("Maximum consecutive ones: " + result);
	}

	public static int findMaxConsecutiveOnes(int[] nums) {
		int maxLen = 0;
		int count = 0;

		for (int num : nums) {
			if (num == 1) {
				count++;
				maxLen = Math.max(maxLen, count);
			} else {
				count = 0; // reset when 0 appears
			}
		}
		return maxLen;
	}
}
