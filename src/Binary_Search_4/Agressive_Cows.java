package Binary_Search_4;

import java.util.*;

public class Agressive_Cows {

	public static void main(String[] args) {
		int[] stalls = { 1, 3, 8, 4, 9 };
		int cows = 3;
		int maxDist = BinarySearch(stalls, cows);
		System.out.println("Largest minimum distance = " + maxDist);

	}

	private static int BinarySearch(int[] stalls, int cows) {
		int n = stalls.length;
		if (n < 2)
			return -1;
		Arrays.sort(stalls);
		int low = 1;
		int high = stalls[n-1] - stalls[0];
		while(low <= high) {
			int mid = low + (high - low) / 2;
			if(CanCompute(stalls,cows,mid)){
				low = mid + 1;
			}else {
				high = mid - 1;
			}
		}
		return high;//for max
	}

	private static boolean CanCompute(int[] stalls, int cows, int mid) {
		int countCows = 1;
		int last = stalls[0];
		for(int i = 1;i<stalls.length;i++) {
			if(stalls[i] - last >= mid) {
				countCows++;
				last = stalls[i];
			}
		}
		return countCows >= cows ? true : false;
	}

}
