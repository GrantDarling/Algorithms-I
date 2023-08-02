public class PercolationStats {
    private static int Size;
    private static int Trials;
    private static int CurrentTrial = 0;
    private static int[] RandomizedSites;
    private static int nextClosedSite = 0;
    private static double[] PercolationThreshold;

    public static void openNextClosedSite() {
        int RandomSite = 0;
        if (nextClosedSite < (Size * Size)) {
            RandomSite = RandomizedSites[nextClosedSite];
            nextClosedSite++;
        }


        int RandomSiteRemainder = RandomSite;

        int RandomRow = 0;
        int RandomCol;

        while (RandomSiteRemainder > Size) {
            RandomSiteRemainder -= Size;
            RandomRow++;
        }

        RandomRow += 1;
        if (RandomSiteRemainder == 0) {
            RandomCol = Size;
        } else {
            RandomCol = RandomSiteRemainder;
        }


        Percolation.open(RandomRow, RandomCol);
    }

    private static void initializeRandomizedSiteArray(int n) {
        RandomizedSites = new int[n * n];
        for (int i = 1; i <= RandomizedSites.length; i++)
            RandomizedSites[i - 1] = i;

        StdRandom.shuffle(RandomizedSites);
    }

    // perform independent trials on an n-by-n grid
    public static void percolationStats(int n, int trials) {
        while (!Percolation.percolates()) {
            openNextClosedSite();
        }

        if (CurrentTrial <= trials - 1) {
            double TotalNumberOfSites = Size * Size;
            double NumberOfSitesOpen = Percolation.numberOfOpenSites();
            double CurrentThreshold = NumberOfSitesOpen / TotalNumberOfSites;
            PercolationThreshold[CurrentTrial] = CurrentThreshold;

            CurrentTrial++;
            nextClosedSite = 0;
            Percolation.percolation(Size);
            initializeRandomizedSiteArray(Size);
            percolationStats(n, trials);
        } else {
            System.out.println("Hello World!");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 1.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 1.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 1.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 1.0;
    }

    // test client (see below)
    public static void main(String[] args) {
        Size = Integer.parseInt(args[0]);
        Trials = Integer.parseInt(args[1]);
        PercolationThreshold = new double[Trials];

        Percolation.percolation(Size);
        initializeRandomizedSiteArray(Size);
        percolationStats(Size, Trials);


    }

}
