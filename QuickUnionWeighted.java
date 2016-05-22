/**
 * Created by Karan on 5/22/16.
 */
public class QuickUnionWeighted implements unionfind {

    int id[];
    int n;
    int sz[];

    public QuickUnionWeighted(int N) {
        this.n = N;
        id = new int[N];
        sz = new int[N];
        //every site is connected to itself initially
        for(int i=0; i<N; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int rootp = find(p);
        int rootq = find(q);

        if(sz[rootq] > sz[rootp]){
            id[rootp] = rootq;
            sz[rootq] += sz[rootp];
        }
        else{
            id[rootq] = rootp;
            sz[rootp] += sz[rootq];
        }


    }

    @Override
    public int find(int p) {
        while(p != id[p]){
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return false;
    }

    @Override
    public int count() {
        int num = 0;
        //does not scale well (use perfect hashing for scalability)
        for(int i=0; i<n; i++) {
            int found = 0;
            for (int j = 0; j < n && i!=j; j++) {
                if (id[i] == id[j]) {
                    found = 1;
                    break;
                }
            }
            if(found == 0){
                num++;
            }
        }
        return num;
    }

    public void printIDs(){
        for(int i=0; i<n; i++){
            System.out.print(id[i] + " ");
        }
        System.out.println();
    }
}
