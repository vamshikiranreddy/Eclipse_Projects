package Sliding_Window_2;

import java.util.*;

public class Fruit_Into_Baskets {

	public static void main(String[] args) {

		int[] fruits = { 1, 2, 1, 2, 3, 2, 2 };

		System.out.println("Brute Force Answer      : " + bruteForce(fruits));
		System.out.println("Optimal (HashMap)       : " + optimal(fruits));
		System.out.println("Most Optimal (2 vars)   : " + mostOptimal(fruits));
	}

	private static int optimal(int[] fruits) {
		Map<Integer, Integer> freqMap = new HashMap<>();
		int n = fruits.length;
		int left = 0, right = 0, max = -1;
		while (right < n) {
			freqMap.put(fruits[right], freqMap.getOrDefault(fruits[right], 0) + 1);
			if (freqMap.size() > 2) {
				while (freqMap.size() > 2) {
					freqMap.put(fruits[left], freqMap.get(fruits[left]) - 1);
					if (freqMap.get(fruits[left]) == 0) {
						freqMap.remove(fruits[left]);
					}
					left++;
				}
			}
			max = Math.max(max, right - left + 1);
			right++;
		}
		return max;
	}

	private static int mostOptimal(int[] fruits) {
		Map<Integer, Integer> freqMap = new HashMap<>();
		int n = fruits.length;
		int left = 0, right = 0, max = -1;
		while (right < n) {
			freqMap.put(fruits[right], freqMap.getOrDefault(fruits[right], 0) + 1);
			if (freqMap.size() > 2) {

				freqMap.put(fruits[left], freqMap.get(fruits[left]) - 1);
				if (freqMap.get(fruits[left]) == 0) {
					freqMap.remove(fruits[left]);
				}
				left++;

			}
			max = Math.max(max, right - left + 1);
			right++;
		}
		return max;
	}

	// 1️⃣ Brute Force – O(n^2)
	private static int bruteForce(int[] fruits) {
		int n = fruits.length;
		int maxLen = 0;

		for (int i = 0; i < n; i++) {
			Set<Integer> set = new HashSet<>();
			for (int j = i; j < n; j++) {
				set.add(fruits[j]);
				if (set.size() > 2)
					break;
				maxLen = Math.max(maxLen, j - i + 1);
			}
		}
		return maxLen;
	}

}
