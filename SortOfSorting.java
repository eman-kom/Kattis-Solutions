import java.util.*;

class SortOfSorting {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();

		Comparator<String> charCompare = (String s1, String s2) -> {
			int firstChar = s1.charAt(0) - s2.charAt(0);
			int secondChar = s1.charAt(1) - s2.charAt(1);
			if (firstChar != 0) return firstChar;
			if (secondChar != 0) return secondChar;
			return 0; 
		};

		while (true) {

			ArrayList<String> stringList = new ArrayList<>();

			for (int i = 0; i < n; i++)
				stringList.add(sc.nextLine());

			Collections.sort(stringList, charCompare);

			for (String s : stringList) 
				System.out.println(s);

			n = sc.nextInt();
			sc.nextLine();
			if (n == 0) break;
			
			System.out.println();
		}
	}
}