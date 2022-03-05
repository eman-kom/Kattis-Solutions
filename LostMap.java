import java.io.*;
import java.util.*;

public class LostMap {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder s = new StringBuilder();
	    int[] p = new int[n];

		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
			if (x[2] != y[2]) return x[2] - y[2];
			if (x[0] != y[0]) return x[0] - y[0];
			else return x[1] - y[1];
		});
		
		for (int i = 0; i < n-1; i++) {
			String[] line = br.readLine().split(" ");

			for (int j = i+1; j < n; j++) {
				int[] arr = {i+1, j+1, Integer.parseInt(line[j])};
				pq.add(arr);
			}
		}

	    for (int i = 0; i < n; i++)
			p[i] = i;

		while (!pq.isEmpty()) {
			int[] arr = pq.poll();
			int u = arr[0];
			int v = arr[1];
			int find_u = find(p, u-1);
			int find_v = find(p, v-1);

			if (find_u != find_v) {
				p[find_u] = find_v;
				s.append(u + " " + v + "\n");
			}
		}

		System.out.println(s.toString());
	}

	static int find(int[] p, int i) {
		if (p[i] == i) return i;
		p[i] = find(p, p[i]);
		return p[i]; 
	}
}