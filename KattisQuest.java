import java.util.*;

public class KattisQuest {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		TreeSet<Node> ts = new TreeSet<>();
		int n = sc.nextInt();
		int count = 1;
		sc.nextLine();

		for (int i = 0; i < n; i++) {
			String[] cmd = sc.nextLine().split(" ");

			if (cmd[0].equals("add")) {
				int gold = Integer.parseInt(cmd[2]);
				int energy = Integer.parseInt(cmd[1]);
				ts.add(new Node(energy, gold, count));
				count++;
			}

			else {
			
				long total_gold = 0;
				Node x = new Node(Integer.parseInt(cmd[1]), 100001, n);

				while (true) {
					//sorted in descending order hence use ceiling instead of floor
					Node quest = ts.ceiling(x);
					if (quest == null) break;
					total_gold += quest.gold;
					x.energy -= quest.energy;
					ts.remove(quest);
				}

				System.out.println(total_gold);
			}
		}
	}

	static class Node implements Comparable<Node> {

		int gold;
		int count;
		int energy;

		public Node(int energy, int gold, int count) {
			this.gold = gold;
			this.count = count;
			this.energy = energy;
		}

		public int compareTo(Node n) {
			
			if (n.energy != this.energy)
				return n.energy - this.energy;

			else if (n.gold != this.gold)
				return n.gold - this.gold;

			else
				return this.count - n.count;
		}
	}
}