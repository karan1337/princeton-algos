/**
 * Created by Karan on 5/25/16.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int id[][];
    int n;
    public WeightedQuickUnionUF wqf;
    int virtualtoppt=-1;
    int virtualbottompt = -1;

    public boolean isValid(int i, int j){
        if(i < n && j<n && i>=0 && j>=0){
            return true;
        }
        else{
            return false;
        }
    }

    public Percolation(int N){

        if(N<=0){
            throw new IllegalArgumentException(N+"");

        }

        this.n = N+1;
        //this allows addressing to go from 0->n-1 which includes 1->N
        id = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                id[i][j] = 0;
            }
        }

        wqf = new WeightedQuickUnionUF(xyTo1D(this.n,this.n));

        //connect all top row elements to the top virtual point (0,0)
        this.virtualtoppt = xyTo1D(0,0);

        //connect all bottom row elements to the bottom virtual point (n-1,0)
        this.virtualbottompt = xyTo1D(n-1,0);

    }

    public void open(int i, int j){
        if(!isValid(i,j)){
            throw new IllegalArgumentException(i+","+j);
        }
        id[i][j] = 1;

        int newpoint = xyTo1D(i,j);

        if(i==1){
            wqf.union(newpoint,virtualtoppt);
        }

        if(i==n-1){
            wqf.union(newpoint,virtualbottompt);
        }
        //call union 4 times for each surrounding point (i-1,j), (i,j-1), (i,j+1), (i+1,j)

        //up
        if(isValid(i-1,j) && isOpen(i-1,j)){
            int pt = xyTo1D(i-1,j);
            wqf.union(newpoint, pt);
        }
        //left
        if(isValid(i,j-1) && isOpen(i,j-1)){
            int pt = xyTo1D(i,j-1);
            wqf.union(newpoint, pt);
        }
        //right
        if(isValid(i,j+1) && isOpen(i,j+1)){
            int pt = xyTo1D(i,j+1);
            wqf.union(newpoint, pt);
        }
        //down
        if(isValid(i+1,j) && isOpen(i+1,j)){
            int pt = xyTo1D(i+1,j);
            wqf.union(newpoint, pt);
        }
    }

    public boolean isOpen(int i, int j){

        if(!isValid(i,j)){
            throw new IllegalArgumentException(i+","+j);
        }

        if(id[i][j] == 1){
            return true;
        }
        else{
            return false;
        }
    }

    //convert 2d point into 1d
    public int xyTo1D(int i, int j){
        return (i*(n+1) + j*(n+2));
    }

    public boolean isFull(int i, int j){

        if(!isValid(i,j)){
            throw new IllegalArgumentException(i+","+j);
        }

        int newpoint = xyTo1D(i,j);
        return wqf.connected(newpoint, this.virtualtoppt);
    }

    public boolean percolates(){

        return wqf.connected(virtualbottompt, virtualtoppt);
    }
};
