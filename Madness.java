import java.io.*;
import java.util.*;

public class Madness {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        String[] mn = br.readLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);
        int[][] vault = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                vault[i][j] = Integer.parseInt(row[j]);
            }
        }

        int[] start = {0, 0, 0};
        pq.offer(start);

        int max = 0;
        while (!pq.isEmpty()) {

            int[] node = pq.poll();
            int x = node[0];
            int y = node[1];
            if (node[2] > max) max = node[2];
            if (x == m-1 && y == n-1) break;

            if (!visited[x][y]) {
                visited[x][y] = true;
                if (x-1 > -1 && !visited[x-1][y]) {
                    int[] up = {x-1, y, vault[x-1][y]-vault[x][y]};
                    pq.offer(up);
                }

                if (x+1 < m && !visited[x+1][y]) {
                    int[] down = {x+1, y, vault[x+1][y]-vault[x][y]};
                    pq.offer(down);
                }

                if (y-1 > -1 && !visited[x][y-1]) {
                    int[] left = {x, y-1, vault[x][y-1]-vault[x][y]};
                    pq.offer(left);
                }

                if (y+1 < n && !visited[x][y+1]) {
                    int[] right = {x, y+1, vault[x][y+1]-vault[x][y]};
                    pq.offer(right);
                }
            }
        }

        System.out.println(max);
    }
}