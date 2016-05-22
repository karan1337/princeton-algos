/**
 * Created by Karan on 5/22/16.
 */

public class QuickFind implements unionfind{

    int id[];
    int n;

    public QuickFind(int N) {
        this.n = N;
        id = new int[N];
        //every site is connected to itself initially
        for(int i=0; i<N; i++){
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        for(int i=0; i<n; i++){
            if(id[i] == pid){
                id[i] = qid;
            }
        }
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return (id[p] == id[q]);
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
};
