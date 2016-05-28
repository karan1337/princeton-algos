/**
 * Monte Carlo Simulation
 * Created by Karan on 5/27/16.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double fractionthreshold[];
    private int T;

    public PercolationStats(int N, int T){

        if(N<=0){
            throw new IllegalArgumentException("Invalid N");
        }

        if(T<=0){
            throw new IllegalArgumentException("Invalid T");
        }

        this.T = T;
        fractionthreshold = new double[T];

        for(int i=0; i<T; i++) {
            Percolation per = new Percolation(N);
            int open = 0;
            while (!per.percolates()) {
                int row = StdRandom.uniform(1, N + 1);
                int col = StdRandom.uniform(1, N + 1);
                if (!per.isOpen(row, col)) {
                    per.open(row, col);
                    open++;
                }
            }
             fractionthreshold[i] = ((double)open)/(N*N);
        }
    }

    public double mean(){

        return StdStats.mean(fractionthreshold);
        /*
        double fracSum = 0.0;
        for(int i=0; i<T; i++){
            fracSum += fractionthreshold[i];
        }
        return fracSum/T;
        */
    }

    public double stddev(){
        return StdStats.stddev(fractionthreshold);
    }

    public double confidenceLo(){
        return (mean() - ((1.96*stddev())/Math.sqrt((double)this.T)));
    }

    public double confidenceHi(){
        return (mean() + ((1.96*stddev())/Math.sqrt((double)this.T)));
    }

    public static void main(String args[]){
        /*
        In in = new In(args[0]);
        int N = in.readInt();         // N-by-N percolation system
        In in2 = new In(args[1]);
        int T = in.readInt();        //#times experiment should be run
        */

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N,T);
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}

