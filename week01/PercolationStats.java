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
        while (!Percolation.percolates()) { // Since Percolation uses static variables, we are creating a memory leak by continuing to re-running this method
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
        }
    }

    // sample mean of percolation threshold
    public static double mean() {
        return StdStats.mean(PercolationThreshold);
    }

    // sample standard deviation of percolation threshold
    public static double stddev() {
        return StdStats.stddev(PercolationThreshold);
    }

    // low endpoint of 95% confidence interval
    public static double confidenceLo() {
        double mean = mean();
        double s = stddev();
        double tSqrt = Math.sqrt(PercolationThreshold.length);
        return (mean - (1.96 * s / tSqrt));
    }

    // high endpoint of 95% confidence interval
    public static double confidenceHi() {
        double mean = mean();
        double s = stddev();
        double tSqrt = Math.sqrt(PercolationThreshold.length);
        return mean + (1.96 * s / tSqrt);
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch time;
        time = new Stopwatch();
        Size = Integer.parseInt(args[0]);
        Trials = Integer.parseInt(args[1]);
        PercolationThreshold = new double[Trials];

        Percolation.percolation(Size);
        initializeRandomizedSiteArray(Size);
        percolationStats(Size, Trials);

        // System.out.println(Arrays.toString(PercolationThreshold));

        System.out.printf("Mean value is         %f \n", mean());
        System.out.printf("Standard deviation is %f \n", stddev());
        System.out.printf("95 confidence interval =[ %f, %f ] \n", confidenceLo(), confidenceHi());
        System.out.printf("Time Elapsed: %f", time.elapsedTime());


    }

}
