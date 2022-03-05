import java.util.*;

public class AssigningWorkstations {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();

		PriorityQueue<int[]> arrival = new PriorityQueue<>(n, (x, y) -> x[0] - y[0]);

		for (int i = 0; i < n; i++) {
			int arrive = sc.nextInt();
			int depart = arrive + sc.nextInt();
			int[] array = {arrive, depart};
			arrival.add(array);
		}

		int ans = 0;
		PriorityQueue<Integer> free = new PriorityQueue<>(n, (x, y) -> x - y);
		PriorityQueue<Integer> lock = new PriorityQueue<>(n, (x, y) -> x - y);

		while (!arrival.isEmpty()) {

			int[] temp = arrival.poll();

			while (!lock.isEmpty() && lock.peek() <= temp[0]) {
				free.add(lock.poll() + m);
			}

			while (!free.isEmpty() && free.peek() < temp[0]) {
				free.poll();
			}

			if (!free.isEmpty()) {
				free.poll();
				ans++;
			}

			lock.add(temp[1]);
		}

		System.out.println(ans);
	}
}