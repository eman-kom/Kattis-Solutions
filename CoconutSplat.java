import java.util.*;

public class CoconutSplat {
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int syllables = sc.nextInt();
		int players = sc.nextInt();

		// initialise all players
		ArrayList<Player> playerList = new ArrayList<>();
		for (int i = 1; i < players+1; i++)
			playerList.add(new Player(i));


		int offset = 0;
		while (playerList.size() != 1) {

			offset = (offset - 1 + syllables) % playerList.size();
			Player p = playerList.get(offset);
			
			switch (p.state) {

				case 0:	// folded -> fist
					p.changeState();
					playerList.add(offset+1, p.split());
					break;

				case 1: // fist -> palm down
					p.changeState();
					offset++;
					break;

				case 2: // palm down -> behind back
					playerList.remove(p);
					break;
			}
		}

		System.out.println(playerList.get(0).name);
	}

	static class Player {

		int name;
		int state;

		Player(int name) {
			this.name = name;
			this.state = 0;
		}

		void changeState() {
			this.state++;
		}

		Player split() {
			Player p = new Player(this.name);
			p.changeState();
			return p;
		}
	}
}