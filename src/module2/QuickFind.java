package module2;

import java.util.Arrays;

public class QuickFind {

    private final int[] id;

    // N is id[] size
    public QuickFind(int N) {
        id = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }

        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public String toString() {
        return Arrays.toString(id);
    }

    public static void main(String[] args) {
        QuickFind qf = new QuickFind(10);

        System.out.println(qf);

        qf.union(1,2);
        System.out.println("union(1, 2): " + qf);

        qf.union(8,7);
        System.out.println("union(8, 7): " + qf);

        qf.union(1,8);
        System.out.println("union(1, 8): " + qf);

        System.out.println("connected(4, 5): " + qf.connected(4,5));
        System.out.println("connected(2, 7): " + qf.connected(2,7));
    }
}