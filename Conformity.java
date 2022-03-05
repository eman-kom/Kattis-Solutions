import java.util.*;

public class Conformity {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();

		int largest = 0;
		int[] course = new int[5];
		HashMap<String, Integer> freq = new HashMap<>();
		
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < 5; j++)
				course[j] = sc.nextInt();

			Arrays.sort(course);
			String key = Arrays.toString(course);

			if (freq.putIfAbsent(key, 1) != null) 
				freq.put(key, freq.get(key) + 1);

			if (freq.get(key) > largest)
				largest = freq.get(key);
		}

		int sum = 0;
		for (int i : freq.values())
			if (i == largest) sum += i;
		
		System.out.println(sum);
	}
}