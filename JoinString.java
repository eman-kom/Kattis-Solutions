import java.util.*;
import java.io.*;

public class JoinString {

    static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int strings = Integer.parseInt(br.readLine());

        //initialise array of strings
        String[] str = new String[strings];
        for (int i = 0; i < strings; i++) {
            str[i] = br.readLine();
        }

        //for the annoying case of having only 1 input
        if (strings == 1) {
            System.out.println(str[0]);
            return;
        }

        int idx = -1;
        HashMap<Integer, ArrayList<Integer>> seq = new HashMap<>();
        for (int i = 0; i < strings-1; i++) {

            String[] ops = br.readLine().split(" ");
            int receive = Integer.parseInt(ops[0])-1;
            int send = Integer.parseInt(ops[1])-1;

            //get relative sequence of numbers
            idx = receive;
            if (seq.putIfAbsent(receive, new ArrayList<>(Arrays.asList(receive, send))) != null) 
                seq.get(receive).add(send);
        }

        //seq.forEach((key, value) -> System.out.println(key + " => " + value));

        ArrayList<Integer> last = seq.get(idx);
        out.append(str[last.get(0)]);

        //start from 1 bcos first element is also key
        //prevents double counting
        for (int i = 1; i < last.size(); i++)
            recur(seq, last.get(i), str);

        System.out.println(out.toString());
    }

    //follow the chain of numbers to print the words
    static void recur(HashMap<Integer, ArrayList<Integer>> seq, int i, String[] str) {
        
        out.append(str[i]);

        if (seq.containsKey(i)) {
            for (int j = 1; j < seq.get(i).size(); j++) {
                recur(seq, seq.get(i).get(j), str);
            }
        }
    }
}