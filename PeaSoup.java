import java.util.*;

public class PeaSoup {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		boolean hasSoup = false;
		boolean hasCake = false;
		String name = "";

		for (int i = 0; i < count; i++) {

			int menu = sc.nextInt();
			sc.nextLine();
			name = sc.nextLine();

			for (int j = 0; j < menu; j++) {

				String food = sc.nextLine();

				if (food.equals("pea soup")) hasSoup = true;
				if (food.equals("pancakes")) hasCake = true;
				if (hasSoup && hasCake) break;

			}

			if (hasSoup && hasCake) break;
			hasSoup = false;
			hasCake = false;
		}

		out = hasSoup && hasCake ? name : "Anywhere is fine I guess";
		System.out.println(out);
	}
}