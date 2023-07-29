public class Percolation {

    private static int Size;
    private static int RowSize;
    private static int ColSize;
    private static int[][] Grid;
    private static WeightedQuickUnionUF uf;
    private static int TotalSitesRemaining;

    private static void isInputOutOfRange(int row, int col) {
        if (row > Size)
            throw new IllegalArgumentException("Row input is too large");
        if (col > Size)
            throw new IllegalArgumentException("Column input is too large");
    }

    // creates n-by-n grid, with all sites initially blocked
    public static int[][] percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("size must be a non-zero positive integer");

        // initialize grid variables
        RowSize = ColSize = Size - 1;
        uf = new WeightedQuickUnionUF(Size * Size);
        TotalSitesRemaining = (Size * Size);

        return new int[n][n];
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
                        //uf.union(0, 1);
                        Grid[row - 1 + RowDir][col - 1 + ColDir] = uf.find(Grid[row - 1][col - 1] - 1) + 1; // update the grid to the root value
                        connectAllSites(row + RowDir, col + ColDir); // check if full
                    }
                }
            }
        }

    }

    // is the site (row, col) full?
    public static boolean isFull(int row, int col) {
        connectAllSites(row, col);
        for (int i = 1; i <= Size; i++) {
            boolean SiteIsOpen = Grid[row - 1][col - 1] != 0 && Grid[0][i - 1] != 0;
            if (SiteIsOpen) {
                boolean SitesAreConnected = uf.find(Grid[row - 1][col - 1] - 1) == uf.find(Grid[0][i - 1] - 1);
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
            if (isFull(10, i)) {
                return true;
            }
        }
        return false;
    }

    private static void setGrid() {
        // sample data points
//        open(1, 5);
//        open(2, 5);
//        open(3, 5);
//        open(4, 5);
//        open(5, 5);
//        open(6, 5);
//        open(6, 6);
//        open(7, 6);
//        open(8, 6);

//        open(8, 6);
//        open(8, 5);
//        open(8, 4);

//        open(4, 4);
//        open(2, 1);
//        open(5, 9);
//        open(3, 3);
//        open(1, 1);
//        open(3, 9);
//        open(6, 3);
//        open(1, 3);
//        open(0, 9);
//        open(3, 1);
//
//
//        open(8, 6);
        open(10, 1);
        open(10, 2);
        open(10, 4);
        open(10, 6);
        open(10, 10);

        open(9, 4);
        open(10, 6);
        open(9, 10);

        // first column
        open(1, 10);
        open(2, 2);
        open(2, 10);
        open(3, 10);
        open(4, 10);

        open(10, 7);
        open(10, 7);
        open(10, 7);
        open(9, 6);
        open(9, 8);
        open(4, 6);


        open(3, 2);
        open(10, 1);
        open(5, 10);
        open(6, 10);
        open(6, 10);
        open(7, 10);
        open(8, 10);
        open(9, 10);

        open(1, 3);
    }

    // test client (optional)
    public static void main(String[] args) {
        Size = Integer.parseInt(args[0]);
        Grid = percolation(Size);
        setGrid();

        boolean x = percolates();
        int y = numberOfOpenSites();
        StdOut.println(Grid);

    }
}
