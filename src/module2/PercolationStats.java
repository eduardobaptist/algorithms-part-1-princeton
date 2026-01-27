package module2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int n;
    private final int trials;
    private Percolation p;
    private final double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Values of n and trials must be greater than 0.");
        }

        this.n = n;
        this.trials = trials;
        results = new double[this.trials];
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / java.lang.Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / java.lang.Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Please send n (grid size) and trials.");
        }

        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        for (int i = 0; i < ps.trials; i++) {
            ps.p = new Percolation(ps.n);

            while (!ps.p.percolates()) {
                ps.p.open(StdRandom.uniformInt(1, ps.n + 1), StdRandom.uniformInt(1, ps.n + 1));
            }

            ps.results[i] = (double) ps.p.numberOfOpenSites() / (ps.n * ps.n);
        }

        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = ["+ps.confidenceLo()+"], ["+ps.confidenceHi()+ "]");
    }

}