import java.util.*;
import java.io.*;

class GCPC {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] o = br.readLine().split(" ");
		int n = Integer.parseInt(o[0]);
		int m = Integer.parseInt(o[1]);

		AVL avl = new AVL();
		int[][] teamList = new int[n+1][2]; 
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < m; i++) {

			o = br.readLine().split(" ");
			int t = Integer.parseInt(o[0]);
			int p = Integer.parseInt(o[1]);

			if (teamList[t][0] == 0 && teamList[t][1] == 0) {
				avl.insert(t, 1, p);
				teamList[t][0] = 1;
				teamList[t][1] = p;
			}

			else {
				int past_pts = teamList[t][0];
				int past_penalty = teamList[t][1];
				avl.remove(t, past_pts, past_penalty);

				teamList[t][0] += 1;
				teamList[t][1] += p;
				avl.insert(t, teamList[t][0], teamList[t][1]);
			}

			s.append(avl.rank(teamList[1][0], teamList[1][1])+"\n");
		}

		System.out.println(s.toString());
	}
}