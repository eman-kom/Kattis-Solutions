import java.util.*;
import java.io.*;

class Spelling {
	public static void main(String[] args) throws IOException {

		String[] numArr = {"2","22","222","3","33","333","4","44","444",
						   "5","55","555","6","66","666","7","77","777","7777",
						   "8","88","888","9","99","999","9999","0"};

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int chr;
		String num;
		String input;
		StringBuilder output = new StringBuilder();

		for (int i = 1; i < n + 1; i++) {

			input = br.readLine();
			output.append("Case #" + i + ": ");

			for (int j = 0; j < input.length(); j++) {

				chr = (int) input.charAt(j) - 97;
				num = (chr == -65) ? numArr[26] : numArr[chr];
				if (output.charAt(output.length() - 1) == num.charAt(0)) output.append(" ");
				output.append(num);
			}

			if (i != n) output.append("\n");
		}

		System.out.println(output.toString());
	}
}