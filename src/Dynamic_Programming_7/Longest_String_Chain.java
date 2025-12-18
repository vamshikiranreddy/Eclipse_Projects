package Dynamic_Programming_7;

import java.util.*;

public class Longest_String_Chain {

	public static void main(String[] args) {
		String[] words = { "a", "b", "ba", "bca", "bda", "bdca" };

		int lengthOfLongestStringChain = longestStringChain(words);

		System.out.println("The length of the Longest String Chain is: " + lengthOfLongestStringChain);

	}

	private static int longestStringChain(String[] words) {
		int n = words.length;
		int[] Dp = new int[n];
		int maxChainLength = 0;
		Arrays.fill(Dp, 1);
		Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
		for (int idx = 0; idx < n; idx++) {
			for (int prev = 0; prev < idx; prev++) {
				if (Check(words[idx], words[prev]) && Dp[prev] + 1 > Dp[idx]) {
					Dp[idx] = Dp[prev] + 1;
				}
			}
			maxChainLength = Math.max(maxChainLength, Dp[idx]);
		}
		return maxChainLength;
	}

	private static boolean Check(String longer, String shorter) {
		if (longer.length() != shorter.length() + 1)
			return false;
		int i = 0, j = 0;
		while (i < longer.length()) {
			if (j < shorter.length() && longer.charAt(i) == shorter.charAt(j)) {
				i++;
				j++;
			} else {
				i++; // longer extenstion
			}
		}
		if (i == longer.length() && j == shorter.length())
			return true;
		else
			return false;
	}

}
