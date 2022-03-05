import java.io.*;
import java.util.*;

public class Teque {

	static int size = 2000002;
	static int headstart = size/2;
	static int tailstart = headstart+1;
	static int[] front = new int[size];
	static int[] back = new int[size];
	static int fhead = headstart;
	static int ftail = tailstart;
	static int bhead = headstart;
	static int btail = tailstart;
	static int fsize = 0;
	static int bsize = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int lines = Integer.parseInt(br.readLine());

		for (int i = 0; i < lines; i++) {

			String[] row = br.readLine().split(" ");
			int num = Integer.parseInt(row[1]);

			switch (row[0]) {

				case "push_back":
					back[btail] = num;
					btail++;
					bsize++;
					balance();
					break;

				case "push_front":
					front[fhead] = num;
					fhead--;
					fsize++;
					balance();
					break;

				case "push_middle":
					front[ftail] = num;
					ftail++;
					fsize++;
					balance();
					break;

				case "get":
					out.append((num > fsize-1) ? 
						back[num-fsize+bhead+1] + "\n" : 
						front[num+fhead+1] + "\n");
					break;
			}
		}

		System.out.print(out.toString());
	}

	static void balance() {
		
		//never allow back to be larger than front
		if (bsize > fsize) {
			front[ftail] = back[bhead+1];
			bsize--;
			bhead++;
			fsize++;
			ftail++;
		}

		//only allow front to be 1 size larger than back (aka odd number centres)
		else if (fsize - bsize == 2) {
			back[bhead] = front[ftail-1];
			bsize++;
			bhead--;
			fsize--;
			ftail--;
		}
	}
}