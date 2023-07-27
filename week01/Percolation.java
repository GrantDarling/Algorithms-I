import java.util.Arrays;

public class Percolation {

    private static int Size;
    private static int RowSize;
    private static int ColSize;
    private static int[][] Grid;
    private static WeightedQuickUnionUF uf;
    private static int advancedUpwards = 0;

    private static void inputOutOfRange(int row, int col) {
        if (row > RowSize)
            throw new IllegalArgumentException("Row input is too large");
        if (col > ColSize)
            throw new IllegalArgumentException("Column input is too large");
    }

    //private static void

    // creates n-by-n grid, with all sites initially blocked
    public static int[][] percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Row input is too large");

        // set matrix of NxN two-dimensional array
        int[][] matrix = new int[n][n];
        // choose starting value
        int value = -1;
        // iterate through array and set value
        for (int[] row : matrix) {
            Arrays.fill(row, value);
        }

        return matrix;
    }

    // opens the site (row, col) if it is not open already
    public static void open(int row, int col) {
        //inputOutOfRange(row, col);

        if (!isOpen(row, col)) {
            int currentRow = (row * Size);
            Grid[row][col] = currentRow + col;

        }
    }

    // is the site (row, col) open?
    public static boolean isOpen(int row, int col) {
        // inputOutOfRange(row, col);
        return Grid[row][col] != -1;
    }

    // is the site (row, col) full?
    public static boolean isFull(int row, int col) {

// && col + 1 <= ColSize
        if (col + 1 <= ColSize && isOpen(row, col) && Grid[row][col + 1] != -1 && uf.find(Grid[row][col]) != uf.find(Grid[row][col + 1])) {
            uf.union(Grid[row][col], Grid[row][col + 1]);
            Grid[row][col + 1] = uf.find(Grid[row][col]);
            isFull(row, col + 1);
        }
// && col - 1 >= 0
        if (col - 1 >= 0 && isOpen(row, col) && Grid[row][col - 1] != -1 && uf.find(Grid[row][col]) != uf.find(Grid[row][col - 1])) {
            uf.union(Grid[row][col], Grid[row][col - 1]);
            Grid[row][col - 1] = uf.find(Grid[row][col]);
            isFull(row, col - 1);
        }
// && (row - 1) >= 0
        //  !!! these should eventually be checked inside isOpen
        if ((row - 1) >= 0 && isOpen(row, col) && Grid[row - 1][col] != -1 && uf.find(Grid[row][col]) != uf.find(Grid[row - 1][col])) {
            uf.union(Grid[row][col], Grid[row - 1][col]);
            Grid[row - 1][col] = uf.find(Grid[row][col]);
            isFull(row - 1, col);
        }
// && row + 1 <= RowSize
        if (row + 1 <= RowSize && isOpen(row, col) && Grid[row + 1][col] != -1 && uf.find(Grid[row][col]) != uf.find(Grid[row + 1][col])) {
            uf.union(Grid[row][col], Grid[row + 1][col]);
            Grid[row + 1][col] = uf.find(Grid[row][col]);
            isFull(row + 1, col);
        }

        return false;
    }

    // returns the number of open sites
    public static int numberOfOpenSites() {
        int TotalOpenSites = 0;
        for (int i = 0; i <= RowSize; i++)
            for (int j = 0; j <= ColSize; j++)
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

        // isFull(9, 6);
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
