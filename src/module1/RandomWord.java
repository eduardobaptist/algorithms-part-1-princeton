package module1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int i = 0;

        /**
         * "Do not store the words in an array or list. Instead, use Knuth’s method: when reading the ith word, select it with probability 1/i
         * to be the champion, replacing the previous champion. After reading all of the words, print the surviving champion."
         */

        while(!StdIn.isEmpty()){
            String str = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / (i + 1.0))) {
                champion = str;
            }
            i++;
        }

        System.out.println(champion);
    }
}
