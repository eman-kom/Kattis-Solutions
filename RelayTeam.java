import java.util.*;

public class RelayTeam {
	public static void main(String[] args) {

		int i = 0;
		String name;
		double total = 0;
		String[] currRunSeq = new String[4];
		String[] finalRunSeq = new String[4];
		double finalTotal = Double.MAX_VALUE;
		List<Runner> firstList = new ArrayList<>();
		List<Runner> secondList = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		// firstList -> runners first timing.
		// secondList -> runners second or more timing.
		for (int j = 0; j < n; j++) {
			name = sc.next();
			firstList.add(new Runner(name, sc.nextDouble()));
			secondList.add(new Runner(name, sc.nextDouble()));
		}

		Collections.sort(firstList);
		Collections.sort(secondList);

		// Get the first 4 runners from each list since the qn requires 4 unique runners.
		firstList = firstList.subList(0, 4);
		secondList = secondList.subList(0, 4);

		// for each runner in first list:
		//		get 3 fastest unique runners from second list
		//		return the lowest timing from the combinations
		for (Runner r : firstList) {

			currRunSeq[0] = r.name;
			total += r.time;

			for (Runner s : secondList) {

				if (i == 3) break;

				// ensures the runner is not double counted
				if (!r.name.equals(s.name)) {
					total += s.time;
					currRunSeq[i+1] = s.name;
					i++;
				}
			}

			//get fastest time
			if (total < finalTotal) {
				finalTotal = total;
				finalRunSeq = currRunSeq.clone();
			}

			// reset counters
			total = 0;
			i = 0;
		}

		System.out.println(finalTotal);
		for (String s: finalRunSeq) System.out.println(s);
	}

	//class to link runner to time
	static class Runner implements Comparable<Runner> {
		
		final String name;
		final double time;

		Runner(String name, double time) {
			this.name = name;
			this.time = time;
		}

		public int compareTo(Runner r) {
			if (this.time > r.time) return 1;
			if (this.time < r.time) return -1;
			return 0;
		}

		public boolean equals(Runner r) {
			return this.name.equals(r.name);
		}
	}
}