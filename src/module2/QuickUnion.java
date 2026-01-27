package module2;

import java.util.Arrays;

public class QuickUnion {
    private final int[] id;

    // N is id[] size
    public QuickUnion(int N) {
        id = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // on quick union we set the root of p to the root of q, so entire components can get connected more quickly
    public void union(int p, int q) {
        id[root(p)] = root(q);
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // searching for the root of an element (when the element id in id[] is the same as its value)
    public int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }

        return i;
    }

    @Override
    public String toString() {
        return Arrays.toString(id);
    }

    public static void main(String[] args) {
        QuickUnion qu = new QuickUnion(10);

        System.out.println(qu);

        qu.union(1,2);
        System.out.println("union(1, 2): " + qu);

        qu.union(8,7);
        System.out.println("union(8, 7): " + qu);

        qu.union(1,8);
        System.out.println("union(1, 8): " + qu);

        System.out.println("connected(4, 5): " + qu.connected(4,5));
        System.out.println("connected(2, 7): " + qu.connected(2,7));
    }
}