import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by Karan on 6/19/16.
 */

public class Subset {
    public static void main(String[] args){

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        int k = Integer.parseInt(args[0]);
        String s;
        int n = 0;
        while(!StdIn.isEmpty()){
            s = StdIn.readString();
            rq.enqueue(s);
            n++;
        }

        for(int i=0; i<k; i++){
            StdOut.println(rq.dequeue());
        }

    }
}
