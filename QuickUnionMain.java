/**
 * Created by Karan on 5/22/16.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionMain {
    public static void main(String args[]){

        int N = StdIn.readInt();
        QuickUnion qf = new QuickUnion(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (qf.connected(p, q)) continue;

            qf.union(p, q);
            qf.printIDs();
            StdOut.println(p + " " + q);
        }
        StdOut.println(qf.count() + " components");

    }
}
