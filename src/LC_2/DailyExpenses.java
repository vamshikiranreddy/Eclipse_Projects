package LC_2;

import java.util.*;

public class DailyExpenses {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter The Number Of Entries :- ");
		int n = in.nextInt();
		in.nextLine();
		List<String> weekDays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
				"Sunday");
		Map<String, Map<String, Integer>> DailySummary = new HashMap<>();
		Map<String, Integer> OverallSummary = new TreeMap<>();
		System.out.println("Enter The Day ANd it's Expenses : ");
		for (int i = 0; i < n; i++) {
			String line = in.nextLine().trim();

			String[] parts = line.split("\\s+");
			if (parts.length < 3)
				continue;

			String currDay = parts[0];
			String cat = parts[1];
			int amt = Integer.parseInt(parts[2]);

			DailySummary.putIfAbsent(currDay, new TreeMap<>());
			Map<String, Integer> map = DailySummary.get(currDay);
			map.put(cat, map.getOrDefault(cat, 0) + amt);

			OverallSummary.put(cat, OverallSummary.getOrDefault(cat, 0) + amt);
		}

		for (String day : weekDays) {
			if (DailySummary.containsKey(day)) {
				System.out.println("Day : " + day);
				Map<String, Integer> map = DailySummary.get(day);
				for (Map.Entry<String, Integer> entry : map.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
			}
		}
		System.out.println("Overall Summary : - ");
		for (Map.Entry<String, Integer> entry : OverallSummary.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		in.close();
	}

}
