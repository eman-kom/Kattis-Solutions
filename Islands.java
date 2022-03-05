import java.io.*;
import java.util.*;

public class Islands {

	static int count = 0;
	static String[][] grid;
	static int[][] visited;
	static LinkedList<int[]> queue;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rc = br.readLine().split(" ");
		int r = Integer.parseInt(rc[0]);
		int c = Integer.parseInt(rc[1]);
		grid = new String[r][c];
		visited = new int[r][c];

		for (int i = 0; i < r; i++)
			grid[i] = br.readLine().split("");

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (visited[i][j] == 0 && grid[i][j].equals("L")) {
					BFS(i, j, r, c);
					count++;
				}
			}
		}

		System.out.println(count);
	}

	static void BFS(int i, int j, int r, int c) {
		
		queue = new LinkedList<>();
		int[] start = {i, j};
		queue.add(start);

		while (!queue.isEmpty()) {
			int[] cur = queue.remove();
			int x = cur[0];
			int y = cur[1];

			if (x-1 > -1) visit(x-1, y); //up
			if (x+1 <  r) visit(x+1, y); //down
			if (y-1 > -1) visit(x, y-1); //left
			if (y+1 <  c) visit(x, y+1); //right
		}
	}

	static void visit(int x, int y) {
		if (visited[x][y] == 0) {
			visited[x][y] = 1;
			if (grid[x][y].equals("L") || grid[x][y].equals("C")) {
				int[] xy = {x, y};
				queue.add(xy);
			}
		}
	}
}