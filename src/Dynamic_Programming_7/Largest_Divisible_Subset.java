package Dynamic_Programming_7;

import java.util.*;

public class Largest_Divisible_Subset {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 4, 8 };
		List<Integer> result = LDS(nums);
		System.out.println("The  Largest Divisible Subset:- " + result);

	}

	private static List<Integer> LDS(int[] nums) {
		int n = nums.length;
		int [] Dp = new int[n]; // for longest at every index
		int [] hashing = new int[n]; // for constructing by storing the prev idx of the before and goes on
		Arrays.fill(Dp, 1);
		Arrays.fill(hashing, -1);
		Arrays.sort(nums);
		int maxlen = 0;
		int lastidx = -1;
		for(int idx = 0;idx<n;idx++) {
			for(int prev = 0;prev < idx;prev++) {
				if(nums[idx] % nums[prev] == 0 && Dp[idx] < Dp[prev] + 1) {
					Dp[idx] = Dp[prev] + 1;
					hashing[idx] = prev; // parent
				}
			}
			if(Dp[idx] > maxlen) {
				maxlen = Dp[idx];
				lastidx = idx;
			}
		}
		List<Integer> result = new ArrayList<>();
		while(lastidx != -1) {
			result.add(nums[lastidx]);
			lastidx = hashing[lastidx];
		}
		Collections.reverse(result);
		return result;
	}

}
