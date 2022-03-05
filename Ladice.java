import java.util.*;
import java.io.*;

class Ladice {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int l = Integer.parseInt(str[1]);

		UnionFind ufds = new UnionFind(l + 1);
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < n; i++) {

			String[] str1 = br.readLine().split(" ");

			int a = Integer.parseInt(str1[0]);
			int b = Integer.parseInt(str1[1]);
			int da = ufds.findSet(a);
			int db = ufds.findSet(b);

			if (da == a) {
				ufds.unionSet(da, db);
				s.append("LADICA\n");
			}

			else if (db == b) {
				ufds.unionSet(db, da);
				s.append("LADICA\n");
			}

			else if (da > 0) {
				ufds.unionSet(da, db);
				s.append("LADICA\n");
			}

			else if (db > 0) {
				ufds.unionSet(db, da);
				s.append("LADICA\n");
			}

			else {
				s.append("SMECE\n");
			}

			//for (int j = 0; j <= l; j++) ufds.findSet(j);
			//System.out.println(a+" "+b+" "+ufds.toString());
		}

		System.out.println(s.toString());
	}

	static class UnionFind {
		
		int[] p;

		public UnionFind(int N) {
			p = new int[N];
			for (int i = 0; i < N; i++) 
				p[i] = i;
		}

		public int findSet(int i) { 
			if (p[i] == i) return i;
			else {
				p[i] = findSet(p[i]);
				return p[i]; 
			} 
		}

		public void unionSet(int from, int to) { 

			if (from != to) 
				p[from] = to;
			else 
				p[to] = 0;
		}

		public String toString() {
			return Arrays.toString(p);
		}
	}
}