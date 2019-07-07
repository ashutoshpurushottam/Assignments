import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final WeightedQuickUnionUF uf;
    private boolean[] opened;
    private int numOpenSites;

    public Percolation(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException();
        }

        // Create n-by-n grid
        n = num;
        uf = new WeightedQuickUnionUF(n * n + 2);
        // all sites are blocked initially
        opened = new boolean[n * n + 2];

        numOpenSites = 0;
    }


    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        int id = index(row, col);

        if (!isOpen(row, col)) {
            numOpenSites++;
            opened[id] = true;

            // left
            int leftIndex = index(row - 1, col);
            if (leftIndex != -1 && isOpen(row - 1, col)) {
                uf.union(id, leftIndex);
            }

            // right
            int rightIndex = index(row + 1, col);
            if (rightIndex != -1 && isOpen(row + 1, col)) {
                uf.union(id, rightIndex);
            }

            // top
            int topIndex = index(row, col - 1);
            if (topIndex != -1 && isOpen(row, col - 1)) {
                uf.union(id, topIndex);
            }

            // bottom
            int bottomIndex = index(row, col + 1);
            if (bottomIndex != -1 && isOpen(row, col + 1)) {
                int q = index(row, col + 1);
                uf.union(id, q);
            }

            if (row == 1) {
                uf.union(0, id);
            }

            if (row == n) {
                uf.union(id, n * n + 1);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        int idx = index(row, col);
        return idx != -1 && opened[idx];
    }


    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        int idx = index(row, col);
        return idx != -1 && uf.connected(0, idx);
    }

    public int numberOfOpenSites() {
        // number of open sites
        return numOpenSites;
    }

    public boolean percolates() {
        // does the system percolate?
        return uf.connected(0, n * n + 1);
    }

    private int index(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            return -1;
        }
        return (row - 1) * n + col;
    }


}
