public class Percolation {

    private static int[][] Grid;
    private static int RowSize;
    private static int ColSize;

    private static void inputOutOfRange(int row, int col) {
        if (row > RowSize)
            throw new IllegalArgumentException("Row input is too large");
        if (col > ColSize)
            throw new IllegalArgumentException("Column input is too large");
    }

    // creates n-by-n grid, with all sites initially blocked
    public static int[][] percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Row input is too large");
        return new int[n][n];
    }

    // opens the site (row, col) if it is not open already
    public static void open(int row, int col) {
        inputOutOfRange(row, col);
        if (!isOpen(row, col)) {
            Grid[row][col] = 1;
        }
    }

    // is the site (row, col) open?
    public static boolean isOpen(int row, int col) {
        inputOutOfRange(row, col);
        return Grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        inputOutOfRange(row, col);
        return Grid[row][col] == 0;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public static boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int Size = Integer.parseInt(args[0]);
        RowSize = ColSize = Size - 1;
        Grid = percolation(Size);

        open(0, 1);
        StdOut.println(Grid);
    }
}
