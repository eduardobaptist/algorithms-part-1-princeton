package module2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int TOP = 0;
    private final int n;
    private int openSites;
    private final boolean[][] matrix;
    private final int bottom;

    private final WeightedQuickUnionUF qu;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Value of n must be greater than 0.");
        }

        int totalSites = n * n + 2;
        this.n = n;

        openSites = 0;
        bottom = totalSites - 1;
        matrix = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = false;
            }
        }

        qu = new WeightedQuickUnionUF(totalSites);
    }

    private int getId(int row, int col) {
        helper(row, col);

        int index = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == row && j == col) {
                    return index;
                }
                index++;
            }
        }
        return index;
    }

    private void helper(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Invalid coordinates.");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        helper(row, col);

        if (isOpen(row, col)) {
            return;
        }

        int r = row - 1;
        int c = col - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == r && j == c) {
                    matrix[i][j] = true;
                    openSites++;
                }
            }
        }

        // has row(s) before
        if (row - 1 >= 1 && isOpen(row - 1, col)) {
            qu.union(getId(row, col), getId(row - 1, col));
        }

        // has row(s) after
        if (row + 1 <= n && isOpen(row + 1, col)) {
            qu.union(getId(row, col), getId(row + 1, col));
        }

        // has col(s) before
        if (col - 1 >= 1 && isOpen(row, col - 1)) {
            qu.union(getId(row, col), getId(row, col - 1));
        }

        // has col(s) after
        if (col + 1 <= n && isOpen(row, col + 1)) {
            qu.union(getId(row, col), getId(row, col + 1));
        }

        if (row == 1) {
            qu.union(getId(row, col), TOP);
        }

        if (row == n) {
            qu.union(getId(row, col), bottom);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        helper(row, col);

        int r = row - 1;
        int c = col - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == r && j == c) {
                    return matrix[i][j];
                }
            }
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        helper(row, col);

        return qu.find(getId(row, col)) == qu.find(TOP);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qu.find(TOP) == qu.find(bottom);
    }
}