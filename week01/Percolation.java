public class Percolation {

    private static int Size;
    private static int[][] Grid;
    private static WeightedQuickUnionUF uf;
    private static int TotalSitesRemaining;

    private static void isInputOutOfRange(int row, int col) {
        if (row > Size + 1)
            throw new IllegalArgumentException("Row input is too large");
        if (col > Size + 1)
            throw new IllegalArgumentException("Column input is too large");
        if (row <= 0)
            throw new IllegalArgumentException("Row input is too small");
        if (col <= 0)
            throw new IllegalArgumentException("Column input is too small");
    }

    // creates n-by-n grid, with all sites initially blocked
    public static int[][] percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("size must be a non-zero positive integer");

        // initialize grid variables
        Size = n;
        uf = new WeightedQuickUnionUF(n * n);
        TotalSitesRemaining = (n * n);
        Grid = new int[n][n];

        return Grid;
    }

    // opens the site (row, col) if it is not open already
    public static void open(int row, int col) {
        isInputOutOfRange(row, col);

        if (!isOpen(row, col)) {
            int currentRow = ((row - 1) * Size);
            Grid[row - 1][col - 1] = currentRow + col;
            TotalSitesRemaining--;
        }
    }

    // is the site (row, col) open?
    public static boolean isOpen(int row, int col) {
        isInputOutOfRange(row, col);
        return Grid[row - 1][col - 1] != 0;
    }

    // connect all sites
    public static void connectAllSites(int row, int col) {
        int[][] Directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] direction : Directions) {
            int RowDir = direction[0];
            int ColDir = direction[1];

            boolean NotOutOfBounds = (col + ColDir) <= Size && (col + ColDir) >= 1 && (row + RowDir) <= Size && (row + RowDir) >= 1;
            if (NotOutOfBounds) {
                boolean BothSitesAreOpen = isOpen(row, col) && Grid[row - 1 + RowDir][col - 1 + ColDir] != 0;
                if (BothSitesAreOpen) {
                    boolean SitesAreNotConnected = uf.find(Grid[row - 1][col - 1] - 1) != uf.find(Grid[row - 1 + RowDir][col - 1 + ColDir] - 1);
                    if (SitesAreNotConnected) {
                        uf.union(Grid[row - 1][col - 1] - 1, Grid[row - 1 + RowDir][col - 1 + ColDir] - 1); // connect the sites
                        Grid[row - 1 + RowDir][col - 1 + ColDir] = uf.find(Grid[row - 1][col - 1] - 1) + 1; // update the grid to the root value
                        connectAllSites(row + RowDir, col + ColDir); // check if full
                    }
                }
            }
        }

    }

    // is the site (row, col) full?
    public static boolean isFull(int row, int col) {
        isInputOutOfRange(row, col);

        connectAllSites(row, col);
        for (int i = 1; i <= Size; i++) {
            boolean SiteIsOpen = Grid[row - 1][col - 1] != 0 && Grid[Size - 1][i - 1] != 0;
            if (SiteIsOpen) {
                boolean SitesAreConnected = uf.find(Grid[row - 1][col - 1] - 1) == uf.find(Grid[Size - 1][i - 1] - 1);
                if (SitesAreConnected) {
                    return true;
                }
            }
        }

        return false;
    }

    // returns the number of open sites
    public static int numberOfOpenSites() {
        return TotalSitesRemaining;
    }

    // does the system percolate?
    public static boolean percolates() {
        for (int i = 1; i <= Size; i++) {
            if (isFull(1, i)) {
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Size = Integer.parseInt(args[0]);
        Grid = percolation(Size);
    }
}
