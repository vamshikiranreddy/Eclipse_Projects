package Arrays;

import java.util.Arrays;

public class Dutch_National_Flag {

	public static void main(String[] args) {
		int[] nums = { 2, 0, 2, 1, 1, 0 };

		sortColors(nums);

		System.out.println(Arrays.toString(nums));
	}

	// Dutch National Flag Algorithm
	public static void sortColors(int[] nums) {
		int low = 0, mid = 0, high = nums.length - 1;

		while (mid <= high) {
			if (nums[mid] == 0) {
				swap(nums, low++, mid++);
			} else if (nums[mid] == 1) {
				mid++;
			} else { // nums[mid] == 2
				swap(nums, mid, high--);
			}
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
