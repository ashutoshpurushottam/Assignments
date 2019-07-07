import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double STD_DEV_CONST = 1.96;

    private final double resultMean;
    private final double resultStd;
    private final int trials;

    public PercolationStats(int n, int trials) {

        if (n <= 0) throw new IndexOutOfBoundsException();
        if (trials <= 0) throw new IndexOutOfBoundsException();

        this.trials = trials;

        double[] resultsArray = new double[trials];

        for (int i = 0; i < trials; i++) {
            int count = 0;
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                // Keep opening sites unless the system does not percolate
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                while (percolation.isOpen(row, col)) {
                    row = StdRandom.uniform(n) + 1;
                    col = StdRandom.uniform(n) + 1;
                }
                count++;
                percolation.open(row, col);

            }

            resultsArray[i] = (double) count / ((double) n * (double) n);
        }

        resultMean = StdStats.mean(resultsArray);
        resultStd = StdStats.stddev(resultsArray);
    }

    // sample mean of percolation threshold
    public double mean() {
        return resultMean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return resultStd;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return resultMean - STD_DEV_CONST * resultStd / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return resultMean + STD_DEV_CONST * resultStd / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int t = StdIn.readInt();
        PercolationStats testPer = new PercolationStats(n, t);
        StdOut.println("mean = " + testPer.mean());
        StdOut.println("stddev = " + testPer.stddev());
        StdOut.println("95% confidence interval " + testPer.confidenceLo() + ", " + testPer
                .confidenceHi());
    }
}
