import java.util.*;

public class CardTrading {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int k = sc.nextInt();

        //fill in number of cards for each type
        int[] deck = new int[t];
        for (int i = 0; i < n; i++)
            deck[sc.nextInt()-1] += 1;

        //get list of potential profit/loss for each card type
        List<Card> catalogue = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int totalBuy = sc.nextInt() * (2 - deck[i]);
            int totalSell = sc.nextInt() * deck[i];
            catalogue.add(new Card(i+1, totalBuy, totalSell));
        }

        Collections.sort(catalogue);

        //calculate final result
        // -> buy k combos
        // -> sell everything else
        long total = 0;
        for (int i = 0; i < catalogue.size(); i++) {
            Card c = catalogue.get(i);
            total += i < k ? c.totalBuy : c.totalSell;
        }

        System.out.println(total);
    }

    static class Card implements Comparable<Card> {
        
        final long name;
        final long totalBuy;
        final long totalSell;
        final long totalSum;

        Card(long name, long totalBuy, long totalSell) {
            this.name = name;
            this.totalBuy = -totalBuy;    //negative because lose money
            this.totalSell = totalSell;
            this.totalSum = totalSell + totalBuy;
        }

        //arrange by totalSum then totalBuy then totalSell
        public int compareTo(Card c) {
            if (this.totalSum > c.totalSum) return 1;
            if (this.totalSum < c.totalSum) return -1;
            if (this.totalBuy > c.totalBuy) return 1;
            if (this.totalBuy < c.totalBuy) return -1;
            if (this.totalSell > c.totalSell) return 1;
            if (this.totalSell < c.totalSell) return -1;
            return 0;
        }

        public boolean equals(Card c) {
            return this.name == c.name;
        }
    }
}