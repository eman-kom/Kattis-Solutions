import java.io.*;
import java.util.*;

public class WeakVertices {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder s = new StringBuilder();

		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == -1) break;

			int[][] adj = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] str = br.readLine().split(" ");
				
				for (int j = 0; j < n; j++) {
					adj[i][j] = Integer.parseInt(str[j]);
				}
			}

			int[] visited = new int[n];
			Arrays.fill(visited, 0);

			for (int k = 0; k < n; k++) {
				if (visited[k] == 1) continue;
				visited[k] = 1;
				boolean triangle = false;

				for (int l = 0; l < n; l++) {
					if (adj[k][l] == 1) {
						for (int m = l+1; m < n; m++) {
							if (adj[k][m] == 1 && adj[l][m] == 1) {
								visited[l] = 1;
								visited[m] = 1;
								triangle = true;
								continue;
							}
						}
					}
				}

				if (!triangle) {
					s.append(k + " ");
				}
			}

			s.append("\n");
		}

		System.out.println(s.toString());
	}
}