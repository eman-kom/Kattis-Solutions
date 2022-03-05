import java.io.*;
import java.util.*;

public class Dominos {

    static boolean[] visited;
    static boolean not_connected;
    static ArrayList<Integer> topo = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < c; i++) {

            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n);
            ArrayList<ArrayList<Integer>> transpose = new ArrayList<>(n);

            for (int j = 0; j < n; j++) {
                adj.add(new ArrayList<Integer>());
                transpose.add(new ArrayList<Integer>());
            }

            for (int j = 0; j < m; j++) {
                String[] xy = br.readLine().split(" ");
                int x = Integer.parseInt(xy[0])-1;
                int y = Integer.parseInt(xy[1])-1;
                transpose.get(y).add(x);
                adj.get(x).add(y);
            }

            topo.clear();
            visited = new boolean[n];
            for (int j = 0; j < n; j++)
                if (!visited[j]) DFS(adj, j);

            int scc = 0;
            visited = new boolean[n];
            Collections.reverse(topo);
            for (int j : topo) {
                if (!visited[j]) {
                    not_connected = true;
                    DFS2(transpose, j, true);
                    if (not_connected) scc++;
                }
            }

            s.append(scc + "\n");
        }

        System.out.println(s.toString());
    }

    public static void DFS(ArrayList<ArrayList<Integer>> adj, int d) {
        
        visited[d] = true;

        for (int j : adj.get(d))
            if (!visited[j]) DFS(adj, j);

        topo.add(d);
    }

    public static void DFS2(ArrayList<ArrayList<Integer>> adj, int d, boolean yes) {
        
        visited[d] = true;

        for (int j : adj.get(d)) {
            if (!visited[j]) 
                DFS2(adj, j, false);

            else if (yes)
                not_connected = false;
        }
    }
}