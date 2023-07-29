import java.util.Arrays;

public class Percolation {

    private static int Size;
    private static int RowSize;
    private static int ColSize;
    private static int[][] Grid;
    private static WeightedQuickUnionUF uf;

    private static void isInputOutOfRange(int row, int col) {
        if (row > RowSize)
            throw new IllegalArgumentException("Row input is too large");
        if (col > ColSize)
            throw new IllegalArgumentException("Column input is too large");
    }

    // creates n-by-n grid, with all sites initially blocked
    public static int[][] percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Row input is too large");

        int[][] matrix = new int[n][n]; // set matrix of NxN two-dimensional array
        int value = -1; // choose starting value
        for (int[] row : matrix) { // iterate through array and set value
            Arrays.fill(row, value);
        }

        return matrix;
    }

    // opens the site (row, col) if it is not open already
    public static void open(int row, int col) {
        isInputOutOfRange(row, col);

        if (!isOpen(row, col)) {
            int currentRow = (row * Size);
            Grid[row][col] = currentRow + col;

        }
    }

    // is the site (row, col) open?
    public static boolean isOpen(int row, int col) {
        isInputOutOfRange(row, col);
        return Grid[row][col] != -1;
    }

    // connect all sites
    public static void connectAllSites(int row, int col) {
        int[][] Directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] direction : Directions) {
            int RowDir = direction[0];
            int ColDir = direction[1];

            boolean NotOutOfBounds = (col + ColDir) <= ColSize && (col + ColDir) >= 0 && (row + RowDir) <= RowSize && (row + RowDir) >= 0;
            if (NotOutOfBounds) {
                boolean BothSitesAreOpen = isOpen(row, col) && Grid[row + RowDir][col + ColDir] != -1;
                if (BothSitesAreOpen) {
                    boolean SitesAreNotConnected = uf.find(Grid[row][col]) != uf.find(Grid[row + RowDir][col + ColDir]);
                    if (SitesAreNotConnected) {
                        uf.union(Grid[row][col], Grid[row + RowDir][col + ColDir]); // connect the sites
                        Grid[row + RowDir][col + ColDir] = uf.find(Grid[row][col]); // update the grid to the root value
                        connectAllSites(row + RowDir, col + ColDir); // check if full
                    }
                }
            }
        }

    }

    // is the site (row, col) full?
    public static boolean isFull(int row, int col) {
        connectAllSites(row, col);

        for (int i = 0; i <= ColSize; i++) {
            boolean SiteIsOpen = Grid[row][col] != -1 && Grid[0][i] != -1;
            if (SiteIsOpen) {
                boolean SitesAreConnected = uf.find(Grid[row][col]) == uf.find(Grid[0][i]);
                if (SitesAreConnected) {
                    return true;
                }
            }
        }

        return false;
    }

    // returns the number of open sites
    public static int numberOfOpenSites() {
        int TotalOpenSites = 0;
        for (int i = 0; i <= RowSize; i++)
            for (int j = 0; j < ColSize; j++)
                if (isFull(i, j))
                    TotalOpenSites++;

        return TotalOpenSites;
    }

    // does the system percolate?
    public static boolean percolates() {

        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Size = Integer.parseInt(args[0]);
        RowSize = ColSize = Size - 1;
        Grid = percolation(Size);
        uf = new WeightedQuickUnionUF(Size * Size);

        // sample data points
        open(0, 5);
        open(1, 5);
        open(2, 5);
        open(3, 5);
        open(4, 5);
        open(5, 5);
        open(6, 5);
        open(6, 6);
        open(7, 6);

        open(8, 6);
        open(8, 5);
        open(8, 4);

        open(4, 4);
        open(2, 1);
        open(5, 9);
        open(3, 3);
        open(0, 2);
        open(1, 1);
        open(3, 9);
        open(6, 3);
        open(1, 3);
        open(0, 9);
        open(3, 1);


        open(8, 6);
        open(9, 6);

        // first column
        open(0, 0);
        open(1, 0);
        open(2, 0);
        open(3, 0);
        open(4, 0);
        open(5, 0);
        open(6, 0);
        open(6, 0);
        open(7, 0);
        open(8, 0);
        open(9, 0);

        boolean a = isFull(9, 6);
        boolean b = isFull(9, 0);
        boolean c = isFull(9, 4);
        StdOut.println(Grid);
        boolean doesPercolate = uf.find(Grid[9][6]) == uf.find(Grid[0][5]);

//        // StdOut.println(numberOfOpenSites());
//        StdOut.println(Grid);
//        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(Size * Size);
//        uf.union(0, 1);
//        uf.union(0, 99);
//        // Grid[0][0] = 0 + Grid[9][9] = 0
//        boolean k = uf.connected(0, 0);
//
//        int y = uf.find(97);
//        WeightedQuickUnionUF z = uf;
//        open(1, 1);
//        open(2, 1);
//        boolean x = percolates();
    }
}
