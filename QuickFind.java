/**
 * Created by Karan on 5/22/16.
 */

public class QuickFind implements unionfind{

    int arr[];
    int n;

    public QuickFind(int N) {
        this.n = N;
        arr = new int[N];
        //every site is connected to itself initially
        for(int i=0; i<N; i++){
            arr[i] = i;
        }
    }

    public void union(int p, int q) {
    
    }

    @Override
    public int find(int p) {
        return 0;
    }

    @Override
    public boolean connected(int p, int q) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
};
