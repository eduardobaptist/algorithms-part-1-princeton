package module2;

import java.util.Arrays;

public class ImprovedQuickUnion {

    private final int[] id;
    private final int[] sz;

    // n is arrays size
    public ImprovedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // here, we weight the trees, so the smaller tree always gets lower when connected to another
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) return;

        if (sz[i] < sz[j]) { // <-- WEIGHTING
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // searching for the root of an element (when the element id in id[] is the same as its value)
    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // <-- PATH COMPRESSION
            i = id[i];
        }

        return i;
    }

    @Override
    public String toString() {
        return Arrays.toString(id);
    }

    public static void main(String[] args) {
        ImprovedQuickUnion qu = new ImprovedQuickUnion(10);

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
