package Dynamic_Programming_7;
import java.util.*;
public class Printing_LIS {

	public static void main(String[] args) {
		int[] nums = {4, 10, 4, 3, 8, 9};
		List<Integer> result = LIS(nums);
		System.out.println("The LIS :- " + result);
	}

	private static List<Integer> LIS(int[] nums) {
		int n = nums.length;
		int [] Dp = new int[n]; // for longest at every index
		int [] hashing = new int[n]; // for constructing by storing the prev idx of the before and goes on
		Arrays.fill(hashing, -1);
		int maxlen = 0;
		int lastidx = -1;
		for(int idx = 0;idx<n;idx++) {
			for(int prev = 0;prev < idx;prev++) {
				if(nums[idx] > nums[prev] && Dp[idx] < Dp[prev] + 1) {
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
