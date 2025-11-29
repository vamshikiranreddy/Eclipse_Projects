package Strings_3;

public class Generation_Subsequesces {

	public static void main(String[] args) {
		String s = "abc";
		generate(0, s, new StringBuilder());
	}

	public static void generate(int idx, String s, StringBuilder sb) {
		// Base Case
		if (idx == s.length()) {
			System.out.println(sb.toString());
			return;
		}

		// INCLUDE current character
		sb.append(s.charAt(idx));
		generate(idx + 1, s, sb);

		// BACKTRACK (remove last character)
		sb.deleteCharAt(sb.length() - 1);

		// EXCLUDE current character
		generate(idx + 1, s, sb);
	}
}
