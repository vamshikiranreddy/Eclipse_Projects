package Sliding_Window;

public class Max_Consecutive_Ones_ii {

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		int result = findMaxConsecutiveOnes(nums); // atmost 1 zero to be flipped

		System.out.println("Maximum consecutive ones: " + result);

	}

	private static int findMaxConsecutiveOnes(int[] nums) {
		int n = nums.length;
		int start = 0, end = 0;
		int max = -1, numzeroes = 0;
		while (end < n) {
			if (nums[end] == 0) {
				numzeroes++;
			}
			while (numzeroes > 1) {
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
